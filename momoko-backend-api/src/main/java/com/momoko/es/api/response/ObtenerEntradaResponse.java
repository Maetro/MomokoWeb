/**
 * ObtenerEntradaResponse.java 05-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.response;

import java.util.List;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class ObtenerEntradaResponse.
 */
public class ObtenerEntradaResponse {

    /** The libro. */
    private EntradaDTO entrada;

    /** The cinco libros parecidos. */
    private List<LibroSimpleDTO> cincoLibrosParecidos;

    /**
     * Gets the entrada.
     *
     * @return the entrada
     */
    public EntradaDTO getEntrada() {
        return this.entrada;
    }

    /**
     * Sets the entrada.
     *
     * @param entrada
     *            the new entrada
     */
    public void setEntrada(final EntradaDTO entrada) {
        this.entrada = entrada;
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
