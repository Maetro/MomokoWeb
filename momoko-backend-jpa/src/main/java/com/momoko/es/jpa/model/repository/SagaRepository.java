/**
 * SagaRepository.java 03-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.saga.SagaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SagaRepository extends CrudRepository<SagaEntity, Integer> {

    SagaEntity findOneByUrlSaga(String urlsaga);

    @Query("SELECT l.urlLibro FROM SagaEntity s join s.libros l WHERE s.sagaId = :sagaId")
    List<String> findUrlsLibrosSaga(@Param("sagaId") Integer sagaId);

    @Query("SELECT l FROM SagaEntity s join s.libros l WHERE s.sagaId = :sagaId")
    List<LibroEntity> findLibrosSaga(@Param("sagaId") Integer sagaId);

    List<SagaEntity> findBySagaIdIn(List<Integer> sagasIds);

    @Query("SELECT s.nombre FROM SagaEntity s")
    List<String> findAllNombresSagas();

    SagaEntity findOneByNombre(String nombre);

}
