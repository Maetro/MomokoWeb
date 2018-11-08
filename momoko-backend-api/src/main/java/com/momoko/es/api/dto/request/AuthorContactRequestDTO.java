package com.momoko.es.api.dto.request;

import java.io.Serializable;

public class AuthorContactRequestDTO implements Serializable {

    private String name;
    private String email;
    private String description;
    private String title;
    private String genre;
    private String isPublished;
    private boolean acceptedPrivacy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublished() {
        return isPublished;
    }

    public void setPublished(String published) {
        isPublished = published;
    }

    public boolean isAcceptedPrivacy() {
        return acceptedPrivacy;
    }

    public void setAcceptedPrivacy(boolean acceptedPrivacy) {
        this.acceptedPrivacy = acceptedPrivacy;
    }


    @Override
    public String toString() {
        return "AuthorContactRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", isPublished=" + isPublished +
                ", acceptedPrivacy=" + acceptedPrivacy +
                '}';
    }
}
