
package com.momoko.es.api.datosestructurados;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Review {

    private String type;
    private String author;
    private String datePublished;
    private String name;
    private String reviewBody;
    private String reviewRating;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(author).append(reviewRating).append(reviewBody).append(name).append(datePublished).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Review) == false) {
            return false;
        }
        Review rhs = ((Review) other);
        return new EqualsBuilder().append(author, rhs.author).append(reviewRating, rhs.reviewRating).append(reviewBody, rhs.reviewBody).append(name, rhs.name).append(datePublished, rhs.datePublished).append(type, rhs.type).isEquals();
    }

}
