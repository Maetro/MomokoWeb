/**
 * LibroDTO.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LibroDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8006086179924231655L;

    /** The libro id. */
    private Integer libroId;

    /** The autor id. */
    private Set<AutorDTO> autores;

    /** The saga id. */
    private Integer sagaId;

    /** The editorial id. */
    private Integer editorialId;

    /** The genero id. */
    private Integer generoId;

    /** The titulo. */
    private String titulo;

    /** The ano edicion. */
    private Integer anoEdicion;

    /** The cita libro. */
    private String citaLibro;

    /** The resumen. */
    private String resumen;

    /** The enlace amazon. */
    private String enlaceAmazon;

    /** The url imagen. */
    private String urlImagen;

    public Integer getLibroId() {
        return this.libroId;
    }

    public void setLibroId(final Integer libroId) {
        this.libroId = libroId;
    }

    /**
     * Gets the autores.
     *
     * @return the autores
     */
    public Set<AutorDTO> getAutores() {
        return this.autores;
    }

    /**
     * Sets the autores.
     *
     * @param autores
     *            the new autores
     */
    public void setAutores(final Set<AutorDTO> autores) {
        this.autores = autores;
    }

    public Integer getSagaId() {
        return this.sagaId;
    }

    public void setSagaId(final Integer sagaId) {
        this.sagaId = sagaId;
    }

    public Integer getEditorialId() {
        return this.editorialId;
    }

    public void setEditorialId(final Integer editorialId) {
        this.editorialId = editorialId;
    }

    public Integer getGeneroId() {
        return this.generoId;
    }

    public void setGeneroId(final Integer generoId) {
        this.generoId = generoId;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoEdicion() {
        return this.anoEdicion;
    }

    public void setAnoEdicion(final Integer anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    public String getCitaLibro() {
        return this.citaLibro;
    }

    public void setCitaLibro(final String citaLibro) {
        this.citaLibro = citaLibro;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    public String getEnlaceAmazon() {
        return this.enlaceAmazon;
    }

    public void setEnlaceAmazon(final String enlaceAmazon) {
        this.enlaceAmazon = enlaceAmazon;
    }

    public String getUrlImagen() {
        return this.urlImagen;
    }

    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof LibroDTO)) {
            return false;
        }
        final LibroDTO castOther = (LibroDTO) other;
        return new EqualsBuilder().append(this.libroId, castOther.libroId).append(this.autores, castOther.autores)
                .append(this.sagaId, castOther.sagaId).append(this.editorialId, castOther.editorialId)
                .append(this.generoId, castOther.generoId).append(this.titulo, castOther.titulo)
                .append(this.anoEdicion, castOther.anoEdicion).append(this.citaLibro, castOther.citaLibro)
                .append(this.resumen, castOther.resumen).append(this.enlaceAmazon, castOther.enlaceAmazon)
                .append(this.urlImagen, castOther.urlImagen).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.libroId).append(this.autores).append(this.sagaId)
                .append(this.editorialId).append(this.generoId)
                .append(this.titulo).append(this.anoEdicion).append(this.citaLibro).append(this.resumen)
                .append(this.enlaceAmazon)
                .append(this.urlImagen).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("libroId", this.libroId).append("autores", this.autores)
                .append("sagaId", this.sagaId)
                .append("editorialId", this.editorialId).append("generoId", this.generoId).append("titulo", this.titulo)
                .append("anoEdicion", this.anoEdicion).append("citaLibro", this.citaLibro)
                .append("resumen", this.resumen)
                .append("enlaceAmazon", this.enlaceAmazon).append("urlImagen", this.urlImagen).toString();
    }

}
