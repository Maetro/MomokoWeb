package com.momoko.es.rest.index.controller;

import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.response.IndexDataReponseDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.index.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class IndexFrontController {

    private static final Logger log = LoggerFactory.getLogger(IndexFrontController.class);

    private final IndexService indexService;

    @Autowired
    public IndexFrontController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping(path = "/indexData")
    public @ResponseBody
    IndexDataReponseDTO getInfoIndex() {
        log.debug("getInfoIndex()");
        IndexDataReponseDTO obtenerIndexDataResponseDTO = this.indexService.getIndexDataResponse();
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        final List<LibroSimpleDTO> ultimasOpiniones = this.indexService.obtenerUltimasFichas();
        final LibroEntradaSimpleDTO ultimoComicAnalizado = this.indexService.obtenerUltimoComicAnalizado();
        obtenerIndexDataResponseDTO.setLibrosMasVistos(librosMasVistos);
        obtenerIndexDataResponseDTO.setUltimoComicAnalizado(ultimoComicAnalizado);
        obtenerIndexDataResponseDTO.setUltimosAnalisis(ultimasOpiniones);
        return obtenerIndexDataResponseDTO;
    }

    @GetMapping(path = "/suscribirse/{email}")
    public @ResponseBody
    ObtenerEntradaResponse suscribirse(@PathVariable("email") final String email) {
        log.debug("suscribirse()");
        this.indexService.suscribirse(email);
        return new ObtenerEntradaResponse();
    }

}
