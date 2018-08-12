package com.momoko.es.jpa.model.entity.filter.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilterBookId implements Serializable {

    @Column(name = "filter_id")
    private Integer filterId;

    @Column(name = "book_id")
    private Integer bookId;

    public FilterBookId(Integer filterId, Integer bookId) {
        this.filterId = filterId;
        this.bookId = bookId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterBookId that = (FilterBookId) o;
        return Objects.equals(filterId, that.filterId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filterId, bookId);
    }
}
