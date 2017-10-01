/**
 * AuditoriaBasica.java 26-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class AuditoriaBasica.
 */
public abstract class AuditoriaBasica {

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

    public EqualsBuilder equalsAuditoria(final AuditoriaBasica comparado, final Object other) {
        final AuditoriaBasica castOther = (AuditoriaBasica) other;
        return new EqualsBuilder().append(comparado.usuarioAlta, castOther.usuarioAlta)
                .append(comparado.fechaAlta, castOther.fechaAlta)
                .append(comparado.usuarioModificacion, castOther.usuarioModificacion)
                .append(comparado.fechaModificacion, castOther.fechaModificacion)
                .append(comparado.usuarioBaja, castOther.usuarioBaja).append(comparado.fechaBaja, castOther.fechaBaja);
    }

    public HashCodeBuilder hashCodeAuditoria(final AuditoriaBasica comparado) {
        return new HashCodeBuilder().append(comparado.usuarioAlta).append(comparado.fechaAlta)
                .append(comparado.usuarioModificacion).append(comparado.fechaModificacion).append(comparado.usuarioBaja)
                .append(comparado.fechaBaja);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuarioAlta", this.usuarioAlta).append("fechaAlta", this.fechaAlta)
                .append("usuarioModificacion", this.usuarioModificacion)
                .append("fechaModificacion", this.fechaModificacion).append("usuarioBaja", this.usuarioBaja)
                .append("fechaBaja", this.fechaBaja).toString();
    }

}
