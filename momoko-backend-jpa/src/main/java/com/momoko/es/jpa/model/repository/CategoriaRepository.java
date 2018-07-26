/**
 * CategoriaRepository.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface CategoriaRepository.
 */
public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Integer> {


    public CategoriaEntity findOneByUrlCategoria(String urlCategoria);

    public List<CategoriaEntity> findAll();

    public List<CategoriaEntity> findByUrlCategoriaIn(List<String> categoriasUrls);

}
