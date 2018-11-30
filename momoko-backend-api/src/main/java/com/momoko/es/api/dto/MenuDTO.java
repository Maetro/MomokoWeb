/**
 * MenuDTO.java 22-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;


import com.momoko.es.api.dto.genre.GenreDTO;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The Class MenuDTO.
 */
public class MenuDTO implements Serializable, Comparable<MenuDTO> {

    private static final long serialVersionUID = -7504636983459471832L;

    /** The nombre. */
    private String nombre;

    /** The orden. */
    private Integer orden;

    /** The url. */
    private String url;

    /** The generos. */
    private List<GenreDTO> generos;

    private boolean showOnMenu;

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
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
     * Gets the generos.
     *
     * @return the generos
     */
    public List<GenreDTO> getGeneros() {
        return this.generos;
    }

    /**
     * Sets the generos.
     *
     * @param generos
     *            the new generos
     */
    public void setGeneros(final List<GenreDTO> generos) {
        this.generos = generos;
    }

    /**
     * Gets the orden.
     *
     * @return the orden
     */
    public Integer getOrden() {
        return this.orden;
    }

    /**
     * Sets the orden.
     *
     * @param orden
     *            the new orden
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    public boolean isShowOnMenu() {
        return showOnMenu;
    }

    public void setShowOnMenu(boolean showOnMenu) {
        this.showOnMenu = showOnMenu;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof MenuDTO)) {
            return false;
        }
        final MenuDTO castOther = (MenuDTO) other;
        return new EqualsBuilder().append(this.url, castOther.url).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.url).toHashCode();
    }

    public int compareTo(final MenuDTO other) {
        return new CompareToBuilder().append(this.orden, other.orden).toComparison();
    }

}
