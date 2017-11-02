package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.service.EtiquetaService;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

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
        final EtiquetaEntity etiquetaEntity = DTOToEntityAdapter.adaptarEtiqueta(etiquetaDTO);
        // Comprobamos si el autor existe.
        final List<EtiquetaEntity> coincidencias = this.etiquetaRepository
                .findByNombre(etiquetaDTO.getNombreEtiqueta());
        if ((CollectionUtils.isEmpty(coincidencias)) || ((etiquetaDTO.getEtiquetaId() != null))) {

            if ((etiquetaEntity.getEtiqueta_id() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!etiquetaEntity.getEtiqueta_id().equals(coincidencias.get(0).getEtiqueta_id()))) {
                    throw new Exception("El nombre de la etiqueta ya se esta utilizando");
                }
            }
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();

            if (etiquetaEntity.getEtiqueta_id() != null) {
                final EtiquetaEntity etiquetaBD = this.etiquetaRepository.findOne(etiquetaEntity.getEtiqueta_id());
                etiquetaEntity.setFechaAlta(etiquetaBD.getFechaAlta());
                etiquetaEntity.setUsuarioAlta(etiquetaBD.getUsuarioAlta());
                etiquetaEntity.setUsuarioModificacion(currentPrincipalName);
                etiquetaEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                etiquetaEntity.setUsuarioAlta(currentPrincipalName);
                etiquetaEntity.setFechaAlta(Calendar.getInstance().getTime());
            }
            return EntityToDTOAdapter.adaptarEtiqueta(this.etiquetaRepository.save(etiquetaEntity));
        } else {
            throw new Exception("El nombre del etiqueta ya se esta utilizando");
        }
    }


}
