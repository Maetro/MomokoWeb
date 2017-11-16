/**
 * StringResponse.java 20-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;

/**
 * The Class StringResponseDTO.
 */
public class StringResponseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8338610363709514803L;

    /** The response. */
    private final String response;

    /**
     * Instantiates a new string response dto.
     *
     * @param s
     *            the s
     */
    public StringResponseDTO(final String s) {
        this.response = s;
    }

    /**
     * Gets the response.
     *
     * @return the response
     */
    public String getResponse() {
        return this.response;
    }

}