package com.momoko.es.rest.saga.controller;

import com.momoko.es.api.dto.PuntuacionDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.response.GuardarSagaResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionSaga;
import com.momoko.es.jpa.model.service.PuntuacionService;
import com.momoko.es.jpa.model.service.SagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class SagaController {

    private static final Logger log = LoggerFactory.getLogger(SagaController.class);

    private final SagaService sagaService;

    private final PuntuacionService puntuacionService;

    @Autowired
    public SagaController(SagaService sagaService, PuntuacionService puntuacionService) {
        this.sagaService = sagaService;
        this.puntuacionService = puntuacionService;
    }

    @GetMapping(path = "/sagas")
    public @ResponseBody
    List<SagaDTO> getAllSagas() {
        log.debug("getAllSagas()");
        return this.sagaService.recuperarSagas();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/sagas/add")
    ResponseEntity<GuardarSagaResponse> saveSaga(@RequestBody final SagaDTO sagaDTO) {
        log.debug("saveSaga(): {}", sagaDTO);
        // Validar
        final List<ErrorCreacionSaga> listaErrores = this.sagaService.validarSaga(sagaDTO);

        // Guardar
        SagaDTO saga = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                saga = this.sagaService.guardarSaga(sagaDTO);
            } catch (final Exception e) {
                log.error(e.getMessage(), e);
                listaErrores.add(ErrorCreacionSaga.ERROR_GUARDADO_SAGA);
            }

            if ((sagaDTO.getNotaSaga() != null) && (saga != null)) {
                final PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
                puntuacionDTO.setValor(new BigDecimal(sagaDTO.getNotaSaga()));
                puntuacionDTO.setEsPuntuacionMomoko(true);
                puntuacionDTO.setSagaId(saga.getSagaId());
                try {
                    this.puntuacionService.guardarPuntuacion(puntuacionDTO);
                } catch (final Exception e) {
                    log.error(e.getMessage(), e);
                    listaErrores.add(ErrorCreacionSaga.ERROR_GUARDADO_PUNTUACION);
                }
            }
        }
        // Responder
        final GuardarSagaResponse respuesta = new GuardarSagaResponse();
        respuesta.setSagaDTO(saga);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((saga != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

}
