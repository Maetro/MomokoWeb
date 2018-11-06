package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.ContactRequestDTO;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
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



    @PostMapping( path = "/sendEmail")
    ResponseEntity<Boolean> sendContactEmail(@RequestBody final ContactRequestDTO contactRequest) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(contactRequest);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(contactRequest);
            } catch (final Exception e) {
                return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
            }
        }
        try {
            this.comentarioService.enviarNotificacion(comentarioDTO);
        } catch (final Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

}
