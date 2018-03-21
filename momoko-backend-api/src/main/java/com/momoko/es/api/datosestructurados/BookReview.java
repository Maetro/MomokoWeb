
package com.momoko.es.api.datosestructurados;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BookReview {

    private String context;
    private String type;
    private String breadcrumb;
    private BookMainEntity mainEntity;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public BookMainEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(BookMainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mainEntity).append(context).append(type).append(breadcrumb).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookReview) == false) {
            return false;
        }
        BookReview rhs = ((BookReview) other);
        return new EqualsBuilder().append(mainEntity, rhs.mainEntity).append(context, rhs.context).append(type, rhs.type).append(breadcrumb, rhs.breadcrumb).isEquals();
    }

}
