/**
 * VisitaRepository.java 10-feb-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.momoko.es.backend.model.entity.VisitaEntity;

public interface VisitaRepository extends CrudRepository<VisitaEntity, Integer> {

    /**
     * Find libros mas vistos.
     *
     * @param pageable
     *            the pageable
     * @param tipoVisita
     *            the tipo visita
     * @param fechaDesde
     *            the fecha desde
     * @return the list
     */
    @Query("SELECT urlVisita FROM VisitaEntity v WHERE v.tipoVisita = :tipoVisita and v.fechaVisita > :fechaDesde GROUP BY urlVisita ORDER BY COUNT(*) DESC")
    List<String> findTipoVisitaMasVistosDesde(Pageable pageable, @Param("tipoVisita") String tipoVisita,
            @Param("fechaDesde") Date fechaDesde);

}
