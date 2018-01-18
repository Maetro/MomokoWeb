/**
 * ObtenerPaginaBusquedaResponse.java 14-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.ResultadoBusquedaDTO;

/**
 * The Class ObtenerPaginaBusquedaResponse.
 */
public class ObtenerPaginaBusquedaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7632851174147844372L;

    /** The libro. */
    private String parametrosBusqueda;

    /** The tres ultimas entradas. */
    private List<ResultadoBusquedaDTO> resultados;

    /** The numero entradas. */
    private Integer numeroEntradas;

    /**
     * Gets the parametros busqueda.
     *
     * @return the parametros busqueda
     */
    public String getParametrosBusqueda() {
        return this.parametrosBusqueda;
    }

    /**
     * Sets the parametros busqueda.
     *
     * @param parametrosBusqueda
     *            the new parametros busqueda
     */
    public void setParametrosBusqueda(final String parametrosBusqueda) {
        this.parametrosBusqueda = parametrosBusqueda;
    }

    /**
     * Gets the resultados.
     *
     * @return the resultados
     */
    public List<ResultadoBusquedaDTO> getResultados() {
        return this.resultados;
    }

    /**
     * Sets the resultados.
     *
     * @param resultados
     *            the new resultados
     */
    public void setResultados(final List<ResultadoBusquedaDTO> resultados) {
        this.resultados = resultados;
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

}
