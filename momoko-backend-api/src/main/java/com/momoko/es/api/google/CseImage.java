
package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.Map;

public class CseImage {

    private String src;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
