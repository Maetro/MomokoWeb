/**
 * BuscadorService.java 20-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.genre.GenreDTO;

import java.util.List;

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
