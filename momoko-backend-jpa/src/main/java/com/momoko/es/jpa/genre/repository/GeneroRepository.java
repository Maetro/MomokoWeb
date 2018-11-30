/**
 * GeneroRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.genre.repository;


import org.springframework.data.repository.CrudRepository;
import com.momoko.es.jpa.genre.entity.GenreEntity;
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
