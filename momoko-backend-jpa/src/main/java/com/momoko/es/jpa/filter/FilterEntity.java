package com.momoko.es.jpa.filter;

import com.momoko.es.api.filter.enums.FilterRuleType;
import com.momoko.es.jpa.genre.entity.GenreEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "filter")
public class FilterEntity {

    @Id
    @GeneratedValue
    private Integer filterId;

    private String nameFilter;

    @Column(unique = true)
    @NotNull
    private String urlFilter;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "filter_genre",
            joinColumns = {@JoinColumn(name = "filter_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<GenreEntity> applicableGenres;

    @Enumerated(EnumType.STRING)
    private FilterRuleType type;

    @OneToMany(
            mappedBy = "filter",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FilterBook> filterBooks = new ArrayList<>();

    /**
     * The libros autor.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "filter")
    private Set<FilterValueEntity> filterValues;

    private String referencedProperty;

    private boolean isBasic;

    private boolean isInclusive;

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getUrlFilter() {
        return urlFilter;
    }

    public void setUrlFilter(String urlFilter) {
        this.urlFilter = urlFilter;
    }

    public List<GenreEntity> getApplicableGenres() {
        return applicableGenres;
    }

    public void setApplicableGenres(List<GenreEntity> applicableGenres) {
        this.applicableGenres = applicableGenres;
    }

    public List<FilterBook> getFilterBooks() {
        return filterBooks;
    }

    public void setFilterBooks(List<FilterBook> filterBooks) {
        this.filterBooks = filterBooks;
    }

    public FilterRuleType getType() {
        return type;
    }

    public void setType(FilterRuleType type) {
        this.type = type;
    }

    public String getReferencedProperty() {
        return referencedProperty;
    }

    public void setReferencedProperty(String referencedProperty) {
        this.referencedProperty = referencedProperty;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public void setBasic(boolean basic) {
        isBasic = basic;
    }

    public boolean isInclusive() {
        return isInclusive;
    }

    public void setInclusive(boolean inclusive) {
        isInclusive = inclusive;
    }

    public Set<FilterValueEntity> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(Set<FilterValueEntity> filterValues) {
        this.filterValues = filterValues;
    }
}
