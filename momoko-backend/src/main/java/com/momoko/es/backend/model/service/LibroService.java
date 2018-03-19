/**
 * LibroService.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;

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

    /**
     * Obtener lista titulos libros.
     *
     * @return the list
     */
    public List<String> obtenerListaTitulosLibros();

    /**
     * Obtener libro.
     *
     * @param urlLibro
     *            the url libro
     * @return the libro dto
     */
    public ObtenerFichaLibroResponse obtenerLibro(String urlLibro);

    /**
     * Obtener libros parecidos.
     *
     * @param libro
     *            the libro
     * @param i
     *            the i
     */
    public List<LibroSimpleDTO> obtenerLibrosParecidos(LibroDTO libro, int i);

    /**
     * Obtener libros genero por fecha.
     *
     * @param genero
     *            the genero
     * @param numeroLibros
     *            the numero libros
     * @param pagina
     *            the pagina
     * @return the list
     */
    List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(GeneroDTO genero, int numeroLibros, int pagina);

    /**
     * Obtener numero libros con analisis genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the integer
     */
    public Integer obtenerNumeroLibrosConAnalisisGenero(GeneroDTO generoDTO);

    /**
     * Obtener libros.
     *
     * @param librosSaga
     *            the libros saga
     * @return the list
     */
    public List<LibroDTO> obtenerLibros(List<String> librosSaga);

    /**
     * Obtener analisis libro.
     *
     * @param urlLibro
     *            the url libro
     * @return the list
     */
    public EntradaDTO obtenerAnalisisLibro(String urlLibro);

}
