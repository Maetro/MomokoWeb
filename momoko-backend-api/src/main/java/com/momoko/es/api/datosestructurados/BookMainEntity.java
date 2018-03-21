
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

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public AggregateRating getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(inLanguage).append(offers).append(datePublished).append(image).append(type).append(publisher).append(author).append(name).append(isbn).append(bookFormat).append(numberOfPages).append(aggregateRating).append(review).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookMainEntity) == false) {
            return false;
        }
        BookMainEntity rhs = ((BookMainEntity) other);
        return new EqualsBuilder().append(inLanguage, rhs.inLanguage).append(offers, rhs.offers).append(datePublished, rhs.datePublished).append(image, rhs.image).append(type, rhs.type).append(publisher, rhs.publisher).append(author, rhs.author).append(name, rhs.name).append(isbn, rhs.isbn).append(bookFormat, rhs.bookFormat).append(numberOfPages, rhs.numberOfPages).append(aggregateRating, rhs.aggregateRating).append(review, rhs.review).isEquals();
    }

}
