/**
 * UsuarioBasicoDTO.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class UsuarioBasicoDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class UsuarioBasicoDTO implements Serializable {

    private static final long serialVersionUID = -5955479291230567701L;

    /** The id usuario. */
    private Integer idUsuario;

    /** The nombre. */
    private String nombre;

    /** The cargo. */
    private String cargo;

    /** The avatar. */
    private String avatar;

    /** The url usuario. */
    private String urlUsuario;

    /**
     * Obtiene id usuario.
     *
     * @return id usuario
     */
    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    /**
     * Establece id usuario.
     *
     * @param idUsuario
     *            nuevo id usuario
     */
    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre
     *            nuevo nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene cargo.
     *
     * @return cargo
     */
    public String getCargo() {
        return this.cargo;
    }

    /**
     * Establece cargo.
     *
     * @param cargo
     *            nuevo cargo
     */
    public void setCargo(final String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtiene avatar.
     *
     * @return avatar
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * Establece avatar.
     *
     * @param avatar
     *            nuevo avatar
     */
    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets the url usuario.
     *
     * @return the url usuario
     */
    public String getUrlUsuario() {
        return this.urlUsuario;
    }

    /**
     * Sets the url usuario.
     *
     * @param urlUsuario
     *            the new url usuario
     */
    public void setUrlUsuario(final String urlUsuario) {
        this.urlUsuario = urlUsuario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioBasicoDTO)) {
            return false;
        }
        final UsuarioBasicoDTO castOther = (UsuarioBasicoDTO) other;
        return new EqualsBuilder().append(this.idUsuario, castOther.idUsuario).append(this.nombre, castOther.nombre)
                .append(this.avatar, castOther.avatar).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.idUsuario).append(this.nombre).append(this.avatar).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idUsuario", this.idUsuario).append("nombre", this.nombre)
                .append("cargo", this.cargo).append("avatar", this.avatar).toString();
    }

}
