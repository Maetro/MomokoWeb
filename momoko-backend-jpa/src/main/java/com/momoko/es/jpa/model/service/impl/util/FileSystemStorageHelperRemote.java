/**
 * FileSystemStorageHelperRemote.java 23-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.commons.configuration.MomokoConfiguracion;

public class FileSystemStorageHelperRemote extends FileSystemStorageHelper {

    private final MomokoConfiguracion momokoConfiguracion;

    private static FileSystemStorageHelperLocal instance;

    public FileSystemStorageHelperRemote(final MomokoConfiguracion momokoConfiguracion) {
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
        final String nuevoArchivo = "";
        try {
            final boolean existeImagen = exists(
                    getImageServerLocation(tipoAlmacenamiento) + "/" + filename + "." + partes[1]);

            if (!existeImagen) {
                final File newName = File.createTempFile(filename, ".tmp");
                final BufferedImage imageToStore = ImageIO.read(file.getInputStream());
                ImageIO.write(imageToStore, partes[1], newName);
                guardarEnServidorImagenes(tipoAlmacenamiento, newName, filename + "." + partes[1]);
            }

        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return nuevoArchivo;
    }

    @Override
    public String storeWithExtension(final BufferedImage image, final String tipoAlmacenamiento, final String name,
            final String extension) {
        File newName;
        final String nuevoArchivo = "";
        try {

            newName = File.createTempFile(name, ".tmp");
            ImageIO.write(image, extension, newName);
            guardarEnServidorImagenes(tipoAlmacenamiento, newName, name + "." + extension);

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return nuevoArchivo;
    }

    @Override
    public Path getFileLocation(final String tipoAlmacenamiento) {

        return Paths
                .get(this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles() + "/" + tipoAlmacenamiento);

    }

    @Override
    protected void saveImage(final String tipoAlmacenamiento, final File newName, final BufferedImage scaledImg)
            throws IOException {
        ImageIO.write(scaledImg, "jpg", newName);
        guardarEnServidorImagenes(tipoAlmacenamiento, newName, newName.getName());
    }

    @Override
    public String getThumbnail(final String tipoAlmacenamiento, final String fileNameOriginal, final Integer width,
            final Integer height, final boolean recortar) throws IOException {
        String miniatura = "";
        final String[] trozos = fileNameOriginal.split("\\.")[0].split("/");
        String fileName = trozos[trozos.length - 1];
        final String fileNameNuevo = fileName + "_thumbnail_" + (recortar ? "1_" : "") + width + "px_" + height + "px"
                + ".jpg";
        fileName += "." + fileNameOriginal.split("\\.")[1];

        final boolean existeMiniatura = exists(getImageServerLocation(tipoAlmacenamiento) + "/" + fileNameNuevo);
        if (!existeMiniatura) {
            final boolean existeOriginal = exists(getImageServerLocation(tipoAlmacenamiento) + "/" + fileName);
            if (existeOriginal) {
                final File locationOriginal = descargarImagen(
                        getImageServerLocation(tipoAlmacenamiento) + "/" + fileName, fileName);

                final InputStream imagenOriginalInputStream = new FileInputStream(locationOriginal);
                final BufferedImage imageToScale = ImageIO.read(imagenOriginalInputStream);
                if (imageToScale != null) {
                    final float sourceAspectRatio = (float) imageToScale.getWidth() / imageToScale.getHeight();
                    final float destinationAspectRatio = (float) width / height;
                    final Scalr.Mode resizeMode = sourceAspectRatio > destinationAspectRatio ? Scalr.Mode.FIT_TO_HEIGHT
                            : Scalr.Mode.FIT_TO_WIDTH;
                    BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, resizeMode,
                            Math.toIntExact(Math.round(width * 1.1)), Math.toIntExact(Math.round(height * 1.1)));

                    if (recortar && ((scaledImg.getWidth() - width) >= 0) && ((scaledImg.getHeight() - height) >= 0)) {
                        scaledImg = Scalr.crop(scaledImg, (scaledImg.getWidth() - width) / 2,
                                (scaledImg.getHeight() - height) / 2, width, height);
                    }
                    final File newName = File.createTempFile(fileNameNuevo, ".jpg");
                    // createJpegWithPredefinedCompression(tipoAlmacenamiento, fileNameNuevo, scaledImg, newName);
                    ImageIO.write(scaledImg, "jpg", newName);
                    guardarEnServidorImagenes(tipoAlmacenamiento, newName, fileNameNuevo);
                }
            }
        }
        miniatura = this.momokoConfiguracion.getDirectorios().getRemote().getUrlImages() + tipoAlmacenamiento + "/"
                + fileNameNuevo;
        return miniatura;

    }

    @Override
    public String getImageServerLocation(final String tipoAlmacenamiento) {
        return this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles() + "/" + tipoAlmacenamiento;
    }

    private void guardarEnServidorImagenes(final String tipoAlmacenamiento, final File outputfile,
            final String fileNameNuevo) {
        final CloseableHttpClient client = HttpClients.createDefault();
        final String urlUpload = this.momokoConfiguracion.getDirectorios().getRemote().getUrlUpload();
        final HttpPost httpPost = new HttpPost(urlUpload + "upload.php");

        // final MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        // builder.addBinaryBody("fileToUpload", outputfile, ContentType.MULTIPART_FORM_DATA, fileNameNuevo);
        // builder.addTextBody("carpeta", tipoAlmacenamiento);
        // final HttpEntity multipart = builder.build();
        // httpPost.setEntity(multipart);

        try {
            final CloseableHttpResponse response = client.execute(httpPost);
            client.close();
            response.getEntity().getContent();
            System.out.println(response);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

}
