/**
 * BuscadorService.java 20-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.genre.GenreDTO;

/**
 * The Interface BuscadorService.
 */
public interface BuscadorService {

    /**
     * Buscar libros.
     *
     * @param librosUrls
     *            the libros urls
     * @return the list
     */
    List<LibroDTO> buscarLibros(List<String> librosUrls);

    /**
     * Buscar entradas.
     *
     * @param entradasUrls
     *            the entradas urls
     * @return the list
     */
    List<EntradaSimpleDTO> buscarEntradas(List<String> entradasUrls);

    /**
     * Buscar generos.
     *
     * @param generosUrls
     *            the generos urls
     * @return the list
     */
    List<GenreDTO> buscarGeneros(List<String> generosUrls);

    /**
     * Buscar categorias.
     *
     * @param categoriasUrls
     *            the categorias urls
     * @return the list
     */
    List<CategoriaDTO> buscarCategorias(List<String> categoriasUrls);

    /**
     * Buscar etiquetas.
     *
     * @param etiquetasUrls
     *            the etiquetas urls
     * @return the list
     */
    List<EtiquetaDTO> buscarEtiquetas(List<String> etiquetasUrls);

}
