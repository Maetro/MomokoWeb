/**
 * AnadirPuntuacionResponse.java 02-nov-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.api.response;

import java.util.List;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.enums.ErrorAnadirPuntuacionEnum;
import com.momoko.es.api.enums.EstadoGuardadoEnum;

/**
 * The Class AnadirPuntuacionResponse.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramon Casares</a>
 */
public class AnadirPuntuacionResponse {

    /** The lista errores validacion. */
    List<ErrorAnadirPuntuacionEnum> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    PuntuacionDTO puntuacionDTO;

    /**
     * Obtiene lista errores validacion.
     *
     * @return lista errores validacion
     */
    public List<ErrorAnadirPuntuacionEnum> getListaErroresValidacion() {
        return listaErroresValidacion;
    }

    /**
     * Establece lista errores validacion.
     *
     * @param listaErroresValidacion
     *            nuevo lista errores validacion
     */
    public void setListaErroresValidacion(List<ErrorAnadirPuntuacionEnum> listaErroresValidacion) {
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
     * Obtiene puntuacion DTO.
     *
     * @return puntuacion DTO
     */
    public PuntuacionDTO getPuntuacionDTO() {
        return puntuacionDTO;
    }

    /**
     * Establece puntuacion DTO.
     *
     * @param puntuacionDTO
     *            nuevo puntuacion DTO
     */
    public void setPuntuacionDTO(PuntuacionDTO puntuacionDTO) {
        this.puntuacionDTO = puntuacionDTO;
    }
}
