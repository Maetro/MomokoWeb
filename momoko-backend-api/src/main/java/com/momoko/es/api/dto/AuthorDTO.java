/**
 * AuthorDTO.java 08-jul-2017
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
 * The Class AuthorDTO.
 */
public class AuthorDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6346918750164335293L;

    /** The autor id. */
    private Integer authorId;

    /** The name. */
    private String name;

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof AuthorDTO)) {
            return false;
        }
        final AuthorDTO castOther = (AuthorDTO) other;
        return new EqualsBuilder().append(this.authorId, castOther.authorId).append(this.name, castOther.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.authorId).append(this.name).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("authorId", this.authorId).append("name", this.name).toString();
    }

}
