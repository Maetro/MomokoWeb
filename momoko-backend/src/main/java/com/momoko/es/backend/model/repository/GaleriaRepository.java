/**
 * GaleriaRepository.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.GaleriaEntity;

@Repository
public interface GaleriaRepository extends CrudRepository<GaleriaEntity, Integer> {

    List<GaleriaEntity> findAll();

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<GaleriaEntity> findByNombreGaleria(String nombre);

    /**
     * Find one by url galeria.
     *
     * @param urlGaleria
     *            the url galeria
     * @return the galeria entity
     */
    GaleriaEntity findOneByUrlGaleria(String urlGaleria);

    /**
     * Find one by url genero and fecha baja is null order by fecha alta desc.
     *
     * @param urlGenero
     *            the url genero
     * @return the genero entity
     */
    GaleriaEntity findOneByUrlGaleriaAndFechaBajaIsNull(String urlGaleria);

}
