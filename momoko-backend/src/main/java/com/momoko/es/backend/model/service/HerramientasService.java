/**
 * HerramientasService.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EntradaUrlDTO;

public interface HerramientasService {

    /**
     * Obtener urls ordenadas por longitud.
     *
     * @return the list
     */
    public List<EntradaUrlDTO> obtenerUrlsOrdenadasPorLongitud();

}
