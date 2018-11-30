package com.momoko.es.rest.book.controller;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.jpa.model.service.LibroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class BookController {


    private final LibroService libroService;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping(path = "/libros")
    public @ResponseBody
    Iterable<LibroDTO> getAllLibros() {
        log.debug("Llamada a la lista de libros");
        return this.libroService.recuperarLibros();
    }

}
