/**
 * PublicarComentarioResponse.java 02-nov-2017
 *
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.enums.ErrorPublicarComentario;
import com.momoko.es.api.enums.EstadoGuardadoEnum;

/**
 * The Class PublicarComentarioResponse.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class PublicarComentarioResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6627097156767698804L;

    /** The lista errores validacion. */
    List<ErrorPublicarComentario> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    ComentarioDTO comentariooDTO;

    /**
     * Obtiene lista errores validacion.
     *
     * @return lista errores validacion
     */
    public List<ErrorPublicarComentario> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    /**
     * Establece lista errores validacion.
     *
     * @param listaErroresValidacion
     *            nuevo lista errores validacion
     */
    public void setListaErroresValidacion(List<ErrorPublicarComentario> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    /**
     * Obtiene estado guardado.
     *
     * @return estado guardado
     */
    public EstadoGuardadoEnum getEstadoGuardado() {
        return estadoGuardado;
    }

    /**
     * Establece estado guardado.
     *
     * @param estadoGuardado
     *            nuevo estado guardado
     */
    public void setEstadoGuardado(EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    /**
     * Obtiene comentarioo DTO.
     *
     * @return comentarioo DTO
     */
    public ComentarioDTO getComentariooDTO() {
        return comentariooDTO;
    }

    /**
     * Establece comentarioo DTO.
     *
     * @param comentariooDTO
     *            nuevo comentarioo DTO
     */
    public void setComentariooDTO(ComentarioDTO comentariooDTO) {
        this.comentariooDTO = comentariooDTO;
    }

}
