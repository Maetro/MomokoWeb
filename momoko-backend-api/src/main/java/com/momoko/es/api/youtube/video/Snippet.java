/**
 * Snippet.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.video;

import java.util.List;

/**
 * The Class Snippet.
 */
public class Snippet {

    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;
    private List<String> tags = null;
    private String categoryId;
    private String liveBroadcastContent;
    private String defaultLanguage;
    private Localized localized;
    private String defaultAudioLanguage;

    /**
     * Gets the published at.
     *
     * @return the published at
     */
    public String getPublishedAt() {
        return this.publishedAt;
    }

    /**
     * Sets the published at.
     *
     * @param publishedAt
     *            the new published at
     */
    public void setPublishedAt(final String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the channel id.
     *
     * @return the channel id
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * Sets the channel id.
     *
     * @param channelId
     *            the new channel id
     */
    public void setChannelId(final String channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title.
     *
     * @param title
     *            the new title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the thumbnails.
     *
     * @return the thumbnails
     */
    public Thumbnails getThumbnails() {
        return this.thumbnails;
    }

    /**
     * Sets the thumbnails.
     *
     * @param thumbnails
     *            the new thumbnails
     */
    public void setThumbnails(final Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * Gets the channel title.
     *
     * @return the channel title
     */
    public String getChannelTitle() {
        return this.channelTitle;
    }

    /**
     * Sets the channel title.
     *
     * @param channelTitle
     *            the new channel title
     */
    public void setChannelTitle(final String channelTitle) {
        this.channelTitle = channelTitle;
    }

    /**
     * Gets the tags.
     *
     * @return the tags
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Sets the tags.
     *
     * @param tags
     *            the new tags
     */
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets the category id.
     *
     * @return the category id
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * Sets the category id.
     *
     * @param categoryId
     *            the new category id
     */
    public void setCategoryId(final String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the live broadcast content.
     *
     * @return the live broadcast content
     */
    public String getLiveBroadcastContent() {
        return this.liveBroadcastContent;
    }

    /**
     * Sets the live broadcast content.
     *
     * @param liveBroadcastContent
     *            the new live broadcast content
     */
    public void setLiveBroadcastContent(final String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    /**
     * Gets the default language.
     *
     * @return the default language
     */
    public String getDefaultLanguage() {
        return this.defaultLanguage;
    }

    /**
     * Sets the default language.
     *
     * @param defaultLanguage
     *            the new default language
     */
    public void setDefaultLanguage(final String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * Gets the localized.
     *
     * @return the localized
     */
    public Localized getLocalized() {
        return this.localized;
    }

    /**
     * Sets the localized.
     *
     * @param localized
     *            the new localized
     */
    public void setLocalized(final Localized localized) {
        this.localized = localized;
    }

    /**
     * Gets the default audio language.
     *
     * @return the default audio language
     */
    public String getDefaultAudioLanguage() {
        return this.defaultAudioLanguage;
    }

    /**
     * Sets the default audio language.
     *
     * @param defaultAudioLanguage
     *            the new default audio language
     */
    public void setDefaultAudioLanguage(final String defaultAudioLanguage) {
        this.defaultAudioLanguage = defaultAudioLanguage;
    }

}
