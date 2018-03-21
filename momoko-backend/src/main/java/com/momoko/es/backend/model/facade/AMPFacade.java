/**
 * AMPFacade.java 17-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.JsonLDUtils;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es", "https://momoko.es" })
@RequestMapping(path = "/amp")
public class AMPFacade {

    @Autowired
    private StorageService almacenImagenes;

    @Autowired
    private LibroService libroService;

    @Autowired
    private MomokoConfiguracion momokoConfiguracion;

    @GetMapping("/analisis/{url-libro}")
    public @ResponseBody String getMyList(final HttpServletRequest request, final HttpServletResponse response,
            @PathVariable("url-libro") final String urlLibro) {
        final String contentType = "text/html;charset=UTF-8";
        response.setContentType(contentType);
        try {
            request.setCharacterEncoding("utf-8");
        } catch (final UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String content = "";
        try {
            content = readFile(this.almacenImagenes.getTemplateFolder() + "/" + "analisis.html",
                    StandardCharsets.UTF_8);
            final EntradaDTO analisis = this.libroService.obtenerAnalisisLibro(urlLibro);
            final LibroDTO libro = analisis.getLibrosEntrada().iterator().next();
            final BigDecimal puntuacion = this.libroService.obtenerPuntucionMomokoLibro(libro.getUrlLibro());
            JsonLDUtils.crearJsonLDAnalisis(libro, analisis, puntuacion);
            content = replaceTagInContent("${titulo}", analisis.getTituloEntrada(), content);
            content = replaceTagInContent("${autor}", analisis.getEditorNombre(), content);
            content = replaceTagInContent("${resumen}", analisis.getResumenEntrada(), content);
            final String miniatura = this.almacenImagenes.obtenerMiniatura(analisis.getImagenDestacada(), 1280, 768,
                    true);
            content = replaceTagInContent("${imagen-entrada}", miniatura, content);

            String body = analisis.getContenidoEntrada();
            while (body.contains("<img ")) {
                final String bloqueImagen = StringUtils.substringBetween(body, "<img ", ">");
                String imagen = StringUtils.substringBetween(bloqueImagen, "src=\"", "\"");
                if (!imagen.contains("https") || !imagen.contains("http")) {
                    imagen = this.almacenImagenes.getUrlImageServer() + imagen;
                }
                final String ampImagen = "<figure>" + "<amp-img src=\"" + imagen
                        + "\" width=\"1280\" height=\"768\" layout=\"responsive\"></amp-img>" + "</figure>";
                body = StringUtils.replace(body, "<img " + bloqueImagen + ">", ampImagen);
            }
            while (body.contains("<p><br></p>")) {
                body = StringUtils.replace(body, "<p><br></p>", "");
            }
            content = replaceTagInContent("${contenido}", body, content);

            content = replaceTagInContent("${meta-titulo}", "Análisis de " + libro.getTitulo(), content);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        return content;
    }

    private String replaceTagInContent(final String tag, final String with, final String content) {
        return StringUtils.replace(content, tag, with);
    }

    static String readFile(final String path, final Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

}
