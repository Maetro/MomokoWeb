/**
 * UserServiceImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
        // TODO Auto-generated method stub
        return null;
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
        if (user != null) {
            return true;
        }
        return false;
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

}
