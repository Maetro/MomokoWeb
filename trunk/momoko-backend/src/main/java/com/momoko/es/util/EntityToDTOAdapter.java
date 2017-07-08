/**
 * EntityToDTOAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import com.momoko.es.api.dto.LibroDTO;
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
        libroDTO.setAutorId(libroEntity.getAutorId());
        libroDTO.setCitaLibro(libroEntity.getCitaLibro());
        libroDTO.setEditorialId(libroEntity.getEditorialId());
        libroDTO.setEnlaceAmazon(libroEntity.getEnlaceAmazon());
        libroDTO.setGeneroId(libroEntity.getGeneroId());
        libroDTO.setLibroId(libroEntity.getLibroId());
        libroDTO.setResumen(libroEntity.getResumen());
        libroDTO.setSagaId(libroEntity.getSagaId());
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        return libroDTO;
    }

}
