package com.momoko.es.jpa.model.facade.amp;

import java.io.Serializable;
import java.util.List;

public class AmpCommentList implements Serializable {

    List<AmpComment> items;

    public List<AmpComment> getItems() {
        return items;
    }

    public void setItems(List<AmpComment> items) {
        this.items = items;
    }
}
