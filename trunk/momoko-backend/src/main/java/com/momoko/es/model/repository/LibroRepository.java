/**
 * LibroRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.model.entity.LibroEntity;

/**
 * The Interface LibroRepository.
 */
public interface LibroRepository extends CrudRepository<LibroEntity, Integer> {

    List<LibroEntity> findByTitulo(String titulo);

}
