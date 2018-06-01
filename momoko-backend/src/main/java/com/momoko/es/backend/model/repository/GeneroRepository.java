/**
 * GeneroRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.GeneroEntity;

/**
 * The Interface GeneroRepository.
 */
public interface GeneroRepository extends CrudRepository<GeneroEntity, Integer> {

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<GeneroEntity> findByNombre(String nombre);

    /**
     * Find one by url genero and fecha baja is null order by fecha alta desc.
     *
     * @param urlGenero
     *            the url genero
     * @return the genero entity
     */
    GeneroEntity findOneByUrlGeneroAndFechaBajaIsNull(String urlGenero);

    /**
     * Find one by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    GeneroEntity findOneByNombreAndFechaBajaIsNull(String nombre);

    /**
     * Find by categoria.
     *
     * @param categoria
     *            the categoria
     * @return the list
     */
    List<GeneroEntity> findByCategoriaUrlCategoriaAndFechaBajaIsNull(String urlCategoria);

    /**
     * Find by url genero in.
     *
     * @param generosUrls
     *            the generos urls
     * @return the list
     */
    List<GeneroEntity> findByUrlGeneroIn(List<String> generosUrls);

}
