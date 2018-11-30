/**
 * ValidadorServiceImpl.java 15-jul-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.enums.AuthorCreationError;
import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.request.AuthorContactRequestDTO;
import com.momoko.es.api.dto.request.EditorContactRequestDTO;
import com.momoko.es.api.dto.request.PublisherContactRequestDTO;
import com.momoko.es.api.dto.request.SuscribeContactRequestDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;
import com.momoko.es.api.enums.errores.ErrorCreacionLibro;
import com.momoko.es.api.enums.errores.FilterCreationError;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.filter.enums.FilterRuleType;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ValidadorServiceImpl.
 */
@Service
public class ValidadorServiceImpl implements ValidadorService {

    @Override
    public List<ErrorCreacionLibro> validarLibro(final LibroDTO libroDTO) {

        final List<ErrorCreacionLibro> listaErrores = new ArrayList<>();

        if (StringUtils.isEmpty(libroDTO.getTitulo())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_TITULO);

        }
        if (CollectionUtils.isEmpty(libroDTO.getAutores())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_AUTOR);
        }

        if (StringUtils.isBlank(libroDTO.getUrlLibro())) {
            listaErrores.add(ErrorCreacionLibro.FALTA_URL);
        }

        if (libroDTO.getEditorial() == null) {
            listaErrores.add(ErrorCreacionLibro.FALTA_EDITORIAL);
        }

        if ((libroDTO.getEnlaceAmazon() != null) && (libroDTO.getEnlaceAmazon().length() > 1000)) {
            listaErrores.add(ErrorCreacionLibro.ENLACE_AMAZON_LARGO);
        }

        if ((libroDTO.getUrlImagen() != null) && (libroDTO.getUrlImagen().length() > 1000)) {
            listaErrores.add(ErrorCreacionLibro.ENLACE_IMAGEN_LARGO);
        }

        return listaErrores;
    }

    @Override
    public List<ErrorCreacionEditorial> validarEditorial(final EditorialDTO editorialDTO) {
        final List<ErrorCreacionEditorial> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(editorialDTO.getNombreEditorial())) {
            listaErrores.add(ErrorCreacionEditorial.FALTA_NOMBRE);
        }
        return listaErrores;
    }

    @Override
    public List<FilterCreationError> validateFilter(FilterDTO filterDTO) {
        final List<FilterCreationError> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(filterDTO.getNameFilter())) {
            errorList.add(FilterCreationError.MISSING_NAME);
        }
        if (StringUtils.isEmpty(filterDTO.getUrlFilter())) {
            errorList.add(FilterCreationError.MISSING_URL);
        }
        if (filterDTO.getFilterType() == null) {
            errorList.add(FilterCreationError.MISSING_TYPE);
        }
        if (filterDTO.getFilterType() == FilterRuleType.ENUM && CollectionUtils.isEmpty(filterDTO.getFilterValues())) {
            errorList.add(FilterCreationError.MISSING_ENUM_DEFINITION);
        }
        return errorList;
    }

    @Override
    public List<AuthorCreationError> validateAuthor(AuthorDTO authorDTO) {
        final List<AuthorCreationError> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(authorDTO.getName())) {
            errorList.add(AuthorCreationError.NO_NAME);
        }
        if (StringUtils.isEmpty(authorDTO.getAuthorUrl())) {
            errorList.add(AuthorCreationError.NO_URL);
        }
        return errorList;
    }

    @Override
    public List<ErrorEmailContactEnum> validateEmailContact(AuthorContactRequestDTO contactRequest) {
        return new ArrayList<>();
    }

    @Override
    public List<ErrorEmailContactEnum> validateEmailContact(EditorContactRequestDTO editorContactRequestDTO) {
        return new ArrayList<>();
    }

    @Override
    public List<ErrorEmailContactEnum> validateEmailContact(PublisherContactRequestDTO publisherContactRequestDTO) {
        return new ArrayList<>();
    }

    @Override
    public List<ErrorEmailContactEnum> validateEmailContact(SuscribeContactRequestDTO suscribeContactRequestDTO) {
        return new ArrayList<>();
    }


}
