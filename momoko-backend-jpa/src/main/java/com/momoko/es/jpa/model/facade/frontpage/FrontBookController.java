package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.NameValue;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.service.FilterService;
import com.momoko.es.jpa.model.repository.filter.IDynamicFilterRepository;
import com.momoko.es.jpa.model.service.GenreService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es",
"http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class FrontBookController {

    private final GenreService genreService;


    @Autowired
    public FrontBookController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(path = "/applyfilter/{url-genre}")
    ResponseEntity<ApplyFilterResponseDTO> applyFilter(@PathVariable("url-genre") final String urlGenre,
                                                       @RequestBody final List<FilterDTO> appliedFilters) {
        ApplyFilterResponseDTO result = this.genreService.getBooksWithFilters(urlGenre, appliedFilters);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
