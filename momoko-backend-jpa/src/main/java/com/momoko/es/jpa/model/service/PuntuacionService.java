/**
 * PuntuacionService.java 02-nov-2017
 *
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.enums.errores.ErrorAnadirPuntuacionEnum;

import java.util.List;

public interface PuntuacionService {

    PuntuacionDTO guardarPuntuacion(PuntuacionDTO puntuacionDTO) throws Exception;

    List<ErrorAnadirPuntuacionEnum> validarPuntuacion(PuntuacionDTO puntuacionDTO);
}
