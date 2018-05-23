/**
 * FileSystemStorageHelper.java 23-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface File system storage helper.
 */
public abstract class FileSystemStorageHelper {

    /**
     * Store.
     *
     * @param file
     *            the file
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @return the string
     */
    public abstract String store(MultipartFile file, String tipoAlmacenamiento);

    /**
     * Store with extension.
     *
     * @param image
     *            the image
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @param name
     *            the name
     * @param extension
     *            the extension
     * @return the string
     */
    public abstract String storeWithExtension(BufferedImage image, String tipoAlmacenamiento, String name,
            String extension);

    /**
     * Gets file location.
     *
     * @param tipoAlmacenamiento
     *            the tipo almacenamiento
     * @return the file location
     */
    public abstract Path getFileLocation(String tipoAlmacenamiento);

    public void createThumbnail(final InputStream fileInputStream, final String tipoAlmacenamiento,
            final String filename) throws IOException {
        final BufferedImage imageToScale = ImageIO.read(fileInputStream);
        final BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, 480, 1, Scalr.OP_ANTIALIAS);
        final String[] fileNameSplits = filename.split("\\.");

        final File newName = new File(
                getFileLocation(tipoAlmacenamiento) + "/" + fileNameSplits[0] + "_thumbnail" + ".jpg");

        saveImage(tipoAlmacenamiento, newName, scaledImg);

    }

    protected abstract void saveImage(String tipoAlmacenamiento, File newName, BufferedImage scaledImg)
            throws IOException;

    public abstract String getThumbnail(String tipoAlmacenamiento, String fileNameOriginal, Integer width,
            Integer height, boolean recortar) throws IOException;

    protected void createJpegWithPredefinedCompression(final String tipoAlmacenamiento, final String fileNameNuevo,
            final BufferedImage scaledImg, final File newName) throws IOException {
        final Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
        final ImageWriter writer = (ImageWriter) iter.next();

        final ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwp.setCompressionQuality(0.85F);

        final FileImageOutputStream output = new FileImageOutputStream(newName);
        writer.setOutput(output);
        final IIOImage image = new IIOImage(scaledImg, null, null);
        writer.write(null, image, iwp);
        writer.dispose();

    }

    public abstract String getImageServerLocation(String tipoAlmacenamiento);

    public File descargarImagen(final String urlFile, final String name) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File tempFile = null;
        try {
            tempFile = File.createTempFile(name, ".tmp");

            final URL url = new URL(urlFile);
            inputStream = url.openStream();

            outputStream = new FileOutputStream(tempFile);

            final byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

        } catch (final MalformedURLException e) {
            System.out.println("MalformedURLException :- " + e.getMessage());

        } catch (final FileNotFoundException e) {
            System.out.println("FileNotFoundException :- " + e.getMessage());

        } catch (final IOException e) {
            System.out.println("IOException :- " + e.getMessage());

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (final IOException e) {
                System.out.println("Finally IOException :- " + e.getMessage());
            }
        }
        return tempFile;
    }

    public static boolean exists(final String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            // HttpURLConnection.setInstanceFollowRedirects(false)
            final HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
