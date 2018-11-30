package com.momoko.es.rest.saga.controller;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaSagaColeccionResponse;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.api.exceptions.UrlElementNotFoundException;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.SagaService;
import com.momoko.es.jpa.model.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class SagaMiscellaneousFrontController {

    private static final Logger log = LoggerFactory.getLogger(SagaMiscellaneousFrontController.class);

    private final SagaService sagaService;

    private final EntradaService entradaService;

    private final StorageService storageService;

    @Autowired
    public SagaMiscellaneousFrontController(SagaService sagaService, EntradaService entradaService,
                                            StorageService storageService) {
        this.sagaService = sagaService;
        this.entradaService = entradaService;
        this.storageService = storageService;
    }


    @GetMapping(path = "/miscelaneos-saga/{url-saga}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaSagaColeccionResponse obtenerMiscelaneosSagaPagina(
            @PathVariable("url-saga") final String urlSaga, @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) final ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) throws UrlElementNotFoundException {
        final ObtenerPaginaSagaColeccionResponse paginaSagaNoticiasResponse = new ObtenerPaginaSagaColeccionResponse();
        final List<EntradaSimpleDTO> miscelaneos = new ArrayList<>();
        return obtenerPaginaSagaMiscelaneoResponse(urlSaga, paginaSagaNoticiasResponse, miscelaneos);

    }

    @GetMapping(path = "/miscelaneos-saga/{url-saga}")
    public @ResponseBody
    ObtenerPaginaSagaColeccionResponse obtenerMiscelaneosSaga(
            @PathVariable("url-saga") final String urlSaga,
            @RequestBody(required = false) final ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) throws UrlElementNotFoundException {
        final ObtenerPaginaSagaColeccionResponse paginaSagaNoticiasResponse = new ObtenerPaginaSagaColeccionResponse();
        final List<EntradaSimpleDTO> miscelaneos = new ArrayList<>();
        return obtenerPaginaSagaMiscelaneoResponse(urlSaga, paginaSagaNoticiasResponse, miscelaneos);

    }

    private ObtenerPaginaSagaColeccionResponse obtenerPaginaSagaMiscelaneoResponse(
            final String urlSaga, final ObtenerPaginaSagaColeccionResponse paginaSagaNoticiasResponse,
            final List<EntradaSimpleDTO> miscelaneos) throws UrlElementNotFoundException {
        log.debug("obtenerPaginaSagaMiscelaneoResponse()");
        final SagaDTO saga = this.sagaService.obtenerSaga(urlSaga);
        final List<DatoEntradaDTO> entradasSimples = saga.getEntradasSaga();
        int numeroEntradas = 0;

        for (final DatoEntradaDTO datoEntradaDTO : entradasSimples) {
            if (datoEntradaDTO.getTipoEntrada().equals(EntryTypeEnum.MISCELLANEOUS.getValue())) {
                final EntradaSimpleDTO entradaSimple = this.entradaService
                        .obtenerEntradaSimple(datoEntradaDTO.getUrlEntrada());
                if (entradaSimple.getImagenEntrada() != null) {
                    try {
                        entradaSimple.setImagenEntrada(this.storageService
                                .obtenerMiniatura(entradaSimple.getImagenEntrada(), 370, 208, true));
                    } catch (final IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }

                miscelaneos.add(entradaSimple);

                numeroEntradas++;
            }
        }
        paginaSagaNoticiasResponse.setSaga(saga);
        paginaSagaNoticiasResponse.setDatosEntrada(entradasSimples);
        paginaSagaNoticiasResponse.setEntradas(miscelaneos);
        paginaSagaNoticiasResponse.setNumeroEntradas(numeroEntradas);
        return paginaSagaNoticiasResponse;
    }


}
