
package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.Map;

public class CseThumbnail {

    private String width;
    private String height;
    private String src;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
