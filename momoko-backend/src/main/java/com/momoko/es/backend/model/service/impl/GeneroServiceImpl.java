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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.backend.model.entity.CategoriaEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.SagaEntity;
import com.momoko.es.backend.model.repository.CategoriaRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;
import com.momoko.es.util.MomokoUtils;

/**
 * The Class GeneroServiceImpl.
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public List<GeneroDTO> obtenerTodosGeneros() {
        final List<GeneroDTO> listaGeneros = new ArrayList<GeneroDTO>();
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        final Iterable<GeneroEntity> generoEntityIterable = this.generoRepository.findAll();
        for (final GeneroEntity generoEntity : generoEntityIterable) {
            final GeneroDTO generoDTO = EntityToDTOAdapter.adaptarGenero(generoEntity);
            generoDTO.setImagenCabeceraGenero(imageServer + generoDTO.getImagenCabeceraGenero());
            generoDTO.setIconoGenero(imageServer + generoDTO.getIconoGenero());
            listaGeneros.add(generoDTO);

        }

        return listaGeneros;
    }

    @Override
    public GeneroDTO guardarGenero(final GeneroDTO generoDTO) throws Exception {

        GeneroEntity generoEntity = DTOToEntityAdapter.adaptarGenero(generoDTO);
        // Comprobamos si el autor existe.
        final List<GeneroEntity> coincidencias = this.generoRepository.findByNombre(generoDTO.getNombre());
        if ((CollectionUtils.isEmpty(coincidencias)) || ((generoDTO.getGeneroId() != null))) {

            if ((generoEntity.getGeneroId() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!generoEntity.getGeneroId().equals(coincidencias.get(0).getGeneroId()))) {
                    throw new Exception("El nombre del genero ya se esta utilizando");
                }
            }
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();

            if (generoEntity.getGeneroId() != null) {
                final GeneroEntity generoBD = this.generoRepository.findOne(generoEntity.getGeneroId());
                generoEntity = generoBD;
                generoEntity.setUsuarioModificacion(currentPrincipalName);
                generoEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                generoEntity.setUsuarioAlta(currentPrincipalName);
                generoEntity.setFechaAlta(Calendar.getInstance().getTime());
            }
            generoEntity.setNombre(generoDTO.getNombre());
            generoEntity.setUrlGenero(generoDTO.getUrlGenero());
            generoEntity.setImagenCabeceraGenero(MomokoUtils.soloNombreYCarpeta(generoDTO.getImagenCabeceraGenero()));
            generoEntity.setImagenIconoGenero(MomokoUtils.soloNombreYCarpeta(generoDTO.getIconoGenero()));
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
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        final GeneroDTO generoDTO = EntityToDTOAdapter.adaptarGenero(generoEntity);
        generoDTO.setImagenCabeceraGenero(imageServer + generoDTO.getImagenCabeceraGenero());
        generoDTO.setIconoGenero(imageServer + generoDTO.getIconoGenero());
        return generoDTO;
    }

    @Override
    public List<CategoriaDTO> obtenerListaCategorias() {
        return EntityToDTOAdapter.adaptarCategorias(this.categoriaRepository.findAll());
    }

    @Override
    public List<GeneroDTO> obtenerGenerosCategoria(final CategoriaDTO categoriaDTO) {
        final List<GeneroEntity> generos = this.generoRepository
                .findByCategoriaUrlCategoriaAndFechaBajaIsNull(categoriaDTO.getUrlCategoria());
        return EntityToDTOAdapter.adaptarGeneros(generos);
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(final GeneroDTO generoDTO, final int numElements,
            final Integer numeroPagina) {
        final List<LibroSimpleDTO> resultado = new ArrayList<>();
        final Integer initElement = numElements * numeroPagina;
        final Integer endElement = initElement + numElements;

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .obtenerAnalisisSagasYLibrosPorGeneroYFecha(generoDTO.getUrlGenero(), initElement, endElement);

        for (final EntradaEntity entradaEntity : listaEntities) {
            if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
                final LibroEntity libro = entradaEntity.getLibrosEntrada().iterator().next();
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, null);
                libroSimple.setRepresentaSaga(false);
                libroSimple.setUrlSaga(null);
                libroSimple.setNombreSaga(null);
                resultado.add(libroSimple);
            } else {
                final SagaEntity saga = entradaEntity.getSagasEntrada().iterator().next();
                final LibroEntity libro = saga.getLibros().iterator().next();
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, null);
                libroSimple.setRepresentaSaga(true);
                libroSimple.setUrlSaga(saga.getUrlSaga());
                libroSimple.setNombreSaga(saga.getNombre());
                resultado.add(libroSimple);
            }
        }
        return resultado;

    }

}
