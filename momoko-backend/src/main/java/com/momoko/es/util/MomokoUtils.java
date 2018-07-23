/**
 * MomokoUtils.java 23-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;

public class MomokoUtils {

    public static final String ERROR = "ERROR";
    private static final Log log = LogFactory.getLog(MomokoUtils.class);

    private static ApplicationContext applicationContext;
    private static ObjectMapper objectMapper;

    public MomokoUtils(ApplicationContext applicationContext,
                      ObjectMapper objectMapper) {

        MomokoUtils.applicationContext = applicationContext;
        MomokoUtils.objectMapper = objectMapper;

        log.info("Created MomokoUtils");
    }




    /**
     * Solo nombre y carpeta.
     *
     * @param imagenDestacada
     *            the imagen destacada
     * @return the string
     */
    public static String soloNombreYCarpeta(final String imagenDestacada) {
        final String[] lista = imagenDestacada.split("/");
        final int elementos = lista.length;
        if (elementos >= 2) {
            return lista[elementos - 2] + "/" + lista[elementos - 1];
        } else {
            return imagenDestacada;
        }
    }

    /**
     * Obtener columna galeria.
     *
     * @param columnas
     *            the columnas
     * @return the string
     */
    public static String obtenerColumnaGaleria(final Integer columnas) {
        String classColumn = "";
        switch (columnas) {
        case 1:
            classColumn = "col-sm-12";
            break;
        case 2:
            classColumn = "col-sm-6";
            break;
        case 3:
            classColumn = "col-sm-4";
            break;
        case 4:
            classColumn = "col-sm-3";
            break;
        case 5:
            classColumn = "col-sm-2";
            break;
        case 6:
            classColumn = "col-sm-2";
            break;
        default:
            classColumn = "col-sm-12";
        }
        return classColumn;
    }

    /**
     * Generar autores string.
     *
     * @param libroDTO
     *            the libro dto
     * @return the string
     */
    public static String generarGenerosString(final LibroDTO libroDTO) {
        String generosString = "";
        if (CollectionUtils.isNotEmpty(libroDTO.getGeneros())) {
            final Iterator<GenreDTO> iterator = libroDTO.getGeneros().iterator();
            while (iterator.hasNext()) {
                final GenreDTO autor = iterator.next();
                generosString += autor.getNombre();
                if (iterator.hasNext()) {
                    generosString += ", ";
                }
            }
        }
        return generosString;
    }

    /**
     * Generar autores string.
     *
     * @param libroDTO
     *            the libro dto
     * @return the string
     */
    public static String generarAutoresString(final LibroDTO libroDTO) {
        String autoresString = "";
        if (CollectionUtils.isNotEmpty(libroDTO.getAutores())) {
            final Iterator<AutorDTO> iterator = libroDTO.getAutores().iterator();
            while (iterator.hasNext()) {
                final AutorDTO autor = iterator.next();
                autoresString += autor.getNombre();
                if (iterator.hasNext()) {
                    autoresString += ", ";
                }
            }
        }
        return autoresString;
    }

}
