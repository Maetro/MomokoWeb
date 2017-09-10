/**
 * EntityToDTOAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.util.HashSet;
import java.util.Set;

import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;

/**
 * The Class EntityToDTOAdapter.
 */
public final class EntityToDTOAdapter {

    /**
     * Adaptar libro.
     *
     * @param libroEntity
     *            the libro entity
     * @return the libro dto
     */
    public static LibroDTO adaptarLibro(final LibroEntity libroEntity) {
        final LibroDTO libroDTO = new LibroDTO();
        libroDTO.setAnoEdicion(libroEntity.getAnoEdicion());
        libroDTO.setCitaLibro(libroEntity.getCitaLibro());
        libroDTO.setEditorial(adaptarEditorial(libroEntity.getEditorial()));
        libroDTO.setEnlaceAmazon(libroEntity.getEnlaceAmazon());
        libroDTO.setLibroId(libroEntity.getLibroId());
        libroDTO.setResumen(libroEntity.getResumen());
        libroDTO.setSagaId(libroEntity.getSagaId());
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        libroDTO.setAutores(adaptarAutores(libroEntity.getAutores()));
        libroDTO.setGeneros(adaptarGeneros(libroEntity.getGeneros()));
        libroDTO.setAnoPublicacion(libroEntity.getAnoPublicacion());
        libroDTO.setNumeroPaginas(libroEntity.getNumeroPaginas());
        libroDTO.setTituloOriginal(libroEntity.getTituloOriginal());
        return libroDTO;
    }

    private static Set<AutorDTO> adaptarAutores(final Set<AutorEntity> autores) {
        final Set<AutorDTO> autoresDTO = new HashSet<AutorDTO>();
        for (final AutorEntity autorEntity : autores) {
            autoresDTO.add(adaptarAutor(autorEntity));
        }
        return autoresDTO;
    }

    /**
     * Adaptar autor.
     *
     * @param autorEntity
     *            the autor entity
     * @return the autor dto
     */
    public static AutorDTO adaptarAutor(final AutorEntity autorEntity) {
        final AutorDTO autorDTO = new AutorDTO();
        autorDTO.setAutorId(autorEntity.getAutorId());
        autorDTO.setNombre(autorEntity.getNombre());
        return autorDTO;
    }

    /**
     * Adaptar editorial.
     *
     * @param editorial
     *            the editorial
     * @return the editorial dto
     */
    public static EditorialDTO adaptarEditorial(final EditorialEntity editorial) {
        final EditorialDTO editorialDTO = new EditorialDTO();
        editorialDTO.setEditorialId(editorial.getEditorialId());
        editorialDTO.setNombreEditorial(editorial.getNombreEditorial());
        return editorialDTO;
    }

    /**
     * Adaptar generos.
     *
     * @param generos
     *            the generos
     * @return the establece
     */
    public static Set<GeneroDTO> adaptarGeneros(final Set<GeneroEntity> generos) {
        final Set<GeneroDTO> generosEntities = new HashSet<GeneroDTO>();
        for (final GeneroEntity generoEntity : generos) {
            generosEntities.add(adaptarGenero(generoEntity));
        }
        return generosEntities;
    }

    /**
     * Adaptar genero.
     *
     * @param generoEntity
     *            the genero dto
     * @return the genero entity
     */
    public static GeneroDTO adaptarGenero(final GeneroEntity generoEntity) {
        final GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setGeneroId(generoEntity.getGenero_id());
        generoDTO.setNombre(generoEntity.getNombre());
        return generoDTO;
    }

}
