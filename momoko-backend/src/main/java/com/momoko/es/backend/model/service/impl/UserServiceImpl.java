/**
 * UserServiceImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.TipoUsuario;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.service.UserService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;
import com.momoko.es.util.NotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Override
    public UsuarioDTO crearUsuario(final UsuarioDTO nuevoUsuario) throws EmailExistsException {
        if (emailExiste(nuevoUsuario.getUsuarioEmail())) {
            throw new EmailExistsException("Ya existe un usuario con ese email: " + nuevoUsuario.getUsuarioEmail());
        }
        final UsuarioEntity usuarioEntity = DTOToEntityAdapter.adaptarUsuario(nuevoUsuario);
        return EntityToDTOAdapter.adaptarUsuario(this.usuarioRepository.save(usuarioEntity));

    }

    @Override
    public List<UsuarioDTO> recuperarUsuarios() {
        Iterable<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        if (usuarios.iterator().hasNext()) {
            for (UsuarioEntity usuario : usuarios) {
                usuariosDTO.add(EntityToDTOAdapter.adaptarUsuario(usuario));
            }
        }
        return usuariosDTO;
    }

    /**
     * Email exist.
     *
     * @param email
     *            the email
     * @return true, if successful
     */
    private boolean emailExiste(final String email) {
        final UsuarioEntity user = this.usuarioRepository.findByUsuarioEmail(email);
        return user != null;
    }

    @Override
    public UsuarioDTO doesUserExist(final String username) throws UserNotFoundException {
        final UsuarioEntity usuario = this.usuarioRepository.findByUsuarioLogin(username);
        return EntityToDTOAdapter.adaptarUsuario(usuario);
    }

    @Override
    public UsuarioDTO doesEmailExist(final String email) throws UserNotFoundException {
        UsuarioDTO usuario = null;
        final UsuarioEntity usuarioBD = this.usuarioRepository.findByUsuarioEmail(email);
        if (usuarioBD != null) {
            usuario = EntityToDTOAdapter.adaptarUsuario(usuarioBD);
        }
        return usuario;
    }

    @Override
    public String getUserEncodedPassword(final String email) {
        return this.usuarioRepository.findEncodedPasswordByEmail(email);
    }

    @Override
    public List<String> getNombresEditores() {
        final List<String> nombresEditores = new ArrayList<>();
        final List<UsuarioEntity> usuariosEditores = this.usuarioRepository.findAllByUsuarioRolIdIs(1);
        for (final UsuarioEntity usuarioEntity : usuariosEditores) {
            nombresEditores.add(usuarioEntity.getUsuarioNick());
        }
        return nombresEditores;
    }

    @Override
    public UsuarioBasicoDTO findFirstByUsuarioUrl(final String urlUsuario) throws UserNotFoundException {
        final UsuarioEntity usuarioBD = this.usuarioRepository.findFirstByUsuarioUrl(urlUsuario);
        return ConversionUtils.obtenerUsuarioBasico(usuarioBD);
    }

    @Override
    public List<RedactorDTO> obtenerRedactoresMomoko() {
        final List<UsuarioEntity> redactoresEntity = this.usuarioRepository
                .findAllByUsuarioRolIdIs(TipoUsuario.REDACTOR.getValue());
        final List<RedactorDTO> redactoresDTO = ConversionUtils.getRedactoresFromUsuarios(redactoresEntity);
        for (final RedactorDTO redactorDTO : redactoresDTO) {
            final BigDecimal media = this.puntuacionRepository.findScoreAverageFromUserId(redactorDTO.getUsuarioId());
            redactorDTO.setMediaPuntuaciones(media);
            final List<EntradaEntity> ultimaEntrada = this.entradaRepository
                    .findEntradaByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(redactorDTO.getUrlRedactor(),
                            new PageRequest(0, 1));
            if (CollectionUtils.isNotEmpty(ultimaEntrada)) {
                redactorDTO.setFechaUltimaEntrada(ultimaEntrada.get(0).getFechaAlta());
            }
        }
        redactoresDTO.sort((RedactorDTO r1, RedactorDTO r2) -> r1.getFechaUltimaEntrada().before(r2.getFechaUltimaEntrada()) ? 1 : -1);

        return redactoresDTO;
    }

    @Override
    public RedactorDTO guardarRedactor(RedactorDTO redactorDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = null;

        if (redactorDTO.getUsuarioId() != null) {
            usuarioEntity = actualizarRedactor(redactorDTO);

        } else {
            usuarioEntity = crearRedactor(redactorDTO);
        }

        return ConversionUtils.getRedactorFromUsuario(usuarioEntity);
    }



    private UsuarioEntity actualizarRedactor(@NotNull RedactorDTO redactorDTO) throws NotFoundException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        UsuarioEntity usuario = this.usuarioRepository.findOne(redactorDTO.getUsuarioId().longValue());
        if (usuario == null){
            throw new NotFoundException("El redactor a actualizar no fue encontrado");
        }
        usuario.setUsuarioLogin(redactorDTO.getNick());
        usuario.setUsuarioNick(redactorDTO.getNick());
        usuario.setUsuarioEmail(redactorDTO.getEmail());
        usuario.setPaginaWeb(redactorDTO.getPaginaWeb());
        usuario.setUsuarioUrl(redactorDTO.getUrlRedactor());
        usuario.setUsuarioNombreVisible(redactorDTO.getNick());
        usuario.setUsuarioRolId(1);
        usuario.setAvatarUrl(redactorDTO.getAvatarRedactor());
        usuario.setCargo(redactorDTO.getDescripcion());
        usuario.setFechaModificacion(Calendar.getInstance().getTime());
        usuario.setUsuarioModificacion(currentPrincipalName);
        usuario.setImagenCabeceraRedactor(redactorDTO.getImagenCabeceraRedactor());
        usuario.setTwitter(redactorDTO.getTwitter());
        usuario.setFacebook(redactorDTO.getFacebook());
        usuario.setInstagram(redactorDTO.getInstagram());
        usuario.setYoutube(redactorDTO.getYoutube());
        return this.usuarioRepository.save(usuario);
    }

    private UsuarioEntity crearRedactor(@NotNull RedactorDTO redactorDTO){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioLogin(redactorDTO.getNick());
        usuario.setUsuarioNick(redactorDTO.getNick());
        usuario.setUsuarioEmail(redactorDTO.getEmail());
        usuario.setUsuarioContrasena("cambiame");
        usuario.setPaginaWeb(redactorDTO.getPaginaWeb());
        usuario.setUsuarioUrl(redactorDTO.getUrlRedactor());
        usuario.setUsuarioNombreVisible(redactorDTO.getNick());
        usuario.setUsuarioRolId(1);
        usuario.setAvatarUrl(redactorDTO.getAvatarRedactor());
        usuario.setCargo(redactorDTO.getDescripcion());
        usuario.setFechaAlta(Calendar.getInstance().getTime());
        usuario.setUsuarioAlta(currentPrincipalName);
        usuario.setImagenCabeceraRedactor(redactorDTO.getImagenCabeceraRedactor());
        usuario.setTwitter(redactorDTO.getTwitter());
        usuario.setFacebook(redactorDTO.getFacebook());
        usuario.setInstagram(redactorDTO.getInstagram());
        usuario.setYoutube(redactorDTO.getYoutube());
        return this.usuarioRepository.save(usuario);
    }


}
