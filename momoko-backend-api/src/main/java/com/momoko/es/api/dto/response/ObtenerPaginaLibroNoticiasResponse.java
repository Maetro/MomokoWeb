/**
 * ObtenerPaginaLibroNoticiasResponse.java 19-dic-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;

/**
 * The Class ObtenerPaginaLibroNoticiasResponse.
 */
public class ObtenerPaginaLibroNoticiasResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 318745796338010279L;

    /** The libro. */
    private LibroDTO libro;

    /** The tres ultimas entradas. */
    private List<EntradaSimpleDTO> noticias;

    private List<DatoEntradaDTO> datoEntrada;

    /** The numero entradas. */
    private Integer numeroEntradas;

    public LibroDTO getLibro() {
        return this.libro;
    }

    public void setLibro(final LibroDTO libro) {
        this.libro = libro;
    }

    public List<EntradaSimpleDTO> getNoticias() {
        return this.noticias;
    }

    public void setNoticias(final List<EntradaSimpleDTO> noticias) {
        this.noticias = noticias;
    }

    public Integer getNumeroEntradas() {
        return this.numeroEntradas;
    }

    public void setNumeroEntradas(final Integer numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public List<DatoEntradaDTO> getDatoEntrada() {
        return datoEntrada;
    }

    public void setDatoEntrada(List<DatoEntradaDTO> datoEntrada) {
        this.datoEntrada = datoEntrada;
    }
}
