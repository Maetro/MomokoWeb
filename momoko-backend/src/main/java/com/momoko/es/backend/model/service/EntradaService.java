/**
 * EntradaService.java 11-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EntradaDTO;

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
    public List<EntradaDTO> recuperarEntradas();


    /**
     * Guardar entrada.
     *
     * @param entradaAGuardar
     *            entrada A guardar
     * @return the entrada DTO
     * @throws Exception
     *             de exception
     */
    public EntradaDTO guardarEntrada(EntradaDTO entradaAGuardar) throws Exception;

}
