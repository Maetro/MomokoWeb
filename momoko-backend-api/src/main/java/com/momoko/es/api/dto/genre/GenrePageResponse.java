/**
 * ObtenerPaginaGeneroResponse.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.genre;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.filter.dto.FilterDTO;

/**
 * The Class ObtenerPaginaGeneroResponse.
 */
public class GenrePageResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1668326554240492058L;

    /** The libro. */
    private GenreDTO genero;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> tresUltimasEntradasConLibro;

    /** The nueve libros genero. */
    private List<LibroSimpleDTO> nueveLibrosGenero;

    private List<FilterDTO> applicableFilters;

    private Integer numeroLibros;

    /**
     * Gets the genero.
     *
     * @return the genero
     */
    public GenreDTO getGenero() {
        return this.genero;
    }

    /**
     * Sets the genero.
     *
     * @param genero
     *            the new genero
     */
    public void setGenero(final GenreDTO genero) {
        this.genero = genero;
    }

    /**
     * Gets the tres ultimas entradas con libro.
     *
     * @return the tres ultimas entradas con libro
     */
    public List<EntradaSimpleDTO> getTresUltimasEntradasConLibro() {
        return this.tresUltimasEntradasConLibro;
    }

    /**
     * Sets the tres ultimas entradas con libro.
     *
     * @param tresUltimasEntradasConLibro
     *            the new tres ultimas entradas con libro
     */
    public void setTresUltimasEntradasConLibro(final List<EntradaSimpleDTO> tresUltimasEntradasConLibro) {
        this.tresUltimasEntradasConLibro = tresUltimasEntradasConLibro;
    }

    /**
     * Gets the nueve libros genero.
     *
     * @return the nueve libros genero
     */
    public List<LibroSimpleDTO> getNueveLibrosGenero() {
        return this.nueveLibrosGenero;
    }

    /**
     * Sets the nueve libros genero.
     *
     * @param nueveLibrosGenero
     *            the new nueve libros genero
     */
    public void setNueveLibrosGenero(final List<LibroSimpleDTO> nueveLibrosGenero) {
        this.nueveLibrosGenero = nueveLibrosGenero;
    }

    /**
     * Gets the numero libros.
     *
     * @return the numero libros
     */
    public Integer getNumeroLibros() {
        return this.numeroLibros;
    }

    /**
     * Sets the numero libros.
     *
     * @param numeroLibros
     *            the new numero libros
     */
    public void setNumeroLibros(final Integer numeroLibros) {
        this.numeroLibros = numeroLibros;
    }

    public List<FilterDTO> getApplicableFilters() {
        return applicableFilters;
    }

    public void setApplicableFilters(List<FilterDTO> applicableFilters) {
        this.applicableFilters = applicableFilters;
    }
}
