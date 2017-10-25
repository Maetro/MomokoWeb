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

    /**
     * Obtiene id usuario.
     *
     * @return id usuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece id usuario.
     *
     * @param idUsuario
     *            nuevo id usuario
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre
     *            nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene cargo.
     *
     * @return cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Establece cargo.
     *
     * @param cargo
     *            nuevo cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtiene avatar.
     *
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Establece avatar.
     *
     * @param avatar
     *            nuevo avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioBasicoDTO))
            return false;
        UsuarioBasicoDTO castOther = (UsuarioBasicoDTO) other;
        return new EqualsBuilder().append(idUsuario, castOther.idUsuario).append(nombre, castOther.nombre)
                .append(avatar, castOther.avatar).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(idUsuario).append(nombre).append(avatar).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idUsuario", idUsuario).append("nombre", nombre).append("cargo", cargo)
                .append("avatar", avatar).toString();
    }

}
