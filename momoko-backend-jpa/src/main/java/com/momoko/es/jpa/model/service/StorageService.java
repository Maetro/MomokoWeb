/**
 * StorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The Interface StorageService.
 */
public interface StorageService {

    /**
     * Store.
     *
     * @param file
     *            the file
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     */
    String store(MultipartFile file, String tipoAlmacenamineto);

    /**
     * Store.
     *
     * @param file
     *            the file
     * @param tipoAlmacenamineto
     *            the tipo almacenamineto
     */
    String store(BufferedImage image, String tipoAlmacenamineto, String name, String extension);

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

    AnchuraAlturaDTO getImageDimensionsThumbnail(String urlImagen) throws IOException;;

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

    /**
     * Crear carpeta si noexiste.
     *
     * @param carpeta
     *            the carpeta
     * @return true, if successful
     */
    boolean crearCarpetaSiNoExiste(String carpeta);

    /**
     * Gets the image folder.
     *
     * @return the image folder
     */
    String getImageFolder();

    /**
     * Gets the url sitemap.
     *
     * @return the url sitemap
     */
    String getUrlSitemap();

    /**
     * Gets the template folder.
     *
     * @return the template folder
     */
    String getTemplateFolder();


}