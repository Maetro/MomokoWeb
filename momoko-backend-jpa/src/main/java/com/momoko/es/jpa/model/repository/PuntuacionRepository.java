/**
 * PuntuacionRepository.java 11-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.PuntuacionEntity;
import com.momoko.es.jpa.model.entity.SagaEntity;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PuntuacionRepository extends CrudRepository<PuntuacionEntity, Integer> {

    public PuntuacionEntity findOnePuntuacionEntityByLibroAndAutor(LibroEntity libro, UsuarioEntity usuario);


    public List<PuntuacionEntity> findByEsPuntuacionMomokoAndLibroLibroIdIn(boolean esPuntuacionMomoko,
                                                                            List<Integer> libroIds);


    public PuntuacionEntity findOneByEsPuntuacionMomokoAndLibro(boolean esPuntuacionMomoko, LibroEntity libro);


    @Query("select p.valor from PuntuacionEntity p join p.libro l WHERE l.urlLibro LIKE :urlLibro")
    BigDecimal findOneByEsPuntuacionMomokoAndLibroUrl(@Param("urlLibro") String urlLibro);

    public PuntuacionEntity findOneByEsPuntuacionMomokoAndSaga(boolean esPuntuacionMomoko, SagaEntity sagaEntity);

    public PuntuacionEntity findOnePuntuacionEntityBySagaAndAutor(SagaEntity saga, UsuarioEntity autor);

    @Query("select AVG(p.valor) from PuntuacionEntity p join p.libro l join l.entradas e "
            + "WHERE e.entradaAutor.usuarioId = :userId AND e.tipoEntrada = 2")
    public BigDecimal findScoreAverageFromUserId(@Param("userId") Integer userId);

    @Query("select p.valor from PuntuacionEntity p join p.saga s WHERE s.urlSaga LIKE :urlSaga")
    public BigDecimal findOneByEsPuntuacionMomokoAndSagaUrl(@Param("urlSaga") String urlSaga);

}
