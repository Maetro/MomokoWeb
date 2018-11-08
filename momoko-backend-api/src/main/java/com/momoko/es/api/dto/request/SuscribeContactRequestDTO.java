package com.momoko.es.api.dto.request;

import java.io.Serializable;

public class SuscribeContactRequestDTO implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SuscribeContactRequestDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
