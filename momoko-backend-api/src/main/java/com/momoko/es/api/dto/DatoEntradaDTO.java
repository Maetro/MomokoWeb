/**
 * DatoEntradaDTO.java 03-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

/**
 * The Class DatoEntradaDTO.
 */
public class DatoEntradaDTO implements Serializable {

    private static final long serialVersionUID = 1771931410522575737L;
    private Integer tipoEntrada;
    private String urlEntrada;

    /**
     * Gets the tipo entrada.
     *
     * @return the tipo entrada
     */
    public Integer getTipoEntrada() {
        return this.tipoEntrada;
    }

    /**
     * Sets the tipo entrada.
     *
     * @param tipoEntrada
     *            the new tipo entrada
     */
    public void setTipoEntrada(final Integer tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    /**
     * Gets the url entrada.
     *
     * @return the url entrada
     */
    public String getUrlEntrada() {
        return this.urlEntrada;
    }

    /**
     * Sets the url entrada.
     *
     * @param urlEntrada
     *            the new url entrada
     */
    public void setUrlEntrada(final String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

}
