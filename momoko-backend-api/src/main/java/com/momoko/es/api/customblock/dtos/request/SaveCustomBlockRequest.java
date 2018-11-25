package com.momoko.es.api.customblock.dtos.request;

import java.io.Serializable;
import java.util.List;

public class SaveCustomBlockRequest implements Serializable {

    private Integer id;
    private boolean active;
    private boolean isCode;
    private String customBlockMainImage;
    private String content;
    private String link;
    private List<String> links;
    private String type;
    private String template;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCode() {
        return isCode;
    }

    public void setCode(boolean code) {
        isCode = code;
    }

    public String getCustomBlockMainImage() {
        return customBlockMainImage;
    }

    public void setCustomBlockMainImage(String customBlockMainImage) {
        this.customBlockMainImage = customBlockMainImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
