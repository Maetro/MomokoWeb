
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Review_ implements Serializable
{

    private String type;
    private String author;
    private String datePublished;
    private String description;
    private String name;
    private ReviewRating reviewRating;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3557395569399654514L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Review_ withType(String type) {
        this.type = type;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Review_ withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Review_ withDatePublished(String datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Review_ withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Review_ withName(String name) {
        this.name = name;
        return this;
    }

    public ReviewRating getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Review_ withReviewRating(ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Review_ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(author).append(reviewRating).append(additionalProperties).append(description).append(name).append(datePublished).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Review_) == false) {
            return false;
        }
        Review_ rhs = ((Review_) other);
        return new EqualsBuilder().append(author, rhs.author).append(reviewRating, rhs.reviewRating).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(name, rhs.name).append(datePublished, rhs.datePublished).append(type, rhs.type).isEquals();
    }

}
