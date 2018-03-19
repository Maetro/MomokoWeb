/**
 * NoSeEncuentranLibrosSagaException.java 02-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

/**
 * The Class NoSeEncuentranLibrosSagaException.
 */
public class NoSeEncuentranLibrosSagaException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3042125571949850023L;

    /**
     * Instantiates a new no se encuentran libros saga exception.
     *
     * @param message
     *            the message
     */
    public NoSeEncuentranLibrosSagaException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new no se encuentran libros saga exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public NoSeEncuentranLibrosSagaException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
