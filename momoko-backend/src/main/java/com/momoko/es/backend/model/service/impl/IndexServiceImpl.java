/**
 * IndexServiceImpl.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;

/**
 * The Class IndexServiceImpl.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Autowired(required = false)
    private StorageService storageService;

    @Override
    public List<EntradaSimpleDTO> obtenerUltimasEntradas() {
        final List<EntradaEntity> listaEntities = this.entradaRepository.findUltimasEntradas(new PageRequest(0, 5));
        final List<EntradaSimpleDTO> listaEntradasSimples = ConversionUtils.obtenerEntradasBasicas(listaEntities);
        for (final EntradaSimpleDTO entradaSimpleDTO : listaEntradasSimples) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 628, 418, true);
                    entradaSimpleDTO.setImagenEntrada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEntradasSimples;
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosMasVistos() {
        final List<LibroEntity> listaLibros = this.libroRepository.findLibrosMasVistos(new PageRequest(0, 5));
        final List<Integer> listaLibrosIds = new ArrayList<Integer>();
        for (final LibroEntity libroEntity : listaLibros) {
            listaLibrosIds.add(libroEntity.getLibroId());
        }
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<LibroEntity, PuntuacionEntity>();
        if (CollectionUtils.isNotEmpty(listaPuntuaciones)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }
        }
        final List<LibroSimpleDTO> listaLibrosSimples = ConversionUtils.obtenerLibrosBasicos(listaLibros,
                mapaPuntacionMomokoPorLibro);

        for (final LibroSimpleDTO libroSimpleDTO : listaLibrosSimples) {
            if (libroSimpleDTO.getPortada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("portadas",
                            libroSimpleDTO.getPortada(), 170, 250, false);
                    libroSimpleDTO.setPortada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaLibrosSimples;
    }

}