/**
 * ObtenerPaginaSagaNoticiasResponse.java 05-jul-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.SagaDTO;

/**
 * The Class ObtenerPaginaSagaNoticiasResponse.
 */
public class ObtenerPaginaSagaColeccionResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 318745796338010279L;

    /** The libro. */
    private SagaDTO saga;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> entradas;

    /** The numero entradas. */
    private Integer numeroEntradas;

    /** The datos entrada. */
    private List<DatoEntradaDTO> datosEntrada;

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
     * Gets the noticias.
     *
     * @return the noticias
     */
    public List<EntradaSimpleDTO> getEntradas() {
        return this.entradas;
    }

    /**
     * Sets the noticias.
     *
     * @param noticias
     *            the new noticias
     */
    public void setEntradas(final List<EntradaSimpleDTO> entradas) {
        this.entradas = entradas;
    }

    /**
     * Gets the numero entradas.
     *
     * @return the numero entradas
     */
    public Integer getNumeroEntradas() {
        return this.numeroEntradas;
    }

    /**
     * Sets the numero entradas.
     *
     * @param numeroEntradas
     *            the new numero entradas
     */
    public void setNumeroEntradas(final Integer numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    /**
     * Gets the datos entrada.
     *
     * @return the datos entrada
     */
    public List<DatoEntradaDTO> getDatosEntrada() {
        return this.datosEntrada;
    }

    /**
     * Sets the datos entrada.
     *
     * @param datosEntrada
     *            the new datos entrada
     */
    public void setDatosEntrada(final List<DatoEntradaDTO> datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

}
