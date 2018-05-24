/**
 * FileSystemStorageService.java 02-sep-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.backend.model.service.impl.util.FileSystemStorageFactory;
import com.momoko.es.backend.model.service.impl.util.FileSystemStorageHelper;
import com.momoko.es.util.ConversionUtils;

/**
 * The Class FileSystemStorageService.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private FileSystemStorageHelper fileSystemHelper;

    public FileSystemStorageService() {
    }

    @Autowired
    ServletContext servletContext;

    @Autowired
    MomokoConfiguracion momokoConfiguracion;

    public FileSystemStorageHelper getFileSystemHelper() {
        if (this.fileSystemHelper == null) {
            this.fileSystemHelper = FileSystemStorageFactory.getFileSystemStorageHelper(this.momokoConfiguracion);
        }
        return this.fileSystemHelper;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.momoko.es.backend.model.service.StorageService#store(org.springframework.web.multipart.MultipartFile,
     * java.lang.String)
     */
    @Override
    public String store(final MultipartFile file, final String tipoAlmacenamiento) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        final String[] partes = filename.split("\\.");
        filename = ConversionUtils.toSlug(partes[0]);
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException("Cannot store file with relative path outside current directory " + filename);
        }

        final String nuevoArchivo = this.getFileSystemHelper().store(file, tipoAlmacenamiento, filename, partes);

        return nuevoArchivo;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.momoko.es.backend.model.service.StorageService#store(org.springframework.web.multipart.MultipartFile,
     * java.lang.String)
     */
    @Override
    public String store(final BufferedImage image, final String tipoAlmacenamiento, final String name,
            final String extension) {
        return this.getFileSystemHelper().storeWithExtension(image, tipoAlmacenamiento, name, extension);
    }

    public void crearMiniatura(final InputStream fileInputStream, final String tipoAlmacenamiento,
            final String filename) throws IOException {
        this.getFileSystemHelper().createThumbnail(fileInputStream, tipoAlmacenamiento, filename);

    }

    @Override
    public String obtenerMiniatura(final String tipoAlmacenamiento, final String fileNameOriginal, final Integer width,
            final Integer height, final boolean recortar) throws IOException {
        final String miniatura = this.getFileSystemHelper().getThumbnail(tipoAlmacenamiento, fileNameOriginal, width,
                height, recortar);
        return miniatura;
    }

    public boolean existe(final File locationMiniatura) {
        return locationMiniatura.length() > 0;
    }

    private boolean esServidorLocal() {
        return this.momokoConfiguracion.isEsServidorLocal();
    }

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
    @Override
    public AnchuraAlturaDTO getImageDimensions(final String filename, final String tipoAlmacenamiento)
            throws IOException {

        final File locationOriginal = this.getFileSystemHelper().descargarImagen(
                this.getFileSystemHelper().getImageServerLocation(tipoAlmacenamiento) + "/" + filename, filename);
        final AnchuraAlturaDTO resultado = new AnchuraAlturaDTO();
        if (locationOriginal.exists()) {
            final InputStream imagenOriginalInputStream = new FileInputStream(locationOriginal);
            final BufferedImage imagen = ImageIO.read(imagenOriginalInputStream);
            if (imagen != null) {
                final int width = imagen.getWidth();
                final int height = imagen.getHeight();
                resultado.setAltura(height);
                resultado.setAnchura(width);
            }
        }
        return resultado;
    }

    @Override
    public String obtenerMiniatura(final String urlImagen, final Integer width, final Integer height,
            final boolean recortar) throws IOException {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return obtenerMiniatura(lista[elementos - 2], lista[elementos - 1], width, height, recortar);
    }

    @Override
    public AnchuraAlturaDTO getImageDimensions(final String urlImagen) throws IOException {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        // TODO: Corregir
        String tipoAlmacenamiento = "";
        String temp = lista[elementos - 2];
        for (int i = elementos - 2; !temp.equals("images"); i--) {
            tipoAlmacenamiento = lista[i] + "/" + tipoAlmacenamiento;
            if (i == 0) {
                break;
            }
            temp = lista[i];
        }
        tipoAlmacenamiento = tipoAlmacenamiento.substring(7, tipoAlmacenamiento.length() - 1);
        return getImageDimensions(lista[elementos - 1], tipoAlmacenamiento);
    }

    @Override
    public String getUrlImageServer() {
        return this.momokoConfiguracion.getDirectorios().getRemote().getUrlImages();
    }

    @Override
    public String obtenerImagenOriginal(final String imagenDestacada) {
        return getUrlImageServer() + imagenDestacada;
    }

    @Override
    public boolean crearCarpetaSiNoExiste(final String carpeta) {

        final Path location = this.getFileSystemHelper().getFileLocation(carpeta);
        try {
            Files.createDirectory(location);
        } catch (final IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public String getImageFolder() {
        if (esServidorLocal()) {
            return this.momokoConfiguracion.getDirectorios().getLocal().getUrlFiles();
        } else {
            return this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles();
        }
    }

    @Override
    public String getUrlSitemap() {
        if (esServidorLocal()) {
            return this.momokoConfiguracion.getDirectorios().getLocal().getUrlSitemap();
        } else {
            return this.momokoConfiguracion.getDirectorios().getRemote().getUrlSitemap();
        }
    }

    @Override
    public String getTemplateFolder() {
        if (esServidorLocal()) {
            return this.momokoConfiguracion.getDirectorios().getLocal().getUrlTemplates();
        } else {
            return this.momokoConfiguracion.getDirectorios().getLocal().getUrlTemplates();
        }
    }

}
