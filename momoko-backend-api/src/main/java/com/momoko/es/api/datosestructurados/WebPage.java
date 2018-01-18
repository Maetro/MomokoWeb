
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WebPage implements Serializable
{

    private String context;
    private String type;
    private String breadcrumb;
    private MainEntity mainEntity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4832692798105323726L;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public WebPage withContext(String context) {
        this.context = context;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WebPage withType(String type) {
        this.type = type;
        return this;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public WebPage withBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
        return this;
    }

    public MainEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    public WebPage withMainEntity(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public WebPage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mainEntity).append(additionalProperties).append(context).append(type).append(breadcrumb).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WebPage) == false) {
            return false;
        }
        WebPage rhs = ((WebPage) other);
        return new EqualsBuilder().append(mainEntity, rhs.mainEntity).append(additionalProperties, rhs.additionalProperties).append(context, rhs.context).append(type, rhs.type).append(breadcrumb, rhs.breadcrumb).isEquals();
    }

}
