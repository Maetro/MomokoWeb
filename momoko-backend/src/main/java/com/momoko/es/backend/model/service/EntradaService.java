/**
 * EntradaService.java 11-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.service;

import java.util.Collection;
import java.util.List;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;

/**
 * The Interface EntradaService.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public interface EntradaService {

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    List<EntradaDTO> recuperarEntradas();

    /**
     * Guardar entrada.
     *
     * @param entradaAGuardar
     *            entrada A guardar
     * @return the entrada DTO
     * @throws Exception
     *             de exception
     */
    EntradaDTO guardarEntrada(EntradaDTO entradaAGuardar) throws Exception;

    /**
     * Obtener entrada.
     *
     * @param urlEntrada
     *            the url entrada
     * @return the obtener entrada response
     */
    ObtenerEntradaResponse obtenerEntrada(String urlEntrada, boolean transformarGalerias);

    /**
     * Obtener entrada video.
     *
     * @param urlVideo
     *            the url video
     * @return the obtener ficha libro response
     */
    ObtenerEntradaResponse obtenerEntradaVideo(String urlVideo);

    /**
     * Obtener tres ultimas entradas populares con libro.
     *
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerTresUltimasEntradasPopularesConLibro();

    /**
     * Obtener entradas categoria por fecha.
     *
     * @param categoriaDTO
     *            the categoria dto
     * @param numeroEntradas
     *            the numero entradas
     * @param pagina
     *            the pagina
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerEntradasCategoriaPorFecha(CategoriaDTO categoriaDTO, int numeroEntradas, int pagina);

    /**
     * Obtener numero entradas categoria.
     *
     * @param categoriaDTO
     *            the categoria dto
     * @return the integer
     */
    Integer obtenerNumeroEntradasCategoria(CategoriaDTO categoriaDTO);

    /**
     * Obtener noticias.
     *
     * @param request
     *            the request
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerNoticias(ObtenerPaginaElementoRequest request);

    /**
     * Obtener numero noticias.
     *
     * @return the integer
     */
    Integer obtenerNumeroNoticias();

    /**
     * Recuperar entradas simples.
     *
     * @return the list
     */
    List<EntradaSimpleDTO> recuperarEntradasSimples();

    /**
     * Obtener miscelaneos.
     *
     * @param request
     *            the request
     * @return the collection<? extends entrada simple dt o>
     */
    Collection<EntradaSimpleDTO> obtenerMiscelaneos(ObtenerPaginaElementoRequest request);

    /**
     * Obtener numero miscelaneos.
     *
     * @return the integer
     */
    Integer obtenerNumeroMiscelaneos();

    /**
     * Obtener videos.
     *
     * @param request
     *            the request
     * @return the collection
     */
    Collection<EntradaSimpleDTO> obtenerVideos(ObtenerPaginaElementoRequest request);

    /**
     * Obtener numero videos.
     *
     * @return the integer
     */
    Integer obtenerNumeroVideos();

    /**
     * Obtener entrada para gestion.
     *
     * @param urlEntrada
     *            the url entrada
     * @return the obtener entrada response
     */
    ObtenerEntradaResponse obtenerEntradaParaGestion(String urlEntrada);

    /**
     * Obtener entrada simple.
     *
     * @param urlEntrada
     *            the url entrada
     * @return the entrada simple dto
     */
    EntradaSimpleDTO obtenerEntradaSimple(String urlEntrada);

    /**
     * Obtener entradas etiqueta por fecha.
     *
     * @param etiquetaDTO
     *            the etiqueta dto
     * @param numeroEntradas
     *            the numero entradas
     * @param numeroPagina
     *            the numero pagina
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerEntradasEtiquetaPorFecha(EtiquetaDTO etiquetaDTO, int numeroEntradas,
            Integer numeroPagina);

    /**
     * Obtener numero entradas etiqueta.
     *
     * @param etiquetaDTO
     *            the etiqueta dto
     * @return the integer
     */
    Integer obtenerNumeroEntradasEtiqueta(EtiquetaDTO etiquetaDTO);

    /**
     * Obtener galerias entrada amp.
     *
     * @param entradaDTO
     *            the entrada dto
     */
    void obtenerGaleriasEntradaAmp(EntradaDTO entradaDTO);

    /**
     * Obtener analisis generos.
     *
     * @param libro
     *            the libro
     * @return the list
     */
    List<EntradaDTO> obtenerAnalisisGeneros(LibroDTO libro);

    /**
     * Obtener entradas aleatorias de tipo.
     *
     * @param value
     *            the value
     * @return the list
     */
    List<EntradaDTO> obtenerEntradasAleatoriasDeTipo(Integer value);

}
