/**
 * HerramientasServiceImpl.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaUrlDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.EtiquetaService;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.HerramientasService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.SagaService;

/**
 * The Class HerramientasServiceImpl.
 */
@Service
public class HerramientasServiceImpl implements HerramientasService {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private SagaService sagaService;

    @Autowired
    private EtiquetaService etiquetaService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<EntradaUrlDTO> obtenerUrlsOrdenadasPorLongitud() {
        List<EntradaUrlDTO> entradasUrl = new ArrayList<>();
        final List<EntradaDTO> entradasSimples = this.entradaService.recuperarEntradas();
        final List<LibroDTO> librosSimples = this.libroService.recuperarLibros();
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();
        final List<SagaDTO> sagas = this.sagaService.recuperarSagas();

        final String url = "";
        final String metaDescription = "";
        final String title = "";
        entradasUrl = addUrlsCategorias(entradasUrl, categorias);
        entradasUrl = addUrlsGeneros(entradasUrl, generos);
        entradasUrl = addUrlsEntradas(entradasUrl, entradasSimples);

        // for (final LibroDTO libro : librosSimples) {
        // url = urlPagina + "/libro/" + libro.getUrlLibro();
        //
        // wsmUrl = new WebSitemapUrl.Options(url).lastMod(libro.getFechaAlta()).priority(0.5)
        // .changeFreq(ChangeFreq.MONTHLY).build();
        // wsg.addUrl(wsmUrl);
        // nrOfURLs++;
        //
        // }
        //
        // for (final EtiquetaDTO etiqueta : etiquetas) {
        // if (etiqueta.getUrlEtiqueta() != null) {
        // url = urlPagina + "/tag/" + etiqueta.getUrlEtiqueta();
        // wsmUrl = new WebSitemapUrl.Options(url).priority(0.4).changeFreq(ChangeFreq.MONTHLY).build();
        // wsg.addUrl(wsmUrl);
        // nrOfURLs++;
        // }
        // }

        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsEntradas(final List<EntradaUrlDTO> entradasUrl,
            final List<EntradaDTO> entradas) {
        final String url;
        final String metaDescription;
        final String title;
        for (final EntradaDTO entrada : entradas) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            entrada.getTipoEntrada();
            // url = "/categoria/" + categoria.getUrlCategoria();
            // entradaUrlDTO.setEntradaId(categoria.getCategoriaId());
            // entradaUrlDTO.setLongitud(url.length());
            //
            // if (categoria.getUrlCategoria().equals("noticias")) {
            // metaDescription = "Últimas noticias de libros del mundo editorial en general: Rumores, estadísticas,
            // fechas de lanzamiento y mucho más.";
            // title = "Momoko - Últimas noticias";
            // } else if (categoria.getUrlCategoria().equals("miscelaneos")) {
            // metaDescription = "Miscelaneos: Contendido con todo lo que rodea a tus libros favoritos, imágenes,
            // eventos y curiosidades:";
            // title = "Momoko - Últimas noticias";
            // } else if (categoria.getUrlCategoria().equals("videos")) {
            // metaDescription = "Videos: Videos relacionados con el mundo de la literatura y sobre tus libros
            // favoritos";
            // title = "Momoko - Últimos vídeos";
            // } else {
            // metaDescription = "Categoría: " + categoria.getNombreCategoria()
            // + ": Últimas entradas en momoko sobre la categoría: " + categoria.getNombreCategoria();
            // title = "Momoko - Categoría: " + categoria.getNombreCategoria();
            // }
            // entradaUrlDTO.setMetaDescription(metaDescription);
            // entradaUrlDTO.setTitle(title);
            // entradaUrlDTO.setTipoUrl("ENTRADA");
            // entradaUrlDTO.setUrlAntigua(null);
            // entradaUrlDTO.setUrlEntrada(categoria.getUrlCategoria());
            // entradaUrlDTO.setUrlCompleta(url);
            // entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsCategorias(final List<EntradaUrlDTO> entradasUrl,
            final List<CategoriaDTO> categorias) {
        String url;
        String metaDescription;
        String title;
        for (final CategoriaDTO categoria : categorias) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/categoria/" + categoria.getUrlCategoria();
            entradaUrlDTO.setEntradaId(categoria.getCategoriaId());
            entradaUrlDTO.setLongitud(url.length());

            if (categoria.getUrlCategoria().equals("noticias")) {
                metaDescription = "Últimas noticias de libros del mundo editorial en general: Rumores, estadísticas, fechas de lanzamiento y mucho más.";
                title = "Momoko - Últimas noticias";
            } else if (categoria.getUrlCategoria().equals("miscelaneos")) {
                metaDescription = "Miscelaneos: Contendido con todo lo que rodea a tus libros favoritos, imágenes, eventos y curiosidades:";
                title = "Momoko - Últimas noticias";
            } else if (categoria.getUrlCategoria().equals("videos")) {
                metaDescription = "Videos: Videos relacionados con el mundo de la literatura y sobre tus libros favoritos";
                title = "Momoko - Últimos vídeos";
            } else {
                metaDescription = "Categoría: " + categoria.getNombreCategoria()
                        + ": Últimas entradas en momoko sobre la categoría: " + categoria.getNombreCategoria();
                title = "Momoko - Categoría: " + categoria.getNombreCategoria();
            }
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("CATEGORIA");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(categoria.getUrlCategoria());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsGeneros(final List<EntradaUrlDTO> entradasUrl, final List<GeneroDTO> generos) {
        String url;
        String metaDescription;
        String title;
        for (final GeneroDTO genero : generos) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/genero/" + genero.getUrlGenero();
            entradaUrlDTO.setEntradaId(genero.getGeneroId());
            entradaUrlDTO.setLongitud(url.length());

            metaDescription = "Últimas fichas de los libros del género " + genero.getNombre()
                    + " desde donde podras acceder a sus noticias y análisis";
            title = "Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros del género "
                    + genero.getNombre() + " en momoko";

            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("GENERO");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(genero.getUrlGenero());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

}
