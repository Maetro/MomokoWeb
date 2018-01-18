
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class AggregateRating implements Serializable
{

    private String type;
    private String ratingValue;
    private String reviewCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 604551158186102183L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AggregateRating withType(String type) {
        this.type = type;
        return this;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public AggregateRating withRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
        return this;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public AggregateRating withReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AggregateRating withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ratingValue).append(additionalProperties).append(reviewCount).append(type).toHashCode();
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
        return new EqualsBuilder().append(ratingValue, rhs.ratingValue).append(additionalProperties, rhs.additionalProperties).append(reviewCount, rhs.reviewCount).append(type, rhs.type).isEquals();
    }

}
