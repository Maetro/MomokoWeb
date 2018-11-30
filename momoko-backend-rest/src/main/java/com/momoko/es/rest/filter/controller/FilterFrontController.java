package com.momoko.es.rest.filter.controller;

import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.jpa.genre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class FilterFrontController {

    private final GenreService genreService;

    @Autowired
    public FilterFrontController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(path = "/applyfilter/{url-genre}")
    ResponseEntity<ApplyFilterResponseDTO> applyFilter(@PathVariable("url-genre") final String urlGenre,
                                                       @RequestBody final List<FilterDTO> appliedFilters) {
        ApplyFilterResponseDTO result = this.genreService.getBooksWithFilters(urlGenre, appliedFilters);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
