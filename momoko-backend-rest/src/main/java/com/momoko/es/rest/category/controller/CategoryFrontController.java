package com.momoko.es.rest.category.controller;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaCategoriaResponse;
import com.momoko.es.jpa.genre.service.GenreService;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.rest.util.ControllerUtil;
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
public class CategoryFrontController {

    private static final Logger log = LoggerFactory.getLogger(CategoryFrontController.class);

    private final GenreService genreService;
    private final EntradaService entradaService;
    private final StorageService almacenImagenes;

    @Autowired
    public CategoryFrontController(GenreService genreService, EntradaService entradaService,
                                   StorageService almacenImagenes) {
        this.genreService = genreService;
        this.entradaService = entradaService;
        this.almacenImagenes = almacenImagenes;
    }

    @GetMapping(path = "/categoria/{url-categoria}")
    public @ResponseBody
    ObtenerPaginaCategoriaResponse obtenerCategoria(
            @PathVariable("url-categoria") final String urlCategoria,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request) {

        final ObtenerPaginaCategoriaResponse categoriaResponse = new ObtenerPaginaCategoriaResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<>();
        ObtenerPaginaElementoRequest finalRequest = ControllerUtil.getObtenerPaginaElementoRequestEmpty(urlCategoria,
                1, request);
        return this.obtenerCategoriaResponse(urlCategoria, finalRequest, categoriaResponse, entradasCategoria);

    }

    @GetMapping(path = "/categoria/{url-categoria}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaCategoriaResponse obtenerCategoria(
            @PathVariable("url-categoria") final String urlCategoria,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaCategoriaResponse categoriaResponse = new ObtenerPaginaCategoriaResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<>();
        ObtenerPaginaElementoRequest finalRequest = ControllerUtil.getObtenerPaginaElementoRequestEmpty(urlCategoria,
                numeroPagina, request);
        return this.obtenerCategoriaResponse(urlCategoria, finalRequest, categoriaResponse, entradasCategoria);

    }



    /**
     * Obtener categoria response.
     *
     * @param urlCategoria      the url categoria
     * @param request           the request
     * @param categoriaResponse the categoria response
     * @param entradasCategoria the entradas categoria
     * @return the obtener pagina categoria response
     */
    private ObtenerPaginaCategoriaResponse obtenerCategoriaResponse(final String urlCategoria,
                                                                    final ObtenerPaginaElementoRequest request, final ObtenerPaginaCategoriaResponse categoriaResponse,
                                                                    final List<EntradaSimpleDTO> entradasCategoria) {
        final CategoriaDTO categoriaDTO = this.genreService.obtenerCategoriaPorUrl(urlCategoria);
        switch (urlCategoria) {
            case "noticias":
                entradasCategoria.addAll(this.entradaService.obtenerNoticias(request));
                categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroNoticias());
                break;
            case "miscelaneos":
                entradasCategoria.addAll(this.entradaService.obtenerMiscelaneos(request));
                categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroMiscelaneos());
                break;
            case "videos":
                entradasCategoria.addAll(this.entradaService.obtenerVideos(request));
                categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroVideos());
                break;
            default:
                entradasCategoria.addAll(
                        this.entradaService.obtenerEntradasCategoriaPorFecha(categoriaDTO, 9, request.getNumeroPagina()));
                categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroEntradasCategoria(categoriaDTO));
                break;
        }
        ControllerUtil.generateThumbnailsOfEntrySimpleDTOS(entradasCategoria, this.almacenImagenes,370, 208, true);

        categoriaResponse.setEntradasCategoria(entradasCategoria);
        categoriaResponse.setCategoria(categoriaDTO);
        return categoriaResponse;
    }

}
