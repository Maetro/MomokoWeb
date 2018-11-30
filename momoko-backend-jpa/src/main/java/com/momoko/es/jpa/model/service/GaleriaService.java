/**
 * GaleriaService.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionGaleria;

import java.util.List;

public interface GaleriaService {

    GaleriaDTO guardarGaleria(GaleriaDTO generoDTO) throws Exception;

    List<GaleriaDTO> obtenerTodasGalerias();

    GaleriaDTO obtenerGaleria(Integer galeriaId);

    List<ErrorCreacionGaleria> validarGaleria(GaleriaDTO galeriaDTO);
}
