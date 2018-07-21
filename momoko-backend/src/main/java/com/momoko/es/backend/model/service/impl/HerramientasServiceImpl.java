/**
 * HerramientasServiceImpl.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.backend.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private GenreService generoService;

    @Autowired
    private UserService userService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<EntradaUrlDTO> obtenerUrlsOrdenadasPorLongitud() {
        List<EntradaUrlDTO> entradasUrl = new ArrayList<>();

        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        entradasUrl = addUrlsCategorias(entradasUrl, categorias);
        final List<GenreDTO> generos = this.generoService.getAllGenres();
        entradasUrl = addUrlsGeneros(entradasUrl, generos);
        final List<EntradaDTO> entradas = this.entradaService.recuperarEntradas();
        entradasUrl = addUrlsEntradas(entradasUrl, entradas);
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        entradasUrl = addUrlsLibros(entradasUrl, libros);
        final List<EtiquetaDTO> etiquetas = this.etiquetaService.obtenerTodasEtiquetas();
        entradasUrl = addUrlsEtiquetas(entradasUrl, etiquetas);
        final List<SagaDTO> sagas = this.sagaService.recuperarSagas();
        entradasUrl = addUrlsSagas(entradasUrl, sagas);
        final List<RedactorDTO> redactores = this.userService.obtenerRedactoresMomoko();
        entradasUrl = addUrlsRedactores(entradasUrl, redactores);
        List<EditorialDTO> editoriales = this.editorialService.recuperarEditoriales();
        entradasUrl = addUrlsEditoriales(entradasUrl, editoriales);
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsEditoriales(List<EntradaUrlDTO> entradasUrl, List<EditorialDTO> editoriales) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final EditorialDTO editorial : editoriales) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/editorial/" + editorial.getUrlEditorial();
            title = "Momoko - Editorial: " + editorial.getNombreEditorial();
            metaDescription =  "Editorial: " + editorial.getNombreEditorial() + ": Últimos libros en momoko de la editorial: " +
                    editorial.getNombreEditorial();

            entradaUrlDTO.setEntradaId(editorial.getEditorialId());
            entradaUrlDTO.setLongitud(url.length());
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("EDITORIAL");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(editorial.getUrlEditorial());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsRedactores(List<EntradaUrlDTO> entradasUrl, List<RedactorDTO> redactores) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final RedactorDTO redactor : redactores) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/redactor/" + redactor.getUrlRedactor();
            title = "Momoko - Redactor: " + redactor.getNombre();
            metaDescription = "Redactor: " + redactor.getNombre() + ": Últimas entradas en momoko del editor: " +
                    redactor.getNombre();


            entradaUrlDTO.setEntradaId(redactor.getUsuarioId());
            entradaUrlDTO.setLongitud(url.length());
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("REDACTOR");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(redactor.getUrlRedactor());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsSagas(List<EntradaUrlDTO> entradasUrl, List<SagaDTO> sagas) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final SagaDTO saga : sagas) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/saga/" + saga.getUrlSaga();
            title = "Ficha de saga " + saga.getNombreSaga();
            metaDescription = "Encuentra aquí toda la información sobre " + saga.getNombreSaga();


            entradaUrlDTO.setEntradaId(saga.getSagaId());
            entradaUrlDTO.setLongitud(url.length());
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("SAGA");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(saga.getUrlSaga());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsEtiquetas(List<EntradaUrlDTO> entradasUrl, List<EtiquetaDTO> etiquetas) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final EtiquetaDTO etiqueta : etiquetas) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/tag/" + etiqueta.getUrlEtiqueta();
            title = "Momoko - Etiqueta: " + etiqueta.getNombreEtiqueta();
            metaDescription = "Últimas entradas en momoko sobre la etiqueta: " +
                    etiqueta.getNombreEtiqueta();


            entradaUrlDTO.setEntradaId(etiqueta.getEtiquetaId());
            entradaUrlDTO.setLongitud(url.length());
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("ETIQUETA");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(etiqueta.getUrlEtiqueta());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;

    }

    private List<EntradaUrlDTO> addUrlsEntradas(final List<EntradaUrlDTO> entradasUrl,
            final List<EntradaDTO> entradas) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final EntradaDTO entrada : entradas) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            TipoEntrada tipoEntrada = TipoEntrada.obtenerTipoEntrada(entrada.getTipoEntrada());
            switch (tipoEntrada){
                case OPINIONES:
                    url = "/opiniones/" + entrada.getUrlEntrada();
                    title = "Reseña libro - " + entrada.getLibrosEntrada().get(0).getTitulo();
                    break;
                case NOTICIA:
                    url = "/" + entrada.getUrlEntrada();
                    title = entrada.getTituloEntrada();
                    break;
                case MISCELANEOS:
                    url = "/" + entrada.getUrlEntrada();
                    title = entrada.getTituloEntrada();
                    break;
                case VIDEO:
                    url = "/" + entrada.getUrlEntrada();
                    title = entrada.getTituloEntrada();
                    break;

            }

             entradaUrlDTO.setEntradaId(entrada.getEntradaId());
             entradaUrlDTO.setLongitud(url.length());
             metaDescription = entrada.getFraseDescriptiva();
             entradaUrlDTO.setMetaDescription(metaDescription);
             entradaUrlDTO.setTitle(title);
             entradaUrlDTO.setTipoUrl("ENTRADA");
             entradaUrlDTO.setUrlAntigua(entrada.getUrlAntigua());
             entradaUrlDTO.setUrlEntrada(entrada.getUrlEntrada());
             entradaUrlDTO.setUrlCompleta(url);
             entradasUrl.add(entradaUrlDTO);
        }
        return entradasUrl;
    }

    private List<EntradaUrlDTO> addUrlsLibros(final List<EntradaUrlDTO> entradasUrl,
                                                final List<LibroDTO> libros) {
        String url = "";
        String metaDescription = "";
        String title = "";
        for (final LibroDTO libro : libros) {
            final EntradaUrlDTO entradaUrlDTO = new EntradaUrlDTO();
            url = "/libro/" + libro.getUrlLibro();
            title = "Ficha de libro " + libro.getTitulo();


            entradaUrlDTO.setEntradaId(libro.getLibroId());
            entradaUrlDTO.setLongitud(url.length());

            StringBuilder autores = new StringBuilder();
            libro.getAutores().forEach(autor -> {
                    autores.append(autor.getNombre() + ", ");
             });
            String autoresString = autores.toString();
            autoresString = autoresString.substring(0, autoresString.length() - 2);

            metaDescription = "Encuentra aquí toda la información sobre " +
                            libro.getTitulo() + " y sobre " + autoresString;
            entradaUrlDTO.setMetaDescription(metaDescription);
            entradaUrlDTO.setTitle(title);
            entradaUrlDTO.setTipoUrl("LIBRO");
            entradaUrlDTO.setUrlAntigua(null);
            entradaUrlDTO.setUrlEntrada(libro.getUrlLibro());
            entradaUrlDTO.setUrlCompleta(url);
            entradasUrl.add(entradaUrlDTO);
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

    private List<EntradaUrlDTO> addUrlsGeneros(final List<EntradaUrlDTO> entradasUrl, final List<GenreDTO> generos) {
        String url;
        String metaDescription;
        String title;
        for (final GenreDTO genero : generos) {
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
