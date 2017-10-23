/**
 * FileSystemStorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.configuration.MomokoConfiguracion;

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
            Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);

            final BufferedImage imageToScale = ImageIO.read(file.getInputStream());
            final BufferedImage scaledImg = Scalr.resize(imageToScale, Method.ULTRA_QUALITY, 480, 1,
                    Scalr.OP_ANTIALIAS);
            final String[] fileNameSplits = filename.split("\\.");

            final File newName = new File(
                    getFileLocation(tipoAlmacenamiento) + "/" + fileNameSplits[0] + "_thumbnail" + ".png");
            ImageIO.write(scaledImg, "png", newName);

        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
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

}
