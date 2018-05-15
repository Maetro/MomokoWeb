/**
 * MainController.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.response.*;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.*;
import com.momoko.es.api.exceptions.ErrorEnGuardadoReconocidoException;
import com.momoko.es.backend.model.service.*;
import com.momoko.es.util.MomokoUtils;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es" })
@RequestMapping(path = "/modelo")
public class ModeloController {

    private static final Logger log = LoggerFactory.getLogger(ModeloController.class);

    @Autowired(required = false)
    private LibroService libroService;

    @Autowired(required = false)
    private SagaService sagaService;

    @Autowired(required = false)
    private GeneroService generoService;

    @Autowired(required = false)
    private EntradaService entradaService;

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private PuntuacionService puntuacionService;

    @Autowired(required = false)
    private GaleriaService galeriaService;

    @Autowired(required = false)
    private EditorialService editorialService;

    @Autowired(required = false)
    private UserService userService;

    @GetMapping(path = "/libros")
    public @ResponseBody Iterable<LibroDTO> getAllLibros() {
        log.debug("Llamada a la lista de libros");
        return this.libroService.recuperarLibros();
    }

    @GetMapping(path = "/generos")
    public @ResponseBody Iterable<GeneroDTO> getAllGeneros() {
        return this.generoService.obtenerTodosGeneros();
    }

    @GetMapping(path = "/redactores")
    public @ResponseBody List<RedactorDTO> getAllRedactores() {
        final List<RedactorDTO> redactores = this.userService.obtenerRedactoresMomoko();
        return redactores;
    }

    @GetMapping(path = "/editoriales")
    public @ResponseBody List<EditorialDTO> getAllEditoriales() {
        return this.editorialService.recuperarEditoriales();
    }

    @GetMapping(path = "/galerias")
    public @ResponseBody Iterable<GaleriaDTO> getAllGalerias() {
        return this.galeriaService.obtenerTodasGalerias();
    }

    @GetMapping(path = "/sagas")
    public @ResponseBody List<SagaDTO> getAllSagas() {
        return this.sagaService.recuperarSagas();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/libros/add")
    ResponseEntity<GuardarLibroResponse> add(@RequestBody final LibroDTO libroDTO) {

        // Validar
        final List<ErrorCreacionLibro> listaErrores = this.validadorService.validarLibro(libroDTO);

        // Guardar
        LibroDTO libro = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                libro = this.libroService.guardarLibro(libroDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionLibro.TITULO_YA_EXISTE);
            }
        }

        if (libroDTO.getNotaMomoko() != null && libro != null) {
            final PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
            puntuacionDTO.setValor(libroDTO.getNotaMomoko());
            puntuacionDTO.setComentario(libroDTO.getComentarioNotaMomoko());
            puntuacionDTO.setEsPuntuacionMomoko(true);
            puntuacionDTO.setLibroId(libro.getLibroId());
            try {
                this.puntuacionService.guardarPuntuacion(puntuacionDTO);
            } catch (final Exception e) {
                listaErrores.add(ErrorCreacionLibro.PUNTUACION_YA_EXISTE);
            }
        }

        // Responder
        final GuardarLibroResponse respuesta = new GuardarLibroResponse();
        respuesta.setLibroDTO(libro);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((libro != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarLibroResponse>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/sagas/add")
    ResponseEntity<GuardarSagaResponse> add(@RequestBody final SagaDTO sagaDTO) {

        // Validar
        final List<ErrorCreacionSaga> listaErrores = this.validadorService.validarSaga(sagaDTO);

        // Guardar
        SagaDTO saga = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                saga = this.sagaService.guardarSaga(sagaDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionSaga.ERROR_GUARDADO_SAGA);
            }

            if (sagaDTO.getNotaSaga() != null && saga != null) {
                final PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
                puntuacionDTO.setValor(new BigDecimal(sagaDTO.getNotaSaga()));
                puntuacionDTO.setEsPuntuacionMomoko(true);
                puntuacionDTO.setSagaId(saga.getSagaId());
                try {
                    this.puntuacionService.guardarPuntuacion(puntuacionDTO);
                } catch (final Exception e) {
                    e.printStackTrace();
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

        return new ResponseEntity<GuardarSagaResponse>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/generos/add")
    ResponseEntity<GuardarGeneroResponse> addGenero(@RequestBody final GeneroDTO generoDTO) {

        // Validar
        final List<ErrorCreacionGenero> listaErrores = this.validadorService.validarGenero(generoDTO);

        // Guardar
        GeneroDTO genero = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                genero = this.generoService.guardarGenero(generoDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionGenero.GENERO_YA_EXISTE);
            }
        }

        // Responder
        final GuardarGeneroResponse respuesta = new GuardarGeneroResponse();
        respuesta.setGeneroDTO(genero);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((genero != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarGeneroResponse>(respuesta, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/redactor/add")
    ResponseEntity<GuardarRedactorResponse> guardarRedactor(@RequestBody final RedactorDTO redactorDTO) {
        // Responder
        final GuardarRedactorResponse respuesta = new GuardarRedactorResponse();

        // Validar
        final List<ErrorCreacionRedactor> listaErrores = this.validadorService.validarRedactor(redactorDTO);

        // Guardar
        RedactorDTO redactorGuardado = null;
        String stackTrace = "";
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                redactorGuardado = this.userService.guardarRedactor(redactorDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionRedactor.ERROR_DESCONOCIDO);
                respuesta.s
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/editorial/add")
    ResponseEntity<GuardarEditorialResponse> guardarEditorial(@RequestBody final EditorialDTO editorialDTO) {

        final GuardarEditorialResponse respuesta = new GuardarEditorialResponse();
        // Validar
        final List<ErrorCreacionEditorial> listaErrores = this.validadorService.validarEditorial(editorialDTO);

        // Guardar
        EditorialDTO editorialGuardada = null;
        String stackTrace = "";
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


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/galerias/add")
    ResponseEntity<GuardarGaleriaResponse> addGaleria(@RequestBody final GaleriaDTO galeriaDTO) {

        // Validar
        final List<ErrorCreacionGaleria> listaErrores = this.validadorService.validarGaleria(galeriaDTO);
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

    @RequestMapping(method = RequestMethod.GET, path = "/informacionGeneral")
    ResponseEntity<InformacionGeneralResponse> getInformacionGeneral() {

        // Responder
        final InformacionGeneralResponse respuesta = new InformacionGeneralResponse();
        final List<String> autores = this.libroService.obtenerListaNombresAutores();
        final List<String> editoriales = this.libroService.obtenerListaNombresEditoriales();
        final List<String> libros = this.libroService.obtenerListaTitulosLibros();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<String> nicksEditores = this.userService.getNombresEditores();
        respuesta.setNombresEditoriales(editoriales);
        respuesta.setTitulosLibros(libros);
        respuesta.setCategorias(categorias);
        respuesta.setNombresAutores(autores);
        respuesta.setNicksEditores(nicksEditores);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @GetMapping(path = "/entradas")
    public @ResponseBody List<EntradaSimpleDTO> getAllEntradas() {
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
        final List<ErrorCreacionEntrada> listaErrores = this.validadorService.validarEntrada(entradaDTO);

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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, path = "/puntuacion/add")
    ResponseEntity<AnadirPuntuacionResponse> puntuarLibro(@RequestBody final PuntuacionDTO puntuacionDTO) {

        final AnadirPuntuacionResponse respuesta = new AnadirPuntuacionResponse();
        HttpStatus status = HttpStatus.OK;
        final List<ErrorAnadirPuntuacionEnum> listaErrores = this.validadorService.validarPuntuacion(puntuacionDTO);
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
