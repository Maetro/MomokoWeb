/**
 * EditorialService.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.backend.model.entity.EditorialEntity;

public interface EditorialService {

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    List<EditorialDTO> recuperarEditoriales();

    /**
     * Guardar editorial.
     *
     * @param editorial
     *            the editorial
     */
    EditorialDTO guardarEditorial(EditorialDTO editorial);

    /**
     * Obtener editorial o crear.
     *
     * @param editorialEntity
     *            the libro entity
     * @return the editorial entity
     */
    EditorialEntity obtenerEditorialOCrear(EditorialEntity editorialEntity);

}
