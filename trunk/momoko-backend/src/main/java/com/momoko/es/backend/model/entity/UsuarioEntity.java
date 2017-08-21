/**
 * UsuarioEntity.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class UsuarioEntity.
 */
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer usuario_id;

    private String usuario_login;

    private String usuario_contrasena;

    private String usuario_nick;

    @Column(name = "usuario_email")
    private String usuarioEmail;

    private String usuario_url;

    private Date usuario_fecha_registro;

    private String usuario_clave_activacion;

    private Integer usuario_status;

    private String usuario_nombre_visible;

    private Integer usuario_rol_id;

    /**
     * Gets the usuario_id.
     *
     * @return the usuario_id
     */
    public Integer getUsuario_id() {
        return this.usuario_id;
    }

    /**
     * Sets the usuario_id.
     *
     * @param usuario_id
     *            the new usuario_id
     */
    public void setUsuario_id(final Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * Gets the usuario_login.
     *
     * @return the usuario_login
     */
    public String getUsuario_login() {
        return this.usuario_login;
    }

    /**
     * Sets the usuario_login.
     *
     * @param usuario_login
     *            the new usuario_login
     */
    public void setUsuario_login(final String usuario_login) {
        this.usuario_login = usuario_login;
    }

    /**
     * Gets the usuario_contrasena.
     *
     * @return the usuario_contrasena
     */
    public String getUsuario_contrasena() {
        return this.usuario_contrasena;
    }

    /**
     * Sets the usuario_contrasena.
     *
     * @param usuario_contrasena
     *            the new usuario_contrasena
     */
    public void setUsuario_contrasena(final String usuario_contrasena) {
        this.usuario_contrasena = usuario_contrasena;
    }

    /**
     * Gets the usuario_nick.
     *
     * @return the usuario_nick
     */
    public String getUsuario_nick() {
        return this.usuario_nick;
    }

    /**
     * Sets the usuario_nick.
     *
     * @param usuario_nick
     *            the new usuario_nick
     */
    public void setUsuario_nick(final String usuario_nick) {
        this.usuario_nick = usuario_nick;
    }

    /**
     * Gets the usuario_email.
     *
     * @return the usuario_email
     */
    public String getUsuarioEmail() {
        return this.usuarioEmail;
    }

    /**
     * Sets the usuario_email.
     *
     * @param usuario_email
     *            the new usuario_email
     */
    public void setUsuarioEmail(final String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    /**
     * Gets the usuario_url.
     *
     * @return the usuario_url
     */
    public String getUsuario_url() {
        return this.usuario_url;
    }

    /**
     * Sets the usuario_url.
     *
     * @param usuario_url
     *            the new usuario_url
     */
    public void setUsuario_url(final String usuario_url) {
        this.usuario_url = usuario_url;
    }

    /**
     * Gets the usuario_fecha_registro.
     *
     * @return the usuario_fecha_registro
     */
    public Date getUsuario_fecha_registro() {
        return this.usuario_fecha_registro;
    }

    /**
     * Sets the usuario_fecha_registro.
     *
     * @param usuario_fecha_registro
     *            the new usuario_fecha_registro
     */
    public void setUsuario_fecha_registro(final Date usuario_fecha_registro) {
        this.usuario_fecha_registro = usuario_fecha_registro;
    }

    /**
     * Gets the usuario_clave_activacion.
     *
     * @return the usuario_clave_activacion
     */
    public String getUsuario_clave_activacion() {
        return this.usuario_clave_activacion;
    }

    /**
     * Sets the usuario_clave_activacion.
     *
     * @param usuario_clave_activacion
     *            the new usuario_clave_activacion
     */
    public void setUsuario_clave_activacion(final String usuario_clave_activacion) {
        this.usuario_clave_activacion = usuario_clave_activacion;
    }

    /**
     * Gets the usuario_status.
     *
     * @return the usuario_status
     */
    public Integer getUsuario_status() {
        return this.usuario_status;
    }

    /**
     * Sets the usuario_status.
     *
     * @param usuario_status
     *            the new usuario_status
     */
    public void setUsuario_status(final Integer usuario_status) {
        this.usuario_status = usuario_status;
    }

    /**
     * Gets the usuario_nombre_visible.
     *
     * @return the usuario_nombre_visible
     */
    public String getUsuario_nombre_visible() {
        return this.usuario_nombre_visible;
    }

    /**
     * Sets the usuario_nombre_visible.
     *
     * @param usuario_nombre_visible
     *            the new usuario_nombre_visible
     */
    public void setUsuario_nombre_visible(final String usuario_nombre_visible) {
        this.usuario_nombre_visible = usuario_nombre_visible;
    }

    /**
     * Gets the usuario_rol_id.
     *
     * @return the usuario_rol_id
     */
    public Integer getUsuario_rol_id() {
        return this.usuario_rol_id;
    }

    /**
     * Sets the usuario_rol_id.
     *
     * @param usuario_rol_id
     *            the new usuario_rol_id
     */
    public void setUsuario_rol_id(final Integer usuario_rol_id) {
        this.usuario_rol_id = usuario_rol_id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuario_id", this.usuario_id)
                .append("usuario_login", this.usuario_login).append("usuario_contrasena", this.usuario_contrasena)
                .append("usuario_nick", this.usuario_nick).append("usuario_email", this.usuarioEmail)
                .append("usuario_url", this.usuario_url).append("usuario_fecha_registro", this.usuario_fecha_registro)
                .append("usuario_clave_activacion", this.usuario_clave_activacion)
                .append("usuario_status", this.usuario_status)
                .append("usuario_nombre_visible", this.usuario_nombre_visible)
                .append("usuario_rol_id", this.usuario_rol_id).toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioEntity)) {
            return false;
        }
        final UsuarioEntity castOther = (UsuarioEntity) other;
        return new EqualsBuilder().append(this.usuario_id, castOther.usuario_id)
                .append(this.usuario_login, castOther.usuario_login)
                .append(this.usuario_contrasena, castOther.usuario_contrasena)
                .append(this.usuario_nick, castOther.usuario_nick).append(this.usuarioEmail, castOther.usuarioEmail)
                .append(this.usuario_url, castOther.usuario_url)
                .append(this.usuario_fecha_registro, castOther.usuario_fecha_registro)
                .append(this.usuario_clave_activacion, castOther.usuario_clave_activacion)
                .append(this.usuario_status, castOther.usuario_status)
                .append(this.usuario_nombre_visible, castOther.usuario_nombre_visible)
                .append(this.usuario_rol_id, castOther.usuario_rol_id).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.usuario_id).append(this.usuario_login).append(this.usuario_contrasena)
                .append(this.usuario_nick).append(this.usuarioEmail).append(this.usuario_url)
                .append(this.usuario_fecha_registro).append(this.usuario_clave_activacion).append(this.usuario_status)
                .append(this.usuario_nombre_visible).append(this.usuario_rol_id).toHashCode();
    }

}
