/**
 * LibroServiceImpl.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.momoko.es.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.exceptions.NoExisteGeneroException;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.GenreEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.repository.AutorRepository;
import com.momoko.es.backend.model.repository.EditorialRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.service.EditorialService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;

/**
 * The Class LibroServiceImpl.
 */
@Service
public class LibroServiceImpl implements LibroService {

    private final EditorialService editorialService;

    private final LibroRepository libroRepository;

    private final EntradaRepository entradaRepository;

    private final AutorRepository autorRepository;

    private final EditorialRepository editorialRepository;

    private final StorageService almacenImagenes;

    private final PuntuacionRepository puntuacionRepository;

    private final GeneroRepository generoRepository;

    @Autowired(required = false)
    public LibroServiceImpl(EditorialService editorialService, LibroRepository libroRepository,
                            EntradaRepository entradaRepository, AutorRepository autorRepository,
                            EditorialRepository editorialRepository, StorageService almacenImagenes,
                            PuntuacionRepository puntuacionRepository, GeneroRepository generoRepository) {
        this.editorialService = editorialService;
        this.libroRepository = libroRepository;
        this.entradaRepository = entradaRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
        this.almacenImagenes = almacenImagenes;
        this.puntuacionRepository = puntuacionRepository;
        this.generoRepository = generoRepository;
    }

    @Override
    public List<LibroDTO> recuperarLibros() {
        final List<LibroDTO> listaLibros = new ArrayList<>();
        final Iterable<LibroEntity> libroEntityIterable = this.libroRepository.findAll();
        final String urlImageServer = this.almacenImagenes.getUrlImageServer();
        for (final LibroEntity libroEntity : libroEntityIterable) {

            final LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);

            // Nota Momoko del libro
            final PuntuacionEntity puntuacionEntity = this.puntuacionRepository
                    .findOneByEsPuntuacionMomokoAndLibro(true, libroEntity);
            if (puntuacionEntity != null) {
                libroDTO.setNotaMomoko(puntuacionEntity.getValor());
                libroDTO.setComentarioNotaMomoko(puntuacionEntity.getComentario());
            }
            if (libroDTO.getUrlImagen() != null) {
                libroDTO.setUrlImagen(urlImageServer + libroDTO.getUrlImagen());
            }
            final String autoresString = MomokoUtils.generarAutoresString(libroDTO);

            libroDTO.setAutoresString(autoresString);

            final String generosString = MomokoUtils.generarGenerosString(libroDTO);

            libroDTO.setGenerosString(generosString);

            listaLibros.add(libroDTO);
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

            final EditorialEntity editorialObra = this.editorialService
                    .obtenerEditorialOCrear(libroEntity.getEditorial());
            libroEntity.setEditorial(editorialObra);
            final Set<GenreEntity> generosObra = obtenerGenerosObra(libroEntity);
            libroEntity.setGeneros(generosObra);

            // libroEntity.
            if (libroEntity.getUrlImagen() != null) {
                libroEntity.setUrlImagen(soloNombreImagenAPortadas(libroEntity.getUrlImagen()));
            }
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

    private Set<GenreEntity> obtenerGenerosObra(final LibroEntity libroEntity) throws NoExisteGeneroException {
        final Set<GenreEntity> generosObra = new HashSet<GenreEntity>();
        if (CollectionUtils.isNotEmpty(libroEntity.getGeneros())) {
            for (final GenreEntity genero : libroEntity.getGeneros()) {

                final GenreEntity generoBD = this.generoRepository
                        .findOneByNombreAndFechaBajaIsNull(genero.getNombre());
                if (generoBD == null) {
                    throw new NoExisteGeneroException("No existe el genero seleccionado en la BD");

                }
                generosObra.add(generoBD);
            }
        }
        return generosObra;
    }

    private String soloNombreImagenAPortadas(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return "portadas/" + lista[elementos - 1];
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
    public LibroDTO obtenerLibroConEntradas(String urlLibro) {
        LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        return EntityToDTOAdapter.adaptarLibro(libroEntity, true);
    }

    @Override
    public ObtenerFichaLibroResponse obtenerFichaLibroResponse(final String urlLibro) {
        final ObtenerFichaLibroResponse respuesta = new ObtenerFichaLibroResponse();
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        respuesta.setDatosEntrada(ConversionUtils.obtenerDatosEntradaFromEntradaEntityList(libroEntity.getEntradas()));
        if (libroEntity != null) {
            final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                    .findByLibrosEntradaIn(Arrays.asList(libroEntity), new PageRequest(0, 4));
            Collections.sort(entradasRelacionadas);
            final List<EntradaSimpleDTO> entradasBasicas = ConversionUtils.obtenerEntradasBasicas(entradasRelacionadas,
                    true);
            // generar miniaturas de 304 x 221
            for (final EntradaSimpleDTO entradaSimpleDTO : entradasBasicas) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 304, 221, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

            respuesta.setTresUltimasEntradas(entradasBasicas);

            final List<DatoEntradaDTO> listaDatosEntradas = ConversionUtils.obtenerDatosEntradaFromEntradaEntityList(entradasRelacionadas);
            LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);
            libroDTO = MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroDTO);
            // Nota Momoko del libro
            final PuntuacionEntity puntuacionEntity = this.puntuacionRepository
                    .findOneByEsPuntuacionMomokoAndLibro(true, libroEntity);
            if (puntuacionEntity != null) {
                libroDTO.setNotaMomoko(puntuacionEntity.getValor());
                libroDTO.setComentarioNotaMomoko(puntuacionEntity.getComentario());
            }
            libroDTO.setEntradasLibro(listaDatosEntradas);

            if (libroEntity.getOrdenSaga() != null) {
                libroDTO.setSaga(EntityToDTOAdapter.adaptarSaga(libroEntity.getSaga(), false, false));
            }

            respuesta.setLibro(libroDTO);
        }
        return respuesta;
    }



