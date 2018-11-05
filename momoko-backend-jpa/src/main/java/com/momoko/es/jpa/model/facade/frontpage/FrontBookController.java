package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.ContactRequestDTO;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.jpa.model.service.GenreService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class FrontBookController {

    private final GenreService genreService;

    private final ValidadorService validationService;


    @Autowired
    public FrontBookController(GenreService genreService, ValidadorService validationService) {
        this.genreService = genreService;
        this.validationService = validationService;
    }

    @PostMapping(path = "/applyfilter/{url-genre}")
    ResponseEntity<ApplyFilterResponseDTO> applyFilter(@PathVariable("url-genre") final String urlGenre,
                                                       @RequestBody final List<FilterDTO> appliedFilters) {
        ApplyFilterResponseDTO result = this.genreService.getBooksWithFilters(urlGenre, appliedFilters);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping( path = "/contact/add")
    ResponseEntity<GuardarComentarioResponse> sendContactEmail(@RequestBody final ContactRequestDTO contactRequest) {

        // Validar
        final List<ErrorCreacionComentario> listaErrores = this.validationService.validateEmailContact(contactRequest);

        // Guardar
        ComentarioDTO comentarioDTO = null;
//        if (CollectionUtils.isEmpty(listaErrores)) {
//            try {
//                comentarioDTO = this.contactService.guardarComentario(comentario);
//            } catch (final Exception e) {
//                listaErrores.add(ErrorCreacionComentario.ERROR_EN_GUARDADO);
//            }
//        }
//        try {
//            this.comentarioService.enviarNotificacion(comentarioDTO);
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

        // Responder
        final GuardarComentarioResponse respuesta = new GuardarComentarioResponse();
        respuesta.setComentario(comentarioDTO);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((comentarioDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarComentarioResponse>(respuesta, HttpStatus.OK);

    }

}
