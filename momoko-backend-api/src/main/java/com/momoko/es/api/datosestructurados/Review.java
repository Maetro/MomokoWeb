
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Review implements Serializable
{

    private String context;
    private String type;
    private AggregateRating aggregateRating;
    private String description;
    private String name;
    private String image;
    private Offers offers;
    private List<Review_> review = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3244691292128334706L;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Review withContext(String context) {
        this.context = context;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Review withType(String type) {
        this.type = type;
        return this;
    }

    public AggregateRating getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public Review withAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Review withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Review withName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Review withImage(String image) {
        this.image = image;
        return this;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }

    public Review withOffers(Offers offers) {
        this.offers = offers;
        return this;
    }

    public List<Review_> getReview() {
        return review;
    }

    public void setReview(List<Review_> review) {
        this.review = review;
    }

    public Review withReview(List<Review_> review) {
        this.review = review;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Review withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(description).append(offers).append(name).append(context).append(image).append(aggregateRating).append(type).append(review).toHashCode();
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
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(offers, rhs.offers).append(name, rhs.name).append(context, rhs.context).append(image, rhs.image).append(aggregateRating, rhs.aggregateRating).append(type, rhs.type).append(review, rhs.review).isEquals();
    }

}
