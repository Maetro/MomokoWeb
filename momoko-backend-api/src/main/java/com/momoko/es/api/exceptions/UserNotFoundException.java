/**
 * UserNotFoundException.java 12-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException() {
        // TODO Auto-generated con
    }
}
