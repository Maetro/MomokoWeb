/**
 * HerramientasServiceImpl.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.backend.model.service.*;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;

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
        final List<EntradaSimpleDTO> entradasSimples = this.entradaService.recuperarEntradasSimples();
        final List<LibroDTO> librosSimples = this.libroService.recuperarLibros();
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        final List<GeneroDTO> generos = this.generoService.obtenerTodosGeneros();
        final List<SagaDTO> sagas = this.sagaService.recuperarSagas();

        String url = "";
        String metaDescription = "";
        String title = "";
        entradasUrl = addUrlsCategorias(entradasUrl, categorias);
        entradasUrl = addUrlsGeneros(entradasUrl, generos);
        entradasUrl =

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

        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsCategorias(List<EntradaUrlDTO> entradasUrl, List<CategoriaDTO> categorias) {
        String url;
        String metaDescription;
        String title;
        for (final CategoriaDTO categoria : categorias) {
            EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/categoria/" + categoria.getUrlCategoria();
            entradaUrlDTO.setEntradaId(categoria.getCategoriaId());
            entradaUrlDTO.setLongitud(url.length());

            if (categoria.getUrlCategoria().equals("noticias")){
                metaDescription = "Últimas noticias de libros del mundo editorial en general: Rumores, estadísticas, fechas de lanzamiento y mucho más.";
                title = "Momoko - Últimas noticias";
            } else if (categoria.getUrlCategoria().equals("miscelaneos")){
                metaDescription = "Miscelaneos: Contendido con todo lo que rodea a tus libros favoritos, imágenes, eventos y curiosidades:";
                title = "Momoko - Últimas noticias";
            } else if (categoria.getUrlCategoria().equals("videos")){
                metaDescription = "Videos: Videos relacionados con el mundo de la literatura y sobre tus libros favoritos";
                title = "Momoko - Últimos vídeos";
            } else {
                metaDescription = "Categoría: " + categoria.getNombreCategoria() + ": Últimas entradas en momoko sobre la categoría: " +
                categoria.getNombreCategoria();
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


    private List<EntradaUrlDTO> addUrlsGeneros(List<EntradaUrlDTO> entradasUrl, List<GeneroDTO> generos) {
        String url;
        String metaDescription;
        String title;
        for (final GeneroDTO genero : generos) {
            EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/genero/" + genero.getUrlGenero();
            entradaUrlDTO.setEntradaId(genero.getGeneroId());
            entradaUrlDTO.setLongitud(url.length());


            metaDescription = "Últimas fichas de los libros del género " + genero.getNombre() +
                    " desde donde podras acceder a sus noticias y análisis";
            title = "Aquí encontrarás críticas, reseñas, opiniones y análisis de los libros del género " + genero.getNombre() +
        " en momoko";

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
