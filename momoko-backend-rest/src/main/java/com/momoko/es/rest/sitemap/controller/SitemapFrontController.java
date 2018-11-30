package com.momoko.es.rest.sitemap.controller;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.jpa.genre.service.GenreService;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.EtiquetaService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.StorageService;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class SitemapFrontController {

    private static final Logger log = LoggerFactory.getLogger(SitemapFrontController.class);

    private final EntradaService entradaService;
    private final IndexService indexService;
    private final LibroService libroService;
    private final EtiquetaService etiquetaService;
    private final GenreService genreService;
    private final StorageService storageService;

    @Autowired
    public SitemapFrontController(EntradaService entradaService, IndexService indexService, LibroService libroService,
                                  EtiquetaService etiquetaService, GenreService genreService,
                                  StorageService storageService) {
        this.entradaService = entradaService;
        this.indexService = indexService;
        this.libroService = libroService;
        this.etiquetaService = etiquetaService;
        this.genreService = genreService;
        this.storageService = storageService;
    }

    @GetMapping("/generarSiteMap")
    void generarSiteMap() throws MalformedURLException {
        log.debug("Generando sitemap");
        final List<EntradaSimpleDTO> entradasSimples = this.entradaService.recuperarEntradasSimples();
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroDTO> librosSimples = this.libroService.recuperarLibros();
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        final List<CategoriaDTO> categorias = this.genreService.obtenerListaCategorias();
        final List<GenreDTO> generos = this.genreService.getAllGenres();

        final Date fechaActualizacionEntradaReciente = obtenerFechaActualizacionMasReciente(ultimasEntradas);
        final Date obtenerFechaLibroMasReciente = obtenerFechaLibroMasReciente(librosSimples);
        // number of URLs counted
        int nrOfURLs = 0;
        final Date ultimaActualizacion = obtenerFechaMasReciente(fechaActualizacionEntradaReciente,
                obtenerFechaLibroMasReciente);

        final String sitemapsDirectoryPath = this.storageService.getUrlSitemap();
        final String urlPagina = "https://momoko.es";

        final File file = new File(sitemapsDirectoryPath);
        log.debug("Datos recolectados");
        log.debug("Directorio guardado: {}", sitemapsDirectoryPath);
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
            generateSiteMapForCategory(urlPagina, wsg, categoria);
            nrOfURLs++;
        }

        for (final GenreDTO genero : generos) {
            nrOfURLs = generateSiteMapForGenre(nrOfURLs, urlPagina, wsg, genero);
        }

        for (final EntradaSimpleDTO entrada : entradasSimples) {
            nrOfURLs = generateSiteMapEntry(nrOfURLs, urlPagina, wsg, entrada);
        }


        for (final LibroDTO libro : librosSimples) {
            generateSiteMapForBook(urlPagina, wsg, libro);
            nrOfURLs++;

        }

        for (final EtiquetaDTO etiqueta : etiquetas) {
            nrOfURLs = generateSiteMapForLabel(nrOfURLs, urlPagina, wsg, etiqueta);
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
        log.debug("Completado: Nº URLS: {}", nrOfURLs);
    }

    private int generateSiteMapForLabel(int nrOfURLs, String urlPagina, WebSitemapGenerator wsg, EtiquetaDTO etiqueta) throws MalformedURLException {
        String url;
        WebSitemapUrl wsmUrl;
        if (etiqueta.getUrlEtiqueta() != null) {
            url = urlPagina + "/tag/" + etiqueta.getUrlEtiqueta();
            wsmUrl = new WebSitemapUrl.Options(url).priority(0.4).changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(wsmUrl);
            nrOfURLs++;
        }
        return nrOfURLs;
    }

    private void generateSiteMapForBook(String urlPagina, WebSitemapGenerator wsg, LibroDTO libro) throws MalformedURLException {
        String url;
        WebSitemapUrl wsmUrl;
        url = urlPagina + "/libro/" + libro.getUrlLibro();

        wsmUrl = new WebSitemapUrl.Options(url).lastMod(libro.getFechaAlta()).priority(0.5)
                .changeFreq(ChangeFreq.MONTHLY).build();
        wsg.addUrl(wsmUrl);
    }

    private int generateSiteMapEntry(int nrOfURLs, String urlPagina, WebSitemapGenerator wsg, EntradaSimpleDTO entrada) throws MalformedURLException {
        String url;
        WebSitemapUrl wsmUrl;
        if (entrada.getUrlLibro() == null) {
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
            }
        } else {
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
        return nrOfURLs;
    }

    private int generateSiteMapForGenre(int nrOfURLs, String urlPagina, WebSitemapGenerator wsg, GenreDTO genero) throws MalformedURLException {
        String url;
        WebSitemapUrl wsmUrl;
        url = urlPagina + "/genero/" + genero.getUrlGenero();
        final List<LibroSimpleDTO> libros = this.libroService.obtenerLibrosConAnalisisGeneroPorFecha(genero, 9, 1);
        if (CollectionUtils.isNotEmpty(libros)) {

            wsmUrl = new WebSitemapUrl.Options(url).lastMod(libros.get(0).getFechaAlta()).priority(0.7)
                    .changeFreq(ChangeFreq.WEEKLY).build();
            wsg.addUrl(wsmUrl);
            nrOfURLs++;
        }
        return nrOfURLs;
    }

    private void generateSiteMapForCategory(String urlPagina, WebSitemapGenerator wsg, CategoriaDTO categoria) throws MalformedURLException {
        String url;
        WebSitemapUrl wsmUrl;
        url = urlPagina + "/categoria/" + categoria.getUrlCategoria();
        final List<EntradaSimpleDTO> entradas = new ArrayList<>();
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
