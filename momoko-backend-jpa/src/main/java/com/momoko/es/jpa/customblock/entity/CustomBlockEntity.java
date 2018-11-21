package com.momoko.es.jpa.customblock.entity;

import com.momoko.es.api.customblock.dtos.enums.CustomBlockTemplateEnum;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.jpa.common.entity.AuditableEntity;

import javax.persistence.*;
import java.util.List;

public class CustomBlockEntity extends AuditableEntity {

    private @Id @GeneratedValue Integer Id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "isCode")
    private boolean isCode;

    @Column(name = "custom_block_main_image")
    private String customBlockMainImage;

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    @Column(name = "links")
    private String links;

    @Enumerated(EnumType.STRING)
    @Column(name = "custom_block_type", columnDefinition = "VARCHAR(15)")
    private CustomBlockTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "custom_block_template", columnDefinition = "VARCHAR(31)")
    private CustomBlockTemplateEnum template;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
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
}
