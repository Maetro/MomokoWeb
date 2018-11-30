/**
 * AuthorDTO.java 08-jul-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.author.dto;


import com.momoko.es.api.dto.LibroDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * The Class AuthorDTO.
 */
public class AuthorDTO implements Serializable {

    private static final long serialVersionUID = -6346918750164335293L;

    private Integer authorId;

    private String name;

    private String authorUrl;

    private Integer birhtYear;

    private String deathYear;

    private String birthCountry;

    private String twitter;

    private String facebook;

    private String instagram;

    private String youtube;

    private String webpage;

    private String description;

    private String avatar;

    private String authorHeaderImage;

    private Set<LibroDTO> authorBooks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Integer getBirhtYear() {
        return birhtYear;
    }

    public void setBirhtYear(Integer birhtYear) {
        this.birhtYear = birhtYear;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(String deathYear) {
        this.deathYear = deathYear;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAuthorHeaderImage() {
        return authorHeaderImage;
    }

    public void setAuthorHeaderImage(String authorHeaderImage) {
        this.authorHeaderImage = authorHeaderImage;
    }

    public Set<LibroDTO> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(Set<LibroDTO> authorBooks) {
        this.authorBooks = authorBooks;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof AuthorDTO)) {
            return false;
        }
        final AuthorDTO castOther = (AuthorDTO) other;
        return new EqualsBuilder().append(this.authorId, castOther.authorId).append(this.name, castOther.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.authorId).append(this.name).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("authorId", this.authorId).append("name", this.name).toString();
    }

}
