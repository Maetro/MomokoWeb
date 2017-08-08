/**
 * EditorialEntity.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EditorialEntity.
 */
@Entity
@Table(name = "editorial")
public class EditorialEntity {

    /** The editorial id. */
    private @Id @GeneratedValue Integer editorialId;

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
        if (!(other instanceof EditorialEntity)) {
            return false;
        }
        final EditorialEntity castOther = (EditorialEntity) other;
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
