/**
 * GeneroRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface GeneroRepository.
 */
public interface GeneroRepository extends CrudRepository<GenreEntity, Integer> {

    List<GenreEntity> findByNombre(String nombre);

    GenreEntity findOneByUrlGeneroAndFechaBajaIsNull(String urlGenero);

    GenreEntity findOneByNombreAndFechaBajaIsNull(String nombre);

    List<GenreEntity> findByCategoriaUrlCategoriaAndFechaBajaIsNull(String urlCategoria);

    List<GenreEntity> findByUrlGeneroIn(List<String> generosUrls);

}
