/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.util;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.filter.dto.FilterValueDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.jpa.author.entity.AuthorEntity;
import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.category.CategoriaEntity;
import com.momoko.es.jpa.comment.ComentarioEntity;
import com.momoko.es.jpa.entry.EntradaEntity;
import com.momoko.es.jpa.gallery.GaleriaEntity;
import com.momoko.es.jpa.filter.FilterEntity;
import com.momoko.es.jpa.filter.FilterValueEntity;
import com.momoko.es.jpa.publisher.EditorialEntity;
import com.momoko.es.jpa.saga.SagaEntity;
import com.momoko.es.jpa.score.PuntuacionEntity;
import com.momoko.es.jpa.tag.EtiquetaEntity;
import com.momoko.es.jpa.user.UsuarioEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class DTOToEntityAdapter.
 */
public final class DTOToEntityAdapter {

    private DTOToEntityAdapter() {
    }

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
        usuario.setEmail(nuevoUsuario.getEmail());
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
                                               final List<SagaDTO> sagasEntrada, final UsuarioEntity autor) {
        final EntradaEntity entradaEntity = new EntradaEntity();
        entradaEntity.setEntradaId(entradaDTO.getEntradaId());
        entradaEntity.setContenidoEntrada(entradaDTO.getContenidoEntrada());
        if (autor != null) {
            entradaEntity.setEntradaAutor(autor);
        }
        entradaEntity.setEntryStatus(entradaDTO.getEntryStatus());
        if (librosEntrada != null) {
            entradaEntity.setLibrosEntrada(adaptarLibros(librosEntrada));
        }
        if (sagasEntrada != null) {
            entradaEntity.setSagasEntrada(adaptarSagas(sagasEntrada));
        }
        entradaEntity.setPermitirComentarios(entradaDTO.getPermitirComentarios());
        entradaEntity.setResumenEntrada(entradaDTO.getResumenEntrada());
        entradaEntity.setEntryType(entradaDTO.getEntryType());
        entradaEntity.setTituloEntrada(entradaDTO.getTituloEntrada());
        entradaEntity.setUrlEntrada(entradaDTO.getUrlEntrada());
        entradaEntity.setImagenDestacada(entradaDTO.getImagenDestacada());
        entradaEntity.setEtiquetas(adaptarEtiquetas(entradaDTO.getEtiquetas()));
        entradaEntity.setFraseDescriptiva(entradaDTO.getFraseDescriptiva());
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
    public static List<LibroEntity> adaptarLibros(final List<LibroDTO> librosEntrada) {

        final List<LibroEntity> librosEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(librosEntrada)) {
            for (final LibroDTO libroDTO : librosEntrada) {
                librosEntity.add(adaptarLibro(libroDTO));
            }
        }
        return librosEntity;

    }

    /**
     * Adaptar sagas.
     *
     * @param sagasEntrada
     *            the sagas entrada
     * @return the list
     */
    public static List<SagaEntity> adaptarSagas(final List<SagaDTO> sagasEntrada) {

        final List<SagaEntity> sagasEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sagasEntrada)) {
            for (final SagaDTO sagaDTO : sagasEntrada) {
                sagasEntity.add(adaptarSaga(sagaDTO));
            }
        }
        return sagasEntity;

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
        if (editorial.getImagenEditorial() != null) {
            editorialEntity.setImagenEditorial(MomokoUtils.soloNombreYCarpeta(editorial.getImagenEditorial()));
        }
        editorialEntity.setDescripcionEditorial(editorial.getDescripcionEditorial());
        editorialEntity.setWebEditorial(editorial.getWebEditorial());
        editorialEntity.setInformacionDeContacto(editorial.getInformacionDeContacto());
        if (editorial.getImagenCabeceraEditorial() != null) {
            editorialEntity
                    .setImagenCabeceraEditorial(MomokoUtils.soloNombreYCarpeta(editorial.getImagenCabeceraEditorial()));
        }
        return editorialEntity;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            the autores
     * @return the establece
     */
    public static Set<AuthorEntity> adaptarAutores(final Set<AuthorDTO> autores) {
        final Set<AuthorEntity> autoresEntities = new HashSet<>();
        for (final AuthorDTO authorDTO : autores) {
            autoresEntities.add(adaptarAutor(authorDTO));
        }
        return autoresEntities;
    }

    /**
     * Adaptar autor.
     *
     * @param authorDTO
     *            the autor dto
     * @return the autor entity
     */
    public static AuthorEntity adaptarAutor(final AuthorDTO authorDTO) {
        final AuthorEntity entity = new AuthorEntity();
        entity.setAuthorId(authorDTO.getAuthorId());
        entity.setName(authorDTO.getName());
        return entity;
    }

    /**
     * Adaptar autores.
     *
     * @param generos
     *            the generos
     * @return the establece
     */
    public static Set<GenreEntity> adaptarGeneros(final Set<GenreDTO> generos) {
        final Set<GenreEntity> generosEntities = new HashSet<>();
        for (final GenreDTO generoDTO : generos) {
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
    public static List<GenreEntity> adaptarGeneros(final List<GenreDTO> generos) {
        final List<GenreEntity> generosEntities = new ArrayList<>();
        for (final GenreDTO generoDTO : generos) {
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
    public static GenreEntity adaptarGenero(final GenreDTO generoDTO) {
        final GenreEntity entity = new GenreEntity();
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
        final Set<EtiquetaEntity> etiquetasEntities = new HashSet<>();
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
     * @param galeriaDTO
     *            the galeria dto
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
        comentario.setEmailComentario(autor.getEmail());
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
        categoriaEntity.setShowOnMenu(categoria.isShowOnMenu());
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


    public static List<FilterEntity> adaptFilters(Set<FilterDTO> filterDTOs) {
        List<FilterEntity> filterEntities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filterDTOs)){
            for (FilterDTO filterDTO : filterDTOs) {
                filterEntities.add(adaptFilter(filterDTO));
            }
        }
        return filterEntities;

    }

    public static FilterEntity adaptFilter(FilterDTO filterDTO) {
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setFilterId(filterDTO.getFilterId());
        filterEntity.setType(filterDTO.getFilterType());
        filterEntity.setNameFilter(filterDTO.getNameFilter());
        filterEntity.setReferencedProperty(filterDTO.getReferencedProperty());
        filterEntity.setUrlFilter(filterDTO.getUrlFilter());
        filterEntity.setBasic(filterDTO.isBasic());
        filterEntity.setFilterId(filterDTO.getFilterId());
        if (CollectionUtils.isNotEmpty(filterDTO.getFilterValues())) {
            Set<FilterValueEntity> filterValues = adaptFilterValues(filterEntity, filterDTO.getFilterValues());
        }
        return filterEntity;
    }



    public static Set<FilterValueEntity> adaptFilterValues(FilterEntity filter, List<FilterValueDTO> possibleValues) {
        Set<FilterValueEntity> result = new HashSet<>();
        if (CollectionUtils.isNotEmpty(possibleValues)) {
            for (FilterValueDTO filterValueDTO : possibleValues) {
                FilterValueEntity filterValue = adaptFilterValue(filter, filterValueDTO);
                result.add(filterValue);
            }
        }
        return result;
    }

    public static FilterValueEntity adaptFilterValue(FilterEntity filter, FilterValueDTO filterValueDTO) {
        FilterValueEntity filterValue = new FilterValueEntity();
        filterValue.setValue(filterValueDTO.getValue());
        filterValue.setName(filterValueDTO.getName());
        filterValue.setFilterOrder(filterValueDTO.getOrder());
        filterValue.setFilterValueId(filterValueDTO.getFilterValueId());
        filterValue.setFilter(filter);
        return filterValue;
    }



}
