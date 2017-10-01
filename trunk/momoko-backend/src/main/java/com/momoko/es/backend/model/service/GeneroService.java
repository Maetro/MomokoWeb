/**
 * GeneroService.java 26-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.GeneroDTO;

public interface GeneroService {

    /**
     * Obtener todos generos.
     *
     * @return the list
     */
    List<GeneroDTO> obtenerTodosGeneros();

    /**
     * Guardar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero dto
     * @throws Exception
     */
    GeneroDTO guardarGenero(GeneroDTO generoDTO) throws Exception;

}
