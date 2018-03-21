
package com.momoko.es.api.datosestructurados;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class AggregateRating {

    private String type;
    private String ratingValue;
    private String reviewCount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ratingValue).append(reviewCount).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AggregateRating) == false) {
            return false;
        }
        AggregateRating rhs = ((AggregateRating) other);
        return new EqualsBuilder().append(ratingValue, rhs.ratingValue).append(reviewCount, rhs.reviewCount).append(type, rhs.type).isEquals();
    }

}
