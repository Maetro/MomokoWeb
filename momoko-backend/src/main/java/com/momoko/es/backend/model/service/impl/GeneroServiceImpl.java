/**
 * GeneroServiceImpl.java 26-sep-2017
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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.backend.model.entity.CategoriaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.repository.CategoriaRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class GeneroServiceImpl.
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Cacheable("generos")
    public List<GeneroDTO> obtenerTodosGeneros() {
        final List<GeneroDTO> listaGeneros = new ArrayList<GeneroDTO>();
        final Iterable<GeneroEntity> generoEntityIterable = this.generoRepository.findAll();
        for (final GeneroEntity generoEntity : generoEntityIterable) {
            listaGeneros.add(EntityToDTOAdapter.adaptarGenero(generoEntity));
        }
        return listaGeneros;
    }

    @Override
    @CachePut(cacheNames = "generos", key = "#generoDTO")
    public GeneroDTO guardarGenero(final GeneroDTO generoDTO) throws Exception {

        GeneroEntity generoEntity = DTOToEntityAdapter.adaptarGenero(generoDTO);
        // Comprobamos si el autor existe.
        final List<GeneroEntity> coincidencias = this.generoRepository.findByNombre(generoDTO.getNombre());
        if ((CollectionUtils.isEmpty(coincidencias)) || ((generoDTO.getGeneroId() != null))) {

            if ((generoEntity.getGenero_id() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!generoEntity.getGenero_id().equals(coincidencias.get(0).getGenero_id()))) {
                    throw new Exception("El nombre del genero ya se esta utilizando");
                }
            }
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();

            if (generoEntity.getGenero_id() != null) {
                final GeneroEntity generoBD = this.generoRepository.findOne(generoEntity.getGenero_id());
                generoEntity = generoBD;
                generoEntity.setUsuarioModificacion(currentPrincipalName);
                generoEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                generoEntity.setUsuarioAlta(currentPrincipalName);
                generoEntity.setFechaAlta(Calendar.getInstance().getTime());
            }
            generoEntity.setImagenCabeceraGenero(generoDTO.getImagenCabeceraGenero());
            generoEntity.setImagenIconoGenero(generoDTO.getIconoGenero());
            generoEntity.setCategoria(DTOToEntityAdapter.adaptarCategoria(generoDTO.getCategoria()));
            return EntityToDTOAdapter.adaptarGenero(this.generoRepository.save(generoEntity));
        } else {
            throw new Exception("El nombre del genero ya se esta utilizando");
        }
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorUrl(final String urlCategoria) {
        final CategoriaEntity categoriaEntity = this.categoriaRepository.findOneByUrlCategoria(urlCategoria);
        return EntityToDTOAdapter.adaptarCategoria(categoriaEntity);
    }

    @Override
    public GeneroDTO obtenerGeneroPorUrl(final String urlGenero) {
        final GeneroEntity generoEntity = this.generoRepository.findOneByUrlGeneroAndFechaBajaIsNull(urlGenero);
        return EntityToDTOAdapter.adaptarGenero(generoEntity);
    }

    @Override
    @Cacheable("categorias")
    public List<CategoriaDTO> obtenerListaCategorias() {
        return EntityToDTOAdapter.adaptarCategorias(this.categoriaRepository.findAll());
    }

}
