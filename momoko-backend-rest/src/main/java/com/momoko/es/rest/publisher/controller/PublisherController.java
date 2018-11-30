package com.momoko.es.rest.publisher.controller;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.response.GuardarEditorialResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;
import com.momoko.es.jpa.publisher.EditorialService;
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
public class PublisherController {

    private static final Logger log = LoggerFactory.getLogger(PublisherController.class);

    private final EditorialService editorialService;

    @Autowired
    public PublisherController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping(path = "/editoriales")
    public @ResponseBody
    List<EditorialDTO> getAllEditoriales() {
        log.debug("getAllEditoriales()");
        return this.editorialService.recuperarEditoriales();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/editorial/add")
    ResponseEntity<GuardarEditorialResponse> guardarEditorial(@RequestBody final EditorialDTO editorialDTO) {

        final GuardarEditorialResponse respuesta = new GuardarEditorialResponse();
        // Validar
        final List<ErrorCreacionEditorial> listaErrores = this.editorialService.validarEditorial(editorialDTO);

        // Guardar
        EditorialDTO editorialGuardada = null;
        final String stackTrace = "";
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                editorialGuardada = this.editorialService.guardarEditorial(editorialDTO);
            } catch (final Exception e) {
                log.error("ERROR", e);
                listaErrores.add(ErrorCreacionEditorial.ERROR_DESCONOCIDO);
                respuesta.setStackTrace(stackTrace);
            }
        }

        // Responder

        respuesta.setEditorialDTO(editorialGuardada);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((editorialGuardada != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

}
