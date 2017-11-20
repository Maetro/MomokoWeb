/**
 * VideoRepository.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.VideoEntity;

/**
 * The Interface VideoRepository.
 */
@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Integer> {

    /**
     * Find first by url entrada.
     *
     * @param urlEntrada
     *            url entrada
     * @return the entrada entity
     */
    VideoEntity findFirstByEntradaUrlEntrada(String urlEntrada);

}
