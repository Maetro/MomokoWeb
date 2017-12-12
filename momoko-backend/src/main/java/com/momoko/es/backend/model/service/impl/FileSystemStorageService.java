/**
 * FileSystemStorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import com.momoko.es.backend.model.service.StorageService;

/**
 * The Class FileSystemStorageService.
 */
@Service
public class FileSystemStorageService implements StorageService {

    @Autowired
    public FileSystemStorageService() {
    }

    @Autowired
    ServletContext servletContext;

    @Autowired
    MomokoConfiguracion momokoConfiguracion;

    /*
     * (non-Javadoc)
     *
     * @see com.momoko.es.backend.model.service.StorageService#store(org.springframework.web.multipart.MultipartFile,
     * java.lang.String)
     */
    @Override
    public void store(final MultipartFile file, final String tipoAlmacenamiento) {
        final String filename = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("Subida imagen_: " + filename);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory " + filename);
            }
            final Path location = getFileLocation(tipoAlmacenamiento).resolve(filename);
            if (!Files.exists(location)) {
                Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.momoko.es.backend.model.service.StorageService#store(org.springframework.web.multipart.MultipartFile,
     * java.lang.String)
     */
    @Override
    public void store(final BufferedImage image, final String tipoAlmacenamiento, final String name) {

        final File outputfile = new File(getFileLocation(tipoAlmacenamiento).resolve(name).toString() + ".png");
        try {
            Files.createFile(getFileLocation(tipoAlmacenamiento).resolve(name + ".png"));
            ImageIO.write(image, "png", outputfile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void crearMiniatura(final InputStream fileInputStream, final String tipoAlmacenamiento,
            final String filename) throws IOException {
        final BufferedImage imageToScale = ImageIO.read(fileInputStream);
        final BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, 480, 1, Scalr.OP_ANTIALIAS);
        final String[] fileNameSplits = filename.split("\\.");

        final File newName = new File(
                getFileLocation(tipoAlmacenamiento) + "/" + fileNameSplits[0] + "_thumbnail" + ".png");
        ImageIO.write(scaledImg, "png", newName);
    }

    @Override
    public String obtenerMiniatura(final String tipoAlmacenamiento, final String fileNameOriginal, final Integer width,
            final Integer height, final boolean recortar) throws IOException {

        final String[] trozos = fileNameOriginal.split("\\.")[0].split("/");
        String fileName = trozos[trozos.length - 1];
        final String fileNameNuevo = fileName + "_thumbnail_" + (recortar ? "1_" : "") + width + "px_" + height + "px"
                + ".png";
        fileName += "." + fileNameOriginal.split("\\.")[1];
        final Path locationMiniatura = getFileLocation(tipoAlmacenamiento).resolve(fileNameNuevo);
        final Path locationOriginal = getFileLocation(tipoAlmacenamiento).resolve(fileName);
        if (!Files.exists(locationMiniatura) && Files.exists(locationOriginal)) {

            final InputStream imagenOriginalInputStream = Files.newInputStream(locationOriginal);
            final BufferedImage imageToScale = ImageIO.read(imagenOriginalInputStream);
            final float sourceAspectRatio = (float) imageToScale.getWidth() / imageToScale.getHeight();
            final float destinationAspectRatio = (float) width / height;
            final Scalr.Mode resizeMode = sourceAspectRatio > destinationAspectRatio ? Scalr.Mode.FIT_TO_HEIGHT
                    : Scalr.Mode.FIT_TO_WIDTH;
            BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, resizeMode, width, height,
                    Scalr.OP_ANTIALIAS);

            if (recortar && ((scaledImg.getWidth() - width) >= 0) && ((scaledImg.getHeight() - height) >= 0)) {
                scaledImg = Scalr.crop(scaledImg, (scaledImg.getWidth() - width) / 2,
                        (scaledImg.getHeight() - height) / 2, width, height);
            }
            final File newName = new File(getFileLocation(tipoAlmacenamiento) + "/" + fileNameNuevo);
            ImageIO.write(scaledImg, "png", newName);
        }
        return this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlImages() + tipoAlmacenamiento
                + "/" + fileNameNuevo;
    }

    @Override
    public Stream<Path> loadAll(final String tipoAlmacenamiento) {
        try {
            return Files.walk(getFileLocation(tipoAlmacenamiento), 1)
                    .filter(path -> !path.equals(getFileLocation(tipoAlmacenamiento)))
                    .map(path -> getFileLocation(tipoAlmacenamiento).relativize(path));
        } catch (final IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(final String filename, final String tipoAlmacenamiento) {
        return getFileLocation(tipoAlmacenamiento).resolve(filename);
    }

    @Override
    public void init(final String tipoAlmacenamiento) {
        try {
            Files.createDirectories(getFileLocation(tipoAlmacenamiento));
        } catch (final IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private Path getFileLocation(final String tipoAlmacenamiento) {
        return Paths.get(this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlFiles() + "/"
                + tipoAlmacenamiento);
    }

    private File multipartToFile(final MultipartFile multipart) throws IllegalStateException, IOException {
        final File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
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
        final Path locationOriginal = getFileLocation(tipoAlmacenamiento).resolve(filename);
        final AnchuraAlturaDTO resultado = new AnchuraAlturaDTO();
        if (Files.exists(locationOriginal)) {
            final InputStream imagenOriginalInputStream = Files.newInputStream(locationOriginal);
            final BufferedImage imagen = ImageIO.read(imagenOriginalInputStream);
            final int width = imagen.getWidth();
            final int height = imagen.getHeight();
            resultado.setAltura(height);
            resultado.setAnchura(width);
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
        return getImageDimensions(lista[elementos - 1], lista[elementos - 2]);
    }

    @Override
    public String getUrlImageServer() {
        return this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlImages();
    }

    @Override
    public String obtenerImagenOriginal(final String imagenDestacada) {
        return getUrlImageServer() + imagenDestacada;
    }

}
