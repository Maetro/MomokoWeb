package com.momoko.es.api.dto.request;

import java.io.Serializable;

public class EditorContactRequestDTO implements Serializable {

    private String name;
    private String email;
    private String description;
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
                ", acceptedPrivacy=" + acceptedPrivacy +
                '}';
    }
}
