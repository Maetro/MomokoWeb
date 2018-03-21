/**
 * BookMainEntity.java 21-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.datosestructurados;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BookMainEntity {

    private String type;
    private String author;
    private String bookFormat;
    private String datePublished;
    private String image;
    private String inLanguage;
    private String isbn;
    private String name;
    private String numberOfPages;
    private Offers offers;
    private String publisher;
    private AggregateRating aggregateRating;
    private List<Review> review = null;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(final String datePublished) {
        this.datePublished = datePublished;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getInLanguage() {
        return this.inLanguage;
    }

    public void setInLanguage(final String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(final String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Offers getOffers() {
        return this.offers;
    }

    public void setOffers(final Offers offers) {
        this.offers = offers;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public AggregateRating getAggregateRating() {
        return this.aggregateRating;
    }

    public void setAggregateRating(final AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public List<Review> getReview() {
        return this.review;
    }

    public void setReview(final List<Review> review) {
        this.review = review;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.inLanguage).append(this.offers).append(this.datePublished)
                .append(this.image).append(this.type).append(this.publisher).append(this.author).append(this.name)
                .append(this.isbn).append(this.bookFormat).append(this.numberOfPages).append(this.aggregateRating)
                .append(this.review).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookMainEntity) == false) {
            return false;
        }
        final BookMainEntity rhs = ((BookMainEntity) other);
        return new EqualsBuilder().append(this.inLanguage, rhs.inLanguage).append(this.offers, rhs.offers)
                .append(this.datePublished, rhs.datePublished).append(this.image, rhs.image).append(this.type, rhs.type)
                .append(this.publisher, rhs.publisher).append(this.author, rhs.author).append(this.name, rhs.name)
                .append(this.isbn, rhs.isbn).append(this.bookFormat, rhs.bookFormat)
                .append(this.numberOfPages, rhs.numberOfPages).append(this.aggregateRating, rhs.aggregateRating)
                .append(this.review, rhs.review).isEquals();
    }

}
