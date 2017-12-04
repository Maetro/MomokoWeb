/**
 * InitDataDTO.java 25-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class InitDataDTO.
 */
public class InitDataDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -83698540591486544L;

    /** The menu. */
    private List<MenuDTO> menu;

    /**
     * Gets the menu.
     *
     * @return the menu
     */
    public List<MenuDTO> getMenu() {
        return this.menu;
    }

    /**
     * Sets the menu.
     *
     * @param menu
     *            the new menu
     */
    public void setMenu(final List<MenuDTO> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof InitDataDTO)) {
            return false;
        }
        final InitDataDTO castOther = (InitDataDTO) other;
        return new EqualsBuilder().append(this.menu, castOther.menu).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.menu).toHashCode();
    }

}
