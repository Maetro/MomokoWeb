/**
 * PublicFacade.java 26-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.InitDataDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.dto.request.ObtenerPaginaCategoriaRequest;
import com.momoko.es.api.dto.request.ObtenerPaginaGeneroRequest;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.api.dto.response.ObtenerIndexDataReponseDTO;
import com.momoko.es.api.dto.response.ObtenerPaginaCategoriaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaGeneroResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.backend.model.service.ValidadorService;
import com.momoko.es.util.ConversionUtils;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(path = "/initData")
    public @ResponseBody InitDataDTO getInitData() {
        System.out.println("Llamada al init");
        final List<MenuDTO> menu = this.indexService.obtenerMenu();

        final InitDataDTO initDataDTO = new InitDataDTO();
        initDataDTO.setMenu(menu);
        return initDataDTO;
    }

    @GetMapping(path = "/indexData")
    public @ResponseBody ObtenerIndexDataReponseDTO getInfoIndex() {
        System.out.println("Llamada a los datos para dibujar el index");
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        final List<LibroSimpleDTO> ultimosAnalisis = this.indexService.obtenerUltimosAnalisis();
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
        System.out.println("LLamada a la lista de entradas");
        return this.entradaService.recuperarEntradas();
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada) {
        System.out.println("Obtener entrada: " + urlEntrada);
        final ObtenerEntradaResponse respuesta = this.entradaService.obtenerEntrada(urlEntrada, true);
        return respuesta;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro) {
        System.out.println("Obtener libro: " + urlLibro);
        final ObtenerFichaLibroResponse respuesta = this.libroService.obtenerLibro(urlLibro);
        respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return respuesta;
    }

    @GetMapping(path = "/video/{url-video}")
    public @ResponseBody ObtenerEntradaResponse obtenerVideo(@PathVariable("url-video") final String urlVideo) {
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

    @GetMapping(path = "/genero/{url-genero}")
    public @ResponseBody ObtenerPaginaGeneroResponse obtenerGenero(@PathVariable("url-genero") final String urlGenero,
            @RequestBody(required = false) ObtenerPaginaGeneroRequest request) {
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

    @GetMapping(path = "/categoria/{url-categoria}")
    public @ResponseBody ObtenerPaginaCategoriaResponse obtenerGenero(
            @PathVariable("url-categoria") final String urlCategoria,
            @RequestBody(required = false) ObtenerPaginaCategoriaRequest request) {
        final ObtenerPaginaCategoriaResponse categoriaResponse = new ObtenerPaginaCategoriaResponse();
        final List<EntradaSimpleDTO> entradasCategoria = new ArrayList<EntradaSimpleDTO>();
        if (request == null) {
            request = new ObtenerPaginaCategoriaRequest();
            request.setNumeroPagina(1);
            request.setOrdenarPor("fecha");
            request.setUrlGenero(urlCategoria);
        }
        final CategoriaDTO categoriaDTO = this.generoService.obtenerCategoriaPorUrl(urlCategoria);
        if (urlCategoria.equals("noticias")) {
            entradasCategoria.addAll(this.entradaService.obtenerNoticias(request));
        } else {
            entradasCategoria.addAll(this.entradaService.obtenerEntradasCategoriaPorFecha(categoriaDTO, 9,
                    request.getNumeroPagina() - 1));
        }
        for (final EntradaSimpleDTO entradaSimpleDTO : entradasCategoria) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 240, 135, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        categoriaResponse.setEntradasCategoria(entradasCategoria);
        categoriaResponse.setCategoria(categoriaDTO);
        return categoriaResponse;

    }

    // TODO: BORRAME

    @RequestMapping(method = RequestMethod.GET, path = "/limpiarImagenesLibros")
    void limpiarImagenesLibrosYEntradas() throws Exception {
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        for (final LibroDTO libroDTO : libros) {
            if (!StringUtils.isBlank(libroDTO.getUrlImagen())) {
                final String urlImagen = aPortadas(libroDTO.getUrlImagen());
                libroDTO.setUrlImagen(urlImagen);
                this.libroService.guardarLibro(libroDTO);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/limpiarImagenesEntradas")
    void limpiarImagenesEntradas() throws Exception {
        final List<EntradaDTO> entradas = this.entradaService.recuperarEntradas();
        for (final EntradaDTO entrada : entradas) {
            if (!StringUtils.isBlank(entrada.getImagenDestacada())) {
                final String urlImagen = soloNombreImagen(entrada.getImagenDestacada());
                entrada.setImagenDestacada(urlImagen);
                this.entradaService.guardarEntrada(entrada);
            }
        }
    }

    private String soloNombreImagen(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return lista[elementos - 2] + "/" + lista[elementos - 1];
    }

    private String aPortadas(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return "portadas" + "/" + lista[elementos - 1];
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsLibros")
    void generarURLsLibros() throws Exception {
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        for (final LibroDTO libroDTO : libros) {
            if (StringUtils.isBlank(libroDTO.getUrlLibro())) {
                final String urlLibro = ConversionUtils.toSlug(libroDTO.getTitulo());
                libroDTO.setUrlLibro(urlLibro);
                this.libroService.guardarLibro(libroDTO);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsGeneros")
    void generarURLsGeneros() throws Exception {
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();
        for (final GeneroDTO generoDTO : generos) {
            if (StringUtils.isBlank(generoDTO.getUrlGenero())) {
                final String urlGenero = ConversionUtils.toSlug(generoDTO.getNombre());
                generoDTO.setUrlGenero(urlGenero);
                this.generoService.guardarGenero(generoDTO);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/asociarACategoriaNovela")
    void asociarACategoriaNovela() throws Exception {
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();
        for (final GeneroDTO generoDTO : generos) {
            if (generoDTO.getCategoria() == null) {
                final CategoriaDTO categoria = this.generoService.obtenerCategoriaPorUrl("novelas");
                generoDTO.setCategoria(categoria);
                this.generoService.guardarGenero(generoDTO);
            }
        }
    }

}
