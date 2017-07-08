/**
 * AutorDTO.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class AutorDTO.
 */
public class AutorDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6346918750164335293L;

    /** The autor id. */
    private Integer autorId;

    /** The nombre. */
    private String nombre;

    public Integer getAutorId() {
        return this.autorId;
    }

    public void setAutorId(final Integer autorId) {
        this.autorId = autorId;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof AutorDTO)) {
            return false;
        }
        final AutorDTO castOther = (AutorDTO) other;
        return new EqualsBuilder().append(this.autorId, castOther.autorId).append(this.nombre, castOther.nombre)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.autorId).append(this.nombre).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("autorId", this.autorId).append("nombre", this.nombre).toString();
    }

}
