/**
 * EditorialRepository.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.EditorialEntity;
import com.momoko.es.jpa.model.entity.EntradaEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorialRepository extends CrudRepository<EditorialEntity, Integer> {

    EditorialEntity findFirstByNombreEditorial(String nombre);

    @Query("SELECT e.nombreEditorial FROM EditorialEntity e")
    List<String> findAllNombresEditoriales();

    EditorialEntity findFirstByUrlEditorial(String urlEditorial);

    @Query("select distinct l from EditorialEntity e join e.librosEditorial l WHERE e.urlEditorial = :urlEditorial AND l.fechaBaja IS NULL ORDER BY l.fechaAlta DESC")
    List<LibroEntity> findLibrosByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditorial") String urlEditorial, Pageable pageable);

    @Query("select COUNT(e) from EditorialEntity e join e.librosEditorial l WHERE e.urlEditorial = :urlEditorial AND l.fechaBaja IS NULL")
    Long findNumberEntradasByEditorialURLsAndFechaBajaIsNull(@Param("urlEditorial") String urlEditorial);

    @Query("select distinct en from EditorialEntity e join e.librosEditorial l join l.entradas en WHERE e.urlEditorial = :urlEditorial AND en.fechaBaja IS NULL ORDER BY en.fechaAlta DESC")
    List<EntradaEntity> findEntradasByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditorial") String urlEditorial, Pageable pageable);

}
