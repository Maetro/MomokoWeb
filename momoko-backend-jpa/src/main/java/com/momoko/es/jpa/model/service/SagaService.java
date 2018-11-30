/**
 * SagaService.java 01-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionSaga;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConID;
import com.momoko.es.api.exceptions.UrlElementNotFoundException;
import com.momoko.es.api.exceptions.NoSeEncuentranLibrosSagaException;

import java.math.BigDecimal;
import java.util.List;

public interface SagaService {

    List<SagaDTO> recuperarSagas();

    SagaDTO guardarSaga(SagaDTO libroAGuardar)
            throws NoSeEncuentranLibrosSagaException, NoSeEncuentraElementoConID;

    List<String> obtenerListaUrlsLibros(final Integer sagaId);

    SagaDTO obtenerSaga(String urlSaga) throws UrlElementNotFoundException;

    BigDecimal obtenerPuntucionMomokoSaga(String urlSaga);

    List<String> getNombresSagas();

	List<EntradaSimpleDTO> obtenerEntradasSaga(SagaDTO sagaDTO);

	List<EntradaSimpleDTO> obtenerEntradasLibrosSaga(SagaDTO sagaDTO);

    List<ErrorCreacionSaga> validarSaga(SagaDTO sagaDTO);
}
