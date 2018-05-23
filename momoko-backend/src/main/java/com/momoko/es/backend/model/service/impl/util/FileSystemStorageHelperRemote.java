package com.momoko.es.backend.model.service.impl.util;

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageHelperRemote implements FileSystemStorageHelper{

    private MomokoConfiguracion momokoConfiguracion;

    private static FileSystemStorageHelperLocal instance;

    public FileSystemStorageHelperRemote(MomokoConfiguracion momokoConfiguracion) {
        this.momokoConfiguracion = momokoConfiguracion;
    }

    public static FileSystemStorageHelper getInstance(final MomokoConfiguracion momokoConfiguracion) {
        if (instance == null){
            instance = new FileSystemStorageHelperLocal(momokoConfiguracion);
        }
        return instance;
    }

    @Override
    public String store(final MultipartFile file, final String tipoAlmacenamiento) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String nuevoArchivo = "";
        final String[] partes = filename.split("\\.");
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
        String nuevoArchivo = "";
        try {

                newName = File.createTempFile(name, ".tmp");
                ImageIO.write(image, extension, newName);
                guardarEnServidorImagenes(tipoAlmacenamiento, newName, name + "." + extension);

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return nuevoArchivo;
    }

    public Path getFileLocation(final String tipoAlmacenamiento) {

            return Paths.get(
                    this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles() + "/" + tipoAlmacenamiento);

    }

    private String getImageServerLocation(final String tipoAlmacenamiento) {
        return this.momokoConfiguracion.getDirectorios().getRemote().getUrlFiles() + "/" + tipoAlmacenamiento;
    }

    private static boolean exists(final String URLName) {
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


    private void guardarEnServidorImagenes(final String tipoAlmacenamiento, final File outputfile,
                                           final String fileNameNuevo) {
        final CloseableHttpClient client = HttpClients.createDefault();
        final String urlUpload = this.momokoConfiguracion.getDirectorios().getRemote().getUrlUpload();
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
}
