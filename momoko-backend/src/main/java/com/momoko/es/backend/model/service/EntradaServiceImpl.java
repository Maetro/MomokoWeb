/**
 * EntradaServiceImpl.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class EntradaServiceImpl implements EntradaService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private EtiquetaRepository etiquetaRepository;

    @Override
    public List<EntradaDTO> recuperarEntradas() {
        final List<EntradaDTO> entradasDTO = new ArrayList<EntradaDTO>();
        final Iterable<EntradaEntity> entradasEntity = this.entradaRepository.findAll();
        for (final EntradaEntity entradaEntity : entradasEntity) {
            entradasDTO.add(EntityToDTOAdapter.adaptarEntrada(entradaEntity));
        }
        return entradasDTO;
    }

    @Override
    @Transactional
    public EntradaDTO guardarEntrada(final EntradaDTO entradaAGuardar) throws Exception {
        LibroDTO libroEntrada = null;
        if (StringUtils.isNotBlank(entradaAGuardar.getLibroEntrada())) {
            libroEntrada = EntityToDTOAdapter
                    .adaptarLibro(this.libroRepository.findOneByTitulo(entradaAGuardar.getLibroEntrada()));
        }
        if (entradaAGuardar.getEntradaId() == null) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();
            entradaAGuardar.setAutor(
                    EntityToDTOAdapter.adaptarUsuario(this.usuarioRepository.findByUsuarioEmail(currentPrincipalName)));
        }
        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, libroEntrada);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<EtiquetaEntity>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                EtiquetaEntity etiquetaBD = this.etiquetaRepository.findOneByNombre(etiqueta.getNombre());
                if (etiquetaBD == null) {
                    etiquetaBD = this.etiquetaRepository.save(etiqueta);
                }
                etiquetasBD.add(etiquetaBD);
            }
            entradaEntity.setEtiquetas(etiquetasBD);
        }

        final boolean esNuevaEntrada = entradaAGuardar.getEntradaId() == null;
        // Comprobamos si la url de la entrada existe.
        final EntradaEntity coincidencia = this.entradaRepository.findFirstByUrlEntrada(entradaEntity.getUrlEntrada());
        if (esNuevaEntrada && (coincidencia != null)) {
            throw new URLEntradaYaExisteException("La entrada es nueva y se esta intentando utilizar una url ya usada");
        }
        if (!esNuevaEntrada && (coincidencia == null)) {
            throw new NoSeEncuentraEntradaException("No se encuentra la entrada");
        }
        if (esNuevaEntrada) {
            entradaEntity = crearNuevaEntrada(entradaEntity);
        } else {
            entradaEntity = actualizarEntrada(entradaEntity, coincidencia);
        }
        return EntityToDTOAdapter.adaptarEntrada(entradaEntity);
    }

    private EntradaEntity actualizarEntrada(final EntradaEntity entradaEntity, final EntradaEntity viejaEntrada) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(viejaEntrada.getFechaAlta());
        entradaEntity.setFechaModificacion(Calendar.getInstance().getTime());
        entradaEntity.setFechaBaja(viejaEntrada.getFechaBaja());
        entradaEntity.setUsuarioAlta(viejaEntrada.getUsuarioAlta());
        entradaEntity.setUsuarioModificacion(currentPrincipalName);
        entradaEntity.setUsuarioBaja(viejaEntrada.getUsuarioBaja());
        final UsuarioEntity usuario = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        entradaEntity.setEntradaAutor(usuario);

        if (entradaEntity.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }
        // TODO: RAMON: Implementar
        entradaEntity.setNumeroComentarios(0);
        this.entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private EntradaEntity crearNuevaEntrada(final EntradaEntity entradaEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
        entradaEntity.setUsuarioAlta(currentPrincipalName);

        if (entradaEntity.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }
        // TODO: RAMON: Implementar
        entradaEntity.setNumeroComentarios(0);
        this.entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private LibroEntity obtenerLibroEntrada(final LibroEntity libroABuscar) {

        LibroEntity libroEncontrado = null;
        if (libroABuscar != null) {
            libroEncontrado = this.libroRepository.findOne(libroABuscar.getLibroId());
        }
        return libroEncontrado;

    }

}
