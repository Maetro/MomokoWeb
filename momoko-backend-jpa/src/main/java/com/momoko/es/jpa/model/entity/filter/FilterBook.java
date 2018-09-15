package com.momoko.es.jpa.model.entity.filter;

import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.key.FilterBookValueId;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "FilterBook")
@Table(name = "filter_book")
public class FilterBook {

    @EmbeddedId
    private FilterBookValueId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filterId")
    private FilterEntity filter;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private LibroEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("valueId")
    private FilterValueEntity value;

    public FilterBook() {
    }

    public FilterBook(FilterEntity filter, LibroEntity book, FilterValueEntity filterBookValue) {
        this.filter = filter;
        this.book = book;
        this.id = new FilterBookValueId(filter.getFilterId(), book.getLibroId(),
                filterBookValue.getFilterValueId());
    }

    public FilterBookValueId getId() {
        return id;
    }

    public void setId(FilterBookValueId id) {
        this.id = id;
    }

    public FilterEntity getFilter() {
        return filter;
    }

    public void setFilter(FilterEntity filter) {
        this.filter = filter;
    }

    public LibroEntity getBook() {
        return book;
    }

    public void setBook(LibroEntity book) {
        this.book = book;
    }

    public FilterValueEntity getValue() {
        return value;
    }

    public void setValue(FilterValueEntity value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterBook that = (FilterBook) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(filter, that.filter) &&
                Objects.equals(book, that.book) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, filter, book, value);
    }
}
