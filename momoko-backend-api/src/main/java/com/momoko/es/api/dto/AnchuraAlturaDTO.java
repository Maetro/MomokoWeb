/**
 * AnchuraAlturaDTO.java 04-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

/**
 * The Class AnchuraAlturaDTO.
 */
public class AnchuraAlturaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6926793051503232563L;

    /** The anchura. */
    private Integer anchura;

    /** The altura. */
    private Integer altura;

    /**
     * Gets the anchura.
     *
     * @return the anchura
     */
    public Integer getAnchura() {
        return this.anchura;
    }

    /**
     * Sets the anchura.
     *
     * @param anchura
     *            the new anchura
     */
    public void setAnchura(final Integer anchura) {
        this.anchura = anchura;
    }

    /**
     * Gets the altura.
     *
     * @return the altura
     */
    public Integer getAltura() {
        return this.altura;
    }

    /**
     * Sets the altura.
     *
     * @param altura
     *            the new altura
     */
    public void setAltura(final Integer altura) {
        this.altura = altura;
    }

}
