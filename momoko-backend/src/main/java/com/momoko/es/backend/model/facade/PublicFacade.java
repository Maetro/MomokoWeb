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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.InitDataDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
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
import com.momoko.es.api.dto.response.ObtenerPaginaEtiquetaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaGeneroResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaLibroNoticiasResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.enums.TipoVisitaEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.api.exceptions.NoSeEncuentraElementoConUrl;
import com.momoko.es.api.google.GoogleSearch;
import com.momoko.es.api.google.Item;
import com.momoko.es.backend.model.service.BuscadorService;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.EtiquetaService;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.SagaService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.backend.model.service.TrackService;
import com.momoko.es.backend.model.service.ValidadorService;
import com.momoko.es.util.ConversionUtils;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es", "https://momoko.es" })
@RequestMapping(path = "/public")
public class PublicFacade {

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
    private TrackService trackService;

    @GetMapping(path = "/initData")
    public @ResponseBody InitDataDTO getInitData() {
        final List<MenuDTO> menu = this.indexService.obtenerMenu();

        final InitDataDTO initDataDTO = new InitDataDTO();
        initDataDTO.setMenu(menu);
        return initDataDTO;
    }

    @GetMapping(path = "/indexData")
    public @ResponseBody ObtenerIndexDataReponseDTO
            getInfoIndex(@RequestHeader(value = "User-Agent") final String userAgent) {
        System.out.println("GET: INDEX user-agent:" + userAgent);
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        final List<LibroSimpleDTO> ultimosAnalisis = this.indexService.obtenerUltimasFichas();
        final LibroEntradaSimpleDTO ultimoComicAnalizado = this.indexService.obtenerUltimoComicAnalizado();
        final ObtenerIndexDataReponseDTO obtenerIndexDataResponseDTO = new ObtenerIndexDataReponseDTO();
        obtenerIndexDataResponseDTO.setUltimasEntradas(ultimasEntradas);
        obtenerIndexDataResponseDTO.setLibrosMasVistos(librosMasVistos);
        obtenerIndexDataResponseDTO.setUltimoComicAnalizado(ultimoComicAnalizado);
        obtenerIndexDataResponseDTO.setUltimosAnalisis(ultimosAnalisis);

        return obtenerIndexDataResponseDTO;
    }

    @GetMapping(path = "/entradas")
    public @ResponseBody List<EntradaDTO> getAllEntradas() {

        return this.entradaService.recuperarEntradas();
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada,
            final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent) {
        System.out.println("GET: " + urlEntrada + " user-agent:" + userAgent);
        ObtenerEntradaResponse respuesta = null;
        if (!urlEntrada.equals("not-found")) {
            respuesta = this.entradaService.obtenerEntrada(urlEntrada, true);
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

        }

        return respuesta;
    }

    @GetMapping(path = "/suscribirse/{email}")
    public @ResponseBody ObtenerEntradaResponse suscribirse(@PathVariable("email") final String email) {
        // System.out.println("Suscribirse: " + email);
        this.indexService.suscribirse(email);
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        return respuesta;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro,
            final HttpServletRequest request, @RequestHeader(value = "User-Agent") final String userAgent) {
        // System.out.println("GET: libro/" + urlLibro + " user-agent:" + userAgent);
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
        // System.out.println("GET: saga/" + urlSaga + " user-agent:" + userAgent);
        final ObtenerFichaSagaResponse sagaResponse = new ObtenerFichaSagaResponse();
        SagaDTO sagaDTO = null;
        try {
            sagaDTO = this.sagaService.obtenerSaga(urlSaga);
            sagaResponse.setSaga(sagaDTO);
            final List<LibroDTO> librosSaga = this.libroService.obtenerLibros(sagaDTO.getLibrosSaga());
            sagaResponse.setLibrosSaga(librosSaga);
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
        System.out.println("GET: video/" + urlVideo + " user-agent:" + userAgent);
        System.out.println("Obtener libro: " + urlVideo);
        final ObtenerEntradaResponse respuesta = this.entradaService.obtenerEntradaVideo(urlVideo);
        // respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return respuesta;
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
        System.out.println("GET: genero/" + urlGenero + " user-agent:" + userAgent);
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
        System.out.println("GET: categoria/" + urlCategoria + " user-agent:" + userAgent);
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
        System.out.println("GET: categoria/" + urlCategoria + " user-agent:" + userAgent);
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
        System.out.println("GET: noticias-libro/" + urlLibro + " user-agent:" + userAgent);
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
        System.out.println("GET: noticias-libro/" + urlLibro + " user-agent:" + userAgent);
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
        System.out.println("GET: etiqueta/" + urlEtiqueta + " user-agent:" + userAgent);
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
        System.out.println("GET: etiqueta/" + urlEtiqueta + " user-agent:" + userAgent);
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
        System.out.println("GET: buscar/" + qry + " user-agent:" + userAgent);
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
        System.out.println(
                "Se envia visita a anlytics: " + (allRequestParams.get("dp") != null ? allRequestParams.get("dp") : 0));
        this.trackService.enviarVisitaAPagina("/r/collect", allRequestParams);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collect")
    public @ResponseBody String anotarVisita2(@RequestParam final Map<String, String> allRequestParams,
            final ModelMap model) throws Exception {
        System.out.println(
                "Se envia visita a anlytics: " + (allRequestParams.get("dp") != null ? allRequestParams.get("dp") : 1));
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
                    url = urlPagina + "/libro/" + entrada.getUrlLibro() + "/" + entrada.getBloque() + "/"
                            + entrada.getUrlEntrada();
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
