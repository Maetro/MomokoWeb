/**
 * UsuarioRepositoryImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.model.entity.UsuarioEntity;

/**
 * The Interface UsuarioRepositoryImpl.
 */
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

}
