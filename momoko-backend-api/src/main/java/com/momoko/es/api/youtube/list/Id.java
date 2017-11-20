/**
 * Id.java 16-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.list;

/**
 * The Class Id.
 */
public class Id {

    private String kind;

    private String videoId;

    /**
     * No args constructor for use in serialization.
     */
    public Id() {
    }

    /**
     * Instantiates a new id.
     *
     * @param kind
     *            the kind
     * @param videoId
     *            the video id
     */
    public Id(final String kind, final String videoId) {
        super();
        this.kind = kind;
        this.videoId = videoId;
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
     * @return the id
     */
    public Id withKind(final String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Gets the video id.
     *
     * @return the video id
     */
    public String getVideoId() {
        return this.videoId;
    }

    /**
     * Sets the video id.
     *
     * @param videoId
     *            the new video id
     */
    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }

    /**
     * With video id.
     *
     * @param videoId
     *            the video id
     * @return the id
     */
    public Id withVideoId(final String videoId) {
        this.videoId = videoId;
        return this;
    }

}
