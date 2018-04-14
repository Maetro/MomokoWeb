/**
 * EtiquetaService.java 02-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;
import java.util.Map;

import com.momoko.es.api.dto.EtiquetaDTO;

public interface EtiquetaService {

    /**
     * Obtener todos generos.
     *
     * @return the list
     */
    List<EtiquetaDTO> obtenerTodasEtiquetas();

    /**
     * Guardar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero dto
     * @throws Exception
     */
    EtiquetaDTO guardarEtiqueta(EtiquetaDTO etiquetaDTO) throws Exception;

    /**
     * Obtener etiqueta por url.
     *
     * @param urlEtiqueta
     *            the url etiqueta
     * @return the etiqueta dto
     */
    EtiquetaDTO obtenerEtiquetaPorUrl(String urlEtiqueta);

    /**
     * Arreglar etiquetas.
     *
     * @return true, if successful
     */
    Map<String, List<EtiquetaDTO>> arreglarEtiquetas();

}
