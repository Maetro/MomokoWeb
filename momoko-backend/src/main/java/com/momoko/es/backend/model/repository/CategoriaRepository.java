/**
 * CategoriaRepository.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.CategoriaEntity;

/**
 * The Interface CategoriaRepository.
 */
public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Integer> {

    /**
     * Find one by url categoria.
     *
     * @param urlCategoria
     *            the url categoria
     * @return the categoria entity
     */
    public CategoriaEntity findOneByUrlCategoria(String urlCategoria);

    public List<CategoriaEntity> findAll();

    /**
     * Find by url categoria in.
     *
     * @param categoriasUrls
     *            the categorias urls
     * @return the list
     */
    public List<CategoriaEntity> findByUrlCategoriaIn(List<String> categoriasUrls);

}
