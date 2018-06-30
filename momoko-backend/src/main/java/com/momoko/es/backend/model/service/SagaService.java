/**
 * SagaService.java 01-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.math.BigDecimal;
import java.util.List;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConID;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConUrl;
import com.momoko.es.api.exceptions.NoSeEncuentranLibrosSagaException;

/**
 * The Interface SagaService.
 */
public interface SagaService {

    /**
     * Recuperar sagas.
     *
     * @return the list
     */
    public List<SagaDTO> recuperarSagas();

    /**
     * Guardar saga.
     *
     * @param libroAGuardar
     *            the libro a guardar
     * @return the saga dto
     * @throws NoSeEncuentraElementoConID
     * @throws Exception
     *             the exception
     */
    public SagaDTO guardarSaga(SagaDTO libroAGuardar)
            throws NoSeEncuentranLibrosSagaException, NoSeEncuentraElementoConID;

    /**
     * Obtener lista urls libros.
     *
     * @return the list
     */
    public List<String> obtenerListaUrlsLibros(final Integer sagaId);

    /**
     * Obtener saga.
     *
     * @param urlSaga
     *            the url saga
     * @return the saga dto
     * @throws NoSeEncuentraElementoConID
     */
    public SagaDTO obtenerSaga(String urlSaga) throws NoSeEncuentraElementoConUrl;

    /**
     * Obtener puntucion momoko saga.
     *
     * @param urlSaga
     *            the url saga
     * @return the big decimal
     */
    public BigDecimal obtenerPuntucionMomokoSaga(String urlSaga);

    /**
     * Gets the nombres sagas.
     *
     * @return the nombres sagas
     */
    public List<String> getNombresSagas();

	public List<EntradaSimpleDTO> obtenerEntradasSaga(SagaDTO sagaDTO);

	public List<EntradaSimpleDTO> obtenerEntradasLibrosSaga(SagaDTO sagaDTO); 
}
