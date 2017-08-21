/**
 * UserController.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.RegistroNuevoUsuarioDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.ErrorCreacionUsuario;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.exceptions.EmailExistsException;
import com.momoko.es.api.response.RegistrarUsuarioRespoonse;
import com.momoko.es.backend.model.service.UserService;
import com.momoko.es.backend.model.service.ValidadorService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/usuario")
public class UserFacade {

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private ValidadorService validadorService;

    @GetMapping(path = "/nuevo")
    public @ResponseBody RegistrarUsuarioRespoonse
            addNewUser(@RequestBody final RegistroNuevoUsuarioDTO nuevoUsuarioRequest) {

        final boolean usuarioValido = this.validadorService.validarUsuario(nuevoUsuarioRequest);

        final List<ErrorCreacionUsuario> listaErrores = new ArrayList<ErrorCreacionUsuario>();

        final UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setEmail(nuevoUsuarioRequest.getEmail());
        nuevoUsuario.setNick(nuevoUsuarioRequest.getNombre());
        nuevoUsuario.setContrasena(nuevoUsuarioRequest.getContrasena());
        nuevoUsuario.setLogin(nuevoUsuarioRequest.getNick());
        nuevoUsuario.setUsuario_rol_id(1);
        Integer usuarioId;
        try {
            usuarioId = this.userService.crearUsuario(nuevoUsuario);
            nuevoUsuario.setUsuarioId(usuarioId);
        } catch (final EmailExistsException e) {
            listaErrores.add(ErrorCreacionUsuario.EMAIL_YA_EXISTE);
        }

        final RegistrarUsuarioRespoonse respuesta = new RegistrarUsuarioRespoonse();
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
        return this.userService.recuperarUsuarios();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(@ModelAttribute("user") @Valid final UsuarioDTO nuevoUsuario) {
        final UsuarioDTO userDto = new UsuarioDTO();

        return "registration";
    }

}
