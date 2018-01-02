/**
 * EtiquetaServiceImpl.java 08-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
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

import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.service.EtiquetaService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public List<EtiquetaDTO> obtenerTodasEtiquetas() {
        final List<EtiquetaDTO> listaEtiquetas = new ArrayList<EtiquetaDTO>();
        final Iterable<EtiquetaEntity> etiquetaEntityIterable = this.etiquetaRepository.findAll();
        for (final EtiquetaEntity etiquetaEntity : etiquetaEntityIterable) {
            listaEtiquetas.add(EntityToDTOAdapter.adaptarEtiqueta(etiquetaEntity));
        }
        return listaEtiquetas;
    }

    @Override
    public EtiquetaDTO guardarEtiqueta(final EtiquetaDTO etiquetaDTO) throws Exception {
        EtiquetaEntity etiquetaBD = null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (etiquetaDTO.getEtiquetaId() != null) {
            etiquetaBD = this.etiquetaRepository.findOne(etiquetaDTO.getEtiquetaId());
            etiquetaBD.setUsuarioModificacion(currentPrincipalName);
            etiquetaBD.setFechaModificacion(Calendar.getInstance().getTime());
        } else if (etiquetaDTO.getUrlEtiqueta() != null) {
            etiquetaBD = this.etiquetaRepository.findOneByEtiquetaUrl(etiquetaDTO.getUrlEtiqueta());
            etiquetaBD.setUsuarioModificacion(currentPrincipalName);
            etiquetaBD.setFechaModificacion(Calendar.getInstance().getTime());
        } else if (etiquetaDTO.getNombreEtiqueta() != null) {
            final String urlEtiqueta = ConversionUtils.toSlug(etiquetaDTO.getNombreEtiqueta());
            etiquetaBD = this.etiquetaRepository.findOneByEtiquetaUrl(urlEtiqueta);
            etiquetaBD.setUsuarioModificacion(currentPrincipalName);
            etiquetaBD.setFechaModificacion(Calendar.getInstance().getTime());
        } else {
            etiquetaBD = DTOToEntityAdapter.adaptarEtiqueta(etiquetaDTO);
            etiquetaBD.setUsuarioAlta(currentPrincipalName);
            etiquetaBD.setFechaAlta(Calendar.getInstance().getTime());
        }

        // Comprobamos si el autor existe.

        etiquetaBD.setNombre(etiquetaDTO.getNombreEtiqueta());
        if (etiquetaBD.getEtiquetaUrl() == null) {
            final String urlEtiqueta = ConversionUtils.toSlug(etiquetaDTO.getNombreEtiqueta());
            etiquetaBD.setEtiquetaUrl(urlEtiqueta);
        } else {
            etiquetaBD.setEtiquetaUrl(etiquetaDTO.getUrlEtiqueta());
        }
        return EntityToDTOAdapter.adaptarEtiqueta(this.etiquetaRepository.save(etiquetaBD));

    }

    @Override
    public EtiquetaDTO obtenerEtiquetaPorUrl(final String urlEtiqueta) {
        final EtiquetaEntity etiquetaEntity = this.etiquetaRepository.findOneByEtiquetaUrl(urlEtiqueta);
        return EntityToDTOAdapter.adaptarEtiqueta(etiquetaEntity);
    }

}
