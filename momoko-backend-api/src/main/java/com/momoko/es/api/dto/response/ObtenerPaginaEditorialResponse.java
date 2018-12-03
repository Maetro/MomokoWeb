/**
 * ObtenerPaginaEditorialResponse.java 09-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class ObtenerPaginaEditorialResponse.
 */
public class ObtenerPaginaEditorialResponse implements Serializable {

    private EditorialDTO editorial;
    private List<EntradaSimpleDTO> tresUltimasEntradasEditorial;
    private List<LibroSimpleDTO> nueveLibrosEditorial;
    private Integer numeroLibros;

    /**
     * Gets the editorial.
     *
     * @return the editorial
     */
    public EditorialDTO getEditorial() {
        return this.editorial;
    }

    /**
     * Sets the editorial.
     *
     * @param editorial
     *            the new editorial
     */
    public void setEditorial(final EditorialDTO editorial) {
        this.editorial = editorial;
    }

    /**
     * Gets the tres ultimas entradas editorial.
     *
     * @return the tres ultimas entradas editorial
     */
    public List<EntradaSimpleDTO> getTresUltimasEntradasEditorial() {
        return this.tresUltimasEntradasEditorial;
    }

    /**
     * Sets the tres ultimas entradas editorial.
     *
     * @param tresUltimasEntradasEditorial
     *            the new tres ultimas entradas editorial
     */
    public void setTresUltimasEntradasEditorial(final List<EntradaSimpleDTO> tresUltimasEntradasEditorial) {
        this.tresUltimasEntradasEditorial = tresUltimasEntradasEditorial;
    }

    /**
     * Gets the nueve libros editorial.
     *
     * @return the nueve libros editorial
     */
    public List<LibroSimpleDTO> getNueveLibrosEditorial() {
        return this.nueveLibrosEditorial;
    }

    /**
     * Sets the nueve libros editorial.
     *
     * @param nueveLibrosEditorial
     *            the new nueve libros editorial
     */
    public void setNueveLibrosEditorial(final List<LibroSimpleDTO> nueveLibrosEditorial) {
        this.nueveLibrosEditorial = nueveLibrosEditorial;
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

}
