/**
 * LibroServiceImpl.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.repository.AutorRepository;
import com.momoko.es.backend.model.repository.EditorialRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class LibroServiceImpl.
 */
@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public Integer crearLibro(final LibroDTO nuevoLibro) {
        // TODO Auto-generated method stub
        return null;
    }

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
            if (libroEntity.getLibroId() != null) {
                libroEntity.setUsuarioModificacion("RMaetro@gmail.com");
                libroEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                libroEntity.setUsuarioAlta("RMaetro@gmail.com");
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
                    // TODO: poner algo que identifique al usuario.
                    nuevoAutor.setUsuarioAlta("RMaetro@gmail.com");
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
    public List<GeneroDTO> obtenerTodosGeneros() {
        final List<GeneroDTO> listaGeneros = new ArrayList<GeneroDTO>();
        final Iterable<GeneroEntity> generoEntityIterable = this.generoRepository.findAll();
        for (final GeneroEntity generoEntity : generoEntityIterable) {
            listaGeneros.add(EntityToDTOAdapter.adaptarGenero(generoEntity));
        }
        return listaGeneros;
    }

}
