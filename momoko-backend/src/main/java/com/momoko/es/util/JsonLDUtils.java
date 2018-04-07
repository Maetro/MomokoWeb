/**
 * JsonLDUtils.java 21-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.api.datosestructurados.BlogPosting;
import com.momoko.es.api.datosestructurados.BookMainEntity;
import com.momoko.es.api.datosestructurados.BookReview;
import com.momoko.es.api.datosestructurados.Logo;
import com.momoko.es.api.datosestructurados.Publisher;
import com.momoko.es.api.datosestructurados.Review;
import com.momoko.es.api.datosestructurados.ReviewRating;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;

public class JsonLDUtils {

    /**
     * Crear json ld analisis.
     *
     * @param libro
     *            the libro
     * @param entrada
     *            the entrada
     * @param puntuacion
     *            the puntuacion
     * @return the string
     */
    public static String crearJsonLDAnalisis(final LibroDTO libro, final EntradaDTO entrada,
            final BigDecimal puntuacion) {
        String jsonLDAnalisis = "";
        if ((libro != null) && (entrada != null)) {

            final BookReview bookReview = new BookReview();
            bookReview.setContext("http://schema.org");
            bookReview.setType("WebPage");
            bookReview.setName("Análisis libro - " + libro.getTitulo());
            bookReview.setUrl("https://momoko.es/analisis/" + entrada.getUrlEntrada());
            GeneroDTO genero = null;

            if (CollectionUtils.isNotEmpty(libro.getGeneros())) {
                genero = libro.getGeneros().iterator().next();
            }
            if (genero != null) {
                final String generosString = MomokoUtils.generarGenerosString(libro);
                bookReview.setBreadcrumb(
                        "Libros > " + genero.getCategoria().getNombreCategoria() + " > " + generosString);
            }
            final BookMainEntity mainEntity = new BookMainEntity();
            mainEntity.setType("Book");

            final String autoresString = MomokoUtils.generarAutoresString(libro);
            mainEntity.setAuthor(autoresString);
            if (libro.getAnoPublicacion() != null) {
                mainEntity.setDatePublished(libro.getAnoPublicacion().toString());
            }
            mainEntity.setImage("https://momoko.es/images/" + libro.getUrlImagen());
            mainEntity.setName(libro.getTitulo());
            mainEntity.setNumberOfPages(libro.getNumeroPaginas().toString());
            if (libro.getEditorial() != null) {
                mainEntity.setPublisher(libro.getEditorial().getNombreEditorial());
            }

            final ArrayList<Review> reviews = new ArrayList<Review>();
            final Review reviewMomoko = new Review();
            reviewMomoko.setType("Review");
            reviewMomoko.setAuthor(entrada.getAutor().getNombre());
            reviewMomoko.setDatePublished(entrada.getFechaAlta().toString());
            reviewMomoko.setName(entrada.getTituloEntrada());
            reviewMomoko.setDescription(entrada.getResumenEntrada());
            // reviewMomoko.setReviewBody(entrada.getContenidoEntrada());
            final ReviewRating ratingMomoko = new ReviewRating();
            ratingMomoko.setBestRating("100");
            ratingMomoko.setWorstRating("0");
            ratingMomoko.setRatingValue(puntuacion.toString());
            ratingMomoko.setType("Rating");
            reviewMomoko.setReviewRating(ratingMomoko);
            reviews.add(reviewMomoko);
            mainEntity.setReview(reviews);
            bookReview.setMainEntity(mainEntity);
            final ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.setSerializationInclusion(Include.NON_NULL);
                jsonLDAnalisis = mapper.writeValueAsString(bookReview);
            } catch (final JsonProcessingException e) {
                e.printStackTrace();
            }
            jsonLDAnalisis = StringUtils.replace(jsonLDAnalisis, "\"context\"", "\"@context\"");
            jsonLDAnalisis = StringUtils.replace(jsonLDAnalisis, "\"type\"", "\"@type\"");
        }
        return jsonLDAnalisis;
    }

    public static String crearJsonLDMiscelaneo(final EntradaDTO entrada) {
        String jsonLDMiscelaneo = "";
        if ((entrada != null)) {
            final BlogPosting blogPosting = new BlogPosting();
            blogPosting.setContext("http://schema.org");
            blogPosting.setType("BlogPosting");
            blogPosting.setHeadline(entrada.getTituloEntrada());
            blogPosting.setImage(entrada.getImagenDestacada());
            blogPosting.setUrl("https://momoko.es/" + entrada.getUrlEntrada());
            blogPosting.setDateCreated(entrada.getFechaAlta().toString());
            blogPosting.setDescription(entrada.getResumenEntrada());
            blogPosting.setArticleBody(entrada.getContenidoEntrada());
            blogPosting.setDatePublished(entrada.getFechaAlta().toString());
            final Publisher publisher = new Publisher();
            publisher.setName("Momoko");
            publisher.setType("Organization");
            final Logo logo = new Logo();
            logo.setType("ImageObject");
            logo.setUrl("https://momoko.es/images/logo_momoko.png");
            publisher.setLogo(logo);
            blogPosting.setPublisher(publisher);
            blogPosting.setDateModified(entrada.getFechaModificacion().toString());
            blogPosting.setMainEntityOfPage("True");
            try {
                blogPosting.setWordcount(String.valueOf(countWords(entrada.getContenidoEntrada())));
            } catch (final Exception e) {
                e.printStackTrace();
            }
            blogPosting.setAuthor(entrada.getEditorNombre());
            final ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.setSerializationInclusion(Include.NON_NULL);
                jsonLDMiscelaneo = mapper.writeValueAsString(blogPosting);
            } catch (final JsonProcessingException e) {
                e.printStackTrace();
            }
            jsonLDMiscelaneo = StringUtils.replace(jsonLDMiscelaneo, "\"context\"", "\"@context\"");
            jsonLDMiscelaneo = StringUtils.replace(jsonLDMiscelaneo, "\"type\"", "\"@type\"");
        }
        return jsonLDMiscelaneo;
    }

    private static int countWords(final String html) throws Exception {
        final org.jsoup.nodes.Document dom = Jsoup.parse(html);
        final String text = dom.text();

        return text.split(" ").length;
    }

}
