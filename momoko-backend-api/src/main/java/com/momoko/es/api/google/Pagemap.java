/**
 * Pagemap.java 15-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Pagemap.
 */
public class Pagemap {

    private List<CseThumbnail> cse_thumbnail = null;
    private List<Metatag> metatags = null;
    private List<CseImage> cse_image = null;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Gets the cse_thumbnail.
     *
     * @return the cse_thumbnail
     */
    public List<CseThumbnail> getCse_thumbnail() {
        return this.cse_thumbnail;
    }

    /**
     * Sets the cse_thumbnail.
     *
     * @param cse_thumbnail
     *            the new cse_thumbnail
     */
    public void setCse_thumbnail(final List<CseThumbnail> cse_thumbnail) {
        this.cse_thumbnail = cse_thumbnail;
    }

    /**
     * Gets the metatags.
     *
     * @return the metatags
     */
    public List<Metatag> getMetatags() {
        return this.metatags;
    }

    /**
     * Sets the metatags.
     *
     * @param metatags
     *            the new metatags
     */
    public void setMetatags(final List<Metatag> metatags) {
        this.metatags = metatags;
    }

    /**
     * Gets the cse_image.
     *
     * @return the cse_image
     */
    public List<CseImage> getCse_image() {
        return this.cse_image;
    }

    /**
     * Sets the cse_image.
     *
     * @param cse_image
     *            the new cse_image
     */
    public void setCse_image(final List<CseImage> cse_image) {
        this.cse_image = cse_image;
    }

    /**
     * Gets the additional properties.
     *
     * @return the additional properties
     */
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Establece additional property.
     *
     * @param name
     *            the name
     * @param value
     *            the value
     */
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

}
