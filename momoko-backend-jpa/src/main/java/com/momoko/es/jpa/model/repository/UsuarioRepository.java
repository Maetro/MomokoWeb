/**
 * UsuarioRepositoryImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The Interface UsuarioRepositoryImpl.
 */
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findById(Integer id);

    UsuarioEntity findByUsuarioLogin(String login);

    @Query("SELECT usuarioContrasena from UsuarioEntity u where u.email = ?1")
    String findEncodedPasswordByEmail(String usuarioEmail);

    List<UsuarioEntity> findAllByEmailIn(List<String> emails);

    List<UsuarioEntity> findAllByUsuarioRolIdIs(Integer rolId);

    UsuarioEntity findFirstByUsuarioUrl(String urlUsuario);

    Optional<UsuarioEntity> findByEmail(String email);

}
