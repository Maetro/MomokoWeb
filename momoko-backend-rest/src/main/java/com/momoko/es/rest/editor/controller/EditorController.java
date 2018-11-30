package com.momoko.es.rest.editor.controller;

import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.response.GuardarRedactorResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionRedactor;
import com.momoko.es.jpa.model.service.UserService;
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
public class EditorController {

    private static final Logger log = LoggerFactory.getLogger(EditorController.class);

    private final UserService userService;

    @Autowired
    public EditorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/redactores")
    public @ResponseBody
    List<RedactorDTO> getAllRedactores() {
        log.debug("getAllRedactores()");
        return this.userService.obtenerRedactoresMomoko();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/redactor/add")
    ResponseEntity<GuardarRedactorResponse> guardarRedactor(@RequestBody final RedactorDTO redactorDTO) {
        // Responder
        final GuardarRedactorResponse respuesta = new GuardarRedactorResponse();

        // Validar
        final List<ErrorCreacionRedactor> listaErrores = this.userService.validarRedactor(redactorDTO);

        // Guardar
        RedactorDTO redactorGuardado = null;
        String stackTrace = "";
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                redactorGuardado = this.userService.guardarRedactor(redactorDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionRedactor.ERROR_DESCONOCIDO);
                respuesta.setListaErroresValidacion(listaErrores);
                stackTrace = e.getMessage();
            }
        }

        respuesta.setRedactorDTO(redactorGuardado);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((redactorGuardado != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarRedactorResponse>(respuesta, HttpStatus.OK);

    }

}
