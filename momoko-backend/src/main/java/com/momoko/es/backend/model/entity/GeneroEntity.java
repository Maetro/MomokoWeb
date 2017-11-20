/**
 * GeneroEntity.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class AutorEntity.
 */
@Entity
@Table(name = "genero", indexes = { @Index(name = "urlGenero", columnList = "urlGenero", unique = true) })
public class GeneroEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer genero_id;

    /** The nombre. */
    private String nombre;

    /** The imagen cabecera genero. */
    private String imagenCabeceraGenero;

    /** The imagen icono genero. */
    private String imagenIconoGenero;

    /** The genero url. */
    private String urlGenero;

    /** The editorial id. */
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    /** The usuario alta. */
    private String usuarioAlta;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The usuario alta. */
    private String usuarioModificacion;

    /** The fecha alta. */
    private Date fechaModificacion;

    /** The usuario alta. */
    private String usuarioBaja;

    /** The fecha alta. */
    private Date fechaBaja;

    /** The libros autor. */
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "generos", fetch = FetchType.LAZY)
    private Set<LibroEntity> librosGenero;

    /**
     * Gets the genero_id.
     *
     * @return the genero_id
     */
    public Integer getGenero_id() {
        return this.genero_id;
    }

    /**
     * Sets the genero_id.
     *
     * @param genero_id
     *            the new genero_id
     */
    public void setGenero_id(final Integer genero_id) {
        this.genero_id = genero_id;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the libros genero.
     *
     * @return the libros genero
     */
    public Set<LibroEntity> getLibrosGenero() {
        return this.librosGenero;
    }

    /**
     * Sets the libros genero.
     *
     * @param librosGenero
     *            the new libros genero
     */
    public void setLibrosGenero(final Set<LibroEntity> librosGenero) {
        this.librosGenero = librosGenero;
    }

    /**
     * Gets the categoria.
     *
     * @return the categoria
     */
    public CategoriaEntity getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the categoria.
     *
     * @param categoria
     *            the new categoria
     */
    public void setCategoria(final CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets the imagen cabecera genero.
     *
     * @return the imagen cabecera genero
     */
    public String getImagenCabeceraGenero() {
        return this.imagenCabeceraGenero;
    }

    /**
     * Sets the imagen cabecera genero.
     *
     * @param imagenCabeceraGenero
     *            the new imagen cabecera genero
     */
    public void setImagenCabeceraGenero(final String imagenCabeceraGenero) {
        this.imagenCabeceraGenero = imagenCabeceraGenero;
    }

    /**
     * Gets the url genero.
     *
     * @return the url genero
     */
    public String getUrlGenero() {
        return this.urlGenero;
    }

    /**
     * Sets the url genero.
     *
     * @param urlGenero
     *            the new url genero
     */
    public void setUrlGenero(final String urlGenero) {
        this.urlGenero = urlGenero;
    }

    /**
     * Gets the imagen icono genero.
     *
     * @return the imagen icono genero
     */
    public String getImagenIconoGenero() {
        return this.imagenIconoGenero;
    }

    /**
     * Sets the imagen icono genero.
     *
     * @param imagenIconoGenero
     *            the new imagen icono genero
     */
    public void setImagenIconoGenero(final String imagenIconoGenero) {
        this.imagenIconoGenero = imagenIconoGenero;
    }

    /**
     * Gets the usuario alta.
     *
     * @return the usuario alta
     */
    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    /**
     * Sets the usuario alta.
     *
     * @param usuarioAlta
     *            the new usuario alta
     */
    public void setUsuarioAlta(final String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * Gets the fecha alta.
     *
     * @return the fecha alta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Sets the fecha alta.
     *
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the usuario modificacion.
     *
     * @return the usuario modificacion
     */
    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    /**
     * Sets the usuario modificacion.
     *
     * @param usuarioModificacion
     *            the new usuario modificacion
     */
    public void setUsuarioModificacion(final String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Gets the fecha modificacion.
     *
     * @return the fecha modificacion
     */
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    /**
     * Sets the fecha modificacion.
     *
     * @param fechaModificacion
     *            the new fecha modificacion
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Gets the usuario baja.
     *
     * @return the usuario baja
     */
    public String getUsuarioBaja() {
        return this.usuarioBaja;
    }

    /**
     * Sets the usuario baja.
     *
     * @param usuarioBaja
     *            the new usuario baja
     */
    public void setUsuarioBaja(final String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Gets the fecha baja.
     *
     * @return the fecha baja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Sets the fecha baja.
     *
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GeneroEntity)) {
            return false;
        }
        final GeneroEntity castOther = (GeneroEntity) other;
        return new EqualsBuilder().append(this.genero_id, castOther.genero_id).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.genero_id).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("genero_id", this.genero_id).append("nombre", this.nombre).toString();
    }

}