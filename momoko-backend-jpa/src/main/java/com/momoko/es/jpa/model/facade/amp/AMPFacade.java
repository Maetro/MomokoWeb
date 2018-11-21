/**
 * AMPFacade.java 17-mar-2018
 * <p>
 * Copyright 2018 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.facade.amp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.jpa.model.service.*;
import com.momoko.es.jpa.model.util.ConversionUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.commons.configuration.MomokoConfiguracion;
import com.momoko.es.jpa.model.util.JsonLDUtils;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/amp")
public class AMPFacade {

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired
    private LibroService libroService;

    @Autowired
    private SagaService sagaService;

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private MomokoConfiguracion momokoConfiguracion;

    @Autowired
    private IndexService indexService;

    @GetMapping("/opiniones/{url-entrada}")
    @Cacheable("opinionesAMP")
    public @ResponseBody
    String getOpinionesAMP(final HttpServletRequest request, final HttpServletResponse response,
                           @PathVariable("url-entrada") final String urlEntrada) {
        final String contentType = "text/html;charset=UTF-8";
        response.setContentType(contentType);
        try {
            request.setCharacterEncoding("utf-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content = "";
        try {
            content = readFile(this.almacenImagenes.getTemplateFolder() + "/" + "analisis.html",
                    StandardCharsets.UTF_8);
            final EntradaDTO opinion = this.entradaService.obtenerEntrada(urlEntrada);
            List<ComentarioDTO> comments = this.entradaService.getEntryComments(urlEntrada);
            if (CollectionUtils.isNotEmpty(opinion.getLibrosEntrada())) {
                final LibroDTO libro = opinion.getLibrosEntrada().iterator().next();
                final BigDecimal puntuacion = this.libroService.obtenerPuntucionMomokoLibro(libro.getUrlLibro());
                final String jsonLD = JsonLDUtils.crearJsonLDOpiniones(libro, opinion, puntuacion);
                content = replaceTagInContent("${jsonLD}", jsonLD, content);
                content = replaceTagInContent("${related}", generarRelated(libro), content);
                content = replaceTagInContent("${meta-titulo}", "Análisis de " + libro.getTitulo(), content);
            }
            if (CollectionUtils.isNotEmpty(opinion.getSagasEntrada())) {
                final SagaDTO saga = opinion.getSagasEntrada().iterator().next();
                final BigDecimal puntuacion = this.sagaService.obtenerPuntucionMomokoSaga(saga.getUrlSaga());
                final String jsonLD = JsonLDUtils.crearJsonLDOpiniones(saga, opinion, puntuacion);
                content = replaceTagInContent("${jsonLD}", jsonLD, content);
                content = replaceTagInContent("${related}", generarRelated(saga), content);
            }
            content = replaceTagInContent("${menu}", generarMenu(), content);

            content = replaceTagInContent("${titulo}", opinion.getTituloEntrada(), content);
            content = replaceTagInContent("${autor}", opinion.getEditorNombre(), content);
            content = replaceTagInContent("${resumen}", opinion.getResumenEntrada(), content);

            content = replaceTagInContent("${urlCanonical}", "https://momoko.es/opiniones/" + opinion.getUrlEntrada(),
                    content);
            final String miniatura = this.almacenImagenes.obtenerMiniatura(opinion.getImagenDestacada(), 1280, 768,
                    true);
            content = replaceTagInContent("${imagen-entrada}", miniatura, content);
            this.entradaService.obtenerGaleriasEntradaAmp(opinion);
            String body = opinion.getContenidoEntrada();
            /*
             * <amp-youtube data-videoid="mGENRKrdoGY" layout="responsive" width="480" height="270"></amp-youtube>
             */
            body = adaptarVideosYoutubeAmp(body);

            body = adaptarImagenesAmp(body);
            while (body.contains("style=\"")) {
                final String bloqueStyle = StringUtils.substringBetween(body, "style=\"", "\"");
                body = StringUtils.replace(body, "style=\"" + bloqueStyle + "\"", "");
            }
            while (body.contains("<p><br></p>")) {
                body = StringUtils.replace(body, "<p><br></p>", "");
            }
            content = replaceTagInContent("${contenido}", body, content);
            content = replaceTagInContent("${comments}", generarCommentsSection(comments), content);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        return content;
    }

    public String adaptarImagenesAmp(String body) throws IOException, MalformedURLException {
        while (body.contains("<img ")) {

            final String bloqueImagen = StringUtils.substringBetween(body, "<img ", ">");
            String imagen = StringUtils.substringBetween(bloqueImagen, "src=\"", "\"");
            try {
                AnchuraAlturaDTO anchuraAltura;

                if (!imagen.contains("http")) {
                    final String imageServer = this.almacenImagenes.getUrlImageServer();
                    anchuraAltura = this.almacenImagenes.getImageDimensionsThumbnail(imageServer + imagen);
                    imagen = this.almacenImagenes.getUrlImageServer() + imagen;
                } else {
                    if (imagen.contains("http://momoko.es")) {
                        imagen = imagen.replaceAll("http://", "https://");
                    }
                    final URL url = new URL(imagen);
                    final BufferedImage image = ImageIO.read(url);
                    if (image == null) {
                        body = StringUtils.replace(body, "<img " + bloqueImagen + ">", "");
                        continue;
                    }
                    anchuraAltura = new AnchuraAlturaDTO();
                    anchuraAltura.setAltura(image.getHeight());
                    anchuraAltura.setAnchura(image.getWidth());
                }
                final String ampImagen = "<figure>" + "<amp-img src=\"" + imagen + "\" width=\""
                        + anchuraAltura.getAnchura() + "\" height=\"" + anchuraAltura.getAltura()
                        + "\" layout=\"responsive\"></amp-img>" + "</figure>";

                body = StringUtils.replace(body, "<img " + bloqueImagen + ">", ampImagen);
            } catch (final ArrayIndexOutOfBoundsException e) {
                body = StringUtils.replace(body, "<img " + bloqueImagen + ">", "");
            }
        }
        return body;
    }

    public String adaptarVideosYoutubeAmp(String body) {
        while (body.contains("<iframe ")) {
            final String bloqueVideo = StringUtils.substringBetween(body, "<iframe ", ">");
            String video = StringUtils.substringBetween(bloqueVideo, "src=\"", "\"");
            if (video.contains("?")) {
                video = video.split("\\?")[0];
            }
            final String[] partes = video.split("/");
            final String ampVideo = "<amp-youtube data-videoid=\"" + partes[partes.length - 1] + "\""
                    + "    layout=\"responsive\"" + "    width=\"480\" height=\"270\"></amp-youtube>";
            body = StringUtils.replace(body, "<iframe " + bloqueVideo + ">", ampVideo);
        }
        return body;
    }

    @GetMapping("/miscelaneo/{url-entrada}")
    @Cacheable("miscelaneoAMP")
    public @ResponseBody
    String getMiscelaneoAMP(final HttpServletRequest request, final HttpServletResponse response,
                            @PathVariable("url-entrada") final String urlEntrada) {
        final String contentType = "text/html;charset=UTF-8";
        response.setContentType(contentType);
        try {
            request.setCharacterEncoding("utf-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content = "";
        try {
            content = readFile(this.almacenImagenes.getTemplateFolder() + "/" + "miscelaneo.html",
                    StandardCharsets.UTF_8);
            List<ComentarioDTO> comments = this.entradaService.getEntryComments(urlEntrada);
            final ObtenerEntradaResponse obtenerEntrada = this.entradaService.obtenerEntrada(urlEntrada, false);
            final List<EntradaDTO> otrosMiscelaneos = this.entradaService
                    .obtenerEntradasAleatoriasDeTipo(EntryTypeEnum.MISCELLANEOUS.getValue());
            final EntradaDTO entrada = obtenerEntrada.getEntrada();

            final String jsonLD = JsonLDUtils.crearJsonLDMiscelaneo(entrada);
            content = replaceTagInContent("${menu}", generarMenu(), content);
            content = replaceTagInContent("${jsonLD}", jsonLD, content);
            content = replaceTagInContent("${titulo}", entrada.getTituloEntrada(), content);
            content = replaceTagInContent("${autor}", entrada.getEditorNombre(), content);
            content = replaceTagInContent("${resumen}", entrada.getResumenEntrada(), content);
            content = replaceTagInContent("${comments}", generarCommentsSection(comments), content);
            content = replaceTagInContent("${related}", generarRelatedMiscelaneo(otrosMiscelaneos), content);
            content = replaceTagInContent("${urlCanonical}", "https://momoko.es/" + entrada.getUrlEntrada(), content);
            final String miniatura = this.almacenImagenes.obtenerMiniatura(entrada.getImagenDestacada(), 1280, 768,
                    true);
            content = replaceTagInContent("${imagen-entrada}", miniatura, content);
            this.entradaService.obtenerGaleriasEntradaAmp(entrada);
            String body = entrada.getContenidoEntrada();
            body = adaptarVideosYoutubeAmp(body);
            body = adaptarImagenesAmp(body);
            while (body.contains("style=\"")) {
                final String bloqueStyle = StringUtils.substringBetween(body, "style=\"", "\"");
                body = StringUtils.replace(body, "style=\"" + bloqueStyle + "\"", "");
            }
            while (body.contains("<p><br></p>")) {
                body = StringUtils.replace(body, "<p><br></p>", "");
            }
            content = replaceTagInContent("${contenido}", body, content);

            content = replaceTagInContent("${meta-titulo}", entrada.getTituloEntrada(), content);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        return content;
    }

    @GetMapping("/noticia/{url-entrada}")
    @Cacheable("noticiaAMP")
    public @ResponseBody
    String getNoticiaAMP(final HttpServletRequest request, final HttpServletResponse response,
                         @PathVariable("url-entrada") final String urlEntrada) {
        final String contentType = "text/html;charset=UTF-8";
        response.setContentType(contentType);
        try {
            request.setCharacterEncoding("utf-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content = "";
        try {
            content = readFile(this.almacenImagenes.getTemplateFolder() + "/" + "noticia.html", StandardCharsets.UTF_8);

            final ObtenerEntradaResponse obtenerEntrada = this.entradaService.obtenerEntrada(urlEntrada, false);
            List<ComentarioDTO> comments = this.entradaService.getEntryComments(urlEntrada);
            final List<EntradaDTO> otrasNoticas = this.entradaService
                    .obtenerEntradasAleatoriasDeTipo(EntryTypeEnum.NEWS.getValue());
            final EntradaDTO entrada = obtenerEntrada.getEntrada();

            final String jsonLD = JsonLDUtils.crearJsonLDMiscelaneo(entrada);
            content = replaceTagInContent("${menu}", generarMenu(), content);
            content = replaceTagInContent("${jsonLD}", jsonLD, content);
            content = replaceTagInContent("${titulo}", entrada.getTituloEntrada(), content);
            content = replaceTagInContent("${autor}", entrada.getEditorNombre(), content);
            content = replaceTagInContent("${resumen}", entrada.getResumenEntrada(), content);
            content = replaceTagInContent("${related}", generarRelatedMiscelaneo(otrasNoticas), content);
            content = replaceTagInContent("${comments}", generarCommentsSection(comments), content);
            content = replaceTagInContent("${urlCanonical}", "https://momoko.es/noticia/" + entrada.getUrlEntrada(), content);
            final String miniatura = this.almacenImagenes.obtenerMiniatura(entrada.getImagenDestacada(), 1280, 768,
                    true);
            content = replaceTagInContent("${imagen-entrada}", miniatura, content);
            this.entradaService.obtenerGaleriasEntradaAmp(entrada);
            String body = entrada.getContenidoEntrada();
            body = adaptarVideosYoutubeAmp(body);
            body = adaptarImagenesAmp(body);
            while (body.contains("style=\"")) {
                final String bloqueStyle = StringUtils.substringBetween(body, "style=\"", "\"");
                body = StringUtils.replace(body, "style=\"" + bloqueStyle + "\"", "");
            }
            while (body.contains("<p><br></p>")) {
                body = StringUtils.replace(body, "<p><br></p>", "");
            }
            content = replaceTagInContent("${contenido}", body, content);

            content = replaceTagInContent("${meta-titulo}", entrada.getTituloEntrada(), content);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        return content;
    }

    private String generarCommentsSection(List<ComentarioDTO> comments) {
        StringBuilder builder = new StringBuilder();
        if (comments.size() == 1){
            builder.append("<h2 class=\"mb3\">" + comments.size() + " Comentario</h2>\n");
        } else {
            builder.append("<h2 class=\"mb3\">" + comments.size() + " Comentarios</h2>\n");
        }
        if (CollectionUtils.isNotEmpty(comments)) {
            boolean first = true;
            int number = 0;
            DateFormat fmt = new SimpleDateFormat("dd MMM yyyy", new Locale("ES"));
            for (ComentarioDTO comment : comments) {
                if (comment.getComentarioPadreId() == null) {
                    number = 0;
                    if (first) {
                        builder.append("      <div class=\"flex px1 pt1\">\n");
                        first = false;
                    } else {
                        builder.append("      <div class=\"flex new px1 pt1\">\n");
                    }
                    builder.append("        <amp-img width=\"44\" height=\"44\" class=\"avatar mr1 flex-none\" alt=\"user\" src=\"" + comment.getAutor().getAvatar() + "\"></amp-img>\n" +
                            "        <div class=\"ampstart-card p1 comment\">\n" +
                            "          <p>\n" +
                            "            <span class=\"user\">" + comment.getAutor().getNombre() + "</span>\n" +
                            "            <span class=\"date\">" + fmt.format(comment.getFecha()).toUpperCase() + "</span>\n" +
                            "          </p>\n" +
                            "          <p>" + comment.getTextoComentario() + "</p>\n" +
                            "        </div>\n" +
                            "      </div>\n");
                } else {
                    if (number % 2 == 0) {
                        builder.append("      <div class=\"flex px1 pt1 right\">\n" +
                                "        <div class=\"ampstart-card p1 comment\">\n" +
                                "          <p>\n" +
                                "            <span class=\"user\">" + comment.getAutor().getNombre() + "</span>\n" +
                                "            <span class=\"date\">" + fmt.format(comment.getFecha()).toUpperCase() + "</span>\n" +
                                "          </p>\n" +
                                "          <p>" + comment.getTextoComentario() + "</p>\n" +
                                "        </div>\n" +
                                "        <amp-img width=\"44\" height=\"44\" class=\"avatar mr1 flex-none\" alt=\"user\" src=\"" + comment.getAutor().getAvatar() + "\"></amp-img>\n" +
                                "      </div>\n");
                    } else {
                        builder.append("      <div class=\"flex px1 pt1\">\n" +
                                "        <amp-img width=\"44\" height=\"44\" class=\"avatar mr1 flex-none\" alt=\"user\" src=\"" + comment.getAutor().getAvatar() + "\"></amp-img>\n" +
                                "        <div class=\"ampstart-card p1 comment\">\n" +
                                "          <p>\n" +
                                "            <span class=\"user\">" + comment.getAutor().getNombre() + "</span>\n" +
                                "            <span class=\"date\">" + fmt.format(comment.getFecha()).toUpperCase() + "</span>\n" +
                                "          </p>\n" +
                                "          <p>" + comment.getTextoComentario() + "</p>\n" +
                                "        </div>\n" +
                                "      </div>\n");

                    }
                    number++;

                }
            }
        } else {
            builder.append("<h4>El mundo no puede vivir sin tu sabiduría.</h4>");
        }
        return builder.toString();
    }

    @GetMapping("/comments/{url-entrada}")
    @Cacheable("comments")
    public @ResponseBody
    AmpCommentList getCommentsAMP(final HttpServletRequest request, final HttpServletResponse response,
                                  @PathVariable("url-entrada") final String urlEntrada) {
        final String contentType = "text/json;charset=UTF-8";
        response.setContentType(contentType);
        List<ComentarioDTO> comments = this.entradaService.getEntryComments(urlEntrada);
        AmpCommentList ampCommentList = ConversionUtils.toAmpComments(comments);
        return ampCommentList;
    }

    private String generarRelatedMiscelaneo(final List<EntradaDTO> otrosMiscelaneos) {

        final StringBuilder builder = new StringBuilder();
        if (otrosMiscelaneos.size() > 0) {
            builder.append("<section class=\"ampstart-related-section mb4 px3\">"
                    + "        <h2 class=\"h3 mb1\">Estos análisis puede que también te interesen:</h2>"
                    + "        <ul class=\"ampstart-related-section-items list-reset flex flex-wrap m0\">");
            for (final EntradaDTO entradaDTO : otrosMiscelaneos) {
                if ((entradaDTO.getImagenDestacada() != null)
                        && entradaDTO.getImagenDestacada().contains("imagenes-destacadas")) {
                    String imagen = entradaDTO.getImagenDestacada();

                    try {
                        imagen = this.almacenImagenes.obtenerMiniatura(entradaDTO.getImagenDestacada(), 200, 174, true);
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                    builder.append("<li class=\"col-12 sm-col-4 md-col-4 lg-col-4 pr2 py2\">");
                    builder.append("<a href=\"https://momoko.es/amp/miscelaneo/" + entradaDTO.getUrlEntrada()
                            + "\" target=\"_blank\" class=\"inline-block p1\" aria-label=\"Link to "
                            + entradaDTO.getTituloEntrada() + "\">");
                    builder.append("<figure class=\"ampstart-image-with-caption m0 relative mb4\">");
                    builder.append(
                            "<amp-img src=\"" + imagen + "\" alt=\"Imagen destacada de " + entradaDTO.getTituloEntrada()
                                    + "\" width=\"233\" height=\"202\" layout=\"responsive\" class=\"\"></amp-img>");
                    builder.append("<figcaption class=\"h5 mt1 px3\"> " + entradaDTO.getTituloEntrada()
                            + "</figcaption> </figure> </a> </li>");
                }
            }
            builder.append("</ul></section></main>");
        }

        return builder.toString();
    }

    private String generarRelated(final LibroDTO libro) {

        final List<EntradaDTO> ultimasOpiniones = this.entradaService.obtenerOpinionesGeneros(libro);
        final StringBuilder builder = new StringBuilder();
        if (ultimasOpiniones.size() > 0) {
            builder.append("<section class=\"ampstart-related-section mb4 px3\">"
                    + "        <h2 class=\"h3 mb1\">Estos análisis puede que también te interesen:</h2>"
                    + "        <ul class=\"ampstart-related-section-items list-reset flex flex-wrap m0\">");
            for (final EntradaDTO entradaDTO : ultimasOpiniones) {
                String imagen = entradaDTO.getImagenDestacada();
                final String urlLibro = entradaDTO.getLibrosEntrada().iterator().next().getUrlLibro();
                try {
                    imagen = this.almacenImagenes.obtenerMiniatura(entradaDTO.getImagenDestacada(), 200, 174, true);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                builder.append("<li class=\"col-12 sm-col-4 md-col-4 lg-col-4 pr2 py2\">");
                builder.append("<a href=\"https://momoko.es/amp/opiniones/" + urlLibro
                        + "\" target=\"_blank\" class=\"inline-block p1\" aria-label=\"Link to "
                        + entradaDTO.getTituloEntrada() + "\">");
                builder.append("<figure class=\"ampstart-image-with-caption m0 relative mb4\">");
                builder.append(
                        "<amp-img src=\"" + imagen + "\" alt=\"Imagen destacada de " + entradaDTO.getTituloEntrada()
                                + "\" width=\"233\" height=\"202\" layout=\"responsive\" class=\"\"></amp-img>");
                builder.append("<figcaption class=\"h5 mt1 px3\"> " + entradaDTO.getTituloEntrada()
                        + "</figcaption> </figure> </a> </li>");
            }
            builder.append("</ul></section></main>");
        }

        return builder.toString();
    }

    private String generarRelated(final SagaDTO saga) {
        // TODO
        return null;
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

    /**
     * Generar menu.
     *
     * @return the string
     */
    public String generarMenu() {
        final List<MenuDTO> menu = this.indexService.obtenerMenu();
        final StringBuilder builder = new StringBuilder();

        for (final MenuDTO menuDTO : menu) {
            if (CollectionUtils.isNotEmpty(menuDTO.getGeneros())) {
                builder.append("<li class=\"ampstart-nav-item ampstart-nav-dropdown relative \">"
                        + "<amp-accordion layout=\"container\" disable-session-states=\"\" class=\"ampstart-dropdown\"> <section>"
                        + "            <header>" + menuDTO.getNombre() + "</header>"
                        + "            <ul class=\"ampstart-dropdown-items list-reset m0 p0\">");
                for (final GenreDTO generoDTO : menuDTO.getGeneros()) {
                    builder.append("<li class=\"ampstart-dropdown-item\">"
                            + "                <a href=\"https://momoko.es/genero/" + generoDTO.getUrlGenero()
                            + "\" class=\"menulink\">" + "                    <amp-img class=\"amp-sidebar-image\""
                            + "                    src=\"" + generoDTO.getIconoGenero() + "\""
                            + "                    width=\"16\"" + "                    height=\"16\""
                            + "                    alt=\"Icono del genero " + generoDTO.getNombre() + "\"></amp-img>"
                            + generoDTO.getNombre() + "</a>" + "              </li>");
                }
                builder.append("</ul></section></amp-accordion></li>");
            } else {
                builder.append("<li class=\"ampstart-nav-item \">"
                        + "        <a class=\"ampstart-nav-link\" href=\"https://momoko.es/categoria/"
                        + menuDTO.getUrl() + "\">" + menuDTO.getNombre() + "</a>" + "      </li>");
            }
        }
        return builder.toString();
    }

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public @ResponseBody
    ComentarioDTO saveComment(final HttpServletRequest request, final HttpServletResponse response,
                           @RequestParam String name, @RequestParam String comment) throws Exception {

        NuevoComentarioRequest nuevoComentario = new NuevoComentarioRequest();
        nuevoComentario.setEmail(null);
        String refferer = request.getHeader("Referer");
        String[] splitted = refferer.split("/");
        String url = splitted[splitted.length-1];
        if(url.contains("?")){
            url = url.substring(0, url.indexOf("?"));
        }
        EntradaDTO entrada = this.entradaService.obtenerEntrada(url);
        nuevoComentario.setNombre(name);
        nuevoComentario.setContenido(comment);
        nuevoComentario.setEntradaId(entrada.getEntradaId());
        ComentarioDTO comentario = this.comentarioService.guardarComentario(nuevoComentario);
        response.addHeader("AMP-Access-Control-Allow-Source-Origin", "https://momoko.es");
        return comentario;


    }

}