package com.momoko.es.jpa.filter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "custom_enum")
public class CustomEnumEntity{

    @Id
    @GeneratedValue
    private Integer customFilterId;

    private String possibleValues;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private FilterEntity filter;

    public CustomEnumEntity() {
    }

    public Integer getCustomFilterId() {
        return customFilterId;
    }

    public void setCustomFilterId(Integer customFilterId) {
        this.customFilterId = customFilterId;
    }

    public String getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(String possibleValues) {
        this.possibleValues = possibleValues;
    }

    public FilterEntity getFilter() {
        return filter;
    }

    public void setFilter(FilterEntity filter) {
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomEnumEntity that = (CustomEnumEntity) o;
        return Objects.equals(customFilterId, that.customFilterId) &&
                Objects.equals(possibleValues, that.possibleValues) &&
                Objects.equals(filter, that.filter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customFilterId, possibleValues, filter);
    }
}
