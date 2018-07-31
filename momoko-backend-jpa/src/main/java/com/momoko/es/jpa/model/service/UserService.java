/**
 * UserService.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import java.util.List;
import java.util.Optional;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.util.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The Interface UserService.
 */
public interface UserService extends UserDetailsService {

    /**
     * Crear usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return the integer
     * @throws EmailExistsException
     *             the email exists exception
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
     * @throws UserNotFoundException
     *             the user not found exception
     */
    public UsuarioDTO doesUserExist(String username) throws UserNotFoundException;

    /**
     * Does email exist.
     *
     * @param email
     *            the email
     * @return the usuario dto
     * @throws UserNotFoundException
     *             the user not found exception
     */
    UsuarioDTO doesEmailExist(String email) throws UserNotFoundException;

    /**
     * Gets the user encoded password.
     *
     * @param email
     *            the email
     * @return the user encoded password
     */
    public String getUserEncodedPassword(String email);

    /**
     * Gets the nombres editores.
     *
     * @return the nombres editores
     */
    public List<String> getNombresEditores();

    /**
     * Find first by usuario url.
     *
     * @param urlUsuario
     *            the url usuario
     * @return the usuario dto
     * @throws UserNotFoundException
     *             the user not found exception
     */
    UsuarioBasicoDTO findFirstByUsuarioUrl(String urlUsuario) throws UserNotFoundException;

    /**
     * Obtener redactores momoko.
     *
     * @return the list
     */
    public List<RedactorDTO> obtenerRedactoresMomoko();

    /**
     * Guardar redactor redactor dto.
     *
     * @param redactorDTO
     *            the redactor dto
     * @return the redactor dto
     */
    RedactorDTO guardarRedactor(RedactorDTO redactorDTO) throws NotFoundException;

    /**
     * Find redactor by url.
     *
     * @param urlRedactor
     *            the url editor
     * @return the redactor dto
     */
    public RedactorDTO findRedactorByUrl(String urlRedactor) throws UserNotFoundException;

    UsuarioEntity findByUsuarioEmail(String username) throws UserNotFoundException;
}
