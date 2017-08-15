/**
 * MainController.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.ErrorCreacionLibro;
import com.momoko.es.api.enums.EstadoGuardadoLibroEnum;
import com.momoko.es.api.enums.UserStatusEnum;
import com.momoko.es.api.response.GuardarLibroResponse;
import com.momoko.es.backend.model.parameter.CrearUsuarioDummyParameter;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.UserService;
import com.momoko.es.backend.model.service.ValidadorService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/modelo")
public class MainController {

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private LibroService libroService;

    @Autowired(required = false)
    private ValidadorService validadorService;

    @GetMapping(path = "/usuarios/add")
    public @ResponseBody String addNewUser(@RequestParam final String email, @RequestParam final String contrasena,
            @RequestParam final String login, @RequestParam final String nick, @RequestParam final String nombreVisible,
            @RequestParam final String url) {
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
        return this.libroService.recuperarLibros();
    }

    @GetMapping(path = "/generos")
    public @ResponseBody Iterable<GeneroDTO> getAllGeneros() {
        return this.libroService.obtenerTodosGeneros();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/libro/guardar")
    ResponseEntity<GuardarLibroResponse> add(@RequestBody final LibroDTO libroDTO) {

        // Validar
        final List<ErrorCreacionLibro> listaErrores = this.validadorService.validarLibro(libroDTO);

        // Guardar
        LibroDTO libro = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            libro = this.libroService.guardarLibro(libroDTO);
        }

        // Responder
        final GuardarLibroResponse respuesta = new GuardarLibroResponse();
        respuesta.setLibroDTO(libro);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((libro != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoLibroEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoLibroEnum.ERROR);
        }

        return new ResponseEntity<GuardarLibroResponse>(respuesta, HttpStatus.OK);

    }

}
