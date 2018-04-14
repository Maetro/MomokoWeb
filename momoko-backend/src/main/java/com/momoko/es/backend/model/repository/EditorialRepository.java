/**
 * EditorialRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.EditorialEntity;

/**
 * The Interface EditorialRepository.
 */
@Repository
public interface EditorialRepository extends CrudRepository<EditorialEntity, Integer> {

    /**
     * Find first by nombre editorial.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    EditorialEntity findFirstByNombreEditorial(String nombre);

    /**
     * Find all nombres editoriales.
     *
     * @return the list
     */
    @Query("SELECT e.nombreEditorial FROM EditorialEntity e")
    List<String> findAllNombresEditoriales();

    /**
     * Find first by nombre editorial.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    EditorialEntity findFirstByUrlEditorial(String urlEditorial);

}
