/**
 * LibroService.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.LibroDTO;

/**
 * The Interface LibroService.
 */
public interface LibroService {

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    public List<LibroDTO> recuperarLibros();

    /**
     * Guardar libro.
     *
     * @param libroAGuardar
     *            the libro a guardar
     * @return the estado guardado libro enum
     * @throws Exception
     */
    public LibroDTO guardarLibro(LibroDTO libroAGuardar) throws Exception;

    /**
     * Obtener lista nombres autores.
     *
     * @return the list
     */
    public List<String> obtenerListaNombresAutores();

    /**
     * Obtener lista nombres editoriales.
     *
     * @return the list
     */
    public List<String> obtenerListaNombresEditoriales();

}
