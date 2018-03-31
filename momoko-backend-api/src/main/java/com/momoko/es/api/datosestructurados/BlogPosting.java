/**
 * BlogPosting.java 25-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.datosestructurados;

/**
 * The Class BlogPosting.
 */
public class BlogPosting {

    private String context;
    private String type;
    private String headline;
    private String alternativeHeadline;
    private String image;
    private String author;
    private String award;
    private String editor;
    private String genre;
    private String keywords;
    private String wordcount;
    private Publisher publisher;
    private String url;
    private String datePublished;
    private String dateCreated;
    private String dateModified;
    private String description;
    private String articleBody;
    private String mainEntityOfPage;

    public String getContext() {
        return this.context;
    }

    public void setContext(final String context) {
        this.context = context;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(final String headline) {
        this.headline = headline;
    }

    public String getAlternativeHeadline() {
        return this.alternativeHeadline;
    }

    public void setAlternativeHeadline(final String alternativeHeadline) {
        this.alternativeHeadline = alternativeHeadline;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getAward() {
        return this.award;
    }

    public void setAward(final String award) {
        this.award = award;
    }

    public String getEditor() {
        return this.editor;
    }

    public void setEditor(final String editor) {
        this.editor = editor;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    public String getWordcount() {
        return this.wordcount;
    }

    public void setWordcount(final String wordcount) {
        this.wordcount = wordcount;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(final String datePublished) {
        this.datePublished = datePublished;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(final String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(final String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getArticleBody() {
        return this.articleBody;
    }

    public void setArticleBody(final String articleBody) {
        this.articleBody = articleBody;
    }

    public String getMainEntityOfPage() {
        return this.mainEntityOfPage;
    }

    public void setMainEntityOfPage(final String esEntidadPrincipal) {
        this.mainEntityOfPage = esEntidadPrincipal;

    }

}