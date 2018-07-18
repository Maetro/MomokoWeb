/**
 * ObtenerFichaLibroResponse.java 05-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class ObtenerFichaLibroResponse.
 */
public class ObtenerFichaLibroResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1668326554240492058L;

    /** The libro. */
    private LibroDTO libro;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> tresUltimasEntradas;

    /** The cinco libros parecidos. */
    private List<LibroSimpleDTO> cincoLibrosParecidos;

    private List<DatoEntradaDTO> datosEntrada;

    /**
     * Gets the libro.
     *
     * @return the libro
     */
    public LibroDTO getLibro() {
        return this.libro;
    }

    /**
     * Sets the libro.
     *
     * @param libro
     *            the new libro
     */
    public void setLibro(final LibroDTO libro) {
        this.libro = libro;
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

    public List<DatoEntradaDTO> getDatosEntrada() {
        return datosEntrada;
    }

    public void setDatosEntrada(List<DatoEntradaDTO> datosEntrada) {
        this.datosEntrada = datosEntrada;
    }
}
