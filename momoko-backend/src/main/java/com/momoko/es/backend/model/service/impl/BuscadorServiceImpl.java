/**
 * BuscadorServiceImpl.java 20-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.backend.model.entity.CategoriaEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.repository.CategoriaRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.service.BuscadorService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class BuscadorServiceImpl implements BuscadorService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private GeneroRepository generoRepository;

    @Autowired(required = false)
    private CategoriaRepository categoriaRepository;

    @Autowired(required = false)
    private EtiquetaRepository etiquetaRepository;

    @Override
    public List<LibroDTO> buscarLibros(final List<String> librosUrls) {

        final List<LibroEntity> entidadesLibros = this.libroRepository.findByUrlLibroIn(librosUrls);
        return EntityToDTOAdapter.adaptarLibros(entidadesLibros);
    }

    @Override
    public List<EntradaSimpleDTO> buscarEntradas(final List<String> entradasUrls) {
        final List<EntradaEntity> entidadesEntradas = this.entradaRepository.findByUrlEntradaIn(entradasUrls);
        return ConversionUtils.obtenerEntradasBasicas(entidadesEntradas, false);
    }

    @Override
    public List<GeneroDTO> buscarGeneros(final List<String> generosUrls) {
        final List<GeneroEntity> entidadesGeneros = this.generoRepository.findByUrlGeneroIn(generosUrls);
        return EntityToDTOAdapter.adaptarGeneros(entidadesGeneros);
    }

    @Override
    public List<CategoriaDTO> buscarCategorias(final List<String> categoriasUrls) {
        final List<CategoriaEntity> entidadesCategorias = this.categoriaRepository.findByUrlCategoriaIn(categoriasUrls);
        return EntityToDTOAdapter.adaptarCategorias(entidadesCategorias);
    }

    @Override
    public List<EtiquetaDTO> buscarEtiquetas(final List<String> etiquetasUrls) {
        final List<EtiquetaEntity> entidadesEntradas = this.etiquetaRepository.findByEtiquetaUrlIn(etiquetasUrls);
        return EntityToDTOAdapter.adaptarEtiquetas(entidadesEntradas);
    }

}
