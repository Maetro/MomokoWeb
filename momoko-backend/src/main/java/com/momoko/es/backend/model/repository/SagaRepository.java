/**
 * SagaRepository.java 03-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.SagaEntity;

/**
 * The Interface SagaRepository.
 *
 * @author <a href="DireccionCorreoUsuario@Empresa">Nombre del Autor</a>
 */
public interface SagaRepository extends CrudRepository<SagaEntity, Integer> {

    /**
     * Find by url saga.
     *
     * @param urlsaga
     *            the urlsaga
     * @return the list
     */
    SagaEntity findOneByUrlSaga(String urlsaga);

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
    @Query("SELECT l.urlLibro FROM SagaEntity s join s.libros l WHERE s.sagaId = :sagaId")
    List<String> findUrlsLibrosSaga(@Param("sagaId") Integer sagaId);

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
    @Query("SELECT l FROM SagaEntity s join s.libros l WHERE s.sagaId = :sagaId")
    List<LibroEntity> findLibrosSaga(@Param("sagaId") Integer sagaId);

    /**
     * Find by saga id in.
     *
     * @param sagasIds
     *            the sagas ids
     * @return the list
     */
    List<SagaEntity> findBySagaIdIn(List<Integer> sagasIds);

    /**
     * Find all nombres editoriales.
     *
     * @return the list
     */
    @Query("SELECT s.nombre FROM SagaEntity s")
    List<String> findAllNombresSagas();

    /**
     * Find one by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the saga entity
     */
    SagaEntity findOneByNombre(String nombre);

}
