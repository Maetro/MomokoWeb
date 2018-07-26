/**
 * EtiquetaServiceImpl.java 08-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.jpa.model.entity.EtiquetaEntity;
import com.momoko.es.jpa.model.repository.EntradaRepository;
import com.momoko.es.jpa.model.repository.EtiquetaRepository;
import com.momoko.es.jpa.model.service.EtiquetaService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

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
            etiquetaBD = this.etiquetaRepository.findById(etiquetaDTO.getEtiquetaId()).orElse(null);
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

    @Override
    @Transactional
    public Map<String, List<EtiquetaDTO>> arreglarEtiquetas() {
        Iterable<EtiquetaEntity> etiquetas = this.etiquetaRepository.findAll();

        for (final EtiquetaEntity etiquetaEntity : etiquetas) {
            if (StringUtils.isEmpty(etiquetaEntity.getEtiquetaUrl())) {
                etiquetaEntity.setEtiquetaUrl(ConversionUtils.toSlug(etiquetaEntity.getNombre()));
                this.etiquetaRepository.save(etiquetaEntity);
            }
        }

        etiquetas = this.etiquetaRepository.findAll();

        final Map<String, List<EtiquetaDTO>> mapaEtiquetas = new HashMap<String, List<EtiquetaDTO>>();
        for (final EtiquetaEntity etiquetaEntity : etiquetas) {
            List<EtiquetaDTO> listaEtiquetas = mapaEtiquetas.get(etiquetaEntity.getEtiquetaUrl());
            if (CollectionUtils.isEmpty(listaEtiquetas)) {
                listaEtiquetas = new ArrayList<EtiquetaDTO>();
            }
            listaEtiquetas.add(EntityToDTOAdapter.adaptarEtiqueta(etiquetaEntity));
            mapaEtiquetas.put(etiquetaEntity.getEtiquetaUrl(), listaEtiquetas);
        }

        return mapaEtiquetas;
    }

}
