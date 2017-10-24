/**
 * EntradaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.EntradaEntity;

/**
 * The Interface EntradaRepository.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramón Casares</a>
 */
@Repository
public interface EntradaRepository extends CrudRepository<EntradaEntity, Integer> {

    /**
     * Find first by url entrada.
     *
     * @param urlEntrada
     *            url entrada
     * @return the entrada entity
     */
    EntradaEntity findFirstByUrlEntrada(String urlEntrada);

}
