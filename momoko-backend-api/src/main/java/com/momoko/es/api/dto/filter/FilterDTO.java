package com.momoko.es.api.dto.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.util.MomokoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterDTO implements Serializable {

    private Integer filterId;

    private String nameFilter;

    private String urlFilter;

    private FilterRuleType filterType;

    private boolean basic;

    private boolean inclusive;

    private List<GenreDTO> genres = new ArrayList<>();

    private List<LibroDTO> books = new ArrayList<>();

    private List<SagaDTO> sagas = new ArrayList<>();

    private String referencedProperty;

    private List<FilterValueDTO> filterValues;

    private List<Integer> selectedFilterValues;

    private List<String> stringSelectedValues;

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

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public List<LibroDTO> getBooks() {
        return books;
    }

    public void setBooks(List<LibroDTO> books) {
        this.books = books;
    }

    public List<SagaDTO> getSagas() {
        return sagas;
    }

    public void setSagas(List<SagaDTO> sagas) {
        this.sagas = sagas;
    }

    public List<FilterValueDTO> getFilterValues() {
        return filterValues;
    }

    public List<String> getStringSelectedValues() {
        return stringSelectedValues;
    }

    public void setStringSelectedValues(List<String> stringSelectedValues) {
        this.stringSelectedValues = stringSelectedValues;
    }

    public void setFilterValues(List<FilterValueDTO> filterValues) {
        filterValues.sort((o1, o2) -> {return o1.compareTo(o2);});
        this.filterValues = filterValues;
    }

    public FilterRuleType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterRuleType filterType) {
        this.filterType = filterType;
    }

    public String getReferencedProperty() {
        return referencedProperty;
    }

    public void setReferencedProperty(String referencedProperty) {
        this.referencedProperty = referencedProperty;
    }

    public List<Integer> getSelectedFilterValues() {
        return selectedFilterValues;
    }

    public void setSelectedFilterValues(List<Integer> selectedFilterValues) {
        this.selectedFilterValues = selectedFilterValues;
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public boolean isInclusive() {
        return inclusive;
    }

    public void setInclusive(boolean inclusive) {
        this.inclusive = inclusive;
    }

    public FilterDTO creteCopy() {
        FilterDTO newFilter = new FilterDTO();
        newFilter.filterId = this.filterId;
        newFilter.nameFilter = this.nameFilter;
        newFilter.urlFilter = this.urlFilter;
        newFilter.filterType = this.filterType;
        newFilter.basic = this.basic;
        newFilter.inclusive = this.inclusive;
        newFilter.genres = this.genres;
        newFilter.books = this.books;
        newFilter.sagas = this.sagas;
        newFilter.referencedProperty = this.referencedProperty;
        newFilter.filterValues = this.filterValues;
        newFilter.selectedFilterValues = this.selectedFilterValues;
        newFilter.stringSelectedValues = this.stringSelectedValues;
        return newFilter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterId, urlFilter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final FilterDTO other = (FilterDTO) obj;
        return Objects.equals(this.filterId, other.filterId)
                && Objects.equals(this.urlFilter, other.urlFilter);
    }

    @Override
    public String toString() {
        return MomokoHelper.toStringInJson(this);
    }
}
