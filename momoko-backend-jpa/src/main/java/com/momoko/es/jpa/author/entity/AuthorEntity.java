/**
 * AuthorEntity.java 25-oct-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.jpa.author.entity;

import com.momoko.es.jpa.common.entity.AuditableEntity;
import com.momoko.es.jpa.book.LibroEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorEntity extends AuditableEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer authorId;

    /** The name. */
    private String name;

    @Column(unique=true)
    private String authorUrl;

    private Integer birhtYear;

    private String deathYear;

    private String birthCountry;

    private String twitter;

    private String facebook;

    private String instagram;

    private String youtube;

    private String webpage;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String avatar;

    private String authorHeaderImage;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private Set<LibroEntity> authorBooks;

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Set<LibroEntity> getAuthorBooks() {
        return this.authorBooks;
    }

    public void setAuthorBooks(final Set<LibroEntity> authorBooks) {
        this.authorBooks = authorBooks;
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
        if (!(other instanceof AuthorEntity)) {
            return false;
        }
        final AuthorEntity castOther = (AuthorEntity) other;
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
