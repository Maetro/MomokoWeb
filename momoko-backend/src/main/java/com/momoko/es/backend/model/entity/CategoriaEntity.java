/**
 * CategoriaEntity.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class CategoriaEntity.
 */
@Entity
@Table(name = "categoria", indexes = { @Index(name = "urlCategoria", columnList = "urlCategoria", unique = true) })
public class CategoriaEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer categoria_id;

    /** The url categoria. */
    private String urlCategoria;

    /** The nombre categoria. */
    private String nombreCategoria;

    /** The background color. */
    private String backgroundColor;

    /** The foreground color. */
    private String foregroundColor;

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
     * Gets the categoria_id.
     *
     * @return the categoria_id
     */
    public Integer getCategoria_id() {
        return this.categoria_id;
    }

    /**
     * Sets the categoria_id.
     *
     * @param categoria_id
     *            the new categoria_id
     */
    public void setCategoria_id(final Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    /**
     * Gets the nombre categoria.
     *
     * @return the nombre categoria
     */
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    /**
     * Sets the nombre categoria.
     *
     * @param nombreCategoria
     *            the new nombre categoria
     */
    public void setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Gets the url categoria.
     *
     * @return the url categoria
     */
    public String getUrlCategoria() {
        return this.urlCategoria;
    }

    /**
     * Sets the url categoria.
     *
     * @param urlCategoria
     *            the new url categoria
     */
    public void setUrlCategoria(final String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }

    /**
     * Gets the background color.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the background color.
     *
     * @param backgroundColor
     *            the new background color
     */
    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets the foreground color.
     *
     * @return the foreground color
     */
    public String getForegroundColor() {
        return this.foregroundColor;
    }

    /**
     * Sets the foreground color.
     *
     * @param foregroundColor
     *            the new foreground color
     */
    public void setForegroundColor(final String foregroundColor) {
        this.foregroundColor = foregroundColor;
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

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CategoriaEntity)) {
            return false;
        }
        final CategoriaEntity castOther = (CategoriaEntity) other;
        return new EqualsBuilder().append(this.categoria_id, castOther.categoria_id)
                .append(this.urlCategoria, castOther.urlCategoria)
                .append(this.backgroundColor, castOther.backgroundColor)
                .append(this.foregroundColor, castOther.foregroundColor).append(this.usuarioAlta, castOther.usuarioAlta)
                .append(this.fechaAlta, castOther.fechaAlta)
                .append(this.usuarioModificacion, castOther.usuarioModificacion)
                .append(this.fechaModificacion, castOther.fechaModificacion)
                .append(this.usuarioBaja, castOther.usuarioBaja).append(this.fechaBaja, castOther.fechaBaja).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.categoria_id).append(this.urlCategoria).append(this.backgroundColor)
                .append(this.foregroundColor).append(this.usuarioAlta).append(this.fechaAlta)
                .append(this.usuarioModificacion).append(this.fechaModificacion).append(this.usuarioBaja)
                .append(this.fechaBaja).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoria_id", this.categoria_id)
                .append("urlCategoria", this.urlCategoria).append("backgroundColor", this.backgroundColor)
                .append("foregroundColor", this.foregroundColor).append("usuarioAlta", this.usuarioAlta)
                .append("fechaAlta", this.fechaAlta).append("usuarioModificacion", this.usuarioModificacion)
                .append("fechaModificacion", this.fechaModificacion).append("usuarioBaja", this.usuarioBaja)
                .append("fechaBaja", this.fechaBaja).toString();
    }

}
