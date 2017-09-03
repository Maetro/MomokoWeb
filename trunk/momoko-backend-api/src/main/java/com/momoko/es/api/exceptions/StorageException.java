/**
 * StorageException.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

/**
 * The Class StorageException.
 */
public class StorageException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6792296449485449939L;

    public StorageException(final String message) {
        super(message);
    }

    public StorageException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
