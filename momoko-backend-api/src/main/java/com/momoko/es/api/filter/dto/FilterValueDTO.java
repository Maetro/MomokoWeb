package com.momoko.es.api.filter.dto;

import java.io.Serializable;
import java.util.Objects;

public class FilterValueDTO implements Serializable, Comparable<FilterValueDTO> {

    private Integer filterValueId;

    private String name;

    private String value;

    private Integer order;

    public FilterValueDTO() {
    }

    public FilterValueDTO(Integer filterValueId, String name, String value, Integer order) {
        this.filterValueId = filterValueId;
        this.name = name;
        this.value = value;
        this.order = order;
    }

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterValueDTO filterValueDTO = (FilterValueDTO) o;
        return Objects.equals(name, filterValueDTO.name) &&
                Objects.equals(value, filterValueDTO.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }


    @Override
    public int compareTo(FilterValueDTO o) {
        if (this.getOrder()!= null){
            return this.getOrder().compareTo(o.getOrder());
        }
        return this.getName().compareTo(o.getName());
    }
}
