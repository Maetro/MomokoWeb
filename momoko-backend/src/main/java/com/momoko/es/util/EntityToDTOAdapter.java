/**
 * EntityToDTOAdapter.java 04-jun-2017
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
import org.apache.commons.lang.StringUtils;

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
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.TipoEntrada;
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
        libroDTO.setTitulo(libroEntity.getTitulo());
        libroDTO.setUrlImagen(libroEntity.getUrlImagen());
        libroDTO.setAutores(adaptarAutores(libroEntity.getAutores()));
        libroDTO.setGeneros(adaptarGeneros(libroEntity.getGeneros()));
        libroDTO.setAnoPublicacion(libroEntity.getAnoPublicacion());
        libroDTO.setNumeroPaginas(libroEntity.getNumeroPaginas());
        libroDTO.setTituloOriginal(libroEntity.getTituloOriginal());
        libroDTO.setUrlLibro(libroEntity.getUrlLibro());
        libroDTO.setFechaAlta(libroEntity.getFechaAlta());
        libroDTO.setOrdenSaga(libroEntity.getOrdenSaga());
        if (libroEntity.getOrdenSaga() != null) {
            libroDTO.setSaga(adaptarSaga(libroEntity.getSaga(), false));
        }
        if (CollectionUtils.isNotEmpty(libroEntity.getEntradas())) {
            for (final EntradaEntity entradaEntity : libroEntity.getEntradas()) {
                if (TipoEntrada.ANALISIS.getValue().equals(entradaEntity.getTipoEntrada())) {
                    libroDTO.setTieneAnalisis(true);
                    break;
                }
            }
        }
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
        entradaDTO.setRedactor(ConversionUtils.getRedactorFromUsuario(entradaEntity.getEntradaAutor()));
        entradaDTO.setEstadoEntrada(entradaEntity.getEstadoEntrada());
        if (entradaEntity.getLibrosEntrada() != null) {
            entradaDTO.setLibrosEntrada(adaptarLibros(entradaEntity.getLibrosEntrada()));
        }
        entradaDTO.setNumeroComentarios(entradaEntity.getNumeroComentarios());
        entradaDTO.setOrden(entradaEntity.getOrden());
        entradaDTO.setPadreEntrada(
                entradaEntity.getPadreEntrada() != null ? adaptarEntrada(entradaEntity.getPadreEntrada()) : null);
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

        entradaDTO.setTipoEntrada(entradaEntity.getTipoEntrada());
        entradaDTO.setTipoEntradaString(TipoEntrada.obtenerTipoEntrada(entradaEntity.getTipoEntrada()).getNombre());
        entradaDTO.setTituloEntrada(entradaEntity.getTituloEntrada());
        entradaDTO.setEditorNombre(entradaEntity.getEntradaAutor().getUsuarioNick());
        entradaDTO.setUrlEntrada(entradaEntity.getUrlEntrada());
        entradaDTO.setUrlAntigua(entradaEntity.getUrlAntigua());
        entradaDTO.setEtiquetas(adaptarEtiquetas(new ArrayList(entradaEntity.getEtiquetas())));
        entradaDTO.setFechaAlta(entradaEntity.getFechaAlta());
        if (entradaEntity.getFechaModificacion() != null) {
            entradaDTO.setFechaModificacion(entradaEntity.getFechaModificacion());
        } else {
            entradaDTO.setFechaModificacion(entradaEntity.getFechaAlta());
        }
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
     * Adaptar saga.
     *
     * @param sagaEntity
     *            saga entity
     * @return the saga DTO
     */
    public static SagaDTO adaptarSaga(final SagaEntity sagaEntity, final boolean adaptarLibros) {
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
    public static Set<GeneroDTO> adaptarGeneros(final Set<GeneroEntity> generos) {
        final Set<GeneroDTO> generosEntities = new HashSet<GeneroDTO>();
        for (final GeneroEntity generoEntity : generos) {
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
    public static List<GeneroDTO> adaptarGeneros(final List<GeneroEntity> generos) {
        final List<GeneroDTO> generosEntities = new ArrayList<GeneroDTO>();
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
        final List<GaleriaDTO> listaDTO = new ArrayList<GaleriaDTO>();
        if (CollectionUtils.isNotEmpty(galerias)) {
            for (final GaleriaEntity galeriaEntity : galerias) {
                final GaleriaDTO galeriaDTO = adaptarGaleria(galeriaEntity);
                listaDTO.add(galeriaDTO);
            }
        }
        return listaDTO;
    }

}
