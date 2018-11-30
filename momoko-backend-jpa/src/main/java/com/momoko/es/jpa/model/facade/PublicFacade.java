package com.momoko.es.jpa.model.facade;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.api.exceptions.UrlElementNotFoundException;
import com.momoko.es.jpa.model.repository.EntradaRepository;
import com.momoko.es.jpa.genre.repository.GeneroRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.VisitaRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.ResultadoBusquedaDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaBusquedaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaEtiquetaResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaLibroNoticiasResponse;
import com.momoko.es.api.dto.response.ObtenerPaginaSagaColeccionResponse;
import com.momoko.es.api.google.GoogleSearch;
import com.momoko.es.api.google.Item;
import com.momoko.es.jpa.model.service.BuscadorService;
import com.momoko.es.jpa.model.service.ComentarioService;
import com.momoko.es.jpa.publisher.EditorialService;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.EtiquetaService;
import com.momoko.es.jpa.genre.service.GenreService;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.SagaService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.TrackService;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.model.service.ValidadorService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.MomokoThumbnailUtils;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
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
    private GenreService generoService;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired(required = false)
    private EtiquetaService etiquetaService;

    @Autowired(required = false)
    private BuscadorService buscadorService;




    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test() {
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarSiteMap")
    void generarSiteMap() throws Exception {
        System.out.println("Generando sitemap");
        final List<EntradaSimpleDTO> entradasSimples = this.entradaService.recuperarEntradasSimples();
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroDTO> librosSimples = this.libroService.recuperarLibros();
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<GenreDTO> generos = this.generoService.getAllGenres();

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

        for (final GenreDTO genero : generos) {
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
        }


        for (final LibroDTO libro : librosSimples) {
            url = urlPagina + "/libro/" + libro.getUrlLibro();

            wsmUrl = new WebSitemapUrl.Options(url).lastMod(libro.getFechaAlta()).priority(0.5)
                    .changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(wsmUrl);
            nrOfURLs++;

        }

        for (
                final EtiquetaDTO etiqueta : etiquetas) {
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
