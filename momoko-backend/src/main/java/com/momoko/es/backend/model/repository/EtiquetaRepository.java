/**
 * EtiquetaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.EtiquetaEntity;

public interface EtiquetaRepository extends CrudRepository<EtiquetaEntity, Integer> {

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<EtiquetaEntity> findByNombre(String nombre);

    /**
     * Find one by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the etiqueta entity
     */
    EtiquetaEntity findOneByNombre(String nombre);

    /**
     * Find one by url categoria.
     *
     * @param urlCategoria
     *            the url categoria
     * @return the categoria entity
     */
    EtiquetaEntity findOneByEtiquetaUrl(String urlEtiqueta);

    /**
     * Find by etiqueta url in.
     *
     * @param etiquetasUrls
     *            the etiquetas urls
     * @return the list
     */
    List<EtiquetaEntity> findByEtiquetaUrlIn(List<String> etiquetasUrls);

}
