package com.momoko.es.commons.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class UsuarioDTO<T>{

    private static final long serialVersionUID = -4327870243033258976L;

    private T userId;

    /** The usuario id. */
    private Integer usuarioId;

    /** The usuario login. */
    private String usuarioLogin;

    /** The usuario contrasena. */
    private String usuarioContrasena;

    /** The usuario nick. */
    private String usuarioNick;

    /** The usuario email. */
    private String email;

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

    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();
    private Serializable tag;

    private boolean unverified = false;
    private boolean blocked = false;
    private boolean admin = false;
    private boolean goodUser = false;
    private boolean goodAdmin = false;

    /**
     * Instancia un nuevo usuario DTO.
     */
    public UsuarioDTO() {
    }

    public T getUserId() {
        return userId;
    }

    public void setUserId(T userId) {
        this.userId = userId;
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

    public Integer getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Integer usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
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
    public String getEmail() {
        return email;
    }

    /**
     * Establece usuario email.
     *
     * @param email
     *            nuevo usuario email
     */
    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Serializable getTag() {
        return tag;
    }

    public void setTag(Serializable tag) {
        this.tag = tag;
    }

    public boolean isUnverified() {
        return unverified;
    }

    public void setUnverified(boolean unverified) {
        this.unverified = unverified;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isGoodUser() {
        return goodUser;
    }

    public void setGoodUser(boolean goodUser) {
        this.goodUser = goodUser;
    }

    public boolean isGoodAdmin() {
        return goodAdmin;
    }

    public void setGoodAdmin(boolean goodAdmin) {
        this.goodAdmin = goodAdmin;
    }

}
