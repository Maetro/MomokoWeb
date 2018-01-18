
package com.momoko.es.api.datosestructurados;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Offers implements Serializable
{

    private String type;
    private String availability;
    private String price;
    private String priceCurrency;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1715831600288166774L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Offers withType(String type) {
        this.type = type;
        return this;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Offers withAvailability(String availability) {
        this.availability = availability;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Offers withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Offers withPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Offers withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(additionalProperties).append(priceCurrency).append(type).append(availability).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Offers) == false) {
            return false;
        }
        Offers rhs = ((Offers) other);
        return new EqualsBuilder().append(price, rhs.price).append(additionalProperties, rhs.additionalProperties).append(priceCurrency, rhs.priceCurrency).append(type, rhs.type).append(availability, rhs.availability).isEquals();
    }

}
