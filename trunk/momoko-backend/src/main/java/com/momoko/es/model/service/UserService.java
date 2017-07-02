/**
 * UserService.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.service;

import java.util.List;

import com.momoko.es.model.dto.UsuarioDTO;

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
    public Integer crearUsuario(UsuarioDTO nuevoUsuario);

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    public List<UsuarioDTO> recuperarUsuarios();

}
