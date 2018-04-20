package com.momoko.es.api.dto.response;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;

import java.io.Serializable;
import java.util.List;

/**
 * The type Guardar editorial response.
 */
public class GuardarEditorialResponse implements Serializable {

    /** The lista errores validacion. */
    private List<ErrorCreacionEditorial> listaErroresValidacion;

    /** The estado guardado. */
    private EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    private EditorialDTO editorialDTO;

    private String stackTrace;

    /**
     * Gets lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionEditorial> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    /**
     * Sets lista errores validacion.
     *
     * @param listaErroresValidacion the lista errores validacion
     */
    public void setListaErroresValidacion(List<ErrorCreacionEditorial> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    /**
     * Gets estado guardado.
     *
     * @return the estado guardado
     */
    public EstadoGuardadoEnum getEstadoGuardado() {
        return estadoGuardado;
    }

    /**
     * Sets estado guardado.
     *
     * @param estadoGuardado the estado guardado
     */
    public void setEstadoGuardado(EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    /**
     * Gets redactor dto.
     *
     * @return the redactor dto
     */
    public EditorialDTO getEditorialDTO () {
        return editorialDTO;
    }

    /**
     * Sets redactor dto.
     *
     * @param editorialDTO the editorial dto
     */
    public void setEditorialDTO (EditorialDTO editorialDTO) {
        this.editorialDTO = editorialDTO;
    }

    /**
     * Gets stack trace.
     *
     * @return the stack trace
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * Sets stack trace.
     *
     * @param stackTrace the stack trace
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
