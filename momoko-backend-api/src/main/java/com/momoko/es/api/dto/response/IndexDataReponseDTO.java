/**
 * IndexDataReponseDTO.java 02-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class IndexDataReponseDTO.
 */
public class IndexDataReponseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4898776657912046120L;

    private List<EntradaSimpleDTO> lastOpinions;

    private List<EntradaSimpleDTO> lastNews;

    private List<EntradaSimpleDTO> lastMiscellaneous;

    /** The libros mas vistos. */
    private List<LibroSimpleDTO> librosMasVistos;

    /** The ultimos analisis. */
    private List<LibroSimpleDTO> ultimosAnalisis;

    /** The ultimo comic analizado. */
    private LibroEntradaSimpleDTO ultimoComicAnalizado;


    public List<EntradaSimpleDTO> getLastOpinions() {
        return lastOpinions;
    }

    public void setLastOpinions(List<EntradaSimpleDTO> lastOpinions) {
        this.lastOpinions = lastOpinions;
    }

    public List<EntradaSimpleDTO> getLastNews() {
        return lastNews;
    }

    public void setLastNews(List<EntradaSimpleDTO> lastNews) {
        this.lastNews = lastNews;
    }

    public List<EntradaSimpleDTO> getLastMiscellaneous() {
        return lastMiscellaneous;
    }

    public void setLastMiscellaneous(List<EntradaSimpleDTO> lastMiscellaneous) {
        this.lastMiscellaneous = lastMiscellaneous;
    }

    public List<LibroSimpleDTO> getLibrosMasVistos() {
        return librosMasVistos;
    }

    public void setLibrosMasVistos(List<LibroSimpleDTO> librosMasVistos) {
        this.librosMasVistos = librosMasVistos;
    }

    public List<LibroSimpleDTO> getUltimosAnalisis() {
        return ultimosAnalisis;
    }

    public void setUltimosAnalisis(List<LibroSimpleDTO> ultimosAnalisis) {
        this.ultimosAnalisis = ultimosAnalisis;
    }

    public LibroEntradaSimpleDTO getUltimoComicAnalizado() {
        return ultimoComicAnalizado;
    }

    public void setUltimoComicAnalizado(LibroEntradaSimpleDTO ultimoComicAnalizado) {
        this.ultimoComicAnalizado = ultimoComicAnalizado;
    }
}
