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
public interface GeneroRepository extends CrudRepository<GeneroEntity, Long> {

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<GeneroEntity> findByNombre(String nombre);

}
