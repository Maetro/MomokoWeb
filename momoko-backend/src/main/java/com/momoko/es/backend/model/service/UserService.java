/**
 * UserService.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;

/**
 * The Interface UserService.
 */
public interface UserService {

    /**
     * Crear usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return the integer
     */
    public UsuarioDTO crearUsuario(UsuarioDTO nuevoUsuario) throws EmailExistsException;

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    public List<UsuarioDTO> recuperarUsuarios();

    /**
     * Does user exist.
     *
     * @param username
     *            the username
     * @return the usuario entity
     */
    public UsuarioDTO doesUserExist(String username) throws UserNotFoundException;

    public boolean existeUsuario(String usuarioEmail);

}
