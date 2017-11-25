/**
 * ObtenerEntradaResponse.java 05-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.util.List;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class ObtenerEntradaResponse.
 */
public class ObtenerEntradaResponse {

    /** The libro. */
    private EntradaDTO entrada;

    /** The cinco libros parecidos. */
    private List<LibroSimpleDTO> cincoLibrosParecidos;

    /** The comentarios. */
    private List<ComentarioDTO> comentarios;

    /** The obtener entrada anterior y siguiente. */
    private List<EntradaSimpleDTO> obtenerEntradaAnteriorYSiguiente;

    /** The cuatro post pequenos con imagen. */
    private List<EntradaSimpleDTO> cuatroPostPequenosConImagen;

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

    /**
     * Gets the comentarios.
     *
     * @return the comentarios
     */
    public List<ComentarioDTO> getComentarios() {
        return this.comentarios;
    }

    /**
     * Sets the comentarios.
     *
     * @param comentarios
     *            the new comentarios
     */
    public void setComentarios(final List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Gets the obtener entrada anterior y siguiente.
     *
     * @return the obtener entrada anterior y siguiente
     */
    public List<EntradaSimpleDTO> getObtenerEntradaAnteriorYSiguiente() {
        return this.obtenerEntradaAnteriorYSiguiente;
    }

    /**
     * Sets the entrada anterior y siguiente.
     *
     * @param obtenerEntradaAnteriorYSiguiente
     *            the new entrada anterior y siguiente
     */
    public void setEntradaAnteriorYSiguiente(final List<EntradaSimpleDTO> obtenerEntradaAnteriorYSiguiente) {
        this.obtenerEntradaAnteriorYSiguiente = obtenerEntradaAnteriorYSiguiente;

    }

    /**
     * Gets the cuatro post pequenos con imagen.
     *
     * @return the cuatro post pequenos con imagen
     */
    public List<EntradaSimpleDTO> getCuatroPostPequenosConImagen() {
        return this.cuatroPostPequenosConImagen;
    }

    /**
     * Sets the cuatro post pequenos con imagen.
     *
     * @param cuatroPostPequenosConImagen
     *            the new cuatro post pequenos con imagen
     */
    public void setCuatroPostPequenosConImagen(final List<EntradaSimpleDTO> cuatroPostPequenosConImagen) {
        this.cuatroPostPequenosConImagen = cuatroPostPequenosConImagen;
    }

}
