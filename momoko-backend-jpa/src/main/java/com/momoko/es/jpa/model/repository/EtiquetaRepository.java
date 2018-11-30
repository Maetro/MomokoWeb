/**
 * EtiquetaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.tag.EtiquetaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EtiquetaRepository extends CrudRepository<EtiquetaEntity, Integer> {

    List<EtiquetaEntity> findByNombre(String nombre);

    EtiquetaEntity findOneByNombre(String nombre);

    EtiquetaEntity findOneByEtiquetaUrl(String urlEtiqueta);

    List<EtiquetaEntity> findByEtiquetaUrlIn(List<String> etiquetasUrls);

}
