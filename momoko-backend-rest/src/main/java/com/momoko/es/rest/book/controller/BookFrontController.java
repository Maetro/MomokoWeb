package com.momoko.es.rest.book.controller;

import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.StorageService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class BookFrontController {

    private static final Logger log = LoggerFactory.getLogger(BookFrontController.class);

    private final LibroService libroService;
    private final StorageService almacenImagenes;

    @Autowired
    public BookFrontController(LibroService libroService, StorageService almacenImagenes) {
        this.libroService = libroService;
        this.almacenImagenes = almacenImagenes;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody
    ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro,
                                           final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent) {
        log.debug("obtenerLibro()");
        final ObtenerFichaLibroResponse respuesta = this.libroService.obtenerFichaLibroResponse(urlLibro);
        if (respuesta.getLibro() != null) {
            respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
            if (CollectionUtils.isNotEmpty(respuesta.getCincoLibrosParecidos())) {
                final String url = this.almacenImagenes.getUrlImageServer();
                for (final LibroSimpleDTO libroSimple : respuesta.getCincoLibrosParecidos()) {

                    libroSimple.setPortada(url + libroSimple.getPortada());

                }
            }
        }

        return respuesta;
    }



}
