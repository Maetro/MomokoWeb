/**
 * LibroSimpleDTO.java 30-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class LibroSimpleDTO.
 */
public class LibroSimpleDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 968948134318773874L;

    /** The titulo. */
    private String titulo;

    /** The nombre autor. */
    private String nombreAutor;

    /** The nota. */
    private BigDecimal nota;

    /** The portada. */
    private String portada;

    /** The url libro. */
    private String urlLibro;

    /** The portada width. */
    private Integer portadaWidth;

    /** The portada height. */
    private Integer portadaHeight;

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
     * Gets the nombre autor.
     *
     * @return the nombre autor
     */
    public String getNombreAutor() {
        return this.nombreAutor;
    }

    /**
     * Sets the nombre autor.
     *
     * @param nombreAutor
     *            the new nombre autor
     */
    public void setNombreAutor(final String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    /**
     * Gets the nota.
     *
     * @return the nota
     */
    public BigDecimal getNota() {
        return this.nota;
    }

    /**
     * Sets the nota.
     *
     * @param nota
     *            the new nota
     */
    public void setNota(final BigDecimal nota) {
        this.nota = nota;
    }

    /**
     * Gets the portada.
     *
     * @return the portada
     */
    public String getPortada() {
        return this.portada;
    }

    /**
     * Sets the portada.
     *
     * @param portada
     *            the new portada
     */
    public void setPortada(final String portada) {
        this.portada = portada;
    }

    /**
     * Gets the url libro.
     *
     * @return the url libro
     */
    public String getUrlLibro() {
        return this.urlLibro;
    }

    /**
     * Sets the url libro.
     *
     * @param urlLibro
     *            the new url libro
     */
    public void setUrlLibro(final String urlLibro) {
        this.urlLibro = urlLibro;
    }

    /**
     * Gets the portada height.
     *
     * @return the portada height
     */
    public Integer getPortadaHeight() {
        return this.portadaHeight;
    }

    /**
     * Gets the portada width.
     *
     * @return the portada width
     */
    public Integer getPortadaWidth() {
        return this.portadaWidth;
    }

    /**
     * Sets the portada height.
     *
     * @param portadaHeight
     *            the new portada height
     */
    public void setPortadaHeight(final Integer portadaHeight) {
        this.portadaHeight = portadaHeight;
    }

    /**
     * Sets the portada width.
     *
     * @param portadaWidth
     *            the new portada width
     */
    public void setPortadaWidth(final Integer portadaWidth) {
        this.portadaWidth = portadaWidth;
    }

}
