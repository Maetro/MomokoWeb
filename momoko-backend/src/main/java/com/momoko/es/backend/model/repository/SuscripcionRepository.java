/**
 * SuscripcionRepository.java 22-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.SuscripcionEntity;

/**
 * The Interface SuscripcionRepository.
 */
public interface SuscripcionRepository extends CrudRepository<SuscripcionEntity, Integer> {

    /**
     * Find one by email.
     *
     * @param email
     *            the email
     * @return the suscripcion entity
     */
    SuscripcionEntity findOneByEmail(String email);

}
