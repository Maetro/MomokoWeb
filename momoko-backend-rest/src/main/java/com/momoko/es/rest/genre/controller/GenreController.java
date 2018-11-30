package com.momoko.es.rest.genre.controller;

import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.genre.GuardarGeneroResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionGenero;
import com.momoko.es.jpa.genre.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class GenreController {

    private static final Logger log = LoggerFactory.getLogger(GenreController.class);

    private final GenreService generoService;

    @Autowired
    public GenreController(GenreService generoService) {
        this.generoService = generoService;
    }


    @GetMapping(path = "/generos")
    public @ResponseBody
    Iterable<GenreDTO> getAllGeneros() {
        log.debug("getAllGeneros");
        return this.generoService.getAllGenres();
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/generos/add")
    ResponseEntity<GuardarGeneroResponse> addGenero(@RequestBody final GenreDTO generoDTO) {

        // Validar
        final List<ErrorCreacionGenero> listaErrores = this.generoService.validarGenero(generoDTO);

        // Guardar
        GenreDTO genero = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                genero = this.generoService.saveGenre(generoDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionGenero.GENERO_YA_EXISTE);
            }
        }

        // Responder
        final GuardarGeneroResponse respuesta = new GuardarGeneroResponse();
        respuesta.setGeneroDTO(genero);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((genero != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarGeneroResponse>(respuesta, HttpStatus.OK);

    }
}
