/**
 * GeneroService.java 26-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.genre.GenrePageResponse;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.api.enums.OrderType;

import java.util.List;

public interface GenreService {

    /**
     * Obtener todos generos.
     *
     * @return the list
     */
    List<GenreDTO> getAllGenres();

    /**
     * Guardar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero dto
     * @throws Exception
     */
    GenreDTO saveGenre(GenreDTO generoDTO) throws Exception;

    /**
     * Obtener categoria por url.
     *
     * @param string
     *            the string
     */
    CategoriaDTO obtenerCategoriaPorUrl(String urlCategoria);

    /**
     * Obtener genero por url.
     *
     * @param urlGenero
     *            the url genero
     * @return the genero dto
     */
    GenreDTO obtenerGeneroPorUrl(String urlGenero);

    /**
     * Obtener lista categorias.
     *
     * @return the list
     */
    List<CategoriaDTO> obtenerListaCategorias();

    /**
     * Obtener generos categoria.
     *
     * @param categoriaDTO
     *            the categoria dto
     * @return the list
     */
    List<GenreDTO> obtenerGenerosCategoria(CategoriaDTO categoriaDTO);

    /**
     * Obtener libros con analisis genero por fecha.
     *
     * @param generoDTO
     *            the genero dto
     * @param i
     *            the i
     * @param numeroPagina
     *            the numero pagina
     * @return the list
     */
    List<LibroSimpleDTO> obtenerLibrosConOpinionesGeneroPorFecha(GenreDTO generoDTO, int numElements,
                                                                 Integer numeroPagina);

    /**
     * Obtener libros con analisis genero por nota.
     *
     * @param generoDTO
     *            the genero dto
     * @param numElements
     *            the num elements
     * @param numeroPagina
     *            the numero pagina
     * @return the list
     */
    List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorNota(GenreDTO generoDTO, int numElements,
                                                               Integer numeroPagina);

    /**
     * Gets the genre page.
     *
     * @param genreUrl
     *            the genre url
     * @param pageNumber
     *            the page number
     * @param tipoOrden
     *            the tipo orden
     * @return the genre page
     */
    GenrePageResponse getGenrePage(String genreUrl, Integer pageNumber, OrderType tipoOrden);

    ApplyFilterResponseDTO getBooksWithFilters(String urlGenre, List<FilterDTO> appliedFilters);
}
