/**
 * Review_.java 21-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.datosestructurados;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Review implements Serializable {

    private String type;
    private String author;
    private String datePublished;
    private String description;
    private String name;
    private String reviewBody;
    private ReviewRating reviewRating;
    private final static long serialVersionUID = -3557395569399654514L;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Review withType(final String type) {
        this.type = type;
        return this;
    }

    /**
     * Gets the review body.
     *
     * @return the review body
     */
    public String getReviewBody() {
        return this.reviewBody;
    }

    /**
     * Sets the review body.
     *
     * @param reviewBody
     *            the new review body
     */
    public void setReviewBody(final String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public Review withAuthor(final String author) {
        this.author = author;
        return this;
    }

    public String getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(final String datePublished) {
        this.datePublished = datePublished;
    }

    public Review withDatePublished(final String datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Review withDescription(final String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Review withName(final String name) {
        this.name = name;
        return this;
    }

    public ReviewRating getReviewRating() {
        return this.reviewRating;
    }

    public void setReviewRating(final ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Review withReviewRating(final ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
        return this;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Review)) {
            return false;
        }
        final Review castOther = (Review) other;
        return new EqualsBuilder().append(this.type, castOther.type).append(this.author, castOther.author)
                .append(this.datePublished, castOther.datePublished).append(this.description, castOther.description)
                .append(this.name, castOther.name).append(this.reviewRating, castOther.reviewRating).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.type).append(this.author).append(this.datePublished)
                .append(this.description).append(this.name).append(this.reviewRating).toHashCode();
    }

}
