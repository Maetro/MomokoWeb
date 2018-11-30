
package com.momoko.es.api.datosestructurados;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book implements Serializable
{

    private String context;
    private String type;
    private String accessibilityAPI;
    private List<String> accessibilityControl = null;
    private List<String> accessibilityFeature = null;
    private List<String> accessibilityHazard = null;
    private AggregateRating aggregateRating;
    private String bookFormat;
    private CopyrightHolder copyrightHolder;
    private String copyrightYear;
    private String description;
    private String genre;
    private String inLanguage;
    private String isFamilyFriendly;
    private String isbn;
    private String name;
    private String numberOfPages;
    private Publisher publisher;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 153370619241815279L;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Book withContext(String context) {
        this.context = context;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Book withType(String type) {
        this.type = type;
        return this;
    }

    public String getAccessibilityAPI() {
        return accessibilityAPI;
    }

    public void setAccessibilityAPI(String accessibilityAPI) {
        this.accessibilityAPI = accessibilityAPI;
    }

    public Book withAccessibilityAPI(String accessibilityAPI) {
        this.accessibilityAPI = accessibilityAPI;
        return this;
    }

    public List<String> getAccessibilityControl() {
        return accessibilityControl;
    }

    public void setAccessibilityControl(List<String> accessibilityControl) {
        this.accessibilityControl = accessibilityControl;
    }

    public Book withAccessibilityControl(List<String> accessibilityControl) {
        this.accessibilityControl = accessibilityControl;
        return this;
    }

    public List<String> getAccessibilityFeature() {
        return accessibilityFeature;
    }

    public void setAccessibilityFeature(List<String> accessibilityFeature) {
        this.accessibilityFeature = accessibilityFeature;
    }

    public Book withAccessibilityFeature(List<String> accessibilityFeature) {
        this.accessibilityFeature = accessibilityFeature;
        return this;
    }

    public List<String> getAccessibilityHazard() {
        return accessibilityHazard;
    }

    public void setAccessibilityHazard(List<String> accessibilityHazard) {
        this.accessibilityHazard = accessibilityHazard;
    }

    public Book withAccessibilityHazard(List<String> accessibilityHazard) {
        this.accessibilityHazard = accessibilityHazard;
        return this;
    }

    public AggregateRating getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public Book withAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
        return this;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public Book withBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
        return this;
    }

    public CopyrightHolder getCopyrightHolder() {
        return copyrightHolder;
    }

    public void setCopyrightHolder(CopyrightHolder copyrightHolder) {
        this.copyrightHolder = copyrightHolder;
    }

    public Book withCopyrightHolder(CopyrightHolder copyrightHolder) {
        this.copyrightHolder = copyrightHolder;
        return this;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public Book withCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Book withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public Book withInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
        return this;
    }

    public String getIsFamilyFriendly() {
        return isFamilyFriendly;
    }

    public void setIsFamilyFriendly(String isFamilyFriendly) {
        this.isFamilyFriendly = isFamilyFriendly;
    }

    public Book withIsFamilyFriendly(String isFamilyFriendly) {
        this.isFamilyFriendly = isFamilyFriendly;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book withName(String name) {
        this.name = name;
        return this;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Book withNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Book withPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Book withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Book))
            return false;
        Book castOther = (Book) other;
        return new EqualsBuilder().append(context, castOther.context).append(type, castOther.type)
                .append(accessibilityAPI, castOther.accessibilityAPI)
                .append(accessibilityControl, castOther.accessibilityControl)
                .append(accessibilityFeature, castOther.accessibilityFeature)
                .append(accessibilityHazard, castOther.accessibilityHazard)
                .append(aggregateRating, castOther.aggregateRating).append(bookFormat, castOther.bookFormat)
                .append(copyrightHolder, castOther.copyrightHolder).append(copyrightYear, castOther.copyrightYear)
                .append(description, castOther.description).append(genre, castOther.genre)
                .append(inLanguage, castOther.inLanguage).append(isFamilyFriendly, castOther.isFamilyFriendly)
                .append(isbn, castOther.isbn).append(name, castOther.name)
                .append(numberOfPages, castOther.numberOfPages).append(publisher, castOther.publisher)
                .append(additionalProperties, castOther.additionalProperties).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(context).append(type).append(accessibilityAPI).append(accessibilityControl)
                .append(accessibilityFeature).append(accessibilityHazard).append(aggregateRating).append(bookFormat)
                .append(copyrightHolder).append(copyrightYear).append(description).append(genre).append(inLanguage)
                .append(isFamilyFriendly).append(isbn).append(name).append(numberOfPages).append(publisher)
                .append(additionalProperties).toHashCode();
    }



}
