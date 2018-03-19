/**
 * ObtenerFichaSagaResponse.java 25-feb-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.SagaDTO;

/**
 * The Class ObtenerFichaSagaResponse.
 */
public class ObtenerFichaSagaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7045750907226393533L;

    /** The saga. */
    private SagaDTO saga;

    /** The libro. */
    private List<LibroDTO> librosSaga;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> tresUltimasEntradas;

    /** The cinco libros parecidos. */
    private List<LibroSimpleDTO> cincoLibrosParecidos;

    /**
     * Gets the libros saga.
     *
     * @return the libros saga
     */
    public List<LibroDTO> getLibrosSaga() {
        return this.librosSaga;
    }

    /**
     * Sets the libros saga.
     *
     * @param librosSaga
     *            the new libros saga
     */
    public void setLibrosSaga(final List<LibroDTO> librosSaga) {
        this.librosSaga = librosSaga;
    }

    /**
     * Gets the saga.
     *
     * @return the saga
     */
    public SagaDTO getSaga() {
        return this.saga;
    }

    /**
     * Sets the saga.
     *
     * @param saga
     *            the new saga
     */
    public void setSaga(final SagaDTO saga) {
        this.saga = saga;
    }

    /**
     * Gets the tres ultimas entradas.
     *
     * @return the tres ultimas entradas
     */
    public List<EntradaSimpleDTO> getTresUltimasEntradas() {
        return this.tresUltimasEntradas;
    }

    /**
     * Sets the tres ultimas entradas.
     *
     * @param tresUltimasEntradas
     *            the new tres ultimas entradas
     */
    public void setTresUltimasEntradas(final List<EntradaSimpleDTO> tresUltimasEntradas) {
        this.tresUltimasEntradas = tresUltimasEntradas;
    }

    /**
     * Gets the cinco libros parecidos.
     *
     * @return the cinco libros parecidos
     */
    public List<LibroSimpleDTO> getCincoLibrosParecidos() {
        return this.cincoLibrosParecidos;
    }

    /**
     * Sets the cinco libros parecidos.
     *
     * @param cincoLibrosParecidos
     *            the new cinco libros parecidos
     */
    public void setCincoLibrosParecidos(final List<LibroSimpleDTO> cincoLibrosParecidos) {
        this.cincoLibrosParecidos = cincoLibrosParecidos;
    }

}
