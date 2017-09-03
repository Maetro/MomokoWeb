/**
 * AutorRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.AutorEntity;

/**
 * The Interface AutorRepository.
 */
public interface AutorRepository extends CrudRepository<AutorEntity, Integer> {

    List<AutorEntity> findByNombre(String nombre);

}
