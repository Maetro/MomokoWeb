/**
 * UsuarioDTO.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class UsuarioDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = -4327870243033258976L;

    /** The usuario id. */
    private Integer usuarioId;

    /** The usuario login. */
    private String usuarioLogin;

    /** The usuario contrasena. */
    private String usuarioContrasena;

    /** The usuario nick. */
    private String usuarioNick;

    /** The usuario email. */
    private String usuarioEmail;

    /** The usuario url. */
    private String usuarioUrl;

    /** The usuario fecha registro. */
    private Date usuarioFechaRegistro;

    /** The usuario clave activacion. */
    private String usuarioClaveActivacion;

    /** The usuario status. */
    private Integer usuarioStatus;

    /** The usuario nombre visible. */
    private String usuarioNombreVisible;

    /** The usuario rol id. */
    private Integer usuarioRolId;

    /**
     * Instancia un nuevo usuario DTO.
     */
    public UsuarioDTO() {
    }

    /**
     * Obtiene usuario id.
     *
     * @return usuario id
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * Establece usuario id.
     *
     * @param usuarioId
     *            nuevo usuario id
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Obtiene usuario login.
     *
     * @return usuario login
     */
    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    /**
     * Establece usuario login.
     *
     * @param usuarioLogin
     *            nuevo usuario login
     */
    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    /**
     * Obtiene usuario contrasena.
     *
     * @return usuario contrasena
     */
    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    /**
     * Establece usuario contrasena.
     *
     * @param usuarioContrasena
     *            nuevo usuario contrasena
     */
    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    /**
     * Obtiene usuario nick.
     *
     * @return usuario nick
     */
    public String getUsuarioNick() {
        return usuarioNick;
    }

    /**
     * Establece usuario nick.
     *
     * @param usuarioNick
     *            nuevo usuario nick
     */
    public void setUsuarioNick(String usuarioNick) {
        this.usuarioNick = usuarioNick;
    }

    /**
     * Obtiene usuario email.
     *
     * @return usuario email
     */
    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    /**
     * Establece usuario email.
     *
     * @param usuarioEmail
     *            nuevo usuario email
     */
    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    /**
     * Obtiene usuario url.
     *
     * @return usuario url
     */
    public String getUsuarioUrl() {
        return usuarioUrl;
    }

    /**
     * Establece usuario url.
     *
     * @param usuarioUrl
     *            nuevo usuario url
     */
    public void setUsuarioUrl(String usuarioUrl) {
        this.usuarioUrl = usuarioUrl;
    }

    /**
     * Obtiene usuario fecha registro.
     *
     * @return usuario fecha registro
     */
    public Date getUsuarioFechaRegistro() {
        return usuarioFechaRegistro;
    }

    /**
     * Establece usuario fecha registro.
     *
     * @param usuarioFechaRegistro
     *            nuevo usuario fecha registro
     */
    public void setUsuarioFechaRegistro(Date usuarioFechaRegistro) {
        this.usuarioFechaRegistro = usuarioFechaRegistro;
    }

    /**
     * Obtiene usuario clave activacion.
     *
     * @return usuario clave activacion
     */
    public String getUsuarioClaveActivacion() {
        return usuarioClaveActivacion;
    }

    /**
     * Establece usuario clave activacion.
     *
     * @param usuarioClaveActivacion
     *            nuevo usuario clave activacion
     */
    public void setUsuarioClaveActivacion(String usuarioClaveActivacion) {
        this.usuarioClaveActivacion = usuarioClaveActivacion;
    }

    /**
     * Obtiene usuario status.
     *
     * @return usuario status
     */
    public Integer getUsuarioStatus() {
        return usuarioStatus;
    }

    /**
     * Establece usuario status.
     *
     * @param usuarioStatus
     *            nuevo usuario status
     */
    public void setUsuarioStatus(Integer usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    /**
     * Obtiene usuario nombre visible.
     *
     * @return usuario nombre visible
     */
    public String getUsuarioNombreVisible() {
        return usuarioNombreVisible;
    }

    /**
     * Establece usuario nombre visible.
     *
     * @param usuarioNombreVisible
     *            nuevo usuario nombre visible
     */
    public void setUsuarioNombreVisible(String usuarioNombreVisible) {
        this.usuarioNombreVisible = usuarioNombreVisible;
    }

    /**
     * Obtiene usuario rol id.
     *
     * @return usuario rol id
     */
    public Integer getUsuarioRolId() {
        return usuarioRolId;
    }

    /**
     * Establece usuario rol id.
     *
     * @param usuarioRolId
     *            nuevo usuario rol id
     */
    public void setUsuarioRolId(Integer usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioDTO))
            return false;
        UsuarioDTO castOther = (UsuarioDTO) other;
        return new EqualsBuilder().append(usuarioId, castOther.usuarioId).append(usuarioLogin, castOther.usuarioLogin)
                .append(usuarioContrasena, castOther.usuarioContrasena).append(usuarioNick, castOther.usuarioNick)
                .append(usuarioEmail, castOther.usuarioEmail).append(usuarioUrl, castOther.usuarioUrl)
                .append(usuarioFechaRegistro, castOther.usuarioFechaRegistro)
                .append(usuarioClaveActivacion, castOther.usuarioClaveActivacion)
                .append(usuarioStatus, castOther.usuarioStatus)
                .append(usuarioNombreVisible, castOther.usuarioNombreVisible)
                .append(usuarioRolId, castOther.usuarioRolId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(usuarioId).append(usuarioLogin).append(usuarioContrasena)
                .append(usuarioNick).append(usuarioEmail).append(usuarioUrl).append(usuarioFechaRegistro)
                .append(usuarioClaveActivacion).append(usuarioStatus).append(usuarioNombreVisible).append(usuarioRolId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuarioId", usuarioId).append("usuarioLogin", usuarioLogin)
                .append("usuarioContrasena", usuarioContrasena).append("usuarioNick", usuarioNick)
                .append("usuarioEmail", usuarioEmail).append("usuarioUrl", usuarioUrl)
                .append("usuarioFechaRegistro", usuarioFechaRegistro)
                .append("usuarioClaveActivacion", usuarioClaveActivacion).append("usuarioStatus", usuarioStatus)
                .append("usuarioNombreVisible", usuarioNombreVisible).append("usuarioRolId", usuarioRolId).toString();
    }

}
