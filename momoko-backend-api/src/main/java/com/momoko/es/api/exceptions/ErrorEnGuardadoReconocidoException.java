/**
 * ErrorEnGuardadoReconocidoException.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.exceptions;

public class ErrorEnGuardadoReconocidoException extends Exception {

    private static final long serialVersionUID = 4427285562822303164L;

    public ErrorEnGuardadoReconocidoException(final String message) {
        super(message);
    }

    public ErrorEnGuardadoReconocidoException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
