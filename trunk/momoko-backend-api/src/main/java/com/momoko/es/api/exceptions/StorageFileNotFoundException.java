/**
 * StorageFileNotFoundException.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

/**
 * The Class StorageFileNotFoundException.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(final String message) {
        super(message);
    }

    public StorageFileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}