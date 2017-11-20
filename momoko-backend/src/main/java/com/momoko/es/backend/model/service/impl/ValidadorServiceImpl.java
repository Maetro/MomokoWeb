/**
 * ValidadorServiceImpl.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.enums.ErrorAnadirPuntuacionEnum;
import com.momoko.es.api.enums.ErrorCreacionEntrada;
import com.momoko.es.api.enums.ErrorCreacionGenero;
import com.momoko.es.api.enums.ErrorCreacionLibro;
import com.momoko.es.api.enums.ErrorPublicarComentario;
import com.momoko.es.api.enums.EstadoEntradaEnum;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.backend.model.service.ValidadorService;

/**
 * The Class ValidadorServiceImpl.
 */
@Service
public class ValidadorServiceImpl implements ValidadorService {

    @Override
    public List<ErrorCreacionLibro> validarLibro(final LibroDTO libroDTO) {

        final List<ErrorCreacionLibro> listaErrores = new ArrayList<ErrorCreacionLibro>();

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
    public List<ErrorCreacionGenero> validarGenero(final GeneroDTO generoDTO) {
        final List<ErrorCreacionGenero> listaErrores = new ArrayList<ErrorCreacionGenero>();

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
        final List<ErrorCreacionEntrada> listaErrores = new ArrayList<ErrorCreacionEntrada>();
        if (entradaDTO.getTituloEntrada() == null) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_TITULO);
        }
        if (entradaDTO.getContenidoEntrada() == null) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_CONTENIDO);
        }
        if (estaPublicada(entradaDTO) && !esTipoMiscelanea(entradaDTO)
                && StringUtils.isEmpty(entradaDTO.getTituloLibroEntrada())) {
            listaErrores.add(ErrorCreacionEntrada.FALTA_LIBRO);
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
    public boolean esTipoMiscelanea(final EntradaDTO entradaDTO) {
        return TipoEntrada.MISCELANEOS.equals(TipoEntrada.obtenerTipoEntrada(entradaDTO.getTipoEntrada()));
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
        final List<ErrorPublicarComentario> listaErrores = new ArrayList<ErrorPublicarComentario>();
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
        final List<ErrorAnadirPuntuacionEnum> listaErrores = new ArrayList<ErrorAnadirPuntuacionEnum>();
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
        final List<ErrorCreacionComentario> listaErrores = new ArrayList<ErrorCreacionComentario>();
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

}
