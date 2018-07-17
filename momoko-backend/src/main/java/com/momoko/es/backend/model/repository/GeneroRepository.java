/**
 * GeneroRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.GenreEntity;

/**
 * The Interface GeneroRepository.
 */
public interface GeneroRepository extends CrudRepository<GenreEntity, Integer> {

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<GenreEntity> findByNombre(String nombre);
    
    /**
     * Find one by url genero and fecha baja is null order by fecha alta desc.
     *
     * @param urlGenero
     *            the url genero
     * @return the genero entity
     */
    GenreEntity findOneByUrlGeneroAndFechaBajaIsNull(String urlGenero);

    /**
     * Find one by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    GenreEntity findOneByNombreAndFechaBajaIsNull(String nombre);

    /**
     * Find by categoria.
     *
     * @param categoria
     *            the categoria
     * @return the list
     */
    List<GenreEntity> findByCategoriaUrlCategoriaAndFechaBajaIsNull(String urlCategoria);

    /**
     * Find by url genero in.
     *
     * @param generosUrls
     *            the generos urls
     * @return the list
     */
    List<GenreEntity> findByUrlGeneroIn(List<String> generosUrls);

}
