/**
 * Item.java 16-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.list;

/**
 * The Class Item.
 */
public class Video {

    private String kind;

    private String etag;

    private Id id;

    private Snippet snippet;

    /**
     * No args constructor for use in serialization.
     */
    public Video() {
    }

    /**
     * Instantiates a new item.
     *
     * @param kind
     *            the kind
     * @param etag
     *            the etag
     * @param id
     *            the id
     * @param snippet
     *            the snippet
     */
    public Video(final String kind, final String etag, final Id id, final Snippet snippet) {
        super();
        this.kind = kind;
        this.etag = etag;
        this.id = id;
        this.snippet = snippet;
    }

    /**
     * Gets the kind.
     *
     * @return the kind
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * Sets the kind.
     *
     * @param kind
     *            the new kind
     */
    public void setKind(final String kind) {
        this.kind = kind;
    }

    /**
     * With kind.
     *
     * @param kind
     *            the kind
     * @return the item
     */
    public Video withKind(final String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Gets the etag.
     *
     * @return the etag
     */
    public String getEtag() {
        return this.etag;
    }

    /**
     * Sets the etag.
     *
     * @param etag
     *            the new etag
     */
    public void setEtag(final String etag) {
        this.etag = etag;
    }

    /**
     * With etag.
     *
     * @param etag
     *            the etag
     * @return the item
     */
    public Video withEtag(final String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Id getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final Id id) {
        this.id = id;
    }

    /**
     * With id.
     *
     * @param id
     *            the id
     * @return the item
     */
    public Video withId(final Id id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the snippet.
     *
     * @return the snippet
     */
    public Snippet getSnippet() {
        return this.snippet;
    }

    /**
     * Sets the snippet.
     *
     * @param snippet
     *            the new snippet
     */
    public void setSnippet(final Snippet snippet) {
        this.snippet = snippet;
    }

    /**
     * With snippet.
     *
     * @param snippet
     *            the snippet
     * @return the item
     */
    public Video withSnippet(final Snippet snippet) {
        this.snippet = snippet;
        return this;
    }

}
