/**
 * GeneroEntity.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class AutorEntity.
 */
@Entity
@Table(name = "genero")
public class GeneroEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer genero_id;

    /** The nombre. */
    private String nombre;

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