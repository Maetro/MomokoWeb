package com.momoko.es.rest.util.controller;

import com.momoko.es.api.contact.dtos.ErrorEmailContactEnum;
import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.AuthorContactRequestDTO;
import com.momoko.es.api.dto.request.EditorContactRequestDTO;
import com.momoko.es.api.dto.request.PublisherContactRequestDTO;
import com.momoko.es.api.dto.request.SuscribeContactRequestDTO;
import com.momoko.es.api.dto.response.ObtenerPaginaBusquedaResponse;
import com.momoko.es.api.google.GoogleSearch;
import com.momoko.es.api.google.Item;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.jpa.model.service.ComentarioService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class UtilFrontController {

    private static final String ERROR = "ERROR";

    private final ValidadorService validationService;

    private final ComentarioService comentarioService;

    private final IndexService indexService;

    private final StorageService storageService;

    @Autowired
    public UtilFrontController(ValidadorService validationService, ComentarioService comentarioService, IndexService indexService, StorageService storageService) {
        this.validationService = validationService;
        this.comentarioService = comentarioService;
        this.indexService = indexService;
        this.storageService = storageService;
    }

    @GetMapping(path = "/health")
    public @ResponseBody
    String getHealth() {
        return "OK";
    }


    @PostMapping(path = "/sendEmailAuthor")
    ResponseEntity<String> sendContactEmail(@RequestBody final AuthorContactRequestDTO authorContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(authorContactRequestDTO);

        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(authorContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<>(ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("SEND", HttpStatus.OK);

    }

    @PostMapping(path = "/sendEmailEditor")
    ResponseEntity<String> sendContactEmailEditor(@RequestBody final EditorContactRequestDTO editorContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(editorContactRequestDTO);

        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(editorContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<>(ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("SEND", HttpStatus.OK);

    }

    @PostMapping(path = "/sendEmailPublisher")
    ResponseEntity<String> sendContactEmailPublisher(@RequestBody final PublisherContactRequestDTO publisherContactRequestDTO) {

        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(publisherContactRequestDTO);

        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(publisherContactRequestDTO);
            } catch (final Exception e) {
                return new ResponseEntity<>(ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("SEND", HttpStatus.OK);

    }

    @PostMapping(path = "/sendEmailSuscribe")
    ResponseEntity<String> sendEmailSuscribe(@RequestBody final SuscribeContactRequestDTO suscribeContactRequestDTO) {

        // Validar
        final List<ErrorEmailContactEnum> listaErrores = this.validationService.validateEmailContact(suscribeContactRequestDTO);

        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                this.comentarioService.sendContactEmail(suscribeContactRequestDTO);
                this.indexService.suscribirse(suscribeContactRequestDTO.getEmail());
            } catch (final Exception e) {
                return new ResponseEntity<>(ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("SEND", HttpStatus.OK);

    }

}
