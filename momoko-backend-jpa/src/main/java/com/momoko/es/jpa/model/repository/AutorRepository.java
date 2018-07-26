/**
 * AutorRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.AutorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends CrudRepository<AutorEntity, Integer> {

    List<AutorEntity> findByNombre(String nombre);

    @Query("SELECT a.nombre FROM AutorEntity a")
    List<String> findAllNombresAutores();

}
