/**
 * UserServiceImpl.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.model.entity.UsuarioEntity;
import com.momoko.es.model.repository.UsuarioRepository;
import com.momoko.es.util.DTOToEntityAdapter;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Integer crearUsuario(final UsuarioDTO nuevoUsuario) {
        final UsuarioEntity usuarioEntity = DTOToEntityAdapter.adaptarUsuario(nuevoUsuario);
        this.usuarioRepository.save(usuarioEntity);
        return null;
    }

    @Override
    public List<UsuarioDTO> recuperarUsuarios() {
        // TODO Auto-generated method stub
        return null;
    }

}
