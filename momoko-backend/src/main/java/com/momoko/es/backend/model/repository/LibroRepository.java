/**
 * LibroRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.momoko.es.backend.model.entity.LibroEntity;

/**
 * The Interface LibroRepository.
 */
public interface LibroRepository extends CrudRepository<LibroEntity, Integer> {

    List<LibroEntity> findByTitulo(String titulo);

    @Query("SELECT l.titulo FROM LibroEntity l")
    List<String> findAllTitulosLibros();

    /**
     * Find one by titulo.
     *
     * @param titulo
     *            the titulo
     * @return the libro entity
     */
    LibroEntity findOneByTitulo(String titulo);

    /**
     * Find one by url libro.
     *
     * @param urlLibro
     *            the url libro
     * @return the libro entity
     */
    LibroEntity findOneByUrlLibro(String urlLibro);

    /**
     * Find libros mas vistos.
     *
     * @param pageRequest
     *            the page request
     * @return the list
     */
    @Query("select l from LibroEntity l ORDER BY l.visitas DESC")
    List<LibroEntity> findLibrosMasVistos(Pageable pageable);

    /**
     * Find libro by generos and fecha baja is null order by fecha alta desc.
     *
     * @param generos
     *            the generos
     * @param pageable
     *            the pageable
     * @return the list
     */
    @Query("select distinct l from LibroEntity l join l.entradas e join l.generos g join l.entradas e WHERE e.tipoEntrada = 2 AND g.generoId IN :generoIds AND e.tipoEntrada IS NOT NULL ORDER BY l.fechaAlta DESC")
    List<LibroEntity> obtenerLibrosConAnalisisGeneroPorFecha(@Param("generoIds") List<Integer> generoIds,
            Pageable pageable);

    /**
     * Find libro by generos and fecha baja is null order by fecha alta desc.
     *
     * @param generos
     *            the generos
     * @param pageable
     *            the pageable
     * @return the list
     */
    @Query("select distinct l from LibroEntity l join l.entradas e join l.generos g WHERE l.libroId <> :libroId AND g.generoId IN :generoIds AND e.tipoEntrada IS NOT NULL ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findLibrosParecidosByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds, @Param("libroId") Integer libroId, Pageable pageable);

    /**
     * Find by libro id in.
     *
     * @param librosId
     *            the libros id
     * @return the list
     */
    List<LibroEntity> findByLibroIdIn(List<Integer> librosId);

    /**
     * Find ultimos analisis.
     *
     * @return the list
     */
    @Query("select distinct l from LibroEntity l join l.entradas e WHERE e.tipoEntrada = 2 ORDER BY e.entradaId DESC")
    List<LibroEntity> findUltimosAnalisis(Pageable pageable);

    /**
     * Find ultimas fichas.
     *
     * @param pageRequest
     *            the page request
     * @return the list
     */
    @Query("select l from LibroEntity l ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findUltimasFichas(Pageable pageable);

}
