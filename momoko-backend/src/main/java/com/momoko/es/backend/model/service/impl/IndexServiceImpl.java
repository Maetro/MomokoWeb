/**
 * IndexServiceImpl.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.service.GeneroService;
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

    @Autowired(required = false)
    private GeneroService generoService;

    @Override
    public List<EntradaSimpleDTO> obtenerUltimasEntradas() {
        final List<EntradaEntity> listaEntities = this.entradaRepository.findUltimasEntradas(new PageRequest(0, 10));
        final List<EntradaSimpleDTO> listaEntradasSimples = ConversionUtils.obtenerEntradasBasicas(listaEntities);

        for (int i = 0; i < 5; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
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
        for (int i = 5; i < 7; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 900, 650, true);
                    entradaSimpleDTO.setImagenEntrada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 7; i < 10; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 520, 384, true);
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

    @Override
    public List<MenuDTO> obtenerMenu() {
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        Collections.sort(categorias);
        final List<MenuDTO> menu = new ArrayList<MenuDTO>();
        for (final CategoriaDTO categoria : categorias) {
            final MenuDTO menuPart = new MenuDTO();
            menuPart.setNombre(categoria.getNombreCategoria());
            menuPart.setUrl(categoria.getUrlCategoria());
            menuPart.setOrden(categoria.getOrden());
            final List<GeneroDTO> generosCategoria = new ArrayList<GeneroDTO>();
            for (final GeneroDTO generoDTO : generos) {
                if (generoDTO.getCategoria().equals(categoria)) {
                    generosCategoria.add(generoDTO);
                }
            }
            Collections.sort(generosCategoria);
            menuPart.setGeneros(generosCategoria);
            menu.add(menuPart);
        }
        return menu;
    }

    @Override
    public LibroEntradaSimpleDTO obtenerUltimoComicAnalizado() {
        final LibroEntradaSimpleDTO libroEntradaSimpleDTO = new LibroEntradaSimpleDTO();
        final CategoriaDTO categoria = this.generoService.obtenerCategoriaPorUrl("comics-novelas-graficas");
        final List<GeneroDTO> generos = this.generoService.obtenerGenerosCategoria(categoria);
        final List<Integer> idsGeneros = new ArrayList<Integer>();
        for (final GeneroDTO generoDTO : generos) {
            idsGeneros.add(generoDTO.getGeneroId());
        }

        final EntradaEntity ultimoComicAnalisis = this.entradaRepository
                .findEntradaAnalisisLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(idsGeneros,
                        new PageRequest(0, 1))
                .iterator().next();

        final LibroEntity ultimoComicAnalizado = ultimoComicAnalisis.getLibrosEntrada().iterator().next();

        final PuntuacionEntity puntuacion = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, Arrays.asList(ultimoComicAnalizado.getLibroId()))
                .iterator().next();

        final LibroSimpleDTO libroSimpleDTO = ConversionUtils.obtenerLibroSimpleDTO(ultimoComicAnalizado, puntuacion);

        if (libroSimpleDTO.getPortada() != null) {
            try {

                final String thumbnail = this.storageService.obtenerMiniatura("portadas", libroSimpleDTO.getPortada(),
                        296, 405, false);
                libroSimpleDTO.setPortada(thumbnail);

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        libroEntradaSimpleDTO.setEntrada(ConversionUtils.obtenerEntradaSimpleDTO(ultimoComicAnalisis));
        libroEntradaSimpleDTO.setLibro(libroSimpleDTO);
        return libroEntradaSimpleDTO;
    }

    @Override
    public List<LibroSimpleDTO> obtenerUltimosAnalisis() {
        final List<LibroEntity> listaLibros = this.libroRepository.findUltimosAnalisis(new PageRequest(0, 5));
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