/**
 * ObtenerIndexDataReponseDTO.java 02-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.IndexDataDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

/**
 * The Class ObtenerIndexDataReponseDTO.
 */
public class ObtenerIndexDataReponseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4898776657912046120L;

    /** The ultimas entradas. */
    private List<EntradaSimpleDTO> ultimasEntradas;

    /** The libros mas vistos. */
    private List<LibroSimpleDTO> librosMasVistos;

    /** The ultimos analisis. */
    private List<LibroSimpleDTO> ultimosAnalisis;

    /** The ultimo comic analizado. */
    private LibroEntradaSimpleDTO ultimoComicAnalizado;

    /**
     * Gets the ultimas entradas.
     *
     * @return the ultimas entradas
     */
    public List<EntradaSimpleDTO> getUltimasEntradas() {
        return this.ultimasEntradas;
    }

    /**
     * Sets the ultimas entradas.
     *
     * @param ultimasEntradas
     *            the new ultimas entradas
     */
    public void setUltimasEntradas(final List<EntradaSimpleDTO> ultimasEntradas) {
        this.ultimasEntradas = ultimasEntradas;
    }

    /**
     * Gets the libros mas vistos.
     *
     * @return the libros mas vistos
     */
    public List<LibroSimpleDTO> getLibrosMasVistos() {
        return this.librosMasVistos;
    }

    /**
     * Sets the libros mas vistos.
     *
     * @param librosMasVistos
     *            the new libros mas vistos
     */
    public void setLibrosMasVistos(final List<LibroSimpleDTO> librosMasVistos) {
        this.librosMasVistos = librosMasVistos;
    }

    /**
     * Gets the ultimo comic analizado.
     *
     * @return the ultimo comic analizado
     */
    public LibroEntradaSimpleDTO getUltimoComicAnalizado() {
        return this.ultimoComicAnalizado;
    }

    /**
     * Sets the ultimo comic analizado.
     *
     * @param ultimoComicAnalizado2
     *            the new ultimo comic analizado
     */
    public void setUltimoComicAnalizado(final LibroEntradaSimpleDTO ultimoComicAnalizado2) {
        this.ultimoComicAnalizado = ultimoComicAnalizado2;
    }

    /**
     * Gets the ultimos analisis.
     *
     * @return the ultimos analisis
     */
    public List<LibroSimpleDTO> getUltimosAnalisis() {
        return this.ultimosAnalisis;
    }

    /**
     * Sets the ultimos analisis.
     *
     * @param ultimosAnalisis
     *            the new ultimos analisis
     */
    public void setUltimosAnalisis(final List<LibroSimpleDTO> ultimosAnalisis) {
        this.ultimosAnalisis = ultimosAnalisis;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof IndexDataDTO)) {
            return false;
        }
        final ObtenerIndexDataReponseDTO castOther = (ObtenerIndexDataReponseDTO) other;
        return new EqualsBuilder().append(this.ultimasEntradas, castOther.ultimasEntradas).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.ultimasEntradas).toHashCode();
    }
}
