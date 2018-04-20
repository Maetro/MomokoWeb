package com.momoko.es.api.dto.response;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionRedactor;

import java.io.Serializable;
import java.util.List;

public class GuardarRedactorResponse implements Serializable {

    /** The lista errores validacion. */
    private List<ErrorCreacionRedactor> listaErroresValidacion;

    /** The estado guardado. */
    private EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    private RedactorDTO redactorDTO;

    private String stackTrace;

    public List<ErrorCreacionRedactor> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    public void setListaErroresValidacion(List<ErrorCreacionRedactor> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    public EstadoGuardadoEnum getEstadoGuardado() {
        return estadoGuardado;
    }

    public void setEstadoGuardado(EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    public RedactorDTO getRedactorDTO() {
        return redactorDTO;
    }

    public void setRedactorDTO(RedactorDTO redactorDTO) {
        this.redactorDTO = redactorDTO;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
