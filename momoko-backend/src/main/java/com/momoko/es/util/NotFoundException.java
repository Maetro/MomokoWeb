/**
 * NotFoundException.java 08-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8002679840422683213L;

    /**
     * Instantiates a new no existe genero exception.
     *
     * @param message
     *            the message
     */
    public NotFoundException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new no existe genero exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
