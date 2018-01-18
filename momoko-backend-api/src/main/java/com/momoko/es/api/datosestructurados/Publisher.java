
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Publisher implements Serializable
{

    private String type;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6858517539237423972L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Publisher withType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher withName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Publisher withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Publisher))
            return false;
        Publisher castOther = (Publisher) other;
        return new EqualsBuilder().append(type, castOther.type).append(name, castOther.name)
                .append(additionalProperties, castOther.additionalProperties).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(name).append(additionalProperties).toHashCode();
    }


}
