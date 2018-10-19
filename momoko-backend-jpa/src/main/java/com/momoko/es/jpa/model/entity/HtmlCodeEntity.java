package com.momoko.es.jpa.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "html_codes", indexes = { @Index(name = "urlCode", columnList = "urlCode", unique = true) })
public class HtmlCodeEntity {

    /** The editorial id. */
    private @Id
    @GeneratedValue
    Integer htmlCodeId;

    private String content;

    private String urlCode;

    public Integer getHtmlCodeId() {
        return htmlCodeId;
    }

    public void setHtmlCodeId(Integer htmlCodeId) {
        this.htmlCodeId = htmlCodeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }
}
