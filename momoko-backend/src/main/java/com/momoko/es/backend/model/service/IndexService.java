/**
 * IndexService.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Interface IndexService.
 */
public interface IndexService {

    /**
     * Obtener ultimas entradas.
     *
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerUltimasEntradas();

    /**
     * Obtener libros mas vistos.
     *
     * @return the list
     */
    List<LibroSimpleDTO> obtenerLibrosMasVistos();

}
