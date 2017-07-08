/**
 * CrearUsuarioDummyParameter.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.parameter;

/**
 * The Class CrearUsuarioDummyParameter.
 */
public class CrearUsuarioDummyParameter {
    private String email;
    private String contrasena;
    private String login;
    private String nick;
    private String nombreVisible;
    private String url;

    /**
     * Instantiates a new crear usuario dummy parameter.
     *
     * @param email
     *            the email
     * @param contrasena
     *            the contrasena
     * @param login
     *            the login
     * @param nick
     *            the nick
     * @param nombreVisible
     *            the nombre visible
     * @param url
     *            the url
     */
    public CrearUsuarioDummyParameter(final String email, final String contrasena, final String login,
            final String nick, final String nombreVisible,
            final String url) {
        this.email = email;
        this.contrasena = contrasena;
        this.login = login;
        this.nick = nick;
        this.nombreVisible = nombreVisible;
        this.url = url;
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

}