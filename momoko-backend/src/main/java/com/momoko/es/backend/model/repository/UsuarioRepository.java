/**
 * UsuarioRepositoryImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.UsuarioEntity;

/**
 * The Interface UsuarioRepositoryImpl.
 */
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    /**
     * Find by email.
     *
     * @param email
     *            the email
     * @return the user
     */
    UsuarioEntity findByUsuarioEmail(String email);

    /**
     * Find by usuario login.
     *
     * @param login
     *            the login
     * @return the usuario entity
     */
    UsuarioEntity findByUsuarioLogin(String login);

    /**
     * Find encoded password by email.
     *
     * @param usuarioEmail
     *            the usuario email
     * @return the string
     */
    @Query("SELECT usuarioContrasena from UsuarioEntity u where u.usuarioEmail = ?1")
    String findEncodedPasswordByEmail(String usuarioEmail);

    /**
     * Find all by usuario email in.
     *
     * @param emails
     *            the emails
     * @return the list
     */
    List<UsuarioEntity> findAllByUsuarioEmailIn(List<String> emails);

}
