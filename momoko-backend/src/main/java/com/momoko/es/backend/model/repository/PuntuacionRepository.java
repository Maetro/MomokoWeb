/**
 * PuntuacionRepository.java 11-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;

public interface PuntuacionRepository extends CrudRepository<PuntuacionEntity, Integer> {

    /**
     * Find one puntuacion entity by libro and autor.
     *
     * @param libro
     *            the libro
     * @param usuario
     *            the usuario
     * @return the puntuacion entity
     */
    public PuntuacionEntity findOnePuntuacionEntityByLibroAndAutor(LibroEntity libro, UsuarioEntity usuario);

    /**
     * Find by es puntuacion momoko and libro.
     *
     * @param esPuntuacionMomoko
     *            the es puntuacion momoko
     * @param libros
     *            the libros
     * @return the list
     */
    public List<PuntuacionEntity> findByEsPuntuacionMomokoAndLibroLibroIdIn(boolean esPuntuacionMomoko,
            List<Integer> libroIds);

    /**
     * Find one by es puntuacion momoko and libro.
     *
     * @param esPuntuacionMomoko
     *            the es puntuacion momoko
     * @param libros
     *            the libros
     * @return the puntuacion entity
     */
    public PuntuacionEntity findOneByEsPuntuacionMomokoAndLibro(boolean esPuntuacionMomoko, LibroEntity libro);

}
