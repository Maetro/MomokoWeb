/**
 * LibroEntity.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "libroId"), inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "autorId"))
    private Set<AutorEntity> autores;

    /** The saga id. */
    private Integer sagaId;

    /** The editorial id. */
    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private EditorialEntity editorial;

    /** The genero id. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "libro_genero", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "libroId"), inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "generoId"))
    private Set<GeneroEntity> generos;

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
     * Gets the autores.
     *
     * @return the autores
     */
    public Set<AutorEntity> getAutores() {
        return this.autores;
    }

    /**
     * Sets the autores.
     *
     * @param autores
     *            the new autores
     */
    public void setAutores(final Set<AutorEntity> autores) {
        this.autores = autores;
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
    public EditorialEntity getEditorial() {
        return this.editorial;
    }

    /**
     * Sets the editorial id.
     *
     * @param editorialId
     *            the new editorial id
     */
    public void setEditorial(final EditorialEntity editorialId) {
        this.editorial = editorialId;
    }

    /**
     * Gets the generos.
     *
     * @return the generos
     */
    public Set<GeneroEntity> getGeneros() {
        return this.generos;
    }

    /**
     * Sets the generos.
     *
     * @param generos
     *            the new generos
     */
    public void setGeneros(final Set<GeneroEntity> generos) {
        this.generos = generos;
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
        return new EqualsBuilder().append(this.libroId, castOther.libroId).append(this.autores, castOther.autores)
                .append(this.sagaId, castOther.sagaId).append(this.editorial, castOther.editorial)
                .append(this.generos, castOther.generos).append(this.titulo, castOther.titulo)
                .append(this.anoEdicion, castOther.anoEdicion).append(this.citaLibro, castOther.citaLibro)
                .append(this.resumen, castOther.resumen).append(this.enlaceAmazon, castOther.enlaceAmazon)
                .append(this.urlImagen, castOther.urlImagen).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.libroId).append(this.autores).append(this.sagaId)
                .append(this.editorial).append(this.generos)
                .append(this.titulo).append(this.anoEdicion).append(this.citaLibro).append(this.resumen)
                .append(this.enlaceAmazon)
                .append(this.urlImagen).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("libroId", this.libroId).append("autorId", this.autores)
                .append("sagaId", this.sagaId)
                .append("editorialId", this.editorial).append("generoId", this.generos).append("titulo", this.titulo)
                .append("anoEdicion", this.anoEdicion).append("citaLibro", this.citaLibro)
                .append("resumen", this.resumen)
                .append("enlaceAmazon", this.enlaceAmazon).append("urlImagen", this.urlImagen).toString();
    }

}
