/**
 * ComentarioServiceImpl.java 12-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.backend.model.entity.ComentarioEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.ComentarioRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private ComentarioRepository comentarioRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    @Transactional
    public ComentarioDTO guardarComentario(final NuevoComentarioRequest comentarioAGuardar) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final ComentarioEntity nuevoComentario = new ComentarioEntity();
        nuevoComentario.setEsBan(false);
        nuevoComentario.setEsSpoiler(false);
        nuevoComentario.setUsuarioAlta(comentarioAGuardar.getEmail());
        nuevoComentario.setFechaAlta(Calendar.getInstance().getTime());
        nuevoComentario.setTextoComentario(comentarioAGuardar.getContenido());
        // TODO: Arregla esto
        if (!authentication.getName().equals("anonymousUser")) {
            final String currentPrincipalName = authentication.getName();
            final UsuarioEntity usuario = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
            nuevoComentario.setNombreComentario(usuario.getUsuarioNick());
            nuevoComentario.setEmailComentario(currentPrincipalName);
            nuevoComentario.setPaginaWebComentario(usuario.getPaginaWeb());
        } else {
            nuevoComentario.setNombreComentario(comentarioAGuardar.getNombre());
            nuevoComentario.setEmailComentario(comentarioAGuardar.getEmail());
            nuevoComentario.setPaginaWebComentario(comentarioAGuardar.getPaginaWeb());
        }
        final EntradaEntity comentarioEntrada = this.entradaRepository.findOne(comentarioAGuardar.getEntradaId());
        nuevoComentario.setEntrada(comentarioEntrada);
        ComentarioEntity comentarioReferenciaEntity = null;

        if (comentarioAGuardar.getComentarioRespuesta() != null) {
            comentarioReferenciaEntity = this.comentarioRepository.findOne(comentarioAGuardar.getComentarioRespuesta());
            nuevoComentario.setComentarioReferenciaEntity(comentarioReferenciaEntity);
        }

        nuevoComentario.setVotosNegativos("");
        nuevoComentario.setVotosPositivos("");

        final ComentarioEntity respuesta = this.comentarioRepository.save(nuevoComentario);

        final String emailComentario = respuesta.getEmailComentario();
        final UsuarioEntity usuarioComentario = this.usuarioRepository.findByUsuarioEmail(emailComentario);
        UsuarioBasicoDTO usuarioBasico = null;
        if (usuarioComentario != null) {
            usuarioBasico = ConversionUtils.obtenerUsuarioBasico(usuarioComentario);
            usuarioBasico.setNombre(respuesta.getNombreComentario());
            if (usuarioBasico.getAvatar() == null) {
                usuarioBasico.setAvatar(ConversionUtils.obtenerGravatar(respuesta.getEmailComentario()));
            }
        } else {
            usuarioBasico = new UsuarioBasicoDTO();
            usuarioBasico.setCargo("Invitado");
            usuarioBasico.setNombre(respuesta.getNombreComentario());
            usuarioBasico.setAvatar(ConversionUtils.obtenerGravatar(respuesta.getEmailComentario()));
        }

        return EntityToDTOAdapter.adaptarComentario(respuesta, usuarioBasico);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosEntrada(final Integer entradaId) {
        final List<ComentarioDTO> comentariosEntrada = new ArrayList<ComentarioDTO>();
        final List<ComentarioEntity> comentariosEntity = this.comentarioRepository.findByEntradaEntradaId(entradaId);
        final Set<String> emailsComentarios = new HashSet<String>();
        for (final ComentarioEntity comentario : comentariosEntity) {
            emailsComentarios.add(comentario.getEmailComentario());
        }
        final List<UsuarioEntity> usuarios = this.usuarioRepository
                .findAllByUsuarioEmailIn(new ArrayList<String>(emailsComentarios));

        final Map<String, UsuarioEntity> mapaUsuariosPorEmail = ConversionUtils.crearMapaDeListaPorValor(usuarios,
                "usuarioEmail", String.class, UsuarioEntity.class);

        for (final ComentarioEntity comentarioEntity : comentariosEntity) {
            final String emailComentario = comentarioEntity.getEmailComentario();
            final UsuarioEntity usuarioComentario = mapaUsuariosPorEmail.get(emailComentario);
            UsuarioBasicoDTO usuarioBasico = null;
            if (usuarioComentario != null) {
                usuarioBasico = ConversionUtils.obtenerUsuarioBasico(usuarioComentario);
                usuarioBasico.setCargo("Miembro");
                usuarioBasico.setNombre(comentarioEntity.getNombreComentario());
                if (usuarioBasico.getAvatar() == null) {
                    usuarioBasico.setAvatar(ConversionUtils.obtenerGravatar(comentarioEntity.getEmailComentario()));
                } else {
                    try {
                        usuarioBasico.setAvatar(
                                this.almacenImagenes.obtenerMiniatura(usuarioBasico.getAvatar(), 120, 120, true));
                    } catch (final IOException e) {
                        usuarioBasico.setAvatar(ConversionUtils.obtenerGravatar(comentarioEntity.getEmailComentario()));
                    }
                }
            } else {
                usuarioBasico = new UsuarioBasicoDTO();
                usuarioBasico.setCargo("Invitado");
                usuarioBasico.setNombre(comentarioEntity.getNombreComentario());
                usuarioBasico.setAvatar(ConversionUtils.obtenerGravatar(comentarioEntity.getEmailComentario()));
            }
            final ComentarioDTO comentarioDTO = EntityToDTOAdapter.adaptarComentario(comentarioEntity, usuarioBasico);
            comentariosEntrada.add(comentarioDTO);
        }
        return comentariosEntrada;
    }

}
