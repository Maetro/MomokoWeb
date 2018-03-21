/**
 * ReviewRating.java 21-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.datosestructurados;

public class ReviewRating {

    private String type;
    private String bestRating;
    private String ratingValue;
    private String worstRating;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getBestRating() {
        return this.bestRating;
    }

    public void setBestRating(final String bestRating) {
        this.bestRating = bestRating;
    }

    public String getRatingValue() {
        return this.ratingValue;
    }

    public void setRatingValue(final String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getWorstRating() {
        return this.worstRating;
    }

    public void setWorstRating(final String worstRating) {
        this.worstRating = worstRating;
    }

}