    @Override
    public List<LibroSimpleDTO> obtenerLibrosParecidos(final LibroDTO libro, final int numeroLibros) {
        final Set<GenreEntity> generos = DTOToEntityAdapter.adaptarGeneros(libro.getGeneros());
        final List<Integer> idsGeneros = new ArrayList<>();
        for (final GenreEntity generoEntity : generos) {
            idsGeneros.add(generoEntity.getGeneroId());
        }

        final List<LibroEntity> listaLibrosParecidos = this.libroRepository
                .findLibrosParecidosByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(idsGeneros, libro.getLibroId(),
                        new PageRequest(0, numeroLibros));
        final List<Integer> listaLibrosIds = new ArrayList<>();
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listaLibrosParecidos)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }

        }
        return ConversionUtils.obtenerLibrosBasicos(listaLibrosParecidos, mapaPuntacionMomokoPorLibro);
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(final GenreDTO genero, final int numeroLibros,
            final int pagina) {

        final List<Integer> idsGeneros = new ArrayList<>();

        idsGeneros.add(genero.getGeneroId());

        final List<LibroEntity> listaLibrosGenero = this.libroRepository.obtenerLibrosConAnalisisGeneroPorFecha(
                idsGeneros, Calendar.getInstance().getTime(), new PageRequest(pagina, numeroLibros));

        return ConversionUtils.obtenerLibrosBasicos(listaLibrosGenero, null);
    }

    @Override
    public Integer obtenerNumeroLibrosConAnalisisGenero(final GenreDTO generoDTO) {
        final Long numeroResultados = this.entradaRepository
                .findNumberEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
                        Arrays.asList(generoDTO.getGeneroId()));
        return numeroResultados.intValue();
    }

    @Override
    public List<LibroDTO> obtenerLibros(final List<String> librosUrl) {
        final List<LibroEntity> listaLibros = this.libroRepository.findByUrlLibroIn(librosUrl);
        final List<LibroDTO> listaLibrosDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listaLibros)) {

            listaLibrosDTO.addAll(EntityToDTOAdapter.adaptarLibros(listaLibros));
            for (final LibroDTO libroDTO : listaLibrosDTO) {

                try {
                    final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes
                            .getImageDimensions(libroDTO.getUrlImagen());
                    libroDTO.setPortadaHeight(alturaAnchura.getAltura());
                    libroDTO.setPortadaWidth(alturaAnchura.getAnchura());
                    final String url = this.almacenImagenes.getUrlImageServer();

                    libroDTO.setUrlImagen(
                            this.almacenImagenes.obtenerMiniatura(libroDTO.getUrlImagen(), 200, 310, true));
                    final Set<GenreDTO> generosImagenes = new HashSet<GenreDTO>();
                    for (final GenreDTO generoDTO : libroDTO.getGeneros()) {
                        generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                        generosImagenes.add(generoDTO);
                    }
                    libroDTO.setGeneros(generosImagenes);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaLibrosDTO;
    }

    @Override
    public EntradaDTO obtenerOpinionesLibro(final String urlLibro) {
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        final List<EntradaEntity> entradas = libroEntity.getEntradas();
        EntradaDTO analisisLibro = null;
        for (final EntradaEntity entradaEntity : entradas) {
            if (TipoEntrada.OPINIONES.getValue().equals(entradaEntity.getTipoEntrada())) {
                analisisLibro = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
                break;
            }
        }
        return analisisLibro;
    }

    @Override
    public BigDecimal obtenerPuntucionMomokoLibro(final String urlLibro) {
        return this.puntuacionRepository.findOneByEsPuntuacionMomokoAndLibroUrl(urlLibro);
    }

}