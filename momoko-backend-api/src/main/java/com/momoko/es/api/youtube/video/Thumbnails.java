/**
 * Thumbnails.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.video;

/**
 * The Class Thumbnails.
 */
public class Thumbnails {

    private Default _default;
    private Medium medium;
    private High high;
    private Standard standard;
    private Maxres maxres;

    /**
     * Gets the default.
     *
     * @return the default
     */
    public Default getDefault() {
        return this._default;
    }

    /**
     * Sets the default.
     *
     * @param _default
     *            the new default
     */
    public void setDefault(final Default _default) {
        this._default = _default;
    }

    /**
     * Gets the medium.
     *
     * @return the medium
     */
    public Medium getMedium() {
        return this.medium;
    }

    /**
     * Sets the medium.
     *
     * @param medium
     *            the new medium
     */
    public void setMedium(final Medium medium) {
        this.medium = medium;
    }

    /**
     * Gets the high.
     *
     * @return the high
     */
    public High getHigh() {
        return this.high;
    }

    /**
     * Sets the high.
     *
     * @param high
     *            the new high
     */
    public void setHigh(final High high) {
        this.high = high;
    }

    /**
     * Gets the standard.
     *
     * @return the standard
     */
    public Standard getStandard() {
        return this.standard;
    }

    /**
     * Sets the standard.
     *
     * @param standard
     *            the new standard
     */
    public void setStandard(final Standard standard) {
        this.standard = standard;
    }

    /**
     * Gets the maxres.
     *
     * @return the maxres
     */
    public Maxres getMaxres() {
        return this.maxres;
    }

    /**
     * Sets the maxres.
     *
     * @param maxres
     *            the new maxres
     */
    public void setMaxres(final Maxres maxres) {
        this.maxres = maxres;
    }

}
