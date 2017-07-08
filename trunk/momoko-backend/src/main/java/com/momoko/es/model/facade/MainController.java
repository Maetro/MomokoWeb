/**
 * MainController.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.facade;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.UserStatusEnum;
import com.momoko.es.model.parameter.CrearUsuarioDummyParameter;
import com.momoko.es.model.service.LibroService;
import com.momoko.es.model.service.UserService;

@Controller
@RequestMapping(path = "/modelo")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private LibroService libroService;

    @GetMapping(path = "/usuarios/add")
    public @ResponseBody String addNewUser(@RequestParam final String email,
            @RequestParam final String contrasena, @RequestParam final String login, @RequestParam final String nick,
            @RequestParam final String nombreVisible, @RequestParam final String url) {
        final UsuarioDTO nuevoUsuario = crearUsuarioDummy(
                new CrearUsuarioDummyParameter(email, contrasena, login, nick, nombreVisible, url));
        this.userService.crearUsuario(nuevoUsuario);
        return "Saved";
    }

    /**
     * Crear usuario dummy.
     *
     * @param usuarioDummyParametros
     *            the usuario dummy parametros
     * @return the usuario dto
     */
    public UsuarioDTO crearUsuarioDummy(final CrearUsuarioDummyParameter usuarioDummyParametros) {
        final UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setLogin(usuarioDummyParametros.getLogin());
        nuevoUsuario.setEmail(usuarioDummyParametros.getEmail());
        nuevoUsuario.setContrasena(usuarioDummyParametros.getContrasena());
        nuevoUsuario.setNick(usuarioDummyParametros.getNick());
        nuevoUsuario.setNombreVisible(usuarioDummyParametros.getNombreVisible());
        nuevoUsuario.setUrl(usuarioDummyParametros.getUrl());
        nuevoUsuario.setFechaRegistro(Calendar.getInstance().getTime());
        nuevoUsuario.setUsuarioStatus(UserStatusEnum.ACTIVO);
        nuevoUsuario.setUsuario_rol_id(1);
        return nuevoUsuario;
    }

    @GetMapping(path = "/usuarios")
    public @ResponseBody Iterable<UsuarioDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return this.userService.recuperarUsuarios();
    }

    @GetMapping(path = "/libros")
    public @ResponseBody Iterable<LibroDTO> getAllLibros() {
        // This returns a JSON or XML with the users
        return this.libroService.recuperarLibros();
    }

}
