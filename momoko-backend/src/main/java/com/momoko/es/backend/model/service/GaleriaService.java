/**
 * GaleriaService.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.GaleriaDTO;

public interface GaleriaService {

    /**
     * Guardar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero dto
     * @throws Exception
     */
    GaleriaDTO guardarGaleria(GaleriaDTO generoDTO) throws Exception;

    /**
     * Obtener todas galerias.
     *
     * @return the list
     */
    List<GaleriaDTO> obtenerTodasGalerias();

    /**
     * Obtener galeria.
     *
     * @param galeriaId
     *            the galeria id
     * @return the galeria dto
     */
    GaleriaDTO obtenerGaleria(Integer galeriaId);

}
