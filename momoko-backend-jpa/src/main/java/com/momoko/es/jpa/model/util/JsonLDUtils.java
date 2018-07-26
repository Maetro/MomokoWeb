/**
 * JsonLDUtils.java 21-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.api.datosestructurados.*;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.math.BigDecimal;
import java.util.ArrayList;

public class JsonLDUtils {

    public static String crearJsonLDOpiniones(final LibroDTO libro, final EntradaDTO entrada,
                                              final BigDecimal puntuacion) {
        String jsonLDOpiniones = "";
        if ((libro != null) && (entrada != null)) {

            final BookReview bookReview = new BookReview();
            bookReview.setContext("http://schema.org");
            bookReview.setType("WebPage");
            bookReview.setName("Análisis libro - " + libro.getTitulo());
            bookReview.setUrl("https://momoko.es/opiniones/" + entrada.getUrlEntrada());
            GenreDTO genero = null;

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
            if (libro.getNumeroPaginas() != null) {
                mainEntity.setNumberOfPages(libro.getNumeroPaginas().toString());
            }
            if (libro.getEditorial() != null) {
                mainEntity.setPublisher(libro.getEditorial().getNombreEditorial());
            }

            final ArrayList<Review> reviews = new ArrayList<Review>();
            final Review reviewMomoko = new Review();
            reviewMomoko.setType("Review");
            reviewMomoko.setAuthor(entrada.getRedactor().getNombre());
            reviewMomoko.setDatePublished(entrada.getFechaAlta().toString());
            reviewMomoko.setName(entrada.getTituloEntrada());
            reviewMomoko.setDescription(entrada.getResumenEntrada());
            // reviewMomoko.setReviewBody(entrada.getContenidoEntrada());
            if (puntuacion != null) {
                final ReviewRating ratingMomoko = new ReviewRating();
                ratingMomoko.setBestRating("100");
                ratingMomoko.setWorstRating("0");
                ratingMomoko.setRatingValue(puntuacion.toString());
                ratingMomoko.setType("Rating");
                reviewMomoko.setReviewRating(ratingMomoko);
                reviews.add(reviewMomoko);
            }
            mainEntity.setReview(reviews);
            bookReview.setMainEntity(mainEntity);
            final ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.setSerializationInclusion(Include.NON_NULL);
                jsonLDOpiniones = mapper.writeValueAsString(bookReview);
            } catch (final JsonProcessingException e) {
                e.printStackTrace();
            }
            jsonLDOpiniones = StringUtils.replace(jsonLDOpiniones, "\"context\"", "\"@context\"");
            jsonLDOpiniones = StringUtils.replace(jsonLDOpiniones, "\"type\"", "\"@type\"");
        }
        return jsonLDOpiniones;
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

    public static String crearJsonLDOpiniones(final SagaDTO saga, final EntradaDTO entrada,
                                              final BigDecimal puntuacion) {
        final String jsonLDOpiniones = "";
        return jsonLDOpiniones;
    }

}
