package com.momoko.es.jpa.model.service;

import java.util.List;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionRedactor;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.model.util.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UsuarioDTO crearUsuario(UsuarioDTO nuevoUsuario) throws EmailExistsException;

    List<UsuarioDTO> recuperarUsuarios();

    UsuarioDTO doesUserExist(String username) throws UserNotFoundException;

    UsuarioDTO doesEmailExist(String email) throws UserNotFoundException;

    String getUserEncodedPassword(String email);

    List<String> getNombresEditores();

    UsuarioBasicoDTO findFirstByUsuarioUrl(String urlUsuario) throws UserNotFoundException;

    List<RedactorDTO> obtenerRedactoresMomoko();

    RedactorDTO guardarRedactor(RedactorDTO redactorDTO) throws NotFoundException;

    RedactorDTO findRedactorByUrl(String urlRedactor) throws UserNotFoundException;

    UsuarioEntity findByUsuarioEmail(String username) throws UserNotFoundException;

    List<ErrorCreacionRedactor> validarRedactor(RedactorDTO redactorDTO);
}
