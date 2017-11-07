/**
 * PublicFacade.java 26-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.IndexDataDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.response.ObtenerEntradaResponse;
import com.momoko.es.api.response.ObtenerFichaLibroResponse;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.LibroService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/public")
public class PublicFacade {

    @Autowired(required = false)
    private EntradaService entradaService;

    @Autowired(required = false)
    private IndexService indexService;

    @Autowired(required = false)
    private LibroService libroService;

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
    public @ResponseBody ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada) {
        System.out.println("Obtener entrada: " + urlEntrada);
        final ObtenerEntradaResponse respuesta = this.entradaService.obtenerEntrada(urlEntrada);
        return respuesta;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro) {
        System.out.println("Obtener libro: " + urlLibro);
        final ObtenerFichaLibroResponse respuesta = this.libroService.obtenerLibro(urlLibro);
        respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return respuesta;
    }

    // TODO: BORRAME
    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsLibros")
    void generarURLsLibros() throws Exception {
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        for (final LibroDTO libroDTO : libros) {
            if (StringUtils.isBlank(libroDTO.getUrlLibro())) {
                final String urlLibro = toSlug(libroDTO.getTitulo());
                libroDTO.setUrlLibro(urlLibro);
                this.libroService.guardarLibro(libroDTO);
            }
        }
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(final String input) {
        final String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        final String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        final String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
