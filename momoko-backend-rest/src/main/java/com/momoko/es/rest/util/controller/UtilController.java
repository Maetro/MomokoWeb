package com.momoko.es.rest.util.controller;

import com.momoko.es.api.author.service.AuthorService;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaUrlDTO;
import com.momoko.es.api.dto.response.InformacionGeneralResponse;
import com.momoko.es.jpa.genre.service.GenreService;
import com.momoko.es.jpa.model.service.HerramientasService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.SagaService;
import com.momoko.es.jpa.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class UtilController {

    private static final Logger log = LoggerFactory.getLogger(UtilController.class);

    private final HerramientasService herramientasService;
    private final AuthorService authorService;
    private final LibroService libroService;
    private final GenreService generoService;
    private final UserService userService;
    private final SagaService sagaService;



    @Autowired
    public UtilController(HerramientasService herramientasService, AuthorService authorService,
                          LibroService libroService, GenreService generoService, UserService userService,
                          SagaService sagaService) {
        this.herramientasService = herramientasService;
        this.authorService = authorService;
        this.libroService = libroService;
        this.generoService = generoService;
        this.userService = userService;
        this.sagaService = sagaService;
    }

    @GetMapping(path = "/entradasUrls")
    public @ResponseBody
    List<EntradaUrlDTO> getAllUrls() {
        log.debug("getAllUrls()");
        return this.herramientasService.obtenerUrlsOrdenadasPorLongitud();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/informacionGeneral")
    ResponseEntity<InformacionGeneralResponse> getInformacionGeneral() {

        // Responder
        final InformacionGeneralResponse respuesta = new InformacionGeneralResponse();
        final List<String> autores = this.authorService.getAllAuthorNames();
        final List<String> editoriales = this.libroService.obtenerListaNombresEditoriales();
        final List<String> libros = this.libroService.obtenerListaTitulosLibros();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<String> nicksEditores = this.userService.getNombresEditores();
        final List<String> sagas = this.sagaService.getNombresSagas();
        respuesta.setNombresEditoriales(editoriales);
        respuesta.setTitulosLibros(libros);
        respuesta.setCategorias(categorias);
        respuesta.setNombresAutores(autores);
        respuesta.setNicksEditores(nicksEditores);
        respuesta.setSagas(sagas);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

}
