package com.momoko.es.api.dto;

import com.momoko.es.api.enums.EstadoGuardadoEnum;

import java.io.Serializable;
import java.util.Objects;

public abstract class SaveResponse implements Serializable {

    Exception errorMessage;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;


    public Exception getException() {
        return errorMessage;
    }

    public void setErrorMessage(Exception errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EstadoGuardadoEnum getEstadoGuardado() {
        return estadoGuardado;
    }

    public void setEstadoGuardado(EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveResponse that = (SaveResponse) o;
        return Objects.equals(errorMessage, that.errorMessage) &&
                estadoGuardado == that.estadoGuardado;
    }

    @Override
    public int hashCode() {

        return Objects.hash(errorMessage, estadoGuardado);
    }

    @Override
    public String toString() {
        return "SaveResponse{" +
                "errorMessage='" + errorMessage + '\'' +
                ", estadoGuardado=" + estadoGuardado +
                '}';
    }
}
