/**
 * FileSystemStorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import com.momoko.es.util.ConversionUtils;

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
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        final String[] partes = filename.split("\\.");
        filename = ConversionUtils.toSlug(partes[0]);
        System.out.println("Subida imagen_: " + filename + "." + partes[1]);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory " + filename);
            }
            final boolean existeImagen = exists(getImageServerLocation(tipoAlmacenamiento) + "/" + filename + ".png");

            if (!existeImagen) {
                final File newName = File.createTempFile(filename, ".tmp");
                final BufferedImage imageToStore = ImageIO.read(file.getInputStream());
                ImageIO.write(imageToStore, "png", newName);
                guardarEnServidorImagenes(tipoAlmacenamiento, newName, filename + ".png");
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
        File newName;
        try {
            newName = File.createTempFile(name, ".tmp");
            ImageIO.write(image, "png", newName);
            guardarEnServidorImagenes(tipoAlmacenamiento, newName, name + ".png");
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    private void guardarEnServidorImagenes(final String tipoAlmacenamiento, final File outputfile,
            final String fileNameNuevo) {
        final CloseableHttpClient client = HttpClients.createDefault();
        final String urlUpload = this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlUpload();
        final HttpPost httpPost = new HttpPost(urlUpload + "upload.php");

        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.addBinaryBody("fileToUpload", outputfile, ContentType.MULTIPART_FORM_DATA, fileNameNuevo);
        builder.addTextBody("carpeta", tipoAlmacenamiento);
        final HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        try {
            final CloseableHttpResponse response = client.execute(httpPost);
            client.close();
            response.getEntity().getContent();
            System.out.println(response);
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
        guardarEnServidorImagenes(tipoAlmacenamiento, newName, newName.getName());
    }

    @Override
    public String obtenerMiniatura(final String tipoAlmacenamiento, final String fileNameOriginal, final Integer width,
            final Integer height, final boolean recortar) throws IOException {

        final String[] trozos = fileNameOriginal.split("\\.")[0].split("/");
        String fileName = trozos[trozos.length - 1];
        final String fileNameNuevo = fileName + "_thumbnail_" + (recortar ? "1_" : "") + width + "px_" + height + "px"
                + ".png";
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
                    BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, resizeMode, width,
                            height, Scalr.OP_ANTIALIAS);

                    if (recortar && ((scaledImg.getWidth() - width) >= 0) && ((scaledImg.getHeight() - height) >= 0)) {
                        scaledImg = Scalr.crop(scaledImg, (scaledImg.getWidth() - width) / 2,
                                (scaledImg.getHeight() - height) / 2, width, height);
                    }
                    final File newName = File.createTempFile(fileNameNuevo, ".tmp");
                    ImageIO.write(scaledImg, "png", newName);
                    guardarEnServidorImagenes(tipoAlmacenamiento, newName, fileNameNuevo);
                }
            }
        }
        return this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlImages() + tipoAlmacenamiento
                + "/" + fileNameNuevo;
    }

    public boolean existe(final File locationMiniatura) {
        return locationMiniatura.length() > 0;
    }

    private File descargarImagen(final String urlFile, final String name) {
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

    private File obtenerImagenDeInputStream(final InputStream inputStream, final String name) {
        OutputStream outputStream = null;
        File tempFile = null;
        try {
            tempFile = File.createTempFile(name, ".tmp");

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

    private Path getFileLocation(final String tipoAlmacenamiento) {
        return Paths.get(this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlFiles() + "/"
                + tipoAlmacenamiento);
    }

    private String getImageServerLocation(final String tipoAlmacenamiento) {
        return this.momokoConfiguracion.getMomokoConfiguracion().get("directorios").getUrlFiles() + "/"
                + tipoAlmacenamiento;
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

        final File locationOriginal = descargarImagen(getImageServerLocation(tipoAlmacenamiento) + "/" + filename,
                filename);
        final AnchuraAlturaDTO resultado = new AnchuraAlturaDTO();
        if (locationOriginal.exists()) {
            final InputStream imagenOriginalInputStream = new FileInputStream(locationOriginal);
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

    @Override
    public boolean crearCarpetaSiNoexiste(final String carpeta) {

        final Path location = getFileLocation(carpeta);
        try {
            Files.createDirectory(location);
        } catch (final IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

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
