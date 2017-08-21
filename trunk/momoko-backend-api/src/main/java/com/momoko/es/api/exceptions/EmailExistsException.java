/**
 * EmailExistsException.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

/**
 * The Class EmailExistsException.
 */
public class EmailExistsException extends Throwable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9104605121643362623L;

    /**
     * Instantiates a new email exists exception.
     *
     * @param message
     *            the message
     */
    public EmailExistsException(final String message) {
        super(message);
    }

}
