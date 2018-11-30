package com.momoko.es.rest.entry.controller;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.response.GuardarEntradaResponse;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionEntrada;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.util.MomokoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class EntryController {

    private static final Logger log = LoggerFactory.getLogger(EntryController.class);

    private final EntradaService entradaService;

    @Autowired
    public EntryController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping(path = "/entradas")
    public @ResponseBody
    List<EntradaSimpleDTO> getAllEntradas() {
        return this.entradaService.recuperarEntradasSimples();
    }

    public @ResponseBody List<EntradaSimpleDTO> getAllEntradasSimples() {
        return this.entradaService.recuperarEntradasSimples();
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody EntradaDTO getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Obtener entrada: %s", urlEntrada));
        }
        final ObtenerEntradaResponse entrada = this.entradaService.obtenerEntradaParaGestion(urlEntrada);
        return entrada.getEntrada();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/entradas/add")
    ResponseEntity<GuardarEntradaResponse> guardarEntrada(@RequestBody final EntradaDTO entradaDTO) {

        // Validar
        final List<ErrorCreacionEntrada> listaErrores = this.entradaService.validarEntrada(entradaDTO);

        // Guardar
        EntradaDTO entrada = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                entrada = this.entradaService.guardarEntrada(entradaDTO);
            } catch (final Exception e) {
                listaErrores.add(ErrorCreacionEntrada.ERROR_EN_GUARDADO);
                log.error(MomokoUtils.ERROR, e);
            }
        }

        // Responder
        final GuardarEntradaResponse respuesta = new GuardarEntradaResponse();
        respuesta.setEntradaDTO(entrada);
        respuesta.setListaErroresValidacion(listaErrores);
        HttpStatus status = HttpStatus.OK;
        if ((entrada != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(respuesta, status);

    }
}
