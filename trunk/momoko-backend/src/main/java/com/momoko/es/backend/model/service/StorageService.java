/**
 * StorageService.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Interface StorageService.
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    void deleteAll();
}
