/**
 * EditorialDTO.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EditorialDTO.
 */
public class EditorialDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8662615990339682558L;

    /** The editorial id. */
    private Integer editorialId;

    /** The nombre editorial. */
    private String nombreEditorial;

    /**
     * Gets the editorial id.
     *
     * @return the editorial id
     */
    public Integer getEditorialId() {
        return this.editorialId;
    }

    /**
     * Sets the editorial id.
     *
     * @param editorialId
     *            the new editorial id
     */
    public void setEditorialId(final Integer editorialId) {
        this.editorialId = editorialId;
    }

    /**
     * Gets the nombre editorial.
     *
     * @return the nombre editorial
     */
    public String getNombreEditorial() {
        return this.nombreEditorial;
    }

    /**
     * Sets the nombre editorial.
     *
     * @param nombreEditorial
     *            the new nombre editorial
     */
    public void setNombreEditorial(final String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EditorialDTO)) {
            return false;
        }
        final EditorialDTO castOther = (EditorialDTO) other;
        return new EqualsBuilder().append(this.editorialId, castOther.editorialId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.editorialId).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("editorialId", this.editorialId)
                .append("nombreEditorial", this.nombreEditorial)
                .toString();
    }

}
