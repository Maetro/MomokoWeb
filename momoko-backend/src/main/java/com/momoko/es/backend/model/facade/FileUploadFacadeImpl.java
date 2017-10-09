/**
 * FileUploadFacade.java 02-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momoko.es.api.exceptions.StorageFileNotFoundException;
import com.momoko.es.api.facade.FileUploadFacade;
import com.momoko.es.backend.model.service.StorageService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadFacadeImpl implements FileUploadFacade {

    private final StorageService storageService;

    @Autowired
    public FileUploadFacadeImpl(final StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("uploadFile") final MultipartFile file,
            final RedirectAttributes redirectAttributes) {
        this.storageService.init();
        this.storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(final StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
