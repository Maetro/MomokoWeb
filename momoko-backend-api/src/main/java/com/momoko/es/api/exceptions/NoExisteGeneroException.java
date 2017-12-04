/**
 * NoExisteGeneroException.java 23-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

/**
 * The Class NoExisteGeneroException.
 */
public class NoExisteGeneroException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8002679840422683213L;

    /**
     * Instantiates a new no existe genero exception.
     *
     * @param message
     *            the message
     */
    public NoExisteGeneroException(final String message) {
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
    public NoExisteGeneroException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
