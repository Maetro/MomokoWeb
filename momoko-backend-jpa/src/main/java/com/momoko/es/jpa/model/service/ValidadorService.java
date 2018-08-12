/**
 * ValidadorService.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.enums.errores.*;

import java.util.List;

public interface ValidadorService {

    List<ErrorCreacionLibro> validarLibro(LibroDTO libroDTO);

    boolean validarUsuario(RegistroNuevoUsuarioDTO nuevoUsuarioRequest);

    List<ErrorCreacionGenero> validarGenero(GenreDTO generoDTO);

    List<ErrorCreacionEntrada> validarEntrada(EntradaDTO entradaDTO);

    List<ErrorPublicarComentario> validarComentario(ComentarioDTO comentarioDTO);

    List<ErrorAnadirPuntuacionEnum> validarPuntuacion(PuntuacionDTO puntuacionDTO);

    List<ErrorCreacionComentario> validarComentario(NuevoComentarioRequest comentario);

    List<ErrorCreacionGaleria> validarGaleria(GaleriaDTO galeriaDTO);

    List<ErrorCreacionSaga> validarSaga(SagaDTO sagaDTO);

    List<ErrorCreacionRedactor> validarRedactor(RedactorDTO redactorDTO);

    List<ErrorCreacionEditorial> validarEditorial(EditorialDTO editorialDTO);

    List<FilterCreationError> validateFilter(com.momoko.es.api.dto.filter.FilterDTO filterDTO);
}
