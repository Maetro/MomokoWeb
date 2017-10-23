/**
 * EtiquetasDTO.java 23-oct-2017
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
 * The Class EtiquetasDTO.
 */
public class EtiquetasDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8099806036673399761L;

    /** The autor id. */
    private Integer etiquetaId;

    /** The nombre. */
    private String nombreEtiqueta;

    public Integer getEtiquetaId() {
        return this.etiquetaId;
    }

    public void setEtiquetaId(final Integer etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public String getNombreEtiqueta() {
        return this.nombreEtiqueta;
    }

    public void setNombreEtiqueta(final String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EtiquetasDTO)) {
            return false;
        }
        final EtiquetasDTO castOther = (EtiquetasDTO) other;
        return new EqualsBuilder().append(this.etiquetaId, castOther.etiquetaId)
                .append(this.nombreEtiqueta, castOther.nombreEtiqueta).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.etiquetaId).append(this.nombreEtiqueta).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("etiquetaId", this.etiquetaId)
                .append("nombreEtiqueta", this.nombreEtiqueta).toString();
    }

}
