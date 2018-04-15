/**
 * EditorialRepository.java 08-ago-2017
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
import org.springframework.stereotype.Repository;

import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;

/**
 * The Interface EditorialRepository.
 */
@Repository
public interface EditorialRepository extends CrudRepository<EditorialEntity, Integer> {

    /**
     * Find first by nombre editorial.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    EditorialEntity findFirstByNombreEditorial(String nombre);

    /**
     * Find all nombres editoriales.
     *
     * @return the list
     */
    @Query("SELECT e.nombreEditorial FROM EditorialEntity e")
    List<String> findAllNombresEditoriales();

    /**
     * Find first by nombre editorial.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    EditorialEntity findFirstByUrlEditorial(String urlEditorial);

    /**
     * Find entrada by editorial ur ls and fecha baja is null order by fecha alta desc.
     *
     * @param urlEditorial
     *            the url editorial
     * @param pageable
     *            the pageable
     * @return the list
     */
    @Query("select distinct l from EditorialEntity e join e.librosEditorial l WHERE e.urlEditorial = :urlEditorial AND l.fechaBaja IS NULL ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findLibrosByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditorial") String urlEditorial, Pageable pageable);

    /**
     * Find number entradas by editor ur ls and fecha baja is null order by fecha alta desc.
     *
     * @param urlEditor
     *            the url editor
     * @return the long
     */
    @Query("select COUNT(e) from EditorialEntity e join e.librosEditorial l WHERE e.urlEditorial = :urlEditorial AND l.fechaBaja IS NULL")
    Long findNumberEntradasByEditorialURLsAndFechaBajaIsNull(@Param("urlEditorial") String urlEditorial);

    /**
     * Find entrada by editorial ur ls and fecha baja is null order by fecha alta desc.
     *
     * @param urlEditorial
     *            the url editorial
     * @param pageable
     *            the pageable
     * @return the list
     */
    @Query("select distinct en from EditorialEntity e join e.librosEditorial l join l.entradas en WHERE e.urlEditorial = :urlEditorial AND en.fechaBaja IS NULL ORDER BY en.fechaAlta DESC")
    List<EntradaEntity> findEntradasByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditorial") String urlEditorial, Pageable pageable);

}
