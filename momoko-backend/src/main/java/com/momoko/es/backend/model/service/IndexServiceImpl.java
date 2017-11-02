/**
 * IndexServiceImpl.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
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
    private StorageService storageService;

    @Override
    public List<EntradaSimpleDTO> obtenerUltimasEntradas() {
        final List<EntradaEntity> listaEntities = this.entradaRepository.findUltimasEntradas(new PageRequest(0, 5));
        final List<EntradaSimpleDTO> listaEntradasSimples = ConversionUtils.obtenerEntradasBasicas(listaEntities);
        for (final EntradaSimpleDTO entradaSimpleDTO : listaEntradasSimples) {
            try {
                final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                        entradaSimpleDTO.getImagenEntrada(), 628, 418);
                entradaSimpleDTO.setImagenEntrada(thumbnail);
            } catch (final IOException e) {
                e.printStackTrace();
            }

        }

        return listaEntradasSimples;
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosMasVistos() {
        final List<LibroEntity> listaLibros = this.libroRepository.findLibrosMasVistos(new PageRequest(0, 5));
        final List<LibroSimpleDTO> listaLibrosSimples = ConversionUtils.obtenerLibrosBasicos(listaLibros);
        return listaLibrosSimples;
    }

}
