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
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.SagaEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;

/**
 * The Class EntityToDTOAdapter.
 */
public final class EntityToDTOAdapter {

    /**
     * Adaptar usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return
     */
    public static UsuarioDTO adaptarUsuario(final UsuarioEntity nuevoUsuario) {
        final UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(nuevoUsuario.getUsuarioId());
        usuario.setUsuarioLogin(nuevoUsuario.getUsuarioLogin());
        usuario.setUsuarioEmail(nuevoUsuario.getUsuarioEmail());
        usuario.setUsuarioFechaRegistro(nuevoUsuario.getUsuarioFechaRegistro());
        usuario.setUsuarioNick(nuevoUsuario.getUsuarioNick());
        usuario.setUsuarioUrl(nuevoUsuario.getUsuarioUrl());
        usuario.setUsuarioStatus(nuevoUsuario.getUsuarioStatus());
        usuario.setUsuarioRolId(nuevoUsuario.getUsuarioRolId());
        return usuario;
    }

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
        libroDTO.setSaga(libroEntity.getSaga() != null ? adaptarSaga(libroEntity.getSaga()) : null);
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        libroDTO.setAutores(adaptarAutores(libroEntity.getAutores()));
        libroDTO.setGeneros(adaptarGeneros(libroEntity.getGeneros()));
        libroDTO.setAnoPublicacion(libroEntity.getAnoPublicacion());
        libroDTO.setNumeroPaginas(libroEntity.getNumeroPaginas());
        libroDTO.setTituloOriginal(libroEntity.getTituloOriginal());
        return libroDTO;
    }

    /**
     * Adaptar entrada.
     *
     * @param entradaEntity
     *            entrada DTO
     * @return the entrada entity
     */
    public static EntradaDTO adaptarEntrada(final EntradaEntity entradaEntity) {
        final EntradaDTO entradaDTO = new EntradaDTO();
        entradaDTO.setEntradaId(entradaEntity.getEntradaId());
        entradaDTO.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        entradaDTO.setAutor(adaptarUsuario(entradaEntity.getEntradaAutor()));
        entradaDTO.setEstadoEntrada(entradaEntity.getEstadoEntrada());
        if (entradaEntity.getLibroEntrada() != null) {
            entradaDTO.setLibroEntrada(entradaEntity.getLibroEntrada().getTitulo());
        }
        entradaDTO.setNumeroComentarios(entradaEntity.getNumeroComentarios());
        entradaDTO.setOrden(entradaEntity.getOrden());
        entradaDTO.setPadreEntrada(
                entradaEntity.getPadreEntrada() != null ? adaptarEntrada(entradaEntity.getPadreEntrada()) : null);
        entradaDTO.setPermitirComentarios(entradaEntity.getPermitirComentarios());
        entradaDTO.setResumenEntrada(entradaEntity.getResumenEntrada());
        entradaDTO.setTipoEntrada(entradaEntity.getTipoEntrada());
        entradaDTO.setTituloEntrada(entradaEntity.getTituloEntrada());
        entradaDTO.setUrlEntrada(entradaEntity.getUrlEntrada());
        return entradaDTO;
    }

    /**
     * Adaptar saga.
     *
     * @param sagaEntity
     *            saga entity
     * @return the saga DTO
     */
    private static SagaDTO adaptarSaga(final SagaEntity sagaEntity) {
        final SagaDTO sagaDTO = new SagaDTO();
        sagaDTO.setSagaId(sagaEntity.getSagaId());
        sagaDTO.setDescripcionSaga(sagaEntity.getDescripcionSaga());
        return sagaDTO;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            autores
     * @return the establece
     */
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

    /**
     * Adaptar etiquetas.
     *
     * @param etiquetas
     *            etiquetas
     * @return the establece
     */
    public static Set<EtiquetaDTO> adaptarEtiquetas(final Set<EtiquetaEntity> etiquetas) {
        final Set<EtiquetaDTO> etiquetasDTO = new HashSet<EtiquetaDTO>();
        for (final EtiquetaEntity etiquetaEntity : etiquetas) {
            etiquetasDTO.add(adaptarEtiqueta(etiquetaEntity));
        }
        return etiquetasDTO;
    }

    /**
     * Adaptar etiqueta.
     *
     * @param etiquetaEntity
     *            etiqueta entity
     * @return the etiqueta DTO
     */
    public static EtiquetaDTO adaptarEtiqueta(final EtiquetaEntity etiquetaEntity) {
        final EtiquetaDTO etiquetaDTO = new EtiquetaDTO();
        etiquetaDTO.setEtiquetaId(etiquetaEntity.getEtiqueta_id());
        etiquetaDTO.setNombreEtiqueta(etiquetaEntity.getNombre());
        return etiquetaDTO;
    }

}
