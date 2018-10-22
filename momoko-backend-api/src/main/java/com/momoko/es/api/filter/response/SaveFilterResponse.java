package com.momoko.es.api.filter.response;

import com.momoko.es.api.dto.SaveResponse;
import com.momoko.es.api.enums.errores.FilterCreationError;
import com.momoko.es.api.filter.dto.FilterDTO;

import java.util.List;
import java.util.Objects;

public class SaveFilterResponse extends SaveResponse{

    List<FilterCreationError> listaErroresValidacion;

    FilterDTO filterDTO;

    public List<FilterCreationError> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    public void setListaErroresValidacion(List<FilterCreationError> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    public FilterDTO getFilterDTO() {
        return filterDTO;
    }

    public void setFilterDTO(FilterDTO filterDTO) {
        this.filterDTO = filterDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveFilterResponse that = (SaveFilterResponse) o;
        return Objects.equals(listaErroresValidacion, that.listaErroresValidacion) &&
                Objects.equals(filterDTO, that.filterDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(listaErroresValidacion, filterDTO);
    }

    @Override
    public String toString() {
        return "SaveFilterResponse{" +
                "listaErroresValidacion=" + listaErroresValidacion +
                ", filterDTO=" + filterDTO +
                '}';
    }
}
