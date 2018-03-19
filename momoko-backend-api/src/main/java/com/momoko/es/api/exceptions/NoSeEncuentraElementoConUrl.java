/**
 * NoSeEncuentraElementoConUrl.java 03-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

public class NoSeEncuentraElementoConUrl extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2160751093417254003L;

    /**
     * Instantiates a new no se encuentra elemento con url.
     *
     * @param message
     *            the message
     */
    public NoSeEncuentraElementoConUrl(final String message) {
        super(message);
    }

    /**
     * Instantiates a new no se encuentra elemento con url.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public NoSeEncuentraElementoConUrl(final String message, final Throwable cause) {
        super(message, cause);
    }

}