/**
 * Example.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.list;

import java.util.List;

public class Example {

    private String kind;
    private String etag;
    private PageInfo pageInfo;
    private List<YoutubeVideoList> items = null;

    public String getKind() {
        return this.kind;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(final String etag) {
        this.etag = etag;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(final PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<YoutubeVideoList> getItems() {
        return this.items;
    }

    public void setItems(final List<YoutubeVideoList> items) {
        this.items = items;
    }

}
