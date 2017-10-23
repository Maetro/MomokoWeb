/**
 * UserController.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;
import com.momoko.es.api.dto.StringResponseDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.ErrorCreacionUsuario;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.api.response.RegistrarUsuarioResponse;
import com.momoko.es.backend.model.service.UserService;
import com.momoko.es.backend.model.service.ValidadorService;

@Controller
@RequestMapping(path = "/account")
public class UserFacade {

    @Autowired(required = false)
    private UserService usuarioService;

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/me", produces = "application/json")
    public @ResponseBody StringResponseDTO obtenerNombreLogin() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        return new StringResponseDTO(currentPrincipalName);
    }

    @GetMapping(path = "/nuevo")
    public @ResponseBody RegistrarUsuarioResponse
            addNewUser(@RequestBody final RegistroNuevoUsuarioDTO nuevoUsuarioRequest) {

        final boolean usuarioValido = this.validadorService.validarUsuario(nuevoUsuarioRequest);

        final List<ErrorCreacionUsuario> listaErrores = new ArrayList<ErrorCreacionUsuario>();

        final UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setUsuarioEmail(nuevoUsuarioRequest.getEmail());
        nuevoUsuario.setUsuarioNick(nuevoUsuarioRequest.getNombre());
        nuevoUsuario.setUsuarioContrasena(nuevoUsuarioRequest.getContrasena());
        nuevoUsuario.setUsuarioLogin(nuevoUsuarioRequest.getNick());
        nuevoUsuario.setUsuarioRolId(1);
        UsuarioDTO usuario;
        try {
            usuario = this.usuarioService.crearUsuario(nuevoUsuario);
            nuevoUsuario.setUsuarioId(usuario.getUsuarioId());
        } catch (final EmailExistsException e) {
            listaErrores.add(ErrorCreacionUsuario.EMAIL_YA_EXISTE);
        }

        final RegistrarUsuarioResponse respuesta = new RegistrarUsuarioResponse();
        respuesta.setUsuarioDTO(nuevoUsuario);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((nuevoUsuario != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }
        return respuesta;
    }

    @GetMapping(path = "/usuarios")
    public @ResponseBody Iterable<UsuarioDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return this.usuarioService.recuperarUsuarios();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(@ModelAttribute("user") @Valid final UsuarioDTO nuevoUsuario) {
        final UsuarioDTO userDto = new UsuarioDTO();

        return "registration";
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<RegistrarUsuarioResponse> processSignup(final ModelMap model,
            @RequestBody final UsuarioDTO reqUser) throws EmailExistsException, UserNotFoundException {
        UsuarioDTO nuevoUsuario = null;
        boolean existeUsuario = false;
        final RegistrarUsuarioResponse response = new RegistrarUsuarioResponse();
        final List<ErrorCreacionUsuario> listaErroresValidacion = new ArrayList<ErrorCreacionUsuario>();
        existeUsuario = this.usuarioService.doesEmailExist(reqUser.getUsuarioEmail()) != null;

        if (existeUsuario) {
            listaErroresValidacion.add(ErrorCreacionUsuario.EMAIL_YA_EXISTE);
        } else {
            nuevoUsuario = new UsuarioDTO();
            nuevoUsuario.setUsuarioEmail(reqUser.getUsuarioEmail());
            nuevoUsuario.setUsuarioContrasena(this.passwordEncoder.encode(reqUser.getUsuarioContrasena()));
            nuevoUsuario.setUsuarioLogin(reqUser.getUsuarioLogin());
            nuevoUsuario.setUsuarioNick(reqUser.getUsuarioLogin());
            nuevoUsuario.setUsuarioFechaRegistro(Calendar.getInstance().getTime());
            nuevoUsuario.setUsuarioRolId(2);
            final UsuarioDTO persistedUser = this.usuarioService.crearUsuario(nuevoUsuario);

            response.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
            response.setUsuarioDTO(persistedUser);
        }
        response.setListaErroresValidacion(listaErroresValidacion);
        return new ResponseEntity<RegistrarUsuarioResponse>(response, HttpStatus.OK);
    }

}
