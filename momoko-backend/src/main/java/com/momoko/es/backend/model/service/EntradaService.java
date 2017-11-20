/**
 * EntradaService.java 11-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;

/**
 * The Interface EntradaService.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public interface EntradaService {

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    List<EntradaDTO> recuperarEntradas();

    /**
     * Guardar entrada.
     *
     * @param entradaAGuardar
     *            entrada A guardar
     * @return the entrada DTO
     * @throws Exception
     *             de exception
     */
    EntradaDTO guardarEntrada(EntradaDTO entradaAGuardar) throws Exception;

    /**
     * Obtener entrada.
     *
     * @param urlEntrada
     *            the url entrada
     * @return the obtener entrada response
     */
    ObtenerEntradaResponse obtenerEntrada(String urlEntrada);

    /**
     * Obtener entrada video.
     *
     * @param urlVideo
     *            the url video
     * @return the obtener ficha libro response
     */
    ObtenerEntradaResponse obtenerEntradaVideo(String urlVideo);

    /**
     * Obtener tres ultimas entradas populares con libro.
     *
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerTresUltimasEntradasPopularesConLibro();

}
