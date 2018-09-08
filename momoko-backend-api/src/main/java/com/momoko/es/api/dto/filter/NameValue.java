package com.momoko.es.api.dto.filter;

import java.io.Serializable;
import java.util.Objects;

public class NameValue implements Serializable, Comparable<NameValue> {

    private String name;

    private String value;

    public NameValue() {
    }

    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameValue nameValue = (NameValue) o;
        return Objects.equals(name, nameValue.name) &&
                Objects.equals(value, nameValue.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }


    @Override
    public int compareTo(NameValue o) {
        return this.getValue().compareTo(o.getValue());
    }
}
