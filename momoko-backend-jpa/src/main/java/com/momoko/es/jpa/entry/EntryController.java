package com.momoko.es.jpa.entry;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.jpa.model.facade.ModeloController;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.LibroService;
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
public class EntryController {

    private static final Logger log = LoggerFactory.getLogger(ModeloController.class);

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private EntradaService entradaService;

    @GetMapping(path = "/entry")
    public @ResponseBody
    List<EntradaSimpleDTO> getAllBooks() {
        final List<EntradaSimpleDTO> entries = this.entradaService.recuperarEntradasSimples();
        return entries;
    }

    @GetMapping(path = "/entry/{urlEntry}")
    public @ResponseBody
    EntradaDTO getEntry(@PathVariable("urlEntry") String urlEntry) {

        if (log.isDebugEnabled()) {
            log.debug(String.format("Obtener entrada: %s", urlEntry));
        }
        final ObtenerEntradaResponse entrada = this.entradaService.obtenerEntradaParaGestion(urlEntry);
        return entrada.getEntrada();
    }

}
