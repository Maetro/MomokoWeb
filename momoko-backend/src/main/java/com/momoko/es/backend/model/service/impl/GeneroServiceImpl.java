/**
 * GeneroServiceImpl.java 26-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.genre.GenrePageResponse;
import com.momoko.es.api.enums.OrderType;
import com.momoko.es.backend.model.entity.CategoriaEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.GenreEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.SagaEntity;
import com.momoko.es.backend.model.repository.CategoriaRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.GeneroRepository;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.GenreService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;
import com.momoko.es.util.MomokoUtils;

/**
 * The Class GeneroServiceImpl.
 */
@Service
public class GeneroServiceImpl implements GenreService {

    @Autowired(required = false)
    private GeneroRepository genreRepository;

    @Autowired(required = false)
    private CategoriaRepository categoriaRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private EntradaService entradaService;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired(required = false)
    private LibroService libroService;

    @Override
    public GenrePageResponse getGenrePage(final String genreUrl, final Integer pageNumber, final OrderType tipoOrden) {
        final GenrePageResponse genrePageResponse = new GenrePageResponse();
        final GenreDTO generoDTO = this.obtenerGeneroPorUrl(genreUrl);
        List<LibroSimpleDTO> genreBooksAndSagas = new ArrayList<>();
        if (OrderType.DATE.equals(tipoOrden)) {
            genreBooksAndSagas = this.obtenerLibrosConAnalisisGeneroPorFecha(generoDTO, 9, pageNumber);
        } else {
            genreBooksAndSagas = this.obtenerLibrosConAnalisisGeneroPorNota(generoDTO, 9, pageNumber);
        }
        final Integer numeroLibros = this.libroService.obtenerNumeroLibrosConAnalisisGenero(generoDTO);
        for (final LibroSimpleDTO libroSimpleDTO : genreBooksAndSagas) {
            if (libroSimpleDTO.getPortada() != null) {
                try {
                    libroSimpleDTO.setPortada(
                            this.almacenImagenes.obtenerMiniatura(libroSimpleDTO.getPortada(), 240, 350, false));

                    final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes
                            .getImageDimensions(libroSimpleDTO.getPortada());
                    libroSimpleDTO.setPortadaHeight(alturaAnchura.getAltura());
                    libroSimpleDTO.setPortadaWidth(alturaAnchura.getAnchura());

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        final List<EntradaSimpleDTO> tresUltimasEntradasConLibro = this.entradaService
                .obtenerTresUltimasEntradasPopularesConLibro();

        genrePageResponse.setGenero(generoDTO);
        genrePageResponse.setNumeroLibros(numeroLibros);
        genrePageResponse.setNueveLibrosGenero(genreBooksAndSagas);
        genrePageResponse.setTresUltimasEntradasConLibro(tresUltimasEntradasConLibro);
        return genrePageResponse;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        final List<GenreDTO> listaGeneros = new ArrayList<GenreDTO>();
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        final Iterable<GenreEntity> generoEntityIterable = this.genreRepository.findAll();
        for (final GenreEntity generoEntity : generoEntityIterable) {
            final GenreDTO generoDTO = EntityToDTOAdapter.adaptarGenero(generoEntity);
            generoDTO.setImagenCabeceraGenero(imageServer + generoDTO.getImagenCabeceraGenero());
            generoDTO.setIconoGenero(imageServer + generoDTO.getIconoGenero());
            listaGeneros.add(generoDTO);

        }

        return listaGeneros;
    }

    @Override
    public GenreDTO saveGenre(final GenreDTO generoDTO) throws Exception {

        GenreEntity generoEntity = DTOToEntityAdapter.adaptarGenero(generoDTO);
        // Comprobamos si el autor existe.
        final List<GenreEntity> coincidencias = this.genreRepository.findByNombre(generoDTO.getNombre());
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
                final GenreEntity generoBD = this.genreRepository.findOne(generoEntity.getGeneroId());
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
            return EntityToDTOAdapter.adaptarGenero(this.genreRepository.save(generoEntity));
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
    public GenreDTO obtenerGeneroPorUrl(final String urlGenero) {
        final GenreEntity generoEntity = this.genreRepository.findOneByUrlGeneroAndFechaBajaIsNull(urlGenero);
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        final GenreDTO generoDTO = EntityToDTOAdapter.adaptarGenero(generoEntity);
        generoDTO.setImagenCabeceraGenero(imageServer + generoDTO.getImagenCabeceraGenero());
        generoDTO.setIconoGenero(imageServer + generoDTO.getIconoGenero());
        return generoDTO;
    }

    @Override
    public List<CategoriaDTO> obtenerListaCategorias() {
        return EntityToDTOAdapter.adaptarCategorias(this.categoriaRepository.findAll());
    }

    @Override
    public List<GenreDTO> obtenerGenerosCategoria(final CategoriaDTO categoriaDTO) {
        final List<GenreEntity> generos = this.genreRepository
                .findByCategoriaUrlCategoriaAndFechaBajaIsNull(categoriaDTO.getUrlCategoria());
        return EntityToDTOAdapter.adaptarGeneros(generos);
    }

    @Override
    @Transactional
    public List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(final GenreDTO generoDTO, final int numElements,
            final Integer numeroPagina) {
        final List<LibroSimpleDTO> resultado = new ArrayList<>();
        final Integer initElement = numElements * numeroPagina;
        final Integer endElement = initElement + numElements;

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .obtenerAnalisisSagasYLibrosPorGeneroYFecha(generoDTO.getUrlGenero(), initElement, endElement);

        for (final EntradaEntity entradaEntity : listaEntities) {
            if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
                final LibroEntity libro = entradaEntity.getLibrosEntrada().iterator().next();
                PuntuacionEntity puntuacion = null;
                if (CollectionUtils.isNotEmpty(libro.getPuntuaciones())) {
                    puntuacion = libro.getPuntuaciones().iterator().next();
                }
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, puntuacion);
                libroSimple.setRepresentaSaga(false);
                libroSimple.setUrlSaga(null);
                libroSimple.setNombreSaga(null);
                resultado.add(libroSimple);
            } else {
                final SagaEntity saga = entradaEntity.getSagasEntrada().iterator().next();
                final LibroEntity libro = saga.getLibros().iterator().next();
                PuntuacionEntity puntuacion = null;
                if (CollectionUtils.isNotEmpty(saga.getPuntuaciones())) {
                    puntuacion = saga.getPuntuaciones().iterator().next();
                }
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, puntuacion);
                libroSimple.setRepresentaSaga(true);
                libroSimple.setUrlSaga(saga.getUrlSaga());
                libroSimple.setNombreSaga(saga.getNombre());
                resultado.add(libroSimple);
            }
        }
        return resultado;

    }

    @Override
    @Transactional
    public List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorNota(final GenreDTO generoDTO, final int numElements,
            final Integer numeroPagina) {
        final List<LibroSimpleDTO> resultado = new ArrayList<>();
        final Integer initElement = numElements * numeroPagina;
        final Integer endElement = initElement + numElements;

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .obtenerAnalisisSagasYlibrosPorGeneroYNota(generoDTO.getUrlGenero(), initElement, endElement);

        for (final EntradaEntity entradaEntity : listaEntities) {
            if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
                final LibroEntity libro = entradaEntity.getLibrosEntrada().iterator().next();
                PuntuacionEntity puntuacion = null;
                if (CollectionUtils.isNotEmpty(libro.getPuntuaciones())) {
                    puntuacion = libro.getPuntuaciones().iterator().next();
                }
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, puntuacion);
                libroSimple.setRepresentaSaga(false);
                libroSimple.setUrlSaga(null);
                libroSimple.setNombreSaga(null);
                resultado.add(libroSimple);
            } else {
                final SagaEntity saga = entradaEntity.getSagasEntrada().iterator().next();
                final LibroEntity libro = saga.getLibros().iterator().next();
                PuntuacionEntity puntuacion = null;
                if (CollectionUtils.isNotEmpty(saga.getPuntuaciones())) {
                    puntuacion = saga.getPuntuaciones().iterator().next();
                }
                final LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libro, puntuacion);
                libroSimple.setRepresentaSaga(true);
                libroSimple.setUrlSaga(saga.getUrlSaga());
                libroSimple.setNombreSaga(saga.getNombre());
                resultado.add(libroSimple);
            }
        }
        return resultado;

    }

}
