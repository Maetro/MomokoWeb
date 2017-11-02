/**
 * ComentarioRepository.java 02-nov-2017
 *
 */
package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.ComentarioEntity;

/**
 * The Interface ComentarioRepository.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Repository
public interface ComentarioRepository extends CrudRepository<ComentarioEntity, Integer> {



}
