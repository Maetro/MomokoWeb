package com.momoko.es.jpa.model.entity.filter;

import javax.persistence.*;

@Entity
@Table(name = "filter_value")
public class FilterValueEntity {

    @Id
    @GeneratedValue
    private Integer filterValueId;

    private String name;

    private String value;

    private String valueType;

    private Integer filterOrder;

    /** The editorial id. */
    @ManyToOne
    @JoinColumn(name = "filter_id")
    private FilterEntity filter;

    public Integer getFilterValueId() {
        return filterValueId;
    }

    public void setFilterValueId(Integer filterValueId) {
        this.filterValueId = filterValueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Integer getFilterOrder() {
        return filterOrder;
    }

    public void setFilterOrder(Integer filterOrder) {
        this.filterOrder = filterOrder;
    }

    public FilterEntity getFilter() {
        return filter;
    }

    public void setFilter(FilterEntity filter) {
        this.filter = filter;
    }
}
