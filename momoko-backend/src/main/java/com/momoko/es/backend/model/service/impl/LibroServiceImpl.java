/**
 * LibroServiceImpl.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.response.ObtenerFichaLibroResponse;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.repository.AutorRepository;
import com.momoko.es.backend.model.repository.EditorialRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class LibroServiceImpl.
 */
@Service
public class LibroServiceImpl implements LibroService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private AutorRepository autorRepository;

    @Autowired(required = false)
    private EditorialRepository editorialRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public List<LibroDTO> recuperarLibros() {
        final List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
        final Iterable<LibroEntity> libroEntityIterable = this.libroRepository.findAll();
        for (final LibroEntity libroEntity : libroEntityIterable) {
            listaLibros.add(EntityToDTOAdapter.adaptarLibro(libroEntity));
        }
        return listaLibros;
    }

    @Override
    public LibroDTO guardarLibro(final LibroDTO libroAGuardar) throws Exception {
        final LibroEntity libroEntity = DTOToEntityAdapter.adaptarLibro(libroAGuardar);
        // Comprobamos si el autor existe.
        final List<LibroEntity> coincidencias = this.libroRepository.findByTitulo(libroAGuardar.getTitulo());
        if ((CollectionUtils.isEmpty(coincidencias)) || ((libroAGuardar.getLibroId() != null))) {
            if ((libroEntity.getLibroId() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!libroEntity.getLibroId().equals(coincidencias.get(0).getLibroId()))) {
                    throw new Exception("El titulo del libro ya se esta utilizando");
                }
            }
            final Set<AutorEntity> autoresObra = obtenerOGuardarAutoresObra(libroEntity);
            libroEntity.setAutores(autoresObra);
            final EditorialEntity editorialObra = obtenerOGuardarEditorialObra(libroEntity);
            libroEntity.setEditorial(editorialObra);
            // libroEntity.
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();
            if (libroEntity.getLibroId() != null) {
                final LibroEntity libroBD = this.libroRepository.findOne(libroEntity.getLibroId());
                libroEntity.setFechaAlta(libroBD.getFechaAlta());
                libroEntity.setUsuarioAlta(libroBD.getUsuarioAlta());
                libroEntity.setUsuarioModificacion(currentPrincipalName);
                libroEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                libroEntity.setUsuarioAlta(currentPrincipalName);
                libroEntity.setFechaAlta(Calendar.getInstance().getTime());
            }

            return EntityToDTOAdapter.adaptarLibro(this.libroRepository.save(libroEntity));
        } else {
            throw new Exception("El titulo del libro ya se esta utilizando");
        }

    }

    /**
     * Obtener o guardar editorial obra.
     *
     * @param libroEntity
     *            the libro entity
     * @return the editorial entity
     */
    private EditorialEntity obtenerOGuardarEditorialObra(final LibroEntity libroEntity) {
        final EditorialEntity editorialABuscar = libroEntity.getEditorial();
        EditorialEntity editorialEncontrada = null;
        if (editorialABuscar != null) {
            editorialEncontrada = this.editorialRepository
                    .findFirstByNombreEditorial(editorialABuscar.getNombreEditorial());
            if (editorialEncontrada == null) {
                // No existe la editorial, la creamos
                editorialEncontrada = new EditorialEntity();
                editorialEncontrada.setNombreEditorial(editorialABuscar.getNombreEditorial());
                editorialEncontrada.setFechaAlta(Calendar.getInstance().getTime());
                // TODO: poner algo que identifique al usuario.
                editorialEncontrada.setUsuarioAlta("RMaetro@gmail.com");
                editorialEncontrada = this.editorialRepository.save(editorialEncontrada);
            }
        }
        return editorialEncontrada;
    }

    /**
     * Obtener o guardar autores obra.
     *
     * @param libroEntity
     *            the libro entity
     * @return the establece
     */
    public Set<AutorEntity> obtenerOGuardarAutoresObra(final LibroEntity libroEntity) {
        final Set<AutorEntity> autoresObra = new HashSet<AutorEntity>();
        if (CollectionUtils.isNotEmpty(libroEntity.getAutores())) {
            for (final AutorEntity autor : libroEntity.getAutores()) {

                final List<AutorEntity> autorEncontradoL = this.autorRepository.findByNombre(autor.getNombre());
                AutorEntity autorEncontrado = null;
                if (CollectionUtils.isEmpty(autorEncontradoL)) {
                    // No existe el autor, lo creamos
                    final AutorEntity nuevoAutor = new AutorEntity();
                    nuevoAutor.setNombre(autor.getNombre());
                    nuevoAutor.setFechaAlta(Calendar.getInstance().getTime());
                    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    final String currentPrincipalName = authentication.getName();
                    nuevoAutor.setUsuarioAlta(currentPrincipalName);
                    nuevoAutor.setFechaAlta(Calendar.getInstance().getTime());
                    autorEncontrado = this.autorRepository.save(nuevoAutor);

                } else {
                    autorEncontrado = autorEncontradoL.get(0);
                }
                autoresObra.add(autorEncontrado);
            }
        }
        return autoresObra;
    }

    @Override
    public List<String> obtenerListaNombresAutores() {
        return this.autorRepository.findAllNombresAutores();
    }

    @Override
    public List<String> obtenerListaNombresEditoriales() {
        return this.editorialRepository.findAllNombresEditoriales();
    }

    @Override
    public List<String> obtenerListaTitulosLibros() {
        return this.libroRepository.findAllTitulosLibros();
    }

    @Override
    public ObtenerFichaLibroResponse obtenerLibro(final String urlLibro) {
        final ObtenerFichaLibroResponse respuesta = new ObtenerFichaLibroResponse();
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        final List<EntradaEntity> entradasRelacionadas = this.entradaRepository.findByLibroEntrada(libroEntity);
        Collections.sort(entradasRelacionadas);
        final List<EntradaSimpleDTO> entradasBasicas = ConversionUtils.obtenerEntradasBasicas(entradasRelacionadas);
        // generar miniaturas de 304 x 221
        for (final EntradaSimpleDTO entradaSimpleDTO : entradasBasicas) {
            try {
                entradaSimpleDTO.setImagenEntrada(
                        this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 304, 221));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        respuesta.setTresUltimasEntradas(entradasBasicas);

        final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();
        if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
            for (final EntradaEntity entradaEntity : entradasRelacionadas) {
                final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                datoEntrada.setTipoEntrada(entradaEntity.getTipoEntrada());
                datoEntrada.setUrlEntrada(entradaEntity.getUrlEntrada());
                listaDatosEntradas.add(datoEntrada);
            }
        }

        final LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);
        try {
            final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes.getImageDimensions(libroEntity.getUrlImagen());
            libroDTO.setPortadaHeight(alturaAnchura.getAltura());
            libroDTO.setPortadaWidth(alturaAnchura.getAnchura());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        libroDTO.setEntradasLibro(listaDatosEntradas);
        respuesta.setLibro(libroDTO);
        return respuesta;
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosParecidos(final LibroDTO libro, final int numeroLibros) {
        final Set<GeneroEntity> generos = DTOToEntityAdapter.adaptarGeneros(libro.getGeneros());
        final List<LibroEntity> listaLibrosParecidos = this.libroRepository
                .findLibroByGeneros(new ArrayList<GeneroEntity>(generos), new PageRequest(0, numeroLibros));
        return ConversionUtils.obtenerLibrosBasicos(listaLibrosParecidos);
    }
}