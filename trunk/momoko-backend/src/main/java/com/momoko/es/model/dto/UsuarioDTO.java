/**
 * UsuarioDTO.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.model.enums.UserStatusEnum;

/**
 * The Class UsuarioDTO.
 */
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = -4327870243033258976L;

    private Integer usuarioId;

    private String login;

    private String contrasena;

    private String nick;

    private String email;

    private String url;

    private Date fechaRegistro;

    private UserStatusEnum usuarioStatus;

    private String nombreVisible;

    private Integer usuario_rol_id;

    /**
     * Gets the usuario id.
     *
     * @return the usuario id
     */
    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuario id.
     *
     * @param usuarioId
     *            the new usuario id
     */
    public void setUsuarioId(final Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets the login.
     *
     * @param login
     *            the new login
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Gets the contrasena.
     *
     * @return the contrasena
     */
    public String getContrasena() {
        return this.contrasena;
    }

    /**
     * Sets the contrasena.
     *
     * @param contrasena
     *            the new contrasena
     */
    public void setContrasena(final String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Gets the nick.
     *
     * @return the nick
     */
    public String getNick() {
        return this.nick;
    }

    /**
     * Sets the nick.
     *
     * @param nick
     *            the new nick
     */
    public void setNick(final String nick) {
        this.nick = nick;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets the url.
     *
     * @param url
     *            the new url
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Gets the fecha registro.
     *
     * @return the fecha registro
     */
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    /**
     * Sets the fecha registro.
     *
     * @param fechaRegistro
     *            the new fecha registro
     */
    public void setFechaRegistro(final Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Gets the usuario status.
     *
     * @return the usuario status
     */
    public UserStatusEnum getUsuarioStatus() {
        return this.usuarioStatus;
    }

    /**
     * Sets the usuario status.
     *
     * @param usuarioStatus
     *            the new usuario status
     */
    public void setUsuarioStatus(final UserStatusEnum usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    /**
     * Gets the nombre visible.
     *
     * @return the nombre visible
     */
    public String getNombreVisible() {
        return this.nombreVisible;
    }

    /**
     * Sets the nombre visible.
     *
     * @param nombreVisible
     *            the new nombre visible
     */
    public void setNombreVisible(final String nombreVisible) {
        this.nombreVisible = nombreVisible;
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

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UsuarioDTO)) {
            return false;
        }
        final UsuarioDTO castOther = (UsuarioDTO) other;
        return new EqualsBuilder().append(this.usuarioId, castOther.usuarioId).append(this.login, castOther.login)
                .append(this.contrasena, castOther.contrasena).append(this.nick, castOther.nick)
                .append(this.email, castOther.email)
                .append(this.url, castOther.url).append(this.fechaRegistro, castOther.fechaRegistro)
                .append(this.usuarioStatus, castOther.usuarioStatus).append(this.nombreVisible, castOther.nombreVisible)
                .append(this.usuario_rol_id, castOther.usuario_rol_id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.usuarioId).append(this.login).append(this.contrasena).append(this.nick)
                .append(this.email)
                .append(this.url).append(this.fechaRegistro).append(this.usuarioStatus).append(this.nombreVisible)
                .append(this.usuario_rol_id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuarioId", this.usuarioId).append("login", this.login)
                .append("contrasena", this.contrasena).append("nick", this.nick).append("email", this.email)
                .append("url", this.url)
                .append("fechaRegistro", this.fechaRegistro).append("usuarioStatus", this.usuarioStatus)
                .append("nombreVisible", this.nombreVisible).append("usuario_rol_id", this.usuario_rol_id).toString();
    }

}
