package com.momoko.es.rest.tag.controller;

import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaEtiquetaResponse;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.service.EtiquetaService;
import com.momoko.es.jpa.model.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class TagFrontController {

    private final EntradaService entradaService;
    private final EtiquetaService etiquetaService;
    private final StorageService storageService;

    @Autowired
    public TagFrontController(EntradaService entradaService, EtiquetaService etiquetaService, StorageService storageService) {
        this.entradaService = entradaService;
        this.etiquetaService = etiquetaService;
        this.storageService = storageService;
    }

    @GetMapping(path = "/etiqueta/{url-etiqueta}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaEtiquetaResponse obtenerEtiqueta(
            @PathVariable("url-etiqueta") final String urlEtiqueta,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaEtiquetaResponse etiquetaResponse = new ObtenerPaginaEtiquetaResponse();
        final List<EntradaSimpleDTO> entradasEtiqueta = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(numeroPagina);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlEtiqueta);
        }
        return obtenerEtiquetaResponse(urlEtiqueta, request, etiquetaResponse, entradasEtiqueta);

    }

    @GetMapping(path = "/etiqueta/{url-etiqueta}")
    public @ResponseBody
    ObtenerPaginaEtiquetaResponse obtenerEtiqueta(
            @PathVariable("url-etiqueta") final String urlEtiqueta,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaEtiquetaResponse etiquetaResponse = new ObtenerPaginaEtiquetaResponse();
        final List<EntradaSimpleDTO> entradasEtiqueta = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlEtiqueta);
        }
        return obtenerEtiquetaResponse(urlEtiqueta, request, etiquetaResponse, entradasEtiqueta);

    }

    private ObtenerPaginaEtiquetaResponse obtenerEtiquetaResponse(final String urlEtiqueta,
                                                                  final ObtenerPaginaElementoRequest request, final ObtenerPaginaEtiquetaResponse etiquetaResponse,
                                                                  final List<EntradaSimpleDTO> entradasEtiqueta) {
        final EtiquetaDTO etiquetaDTO = this.etiquetaService.obtenerEtiquetaPorUrl(urlEtiqueta);

        entradasEtiqueta
                .addAll(this.entradaService.obtenerEntradasEtiquetaPorFecha(etiquetaDTO, 9, request.getNumeroPagina()));
        etiquetaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroEntradasEtiqueta(etiquetaDTO));

        for (final EntradaSimpleDTO entradaSimpleDTO : entradasEtiqueta) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.storageService.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 370, 208, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        etiquetaResponse.setEntradasEtiqueta(entradasEtiqueta);
        etiquetaResponse.setEtiqueta(etiquetaDTO);

        return etiquetaResponse;
    }

}
