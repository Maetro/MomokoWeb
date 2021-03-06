/**
 * FileSystemStorageHelperLocal.java 23-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.commons.configuration.MomokoConfiguracion;

public class FileSystemStorageHelperLocal extends FileSystemStorageHelper {

    private final MomokoConfiguracion momokoConfiguracion;

    private static FileSystemStorageHelperLocal instance;

    public FileSystemStorageHelperLocal(final MomokoConfiguracion momokoConfiguracion) {
        this.momokoConfiguracion = momokoConfiguracion;
    }

    public static FileSystemStorageHelper getInstance(final MomokoConfiguracion momokoConfiguracion) {
        if (instance == null) {
            instance = new FileSystemStorageHelperLocal(momokoConfiguracion);
        }
        return instance;
    }

    @Override
    public String store(final MultipartFile file, final String tipoAlmacenamiento, final String filename,
            final String[] partes) {
        String nuevoArchivo = "";
        try {
            nuevoArchivo = filename + "." + partes[1];
            Path location = getFileLocation(tipoAlmacenamiento).resolve(nuevoArchivo);
            int cont = 1;
            while (Files.exists(location)) {
                nuevoArchivo = filename + "_" + cont + "." + partes[1];
                location = getFileLocation(tipoAlmacenamiento).resolve(nuevoArchivo);
                cont++;
            }
            if (!Files.exists(location)) {
                Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return nuevoArchivo;
    }

    @Override
    public String storeWithExtension(final BufferedImage image, final String tipoAlmacenamiento, final String name,
            final String extension) {
        final File newName;
        String nuevoArchivo = "";
        try {
            final File outputfile = new File(
                    getFileLocation(tipoAlmacenamiento).resolve(name).toString() + "." + extension);
            Files.createFile(getFileLocation(tipoAlmacenamiento).resolve(name + "." + extension));
            nuevoArchivo = name + "." + extension;
            ImageIO.write(image, extension, outputfile);

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return nuevoArchivo;
    }

    @Override
    public Path getFileLocation(final String tipoAlmacenamiento) {

        return Paths.get(this.momokoConfiguracion.getDirectorios().getLocal().getUrlFiles() + "/" + tipoAlmacenamiento);

    }

    @Override
    public String getImageServerLocation(final String tipoAlmacenamiento) {
        return this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles() + "/" + tipoAlmacenamiento;
    }

    @Override
    protected void saveImage(final String tipoAlmacenamiento, final File newName, final BufferedImage scaledImg)
            throws IOException {
        ImageIO.write(scaledImg, "jpg", newName);
    }

    @Override
    public String getThumbnail(final String tipoAlmacenamiento, final String fileNameOriginal, final Integer destinationWidth,
            final Integer destinationHeight, final boolean recortar) throws IOException {
        String miniatura = "";
        final String[] trozos = fileNameOriginal.split("\\.")[0].split("/");
        String fileName = trozos[trozos.length - 1];
        final String fileNameNuevo = fileName + "_thumbnail_" + (recortar ? "1_" : "") + destinationWidth + "px_" + destinationHeight + "px"
                + ".jpg";
        fileName += "." + fileNameOriginal.split("\\.")[1];
        final Path locationMiniatura = getFileLocation(tipoAlmacenamiento).resolve(fileNameNuevo);
        final Path locationOriginal = getFileLocation(tipoAlmacenamiento).resolve(fileName);
        if (!Files.exists(locationMiniatura) && Files.exists(locationOriginal)) {
            if (!recortar){
                final InputStream imagenOriginalInputStream = Files.newInputStream(locationOriginal);
                final BufferedImage originalImageBuffer = ImageIO.read(imagenOriginalInputStream);
                int originalWidth = originalImageBuffer.getWidth();
                int originalHeight = originalImageBuffer.getHeight();
                final float sourceAspectRatio = (float) originalWidth / originalHeight;
                final float destinationAspectRatio = (float) destinationWidth / destinationHeight;
                Scalr.Mode resizeMode = null;
                if ((originalWidth - destinationWidth) > (originalHeight - destinationHeight)){
                    resizeMode = Scalr.Mode.FIT_TO_WIDTH;
                } else {
                    resizeMode = Scalr.Mode.FIT_TO_HEIGHT;
                }

                BufferedImage scaledImg = Scalr.resize(originalImageBuffer, Method.ULTRA_QUALITY, resizeMode, destinationWidth, destinationHeight);


                final File newName = new File(getFileLocation(tipoAlmacenamiento) + "/" + fileNameNuevo);
                ImageIO.write(scaledImg, "jpg", newName);
                imagenOriginalInputStream.close();
            } else {
                final InputStream imagenOriginalInputStream = Files.newInputStream(locationOriginal);
                final BufferedImage imageToScale = ImageIO.read(imagenOriginalInputStream);
                final float sourceAspectRatio = (float) imageToScale.getWidth() / imageToScale.getHeight();
                final float destinationAspectRatio = (float) destinationWidth / destinationHeight;
                final Scalr.Mode resizeMode = sourceAspectRatio > destinationAspectRatio ? Scalr.Mode.FIT_TO_HEIGHT
                        : Scalr.Mode.FIT_TO_WIDTH;

                BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, resizeMode, destinationWidth, destinationHeight);
                if (recortar && ((scaledImg.getWidth() - destinationWidth) >= 0) && ((scaledImg.getHeight() - destinationHeight) >= 0)) {

                    scaledImg = Scalr.crop(scaledImg, (scaledImg.getWidth() - destinationWidth) / 2,
                            (scaledImg.getHeight() - destinationHeight) / 2, destinationWidth, destinationHeight);
                }
                final File newName = new File(getFileLocation(tipoAlmacenamiento) + "/" + fileNameNuevo);
                ImageIO.write(scaledImg, "jpg", newName);
                imagenOriginalInputStream.close();
            }
        }
        miniatura = this.momokoConfiguracion.getDirectorios().getRemote().getUrlImages() + tipoAlmacenamiento + "/"
                + fileNameNuevo;
        return miniatura;
    }

}
