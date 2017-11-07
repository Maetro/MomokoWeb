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

import com.momoko.es.backend.model.entity.GeneroEntity;
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
     * Find libro by generos.
     *
     * @param genero
     *            the genero
     * @return the list
     */
    List<LibroEntity> findLibroByGeneros(List<GeneroEntity> generos, Pageable pageable);

}
