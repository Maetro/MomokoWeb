/**
 * ResultadoBusquedaDTO.java 14-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

/**
 * The Class ResultadoBusquedaDTO.
 */
public class ResultadoBusquedaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5830796064569493236L;

    /** The titulo. */
    private String titulo;

    /** The html titulo. */
    private String htmlTitulo;

    /** The descripcion. */
    private String descripcion;

    /** The html descripcion. */
    private String htmlDescripcion;

    /** The url. */
    private String url;

    /** The imagen. */
    private String imagen;

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the html titulo.
     *
     * @return the html titulo
     */
    public String getHtmlTitulo() {
        return this.htmlTitulo;
    }

    /**
     * Sets the html titulo.
     *
     * @param htmlTitulo
     *            the new html titulo
     */
    public void setHtmlTitulo(final String htmlTitulo) {
        this.htmlTitulo = htmlTitulo;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the html descripcion.
     *
     * @return the html descripcion
     */
    public String getHtmlDescripcion() {
        return this.htmlDescripcion;
    }

    /**
     * Sets the html descripcion.
     *
     * @param htmlDescripcion
     *            the new html descripcion
     */
    public void setHtmlDescripcion(final String htmlDescripcion) {
        this.htmlDescripcion = htmlDescripcion;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets the url.
     *
     * @param url
     *            the new url
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Gets the imagen.
     *
     * @return the imagen
     */
    public String getImagen() {
        return this.imagen;
    }

    /**
     * Sets the imagen.
     *
     * @param imagen
     *            the new imagen
     */
    public void setImagen(final String imagen) {
        this.imagen = imagen;
    }

}
