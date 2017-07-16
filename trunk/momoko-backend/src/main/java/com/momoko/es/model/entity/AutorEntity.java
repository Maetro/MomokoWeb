/**
 * AutorEntity.java 08-jul-2017
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

/**
 * The Class AutorEntity.
 */
@Entity
@Table(name = "autor")
public class AutorEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer autorId;

    /** The nombre. */
    private String nombre;

    /** The libros autor. */
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private Set<LibroEntity> librosAutor;

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
     * Gets the libros autor.
     *
     * @return the libros autor
     */
    public Set<LibroEntity> getLibrosAutor() {
        return this.librosAutor;
    }

    /**
     * Sets the libros autor.
     *
     * @param librosAutor
     *            the new libros autor
     */
    public void setLibrosAutor(final Set<LibroEntity> librosAutor) {
        this.librosAutor = librosAutor;
    }

}
