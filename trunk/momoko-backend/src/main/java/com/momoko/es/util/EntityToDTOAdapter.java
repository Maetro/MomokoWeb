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
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.model.entity.AutorEntity;
import com.momoko.es.model.entity.LibroEntity;

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
        libroDTO.setEditorialId(libroEntity.getEditorialId());
        libroDTO.setEnlaceAmazon(libroEntity.getEnlaceAmazon());
        libroDTO.setGeneroId(libroEntity.getGeneroId());
        libroDTO.setLibroId(libroEntity.getLibroId());
        libroDTO.setResumen(libroEntity.getResumen());
        libroDTO.setSagaId(libroEntity.getSagaId());
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        libroDTO.setAutores(adaptarAutores(libroEntity.getAutores()));
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

}
