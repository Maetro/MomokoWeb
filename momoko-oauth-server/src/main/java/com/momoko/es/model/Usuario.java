/**
 * Usuario.java 14-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model;

public class Usuario {
    private String email;
    private String name;

    public Usuario() {
    }

    public Usuario(final String email, final String name) {
        super();
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
