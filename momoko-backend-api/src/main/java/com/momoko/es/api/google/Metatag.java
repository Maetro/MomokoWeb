
package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.Map;

public class Metatag {

    private String viewport;
    private String momoko;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getViewport() {
        return viewport;
    }

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public String getMomoko() {
        return momoko;
    }

    public void setMomoko(String momoko) {
        this.momoko = momoko;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
