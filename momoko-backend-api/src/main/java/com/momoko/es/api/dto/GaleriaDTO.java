/**
 * GaleriaDTO.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The Class GaleriaDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class GaleriaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 226854924754776466L;

    /** The galeria id. */
    private Integer galeriaId;

    /** The fotografias. */
    private List<String> imagenes;

    /** The columnas. */
    private Integer columnas;

    /** The entrada. */
    private String urlGaleria;

    /** The nombre galeria. */
    private String nombreGaleria;

    /**
     * Gets the galeria id.
     *
     * @return the galeria id
     */
    public Integer getGaleriaId() {
        return this.galeriaId;
    }

    /**
     * Sets the galeria id.
     *
     * @param galeriaId
     *            the new galeria id
     */
    public void setGaleriaId(final Integer galeriaId) {
        this.galeriaId = galeriaId;
    }

    /**
     * Gets the imagenes.
     *
     * @return the imagenes
     */
    public List<String> getImagenes() {
        return this.imagenes;
    }

    /**
     * Sets the imagenes.
     *
     * @param imagenes
     *            the new imagenes
     */
    public void setImagenes(final List<String> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     * Gets the columnas.
     *
     * @return the columnas
     */
    public Integer getColumnas() {
        return this.columnas;
    }

    /**
     * Sets the columnas.
     *
     * @param columnas
     *            the new columnas
     */
    public void setColumnas(final Integer columnas) {
        this.columnas = columnas;
    }

    /**
     * Gets the url galeria.
     *
     * @return the url galeria
     */
    public String getUrlGaleria() {
        return this.urlGaleria;
    }

    /**
     * Sets the url galeria.
     *
     * @param urlGaleria
     *            the new url galeria
     */
    public void setUrlGaleria(final String urlGaleria) {
        this.urlGaleria = urlGaleria;
    }

    /**
     * Gets the nombre galeria.
     *
     * @return the nombre galeria
     */
    public String getNombreGaleria() {
        return this.nombreGaleria;
    }

    /**
     * Sets the nombre galeria.
     *
     * @param nombreGaleria
     *            the new nombre galeria
     */
    public void setNombreGaleria(final String nombreGaleria) {
        this.nombreGaleria = nombreGaleria;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GaleriaDTO)) {
            return false;
        }
        final GaleriaDTO castOther = (GaleriaDTO) other;
        return new EqualsBuilder().append(this.galeriaId, castOther.galeriaId)
                .append(this.urlGaleria, castOther.urlGaleria).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.galeriaId).append(this.urlGaleria).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("galeriaId", this.galeriaId).append("imagenes", this.imagenes)
                .append("columnas", this.columnas).append("urlGaleria", this.urlGaleria)
                .append("nombreGaleria", this.nombreGaleria).toString();
    }

}
