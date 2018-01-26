
package com.momoko.es.api.google;

import java.util.HashMap;
import java.util.Map;

public class SearchInformation {

    private Float searchTime;
    private String formattedSearchTime;
    private String totalResults;
    private String formattedTotalResults;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Float getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Float searchTime) {
        this.searchTime = searchTime;
    }

    public String getFormattedSearchTime() {
        return formattedSearchTime;
    }

    public void setFormattedSearchTime(String formattedSearchTime) {
        this.formattedSearchTime = formattedSearchTime;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getFormattedTotalResults() {
        return formattedTotalResults;
    }

    public void setFormattedTotalResults(String formattedTotalResults) {
        this.formattedTotalResults = formattedTotalResults;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
