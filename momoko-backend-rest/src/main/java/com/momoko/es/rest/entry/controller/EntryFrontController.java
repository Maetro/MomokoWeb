package com.momoko.es.rest.entry.controller;

import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.util.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class EntryFrontController {

    private static final Logger log = LoggerFactory.getLogger(EntryController.class);

    private final EntradaService entradaService;

    @Autowired
    public EntryFrontController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody
    ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada,
                                           final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent)
            throws NotFoundException {
        log.debug("getEntradaByUrl");
        ObtenerEntradaResponse respuesta = null;
        if (!urlEntrada.equals("not-found")) {
            respuesta = this.entradaService.obtenerEntrada(urlEntrada, true);
        }
        if (respuesta == null || respuesta.getEntrada() == null) {
            throw new NotFoundException("No se ha encontrado la entrada: " + urlEntrada);
        }
        return respuesta;
    }

    @GetMapping(path = "/video/{url-video}")
    public @ResponseBody
    ObtenerEntradaResponse obtenerVideo(@PathVariable("url-video") final String urlVideo,
                                        @RequestHeader(value = "User-Agent") final String userAgent) {
        return this.entradaService.obtenerEntradaVideo(urlVideo);
    }

}
