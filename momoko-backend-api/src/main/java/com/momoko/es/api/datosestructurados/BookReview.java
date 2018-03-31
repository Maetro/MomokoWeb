/**
 * BookReview.java 28-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.datosestructurados;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BookReview {

    private String context;
    private String type;
    private String breadcrumb;
    private String name;
    private String url;
    private String description;
    private BookMainEntity mainEntity;

    public String getContext() {
        return this.context;
    }

    public void setContext(final String context) {
        this.context = context;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getBreadcrumb() {
        return this.breadcrumb;
    }

    public void setBreadcrumb(final String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public BookMainEntity getMainEntity() {
        return this.mainEntity;
    }

    public void setMainEntity(final BookMainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.mainEntity).append(this.context).append(this.type)
                .append(this.breadcrumb).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookReview) == false) {
            return false;
        }
        final BookReview rhs = ((BookReview) other);
        return new EqualsBuilder().append(this.mainEntity, rhs.mainEntity).append(this.context, rhs.context)
                .append(this.type, rhs.type).append(this.breadcrumb, rhs.breadcrumb).isEquals();
    }

}
