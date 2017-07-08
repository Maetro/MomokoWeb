/**
 * LibroEntity.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class LibroEntity.
 */
@Entity
@Table(name = "libro")
public class LibroEntity {

    /** The libro id. */
    private @Id @GeneratedValue Integer libroId;

    /** The autor id. */
    private Integer autorId;

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

    /**
     * Instantiates a new libro entity.
     */
    public LibroEntity() {
    }

    /**
     * Gets the libro id.
     *
     * @return the libro id
     */
    public Integer getLibroId() {
        return this.libroId;
    }

    /**
     * Sets the libro id.
     *
     * @param libroId
     *            the new libro id
     */
    public void setLibroId(final Integer libroId) {
        this.libroId = libroId;
    }

    /**
     * Gets the autor id.
     *
     * @return the autor id
     */
    public Integer getAutorId() {
        return this.autorId;
    }

    /**
     * Sets the autor id.
     *
     * @param autorId
     *            the new autor id
     */
    public void setAutorId(final Integer autorId) {
        this.autorId = autorId;
    }

    /**
     * Gets the saga id.
     *
     * @return the saga id
     */
    public Integer getSagaId() {
        return this.sagaId;
    }

    /**
     * Sets the saga id.
     *
     * @param sagaId
     *            the new saga id
     */
    public void setSagaId(final Integer sagaId) {
        this.sagaId = sagaId;
    }

    /**
     * Gets the editorial id.
     *
     * @return the editorial id
     */
    public Integer getEditorialId() {
        return this.editorialId;
    }

    /**
     * Sets the editorial id.
     *
     * @param editorialId
     *            the new editorial id
     */
    public void setEditorialId(final Integer editorialId) {
        this.editorialId = editorialId;
    }

    /**
     * Gets the genero id.
     *
     * @return the genero id
     */
    public Integer getGeneroId() {
        return this.generoId;
    }

    /**
     * Sets the genero id.
     *
     * @param generoId
     *            the new genero id
     */
    public void setGeneroId(final Integer generoId) {
        this.generoId = generoId;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the ano edicion.
     *
     * @return the ano edicion
     */
    public Integer getAnoEdicion() {
        return this.anoEdicion;
    }

    /**
     * Sets the ano edicion.
     *
     * @param anoEdicion
     *            the new ano edicion
     */
    public void setAnoEdicion(final Integer anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    /**
     * Gets the cita libro.
     *
     * @return the cita libro
     */
    public String getCitaLibro() {
        return this.citaLibro;
    }

    /**
     * Sets the cita libro.
     *
     * @param citaLibro
     *            the new cita libro
     */
    public void setCitaLibro(final String citaLibro) {
        this.citaLibro = citaLibro;
    }

    /**
     * Gets the resumen.
     *
     * @return the resumen
     */
    public String getResumen() {
        return this.resumen;
    }

    /**
     * Sets the resumen.
     *
     * @param resumen
     *            the new resumen
     */
    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    /**
     * Gets the enlace amazon.
     *
     * @return the enlace amazon
     */
    public String getEnlaceAmazon() {
        return this.enlaceAmazon;
    }

    /**
     * Sets the enlace amazon.
     *
     * @param enlaceAmazon
     *            the new enlace amazon
     */
    public void setEnlaceAmazon(final String enlaceAmazon) {
        this.enlaceAmazon = enlaceAmazon;
    }

    /**
     * Gets the url imagen.
     *
     * @return the url imagen
     */
    public String getUrlImagen() {
        return this.urlImagen;
    }

    /**
     * Sets the url imagen.
     *
     * @param urlImagen
     *            the new url imagen
     */
    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof LibroEntity)) {
            return false;
        }
        final LibroEntity castOther = (LibroEntity) other;
        return new EqualsBuilder().append(this.libroId, castOther.libroId).append(this.autorId, castOther.autorId)
                .append(this.sagaId, castOther.sagaId).append(this.editorialId, castOther.editorialId)
                .append(this.generoId, castOther.generoId).append(this.titulo, castOther.titulo)
                .append(this.anoEdicion, castOther.anoEdicion).append(this.citaLibro, castOther.citaLibro)
                .append(this.resumen, castOther.resumen).append(this.enlaceAmazon, castOther.enlaceAmazon)
                .append(this.urlImagen, castOther.urlImagen).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.libroId).append(this.autorId).append(this.sagaId)
                .append(this.editorialId).append(this.generoId)
                .append(this.titulo).append(this.anoEdicion).append(this.citaLibro).append(this.resumen)
                .append(this.enlaceAmazon)
                .append(this.urlImagen).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("libroId", this.libroId).append("autorId", this.autorId)
                .append("sagaId", this.sagaId)
                .append("editorialId", this.editorialId).append("generoId", this.generoId).append("titulo", this.titulo)
                .append("anoEdicion", this.anoEdicion).append("citaLibro", this.citaLibro)
                .append("resumen", this.resumen)
                .append("enlaceAmazon", this.enlaceAmazon).append("urlImagen", this.urlImagen).toString();
    }

}
