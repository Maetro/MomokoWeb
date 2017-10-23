package com.momoko.es.backend.model.service;

import java.util.List;

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

}
