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

import com.momoko.es.model.dto.UsuarioDTO;
import com.momoko.es.model.enums.UserStatusEnum;
import com.momoko.es.model.service.UserService;

@Controller
@RequestMapping(path = "/modelo")
public class MainController {

    @Autowired(required = false)
    private UserService userService;

    @GetMapping(path = "/usuarios/add")
    public @ResponseBody String addNewUser(@RequestParam final String email,
            @RequestParam final String contrasena, @RequestParam final String login, @RequestParam final String nick,
            @RequestParam final String nombreVisible, @RequestParam final String url) {
        final UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setLogin(login);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setNick(nick);
        nuevoUsuario.setNombreVisible(nombreVisible);
        nuevoUsuario.setUrl(url);
        nuevoUsuario.setFechaRegistro(Calendar.getInstance().getTime());
        nuevoUsuario.setUsuarioStatus(UserStatusEnum.ACTIVO);
        nuevoUsuario.setUsuario_rol_id(1);
        this.userService.crearUsuario(nuevoUsuario);
        return "Saved";
    }

    @GetMapping(path = "/usuarios")
    public @ResponseBody Iterable<UsuarioDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return this.userService.recuperarUsuarios();
    }

}
