/**
 * ValidadorService.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import java.util.List;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.enums.errores.ErrorAnadirPuntuacionEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;
import com.momoko.es.api.enums.errores.ErrorCreacionEntrada;
import com.momoko.es.api.enums.errores.ErrorCreacionGaleria;
import com.momoko.es.api.enums.errores.ErrorCreacionGenero;
import com.momoko.es.api.enums.errores.ErrorCreacionLibro;
import com.momoko.es.api.enums.errores.ErrorCreacionRedactor;
import com.momoko.es.api.enums.errores.ErrorCreacionSaga;
import com.momoko.es.api.enums.errores.ErrorPublicarComentario;

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
    List<ErrorCreacionGenero> validarGenero(GenreDTO generoDTO);

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

    /**
     * Validar galeria.
     *
     * @param galeriaDTO
     *            the galeria dto
     * @return the list
     */
    List<ErrorCreacionGaleria> validarGaleria(GaleriaDTO galeriaDTO);

    /**
     * Validar saga.
     *
     * @param sagaDTO
     *            the saga dto
     * @return the list
     */
    List<ErrorCreacionSaga> validarSaga(SagaDTO sagaDTO);

    /**
     * Validar redactor list.
     *
     * @param redactorDTO
     *            the redactor dto
     * @return the list
     */
    List<ErrorCreacionRedactor> validarRedactor(RedactorDTO redactorDTO);

    /**
     * Validar editorial list.
     *
     * @param editorialDTO
     *            the editorial dto
     * @return the list
     */
    List<ErrorCreacionEditorial> validarEditorial(EditorialDTO editorialDTO);
}
