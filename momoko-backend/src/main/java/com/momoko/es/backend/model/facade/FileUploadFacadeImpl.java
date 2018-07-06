/**
 * FileUploadFacade.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.momoko.es.api.dto.response.StringResponseDTO;
import com.momoko.es.api.exceptions.StorageFileNotFoundException;
import com.momoko.es.api.facade.FileUploadFacade;
import com.momoko.es.backend.model.service.StorageService;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es" })
@RequestMapping(path = "/modelo")
public class FileUploadFacadeImpl implements FileUploadFacade {

    private final StorageService storageService;

    @Autowired
    public FileUploadFacadeImpl(final StorageService storageService) {
        this.storageService = storageService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upload")
    public @ResponseBody StringResponseDTO handleFileUpload(@RequestParam("uploadFile") final MultipartFile[] files,
            @RequestParam("tipoSubida") final String tipoSubida) {
        final StringBuilder buider = new StringBuilder();
        if (!ArrayUtils.isEmpty(files)) {
            for (final MultipartFile multipartFile : files) {
                final String finalFileName = this.storageService.store(multipartFile, tipoSubida);
                buider.append(finalFileName);
            }
        }
        final StringResponseDTO response = new StringResponseDTO(buider.toString());

        return response;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(final StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
