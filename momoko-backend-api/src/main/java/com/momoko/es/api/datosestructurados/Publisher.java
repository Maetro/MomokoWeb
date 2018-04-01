/**
 * Publisher.java 01-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.datosestructurados;

import java.io.Serializable;

public class Publisher implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 349118749227951519L;
    private String type;
    private String name;
    private Logo logo;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the logo.
     *
     * @return the logo
     */
    public Logo getLogo() {
        return this.logo;
    }

    /**
     * Sets the logo.
     *
     * @param logo
     *            the new logo
     */
    public void setLogo(final Logo logo) {
        this.logo = logo;
    }

}
