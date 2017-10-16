/**
 * EmployeeController.java 14-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.momoko.es.model.Usuario;

@Controller
public class UsuarioController {

    private final List<Usuario> usuario = new ArrayList<>();

    @GetMapping("/usuario")
    @ResponseBody
    public Optional<Usuario> getEmployee(@RequestParam final String email) {
        return this.usuario.stream().filter(x -> x.getEmail().equals(email)).findAny();
    }

    @PostMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void postMessage(@RequestBody final Usuario usuario) {
        this.usuario.add(usuario);
    }

}
