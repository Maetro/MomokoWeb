/**
 * ValidadorService.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

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
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;

/**
 * The Interface ValidadorService.
 */
public interface ValidadorService {

    /**
     * Validar libro.
     *
     * @param libroDTO
     *            the libro dto
     * @return the list
     */
    List<ErrorCreacionLibro> validarLibro(LibroDTO libroDTO);

    /**
     * Validar usuario.
     *
     * @param nuevoUsuarioRequest
     *            the nuevo usuario request
     * @return the list
     */
    boolean validarUsuario(RegistroNuevoUsuarioDTO nuevoUsuarioRequest);

    /**
     * Validar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the list
     */
    List<ErrorCreacionGenero> validarGenero(GeneroDTO generoDTO);

    /**
     * Validar entrada.
     *
     * @param entradaDTO
     *            entrada DTO
     * @return the list
     */
    List<ErrorCreacionEntrada> validarEntrada(EntradaDTO entradaDTO);

    /**
     * Validar comentario.
     *
     * @param comentarioDTO
     *            comentario DTO
     * @return the collection
     */
    List<ErrorPublicarComentario> validarComentario(ComentarioDTO comentarioDTO);

    /**
     * Validar puntuacion.
     *
     * @param puntuacionDTO
     *            puntuacion DTO
     * @return the list
     */
    List<ErrorAnadirPuntuacionEnum> validarPuntuacion(PuntuacionDTO puntuacionDTO);

    /**
     * Validar comentario.
     *
     * @param comentario
     *            the comentario
     * @return the list
     */
    List<ErrorCreacionComentario> validarComentario(NuevoComentarioRequest comentario);

}
