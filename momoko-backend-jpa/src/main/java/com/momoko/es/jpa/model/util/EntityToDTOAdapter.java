/**
 * EntityToDTOAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.api.filter.dto.FilterValueDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.jpa.author.adapter.AuthorAdapter;
import com.momoko.es.jpa.model.entity.*;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import com.momoko.es.jpa.model.entity.filter.FilterValueEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.jpa.author.entity.AuthorEntity;

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
        usuario.setUsuarioId(nuevoUsuario.getId());
        usuario.setUserId(nuevoUsuario.getId());
        usuario.setUsuarioLogin(nuevoUsuario.getUsuarioLogin());

        usuario.setUserId(nuevoUsuario.getId());
        usuario.setUsuarioId(nuevoUsuario.getId());
        usuario.setUsername(nuevoUsuario.getEmail());
        usuario.setPassword(nuevoUsuario.getPassword());
        usuario.setRoles(nuevoUsuario.getRoles());
        usuario.setTag(nuevoUsuario.toTag());

        boolean unverified = nuevoUsuario.hasRole(UserUtils.Role.UNVERIFIED);
        boolean blocked = nuevoUsuario.hasRole(UserUtils.Role.BLOCKED);
        boolean admin = nuevoUsuario.hasRole(UserUtils.Role.ADMIN);
        boolean goodUser = !(unverified || blocked);
        boolean goodAdmin = goodUser && admin;

        usuario.setAdmin(admin);
        usuario.setBlocked(blocked);
        usuario.setGoodAdmin(goodAdmin);
        usuario.setGoodUser(goodUser);
        usuario.setUnverified(unverified);
        usuario.setEmail(nuevoUsuario.getEmail());
        usuario.setUsuarioFechaRegistro(nuevoUsuario.getUsuarioFechaRegistro());
        usuario.setUsuarioNick(nuevoUsuario.getUsuarioNick());
        usuario.setUsuarioUrl(nuevoUsuario.getUsuarioUrl());
        usuario.setUsuarioStatus(nuevoUsuario.getUsuarioStatus());
        usuario.setUsuarioRolId(nuevoUsuario.getUsuarioRolId());
        return usuario;
    }

    public static LibroDTO adaptarLibro(final LibroEntity libroEntity) {
        return EntityToDTOAdapter.adaptarLibro(libroEntity, false);
    }

    /**
     * Adaptar libro.
     *
     * @param libroEntity
     *            the libro entity
     * @return the libro dto
     */
    public static LibroDTO adaptarLibro(final LibroEntity libroEntity, final boolean adaptarEntradas) {
        final LibroDTO libroDTO = new LibroDTO();
        libroDTO.setAnoEdicion(libroEntity.getAnoEdicion());
        libroDTO.setCitaLibro(libroEntity.getCitaLibro());
        libroDTO.setEditorial(adaptarEditorial(libroEntity.getEditorial()));
        libroDTO.setEnlaceAmazon(libroEntity.getEnlaceAmazon());
        libroDTO.setLibroId(libroEntity.getLibroId());
        libroDTO.setResumen(libroEntity.getResumen());
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        libroDTO.setAutores(AuthorAdapter.adaptAuthorEntities(libroEntity.getAutores(), false));
        libroDTO.setGeneros(adaptarGeneros(libroEntity.getGeneros()));
        libroDTO.setAnoPublicacion(libroEntity.getAnoPublicacion());
        libroDTO.setNumeroPaginas(libroEntity.getNumeroPaginas());
        libroDTO.setTituloOriginal(libroEntity.getTituloOriginal());
        libroDTO.setUrlLibro(libroEntity.getUrlLibro());
        libroDTO.setFechaAlta(libroEntity.getFechaAlta());
        libroDTO.setOrdenSaga(libroEntity.getOrdenSaga());
        if (libroEntity.getOrdenSaga() != null) {
            libroDTO.setSaga(adaptarSaga(libroEntity.getSaga(), false, false));
        }
        if (CollectionUtils.isNotEmpty(libroEntity.getEntradas())) {
            for (final EntradaEntity entradaEntity : libroEntity.getEntradas()) {
                if (EntryTypeEnum.OPINION.getValue().equals(entradaEntity.getTipoEntrada())) {
                    libroDTO.setTieneOpinion(true);
                    if (!adaptarEntradas) {
                        break;
                    }
                }
            }
            if (adaptarEntradas) {
                libroDTO.setEntradasLibro(
                        ConversionUtils.obtenerDatosEntradaFromEntradaEntityList(libroEntity.getEntradas()));
            }
        }
        return libroDTO;
    }

    public static List<EntradaDTO> adaptarEntradas(final List<EntradaEntity> entradaEntities) {
        final List<EntradaDTO> entradas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entradaEntities)) {
            for (final EntradaEntity entradaEntity : entradaEntities) {
                entradas.add(adaptarEntrada(entradaEntity));
            }
        }
        return entradas;
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
        entradaDTO.setRedactor(ConversionUtils.getRedactorFromUsuario(entradaEntity.getEntradaAutor()));
        entradaDTO.setEntryStatus(entradaEntity.getEntryStatus());
        if (entradaEntity.getLibrosEntrada() != null) {
            entradaDTO.setLibrosEntrada(adaptarLibros(entradaEntity.getLibrosEntrada()));
        }
        if (entradaEntity.getSagasEntrada() != null) {
            entradaDTO.setSagasEntrada(adaptarSagas(entradaEntity.getSagasEntrada()));
        }
        entradaDTO.setPermitirComentarios(entradaEntity.getPermitirComentarios());
        entradaDTO.setResumenEntrada(entradaEntity.getResumenEntrada());
        if (StringUtils.isNotBlank(entradaEntity.getFraseDescriptiva())) {
            entradaDTO.setFraseDescriptiva(entradaEntity.getFraseDescriptiva());
        } else if (StringUtils.isNotBlank(entradaEntity.getResumenEntrada())) {
            entradaDTO
                    .setFraseDescriptiva(ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getResumenEntrada(), 200));
        } else {
            entradaDTO
                    .setResumenEntrada(ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getContenidoEntrada(), 500));
            entradaDTO.setFraseDescriptiva(
                    ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getContenidoEntrada(), 200));
        }

        entradaDTO.setEntryType(entradaEntity.getEntryType());
        entradaDTO.setFechaAlta(entradaEntity.getCreatedDate());
        entradaDTO.setFechaModificacion(entradaEntity.getModifiedDate());
        entradaDTO.setTituloEntrada(entradaEntity.getTituloEntrada());
        entradaDTO.setEditorNombre(entradaEntity.getEntradaAutor().getUsuarioNick());
        entradaDTO.setUrlEntrada(entradaEntity.getUrlEntrada());
        entradaDTO.setUrlAntigua(entradaEntity.getUrlAntigua());
        entradaDTO.setEtiquetas(adaptarEtiquetas(new ArrayList(entradaEntity.getEtiquetas())));

        entradaDTO.setImagenDestacada(entradaEntity.getImagenDestacada());
        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            final List<String> titulos = new ArrayList<String>();
            for (final LibroEntity libroEntity : entradaEntity.getLibrosEntrada()) {
                titulos.add(libroEntity.getTitulo());
            }
            entradaDTO.setTitulosLibrosEntrada(titulos);
        }
        if (CollectionUtils.isNotEmpty(entradaEntity.getSagasEntrada())) {
            final List<String> nombres = new ArrayList<String>();
            for (final SagaEntity sagaEntity : entradaEntity.getSagasEntrada()) {
                nombres.add(sagaEntity.getNombre());
            }
            entradaDTO.setNombresSagasEntrada(nombres);
        }
        entradaDTO.setEnMenu(entradaEntity.isEnMenu());
        entradaDTO.setConSidebar(entradaEntity.isConSidebar());
        entradaDTO.setUrlMenuLibro(entradaEntity.getUrlMenuLibro());
        entradaDTO.setNombreMenuLibro(entradaEntity.getNombreMenuLibro());
        return entradaDTO;
    }

    /**
     * Adaptar libros.
     *
     * @param librosEntrada
     *            the libros entrada
     * @return the list
     */
    public static List<LibroDTO> adaptarLibros(final List<LibroEntity> librosEntrada) {
        final List<LibroDTO> librosDTO = new ArrayList<LibroDTO>();
        if (CollectionUtils.isNotEmpty(librosEntrada)) {
            for (final LibroEntity libroEntity : librosEntrada) {
                librosDTO.add(adaptarLibro(libroEntity));
            }
        }
        return librosDTO;
    }

    /**
     * Adaptar sagas.
     *
     * @param sagasEntrada
     *            the sagas entrada
     * @return the list
     */
    public static List<SagaDTO> adaptarSagas(final List<SagaEntity> sagasEntrada) {
        final List<SagaDTO> sagasDTO = new ArrayList<SagaDTO>();
        if (CollectionUtils.isNotEmpty(sagasEntrada)) {
            for (final SagaEntity SagaEntity : sagasEntrada) {
                sagasDTO.add(adaptarSaga(SagaEntity, false, false));
            }
        }
        return sagasDTO;
    }

    /**
     * Adaptar saga.
     *
     * @param sagaEntity
     *            saga entity
     * @return the saga DTO
     */
    public static SagaDTO adaptarSaga(final SagaEntity sagaEntity, final boolean adaptarEntradas,
            final boolean adaptarLibros) {
        final SagaDTO sagaDTO = new SagaDTO();
        sagaDTO.setSagaId(sagaEntity.getSagaId());
        sagaDTO.setNombreSaga(sagaEntity.getNombre());
        sagaDTO.setImagenSaga(sagaEntity.getImagenSaga());
        if (adaptarLibros) {
            final List<String> urlLibrosSaga = new ArrayList<String>();
            final List<LibroDTO> librosSaga = new ArrayList<LibroDTO>();
            if (CollectionUtils.isNotEmpty(sagaEntity.getLibros())) {
                for (final LibroEntity libroEntity : sagaEntity.getLibros()) {
                    urlLibrosSaga.add(libroEntity.getUrlLibro());
                    librosSaga.add(adaptarLibro(libroEntity));
                }
            }

            sagaDTO.setLibrosSaga(librosSaga);
            sagaDTO.setUrlsLibrosSaga(urlLibrosSaga);
        }
        if (adaptarEntradas) {
            final List<EntradaDTO> entradasSaga = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(sagaEntity.getEntradas())) {
                entradasSaga.addAll(adaptarEntradas(sagaEntity.getEntradas()));
            }
        }
        if (CollectionUtils.isNotEmpty(sagaEntity.getLibros())) {
            final Set<GenreEntity> listaGeneros = sagaEntity.getLibros().stream().map(LibroEntity::getGeneros)
                    .collect(HashSet::new, Set::addAll, Set::addAll);
            sagaDTO.setGeneros(adaptarGeneros(listaGeneros));
        }

        if (CollectionUtils.isNotEmpty(sagaEntity.getLibros())) {
            final Set<AuthorEntity> listaAutores = sagaEntity.getLibros().stream().map(LibroEntity::getAutores)
                    .collect(HashSet::new, Set::addAll, Set::addAll);
            sagaDTO.setAutores(AuthorAdapter.adaptAuthorEntities(listaAutores, false));
        }

        if (CollectionUtils.isNotEmpty(sagaEntity.getLibros())) {
            final EditorialEntity editorial = sagaEntity.getLibros().iterator().next().getEditorial();
            sagaDTO.setEditorial(adaptarEditorial(editorial));
        }

        sagaDTO.setResumen(sagaEntity.getResumen());
        sagaDTO.setNumeroVolumenes(sagaEntity.getNumeroVolumenes());
        sagaDTO.setEstaTerminada(sagaEntity.getEstaTerminada());
        sagaDTO.setTipoSaga(sagaEntity.getTipoSaga());
        sagaDTO.setDominaLibros(sagaEntity.getDominaLibros());
        sagaDTO.setUrlSaga(sagaEntity.getUrlSaga());
        final List<PuntuacionDTO> puntuacionesDTO = new ArrayList<PuntuacionDTO>();
        if (CollectionUtils.isNotEmpty(sagaEntity.getPuntuaciones())) {
            for (final PuntuacionEntity puntuacion : sagaEntity.getPuntuaciones()) {
                if (puntuacion.isEsPuntuacionMomoko()) {
                    sagaDTO.setNotaSaga(puntuacion.getValor().intValue());
                }
                puntuacionesDTO.add(adaptarPuntuacion(puntuacion));
            }
        }
        sagaDTO.setPuntuacionesSaga(puntuacionesDTO);
        return sagaDTO;
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
        editorialDTO.setUrlEditorial(editorial.getUrlEditorial());
        editorialDTO.setDescripcionEditorial(editorial.getDescripcionEditorial());
        editorialDTO.setImagenCabeceraEditorial(editorial.getImagenCabeceraEditorial());
        editorialDTO.setImagenEditorial(editorial.getImagenEditorial());
        editorialDTO.setInformacionDeContacto(editorial.getInformacionDeContacto());
        editorialDTO.setWebEditorial(editorial.getWebEditorial());
        editorialDTO.setInfoAdicional(ConversionUtils.deJSONToInfoAdicionalDTO(editorial.getInfoAdicionalJSON()));
        return editorialDTO;
    }

    /**
     * Adaptar generos.
     *
     * @param generos
     *            the generos
     * @return the establece
     */
    public static Set<GenreDTO> adaptarGeneros(final Set<GenreEntity> generos) {
        final Set<GenreDTO> generosEntities = new HashSet<GenreDTO>();
        for (final GenreEntity generoEntity : generos) {
            generosEntities.add(adaptarGenero(generoEntity));
        }
        return generosEntities;
    }

    /**
     * Adaptar generos.
     *
     * @param generos
     *            the generos
     * @return the establece
     */
    public static List<GenreDTO> adaptarGeneros(final List<GenreEntity> generos) {
        final List<GenreDTO> generosEntities = new ArrayList<GenreDTO>();
        for (final GenreEntity generoEntity : generos) {
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
    public static GenreDTO adaptarGenero(final GenreEntity generoEntity) {
        final GenreDTO generoDTO = new GenreDTO();
        generoDTO.setGeneroId(generoEntity.getGeneroId());
        generoDTO.setNombre(generoEntity.getNombre());
        generoDTO.setUrlGenero(generoEntity.getUrlGenero());
        generoDTO.setImagenCabeceraGenero(generoEntity.getImagenCabeceraGenero());
        generoDTO.setIconoGenero(generoEntity.getImagenIconoGenero());
        if (generoEntity.getCategoria() != null) {
            generoDTO.setCategoria(adaptarCategoria(generoEntity.getCategoria()));
        }
        return generoDTO;
    }

    /**
     * Adaptar etiquetas.
     *
     * @param etiquetas
     *            etiquetas
     * @return the establece
     */
    public static List<EtiquetaDTO> adaptarEtiquetas(final List<EtiquetaEntity> etiquetas) {
        final List<EtiquetaDTO> etiquetasDTO = new ArrayList<EtiquetaDTO>();
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
        if (etiquetaEntity != null) {
            etiquetaDTO.setEtiquetaId(etiquetaEntity.getEtiquetaId());
            etiquetaDTO.setNombreEtiqueta(etiquetaEntity.getNombre());
            etiquetaDTO.setUrlEtiqueta(etiquetaEntity.getEtiquetaUrl());
        }
        return etiquetaDTO;
    }

    public static GaleriaDTO adaptarGaleria(final GaleriaEntity galeriaEntity) {
        final GaleriaDTO galeriaDTO = new GaleriaDTO();
        galeriaDTO.setGaleriaId(galeriaEntity.getGaleriaId());
        galeriaDTO.setColumnas(galeriaEntity.getColumnas());
        galeriaDTO.setImagenes(ConversionUtils.divide(galeriaEntity.getImagenes(), ";"));
        galeriaDTO.setUrlGaleria(galeriaEntity.getUrlGaleria());
        galeriaDTO.setNombreGaleria(galeriaEntity.getNombreGaleria());

        return galeriaDTO;
    }

    /**
     * Adaptar comentario.
     *
     * @param comentarioEntity
     *            comentario entity
     * @return the comentario DTO
     */
    public static ComentarioDTO adaptarComentario(final ComentarioEntity comentarioEntity,
            final UsuarioBasicoDTO usarioBasico) {
        final ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setAutor(usarioBasico);
        comentarioDTO.setVotosPositivos(ConversionUtils.divide(comentarioEntity.getVotosPositivos()).size());
        comentarioDTO.setVotosNegativos(ConversionUtils.divide(comentarioEntity.getVotosNegativos()).size());
        comentarioDTO.setTextoComentario(comentarioEntity.getTextoComentario());
        comentarioDTO.setEsBan(comentarioEntity.isEsBan());
        comentarioDTO.setEsSpoiler(comentarioEntity.isEsSpoiler());
        comentarioDTO.setComentarioId(comentarioEntity.getComentarioId());
        comentarioDTO.setEntradaId(comentarioEntity.getEntrada().getEntradaId());
        comentarioDTO.setFecha(comentarioEntity.getFechaAlta());
        if (comentarioEntity.getComentarioReferenciaEntity() != null) {
            comentarioDTO.setComentarioReferencia(comentarioEntity.getComentarioReferenciaEntity().getComentarioId());
        }
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
        final PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
        puntuacionDTO.setAutor(ConversionUtils.obtenerUsuarioBasico(puntuacionEntity.getAutor()));
        puntuacionDTO.setComentario(puntuacionEntity.getComentario());
        puntuacionDTO.setEsPuntuacionMomoko(puntuacionEntity.isEsPuntuacionMomoko());
        if (puntuacionEntity.getLibro() != null) {
            puntuacionDTO.setLibroId(puntuacionEntity.getLibro().getLibroId());
        }
        if (puntuacionEntity.getSaga() != null) {
            puntuacionDTO.setSagaId(puntuacionEntity.getSaga().getSagaId());
        }
        puntuacionDTO.setValor(puntuacionEntity.getValor());
        return puntuacionDTO;
    }

    /**
     * Adaptar categoria.
     *
     * @param categoriaEntity
     *            the categoria entity
     * @return the categoria dto
     */
    public static CategoriaDTO adaptarCategoria(final CategoriaEntity categoriaEntity) {
        final CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoriaId(categoriaEntity.getCategoria_id());
        categoriaDTO.setForegroundColor(categoriaEntity.getForegroundColor());
        categoriaDTO.setNombreCategoria(categoriaEntity.getNombreCategoria());
        categoriaDTO.setBackgroundColor(categoriaEntity.getBackgroundColor());
        categoriaDTO.setUrlCategoria(categoriaEntity.getUrlCategoria());
        categoriaDTO.setNombreCategoria(categoriaEntity.getNombreCategoria());
        categoriaDTO.setOrden(categoriaEntity.getOrden());
        categoriaDTO.setShowOnMenu(categoriaEntity.isShowOnMenu());
        return categoriaDTO;

    }

    /**
     * Adaptar categorias.
     *
     * @param categorias
     *            the categorias
     * @return the list
     */
    public static List<CategoriaDTO> adaptarCategorias(final List<CategoriaEntity> categorias) {
        final List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
        if (CollectionUtils.isNotEmpty(categorias)) {
            for (final CategoriaEntity categoriaEntity : categorias) {
                final CategoriaDTO categoriaDTO = adaptarCategoria(categoriaEntity);
                listaDTO.add(categoriaDTO);
            }
        }
        return listaDTO;
    }

    /**
     * Adaptar galerias.
     *
     * @param galerias
     *            the galerias
     * @return the list
     */
    public static List<GaleriaDTO> adaptarGalerias(final List<GaleriaEntity> galerias) {
        final List<GaleriaDTO> listaDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(galerias)) {
            for (final GaleriaEntity galeriaEntity : galerias) {
                final GaleriaDTO galeriaDTO = adaptarGaleria(galeriaEntity);
                listaDTO.add(galeriaDTO);
            }
        }
        return listaDTO;
    }

    public static List<FilterDTO> adaptFilters(Set<FilterEntity> filterEntities) {
        List<FilterDTO> filterDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filterEntities)){
            for (FilterEntity filterEntity : filterEntities) {
                filterDTO.add(adaptFilter(filterEntity));
            }
        }
        return filterDTO;

    }

    public static FilterDTO adaptFilter(FilterEntity filterEntity) {
        FilterDTO filterDTO = new FilterDTO();
        if (filterEntity.getFilterValues() != null) {
            filterDTO.setFilterValues(ConversionUtils.toPossibleValues(filterEntity.getFilterValues()));
        }
        if (CollectionUtils.isNotEmpty(filterEntity.getApplicableGenres())){
            filterDTO.setGenres(EntityToDTOAdapter.adaptarGeneros(filterEntity.getApplicableGenres()));
        }
        filterDTO.setFilterType(filterEntity.getType());
        filterDTO.setReferencedProperty(filterEntity.getReferencedProperty());
        filterDTO.setNameFilter(filterEntity.getNameFilter());
        filterDTO.setUrlFilter(filterEntity.getUrlFilter());
        filterDTO.setBasic(filterEntity.isBasic());
        filterDTO.setInclusive(filterEntity.isInclusive());
        filterDTO.setFilterId(filterEntity.getFilterId());
        if (filterEntity.getFilterValues() != null) {
            filterDTO.setFilterValues(EntityToDTOAdapter.adaptFilterValues(filterEntity.getFilterValues()));
        }
        return filterDTO;
    }

    private static List<FilterValueDTO> adaptFilterValues(Set<FilterValueEntity> filterValues) {
        List<FilterValueDTO> filterValueList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filterValues)){
            for (FilterValueEntity filterValueEntity : filterValues) {
                filterValueList.add(adaptFilterValue(filterValueEntity));
            }
        }
        return filterValueList;
    }

    private static FilterValueDTO adaptFilterValue(FilterValueEntity filterValueEntity) {
        FilterValueDTO filterValueDTO = new FilterValueDTO();
        filterValueDTO.setName(filterValueEntity.getName());
        filterValueDTO.setValue(filterValueEntity.getValue());
        filterValueDTO.setOrder(filterValueEntity.getFilterOrder());
        filterValueDTO.setFilterValueId(filterValueEntity.getFilterValueId());
        return filterValueDTO;
    }


}
