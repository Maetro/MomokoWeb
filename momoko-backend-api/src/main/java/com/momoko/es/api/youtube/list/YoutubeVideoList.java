/**
 * YoutubeVideo.java 16-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.list;

import java.util.List;

/**
 * The Class YoutubeVideo.
 */
public class YoutubeVideoList {

    private String kind;

    private String etag;

    private String nextPageToken;

    private String regionCode;

    private PageInfo pageInfo;

    private List<Video> items = null;

    /**
     * No args constructor for use in serialization.
     */
    public YoutubeVideoList() {
    }

    /**
     * Instantiates a new youtube video.
     *
     * @param kind
     *            the kind
     * @param etag
     *            the etag
     * @param nextPageToken
     *            the next page token
     * @param regionCode
     *            the region code
     * @param pageInfo
     *            the page info
     * @param items
     *            the items
     */
    public YoutubeVideoList(final String kind, final String etag, final String nextPageToken, final String regionCode,
            final PageInfo pageInfo, final List<Video> items) {
        super();
        this.kind = kind;
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.regionCode = regionCode;
        this.pageInfo = pageInfo;
        this.items = items;
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
     * @return the youtube video
     */
    public YoutubeVideoList withKind(final String kind) {
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
     * @return the youtube video
     */
    public YoutubeVideoList withEtag(final String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Gets the next page token.
     *
     * @return the next page token
     */
    public String getNextPageToken() {
        return this.nextPageToken;
    }

    /**
     * Sets the next page token.
     *
     * @param nextPageToken
     *            the new next page token
     */
    public void setNextPageToken(final String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    /**
     * With next page token.
     *
     * @param nextPageToken
     *            the next page token
     * @return the youtube video
     */
    public YoutubeVideoList withNextPageToken(final String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    /**
     * Gets the region code.
     *
     * @return the region code
     */
    public String getRegionCode() {
        return this.regionCode;
    }

    /**
     * Sets the region code.
     *
     * @param regionCode
     *            the new region code
     */
    public void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * With region code.
     *
     * @param regionCode
     *            the region code
     * @return the youtube video
     */
    public YoutubeVideoList withRegionCode(final String regionCode) {
        this.regionCode = regionCode;
        return this;
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
     * With page info.
     *
     * @param pageInfo
     *            the page info
     * @return the youtube video
     */
    public YoutubeVideoList withPageInfo(final PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<Video> getItems() {
        return this.items;
    }

    /**
     * Sets the items.
     *
     * @param items
     *            the new items
     */
    public void setItems(final List<Video> items) {
        this.items = items;
    }

    /**
     * With items.
     *
     * @param items
     *            the items
     * @return the youtube video
     */
    public YoutubeVideoList withItems(final List<Video> items) {
        this.items = items;
        return this;
    }

}
