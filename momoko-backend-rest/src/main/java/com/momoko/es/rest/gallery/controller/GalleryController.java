package com.momoko.es.rest.gallery.controller;

import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.dto.response.GuardarGaleriaResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionGaleria;
import com.momoko.es.api.exceptions.ErrorEnGuardadoReconocidoException;
import com.momoko.es.jpa.model.service.GaleriaService;
import com.momoko.es.jpa.model.util.MomokoUtils;
import com.momoko.es.rest.publisher.controller.PublisherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class GalleryController {

    private static final Logger log = LoggerFactory.getLogger(PublisherController.class);

    private final GaleriaService galeriaService;

    @Autowired
    public GalleryController(GaleriaService galeriaService) {
        this.galeriaService = galeriaService;
    }

    @GetMapping(path = "/galerias")
    public @ResponseBody
    Iterable<GaleriaDTO> getAllGalerias() {
        log.debug("getAllGalerias");
        return this.galeriaService.obtenerTodasGalerias();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/galerias/add")
    ResponseEntity<GuardarGaleriaResponse> addGaleria(@RequestBody final GaleriaDTO galeriaDTO) {

        // Validar
        final List<ErrorCreacionGaleria> listaErrores = this.galeriaService.validarGaleria(galeriaDTO);
        final List<String> mensajeError = new ArrayList<>();
        // Guardar
        GaleriaDTO galeria = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                galeria = this.galeriaService.guardarGaleria(galeriaDTO);
            } catch (final ErrorEnGuardadoReconocidoException e) {

                listaErrores.add(ErrorCreacionGaleria.ERROR_EN_GUARDADO);
                mensajeError.add(e.getMessage());
                log.error(MomokoUtils.ERROR, e);

            } catch (final Exception e) {
                log.error(MomokoUtils.ERROR, e);
                listaErrores.add(ErrorCreacionGaleria.ERROR_EN_GUARDADO);
            }
        }

        // Responder
        final GuardarGaleriaResponse respuesta = new GuardarGaleriaResponse();
        respuesta.setGaleria(galeria);
        respuesta.setListaErroresValidacion(listaErrores);
        respuesta.setMensajeError(mensajeError);
        if ((galeria != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

}
