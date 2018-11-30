package com.momoko.es.rest.score.controller;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.response.AnadirPuntuacionResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorAnadirPuntuacionEnum;
import com.momoko.es.jpa.model.service.PuntuacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class ScoreController {

    private static final Logger log = LoggerFactory.getLogger(ScoreController.class);

    private final PuntuacionService puntuacionService;

    @Autowired
    public ScoreController(PuntuacionService puntuacionService) {
        this.puntuacionService = puntuacionService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, path = "/puntuacion/add")
    ResponseEntity<AnadirPuntuacionResponse> puntuarLibro(@RequestBody final PuntuacionDTO puntuacionDTO) {

        final AnadirPuntuacionResponse respuesta = new AnadirPuntuacionResponse();
        HttpStatus status = HttpStatus.OK;
        final List<ErrorAnadirPuntuacionEnum> listaErrores = this.puntuacionService.validarPuntuacion(puntuacionDTO);
        PuntuacionDTO puntuacion = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                puntuacion = this.puntuacionService.guardarPuntuacion(puntuacionDTO);
            } catch (final Exception e) {
                listaErrores.add(ErrorAnadirPuntuacionEnum.ERROR_EN_GUARDADO);
            }
        }
        respuesta.setPuntuacionDTO(puntuacion);
        if ((puntuacion != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(respuesta, status);
    }

}
