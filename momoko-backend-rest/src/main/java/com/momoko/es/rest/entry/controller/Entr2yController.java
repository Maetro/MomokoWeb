package com.momoko.es.rest.entry.controller;

import com.momoko.es.api.entry.dto.EntradaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.entry.request.EditEntryRequest;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/model")
public class Entr2yController {

    private static final Logger log = LoggerFactory.getLogger(Entr2yController.class);

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private EntradaService entradaService;



}
