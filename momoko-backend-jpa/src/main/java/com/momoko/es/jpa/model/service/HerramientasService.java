/**
 * HerramientasService.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.EntradaUrlDTO;

import java.util.List;

public interface HerramientasService {

    /**
     * Obtener urls ordenadas por longitud.
     *
     * @return the list
     */
    public List<EntradaUrlDTO> obtenerUrlsOrdenadasPorLongitud();

}
