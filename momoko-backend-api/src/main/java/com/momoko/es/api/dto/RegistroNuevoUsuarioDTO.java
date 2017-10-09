/**
 * RegistroNuevoUsuarioDTO.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.momoko.es.api.validacion.ContrasenasIguales;

/**
 * The Class RegistroNuevoUsuarioDTO.
 */
@ContrasenasIguales
public class RegistroNuevoUsuarioDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1362460971418371934L;

    @NotNull
    @Size(min = 1)
    private String nombre;

    @NotNull
    @Size(min = 1)
    private String nick;

    @NotNull
    @Size(min = 1)
    private String contrasena;

    @NotNull
    @Size(min = 1)
    private String contrasenaRepetida;

    @NotNull
    @Size(min = 1)
    private String email;

    /**
     * Instantiates a new registro nuevo usuario dto.
     */
    public RegistroNuevoUsuarioDTO() {

    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the apellidos.
     *
     * @return the apellidos
     */
    public String getNick() {
        return this.nick;
    }

    /**
     * Sets the apellidos.
     *
     * @param apellidos
     *            the new apellidos
     */
    public void setNick(final String nick) {
        this.nick = nick;
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
     * Gets the contrasena repetida.
     *
     * @return the contrasena repetida
     */
    public String getContrasenaRepetida() {
        return this.contrasenaRepetida;
    }

    /**
     * Sets the contrasena repetida.
     *
     * @param contrasenaRepetida
     *            the new contrasena repetida
     */
    public void setContrasenaRepetida(final String contrasenaRepetida) {
        this.contrasenaRepetida = contrasenaRepetida;
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

}
