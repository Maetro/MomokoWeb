/**
 * GeneroServiceImpl.java 26-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class GeneroServiceImpl.
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public List<GeneroDTO> obtenerTodosGeneros() {
        final List<GeneroDTO> listaGeneros = new ArrayList<GeneroDTO>();
        final Iterable<GeneroEntity> generoEntityIterable = this.generoRepository.findAll();
        for (final GeneroEntity generoEntity : generoEntityIterable) {
            listaGeneros.add(EntityToDTOAdapter.adaptarGenero(generoEntity));
        }
        return listaGeneros;
    }

    @Override
    public GeneroDTO guardarGenero(final GeneroDTO generoDTO) throws Exception {
        final GeneroEntity generoEntity = DTOToEntityAdapter.adaptarGenero(generoDTO);
        // Comprobamos si el autor existe.
        final List<GeneroEntity> coincidencias = this.generoRepository.findByNombre(generoDTO.getNombre());
        if ((CollectionUtils.isEmpty(coincidencias)) || ((generoDTO.getGeneroId() != null))) {
            if ((generoEntity.getGenero_id() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!generoEntity.getGenero_id().equals(coincidencias.get(0).getGenero_id()))) {
                    throw new Exception("El nombre del genero ya se esta utilizando");
                }
            }
            // libroEntity.
            if (generoEntity.getGenero_id() != null) {
                generoEntity.setUsuarioModificacion("RMaetro@gmail.com");
                generoEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                generoEntity.setUsuarioAlta("RMaetro@gmail.com");
                generoEntity.setFechaAlta(Calendar.getInstance().getTime());
            }
            return EntityToDTOAdapter.adaptarGenero(this.generoRepository.save(generoEntity));
        } else {
            throw new Exception("El nombre del genero ya se esta utilizando");
        }
    }

}
