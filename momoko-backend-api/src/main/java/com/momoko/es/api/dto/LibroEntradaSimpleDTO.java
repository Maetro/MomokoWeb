/**
 * LibroEntradaSimpleDTO.java 02-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

/**
 * The Class LibroEntradaSimpleDTO.
 */
public class LibroEntradaSimpleDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6986237641386333093L;

    /** The libro. */
    private LibroSimpleDTO libro;

    /** The entrada. */
    private EntradaSimpleDTO entrada;

    /**
     * Gets the libro.
     *
     * @return the libro
     */
    public LibroSimpleDTO getLibro() {
        return this.libro;
    }

    /**
     * Sets the libro.
     *
     * @param libro
     *            the new libro
     */
    public void setLibro(final LibroSimpleDTO libro) {
        this.libro = libro;
    }

    /**
     * Gets the entrada.
     *
     * @return the entrada
     */
    public EntradaSimpleDTO getEntrada() {
        return this.entrada;
    }

    /**
     * Sets the entrada.
     *
     * @param entrada
     *            the new entrada
     */
    public void setEntrada(final EntradaSimpleDTO entrada) {
        this.entrada = entrada;
    }

}
