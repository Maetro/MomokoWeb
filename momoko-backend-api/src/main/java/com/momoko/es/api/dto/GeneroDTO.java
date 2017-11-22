/**
 * GeneroDTO.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class GeneroDTO.
 */
public class GeneroDTO implements Serializable, Comparable<GeneroDTO> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6734916350484920824L;

    /** The autor id. */
    private Integer generoId;

    /** The nombre. */
    private String nombre;

    /** The imagen cabecera genero. */
    private String imagenCabeceraGenero;

    /** The icono genero. */
    private String iconoGenero;

    /** The genero url. */
    private String urlGenero;

    /** The editorial id. */
    private CategoriaDTO categoria;

    /**
     * Gets the genero id.
     *
     * @return the genero id
     */
    public Integer getGeneroId() {
        return this.generoId;
    }

    /**
     * Sets the genero id.
     *
     * @param generoId
     *            the new genero id
     */
    public void setGeneroId(final Integer generoId) {
        this.generoId = generoId;
    }

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
     * Gets the imagen cabecera genero.
     *
     * @return the imagen cabecera genero
     */
    public String getImagenCabeceraGenero() {
        return this.imagenCabeceraGenero;
    }

    /**
     * Sets the imagen cabecera genero.
     *
     * @param imagenCabeceraGenero
     *            the new imagen cabecera genero
     */
    public void setImagenCabeceraGenero(final String imagenCabeceraGenero) {
        this.imagenCabeceraGenero = imagenCabeceraGenero;
    }

    /**
     * Gets the icono genero.
     *
     * @return the icono genero
     */
    public String getIconoGenero() {
        return this.iconoGenero;
    }

    /**
     * Sets the icono genero.
     *
     * @param iconoGenero
     *            the new icono genero
     */
    public void setIconoGenero(final String iconoGenero) {
        this.iconoGenero = iconoGenero;
    }

    /**
     * Gets the url genero.
     *
     * @return the url genero
     */
    public String getUrlGenero() {
        return this.urlGenero;
    }

    /**
     * Sets the url genero.
     *
     * @param urlGenero
     *            the new url genero
     */
    public void setUrlGenero(final String urlGenero) {
        this.urlGenero = urlGenero;
    }

    /**
     * Gets the categoria.
     *
     * @return the categoria
     */
    public CategoriaDTO getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the categoria.
     *
     * @param categoria
     *            the new categoria
     */
    public void setCategoria(final CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GeneroDTO)) {
            return false;
        }
        final GeneroDTO castOther = (GeneroDTO) other;
        return new EqualsBuilder().append(this.generoId, castOther.generoId).append(this.nombre, castOther.nombre)
                .isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.generoId).append(this.nombre).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("generoId", this.generoId).append("nombre", this.nombre).toString();
    }

    public int compareTo(final GeneroDTO other) {
        return new CompareToBuilder().append(this.nombre, other.nombre).toComparison();
    }

}
