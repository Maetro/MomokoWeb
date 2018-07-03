/**
 * PublicFacade.java 26-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.InitDataDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.ResultadoBusquedaDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.request.ObtenerPaginaGeneroRequest;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.api.dto.response.ObtenerFichaSagaResponse;
import com.momoko.es.api.dto.response.ObtenerIndexDataReponseDTO;
import com.momoko.es.api.dto.response.ObtenerPaginaBusquedaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaCategoriaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaEditorialResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaEtiquetaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaGeneroResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaLibroNoticiasResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaRedactorResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.enums.TipoVisitaEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConUrl;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.api.google.GoogleSearch;
import com.momoko.es.api.google.Item;
import com.momoko.es.backend.model.service.BuscadorService;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EditorialService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.EtiquetaService;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.SagaService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.backend.model.service.TrackService;
import com.momoko.es.backend.model.service.UserService;
import com.momoko.es.backend.model.service.ValidadorService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.Mail;
import com.momoko.es.util.NotFoundException;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es", "https://momoko.es" })
@RequestMapping(path = "/public")
public class PublicFacade {

    private static final Logger log = LoggerFactory.getLogger(PublicFacade.class);

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private LibroService libroService;

    @Autowired(required = false)
    private ComentarioService comentarioService;

    @Autowired
    private ValidadorService validadorService;

    @Autowired(required = false)
    private GeneroService generoService;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired(required = false)
    private EtiquetaService etiquetaService;

    @Autowired(required = false)
    private SagaService sagaService;

    @Autowired(required = false)
    private BuscadorService buscadorService;

    @Autowired(required = false)
    private EditorialService editorialService;

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private TrackService trackService;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/initData")
    public @ResponseBody InitDataDTO getInitData() {
        final StopWatch stopWatch = new StopWatch("getInitData()");
        stopWatch.start("Obtener Init Data");
        final List<MenuDTO> menu = this.indexService.obtenerMenu();

        final InitDataDTO initDataDTO = new InitDataDTO();
        initDataDTO.setMenu(menu);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return initDataDTO;
    }

    @GetMapping(path = "/indexData")
    public @ResponseBody ObtenerIndexDataReponseDTO
            getInfoIndex(@RequestHeader(value = "User-Agent") final String userAgent) {
        final StopWatch stopWatch = new StopWatch("getInfoIndex()");
        stopWatch.start("obtenerUltimasEntradas");
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        stopWatch.stop();
        stopWatch.start("obtenerLibrosMasVistos");
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        stopWatch.stop();
        stopWatch.start("obtenerUltimasFichas");
        final List<LibroSimpleDTO> ultimosAnalisis = this.indexService.obtenerUltimasFichas();
        stopWatch.stop();
        stopWatch.start("obtenerUltimoComicAnalizado");
        final LibroEntradaSimpleDTO ultimoComicAnalizado = this.indexService.obtenerUltimoComicAnalizado();
        stopWatch.stop();
        stopWatch.start("Crear objeto respuesta");
        final ObtenerIndexDataReponseDTO obtenerIndexDataResponseDTO = new ObtenerIndexDataReponseDTO();
        obtenerIndexDataResponseDTO.setUltimasEntradas(ultimasEntradas);
        obtenerIndexDataResponseDTO.setLibrosMasVistos(librosMasVistos);
        obtenerIndexDataResponseDTO.setUltimoComicAnalizado(ultimoComicAnalizado);
        obtenerIndexDataResponseDTO.setUltimosAnalisis(ultimosAnalisis);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return obtenerIndexDataResponseDTO;
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada,
            final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent)
            throws NotFoundException {
        ObtenerEntradaResponse respuesta = null;
        final StopWatch stopWatch = new StopWatch("getEntradaByUrl()");
        if (!urlEntrada.equals("not-found")) {

            stopWatch.start("obtenerEntrada");
            respuesta = this.entradaService.obtenerEntrada(urlEntrada, true);
            stopWatch.stop();
            stopWatch.start("almacenarVisitaEnBD");
            final String ip = getClientIp(request);
            if (respuesta.getEntrada() == null) {
                this.trackService.alamacenarVisitaBD(urlEntrada, TipoVisitaEnum.FALLO, ip);
            } else {
                this.trackService.alamacenarVisitaBD(urlEntrada, TipoVisitaEnum.ENTRADA, ip);
                if (CollectionUtils.isNotEmpty(respuesta.getEntrada().getLibrosEntrada())) {
                    for (final LibroDTO libroDTO : respuesta.getEntrada().getLibrosEntrada()) {
                        this.trackService.alamacenarVisitaBD(libroDTO.getUrlLibro(), TipoVisitaEnum.LIBRO, ip);
                    }
                }
            }
            stopWatch.stop();
            log.debug(stopWatch.prettyPrint());
        }
        if (respuesta.getEntrada() == null) {
            throw new NotFoundException("No se ha encontrado la entrada: " + urlEntrada);
        }
        return respuesta;
    }

    @GetMapping(path = "/suscribirse/{email}")
    public @ResponseBody ObtenerEntradaResponse suscribirse(@PathVariable("email") final String email) {

        this.indexService.suscribirse(email);
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        return respuesta;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro,
            final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerFichaLibroResponse respuesta = this.libroService.obtenerLibro(urlLibro);
        final String ip = getClientIp(request);
        if (respuesta.getLibro() != null) {
            respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
            if (CollectionUtils.isNotEmpty(respuesta.getCincoLibrosParecidos())) {
                final String url = this.almacenImagenes.getUrlImageServer();
                for (final LibroSimpleDTO libroSimple : respuesta.getCincoLibrosParecidos()) {

                    libroSimple.setPortada(url + libroSimple.getPortada());

                }
            }
        }
        if (respuesta.getLibro() != null) {
            this.trackService.alamacenarVisitaBD(respuesta.getLibro().getUrlLibro(), TipoVisitaEnum.LIBRO, ip);
        }
        return respuesta;
    }

    @GetMapping(path = "/saga/{url-saga}")
    public @ResponseBody ObtenerFichaSagaResponse obtenerSaga(@PathVariable("url-saga") final String urlSaga,
            final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerFichaSagaResponse sagaResponse = new ObtenerFichaSagaResponse();
        SagaDTO sagaDTO = null;
        try {
            sagaDTO = this.sagaService.obtenerSaga(urlSaga);
            sagaResponse.setSaga(sagaDTO);
            final List<LibroDTO> librosSaga = this.libroService.obtenerLibros(sagaDTO.getLibrosSaga());
            sagaResponse.setLibrosSaga(librosSaga);
            final List<EntradaSimpleDTO> entradasSaga = this.sagaService.obtenerEntradasSaga(sagaDTO);
            sagaResponse.setTresUltimasEntradas(entradasSaga);

            final List<EntradaSimpleDTO> entradasLibrosSaga = this.sagaService.obtenerEntradasLibrosSaga(sagaDTO);
            sagaResponse.setTresUltimasEntradasLibros(entradasLibrosSaga);
        } catch (final NoSeEncuentraElementoConUrl e) {
            e.printStackTrace();
        }
        if (sagaResponse.getSaga() != null) {
            final String ip = getClientIp(request);
            this.trackService.alamacenarVisitaBD(sagaResponse.getSaga().getUrlSaga(), TipoVisitaEnum.SAGA, ip);
        }
        return sagaResponse;

    }

    @GetMapping(path = "/video/{url-video}")
    public @ResponseBody ObtenerEntradaResponse obtenerVideo(@PathVariable("url-video") final String urlVideo,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerEntradaResponse respuesta = this.entradaService.obtenerEntradaVideo(urlVideo);
        // respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return respuesta;
    }

    @GetMapping(path = "/test")
    public @ResponseBody String obtenerVideo() {
        final String response = this.passwordEncoder.encode("test");
        // respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/comentario/add")
    ResponseEntity<GuardarComentarioResponse> addComentario(@RequestBody final NuevoComentarioRequest comentario) {

        // Validar
        final List<ErrorCreacionComentario> listaErrores = this.validadorService.validarComentario(comentario);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                comentarioDTO = this.comentarioService.guardarComentario(comentario);
                this.comentarioService.enviarNotificacion(comentarioDTO);
            } catch (final Exception e) {
                listaErrores.add(ErrorCreacionComentario.ERROR_EN_GUARDADO);
            }
        }

        // Responder
        final GuardarComentarioResponse respuesta = new GuardarComentarioResponse();
        respuesta.setComentario(comentarioDTO);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((comentarioDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        Mail.sendEmail("Nuevo comentario en momoko",
                "Hay un nuevo comentario en momoko en la entrada: " + comentario.getEntradaId(), "RMaetro@gmail.com");
        Mail.sendEmail("Nuevo comentario en momoko",
                "Hay un nuevo comentario en momoko en la entrada: " + comentario.getEntradaId(),
                "kizuna.owo@gmail.com");
        return new ResponseEntity<GuardarComentarioResponse>(respuesta, HttpStatus.OK);

    }

    @GetMapping(path = "/genero/{url-genero}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaGeneroResponse obtenerGeneroPagina(
            @PathVariable("url-genero") final String urlGenero,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaGeneroResponse categoriaResponse = new ObtenerPaginaGeneroResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<EntradaSimpleDTO>();

        final ObtenerPaginaGeneroRequest request = new ObtenerPaginaGeneroRequest();
        request.setNumeroPagina(numeroPagina);
        request.setOrdenarPor("fecha");
        request.setUrlGenero(urlGenero);

        return obtenerGenero(urlGenero, request, userAgent);

    }

    @GetMapping(path = "/genero/{url-genero}")
    public @ResponseBody ObtenerPaginaGeneroResponse obtenerGenero(@PathVariable("url-genero") final String urlGenero,
            @RequestBody(required = false) ObtenerPaginaGeneroRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaGeneroResponse generoResponse = new ObtenerPaginaGeneroResponse();
        if (request == null) {
            request = new ObtenerPaginaGeneroRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlGenero(urlGenero);
        }
        final GeneroDTO generoDTO = this.generoService.obtenerGeneroPorUrl(urlGenero);
        final List<LibroSimpleDTO> librosGenero = this.libroService.obtenerLibrosConAnalisisGeneroPorFecha(generoDTO, 9,
                request.getNumeroPagina() - 1);
        final Integer numeroLibros = this.libroService.obtenerNumeroLibrosConAnalisisGenero(generoDTO);
        for (final LibroSimpleDTO libroSimpleDTO : librosGenero) {
            if (libroSimpleDTO.getPortada() != null) {
                try {
                    libroSimpleDTO.setPortada(
                            this.almacenImagenes.obtenerMiniatura(libroSimpleDTO.getPortada(), 240, 350, false));

                    final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes
                            .getImageDimensions(libroSimpleDTO.getPortada());
                    libroSimpleDTO.setPortadaHeight(alturaAnchura.getAltura());
                    libroSimpleDTO.setPortadaWidth(alturaAnchura.getAnchura());

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        final List<EntradaSimpleDTO> tresUltimasEntradasConLibro = this.entradaService
                .obtenerTresUltimasEntradasPopularesConLibro();

        generoResponse.setGenero(generoDTO);
        generoResponse.setNumeroLibros(numeroLibros);
        generoResponse.setNueveLibrosGenero(librosGenero);
        generoResponse.setTresUltimasEntradasConLibro(tresUltimasEntradasConLibro);
        return generoResponse;

    }

    @GetMapping(path = "/categoria/{url-categoria}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaCategoriaResponse obtenerCategoria(
            @PathVariable("url-categoria") final String urlCategoria,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaCategoriaResponse categoriaResponse = new ObtenerPaginaCategoriaResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(numeroPagina);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlCategoria);
        }
        return obtenerCategoriaResponse(urlCategoria, request, categoriaResponse, entradasCategoria);

    }

    @GetMapping(path = "/categoria/{url-categoria}")
    public @ResponseBody ObtenerPaginaCategoriaResponse obtenerCategoria(
            @PathVariable("url-categoria") final String urlCategoria,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaCategoriaResponse categoriaResponse = new ObtenerPaginaCategoriaResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlCategoria);
        }
        return obtenerCategoriaResponse(urlCategoria, request, categoriaResponse, entradasCategoria);

    }

    @GetMapping(path = "/editorial/{url-editorial}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaEditorialResponse obtenerEditorial(
            @PathVariable("url-editorial") final String urlEditorial,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(numeroPagina);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlEditorial);

        return obtenerEditorialResponse(request);

    }

    @GetMapping(path = "/editorial/{url-editorial}")
    public @ResponseBody ObtenerPaginaEditorialResponse obtenerEditorial(
            @PathVariable("url-editorial") final String urlEditorial,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(1);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlEditorial);

        return obtenerEditorialResponse(request);

    }

    private ObtenerPaginaEditorialResponse obtenerEditorialResponse(final ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaEditorialResponse editorialResponse = new ObtenerPaginaEditorialResponse();
        final StopWatch stopWatch = new StopWatch("obtenerEditorialResponse()");
        stopWatch.start("Obtener Nueve libros editorial");
        final List<LibroSimpleDTO> nueveLibrosEditorial = this.editorialService
                .obtenerLibrosEditorial(request.getUrlElemento(), 9, request.getNumeroPagina());

        editorialResponse.setNueveLibrosEditorial(nueveLibrosEditorial);
        stopWatch.stop();
        stopWatch.start("Obtener Editorial");
        editorialResponse.setEditorial(this.editorialService.obtenerEditorialByUrl(request.getUrlElemento()));
        stopWatch.stop();
        stopWatch.start("Obtener 3 ultimas entradas editorial");
        editorialResponse.setTresUltimasEntradasEditorial(
                this.editorialService.obtenerUltimasEntradasEditorial(request.getUrlElemento(), 3, 1));
        stopWatch.stop();
        stopWatch.start("Obtener Numero libros editorial");
        editorialResponse.setNumeroLibros(this.editorialService.obtenerNumeroLibrosEditorial(request.getUrlElemento()));
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return editorialResponse;
    }

    @GetMapping(path = "/redactor/{url-redactor}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaRedactorResponse obtenerEditor(
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
    public @ResponseBody ObtenerPaginaRedactorResponse obtenerEditor(
            @PathVariable("url-redactor") final String urlRedactor,
            @RequestHeader(value = "User-Agent") final String userAgent) {

        final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
        request.setNumeroPagina(1);
        request.setOrdenarPor("fecha");
        request.setUrlElemento(urlRedactor);

        return obtenerRedactorResponse(request);

    }

    private ObtenerPaginaRedactorResponse obtenerRedactorResponse(final ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaRedactorResponse redactorResponse = new ObtenerPaginaRedactorResponse();
        final StopWatch stopWatch = new StopWatch("obtenerEditorResponse()");
        stopWatch.start("Obtener Nueve entradas editor");
        final List<EntradaSimpleDTO> nueveEntradasEditor = this.entradaService
                .obtenerEntradasEditorPorFecha(request.getUrlElemento(), 9, request.getNumeroPagina());
        for (final EntradaSimpleDTO entradaSimpleDTO : nueveEntradasEditor) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 370, 208, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    /**
     * Obtener categoria response.
     *
     * @param urlCategoria
     *            the url categoria
     * @param request
     *            the request
     * @param categoriaResponse
     *            the categoria response
     * @param entradasCategoria
     *            the entradas categoria
     * @return the obtener pagina categoria response
     */
    private ObtenerPaginaCategoriaResponse obtenerCategoriaResponse(final String urlCategoria,
            final ObtenerPaginaElementoRequest request, final ObtenerPaginaCategoriaResponse categoriaResponse,
            final List<EntradaSimpleDTO> entradasCategoria) {
        final CategoriaDTO categoriaDTO = this.generoService.obtenerCategoriaPorUrl(urlCategoria);
        if (urlCategoria.equals("noticias")) {
            entradasCategoria.addAll(this.entradaService.obtenerNoticias(request));
            categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroNoticias());
        } else if (urlCategoria.equals("miscelaneos")) {
            entradasCategoria.addAll(this.entradaService.obtenerMiscelaneos(request));
            categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroMiscelaneos());
        } else if (urlCategoria.equals("videos")) {
            entradasCategoria.addAll(this.entradaService.obtenerVideos(request));
            categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroVideos());
        } else {
            entradasCategoria.addAll(
                    this.entradaService.obtenerEntradasCategoriaPorFecha(categoriaDTO, 9, request.getNumeroPagina()));
            categoriaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroEntradasCategoria(categoriaDTO));
        }
        for (final EntradaSimpleDTO entradaSimpleDTO : entradasCategoria) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 370, 208, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        categoriaResponse.setEntradasCategoria(entradasCategoria);
        categoriaResponse.setCategoria(categoriaDTO);
        return categoriaResponse;
    }

    @GetMapping(path = "/noticias-libro/{url-libro}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaLibroNoticiasResponse obtenerNoticiasLibroPagina(
            @PathVariable("url-libro") final String urlLibro, @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse = new ObtenerPaginaLibroNoticiasResponse();
        final List<EntradaSimpleDTO> noticias = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(numeroPagina);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlLibro);
        }
        return obtenerPaginaLibroNoticiasResponse(urlLibro, request, paginaLibroNoticiasResponse, noticias);

    }

    @GetMapping(path = "/noticias-libro/{url-libro}")
    public @ResponseBody ObtenerPaginaLibroNoticiasResponse obtenerNoticiasLibro(
            @PathVariable("url-libro") final String urlLibro,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse = new ObtenerPaginaLibroNoticiasResponse();
        final List<EntradaSimpleDTO> noticias = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlLibro);
        }
        return obtenerPaginaLibroNoticiasResponse(urlLibro, request, paginaLibroNoticiasResponse, noticias);

    }

    private ObtenerPaginaLibroNoticiasResponse obtenerPaginaLibroNoticiasResponse(final String urlLibro,
            final ObtenerPaginaElementoRequest request,
            final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse,
            final List<EntradaSimpleDTO> noticias) {
        final LibroDTO libro = this.libroService.obtenerLibro(urlLibro).getLibro();
        final List<DatoEntradaDTO> entradasSimples = libro.getEntradasLibro();
        int numeroEntradas = 0;

        for (final DatoEntradaDTO datoEntradaDTO : entradasSimples) {
            if (datoEntradaDTO.getTipoEntrada().equals(TipoEntrada.NOTICIA.getValue())) {
                final EntradaSimpleDTO entradaSimple = this.entradaService
                        .obtenerEntradaSimple(datoEntradaDTO.getUrlEntrada());
                if (entradaSimple.getImagenEntrada() != null) {
                    try {
                        entradaSimple.setImagenEntrada(this.almacenImagenes
                                .obtenerMiniatura(entradaSimple.getImagenEntrada(), 370, 208, true));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }

                noticias.add(entradaSimple);

                numeroEntradas++;
            }
        }
        paginaLibroNoticiasResponse.setLibro(libro);
        paginaLibroNoticiasResponse.setNoticias(noticias);
        paginaLibroNoticiasResponse.setNumeroEntradas(numeroEntradas);
        return paginaLibroNoticiasResponse;
    }

    @GetMapping(path = "/etiqueta/{url-etiqueta}/{numero-pagina}")
    public @ResponseBody ObtenerPaginaEtiquetaResponse obtenerEtiqueta(
            @PathVariable("url-etiqueta") final String urlEtiqueta,
            @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaEtiquetaResponse etiquetaResponse = new ObtenerPaginaEtiquetaResponse();
        final List<EntradaSimpleDTO> entradasEtiqueta = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(numeroPagina);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlEtiqueta);
        }
        return obtenerEtiquetaResponse(urlEtiqueta, request, etiquetaResponse, entradasEtiqueta);

    }

    @GetMapping(path = "/etiqueta/{url-etiqueta}")
    public @ResponseBody ObtenerPaginaEtiquetaResponse obtenerEtiqueta(
            @PathVariable("url-etiqueta") final String urlEtiqueta,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaEtiquetaResponse etiquetaResponse = new ObtenerPaginaEtiquetaResponse();
        final List<EntradaSimpleDTO> entradasEtiqueta = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlEtiqueta);
        }
        return obtenerEtiquetaResponse(urlEtiqueta, request, etiquetaResponse, entradasEtiqueta);

    }

    @GetMapping(path = "/buscar/{parametros-busqueda}")
    public @ResponseBody ObtenerPaginaBusquedaResponse obtenerBusqueda(
            @PathVariable("parametros-busqueda") final String parametrosBusqueda,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        final ObtenerPaginaBusquedaResponse busquedaResponse = new ObtenerPaginaBusquedaResponse();

        final String key = "AIzaSyBnQDrUmjpTtgHSgxTaOnt39u6SXiDvwPE";
        parametrosBusqueda.replaceAll(" ", "%20");
        final String qry = parametrosBusqueda;

        String url;
        final List<String> entradas = new ArrayList<String>();
        final List<String> etiquetas = new ArrayList<String>();
        final List<String> libros = new ArrayList<String>();
        final List<String> generos = new ArrayList<String>();
        final List<String> categorias = new ArrayList<String>();
        final List<String> order = new ArrayList<String>();
        final Map<String, ResultadoBusquedaDTO> mapaResultados = new LinkedHashMap<String, ResultadoBusquedaDTO>();
        final List<ResultadoBusquedaDTO> resultados = new ArrayList<ResultadoBusquedaDTO>();

        url = "https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=012955512977371834337:gke80zmzrl4&q=" + qry
                + "&alt=json";
        final RestTemplate restTemplate = new RestTemplate();
        final GoogleSearch googleSearch = restTemplate.getForObject(url, GoogleSearch.class);
        int numeroItems = 0;
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        if (CollectionUtils.isNotEmpty(googleSearch.getItems())) {
            for (final Item item : googleSearch.getItems()) {

                final String[] splitedUrl = item.getLink().split("momoko.es/");
                String urlPart = "htpp://momoko.es";
                if (splitedUrl.length > 1) {
                    urlPart = splitedUrl[1];
                }
                if (urlPart.contains("noticias/")) {
                    final String libroUrl = urlPart.split("noticias/")[1];
                    libros.add(libroUrl.trim());
                    order.add("libro/" + libroUrl);
                } else if (urlPart.contains("tag/")) {
                    String tagUrl = urlPart.split("tag/")[1];
                    tagUrl = tagUrl.replaceAll("/", "").trim();
                    etiquetas.add(tagUrl.trim());
                    order.add(urlPart.trim());
                } else if (urlPart.contains("categoria/")) {
                    final String categoriaUrl = urlPart.split("categoria/")[1];
                    categorias.add(categoriaUrl.trim());
                    order.add(urlPart.trim());
                } else if (urlPart.contains("genero/")) {
                    final String generoUrl = urlPart.split("genero/")[1];
                    generos.add(generoUrl.trim());
                    order.add(urlPart.trim());
                } else if (urlPart.contains("resena/")) {
                    final String entradaUrl = urlPart.split("resena/")[1];
                    entradas.add(entradaUrl.trim());
                    order.add(entradaUrl.trim());
                } else if (urlPart.contains("analisis/")) {
                    final String entradaUrl = urlPart.split("analisis/")[1];
                    entradas.add(entradaUrl.trim());
                    order.add(entradaUrl.trim());
                } else if (urlPart.contains("noticia/")) {
                    final String entradaUrl = urlPart.split("noticia/")[1];
                    entradas.add(entradaUrl.trim());
                    order.add(entradaUrl.trim());
                } else if (urlPart.contains("miscelaneo/")) {
                    final String entradaUrl = urlPart.split("miscelaneo/")[1];
                    entradas.add(entradaUrl.trim());
                    order.add(entradaUrl.trim());
                } else if (urlPart.contains("libro/")) {
                    String libroUrl = urlPart.split("libro/")[1];
                    if (libroUrl.contains("/")) {
                        libroUrl = libroUrl.split("/")[0];
                    }
                    libros.add(libroUrl.trim());
                    order.add(urlPart.trim());
                } else {
                    urlPart = urlPart.replaceAll("/", "").trim();
                    entradas.add(urlPart);
                    order.add(urlPart);
                }

                numeroItems++;
            }

            if (CollectionUtils.isNotEmpty(libros)) {

                final List<LibroDTO> librosDTO = this.buscadorService.buscarLibros(libros);
                for (final LibroDTO libroDTO : librosDTO) {
                    final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
                    resultado.setTitulo(libroDTO.getTitulo());
                    resultado.setHtmlDescripcion(libroDTO.getResumen());
                    resultado.setHtmlTitulo(libroDTO.getTitulo());
                    if (libroDTO.getUrlImagen() != null) {
                        resultado.setImagen(imageServer + libroDTO.getUrlImagen());
                    }
                    resultado.setDescripcion(libroDTO.getResumen());
                    final String urlLibro = "libro/" + libroDTO.getUrlLibro();
                    resultado.setUrl(urlLibro);
                    mapaResultados.put(urlLibro, resultado);
                }
            }
            if (CollectionUtils.isNotEmpty(entradas)) {

                final List<EntradaSimpleDTO> entradasDTO = this.buscadorService.buscarEntradas(entradas);
                for (final EntradaSimpleDTO entradaSimple : entradasDTO) {
                    final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
                    resultado.setTitulo(entradaSimple.getTituloEntrada());
                    resultado.setHtmlDescripcion(entradaSimple.getResumen());
                    resultado.setHtmlTitulo(entradaSimple.getTituloEntrada());
                    if (entradaSimple.getImagenEntrada() != null) {
                        resultado.setImagen(imageServer + entradaSimple.getImagenEntrada());
                    }
                    resultado.setDescripcion(entradaSimple.getResumen());
                    final String urlEntrada = entradaSimple.getUrlEntrada();
                    resultado.setUrl(urlEntrada);
                    mapaResultados.put(urlEntrada, resultado);
                }
            }
            if (CollectionUtils.isNotEmpty(etiquetas)) {

                final List<EtiquetaDTO> etiquetasDTO = this.buscadorService.buscarEtiquetas(etiquetas);

                for (final EtiquetaDTO etiquetaSimple : etiquetasDTO) {
                    final List<EntradaSimpleDTO> entrada = this.entradaService
                            .obtenerEntradasEtiquetaPorFecha(etiquetaSimple, 1, 1);

                    final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
                    resultado.setTitulo(etiquetaSimple.getNombreEtiqueta());
                    resultado.setHtmlDescripcion("La etiqueta: " + etiquetaSimple.getNombreEtiqueta());
                    resultado.setHtmlTitulo(etiquetaSimple.getNombreEtiqueta());
                    if (CollectionUtils.isNotEmpty(entrada)) {
                        final EntradaSimpleDTO entradaSimple = entrada.get(0);
                        resultado.setImagen(imageServer + entradaSimple.getImagenEntrada());
                    }
                    resultado.setDescripcion("La etiqueta: " + etiquetaSimple.getNombreEtiqueta());
                    final String urlEtiqueta = "tag/" + etiquetaSimple.getUrlEtiqueta();
                    resultado.setUrl(urlEtiqueta);
                    mapaResultados.put(urlEtiqueta, resultado);
                }
            }
            if (CollectionUtils.isNotEmpty(generos)) {

                final List<GeneroDTO> generosDTO = this.buscadorService.buscarGeneros(etiquetas);
                for (final GeneroDTO generoDTO : generosDTO) {
                    final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
                    resultado.setTitulo(generoDTO.getNombre());
                    resultado.setHtmlDescripcion("El género: " + generoDTO.getNombre());
                    resultado.setHtmlTitulo(generoDTO.getNombre());
                    if (generoDTO.getImagenCabeceraGenero() != null) {
                        try {
                            resultado.setImagen(this.almacenImagenes
                                    .obtenerMiniatura(generoDTO.getImagenCabeceraGenero(), 300, 190, true));
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                    resultado.setDescripcion("El género: " + generoDTO.getNombre());
                    final String urlGenero = "genero/" + generoDTO.getUrlGenero();
                    resultado.setUrl(urlGenero);
                    mapaResultados.put(urlGenero, resultado);
                }
            }
            if (CollectionUtils.isNotEmpty(categorias)) {

                final List<CategoriaDTO> categoriasDTO = this.buscadorService.buscarCategorias(categorias);
                for (final CategoriaDTO categoriaDTO : categoriasDTO) {
                    final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
                    resultado.setTitulo(categoriaDTO.getNombreCategoria());
                    resultado.setHtmlDescripcion("La categoría: " + categoriaDTO.getNombreCategoria());
                    resultado.setHtmlTitulo(categoriaDTO.getNombreCategoria());

                    resultado.setDescripcion("La categoría: " + categoriaDTO.getNombreCategoria());
                    final String urlCategoria = "categoria/" + categoriaDTO.getUrlCategoria();
                    resultado.setUrl(urlCategoria);
                    mapaResultados.put(urlCategoria, resultado);
                }
            }

        }

        busquedaResponse.setNumeroEntradas(numeroItems);
        busquedaResponse.setParametrosBusqueda(parametrosBusqueda);
        for (final String urlOrden : order) {
            final ResultadoBusquedaDTO pagina = mapaResultados.get(urlOrden);
            if (pagina != null) {
                resultados.add(pagina);
            }
        }

        busquedaResponse.setResultados(resultados);

        return busquedaResponse;

    }

    /**
     * Obtener categoria response.
     *
     * @param urlCategoria
     *            the url categoria
     * @param request
     *            the request
     * @param etiquetaResponse
     *            the categoria response
     * @param entradasCategoria
     *            the entradas categoria
     * @return the obtener pagina categoria response
     */
    private ObtenerPaginaEtiquetaResponse obtenerEtiquetaResponse(final String urlEtiqueta,
            final ObtenerPaginaElementoRequest request, final ObtenerPaginaEtiquetaResponse etiquetaResponse,
            final List<EntradaSimpleDTO> entradasEtiqueta) {
        final EtiquetaDTO etiquetaDTO = this.etiquetaService.obtenerEtiquetaPorUrl(urlEtiqueta);

        entradasEtiqueta
                .addAll(this.entradaService.obtenerEntradasEtiquetaPorFecha(etiquetaDTO, 9, request.getNumeroPagina()));
        etiquetaResponse.setNumeroEntradas(this.entradaService.obtenerNumeroEntradasEtiqueta(etiquetaDTO));

        for (final EntradaSimpleDTO entradaSimpleDTO : entradasEtiqueta) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 370, 208, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        etiquetaResponse.setEntradasEtiqueta(entradasEtiqueta);
        etiquetaResponse.setEtiqueta(etiquetaDTO);

        return etiquetaResponse;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/r/collect")
    public @ResponseBody String anotarVisita(@RequestParam final Map<String, String> allRequestParams,
            final ModelMap model) throws Exception {
        this.trackService.enviarVisitaAPagina("/r/collect", allRequestParams);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collect")
    public @ResponseBody String anotarVisita2(@RequestParam final Map<String, String> allRequestParams,
            final ModelMap model) throws Exception {
        this.trackService.enviarVisitaAPagina("/collect", allRequestParams);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsEtiquetas")
    void generarURLsEtiquetas() throws Exception {
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        for (final EtiquetaDTO etiquetaDTO : etiquetas) {
            if (etiquetaDTO.getUrlEtiqueta() == null) {
                final String urlEtiqueta = ConversionUtils.toSlug(etiquetaDTO.getNombreEtiqueta());
                etiquetaDTO.setUrlEtiqueta(urlEtiqueta);
                try {
                    this.etiquetaService.guardarEtiqueta(etiquetaDTO);
                } catch (final Exception e) {
                    System.out.println(urlEtiqueta);
                }
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsEditoriales")
    public @ResponseBody String generarURLsEditoriales() throws Exception {
        final List<EditorialDTO> editoriales = this.editorialService.recuperarEditoriales();
        for (final EditorialDTO editorial : editoriales) {
            if (editorial.getUrlEditorial() == null) {
                final String urlEditorial = ConversionUtils.toSlug(editorial.getNombreEditorial());
                editorial.setUrlEditorial(urlEditorial);
                try {
                    this.editorialService.guardarEditorial(editorial);
                } catch (final Exception e) {
                    System.out.println(urlEditorial);
                }
            }
        }
        return "DONE";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/email")
    void testEmail() throws Exception {
        Mail.sendEmail("Test email", "Contenido", "RMaetro@gmail.com");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarRedirects")
    public @ResponseBody String generarRedirects() throws Exception {
        System.out.println("Generando redirections");
        final StringBuilder builder = new StringBuilder();
        final List<EntradaDTO> entradas = this.entradaService.recuperarEntradas();
        for (final EntradaDTO entradaDTO : entradas) {
            if (TipoEntrada.ANALISIS.getValue().equals(entradaDTO.getTipoEntrada())) {
                final LibroDTO libro = entradaDTO.getLibrosEntrada().iterator().next();
                if (StringUtils.isEmpty(entradaDTO.getUrlAntigua())) {
                    builder.append("/libro/" + libro.getUrlLibro() + "/resena/" + entradaDTO.getUrlEntrada() + " "
                            + "/analisis/" + entradaDTO.getUrlEntrada()).append(";<br/>");
                    builder.append("/" + entradaDTO.getUrlEntrada() + " " + "/analisis/" + entradaDTO.getUrlEntrada())
                            .append(";<br/>");
                } else {
                    builder.append("/libro/" + libro.getUrlLibro() + "/resena/" + entradaDTO.getUrlAntigua() + " "
                            + "/analisis/" + entradaDTO.getUrlEntrada()).append(";<br/>");
                    builder.append("/" + entradaDTO.getUrlAntigua() + " " + "/analisis/" + entradaDTO.getUrlEntrada())
                            .append(";<br/>");
                    builder.append("/libro/" + libro.getUrlLibro() + "/resena/" + entradaDTO.getUrlEntrada() + " "
                            + "/analisis/" + entradaDTO.getUrlEntrada()).append(";<br/>");
                    builder.append("/" + entradaDTO.getUrlEntrada() + " " + "/analisis/" + entradaDTO.getUrlEntrada())
                            .append(";<br/>");
                }
            }
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/arreglarEtiquetas")
    public @ResponseBody String arreglarEtiquetas() {
        final Map<String, List<EtiquetaDTO>> etiquetas = this.etiquetaService.arreglarEtiquetas();

        for (final List<EtiquetaDTO> etiquetaDTO : etiquetas.values()) {
            final Iterator<EtiquetaDTO> ite = etiquetaDTO.iterator();
            final EtiquetaDTO etiquetaNueva = ite.next();
            while (ite.hasNext()) {
                final EtiquetaDTO etiquetaAEliminar = ite.next();
                final List<EntradaSimpleDTO> entradas = this.entradaService.obtenerEntradasEtiqueta(etiquetaAEliminar);
                for (final EntradaSimpleDTO entradaSimpleDTO : entradas) {
                    this.entradaService.eliminarEtiqueta(entradaSimpleDTO.getUrlEntrada(),
                            etiquetaAEliminar.getEtiquetaId());
                    this.entradaService.anadirEtiqueta(entradaSimpleDTO.getUrlEntrada(), etiquetaNueva.getEtiquetaId());
                }
            }
        }

        return null;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarSiteMap")
    void generarSiteMap() throws Exception {
        System.out.println("Generando sitemap");
        final List<EntradaSimpleDTO> entradasSimples = this.entradaService.recuperarEntradasSimples();
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroDTO> librosSimples = this.libroService.recuperarLibros();
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();

        final Date fechaActualizacionEntradaReciente = obtenerFechaActualizacionMasReciente(ultimasEntradas);
        final Date obtenerFechaLibroMasReciente = obtenerFechaLibroMasReciente(librosSimples);
        // number of URLs counted
        int nrOfURLs = 0;
        final Date ultimaActualizacion = obtenerFechaMasReciente(fechaActualizacionEntradaReciente,
                obtenerFechaLibroMasReciente);

        final String sitemapsDirectoryPath = this.almacenImagenes.getUrlSitemap();
        final String urlPagina = "https://momoko.es";

        final File file = new File(sitemapsDirectoryPath);
        System.out.println("Datos recolectados");
        System.out.println("Directorio guardado: " + sitemapsDirectoryPath);
        final WebSitemapGenerator wsg = WebSitemapGenerator.builder("https://momoko.es", file).fileNamePrefix("sitemap") // name
                // of
                // the
                // generated
                // sitemap
                .gzip(true) // recommended - as it decreases the file's size significantly
                .build();

        String url = urlPagina + "/";

        WebSitemapUrl wsmUrl = new WebSitemapUrl.Options(url).lastMod(ultimaActualizacion).priority(1.0)
                .changeFreq(ChangeFreq.HOURLY).build();
        wsg.addUrl(wsmUrl);
        nrOfURLs++;

        for (final CategoriaDTO categoria : categorias) {
            url = urlPagina + "/categoria/" + categoria.getUrlCategoria();
            final List<EntradaSimpleDTO> entradas = new ArrayList<EntradaSimpleDTO>();
            final String urlCategoria = categoria.getUrlCategoria();
            final ObtenerPaginaElementoRequest request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlCategoria);
            if (urlCategoria.equals("noticias")) {
                entradas.addAll(this.entradaService.obtenerNoticias(request));
            } else if (urlCategoria.equals("miscelaneos")) {
                entradas.addAll(this.entradaService.obtenerMiscelaneos(request));
            } else if (urlCategoria.equals("videos")) {
                entradas.addAll(this.entradaService.obtenerVideos(request));

            } else {
                entradas.addAll(
                        this.entradaService.obtenerEntradasCategoriaPorFecha(categoria, 9, request.getNumeroPagina()));

            }

            wsmUrl = new WebSitemapUrl.Options(url).lastMod(entradas.get(0).getFechaAlta()).priority(0.8)
                    .changeFreq(ChangeFreq.DAILY).build();
            wsg.addUrl(wsmUrl);
            nrOfURLs++;
        }

        for (final GeneroDTO genero : generos) {
            url = urlPagina + "/genero/" + genero.getUrlGenero();
            final List<LibroSimpleDTO> libros = this.libroService.obtenerLibrosConAnalisisGeneroPorFecha(genero, 9, 1);
            if (CollectionUtils.isNotEmpty(libros)) {

                wsmUrl = new WebSitemapUrl.Options(url).lastMod(libros.get(0).getFechaAlta()).priority(0.7)
                        .changeFreq(ChangeFreq.WEEKLY).build();
                wsg.addUrl(wsmUrl);
                nrOfURLs++;
            }
        }

        for (final EntradaSimpleDTO entrada : entradasSimples) {
            url = urlPagina + "/" + entrada.getUrlEntrada();
            if (entrada.getFechaAlta().before(Calendar.getInstance().getTime())) {
                if (entrada.getFechaModificacion() != null) {
                    wsmUrl = new WebSitemapUrl.Options(url).lastMod(entrada.getFechaModificacion()).priority(0.5)
                            .changeFreq(ChangeFreq.MONTHLY).build();
                } else {
                    wsmUrl = new WebSitemapUrl.Options(url).lastMod(entrada.getFechaAlta()).priority(0.5)
                            .changeFreq(ChangeFreq.MONTHLY).build();
                }
                wsg.addUrl(wsmUrl);
                nrOfURLs++;
                if (entrada.getUrlLibro() != null) {
                    url = urlPagina + "/" + entrada.getBloque() + "/" + entrada.getUrlEntrada();
                    if (entrada.getFechaModificacion() != null) {
                        wsmUrl = new WebSitemapUrl.Options(url).lastMod(entrada.getFechaModificacion()).priority(0.5)
                                .changeFreq(ChangeFreq.MONTHLY).build();
                    } else {
                        wsmUrl = new WebSitemapUrl.Options(url).lastMod(entrada.getFechaAlta()).priority(0.5)
                                .changeFreq(ChangeFreq.MONTHLY).build();
                    }
                    wsg.addUrl(wsmUrl);
                    nrOfURLs++;
                }
            }

        }

        for (final LibroDTO libro : librosSimples) {
            url = urlPagina + "/libro/" + libro.getUrlLibro();

            wsmUrl = new WebSitemapUrl.Options(url).lastMod(libro.getFechaAlta()).priority(0.5)
                    .changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(wsmUrl);
            nrOfURLs++;

        }

        for (final EtiquetaDTO etiqueta : etiquetas) {
            if (etiqueta.getUrlEtiqueta() != null) {
                url = urlPagina + "/tag/" + etiqueta.getUrlEtiqueta();
                wsmUrl = new WebSitemapUrl.Options(url).priority(0.4).changeFreq(ChangeFreq.MONTHLY).build();
                wsg.addUrl(wsmUrl);
                nrOfURLs++;
            }
        }

        // One sitemap can contain a maximum of 50,000 URLs.
        if (nrOfURLs <= 50000) {
            wsg.write();
        } else {
            // in this case multiple files will be created and sitemap_index.xml file describing the files which will be
            // ignored
            // workaround to resolve the issue described at
            // http://code.google.com/p/sitemapgen4j/issues/attachmentText?id=8&aid=80003000&name=Admit_Single_Sitemap_in_Index.patch&token=p2CFJZ5OOE5utzZV1UuxnVzFJmE%3A1375266156989
            wsg.write();
            wsg.writeSitemapsWithIndex();
        }
        System.out.println("Completado: Nº URLS: " + nrOfURLs);
    }

    private Date obtenerFechaLibroMasReciente(final List<LibroDTO> librosSimples) {
        Date fechaMasReciente = null;
        for (final LibroDTO libro : librosSimples) {
            if (libro.getFechaAlta() != null) {
                fechaMasReciente = obtenerFechaMasReciente(fechaMasReciente, libro.getFechaAlta());
            }
        }
        return fechaMasReciente;
    }

    /**
     * Obtener fecha actualizacion mas reciente.
     *
     * @param entradasSimples
     *            the entradas simples
     * @return the date
     */
    private Date obtenerFechaActualizacionMasReciente(final List<EntradaSimpleDTO> entradasSimples) {
        Date fechaMasReciente = null;
        for (final EntradaSimpleDTO entradaSimpleDTO : entradasSimples) {
            fechaMasReciente = obtenerFechaMasReciente(fechaMasReciente, entradaSimpleDTO.getFechaAlta());
        }
        return fechaMasReciente;
    }

    /**
     * Obtener fecha mas reciente.
     *
     * @param fechaMasReciente
     *            the fecha mas reciente
     * @param candidata
     *            the candidata
     * @return the date
     */
    public Date obtenerFechaMasReciente(Date fechaMasReciente, final Date candidata) {
        if ((fechaMasReciente == null) || (candidata.after(fechaMasReciente))) {
            fechaMasReciente = candidata;
        }
        return fechaMasReciente;
    }

    private static String getClientIp(final HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-Real-IP");
            if ((remoteAddr == null) || "".equals(remoteAddr)) {
                remoteAddr = request.getHeader("X-FORWARDED-FOR");
                if ((remoteAddr == null) || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
        }

        return remoteAddr;
    }

}
