package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.AuthorContactRequestDTO;
import com.momoko.es.api.dto.request.EditorContactRequestDTO;
import com.momoko.es.api.dto.request.PublisherContactRequestDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.jpa.model.service.ComentarioService;
import com.momoko.es.jpa.model.service.GenreService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class FrontBookController {

    private final GenreService genreService;

    private final ValidadorService validationService;

    private final ComentarioService comentarioService;

    @Autowired
    public FrontBookController(GenreService genreService, ValidadorService validationService,
                               ComentarioService comentarioService) {
        this.genreService = genreService;
        this.validationService = validationService;
        this.comentarioService = comentarioService;
    }

    @PostMapping(path = "/applyfilter/{url-genre}")
    ResponseEntity<ApplyFilterResponseDTO> applyFilter(@PathVariable("url-genre") final String urlGenre,
                                                       @RequestBody final List<FilterDTO> appliedFilters) {
        ApplyFilterResponseDTO result = this.genreService.getBooksWithFilters(urlGenre, appliedFilters);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping( path = "/sendEmailAuthor")
    ResponseEntity<String> sendContactEmail(@RequestBody final AuthorContactRequestDTO authorContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(authorContactRequestDTO);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(authorContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<String>("SEND", HttpStatus.OK);

    }

    @PostMapping( path = "/sendEmailEditor")
    ResponseEntity<String> sendContactEmailEditor(@RequestBody final EditorContactRequestDTO editorContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(editorContactRequestDTO);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(editorContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<String>("SEND", HttpStatus.OK);

    }

    @PostMapping( path = "/sendEmailPublisher")
    ResponseEntity<String> sendContactEmailPublisher(@RequestBody final PublisherContactRequestDTO publisherContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(publisherContactRequestDTO);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(publisherContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<String>("SEND", HttpStatus.OK);

    }

}
