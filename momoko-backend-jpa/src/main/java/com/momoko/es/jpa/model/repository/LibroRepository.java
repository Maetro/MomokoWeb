/**
 * LibroRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.book.LibroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LibroRepository extends CrudRepository<LibroEntity, Integer> {

    @Override
    List<LibroEntity> findAll();

    List<LibroEntity> findByTitulo(String titulo);

    @Query("SELECT l.titulo FROM LibroEntity l")
    List<String> findAllTitulosLibros();

    LibroEntity findOneByTitulo(String titulo);

    LibroEntity findOneByUrlLibro(String urlLibro);

    @Query("select l from LibroEntity l ORDER BY l.visitas DESC")
    List<LibroEntity> findLibrosMasVistos(Pageable pageable);

    @Query("select distinct l from LibroEntity l join l.entradas e join l.generos g join l.entradas e"
            + " WHERE e.tipoEntrada = 2 AND g.generoId IN :generoIds AND e.tipoEntrada IS NOT NULL"
            + " AND e.createdDate < :ahora ORDER BY l.fechaAlta DESC")
    List<LibroEntity> obtenerLibrosConAnalisisGeneroPorFecha(@Param("generoIds") List<Integer> generoIds,
                                                             @Param("ahora") Date ahora, Pageable pageable);

    @Query("select distinct l from LibroEntity l join l.entradas e join l.generos g WHERE l.libroId <> :libroId AND g.generoId IN :generoIds AND e.tipoEntrada IS NOT NULL ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findLibrosParecidosByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds, @Param("libroId") Integer libroId, Pageable pageable);


    List<LibroEntity> findByLibroIdIn(List<Integer> librosId);

    List<LibroEntity> findByUrlLibroIn(List<String> urlsLibros);

    @Query("select distinct l from LibroEntity l join l.entradas e WHERE e.tipoEntrada = 2 ORDER BY e.entradaId DESC")
    List<LibroEntity> findUltimosAnalisis(Pageable pageable);

    @Query("select l from LibroEntity l ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findUltimasFichas(Pageable pageable);

}
