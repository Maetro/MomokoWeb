package com.momoko.es.api.exceptions;

public class UrlElementNotFoundException extends Exception {

    private static final long serialVersionUID = 2160751093417254003L;

    public UrlElementNotFoundException(final String message) {
        super(message);
    }

    public UrlElementNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}