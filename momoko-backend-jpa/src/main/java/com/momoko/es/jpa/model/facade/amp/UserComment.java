package com.momoko.es.jpa.model.facade.amp;

import java.io.Serializable;

public class UserComment implements Serializable{

    private String name;
    private String comment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
