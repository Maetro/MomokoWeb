/**
 * PuntuacionService.java 02-nov-2017
 *
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.PuntuacionDTO;

public interface PuntuacionService {


    /**
     * Guardar puntuacion.
     *
     * @param puntuacionDTO
     *            puntuacion DTO
     * @return the puntuacion DTO
     * @throws Exception
     *             de exception
     */
    public PuntuacionDTO guardarPuntuacion(PuntuacionDTO puntuacionDTO) throws Exception;

}
