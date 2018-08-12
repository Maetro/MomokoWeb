package com.momoko.es.api.dto.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.genre.GenreDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterDTO implements Serializable {

    private Integer filterId;

    private String nameFilter;

    private String urlFilter;

    private FilterRuleType filterType;

    private List<GenreDTO> genres = new ArrayList<>();

    private List<LibroDTO> books = new ArrayList<>();

    private List<SagaDTO> sagas = new ArrayList<>();

    private String referencedProperty;

    private List<String> possibleValues;

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

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<String> possibleValues) {
        this.possibleValues = possibleValues;
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
}
