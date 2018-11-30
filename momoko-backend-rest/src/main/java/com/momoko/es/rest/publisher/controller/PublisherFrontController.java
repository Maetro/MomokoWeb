package com.momoko.es.rest.publisher.controller;

import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaEditorialResponse;
import com.momoko.es.jpa.publisher.EditorialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class PublisherFrontController {

    private static final Logger log = LoggerFactory.getLogger(PublisherController.class);

    private final EditorialService editorialService;

    @Autowired
    public PublisherFrontController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping(path = "/editorial/{url-editorial}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaEditorialResponse obtenerEditorial(
            @PathVariable("url-editorial") final String urlEditorial,
            @PathVariable("numero-pagina") final Integer numeroPagina) {
        log.debug("obtenerEditorial()");
        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(numeroPagina);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlEditorial);

        return this.obtenerEditorialResponse(request);

    }

    @GetMapping(path = "/editorial/{url-editorial}")
    public @ResponseBody
    ObtenerPaginaEditorialResponse obtenerEditorial(
            @PathVariable("url-editorial") final String urlEditorial) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(1);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlEditorial);

        return this.obtenerEditorialResponse(request);

    }

    private ObtenerPaginaEditorialResponse obtenerEditorialResponse(final ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaEditorialResponse editorialResponse = new ObtenerPaginaEditorialResponse();
        final List<LibroSimpleDTO> nueveLibrosEditorial = this.editorialService
                .obtenerLibrosEditorial(request.getUrlElemento(), 9, request.getNumeroPagina());
        editorialResponse.setNueveLibrosEditorial(nueveLibrosEditorial);
        editorialResponse.setEditorial(this.editorialService.obtenerEditorialByUrl(request.getUrlElemento()));
        editorialResponse.setTresUltimasEntradasEditorial(
                this.editorialService.obtenerUltimasEntradasEditorial(request.getUrlElemento(), 3, 1));
        editorialResponse.setNumeroLibros(this.editorialService.obtenerNumeroLibrosEditorial(request.getUrlElemento()));
        return editorialResponse;
    }

}
