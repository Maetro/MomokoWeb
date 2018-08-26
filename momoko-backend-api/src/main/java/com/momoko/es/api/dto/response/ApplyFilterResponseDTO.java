package com.momoko.es.api.dto.response;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.filter.FilterDTO;

import java.util.List;
import java.util.Objects;

public class ApplyFilterResponseDTO {

    List<FilterDTO> avaliableFiltersList;

    List<LibroSimpleDTO> booksSelected;

    public List<FilterDTO> getAvaliableFiltersList() {
        return avaliableFiltersList;
    }

    public void setAvaliableFiltersList(List<FilterDTO> avaliableFiltersList) {
        this.avaliableFiltersList = avaliableFiltersList;
    }

    public List<LibroSimpleDTO> getBooksSelected() {
        return booksSelected;
    }

    public void setBooksSelected(List<LibroSimpleDTO> booksSelected) {
        this.booksSelected = booksSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyFilterResponseDTO that = (ApplyFilterResponseDTO) o;
        return Objects.equals(getAvaliableFiltersList(), that.getAvaliableFiltersList()) &&
                Objects.equals(getBooksSelected(), that.getBooksSelected());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAvaliableFiltersList(), getBooksSelected());
    }

    @Override
    public String toString() {
        return "ApplyFilterResponseDTO{" +
                "avaliableFiltersList=" + avaliableFiltersList +
                ", booksSelected=" + booksSelected +
                '}';
    }
}
