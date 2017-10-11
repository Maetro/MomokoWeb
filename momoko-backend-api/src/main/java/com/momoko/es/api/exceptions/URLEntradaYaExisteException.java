package com.momoko.es.api.exceptions;

public class URLEntradaYaExisteException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6792296449485449939L;

    public URLEntradaYaExisteException(final String message) {
        super(message);
    }

    public URLEntradaYaExisteException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
