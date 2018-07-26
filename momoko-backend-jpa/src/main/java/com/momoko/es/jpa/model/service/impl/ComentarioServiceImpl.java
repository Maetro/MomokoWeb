/**
 * ComentarioServiceImpl.java 12-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.jpa.model.entity.ComentarioEntity;
import com.momoko.es.jpa.model.entity.EntradaEntity;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.repository.ComentarioRepository;
import com.momoko.es.jpa.model.repository.EntradaRepository;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.service.ComentarioService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import com.momoko.es.jpa.model.util.Mail;

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
        final EntradaEntity comentarioEntrada = this.entradaRepository.findById(comentarioAGuardar.getEntradaId())
                .orElse(null);
        nuevoComentario.setEntrada(comentarioEntrada);
        ComentarioEntity comentarioReferenciaEntity = null;

        if (comentarioAGuardar.getComentarioRespuesta() != null) {
            comentarioReferenciaEntity = this.comentarioRepository.findById(comentarioAGuardar.getComentarioRespuesta())
                    .orElse(null);
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
            usuarioBasico.setUrlUsuario(usuarioComentario.getUsuarioUrl());
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
                usuarioBasico.setUrlUsuario(usuarioComentario.getUsuarioUrl());
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

    @Override
    public void enviarNotificacion(final ComentarioDTO comentarioDTO) {
        try {

            final ComentarioEntity comentarioPrincipal = this.comentarioRepository
                    .findById(comentarioDTO.getComentarioId()).orElse(null);
            final EntradaEntity entrada = comentarioPrincipal.getEntrada();
            final String content = generarEmailNuevoComentario(comentarioPrincipal, entrada,
                    entrada.getEntradaAutor().getUsuarioNick());

            Mail.sendEmail("Nuevo comentario en momoko.es", content, entrada.getEntradaAutor().getUsuarioEmail());
            Mail.sendEmail("Nuevo comentario en momoko.es", content, "kizuna.owo@gmail.com");
            Mail.sendEmail("Nuevo comentario en momoko.es", content, "RMaetro@gmail.com");

            if (comentarioPrincipal.getComentarioReferenciaEntity() != null) {

                enviarEmailRespuestaComentario(comentarioPrincipal, entrada);
            }

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarEmailRespuestaComentario(final ComentarioEntity comentarioPrincipal,
            final EntradaEntity entrada) {
        final ComentarioEntity referencia = comentarioPrincipal.getComentarioReferenciaEntity();
        String content = null;
        try {
            content = generarEmail(comentarioPrincipal, entrada, referencia.getNombreComentario(),
                    "email-comment.html");
            Mail.sendEmail("Te han respondido un comentario en momoko.es", content, referencia.getEmailComentario());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private String generarEmailNuevoComentario(final ComentarioEntity comentarioPrincipal, final EntradaEntity entrada,
            final String nombreDestinatario) throws IOException {

        final String mailTemplate = "email-notification.html";

        final String content = generarEmail(comentarioPrincipal, entrada, nombreDestinatario, mailTemplate);
        return content;
    }

    /**
     * Generar email.
     *
     * @param comentarioPrincipal
     *            the comentario principal
     * @param entrada
     *            the entrada
     * @param nombreDestinatario
     *            the nombre destinatario
     * @param mailTemplate
     *            the mail template
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public String generarEmail(final ComentarioEntity comentarioPrincipal, final EntradaEntity entrada,
            final String nombreDestinatario, final String mailTemplate) throws IOException {
        final SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd/mm/yyyy");

        String content = Mail.readMailTemplate(this.almacenImagenes.getTemplateFolder() + "/" + mailTemplate,
                StandardCharsets.UTF_8);

        content = Mail.replaceTagInContent("${nombreDestinatario}", nombreDestinatario, content);
        content = Mail.replaceTagInContent("${nombreComentario}", comentarioPrincipal.getNombreComentario(), content);
        content = Mail.replaceTagInContent("${tituloEntrada}", entrada.getTituloEntrada(), content);
        content = Mail.replaceTagInContent("${tipoEntrada}",
                TipoEntrada.obtenerTipoEntrada(entrada.getTipoEntrada()).getNombre(), content);

        content = Mail.replaceTagInContent("${fechaComentario}", dt.format(comentarioPrincipal.getFechaAlta()),
                content);
        content = Mail.replaceTagInContent("${contenidoComentario}", comentarioPrincipal.getTextoComentario(), content);
        content = Mail.replaceTagInContent("${urlComentario}", "https://momoko.es/" + entrada.getUrlEntrada(), content);
        return content;
    }

}
