/**
 * GaleriaRepository.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.GaleriaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaleriaRepository extends CrudRepository<GaleriaEntity, Integer> {

    List<GaleriaEntity> findAll();

    List<GaleriaEntity> findByNombreGaleria(String nombre);

    GaleriaEntity findOneByUrlGaleria(String urlGaleria);

    GaleriaEntity findOneByUrlGaleriaAndFechaBajaIsNull(String urlGaleria);

}
