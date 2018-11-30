/**
 * ValidadorService.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.enums.AuthorCreationError;
import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.request.*;
import com.momoko.es.api.enums.errores.*;
import com.momoko.es.api.filter.dto.FilterDTO;

import java.util.List;

public interface ValidadorService {

    List<ErrorCreacionLibro> validarLibro(LibroDTO libroDTO);

    List<ErrorCreacionEditorial> validarEditorial(EditorialDTO editorialDTO);

    List<FilterCreationError> validateFilter(FilterDTO filterDTO);

    List<AuthorCreationError> validateAuthor(AuthorDTO authorDTO);

    List<ErrorEmailContactEnum> validateEmailContact(AuthorContactRequestDTO contactRequest);

    List<ErrorEmailContactEnum> validateEmailContact(EditorContactRequestDTO editorContactRequestDTO);

    List<ErrorEmailContactEnum> validateEmailContact(PublisherContactRequestDTO publisherContactRequestDTO);

    List<ErrorEmailContactEnum> validateEmailContact(SuscribeContactRequestDTO suscribeContactRequestDTO);
}
