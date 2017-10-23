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

    void init(String tipoAlmacenamineto);

    void store(MultipartFile file, String tipoAlmacenamineto);

    Stream<Path> loadAll(String tipoAlmacenamineto);

    Path load(String filename, String tipoAlmacenamineto);
}
