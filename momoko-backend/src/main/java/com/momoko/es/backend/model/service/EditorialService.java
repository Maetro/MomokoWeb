/**
 * EditorialService.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EditorialDTO;

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

}
