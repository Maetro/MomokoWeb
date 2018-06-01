/**
 * GaleriaServiceImpl.java 26-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.exceptions.ErrorEnGuardadoReconocidoException;
import com.momoko.es.backend.model.entity.GaleriaEntity;
import com.momoko.es.backend.model.repository.GaleriaRepository;
import com.momoko.es.backend.model.service.GaleriaService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;
import com.momoko.es.util.MomokoUtils;

@Service
public class GaleriaServiceImpl implements GaleriaService {

    @Autowired(required = false)
    private GaleriaRepository galeriaRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public GaleriaDTO obtenerGaleria(final Integer galeriaId) {
        return EntityToDTOAdapter.adaptarGaleria(this.galeriaRepository.findById(galeriaId).orElse(null));
    }

    @Override
    public List<GaleriaDTO> obtenerTodasGalerias() {
        final List<GaleriaEntity> galerias = this.galeriaRepository.findAll();
        final String urlImagenServer = this.almacenImagenes.getUrlImageServer();
        final List<GaleriaDTO> galeriasDTO = EntityToDTOAdapter.adaptarGalerias(galerias);
        for (final GaleriaDTO galeriaDTO : galeriasDTO) {
            final List<String> imagenes = new ArrayList<String>();
            for (final String imagen : galeriaDTO.getImagenes()) {
                imagenes.add(urlImagenServer + imagen);
            }
            galeriaDTO.setImagenes(imagenes);
        }
        return galeriasDTO;
    }

    @Override
    public GaleriaDTO guardarGaleria(final GaleriaDTO galeriaDTO) throws ErrorEnGuardadoReconocidoException {
        GaleriaEntity galeriaEntity = null;

        if (galeriaDTO.getGaleriaId() != null) {
            galeriaEntity = actualizarGaleria(galeriaDTO);

        } else {
            galeriaEntity = crearGaleria(galeriaDTO);
        }

        return EntityToDTOAdapter.adaptarGaleria(galeriaEntity);
    }

    private GaleriaEntity crearGaleria(final GaleriaDTO galeriaDTO) throws ErrorEnGuardadoReconocidoException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        relativizarDireccionImagenes(galeriaDTO);
        final GaleriaEntity galeriaEntity = DTOToEntityAdapter.adaptarGaleria(galeriaDTO);
        galeriaEntity.setFechaAlta(Calendar.getInstance().getTime());
        galeriaEntity.setUsuarioAlta(currentPrincipalName);
        final GaleriaEntity entidadConURL = this.galeriaRepository.findOneByUrlGaleria(galeriaDTO.getUrlGaleria());
        if (entidadConURL != null) {
            throw new ErrorEnGuardadoReconocidoException("La url ya la tiene otra galeria");
        }
        final List<GaleriaEntity> entidadConNombre = this.galeriaRepository
                .findByNombreGaleria(galeriaDTO.getNombreGaleria());
        if (CollectionUtils.isNotEmpty(entidadConNombre)) {
            throw new ErrorEnGuardadoReconocidoException("El nombre ya lo tiene otra galeria");
        }
        return this.galeriaRepository.save(galeriaEntity);
    }

    /**
     * Actualizar galeria.
     *
     * @param galeriaDTO
     *            the galeria dto
     * @return the galeria entity
     * @throws ErrorEnGuardadoReconocidoException
     *             the error en guardado reconocido exception
     */
    private GaleriaEntity actualizarGaleria(final GaleriaDTO galeriaDTO) throws ErrorEnGuardadoReconocidoException {
        final GaleriaEntity galeriaEntity = this.galeriaRepository.findById(galeriaDTO.getGaleriaId()).orElse(null);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        if (!galeriaDTO.getUrlGaleria().equals(galeriaEntity.getUrlGaleria())) {
            final GaleriaEntity entidadConURL = this.galeriaRepository.findOneByUrlGaleria(galeriaDTO.getUrlGaleria());
            if (entidadConURL != null) {
                throw new ErrorEnGuardadoReconocidoException("La url ya la tiene otra galeria");
            }
            galeriaEntity.setUrlGaleria(galeriaDTO.getUrlGaleria());
        }
        if (!galeriaDTO.getNombreGaleria().equals(galeriaEntity.getNombreGaleria())) {
            final List<GaleriaEntity> entidadConNombre = this.galeriaRepository
                    .findByNombreGaleria(galeriaDTO.getNombreGaleria());
            if (CollectionUtils.isNotEmpty(entidadConNombre)) {
                throw new ErrorEnGuardadoReconocidoException("El nombre ya lo tiene otra galeria");
            }
            galeriaEntity.setNombreGaleria(galeriaDTO.getNombreGaleria());
        }
        galeriaEntity.setFechaModificacion(Calendar.getInstance().getTime());
        galeriaEntity.setUsuarioModificacion(currentPrincipalName);
        relativizarDireccionImagenes(galeriaDTO);
        galeriaEntity.setImagenes(ConversionUtils.join(galeriaDTO.getImagenes()));
        galeriaEntity.setColumnas(galeriaDTO.getColumnas());
        return this.galeriaRepository.save(galeriaEntity);

    }

    /**
     * Relativizar direccion imagenes.
     *
     * @param galeriaDTO
     *            the galeria dto
     */
    public void relativizarDireccionImagenes(final GaleriaDTO galeriaDTO) {
        final List<String> imagenesRelativas = new ArrayList<String>();
        for (final String imagen : galeriaDTO.getImagenes()) {
            imagenesRelativas.add(MomokoUtils.soloNombreYCarpeta(imagen));
        }
        galeriaDTO.setImagenes(imagenesRelativas);
    }

}
