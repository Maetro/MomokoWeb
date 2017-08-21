/**
 * MainController.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.TestLibroDTO;
import com.momoko.es.api.enums.ErrorCreacionLibro;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.response.GuardarLibroResponse;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.ValidadorService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/modelo")
public class MainController {

    @Autowired(required = false)
    private LibroService libroService;

    @Autowired(required = false)
    private ValidadorService validadorService;

    @GetMapping(path = "/libros")
    public @ResponseBody Iterable<LibroDTO> getAllLibros() {
        System.out.println("LLamada a la lista de libros");
        return this.libroService.recuperarLibros();
    }

    @GetMapping(path = "/generos")
    public @ResponseBody Iterable<GeneroDTO> getAllGeneros() {
        return this.libroService.obtenerTodosGeneros();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/libros/add")
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
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarLibroResponse>(respuesta, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/libros/add2")
    ResponseEntity<GuardarLibroResponse> add2(@RequestBody final TestLibroDTO libroDTO) {

        System.out.println(libroDTO);

        // // Validar
        // final List<ErrorCreacionLibro> listaErrores = this.validadorService.validarLibro(libroDTO);
        //
        // // Guardar
        // LibroDTO libro = null;
        // if (CollectionUtils.isEmpty(listaErrores)) {
        // libro = this.libroService.guardarLibro(libroDTO);
        // }
        //
        // // Responder
        final GuardarLibroResponse respuesta = new GuardarLibroResponse();
        // respuesta.setLibroDTO(libro);
        // respuesta.setListaErroresValidacion(listaErrores);
        //
        // if ((libro != null) && CollectionUtils.isEmpty(listaErrores)) {
        // respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        // } else {
        // respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        // }

        return new ResponseEntity<GuardarLibroResponse>(respuesta, HttpStatus.OK);

    }

}
