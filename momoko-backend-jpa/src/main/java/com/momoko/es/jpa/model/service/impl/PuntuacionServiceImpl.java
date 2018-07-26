/**
 * PuntuacionServiceImpl.java 02-nov-2017
 *
 */
package com.momoko.es.jpa.model.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.PuntuacionEntity;
import com.momoko.es.jpa.model.entity.SagaEntity;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.PuntuacionRepository;
import com.momoko.es.jpa.model.repository.SagaRepository;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.service.PuntuacionService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;

/**
 * The Class PuntuacionServiceImpl.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Service
public class PuntuacionServiceImpl implements PuntuacionService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private SagaRepository sagaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Override
    public PuntuacionDTO guardarPuntuacion(final PuntuacionDTO puntuacionDTO) throws Exception {
        PuntuacionEntity respuesta;
        LibroEntity libro;
        SagaEntity saga;
        if (puntuacionDTO.getLibroId() != null) {
            libro = this.libroRepository.findById(puntuacionDTO.getLibroId()).orElse(null);
            respuesta = guardarPuntuacionLibro(puntuacionDTO, libro);

        } else {

            saga = this.sagaRepository.findById(puntuacionDTO.getSagaId()).orElse(null);
            respuesta = guardarPuntuacionSaga(puntuacionDTO, saga);
        }

        return EntityToDTOAdapter.adaptarPuntuacion(respuesta);
    }

    /**
     * Guardar puntuacion libro.
     *
     * @param puntuacionDTO
     *            the puntuacion dto
     * @param libro
     *            the libro
     * @return the puntuacion entity
     */
    public PuntuacionEntity guardarPuntuacionLibro(final PuntuacionDTO puntuacionDTO, final LibroEntity libro) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        puntuacionDTO.setAutor(ConversionUtils.obtenerUsuarioBasico(autor));
        final PuntuacionEntity viejaPuntuacion = this.puntuacionRepository.findOnePuntuacionEntityByLibroAndAutor(libro,
                autor);
        PuntuacionEntity respuesta = null;
        if (viejaPuntuacion == null) {
            // Nueva puntuacion
            final PuntuacionEntity puntuacionEntity = DTOToEntityAdapter.adaptarPuntuacion(puntuacionDTO, autor, libro);

            if (puntuacionEntity.getPuntuacionId() != null) {
                throw new URLEntradaYaExisteException("La puntuacion ya esta creada");
            }

            puntuacionEntity.setFechaAlta(Calendar.getInstance().getTime());
            puntuacionEntity.setUsuarioAlta(currentPrincipalName);
            respuesta = this.puntuacionRepository.save(puntuacionEntity);
        } else {
            // Actualizar puntuacion
            viejaPuntuacion.setUsuarioModificacion(currentPrincipalName);
            viejaPuntuacion.setFechaModificacion(Calendar.getInstance().getTime());
            viejaPuntuacion.setComentario(puntuacionDTO.getComentario());
            viejaPuntuacion.setValor(puntuacionDTO.getValor());
            respuesta = this.puntuacionRepository.save(viejaPuntuacion);
        }
        return respuesta;
    }

    /**
     * Guardar puntuacion libro.
     *
     * @param puntuacionDTO
     *            the puntuacion dto
     * @param libro
     *            the libro
     * @return the puntuacion entity
     */
    public PuntuacionEntity guardarPuntuacionSaga(final PuntuacionDTO puntuacionDTO, final SagaEntity saga) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        puntuacionDTO.setAutor(ConversionUtils.obtenerUsuarioBasico(autor));
        final PuntuacionEntity viejaPuntuacion = this.puntuacionRepository.findOnePuntuacionEntityBySagaAndAutor(saga,
                autor);
        PuntuacionEntity respuesta = null;
        if (viejaPuntuacion == null) {
            // Nueva puntuacion
            final PuntuacionEntity puntuacionEntity = DTOToEntityAdapter.adaptarPuntuacion(puntuacionDTO, autor, saga);

            if (puntuacionEntity.getPuntuacionId() != null) {
                throw new URLEntradaYaExisteException("La puntuacion ya esta creada");
            }

            puntuacionEntity.setFechaAlta(Calendar.getInstance().getTime());
            puntuacionEntity.setUsuarioAlta(currentPrincipalName);
            respuesta = this.puntuacionRepository.save(puntuacionEntity);
        } else {
            // Actualizar puntuacion
            viejaPuntuacion.setUsuarioModificacion(currentPrincipalName);
            viejaPuntuacion.setFechaModificacion(Calendar.getInstance().getTime());
            viejaPuntuacion.setComentario(puntuacionDTO.getComentario());
            viejaPuntuacion.setValor(puntuacionDTO.getValor());
            respuesta = this.puntuacionRepository.save(viejaPuntuacion);
        }
        return respuesta;
    }

}
