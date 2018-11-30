/**
 * GaleriaEntity.java 24-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.jpa.gallery;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class GaleriaEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Entity
@Table(name = "galeria", indexes = { @Index(name = "urlGaleria", columnList = "urlGaleria", unique = true) })
public class GaleriaEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8893566872732185597L;

    /** The galeria id. */
    private @Id @GeneratedValue Integer galeriaId;

    /** The fotografias. */
    private String imagenes;

    /** The columnas. */
    private Integer columnas;

    /** The nombre galeria. */
    private String nombreGaleria;

    /** The url galeria. */
    private String urlGaleria;

    /** The usuario alta. */
    private String usuarioAlta;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The usuario alta. */
    private String usuarioModificacion;

    /** The fecha alta. */
    private Date fechaModificacion;

    /** The usuario alta. */
    private String usuarioBaja;

    /** The fecha alta. */
    private Date fechaBaja;

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
    public String getImagenes() {
        return this.imagenes;
    }

    /**
     * Sets the imagenes.
     *
     * @param imagenes
     *            the new imagenes
     */
    public void setImagenes(final String imagenes) {
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
     * Gets the usuario alta.
     *
     * @return the usuario alta
     */
    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    /**
     * Sets the usuario alta.
     *
     * @param usuarioAlta
     *            the new usuario alta
     */
    public void setUsuarioAlta(final String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * Gets the fecha alta.
     *
     * @return the fecha alta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Sets the fecha alta.
     *
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the usuario modificacion.
     *
     * @return the usuario modificacion
     */
    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    /**
     * Sets the usuario modificacion.
     *
     * @param usuarioModificacion
     *            the new usuario modificacion
     */
    public void setUsuarioModificacion(final String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Gets the fecha modificacion.
     *
     * @return the fecha modificacion
     */
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    /**
     * Sets the fecha modificacion.
     *
     * @param fechaModificacion
     *            the new fecha modificacion
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Gets the usuario baja.
     *
     * @return the usuario baja
     */
    public String getUsuarioBaja() {
        return this.usuarioBaja;
    }

    /**
     * Sets the usuario baja.
     *
     * @param usuarioBaja
     *            the new usuario baja
     */
    public void setUsuarioBaja(final String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Gets the fecha baja.
     *
     * @return the fecha baja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Sets the fecha baja.
     *
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GaleriaEntity)) {
            return false;
        }
        final GaleriaEntity castOther = (GaleriaEntity) other;
        return new EqualsBuilder().append(this.galeriaId, castOther.galeriaId)
                .append(this.urlGaleria, castOther.urlGaleria).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.galeriaId).append(this.urlGaleria).toHashCode();
    }

}
