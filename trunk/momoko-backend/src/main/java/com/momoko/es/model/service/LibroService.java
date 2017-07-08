/**
 * LibroService.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.service;

import java.util.List;

import com.momoko.es.api.dto.LibroDTO;

/**
 * The Interface LibroService.
 */
public interface LibroService {

    /**
     * Crear usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return the integer
     */
    public Integer crearLibro(LibroDTO nuevoLibro);

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    public List<LibroDTO> recuperarLibros();
}
