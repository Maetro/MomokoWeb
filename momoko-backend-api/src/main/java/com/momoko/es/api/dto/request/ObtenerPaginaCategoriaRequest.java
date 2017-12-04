/**
 * ObtenerPaginaCategoriaRequest.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.request;

import java.io.Serializable;

public class ObtenerPaginaCategoriaRequest implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7419242802857600919L;

    /** The numero pagina. */
    private Integer numeroPagina;

    /** The url genero. */
    private String urlCategoria;

    /** The ordenar por. */
    private String ordenarPor;

    /**
     * Gets the numero pagina.
     *
     * @return the numero pagina
     */
    public Integer getNumeroPagina() {
        return this.numeroPagina;
    }

    /**
     * Sets the numero pagina.
     *
     * @param numeroPagina
     *            the new numero pagina
     */
    public void setNumeroPagina(final Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    /**
     * Gets the url genero.
     *
     * @return the url genero
     */
    public String getUrlCategoria() {
        return this.urlCategoria;
    }

    /**
     * Sets the url genero.
     *
     * @param urlGenero
     *            the new url genero
     */
    public void setUrlGenero(final String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }

    /**
     * Gets the ordenar por.
     *
     * @return the ordenar por
     */
    public String getOrdenarPor() {
        return this.ordenarPor;
    }

    /**
     * Sets the ordenar por.
     *
     * @param ordenarPor
     *            the new ordenar por
     */
    public void setOrdenarPor(final String ordenarPor) {
        this.ordenarPor = ordenarPor;
    }

}
