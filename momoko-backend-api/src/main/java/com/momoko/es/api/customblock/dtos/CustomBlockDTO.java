package com.momoko.es.api.customblock.dtos;

import com.momoko.es.api.customblock.dtos.enums.CustomBlockTemplateEnum;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;

import java.io.Serializable;
import java.util.List;

public class CustomBlockDTO implements Serializable {

    private Integer id;
    private boolean active;
    private boolean isCode;
    private String customBlockMainImage;
    private String content;
    private String link;
    private List<LibroEntradaSimpleDTO> links;
    private CustomBlockTypeEnum type;
    private CustomBlockTemplateEnum template;

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

    public List<LibroEntradaSimpleDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LibroEntradaSimpleDTO> links) {
        this.links = links;
    }

    public CustomBlockTypeEnum getType() {
        return type;
    }

    public void setType(CustomBlockTypeEnum type) {
        this.type = type;
    }

    public CustomBlockTemplateEnum getTemplate() {
        return template;
    }

    public void setTemplate(CustomBlockTemplateEnum template) {
        this.template = template;
    }

    public boolean isCode() {
        return isCode;
    }

    public void setCode(boolean code) {
        isCode = code;
    }
}
