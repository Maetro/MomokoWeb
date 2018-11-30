package com.momoko.es.rest.saga.controller;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.response.ObtenerFichaSagaResponse;
import com.momoko.es.api.exceptions.UrlElementNotFoundException;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.SagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class SagaFrontController {

    private static final Logger log = LoggerFactory.getLogger(SagaFrontController.class);

    private final SagaService sagaService;

    private final LibroService libroService;

    @Autowired
    public SagaFrontController(SagaService sagaService, LibroService libroService) {
        this.sagaService = sagaService;
        this.libroService = libroService;
    }

    @GetMapping(path = "/saga/{url-saga}")
    public @ResponseBody
    ObtenerFichaSagaResponse obtenerSaga(@PathVariable("url-saga") final String urlSaga) {

        final ObtenerFichaSagaResponse sagaResponse = new ObtenerFichaSagaResponse();
        SagaDTO sagaDTO = null;
        try {
            sagaDTO = this.sagaService.obtenerSaga(urlSaga);
            sagaResponse.setSaga(sagaDTO);
            final List<LibroDTO> librosSaga = this.libroService.obtenerLibros(sagaDTO.getLibrosSaga());
            sagaResponse.setLibrosSaga(librosSaga);
            final List<EntradaSimpleDTO> entradasSaga = this.sagaService.obtenerEntradasSaga(sagaDTO);
            sagaResponse.setTresUltimasEntradas(entradasSaga);

            final List<EntradaSimpleDTO> entradasLibrosSaga = this.sagaService.obtenerEntradasLibrosSaga(sagaDTO);
            sagaResponse.setTresUltimasEntradasLibros(entradasLibrosSaga);
        } catch (final UrlElementNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return sagaResponse;

    }
}
