/**
 * Item.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.video;

/**
 * The Class Item.
 */
public class Item {

    private String kind;
    private String etag;
    private String id;
    private Snippet snippet;

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
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final String id) {
        this.id = id;
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

}
