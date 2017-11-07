/**
 * EntradaServiceImpl.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.api.response.ObtenerEntradaResponse;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class EntradaServiceImpl.
 */
@Service
public class EntradaServiceImpl implements EntradaService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private LibroService libroService;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private EtiquetaRepository etiquetaRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public ObtenerEntradaResponse obtenerEntrada(final String urlEntrada) {
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();
        final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                .findByLibroEntrada(entradaEntity.getLibroEntrada());
        try {
            entradaEntity.setImagenDestacada(
                    this.almacenImagenes.obtenerMiniatura(entradaEntity.getImagenDestacada(), 770, 432));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        Collections.sort(entradasRelacionadas);
        if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
            for (final EntradaEntity entradaRelacionadaEntity : entradasRelacionadas) {
                final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                listaDatosEntradas.add(datoEntrada);
            }
        }
        final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
        entradaDTO.getLibroEntrada().setEntradasLibro(listaDatosEntradas);
        respuesta.setEntrada(entradaDTO);
        respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(entradaDTO.getLibroEntrada(), 5));
        return respuesta;
    }

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
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        if (entradaAGuardar.getLibroEntrada() != null) {
            libroEntrada = EntityToDTOAdapter
                    .adaptarLibro(this.libroRepository.findOneByTitulo(entradaAGuardar.getTituloLibroEntrada()));
        }
        UsuarioEntity autor = null;
        if (entradaAGuardar.getEntradaId() == null) {
            autor = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        } else {
            autor = this.usuarioRepository.findByUsuarioLogin(entradaAGuardar.getAutor().getNombre());
        }

        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, libroEntrada, autor);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<EtiquetaEntity>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                List<String> etiquetas = new ArrayList<String>();
                if (StringUtils.contains(etiqueta.getNombre(), ",")) {
                    etiquetas = ConversionUtils.divide(etiqueta.getNombre());
                } else {
                    etiquetas.add(etiqueta.getNombre());
                }
                for (final String nombreEtiqueta : etiquetas) {
                    EtiquetaEntity etiquetaBD = this.etiquetaRepository.findOneByNombre(nombreEtiqueta.trim());
                    if (etiquetaBD == null) {
                        final EtiquetaEntity nuevaEtiqueta = new EtiquetaEntity();
                        nuevaEtiqueta.setNombre(nombreEtiqueta.trim());
                        nuevaEtiqueta.setFechaAlta(Calendar.getInstance().getTime());
                        nuevaEtiqueta.setUsuarioAlta(currentPrincipalName);
                        etiquetaBD = this.etiquetaRepository.save(nuevaEtiqueta);
                    }
                    etiquetasBD.add(etiquetaBD);
                }

            }
            entradaEntity.setEtiquetas(etiquetasBD);
        }

        final boolean esNuevaEntrada = entradaAGuardar.getEntradaId() == null;
        // Comprobamos si la url de la entrada existe.
        EntradaEntity coincidencia;
        if (esNuevaEntrada) {
            coincidencia = this.entradaRepository.findFirstByUrlEntrada(entradaEntity.getUrlEntrada());
        } else {
            coincidencia = this.entradaRepository.findOne(entradaAGuardar.getEntradaId());
        }

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
            viejaEntrada.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        } else {
            viejaEntrada.setLibroEntrada(null);
        }

        entradaEntity.setUsuarioModificacion(currentPrincipalName);
        entradaEntity.setFechaModificacion(Calendar.getInstance().getTime());
        final UsuarioEntity usuario = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);

        if (viejaEntrada.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            viejaEntrada.setPadreEntrada(padre);
        } else {
            viejaEntrada.setPadreEntrada(null);
        }

        viejaEntrada.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        viejaEntrada.setEntradaId(entradaEntity.getEntradaId());
        viejaEntrada.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        viejaEntrada.setEstadoEntrada(entradaEntity.getEstadoEntrada());

        viejaEntrada.setPermitirComentarios(entradaEntity.getPermitirComentarios());
        viejaEntrada.setResumenEntrada(entradaEntity.getResumenEntrada());
        viejaEntrada.setTipoEntrada(entradaEntity.getTipoEntrada());
        viejaEntrada.setTituloEntrada(entradaEntity.getTituloEntrada());
        viejaEntrada.setUrlEntrada(entradaEntity.getUrlEntrada());
        viejaEntrada.setEtiquetas(entradaEntity.getEtiquetas());
        viejaEntrada.setImagenDestacada(entradaEntity.getImagenDestacada());
        this.entradaRepository.save(viejaEntrada);
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
        if (entradaEntity.getPermitirComentarios() == null) {
            entradaEntity.setPermitirComentarios(true);
        }
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
