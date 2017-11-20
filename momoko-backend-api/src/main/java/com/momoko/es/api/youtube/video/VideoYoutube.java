/**
 * VideoYoutube.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.video;

import java.util.List;

/**
 * The Class VideoYoutube.
 */
public class VideoYoutube {

    private String kind;
    private String etag;
    private PageInfo pageInfo;
    private List<Item> items = null;

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
     * Gets the page info.
     *
     * @return the page info
     */
    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    /**
     * Sets the page info.
     *
     * @param pageInfo
     *            the new page info
     */
    public void setPageInfo(final PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Sets the items.
     *
     * @param items
     *            the new items
     */
    public void setItems(final List<Item> items) {
        this.items = items;
    }

}
