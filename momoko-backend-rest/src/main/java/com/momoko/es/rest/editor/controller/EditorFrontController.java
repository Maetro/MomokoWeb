package com.momoko.es.rest.editor.controller;

import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaRedactorResponse;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.rest.util.ControllerUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class EditorFrontController {

    private static final Logger log = LoggerFactory.getLogger(EditorFrontController.class);

    private final UserService userService;
    private final EntradaService entradaService;
    private final StorageService almacenImagenes;

    @Autowired
    public EditorFrontController(UserService userService, EntradaService entradaService,
                                 StorageService almacenImagenes) {
        this.userService = userService;
        this.entradaService = entradaService;
        this.almacenImagenes = almacenImagenes;
    }


    @GetMapping(path = "/redactor/{url-redactor}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaRedactorResponse obtenerEditor(
            @PathVariable("url-redactor") final String urlRedactor,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(numeroPagina);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlRedactor);

        return obtenerRedactorResponse(request);

    }

    @GetMapping(path = "/redactor/{url-redactor}")
    public @ResponseBody
    ObtenerPaginaRedactorResponse obtenerEditor(
            @PathVariable("url-redactor") final String urlRedactor,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(1);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlRedactor);

        return this.obtenerRedactorResponse(request);

    }

    private ObtenerPaginaRedactorResponse obtenerRedactorResponse(final ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaRedactorResponse redactorResponse = new ObtenerPaginaRedactorResponse();
        final StopWatch stopWatch = new StopWatch("obtenerEditorResponse()");
        stopWatch.start("Obtener Nueve entradas editor");
        final List<EntradaSimpleDTO> nueveEntradasEditor = this.entradaService
                .obtenerEntradasEditorPorFecha(request.getUrlElemento(), 9, request.getNumeroPagina());
        ControllerUtil.generateThumbnailsOfEntrySimpleDTOS(nueveEntradasEditor, this.almacenImagenes,370, 208, true);
        redactorResponse.setNueveEntradasEditor(nueveEntradasEditor);
        stopWatch.stop();
        stopWatch.start("Obtener Redactor");
        if (CollectionUtils.isNotEmpty(nueveEntradasEditor)) {
            final String urlEditor = nueveEntradasEditor.iterator().next().getUrlEditor();

            RedactorDTO redactorDTO;
            try {
                redactorDTO = this.userService.findRedactorByUrl(urlEditor);
                redactorResponse.setRedactor(redactorDTO);
                final String imageServer = this.almacenImagenes.getUrlImageServer();
                if (redactorDTO.getAvatarRedactor() != null) {
                    try {
                        redactorDTO.setAvatarRedactor(
                                this.almacenImagenes.obtenerMiniatura(redactorDTO.getAvatarRedactor(), 170, 170, true));
                    } catch (final IOException e) {
                        redactorDTO.setAvatarRedactor(ConversionUtils.obtenerGravatar(redactorDTO.getEmail()));
                    }
                } else {
                    redactorDTO.setAvatarRedactor(ConversionUtils.obtenerGravatar(redactorDTO.getEmail()));
                }
                if (redactorDTO.getImagenCabeceraRedactor() != null) {
                    redactorDTO.setImagenCabeceraRedactor(imageServer + redactorDTO.getImagenCabeceraRedactor());
                } else {
                    redactorDTO.setImagenCabeceraRedactor("/assets/style/images/art/parallax2.jpg");
                }
            } catch (final UserNotFoundException e) {
                e.printStackTrace();
            }

        }
        stopWatch.stop();

        stopWatch.start("Obtener Numero libros editor");
        redactorResponse.setNumeroEntradas(this.entradaService.obtenerNumeroEntradasEditor(request.getUrlElemento()));
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return redactorResponse;
    }




}
