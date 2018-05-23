package com.momoko.es.backend.model.service.impl.util;

import com.momoko.es.backend.configuration.MomokoConfiguracion;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The interface File system storage helper.
 */
public interface FileSystemStorageHelper {

    /**
     * Store.
     *
     * @param file               the file
     * @param tipoAlmacenamiento the tipo almacenamiento
     * @return the string
     */
    public String store(final MultipartFile file, final String tipoAlmacenamiento);

    public String storeWithExtension(final BufferedImage image, final String tipoAlmacenamiento, final String name,
                                   final String extension);

    /**
     * Gets file location.
     *
     * @param tipoAlmacenamiento the tipo almacenamiento
     * @return the file location
     */
    public Path getFileLocation(String tipoAlmacenamiento);


}
