/**
 * PuntuacionServiceImpl.java 02-nov-2017
 *
 */
package com.momoko.es.backend.model.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.service.PuntuacionService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

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
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Override
    public PuntuacionDTO guardarPuntuacion(final PuntuacionDTO puntuacionDTO) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        final LibroEntity libro = this.libroRepository.findOne(puntuacionDTO.getLibroId());
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

        return EntityToDTOAdapter.adaptarPuntuacion(respuesta);
    }

}