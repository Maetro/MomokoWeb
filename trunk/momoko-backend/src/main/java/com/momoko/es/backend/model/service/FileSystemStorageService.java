/**
 * FileSystemStorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.exceptions.StorageException;
import com.momoko.es.backend.model.service.properties.StorageProperties;

/**
 * The Class FileSystemStorageService.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final String location;

    @Autowired
    public FileSystemStorageService(final StorageProperties properties) {
        this.location = properties.getLocation();
    }

    @Autowired
    ServletContext servletContext;

    @Override
    public void store(final MultipartFile file) {
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
            final Path location = getFileLocation().resolve(filename);

            Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(getFileLocation(), 1).filter(path -> !path.equals(getFileLocation()))
                    .map(path -> getFileLocation().relativize(path));
        } catch (final IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(final String filename) {
        return getFileLocation().resolve(filename);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(getFileLocation().toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(getFileLocation());
        } catch (final IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private Path getFileLocation() {
        return Paths.get("D:\\XAMPP\\htdocs\\images");
    }

}
