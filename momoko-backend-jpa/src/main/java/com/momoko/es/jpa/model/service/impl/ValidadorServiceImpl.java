/**
 * ValidadorServiceImpl.java 15-jul-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.*;
import com.momoko.es.api.author.enums.AuthorCreationError;
import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.dto.request.ContactRequestDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.filter.enums.FilterRuleType;
import com.momoko.es.api.enums.errores.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.enums.EstadoEntradaEnum;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.jpa.model.service.ValidadorService;

/**
 * The Class ValidadorServiceImpl.
 */
@Service
public class ValidadorServiceImpl implements ValidadorService {

    @Override
    public List<ErrorCreacionLibro> validarLibro(final LibroDTO libroDTO) {

        final List<ErrorCreacionLibro> listaErrores = new ArrayList<>();

        if (StringUtils.isEmpty(libroDTO.getTitulo())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_TITULO);

        }
        if (CollectionUtils.isEmpty(libroDTO.getAutores())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_AUTOR);
        }

        if (StringUtils.isBlank(libroDTO.getUrlLibro())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_URL);
        }

        if (libroDTO.getEditorial() == null) {
            listaErrores.add(ErrorCreacionLibro.FALTA_EDITORIAL);
        }

        if ((libroDTO.getEnlaceAmazon() != null) && (libroDTO.getEnlaceAmazon().length() > 1000)) {
            listaErrores.add(ErrorCreacionLibro.ENLACE_AMAZON_LARGO);
        }

        if ((libroDTO.getUrlImagen() != null) && (libroDTO.getUrlImagen().length() > 1000)) {
            listaErrores.add(ErrorCreacionLibro.ENLACE_IMAGEN_LARGO);
        }

        return listaErrores;
    }

    @Override
    public boolean validarUsuario(@Valid final RegistroNuevoUsuarioDTO nuevoUsuarioRequest) {
        return true;
    }

    @Override
    public List<ErrorCreacionGenero> validarGenero(final GenreDTO generoDTO) {
        final List<ErrorCreacionGenero> listaErrores = new ArrayList<>();

        if (StringUtils.isEmpty(generoDTO.getNombre())) {
            listaErrores.add(ErrorCreacionGenero.FALTA_GENERO);

        }
        if (StringUtils.isEmpty(generoDTO.getUrlGenero())) {
            listaErrores.add(ErrorCreacionGenero.FALTA_URL);

        }
        if (StringUtils.isEmpty(generoDTO.getImagenCabeceraGenero())) {
            listaErrores.add(ErrorCreacionGenero.FALTA_IMAGEN_CABECERA);

        }
        if (StringUtils.isEmpty(generoDTO.getIconoGenero())) {
            listaErrores.add(ErrorCreacionGenero.FALTA_ICONO);
        }

        if (generoDTO.getCategoria() == null) {
            listaErrores.add(ErrorCreacionGenero.FALTA_CATEGORIA);
        }

        return listaErrores;
    }

    @Override
    public List<ErrorCreacionEntrada> validarEntrada(final EntradaDTO entradaDTO) {
        final List<ErrorCreacionEntrada> listaErrores = new ArrayList<>();
        if (entradaDTO.getTituloEntrada() == null) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_TITULO);
        }
        if (entradaDTO.getContenidoEntrada() == null) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_CONTENIDO);
        }
        if (estaPublicada(entradaDTO) && !esTipoMiscelaneaOVideoONoticia(entradaDTO)
                && CollectionUtils.isEmpty(entradaDTO.getTitulosLibrosEntrada())
                && CollectionUtils.isEmpty(entradaDTO.getNombresSagasEntrada())) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_LIBRO);
        }
        if (StringUtils.isEmpty(entradaDTO.getEditorNombre())) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_EDITOR_POST);
        }
        return listaErrores;
    }

    /**
     * Es tipo miscelanea.
     *
     * @param entradaDTO
     *            the entrada dto
     * @return true, if successful
     */
    public boolean esTipoMiscelaneaOVideoONoticia(final EntradaDTO entradaDTO) {
        return TipoEntrada.MISCELANEOS.equals(TipoEntrada.obtenerTipoEntrada(entradaDTO.getTipoEntrada()))
                || TipoEntrada.VIDEO.equals(TipoEntrada.obtenerTipoEntrada(entradaDTO.getTipoEntrada()))
                || TipoEntrada.NOTICIA.equals(TipoEntrada.obtenerTipoEntrada(entradaDTO.getTipoEntrada()));
    }

    /**
     * Esta publicada.
     *
     * @param entradaDTO
     *            the entrada dto
     * @return true, if successful
     */
    public boolean estaPublicada(final EntradaDTO entradaDTO) {
        return EstadoEntradaEnum.PUBLICADA.equals(EstadoEntradaEnum.obtenerTipoEntrada(entradaDTO.getEstadoEntrada()));
    }

    @Override
    public List<ErrorPublicarComentario> validarComentario(final ComentarioDTO comentarioDTO) {
        final List<ErrorPublicarComentario> listaErrores = new ArrayList<>();
        if (StringUtils.isNotEmpty(comentarioDTO.getTextoComentario())) {
            listaErrores.add(ErrorPublicarComentario.COMENTARIO_VACIO);
        }
        if (comentarioDTO.getEntradaId() == null) {
            listaErrores.add(ErrorPublicarComentario.FALTA_ENTIDAD_ASOCIADA);
        }
        return listaErrores;
    }

    @Override
    public List<ErrorAnadirPuntuacionEnum> validarPuntuacion(final PuntuacionDTO puntuacionDTO) {
        final List<ErrorAnadirPuntuacionEnum> listaErrores = new ArrayList<>();
        if ((puntuacionDTO.getValor() == null)
                || ((puntuacionDTO.getValor().intValue() >= 0) && (puntuacionDTO.getValor().intValue() <= 10))) {
            listaErrores.add(ErrorAnadirPuntuacionEnum.PUNTUACION_INCORRECTA);
        }
        if (puntuacionDTO.getLibroId() == null) {
            listaErrores.add(ErrorAnadirPuntuacionEnum.FALTA_LIBRO);
        }
        return listaErrores;
    }

    @Override
    public List<ErrorCreacionComentario> validarComentario(final NuevoComentarioRequest comentario) {
        final List<ErrorCreacionComentario> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(comentario.getNombre())) {
            listaErrores.add(ErrorCreacionComentario.FALTA_NOMBRE);
        }
        if (StringUtils.isEmpty(comentario.getEmail())) {
            listaErrores.add(ErrorCreacionComentario.FALTA_EMAIL);
        }
        if (StringUtils.isEmpty(comentario.getContenido())) {
            listaErrores.add(ErrorCreacionComentario.COMENTARIO_VACIO);
        }
        if (comentario.getEntradaId() == null) {
            listaErrores.add(ErrorCreacionComentario.NO_SE_ENCUENTRA_ENTRADA);
        }
        return listaErrores;
    }

    @Override
    public List<ErrorCreacionGaleria> validarGaleria(final GaleriaDTO galeriaDTO) {
        final List<ErrorCreacionGaleria> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(galeriaDTO.getNombreGaleria())) {
            listaErrores.add(ErrorCreacionGaleria.FALTA_NOMBRE);
        }
        if (StringUtils.isEmpty(galeriaDTO.getUrlGaleria())) {
            listaErrores.add(ErrorCreacionGaleria.FALTA_URL);
        }
        if (galeriaDTO.getColumnas() == null) {
            listaErrores.add(ErrorCreacionGaleria.FALTA_NUMERO_COLUMNAS);
        }
        if (CollectionUtils.isEmpty(galeriaDTO.getImagenes())) {
            listaErrores.add(ErrorCreacionGaleria.FALTAN_IMAGENES);
        }
        return listaErrores;
    }

    @Override
    public List<ErrorCreacionSaga> validarSaga(final SagaDTO sagaDTO) {
        final List<ErrorCreacionSaga> listaErrores = new ArrayList<>();
        if (CollectionUtils.isEmpty(sagaDTO.getLibrosSaga())) {
            listaErrores.add(ErrorCreacionSaga.FALTAN_LIBROS);
        }
        if (StringUtils.isEmpty(sagaDTO.getNombreSaga())) {
            listaErrores.add(ErrorCreacionSaga.FALTA_NOMBRE);
        }
        if (StringUtils.isEmpty(sagaDTO.getImagenSaga())) {
            listaErrores.add(ErrorCreacionSaga.FALTA_IMAGEN);
        }
        if (StringUtils.isEmpty(sagaDTO.getUrlSaga())) {
            listaErrores.add(ErrorCreacionSaga.FALTA_URL);
        }

        return listaErrores;
    }

    @Override
    public List<ErrorCreacionRedactor> validarRedactor(final RedactorDTO redactorDTO) {
        final List<ErrorCreacionRedactor> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(redactorDTO.getNombre())) {
            listaErrores.add(ErrorCreacionRedactor.FALTA_NOMBRE);
        }
        return listaErrores;
    }

    @Override
    public List<ErrorCreacionEditorial> validarEditorial(final EditorialDTO editorialDTO) {
        final List<ErrorCreacionEditorial> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(editorialDTO.getNombreEditorial())) {
            listaErrores.add(ErrorCreacionEditorial.FALTA_NOMBRE);
        }
        return listaErrores;
    }

    @Override
    public List<FilterCreationError> validateFilter(FilterDTO filterDTO) {
        final List<FilterCreationError> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(filterDTO.getNameFilter())) {
            errorList.add(FilterCreationError.MISSING_NAME);
        }
        if (StringUtils.isEmpty(filterDTO.getUrlFilter())) {
            errorList.add(FilterCreationError.MISSING_URL);
        }
        if (filterDTO.getFilterType() == null) {
            errorList.add(FilterCreationError.MISSING_TYPE);
        }
        if (filterDTO.getFilterType() == FilterRuleType.ENUM && CollectionUtils.isEmpty(filterDTO.getFilterValues())) {
            errorList.add(FilterCreationError.MISSING_ENUM_DEFINITION);
        }
        return errorList;
    }

    @Override
    public List<AuthorCreationError> validateAuthor(AuthorDTO authorDTO) {
        final List<AuthorCreationError> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(authorDTO.getName())) {
            errorList.add(AuthorCreationError.NO_NAME);
        }
        if (StringUtils.isEmpty(authorDTO.getAuthorUrl())) {
            errorList.add(AuthorCreationError.NO_URL);
        }
        return errorList;
    }

    @Override
    public List<ErrorEmailContactEnum> validateEmailContact(ContactRequestDTO contactRequest) {
        return null;
    }


}
