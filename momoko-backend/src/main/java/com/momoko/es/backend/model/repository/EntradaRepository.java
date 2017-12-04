/**
 * EntradaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
    @Query("select e from EntradaEntity e ORDER by e.entradaId DESC")
    List<EntradaEntity> findUltimasEntradas(Pageable pageable);

    /**
     * Find by libro entrada not null order by libro entrada visitas desc.
     *
     * @param pageable
     *            the pageable
     * @return the list
     */
    List<EntradaEntity> findTop3ByLibrosEntradaIsNotNullOrderByLibrosEntradaVisitasDesc();

    /**
     * Find by libro entrada.
     *
     * @param libroEntrada
     *            the libro entrada
     * @return the list
     */
    List<EntradaEntity> findByLibrosEntradaIn(List<LibroEntity> librosEntrada);

    /**
     * Find entrada miscelaneos anterior a fecha.
     *
     * @param fechaAlta
     *            the fecha alta
     * @return the entrada entity
     */
    @Query("select e from EntradaEntity e where e.tipoEntrada = 3 and e.fechaAlta < :fechaAlta and e.fechaBaja IS NULL ORDER by e.fechaAlta DESC")
    Page<EntradaEntity> findEntradaMiscelaneosAnteriorAFecha(@Param("fechaAlta") Date fechaAlta, Pageable limit);

    /**
     * Find entrada miscelaneos posterior a fecha.
     *
     * @param fechaAlta
     *            the fecha alta
     * @param limit
     *            the limit
     * @return the entrada entity
     */
    @Query("select e from EntradaEntity e where e.tipoEntrada = 3 and e.fechaAlta > :fechaAlta and e.fechaBaja IS NULL ORDER by e.fechaAlta DESC")
    Page<EntradaEntity> findEntradaMiscelaneosPosteriorAFecha(@Param("fechaAlta") Date fechaAlta, Pageable limit);

    /**
     * Seleccionar entradas aleatorias.
     *
     * @param entradaId
     *            the entrada id
     * @param pageRequest
     *            the page request
     * @return the list
     */
    @Query("SELECT e from EntradaEntity e where e.entradaId <> :entradaId and e.fechaBaja IS NULL ORDER BY rand()")
    Page<EntradaEntity> seleccionarEntradasAleatorias(@Param("entradaId") Integer entradaId, Pageable pageRequest);

    /**
     * Find by tipo entrada and fecha baja is null.
     *
     * @param tipoEntrada
     *            the tipo entrada
     * @return the list
     */
    List<EntradaEntity> findByTipoEntradaAndFechaBajaIsNullOrderByFechaAltaDesc(Integer tipoEntrada, Pageable limit);

    @Query("select distinct e from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2 AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    List<EntradaEntity> findEntradaAnalisisLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2 AND e.fechaBaja IS NULL")
    Long findNumberEntradaAnalisisLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds);

}
