/**
 * EtiquetasDTO.java 23-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * The Class EtiquetasDTO.
 */
public class EtiquetaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8099806036673399761L;

    /** The autor id. */
    private Integer etiquetaId;

    /** The nombre. */
    private String nombreEtiqueta;

    /** The url etiqueta. */
    private String urlEtiqueta;

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

    /**
     * Gets the url etiqueta.
     *
     * @return the url etiqueta
     */
    public String getUrlEtiqueta() {
        return this.urlEtiqueta;
    }

    /**
     * Sets the url etiqueta.
     *
     * @param urlEtiqueta
     *            the new url etiqueta
     */
    public void setUrlEtiqueta(final String urlEtiqueta) {
        this.urlEtiqueta = urlEtiqueta;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EtiquetaDTO)) {
            return false;
        }
        final EtiquetaDTO castOther = (EtiquetaDTO) other;
        return new EqualsBuilder().append(this.etiquetaId, castOther.etiquetaId)
                .append(this.nombreEtiqueta, castOther.nombreEtiqueta).append(this.urlEtiqueta, castOther.urlEtiqueta)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.etiquetaId).append(this.nombreEtiqueta).append(this.urlEtiqueta)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("etiquetaId", this.etiquetaId)
                .append("nombreEtiqueta", this.nombreEtiqueta).toString();
    }

}
