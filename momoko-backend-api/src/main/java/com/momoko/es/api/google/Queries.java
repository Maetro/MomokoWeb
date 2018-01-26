
package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queries {

    private List<Request> request = null;
    private List<NextPage> nextPage = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public List<NextPage> getNextPage() {
        return nextPage;
    }

    public void setNextPage(List<NextPage> nextPage) {
        this.nextPage = nextPage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
