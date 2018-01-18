
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ReviewRating implements Serializable
{

    private String type;
    private String bestRating;
    private String ratingValue;
    private String worstRating;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2930609240651872255L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReviewRating withType(String type) {
        this.type = type;
        return this;
    }

    public String getBestRating() {
        return bestRating;
    }

    public void setBestRating(String bestRating) {
        this.bestRating = bestRating;
    }

    public ReviewRating withBestRating(String bestRating) {
        this.bestRating = bestRating;
        return this;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public ReviewRating withRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
        return this;
    }

    public String getWorstRating() {
        return worstRating;
    }

    public void setWorstRating(String worstRating) {
        this.worstRating = worstRating;
    }

    public ReviewRating withWorstRating(String worstRating) {
        this.worstRating = worstRating;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ReviewRating withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bestRating).append(ratingValue).append(additionalProperties).append(type).append(worstRating).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReviewRating) == false) {
            return false;
        }
        ReviewRating rhs = ((ReviewRating) other);
        return new EqualsBuilder().append(bestRating, rhs.bestRating).append(ratingValue, rhs.ratingValue).append(additionalProperties, rhs.additionalProperties).append(type, rhs.type).append(worstRating, rhs.worstRating).isEquals();
    }

}
