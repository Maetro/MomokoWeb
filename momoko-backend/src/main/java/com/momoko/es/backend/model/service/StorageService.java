/**
 * StorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.dto.AnchuraAlturaDTO;

/**
 * The Interface StorageService.
 */
public interface StorageService {

    /**
     * Inicializa.
     *
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     */
    void init(String tipoAlmacenamineto);

    /**
     * Store.
     *
     * @param file
     *            the file
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     */
    void store(MultipartFile file, String tipoAlmacenamineto);

    /**
     * Store.
     *
     * @param file
     *            the file
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     */
    void store(BufferedImage image, String tipoAlmacenamineto, final String name);

    /**
     * Load all.
     *
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     * @return the stream
     */
    Stream<Path> loadAll(String tipoAlmacenamineto);

    /**
     * Load.
     *
     * @param filename
     *            the filename
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     * @return the path
     */
    Path load(String filename, String tipoAlmacenamineto);

    /**
     * Obtener miniatura.
     *
     * @param urlImagen
     *            the url imagen
     * @param width
     *            the width
     * @param height
     *            the height
     * @param recortar
     *            the recortar
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    String obtenerMiniatura(String urlImagen, Integer width, Integer height, boolean recortar) throws IOException;

    /**
     * Obtener miniatura.
     *
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @param filename
     *            the filename
     * @param width
     *            the width
     * @param height
     *            the height
     * @param recortar
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    String obtenerMiniatura(String tipoAlmacenamiento, String filename, Integer width, Integer height, boolean recortar)
            throws IOException;

    /**
     * Gets the image dimensions.
     *
     * @param filename
     *            the filename
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @return the image dimensions
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    AnchuraAlturaDTO getImageDimensions(String filename, String tipoAlmacenamiento) throws IOException;

    /**
     * Gets the image dimensions.
     *
     * @param filename
     *            the filename
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @return the image dimensions
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    AnchuraAlturaDTO getImageDimensions(String urlImagen) throws IOException;

    /**
     * Gets the url image server.
     *
     * @return the url image server
     */
    String getUrlImageServer();

    /**
     * Obtener imagen original.
     *
     * @param imagenDestacada
     *            the imagen destacada
     * @return the string
     */
    String obtenerImagenOriginal(String imagenDestacada);

}
