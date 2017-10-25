/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.util.HashSet;
import java.util.Set;

import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.ComentarioEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.GaleriaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.SagaEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;

/**
 * The Class DTOToEntityAdapter.
 */
public final class DTOToEntityAdapter {

    /**
     * Adaptar usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return
     */
    public static UsuarioEntity adaptarUsuario(final UsuarioDTO nuevoUsuario) {
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioId(nuevoUsuario.getUsuarioId());
        usuario.setUsuarioLogin(nuevoUsuario.getUsuarioLogin());
        usuario.setUsuarioContrasena(nuevoUsuario.getUsuarioContrasena());
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
     * @param libroDTO
     *            the libro dto
     * @return the libro entity
     */
    public static LibroEntity adaptarLibro(final LibroDTO libroDTO) {
        final LibroEntity libroEntity = new LibroEntity();
        libroEntity.setAnoEdicion(libroDTO.getAnoEdicion());
        libroEntity.setAutores(adaptarAutores(libroDTO.getAutores()));
        libroEntity.setCitaLibro(libroDTO.getCitaLibro());
        libroEntity.setEditorial(adaptarEditorial(libroDTO.getEditorial()));
        libroEntity.setEnlaceAmazon(libroDTO.getEnlaceAmazon());
        libroEntity.setGeneros(adaptarGeneros(libroDTO.getGeneros()));
        libroEntity.setLibroId(libroDTO.getLibroId());
        libroEntity.setResumen(libroDTO.getResumen());
        libroEntity.setSaga(adpatarSaga(libroDTO.getSaga()));
        libroEntity.setTitulo(libroDTO.getTitulo());
        libroEntity.setUrlImagen(libroDTO.getUrlImagen());
        libroEntity.setAnoPublicacion(libroDTO.getAnoPublicacion());
        libroEntity.setNumeroPaginas(libroDTO.getNumeroPaginas());
        libroEntity.setTituloOriginal(libroDTO.getTituloOriginal());
        return libroEntity;
    }

    /**
     * Adaptar entrada.
     *
     * @param entradaDTO
     *            entrada DTO
     * @return the entrada entity
     */
    public static EntradaEntity adaptarEntrada(final EntradaDTO entradaDTO, final LibroDTO libroEntrada) {
        final EntradaEntity entradaEntity = new EntradaEntity();
        entradaEntity.setEntradaId(entradaDTO.getEntradaId());
        entradaEntity.setContenidoEntrada(entradaDTO.getContenidoEntrada());
        entradaEntity.setEntradaAutor(adaptarUsuario(entradaDTO.getAutor()));
        entradaEntity.setEstadoEntrada(entradaDTO.getEstadoEntrada());
        if (libroEntrada != null) {
            entradaEntity.setLibroEntrada(adaptarLibro(libroEntrada));
        }
        entradaEntity.setNumeroComentarios(entradaDTO.getNumeroComentarios());
        entradaEntity.setOrden(entradaDTO.getOrden());
        entradaEntity.setPadreEntrada(
                entradaDTO.getPadreEntrada() != null ? adaptarEntrada(entradaDTO.getPadreEntrada(), null) : null);
        entradaEntity.setPermitirComentarios(entradaDTO.getPermitirComentarios());
        entradaEntity.setResumenEntrada(entradaDTO.getResumenEntrada());
        entradaEntity.setTipoEntrada(entradaDTO.getTipoEntrada());
        entradaEntity.setTituloEntrada(entradaDTO.getTituloEntrada());
        entradaEntity.setUrlEntrada(entradaDTO.getUrlEntrada());
        entradaEntity.setImagenDestacada(entradaDTO.getImagenDestacada());
        entradaEntity.setEtiquetas(adaptarEtiquetas(entradaDTO.getEtiquetas()));
        return entradaEntity;
    }

    private static SagaEntity adpatarSaga(final SagaDTO saga) {
        SagaEntity entity = null;
        if (saga != null) {
            entity = new SagaEntity();
            entity.setSagaId(saga.getSagaId());
            entity.setDescripcionSaga(saga.getDescripcionSaga());
        }
        return entity;
    }

    /**
     * Adaptar editorial.
     *
     * @param editorial
     *            the editorial
     * @return the editorial entity
     */
    private static EditorialEntity adaptarEditorial(final EditorialDTO editorial) {
        final EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setEditorialId(editorial.getEditorialId());
        editorialEntity.setNombreEditorial(editorial.getNombreEditorial());
        return editorialEntity;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            the autores
     * @return the establece
     */
    public static Set<AutorEntity> adaptarAutores(final Set<AutorDTO> autores) {
        final Set<AutorEntity> autoresEntities = new HashSet<AutorEntity>();
        for (final AutorDTO autorDTO : autores) {
            autoresEntities.add(adaptarAutor(autorDTO));
        }
        return autoresEntities;
    }

    /**
     * Adaptar autor.
     *
     * @param autorDTO
     *            the autor dto
     * @return the autor entity
     */
    public static AutorEntity adaptarAutor(final AutorDTO autorDTO) {
        final AutorEntity entity = new AutorEntity();
        entity.setAutorId(autorDTO.getAutorId());
        entity.setNombre(autorDTO.getNombre());
        return entity;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            the autores
     * @return the establece
     */
    public static Set<GeneroEntity> adaptarGeneros(final Set<GeneroDTO> generos) {
        final Set<GeneroEntity> generosEntities = new HashSet<GeneroEntity>();
        for (final GeneroDTO generoDTO : generos) {
            generosEntities.add(adaptarGenero(generoDTO));
        }
        return generosEntities;
    }

    /**
     * Adaptar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero entity
     */
    public static GeneroEntity adaptarGenero(final GeneroDTO generoDTO) {
        final GeneroEntity entity = new GeneroEntity();
        entity.setGenero_id(generoDTO.getGeneroId());
        entity.setNombre(generoDTO.getNombre());
        return entity;
    }

    /**
     * Adaptar etiquetas.
     *
     * @param etiquetas
     *            etiquetas
     * @return the establece
     */
    public static Set<EtiquetaEntity> adaptarEtiquetas(final Set<EtiquetaDTO> etiquetas) {
        final Set<EtiquetaEntity> etiquetasEntities = new HashSet<EtiquetaEntity>();
        for (final EtiquetaDTO etiquetaDTO : etiquetas) {
            etiquetasEntities.add(adaptarEtiqueta(etiquetaDTO));
        }
        return etiquetasEntities;
    }

    /**
     * Adaptar etiqueta.
     *
     * @param etiquetaDTO
     *            etiqueta entity
     * @return the etiqueta DTO
     */
    public static EtiquetaEntity adaptarEtiqueta(final EtiquetaDTO etiquetaDTO) {
        final EtiquetaEntity etiquetaEntity = new EtiquetaEntity();
        etiquetaEntity.setEtiqueta_id(etiquetaDTO.getEtiquetaId());
        etiquetaEntity.setNombre(etiquetaDTO.getNombreEtiqueta());
        return etiquetaEntity;
    }

    /**
     * Adaptar etiqueta.
     *
     * @param etiquetaEntity
     *            etiqueta entity
     * @return the etiqueta DTO
     */
    public static GaleriaDTO adaptarGaleria(final GaleriaEntity galeriaEntity) {
        final GaleriaDTO galeriaDTO = new GaleriaDTO();
        galeriaDTO.setGaleriaId(galeriaEntity.getGaleriaId());
        galeriaDTO.setEntrada(galeriaEntity.getEntrada().getUrlEntrada());
        galeriaDTO.setFotografias(ConversionUtils.divide(galeriaEntity.getFotografias()));
        galeriaDTO.setColumnas(galeriaEntity.getColumnas());
        return galeriaDTO;
    }

    /**
     * Adaptar comentario.
     *
     * @param comentarioEntity
     *            comentario entity
     * @return the comentario DTO
     */
    public static ComentarioDTO adaptarComentario(final ComentarioEntity comentarioEntity) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setAutor(ConversionUtils.obtenerUsuarioBasico(comentarioEntity.getAutor()));
        comentarioDTO.setVotosPositivos(ConversionUtils.divide(comentarioEntity.getVotosPositivos()).size());
        comentarioDTO.setVotosNegativos(ConversionUtils.divide(comentarioEntity.getVotosNegativos()).size());
        comentarioDTO.setTextoComentario(comentarioEntity.getTextoComentario());
        comentarioDTO.setEsBan(comentarioEntity.isEsBan());
        comentarioDTO.setEsSpoiler(comentarioEntity.isEsSpoiler());
        comentarioDTO.setComentarioId(comentarioEntity.getComentarioId());
        return comentarioDTO;
    }

    /**
     * Adaptar puntuacion.
     *
     * @param puntuacionEntity
     *            puntuacion entity
     * @return the puntuacion DTO
     */
    public static PuntuacionDTO adaptarPuntuacion(final PuntuacionEntity puntuacionEntity) {
        PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
        puntuacionDTO.setAutor(ConversionUtils.obtenerUsuarioBasico(puntuacionEntity.getAutor()));
        puntuacionDTO.setComentario(puntuacionEntity.getComentario());
        puntuacionDTO.setEsPuntuacionMomoko(puntuacionEntity.isEsPuntuacionMomoko());
        puntuacionDTO.setLibroId(puntuacionEntity.getLibro().getLibroId());
        puntuacionDTO.setValor(puntuacionEntity.getValor());
        return puntuacionDTO;
    }
}
