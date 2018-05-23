package com.momoko.es.backend.model.service.impl.util;

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageHelperLocal implements FileSystemStorageHelper {

    private MomokoConfiguracion momokoConfiguracion;

    private static FileSystemStorageHelperLocal instance;

    public FileSystemStorageHelperLocal(MomokoConfiguracion momokoConfiguracion) {
        this.momokoConfiguracion = momokoConfiguracion;
    }

    public static FileSystemStorageHelper getInstance(final MomokoConfiguracion momokoConfiguracion) {
        if (instance == null) {
            instance = new FileSystemStorageHelperLocal(momokoConfiguracion);
        }
        return instance;
    }

    @Override
    public String store(final MultipartFile file,                    final String tipoAlmacenamiento) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String nuevoArchivo = "";
        final String[] partes = filename.split("\\.");
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
        File newName;
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

    public Path getFileLocation( final String tipoAlmacenamiento) {

        return Paths
                .get(momokoConfiguracion.getDirectorios().getLocal().getUrlFiles() + "/" + tipoAlmacenamiento);

    }
}
