/**
 * GeneroEntity.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.entity;

import java.util.Set;

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
    private @Id @GeneratedValue Integer generoId;

    /** The nombre. */
    private String nombre;

    /** The libros autor. */
    @ManyToMany(mappedBy = "generos", fetch = FetchType.LAZY)
    private Set<LibroEntity> librosGenero;

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

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GeneroEntity)) {
            return false;
        }
        final GeneroEntity castOther = (GeneroEntity) other;
        return new EqualsBuilder().append(this.generoId, castOther.generoId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.generoId).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("generoId", this.generoId).append("nombre", this.nombre).toString();
    }

}