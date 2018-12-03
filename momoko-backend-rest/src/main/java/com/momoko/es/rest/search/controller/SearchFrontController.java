package com.momoko.es.rest.search.controller;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.response.ObtenerPaginaBusquedaResponse;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.google.GoogleSearch;
import com.momoko.es.api.google.Item;
import com.momoko.es.jpa.model.service.BuscadorService;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.service.StorageService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class SearchFrontController {

    private static final Logger log = LoggerFactory.getLogger(SearchFrontController.class);

    private static final String LIBRO = "libro/";
    private static final String GENERO = "genero/";
    private static final String CATEGORIA = "categoria/";
    private final StorageService storageService;
    private final BuscadorService buscadorService;
    private final EntradaService entradaService;

    @Autowired
    public SearchFrontController(StorageService storageService, BuscadorService buscadorService,
                                 EntradaService entradaService) {
        this.storageService = storageService;
        this.buscadorService = buscadorService;
        this.entradaService = entradaService;
    }


    @GetMapping(path = "/buscar/{parametros-busqueda}")
    public @ResponseBody
    ObtenerPaginaBusquedaResponse obtenerBusqueda(
            @PathVariable("parametros-busqueda") final String parametrosBusqueda,
            @RequestHeader(value = "User-Agent") final String userAgent) {
        log.debug("search");
        final ObtenerPaginaBusquedaResponse busquedaResponse = new ObtenerPaginaBusquedaResponse();

        final String key = "AIzaSyBnQDrUmjpTtgHSgxTaOnt39u6SXiDvwPE";
        String parametrosBusquedaClean = parametrosBusqueda.replaceAll(" ", "%20");
        final String qry = parametrosBusquedaClean;

        String url;
        final List<String> entradas = new ArrayList<>();
        final List<String> etiquetas = new ArrayList<>();
        final List<String> libros = new ArrayList<>();
        final List<String> generos = new ArrayList<>();
        final List<String> categorias = new ArrayList<>();
        final List<String> order = new ArrayList<>();
        final Map<String, ResultadoBusquedaDTO> mapaResultados = new LinkedHashMap<>();
        final List<ResultadoBusquedaDTO> resultados = new ArrayList<>();

        url = "https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=012955512977371834337:gke80zmzrl4&q=" + qry
                + "&alt=json";
        final RestTemplate restTemplate = new RestTemplate();
        final GoogleSearch googleSearch = restTemplate.getForObject(url, GoogleSearch.class);
        int numeroItems = 0;
        final String imageServer = this.storageService.getUrlImageServer();
        if (CollectionUtils.isNotEmpty(googleSearch.getItems())) {
            for (final Item item : googleSearch.getItems()) {
                analizeGoogleSearchItem(entradas, etiquetas, libros, generos, categorias, order, item);
                numeroItems++;
            }

            getDataOfElementsToShow(entradas, etiquetas, libros, generos, categorias, mapaResultados, imageServer);

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

    public void getDataOfElementsToShow(List<String> entradas, List<String> etiquetas, List<String> libros, List<String> generos, List<String> categorias, Map<String, ResultadoBusquedaDTO> mapaResultados, String imageServer) {
        if (CollectionUtils.isNotEmpty(libros)) {

            getBookDataToShow(libros, mapaResultados, imageServer);
        }
        if (CollectionUtils.isNotEmpty(entradas)) {

            getEntryDataToShow(entradas, mapaResultados, imageServer);
        }
        if (CollectionUtils.isNotEmpty(etiquetas)) {

            getTagDataToShow(etiquetas, mapaResultados, imageServer);
        }
        if (CollectionUtils.isNotEmpty(generos)) {

            getGenreDataToShow(etiquetas, mapaResultados);
        }
        if (CollectionUtils.isNotEmpty(categorias)) {

            getCategoryDataToShow(categorias, mapaResultados);
        }
    }

    public void getCategoryDataToShow(List<String> categorias, Map<String, ResultadoBusquedaDTO> mapaResultados) {
        final List<CategoriaDTO> categoriasDTO = this.buscadorService.buscarCategorias(categorias);
        for (final CategoriaDTO categoriaDTO : categoriasDTO) {
            final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
            resultado.setTitulo(categoriaDTO.getNombreCategoria());
            resultado.setHtmlDescripcion("La categoría: " + categoriaDTO.getNombreCategoria());
            resultado.setHtmlTitulo(categoriaDTO.getNombreCategoria());

            resultado.setDescripcion("La categoría: " + categoriaDTO.getNombreCategoria());
            final String urlCategoria = CATEGORIA + categoriaDTO.getUrlCategoria();
            resultado.setUrl(urlCategoria);
            mapaResultados.put(urlCategoria, resultado);
        }
    }

    public void getGenreDataToShow(List<String> etiquetas, Map<String, ResultadoBusquedaDTO> mapaResultados) {
        final List<GenreDTO> generosDTO = this.buscadorService.buscarGeneros(etiquetas);
        for (final GenreDTO generoDTO : generosDTO) {
            final ResultadoBusquedaDTO resultado = new ResultadoBusquedaDTO();
            resultado.setTitulo(generoDTO.getNombre());
            resultado.setHtmlDescripcion("El género: " + generoDTO.getNombre());
            resultado.setHtmlTitulo(generoDTO.getNombre());
            if (generoDTO.getImagenCabeceraGenero() != null) {
                try {
                    resultado.setImagen(this.storageService
                            .obtenerMiniatura(generoDTO.getImagenCabeceraGenero(), 300, 190, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
            resultado.setDescripcion("El género: " + generoDTO.getNombre());
            final String urlGenero = GENERO + generoDTO.getUrlGenero();
            resultado.setUrl(urlGenero);
            mapaResultados.put(urlGenero, resultado);
        }
    }

    public void getTagDataToShow(List<String> etiquetas, Map<String, ResultadoBusquedaDTO> mapaResultados, String imageServer) {
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

    public void getEntryDataToShow(List<String> entradas, Map<String, ResultadoBusquedaDTO> mapaResultados, String imageServer) {
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

    public void getBookDataToShow(List<String> libros, Map<String, ResultadoBusquedaDTO> mapaResultados, String imageServer) {
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
            final String urlLibro = LIBRO + libroDTO.getUrlLibro();
            resultado.setUrl(urlLibro);
            mapaResultados.put(urlLibro, resultado);
        }
    }

    public void analizeGoogleSearchItem(List<String> entradas, List<String> etiquetas, List<String> libros,
                                       List<String> generos, List<String> categorias, List<String> order, Item item) {
        final String[] splitedUrl = item.getLink().split("momoko.es/");
        String urlPart = "htpp://momoko.es";
        if (splitedUrl.length > 1) {
            urlPart = splitedUrl[1];
        }
        if (urlPart.contains("noticias/")) {
            final String libroUrl = urlPart.split("noticias/")[1];
            libros.add(libroUrl.trim());
            order.add(LIBRO + libroUrl);
        } else if (urlPart.contains("tag/")) {
            String tagUrl = urlPart.split("tag/")[1];
            tagUrl = tagUrl.replaceAll("/", "").trim();
            etiquetas.add(tagUrl.trim());
            order.add(urlPart.trim());
        } else if (urlPart.contains(CATEGORIA)) {
            final String categoriaUrl = urlPart.split(CATEGORIA)[1];
            categorias.add(categoriaUrl.trim());
            order.add(urlPart.trim());
        } else if (urlPart.contains(GENERO)) {
            final String generoUrl = urlPart.split(GENERO)[1];
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
        } else if (urlPart.contains("opiniones/")) {
            final String entradaUrl = urlPart.split("opiniones/")[1];
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
        } else if (urlPart.contains(LIBRO)) {
            String libroUrl = urlPart.split(LIBRO)[1];
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
     * @param entradasSimples the entradas simples
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
     * @param fechaMasReciente the fecha mas reciente
     * @param candidata        the candidata
     * @return the date
     */
    public Date obtenerFechaMasReciente(Date fechaMasReciente, final Date candidata) {
        if ((fechaMasReciente == null) || (candidata.after(fechaMasReciente))) {
            fechaMasReciente = candidata;
        }
        return fechaMasReciente;
    }

}
