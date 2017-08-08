/**
 * GeneroDTO.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GeneroDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6734916350484920824L;

    /** The autor id. */
    private Integer generoId;

    /** The nombre. */
    private String nombre;

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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GeneroDTO)) {
            return false;
        }
        final GeneroDTO castOther = (GeneroDTO) other;
        return new EqualsBuilder().append(this.generoId, castOther.generoId).append(this.nombre, castOther.nombre)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.generoId).append(this.nombre).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("generoId", this.generoId).append("nombre", this.nombre).toString();
    }

}
