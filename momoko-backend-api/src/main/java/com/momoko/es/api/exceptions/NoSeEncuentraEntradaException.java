/**
 * NoSeEncuentraEntradaException.java 11-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.exceptions;

/**
 * The Class NoSeEncuentraEntradaException.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class NoSeEncuentraEntradaException extends Exception {

    private static final long serialVersionUID = 4427285562822303164L;

    public NoSeEncuentraEntradaException(final String message) {
        super(message);
    }

    public NoSeEncuentraEntradaException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
