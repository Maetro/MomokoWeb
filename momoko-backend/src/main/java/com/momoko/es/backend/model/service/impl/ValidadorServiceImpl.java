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
import com.momoko.es.api.enums.ErrorAnadirPuntuacionEnum;
import com.momoko.es.api.enums.ErrorCreacionEntrada;
import com.momoko.es.api.enums.ErrorCreacionGenero;
import com.momoko.es.api.enums.ErrorCreacionLibro;
import com.momoko.es.api.enums.ErrorPublicarComentario;
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
        return listaErrores;
    }

    @Override
    public List<ErrorPublicarComentario> validarComentario(ComentarioDTO comentarioDTO) {
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
    public List<ErrorAnadirPuntuacionEnum> validarPuntuacion(PuntuacionDTO puntuacionDTO) {
        final List<ErrorAnadirPuntuacionEnum> listaErrores = new ArrayList<ErrorAnadirPuntuacionEnum>();
        if ((puntuacionDTO.getValor() == null) || (puntuacionDTO.getValor() >= 0)
                && (puntuacionDTO.getValor() <= 100)) {
            listaErrores.add(ErrorAnadirPuntuacionEnum.PUNTUACION_INCORRECTA);
        }
        if (puntuacionDTO.getLibroId() == null) {
            listaErrores.add(ErrorAnadirPuntuacionEnum.FALTA_LIBRO);
        }
        return listaErrores;
    }

}
