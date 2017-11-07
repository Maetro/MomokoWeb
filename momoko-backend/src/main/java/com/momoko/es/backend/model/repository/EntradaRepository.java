/**
 * EntradaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;

/**
 * The Interface EntradaRepository.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
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

    /**
     * Find ultimas entradas.
     *
     * @param num
     *            the num
     * @return the list
     */
    @Query("select e from EntradaEntity e ORDER by e.fechaAlta DESC")
    List<EntradaEntity> findUltimasEntradas(Pageable pageable);

    /**
     * Find by libro entrada.
     *
     * @param libro
     *            the libro
     * @return the list
     */
    List<EntradaEntity> findByLibroEntrada(LibroEntity libro);

}
