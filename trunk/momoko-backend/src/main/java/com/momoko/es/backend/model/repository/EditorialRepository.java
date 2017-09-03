/**
 * EditorialRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.EditorialEntity;

/**
 * The Interface EditorialRepository.
 */
public interface EditorialRepository extends CrudRepository<EditorialEntity, Integer> {

    /**
     * Find first by nombre editorial.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    EditorialEntity findFirstByNombreEditorial(String nombre);

}
