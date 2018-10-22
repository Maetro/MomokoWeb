package com.momoko.es.jpa.author.controller;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.response.SaveAuthorResponse;
import com.momoko.es.api.author.service.AuthorService;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.response.GuardarLibroResponse;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/model/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired(required = false)
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public @ResponseBody
    Set<AuthorDTO> getAllAuthors() {
        final Set<AuthorDTO> authors = this.authorService.getAllAuthors();
        return authors;
    }

    @GetMapping(path = "/{urlAuthor}")
    public @ResponseBody
    AuthorDTO getAuthor(@PathVariable("urlAuthor") String urlAuthor) {

        final AuthorDTO author = this.authorService.getAuthorByUrl(urlAuthor);
        return author;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/add")
    ResponseEntity<SaveAuthorResponse> saveAuthor(@RequestBody final AuthorDTO authorDTO) throws Exception {

        SaveAuthorResponse response = this.authorService.saveAuthor(authorDTO);
        return new ResponseEntity<SaveAuthorResponse>(response, HttpStatus.OK);

    }

}
