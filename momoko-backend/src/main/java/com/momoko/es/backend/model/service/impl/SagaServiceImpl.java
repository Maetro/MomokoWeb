/**
 * SagaServiceImpl.java 01-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConID;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConUrl;
import com.momoko.es.api.exceptions.NoSeEncuentranLibrosSagaException;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.SagaEntity;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.repository.SagaRepository;
import com.momoko.es.backend.model.service.SagaService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class SagaServiceImpl.
 */
@Service
public class SagaServiceImpl implements SagaService {

    @Autowired(required = false)
    private SagaRepository sagaRepository;

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Override
    public List<SagaDTO> recuperarSagas() {
        final List<SagaDTO> listaSagas = new ArrayList<SagaDTO>();
        final Iterable<SagaEntity> sagaEntityIterable = this.sagaRepository.findAll();
        final String urlImageServer = this.almacenImagenes.getUrlImageServer();
        for (final SagaEntity sagaEntity : sagaEntityIterable) {

            final SagaDTO sagaDTO = EntityToDTOAdapter.adaptarSaga(sagaEntity, true);

            // Nota Momoko del libro
            final PuntuacionEntity puntuacionEntity = this.puntuacionRepository.findOneByEsPuntuacionMomokoAndSaga(true,
                    sagaEntity);
            if (puntuacionEntity != null) {
                sagaDTO.setNotaSaga(puntuacionEntity.getValor().intValue());
            }
            if (sagaDTO.getImagenSaga() != null) {
                sagaDTO.setImagenSaga(urlImageServer + sagaDTO.getImagenSaga());

            }
            sagaDTO.setLibrosString(ConversionUtils.join(sagaDTO.getLibrosSaga()));
            listaSagas.add(sagaDTO);
        }

        return listaSagas;
    }

    @Override
    public SagaDTO guardarSaga(final SagaDTO sagaAGuardar)
            throws NoSeEncuentranLibrosSagaException, NoSeEncuentraElementoConID {
        SagaEntity sagaEntity = null;

        if (sagaAGuardar.getSagaId() != null) {
            sagaEntity = actualizarSaga(sagaAGuardar);

        } else {
            sagaEntity = crearSaga(sagaAGuardar);
        }

        return EntityToDTOAdapter.adaptarSaga(sagaEntity, true);
    }

    private SagaEntity crearSaga(final SagaDTO sagaAGuardar) throws NoSeEncuentranLibrosSagaException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        final SagaEntity sagaEntity = DTOToEntityAdapter.adaptarSaga(sagaAGuardar);
        sagaEntity.setFechaAlta(Calendar.getInstance().getTime());
        sagaEntity.setUsuarioAlta(currentPrincipalName);
        sagaEntity.setImagenSaga(soloNombreImagenASagas(sagaEntity.getImagenSaga()));
        final List<LibroEntity> librosSaga = this.libroRepository.findByUrlLibroIn(sagaAGuardar.getLibrosSaga());
        final Map<String, LibroEntity> mapaOrdenLibros = new HashMap<>();
        if (CollectionUtils.isNotEmpty(librosSaga)) {
            for (final LibroEntity libroEntity : librosSaga) {
                mapaOrdenLibros.put(libroEntity.getUrlLibro(), libroEntity);
            }
            int orden = 1;
            for (final String urlLibro : sagaAGuardar.getLibrosSaga()) {
                final LibroEntity libroNumero = mapaOrdenLibros.get(urlLibro);
                if (libroNumero != null) {
                    libroNumero.setOrdenSaga(orden);
                    orden++;
                }
                sagaEntity.addLibro(libroNumero);
            }

        } else {
            throw new NoSeEncuentranLibrosSagaException("Libros: " + sagaAGuardar.getLibrosSaga());
        }

