/**
 * ValidadorService.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;
import com.momoko.es.api.enums.ErrorCreacionGenero;
import com.momoko.es.api.enums.ErrorCreacionLibro;

/**
 * The Interface ValidadorService.
 */
public interface ValidadorService {

    /**
     * Validar libro.
     *
     * @param libroDTO
     *            the libro dto
     * @return the list
     */
    List<ErrorCreacionLibro> validarLibro(LibroDTO libroDTO);

    /**
     * Validar usuario.
     *
     * @param nuevoUsuarioRequest
     *            the nuevo usuario request
     * @return the list
     */
    boolean validarUsuario(RegistroNuevoUsuarioDTO nuevoUsuarioRequest);

    /**
     * Validar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the list
     */
    List<ErrorCreacionGenero> validarGenero(GeneroDTO generoDTO);

}
