/**
 * EditorialServiceImpl.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.repository.EditorialRepository;
import com.momoko.es.backend.model.service.EditorialService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public List<EditorialDTO> recuperarEditoriales() {
        final List<EditorialDTO> editoriales = new ArrayList<EditorialDTO>();
        final Iterable<EditorialEntity> listaEditoriales = this.editorialRepository.findAll();
        for (final EditorialEntity editorialEntity : listaEditoriales) {
            editoriales.add(EntityToDTOAdapter.adaptarEditorial(editorialEntity));
        }
        return editoriales;
    }

    @Override
    @Transactional
    public EditorialDTO guardarEditorial(final EditorialDTO editorial) {
        EditorialDTO editorialDTO = null;
        if (editorial.getEditorialId() != null) {
            editorialDTO = actualizarEditorial(editorial);
        } else {
            editorialDTO = nuevaEditorial(editorial);
        }
        return editorialDTO;

    }

    private EditorialDTO nuevaEditorial(final EditorialDTO editorial) {
        EditorialEntity entity = DTOToEntityAdapter.adaptarEditorial(editorial);
        entity = anadirDatosCreacionEditorial(entity);
        final EditorialEntity editorialEntity = this.editorialRepository.save(entity);
        return EntityToDTOAdapter.adaptarEditorial(editorialEntity);
    }

    private EditorialDTO actualizarEditorial(final EditorialDTO editorial) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final EditorialEntity editorialEntity = this.editorialRepository.findOne(editorial.getEditorialId());
        editorialEntity.setFechaModificacion(Calendar.getInstance().getTime());
        editorialEntity.setUsuarioModificacion(currentPrincipalName);
        editorialEntity.setUrlEditorial(editorial.getUrlEditorial());
        editorialEntity.setNombreEditorial(editorial.getNombreEditorial());
        this.editorialRepository.save(editorialEntity);
        return EntityToDTOAdapter.adaptarEditorial(editorialEntity);
    }

    @Override
    public EditorialEntity obtenerEditorialOCrear(final EditorialEntity editorialEntity) {
        EditorialEntity editorialBD = null;
        if (editorialEntity == null) {
            throw new RuntimeException("La editorial es nula");
        }
        if (editorialEntity.getEditorialId() != null) {
            editorialBD = this.editorialRepository.findOne(editorialEntity.getEditorialId());
        } else if (editorialEntity.getUrlEditorial() != null) {
            editorialBD = this.editorialRepository.findFirstByUrlEditorial(editorialEntity.getUrlEditorial());
        } else if (editorialEntity.getNombreEditorial() != null) {
            editorialBD = this.editorialRepository.findFirstByNombreEditorial(editorialEntity.getUrlEditorial());
            if (editorialBD == null) {
                editorialBD = anadirDatosCreacionEditorial(editorialEntity);
                editorialBD.setUrlEditorial(ConversionUtils.toSlug(editorialBD.getNombreEditorial()));
                editorialBD = this.editorialRepository.save(editorialBD);
            }
        } else {
            throw new RuntimeException("La editorial es nula");
        }
        return editorialBD;
    }

    private EditorialEntity anadirDatosCreacionEditorial(final EditorialEntity entity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        entity.setFechaAlta(Calendar.getInstance().getTime());
        entity.setUsuarioAlta(currentPrincipalName);
        return entity;
    }

}
