package com.momoko.es.jpa.model.entity.filter.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilterBookValueId implements Serializable {

    @Column(name = "filter_id")
    private Integer filterId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "value_id")
    private Integer valueId;

    public FilterBookValueId(){
    }

    public FilterBookValueId(Integer filterId, Integer bookId, Integer valueId) {
        this.filterId = filterId;
        this.bookId = bookId;
        this.valueId = valueId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterBookValueId that = (FilterBookValueId) o;
        return Objects.equals(filterId, that.filterId) &&
                Objects.equals(bookId, that.bookId) &&
                Objects.equals(valueId, that.valueId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filterId, bookId, valueId);
    }
}
