/**
 * UserServiceImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.momoko.es.api.enums.errores.ErrorCreacionRedactor;
import com.momoko.es.commons.security.MomokoPrincipal;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.api.enums.TipoUsuario;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.jpa.entry.EntradaEntity;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.model.repository.EntradaRepository;
import com.momoko.es.jpa.model.repository.PuntuacionRepository;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import com.momoko.es.jpa.model.util.MomokoUtils;
import com.momoko.es.jpa.model.util.NotFoundException;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public MomokoPrincipal loadUserByUsername(String username)
            throws UsernameNotFoundException {

        log.debug("Loading user having username: " + username);

        // delegates to findUserByUsername
        UsuarioEntity user = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.debug("Loaded user having username: " + username);

        return new MomokoPrincipal(EntityToDTOAdapter.adaptarUsuario(user));
    }


    @Override
    public UsuarioDTO crearUsuario(final UsuarioDTO nuevoUsuario) throws EmailExistsException {
        if (emailExiste(nuevoUsuario.getEmail())) {
            throw new EmailExistsException("Ya existe un usuario con ese email: " + nuevoUsuario.getEmail());
        }
        final UsuarioEntity usuarioEntity = DTOToEntityAdapter.adaptarUsuario(nuevoUsuario);
        return EntityToDTOAdapter.adaptarUsuario(this.usuarioRepository.save(usuarioEntity));

    }

    @Override
    public List<UsuarioDTO> recuperarUsuarios() {
        final Iterable<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
        final List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        if (usuarios.iterator().hasNext()) {
            for (final UsuarioEntity usuario : usuarios) {
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
        final UsuarioEntity user = this.usuarioRepository.findByEmail(email).orElse(null);
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
        final UsuarioEntity usuarioBD = this.usuarioRepository.findByEmail(email).orElse(null);
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
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        for (final RedactorDTO redactorDTO : redactoresDTO) {
            final BigDecimal media = this.puntuacionRepository.findScoreAverageFromUserId(redactorDTO.getUsuarioId());
            redactorDTO.setMediaPuntuaciones(media);
            final List<EntradaEntity> ultimaEntrada = this.entradaRepository
                    .findEntradaByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(redactorDTO.getUrlRedactor(),
                            PageRequest.of(0, 1));
            if (CollectionUtils.isNotEmpty(ultimaEntrada)) {
                redactorDTO.setFechaUltimaEntrada(ultimaEntrada.get(0).getCreatedDate());
            }
            redactorDTO.setAvatarRedactor(imageServer + redactorDTO.getAvatarRedactor());
            redactorDTO.setImagenCabeceraRedactor(imageServer + redactorDTO.getImagenCabeceraRedactor());
        }
        redactoresDTO.sort((final RedactorDTO r1, final RedactorDTO r2) -> (r1.getFechaUltimaEntrada() != null)
                && r1.getFechaUltimaEntrada().before(r2.getFechaUltimaEntrada()) ? 1 : -1);

        return redactoresDTO;
    }

    @Override
    public RedactorDTO guardarRedactor(final RedactorDTO redactorDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = null;

        if (redactorDTO.getUsuarioId() != null) {
            usuarioEntity = actualizarRedactor(redactorDTO);

        } else {
            usuarioEntity = crearRedactor(redactorDTO);
        }

        return ConversionUtils.getRedactorFromUsuario(usuarioEntity);
    }

    private UsuarioEntity actualizarRedactor(@NotNull final RedactorDTO redactorDTO) throws NotFoundException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final UsuarioEntity usuario = this.usuarioRepository.findById(redactorDTO.getUsuarioId()).orElse(null);
        if (usuario == null) {
            throw new NotFoundException("El redactor a actualizar no fue encontrado");
        }
        usuario.setUsuarioLogin(redactorDTO.getNick());
        usuario.setUsuarioNick(redactorDTO.getNick());
        usuario.setEmail(redactorDTO.getEmail());
        usuario.setPaginaWeb(redactorDTO.getPaginaWeb());
        usuario.setUsuarioUrl(redactorDTO.getUrlRedactor());
        usuario.setUsuarioNombreVisible(redactorDTO.getNick());
        usuario.setUsuarioRolId(1);
        if (redactorDTO.getAvatarRedactor() != null) {
            usuario.setAvatarUrl(MomokoUtils.soloNombreYCarpeta(redactorDTO.getAvatarRedactor()));
        }
        usuario.setCargo(redactorDTO.getCargo());
        usuario.setDescripcion(redactorDTO.getDescripcion());
        usuario.setFechaModificacion(Calendar.getInstance().getTime());
        usuario.setUsuarioModificacion(currentPrincipalName);
        if (redactorDTO.getImagenCabeceraRedactor() != null) {
            usuario.setImagenCabeceraRedactor(MomokoUtils.soloNombreYCarpeta(redactorDTO.getImagenCabeceraRedactor()));
        }
        usuario.setTwitter(redactorDTO.getTwitter());
        usuario.setFacebook(redactorDTO.getFacebook());
        usuario.setInstagram(redactorDTO.getInstagram());
        usuario.setYoutube(redactorDTO.getYoutube());
        return this.usuarioRepository.save(usuario);
    }

    private UsuarioEntity crearRedactor(@NotNull final RedactorDTO redactorDTO) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioLogin(redactorDTO.getNick());
        usuario.setUsuarioNick(redactorDTO.getNick());
        usuario.setEmail(redactorDTO.getEmail());
        usuario.setPassword("cambiame");
        usuario.setPaginaWeb(redactorDTO.getPaginaWeb());
        usuario.setUsuarioUrl(redactorDTO.getUrlRedactor());
        usuario.setUsuarioNombreVisible(redactorDTO.getNick());
        usuario.setUsuarioRolId(1);
        usuario.setAvatarUrl(MomokoUtils.soloNombreYCarpeta(redactorDTO.getAvatarRedactor()));
        usuario.setCargo(redactorDTO.getCargo());
        usuario.setDescripcion(redactorDTO.getDescripcion());
        usuario.setFechaAlta(Calendar.getInstance().getTime());
        usuario.setUsuarioFechaRegistro(Calendar.getInstance().getTime());
        usuario.setUsuarioStatus(0);
        usuario.setUsuarioAlta(currentPrincipalName);
        usuario.setImagenCabeceraRedactor(MomokoUtils.soloNombreYCarpeta(redactorDTO.getImagenCabeceraRedactor()));
        usuario.setTwitter(redactorDTO.getTwitter());
        usuario.setFacebook(redactorDTO.getFacebook());
        usuario.setInstagram(redactorDTO.getInstagram());
        usuario.setYoutube(redactorDTO.getYoutube());
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public RedactorDTO findRedactorByUrl(final String urlRedactor) throws UserNotFoundException {
        final UsuarioEntity usuarioBD = this.usuarioRepository.findFirstByUsuarioUrl(urlRedactor);
        if (usuarioBD == null) {
            throw new UserNotFoundException("No existe el usuario con la url: " + urlRedactor);
        }

        return ConversionUtils.getRedactorFromUsuario(usuarioBD);
    }

    @Override
    public UsuarioEntity findByUsuarioEmail(String username) throws UserNotFoundException{
        return usuarioRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List<ErrorCreacionRedactor> validarRedactor(final RedactorDTO redactorDTO) {
        final List<ErrorCreacionRedactor> listaErrores = new ArrayList<>();
        if (StringUtils.isEmpty(redactorDTO.getNombre())) {
            listaErrores.add(ErrorCreacionRedactor.FALTA_NOMBRE);
        }
        return listaErrores;
    }
}
