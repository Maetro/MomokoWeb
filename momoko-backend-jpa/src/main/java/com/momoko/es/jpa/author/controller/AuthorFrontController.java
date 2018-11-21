package com.momoko.es.jpa.author.controller;

import com.momoko.es.api.author.response.AuthorPageResponse;
import com.momoko.es.api.author.service.AuthorService;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaRedactorResponse;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.jpa.model.util.ConversionUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public/author")
public class AuthorFrontController {

    @Autowired
    public AuthorService authorService;

    @GetMapping(path = "/{url-author}")
    public @ResponseBody
    AuthorPageResponse obtenerEditor(
            @PathVariable("url-author") final String urlAuthor) {
        AuthorPageResponse authorPageResponse = authorService.getAuthorAndAuthorBooksByUrl(urlAuthor);
        return authorPageResponse;
    }


}
