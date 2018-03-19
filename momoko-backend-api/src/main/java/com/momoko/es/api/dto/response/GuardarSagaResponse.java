/**
 * GuardarSagaResponse.java 25-feb-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.enums.ErrorCreacionSaga;
import com.momoko.es.api.enums.EstadoGuardadoEnum;

/**
 * The Class GuardarSagaResponse.
 */
public class GuardarSagaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2106137858805419990L;

    /** The lista errores validacion. */
    private List<ErrorCreacionSaga> listaErroresValidacion;

    /** The estado guardado. */
    private EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    private SagaDTO sagaDTO;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionSaga> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionSaga> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    /**
     * Gets the estado guardado.
     *
     * @return the estado guardado
     */
    public EstadoGuardadoEnum getEstadoGuardado() {
        return this.estadoGuardado;
    }

    /**
     * Sets the estado guardado.
     *
     * @param estadoGuardado
     *            the new estado guardado
     */
    public void setEstadoGuardado(final EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    /**
     * Gets the saga dto.
     *
     * @return the saga dto
     */
    public SagaDTO getSagaDTO() {
        return this.sagaDTO;
    }

    /**
     * Sets the saga dto.
     *
     * @param sagaDTO
     *            the new saga dto
     */
    public void setSagaDTO(final SagaDTO sagaDTO) {
        this.sagaDTO = sagaDTO;
    }

}
