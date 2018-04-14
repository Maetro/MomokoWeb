/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.CategoriaDTO;
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
import com.momoko.es.backend.model.entity.CategoriaEntity;
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
        libroEntity.setTitulo(libroDTO.getTitulo());
        libroEntity.setUrlImagen(libroDTO.getUrlImagen());
        libroEntity.setAnoPublicacion(libroDTO.getAnoPublicacion());
        libroEntity.setNumeroPaginas(libroDTO.getNumeroPaginas());
        libroEntity.setTituloOriginal(libroDTO.getTituloOriginal());
        libroEntity.setUrlLibro(libroDTO.getUrlLibro());
        libroEntity.setFechaAlta(libroDTO.getFechaAlta());
        return libroEntity;
    }

    /**
     * Adaptar entrada.
     *
     * @param entradaDTO
     *            entrada DTO
     * @return the entrada entity
     */
    public static EntradaEntity adaptarEntrada(final EntradaDTO entradaDTO, final List<LibroDTO> librosEntrada,
            final UsuarioEntity autor) {
        final EntradaEntity entradaEntity = new EntradaEntity();
        entradaEntity.setEntradaId(entradaDTO.getEntradaId());
        entradaEntity.setContenidoEntrada(entradaDTO.getContenidoEntrada());
        if (autor != null) {
            entradaEntity.setEntradaAutor(autor);
        }
        entradaEntity.setEstadoEntrada(entradaDTO.getEstadoEntrada());
        if (librosEntrada != null) {
            entradaEntity.setLibrosEntrada(adaptarLibros(librosEntrada));
        }
        entradaEntity.setNumeroComentarios(entradaDTO.getNumeroComentarios());
        entradaEntity.setOrden(entradaDTO.getOrden());
        entradaEntity.setPadreEntrada(
                entradaDTO.getPadreEntrada() != null ? adaptarEntrada(entradaDTO.getPadreEntrada(), null, null) : null);
        entradaEntity.setPermitirComentarios(entradaDTO.getPermitirComentarios());
        entradaEntity.setResumenEntrada(entradaDTO.getResumenEntrada());
        entradaEntity.setTipoEntrada(entradaDTO.getTipoEntrada());
        entradaEntity.setTituloEntrada(entradaDTO.getTituloEntrada());
        entradaEntity.setUrlEntrada(entradaDTO.getUrlEntrada());
        entradaEntity.setImagenDestacada(entradaDTO.getImagenDestacada());
        entradaEntity.setEtiquetas(adaptarEtiquetas(entradaDTO.getEtiquetas()));
        entradaEntity.setFraseDescriptiva(entradaDTO.getFraseDescriptiva());
        entradaEntity.setFechaAlta(entradaDTO.getFechaAlta());
        entradaEntity.setEnMenu(entradaDTO.isEnMenu());
        entradaEntity.setConSidebar(entradaDTO.isConSidebar());
        entradaEntity.setUrlMenuLibro(entradaDTO.getUrlMenuLibro());
        entradaEntity.setNombreMenuLibro(entradaDTO.getNombreMenuLibro());
        return entradaEntity;
    }

    /**
     * Adaptar libros.
     *
     * @param librosEntrada
     *            the libros entrada
     * @return the list
     */
    private static List<LibroEntity> adaptarLibros(final List<LibroDTO> librosEntrada) {

        final List<LibroEntity> librosEntity = new ArrayList<LibroEntity>();
        if (CollectionUtils.isNotEmpty(librosEntrada)) {
            for (final LibroDTO libroDTO : librosEntrada) {
                librosEntity.add(adaptarLibro(libroDTO));
            }
        }
        return librosEntity;

    }

    /**
     * Adaptar saga.
     *
     * @param saga
     *            the saga
     * @return the saga entity
     */
    public static SagaEntity adaptarSaga(final SagaDTO saga) {
        SagaEntity entity = null;
        if (saga != null) {
            entity = new SagaEntity();
            entity.setSagaId(saga.getSagaId());
            entity.setNombre(saga.getNombreSaga());
            entity.setUrlSaga(saga.getUrlSaga());
            entity.setImagenSaga(saga.getImagenSaga());
            entity.setResumen(saga.getResumen());
            entity.setNumeroVolumenes(saga.getNumeroVolumenes());
            entity.setEstaTerminada(saga.getEstaTerminada());
            entity.setTipoSaga(saga.getTipoSaga());
            entity.setDominaLibros(saga.getDominaLibros());
        }
        return entity;
    }

    /**
     * Adaptar editorial. thr
     *
     * @param editorial
     *            the editorial
     * @return the editorial entity
     */
    public static EditorialEntity adaptarEditorial(final EditorialDTO editorial) {
        final EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setEditorialId(editorial.getEditorialId());
        editorialEntity.setUrlEditorial(editorial.getUrlEditorial());
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
     * Adaptar generos.
     *
     * @param generos
     *            the generos
     * @return the list
     */
    public static List<GeneroEntity> adaptarGeneros(final List<GeneroDTO> generos) {
        final List<GeneroEntity> generosEntities = new ArrayList<GeneroEntity>();
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
        entity.setGeneroId(generoDTO.getGeneroId());
        entity.setNombre(generoDTO.getNombre());
        entity.setUrlGenero(generoDTO.getUrlGenero());
        entity.setImagenCabeceraGenero(generoDTO.getImagenCabeceraGenero());
        entity.setImagenIconoGenero(generoDTO.getIconoGenero());
        if (generoDTO.getCategoria() != null) {
            entity.setCategoria(adaptarCategoria(generoDTO.getCategoria()));
        }
        return entity;
    }

    /**
     * Adaptar etiquetas.
     *
     * @param etiquetas
     *            etiquetas
     * @return the establece
     */
    public static Set<EtiquetaEntity> adaptarEtiquetas(final List<EtiquetaDTO> etiquetas) {
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
        etiquetaEntity.setEtiquetaId(etiquetaDTO.getEtiquetaId());
        etiquetaEntity.setNombre(etiquetaDTO.getNombreEtiqueta());
        etiquetaEntity.setEtiquetaUrl(etiquetaDTO.getUrlEtiqueta());
        return etiquetaEntity;
    }

    /**
     * Adaptar galeria.
     *
     * @param galeriaEntity
     *            the galeria entity
     * @return the galeria dto
     */
    public static GaleriaEntity adaptarGaleria(final GaleriaDTO galeriaDTO) {
        final GaleriaEntity galeriaEntity = new GaleriaEntity();
        galeriaEntity.setGaleriaId(galeriaDTO.getGaleriaId());
        galeriaEntity.setColumnas(galeriaDTO.getColumnas());
        galeriaEntity.setImagenes(ConversionUtils.join(galeriaDTO.getImagenes()));
        galeriaEntity.setNombreGaleria(galeriaDTO.getNombreGaleria());
        galeriaEntity.setUrlGaleria(galeriaDTO.getUrlGaleria());
        return galeriaEntity;
    }

    /**
     * Adaptar comentario.
     *
     * @param comentarioDTO
     *            comentario DTO
     * @param entrada
     *            entrada
     * @param autor
     *            autor
     * @param comentarioReferenciaEntity
     *            comentario referencia entity
     * @return the comentario entity
     */
    public static ComentarioEntity adaptarComentario(final ComentarioDTO comentarioDTO, final EntradaEntity entrada,
            final UsuarioEntity autor, final ComentarioEntity comentarioReferenciaEntity) {
        final ComentarioEntity comentario = new ComentarioEntity();
        comentario.setEmailComentario(autor.getUsuarioEmail());
        comentario.setNombreComentario(autor.getUsuarioLogin());
        comentario.setPaginaWebComentario(autor.getPaginaWeb());
        comentario.setEntrada(entrada);
        comentario.setComentarioReferenciaEntity(comentarioReferenciaEntity);
        comentario.setTextoComentario(comentarioDTO.getTextoComentario());
        comentario.setEsBan(comentarioDTO.isEsBan());
        comentario.setEsSpoiler(comentarioDTO.isEsSpoiler());
        comentario.setComentarioId(comentarioDTO.getComentarioId());
        return comentario;
    }

    /**
     * Adaptar puntuacion.
     *
     * @param puntuacionDTO
     *            puntuacion DTO
     * @param autor
     *            autor
     * @param libro
     *            libro
     * @return the puntuacion entity
     */
    public static PuntuacionEntity adaptarPuntuacion(final PuntuacionDTO puntuacionDTO, final UsuarioEntity autor,
            final LibroEntity libro) {
        final PuntuacionEntity puntuacionEntity = new PuntuacionEntity();
        puntuacionEntity.setAutor(autor);
        puntuacionEntity.setComentario(puntuacionDTO.getComentario());
        puntuacionEntity.setLibro(libro);
        puntuacionEntity.setValor(puntuacionDTO.getValor());
        puntuacionEntity.setEsPuntuacionMomoko(puntuacionDTO.isEsPuntuacionMomoko());
        return puntuacionEntity;
    }

    /**
     * Adaptar categoria.
     *
     * @param categoria
     *            the categoria entity
     * @return the categoria dto
     */
    public static CategoriaEntity adaptarCategoria(final CategoriaDTO categoria) {
        final CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setCategoria_id(categoria.getCategoriaId());
        categoriaEntity.setForegroundColor(categoria.getForegroundColor());
        categoriaEntity.setBackgroundColor(categoria.getBackgroundColor());
        categoriaEntity.setUrlCategoria(categoria.getUrlCategoria());
        categoriaEntity.setOrden(categoria.getOrden());
        categoriaEntity.setNombreCategoria(categoria.getNombreCategoria());
        return categoriaEntity;

    }

    public static PuntuacionEntity adaptarPuntuacion(final PuntuacionDTO puntuacionDTO, final UsuarioEntity autor,
            final SagaEntity saga) {
        final PuntuacionEntity puntuacionEntity = new PuntuacionEntity();
        puntuacionEntity.setAutor(autor);
        puntuacionEntity.setComentario(puntuacionDTO.getComentario());
        puntuacionEntity.setSaga(saga);
        puntuacionEntity.setValor(puntuacionDTO.getValor());
        puntuacionEntity.setEsPuntuacionMomoko(puntuacionDTO.isEsPuntuacionMomoko());
        return puntuacionEntity;
    }

}
