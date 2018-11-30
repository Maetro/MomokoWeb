
package com.momoko.es.api.datosestructurados;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Offers {

    private String type;
    private String availability;
    private String price;
    private String priceCurrency;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(priceCurrency).append(type).append(availability).toHashCode();
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
        return new EqualsBuilder().append(price, rhs.price).append(priceCurrency, rhs.priceCurrency).append(type, rhs.type).append(availability, rhs.availability).isEquals();
    }

}
