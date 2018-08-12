package com.momoko.es.jpa.model.entity.filter;

import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.key.FilterBookId;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "FilterBook")
@Table(name = "filter_book")
public class FilterBook {

    @EmbeddedId
    private FilterBookId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filterId")
    private FilterEntity filter;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private LibroEntity book;

    private String value;

    public FilterBook() {
    }

    public FilterBook(FilterEntity filter, LibroEntity book) {
        this.filter = filter;
        this.book = book;
        this.id = new FilterBookId(filter.getFilterId(), book.getLibroId());
    }

    public FilterBookId getId() {
        return id;
    }

    public void setId(FilterBookId id) {
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
