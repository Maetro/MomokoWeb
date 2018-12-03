package com.momoko.es.rest.author.controller;

import com.momoko.es.api.author.response.AuthorPageResponse;
import com.momoko.es.api.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
