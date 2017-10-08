/**
 * PageController.java 08-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class PageController {

    @GetMapping(path = "/lista-generos")
    public void obtenerPaginaGeneros() {
        System.out.println("Cargar lista de géneros");
    }

}
