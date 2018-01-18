
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class MainEntity implements Serializable
{

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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8067715399156175678L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MainEntity withType(String type) {
        this.type = type;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public MainEntity withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public MainEntity withBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
        return this;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public MainEntity withDatePublished(String datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MainEntity withImage(String image) {
        this.image = image;
        return this;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public MainEntity withInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public MainEntity withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainEntity withName(String name) {
        this.name = name;
        return this;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public MainEntity withNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }

    public MainEntity withOffers(Offers offers) {
        this.offers = offers;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public MainEntity withPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public AggregateRating getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public MainEntity withAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
        return this;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public MainEntity withReview(List<Review> review) {
        this.review = review;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MainEntity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(inLanguage).append(offers).append(datePublished).append(image).append(type).append(publisher).append(author).append(additionalProperties).append(name).append(isbn).append(bookFormat).append(numberOfPages).append(aggregateRating).append(review).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MainEntity) == false) {
            return false;
        }
        MainEntity rhs = ((MainEntity) other);
        return new EqualsBuilder().append(inLanguage, rhs.inLanguage).append(offers, rhs.offers).append(datePublished, rhs.datePublished).append(image, rhs.image).append(type, rhs.type).append(publisher, rhs.publisher).append(author, rhs.author).append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(isbn, rhs.isbn).append(bookFormat, rhs.bookFormat).append(numberOfPages, rhs.numberOfPages).append(aggregateRating, rhs.aggregateRating).append(review, rhs.review).isEquals();
    }

}
