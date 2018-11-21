/**
 * IndexService.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.index.service;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.api.dto.response.IndexDataReponseDTO;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;

import java.util.List;

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

    /**
     * Obtener menu.
     *
     * @return the list
     */
    List<MenuDTO> obtenerMenu();

    /**
     * Obtener ultimo comic analizado.
     *
     * @return the libro simple dto
     */
    LibroEntradaSimpleDTO obtenerUltimoComicAnalizado();

    /**
     * Obtener ultimos analisis.
     *
     * @return the list
     */
    List<LibroSimpleDTO> obtenerUltimosAnalisis();

    /**
     * Obtener ultimas fichas.
     *
     * @return the list
     */
    List<LibroSimpleDTO> obtenerUltimasFichas();

    /**
     * Suscribirse.
     *
     * @param email
     *            the email
     */
    void suscribirse(String email);

    IndexDataReponseDTO getIndexDataResponse();

}
