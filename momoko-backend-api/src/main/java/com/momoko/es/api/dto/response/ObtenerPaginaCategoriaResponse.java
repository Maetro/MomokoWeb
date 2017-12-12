/**
 * ObtenerPaginaCategoriaResponse.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;

public class ObtenerPaginaCategoriaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7632851174147844372L;

    /** The libro. */
    private CategoriaDTO categoria;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> entradasCategoria;

    /** The numero entradas. */
    private Integer numeroEntradas;

    /**
     * Gets the genero.
     *
     * @return the genero
     */
    public CategoriaDTO getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the genero.
     *
     * @param genero
     *            the new genero
     */
    public void setCategoria(final CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets the entradas categoria.
     *
     * @return the entradas categoria
     */
    public List<EntradaSimpleDTO> getEntradasCategoria() {
        return this.entradasCategoria;
    }

    /**
     * Sets the entradas categoria.
     *
     * @param entradasCategoria
     *            the new entradas categoria
     */
    public void setEntradasCategoria(final List<EntradaSimpleDTO> entradasCategoria) {
        this.entradasCategoria = entradasCategoria;

    }

    /**
     * Gets the numero entradas.
     *
     * @return the numero entradas
     */
    public Integer getNumeroEntradas() {
        return this.numeroEntradas;
    }

    /**
     * Sets the numero entradas.
     *
     * @param numeroEntradas
     *            the new numero entradas
     */
    public void setNumeroEntradas(final Integer numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

}
