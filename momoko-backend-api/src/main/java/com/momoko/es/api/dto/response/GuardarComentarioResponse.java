/**
 * GuardarComentarioResponse.java 12-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.util.List;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;

/**
 * The Class GuardarComentarioResponse.
 */
public class GuardarComentarioResponse {

    /** The estado guardado. */
    private EstadoGuardadoEnum estadoGuardado;

    /** The comentario. */
    private ComentarioDTO comentario;

    /** The lista errores validacion. */
    private List<ErrorCreacionComentario> listaErroresValidacion;

    /**
     * Gets the estado guardado.
     *
     * @return the estado guardado
     */
    public EstadoGuardadoEnum getEstadoGuardado() {
        return this.estadoGuardado;
    }

    /**
     * Sets the estado guardado.
     *
     * @param estadoGuardado
     *            the new estado guardado
     */
    public void setEstadoGuardado(final EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    /**
     * Gets the comentario.
     *
     * @return the comentario
     */
    public ComentarioDTO getComentario() {
        return this.comentario;
    }

    /**
     * Sets the comentario.
     *
     * @param comentario
     *            the new comentario
     */
    public void setComentario(final ComentarioDTO comentario) {
        this.comentario = comentario;
    }

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionComentario> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionComentario> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

}
