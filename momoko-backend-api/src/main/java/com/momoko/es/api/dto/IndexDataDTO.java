/**
 * IndexDataDTO.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class IndexDataDTO.
 */
public class IndexDataDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5400417252436408834L;

    /** The ultimas entradas. */
    private List<EntradaSimpleDTO> ultimasEntradas;

    /** The libros mas vistos. */
    private List<LibroSimpleDTO> librosMasVistos;

    /** The menu. */
    private List<MenuDTO> menu;

    /**
     * Gets the ultimas entradas.
     *
     * @return the ultimas entradas
     */
    public List<EntradaSimpleDTO> getUltimasEntradas() {
        return this.ultimasEntradas;
    }

    /**
     * Sets the ultimas entradas.
     *
     * @param ultimasEntradas
     *            the new ultimas entradas
     */
    public void setUltimasEntradas(final List<EntradaSimpleDTO> ultimasEntradas) {
        this.ultimasEntradas = ultimasEntradas;
    }

    /**
     * Gets the libros mas vistos.
     *
     * @return the libros mas vistos
     */
    public List<LibroSimpleDTO> getLibrosMasVistos() {
        return this.librosMasVistos;
    }

    /**
     * Sets the libros mas vistos.
     *
     * @param librosMasVistos
     *            the new libros mas vistos
     */
    public void setLibrosMasVistos(final List<LibroSimpleDTO> librosMasVistos) {
        this.librosMasVistos = librosMasVistos;
    }

    /**
     * Gets the menu.
     *
     * @return the menu
     */
    public List<MenuDTO> getMenu() {
        return this.menu;
    }

    /**
     * Sets the menu.
     *
     * @param menu
     *            the new menu
     */
    public void setMenu(final List<MenuDTO> menu) {
        this.menu = menu;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof IndexDataDTO)) {
            return false;
        }
        final IndexDataDTO castOther = (IndexDataDTO) other;
        return new EqualsBuilder().append(this.ultimasEntradas, castOther.ultimasEntradas).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.ultimasEntradas).toHashCode();
    }

}