        return this.sagaRepository.save(sagaEntity);
    }

    private SagaEntity actualizarSaga(final SagaDTO sagaAGuardar)
            throws NoSeEncuentraElementoConID, NoSeEncuentranLibrosSagaException {
        final SagaEntity sagaEntity = this.sagaRepository.findOne(sagaAGuardar.getSagaId());
        if (sagaEntity == null) {
            throw new NoSeEncuentraElementoConID("Saga ID: " + sagaAGuardar.getSagaId());
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final List<LibroEntity> librosSaga = this.libroRepository.findByUrlLibroIn(sagaAGuardar.getLibrosSaga());
        sagaEntity.getLibros().clear();
        final Map<String, LibroEntity> mapaOrdenLibros = new HashMap<>();
        if (CollectionUtils.isNotEmpty(librosSaga)) {
            for (final LibroEntity libroEntity : librosSaga) {
                mapaOrdenLibros.put(libroEntity.getUrlLibro(), libroEntity);
            }
            int orden = 1;
            for (final String urlLibro : sagaAGuardar.getLibrosSaga()) {
                final LibroEntity libroNumero = mapaOrdenLibros.get(urlLibro);
                if (libroNumero != null) {
                    libroNumero.setOrdenSaga(orden);
                    orden++;
                }
                sagaEntity.addLibro(libroNumero);
            }

        } else {
            throw new NoSeEncuentranLibrosSagaException("Libros: " + sagaAGuardar.getLibrosSaga());
        }

        sagaEntity.setNombre(sagaAGuardar.getNombreSaga());
        sagaEntity.setUrlSaga(sagaAGuardar.getUrlSaga());
        sagaEntity.setImagenSaga(soloNombreImagenASagas(sagaAGuardar.getImagenSaga()));
        sagaEntity.setResumen(sagaAGuardar.getResumen());
        sagaEntity.setNumeroVolumenes(sagaAGuardar.getNumeroVolumenes());
        sagaEntity.setEstaTerminada(sagaAGuardar.getEstaTerminada());
        sagaEntity.setTipoSaga(sagaAGuardar.getTipoSaga());
        sagaEntity.setDominaLibros(sagaAGuardar.getDominaLibros());
        sagaEntity.setFechaModificacion(Calendar.getInstance().getTime());
        sagaEntity.setUsuarioModificacion(currentPrincipalName);
        sagaEntity.setEstaTerminada(sagaAGuardar.getEstaTerminada());
        sagaEntity.setDominaLibros(sagaAGuardar.getDominaLibros());
        sagaEntity.setTipoSaga(sagaAGuardar.getTipoSaga());

        return this.sagaRepository.save(sagaEntity);
    }

    private String soloNombreImagenASagas(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return "sagas/" + lista[elementos - 1];
    }

    @Override
    public SagaDTO obtenerSaga(final String urlSaga) throws NoSeEncuentraElementoConUrl {
        final SagaEntity sagaEntity = this.sagaRepository.findOneByUrlSaga(urlSaga);
        if (sagaEntity == null) {
            throw new NoSeEncuentraElementoConUrl("Saga URL: " + urlSaga);
        }
        final SagaDTO sagaDTO = EntityToDTOAdapter.adaptarSaga(sagaEntity, true);
        try {
            sagaDTO.setImagenSaga(this.almacenImagenes.obtenerMiniatura(sagaDTO.getImagenSaga(), 200, 200, true));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return sagaDTO;
    }

    @Override
    public List<String> obtenerListaUrlsLibros(final Integer sagaId) {
        final List<String> urlsLibros = this.sagaRepository.findUrlsLibrosSaga(sagaId);
        return urlsLibros;
    }

    @Override
    public BigDecimal obtenerPuntucionMomokoSaga(final String urlSaga) {
        return this.puntuacionRepository.findOneByEsPuntuacionMomokoAndSagaUrl(urlSaga);
    }

    @Override
    public List<String> getNombresSagas() {
        return this.sagaRepository.findAllNombresSagas();
    }

}
