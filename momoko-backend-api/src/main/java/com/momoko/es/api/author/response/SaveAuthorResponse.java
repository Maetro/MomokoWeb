package com.momoko.es.api.author.response;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.enums.AuthorCreationError;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionLibro;
import com.momoko.es.api.filter.dto.FilterDTO;

import java.io.Serializable;
import java.util.List;

public class SaveAuthorResponse implements Serializable {

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    List<AuthorCreationError> listaErroresValidacion;

    AuthorDTO authorDTO;

    public EstadoGuardadoEnum getEstadoGuardado() {
        return estadoGuardado;
    }

    public void setEstadoGuardado(EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    public List<AuthorCreationError> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    public void setListaErroresValidacion(List<AuthorCreationError> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }


}
