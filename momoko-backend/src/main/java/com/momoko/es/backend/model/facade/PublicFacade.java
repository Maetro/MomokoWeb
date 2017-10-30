/**
 * PublicFacade.java 26-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.IndexDataDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.IndexService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/public")
public class PublicFacade {

    @Autowired(required = false)
    private EntradaService entradaService;

    @Autowired(required = false)
    private IndexService indexService;

    @GetMapping(path = "/indexData")
    public @ResponseBody IndexDataDTO getInfoIndex() {
        System.out.println("Llamada a los datos para dibujar el index");
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        final IndexDataDTO indexDataDTO = new IndexDataDTO();
        indexDataDTO.setUltimasEntradas(ultimasEntradas);
        indexDataDTO.setLibrosMasVistos(librosMasVistos);
        return indexDataDTO;
    }

    @GetMapping(path = "/entradas")
    public @ResponseBody List<EntradaDTO> getAllEntradas() {
        System.out.println("LLamada a la lista de entradas");
        return this.entradaService.recuperarEntradas();
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody EntradaDTO getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada) {
        System.out.println("Obtener entrada: " + urlEntrada);
        final EntradaDTO entrada = this.entradaService.obtenerEntrada(urlEntrada);
        return entrada;
    }

}
