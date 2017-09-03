/**
 * AutorEntity.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;
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
@Table(name = "autor")
public class AutorEntity {

    /** The autor id. */
    private @Id @GeneratedValue Integer autorId;

    /** The nombre. */
    private String nombre;

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
        if (!(other instanceof AutorEntity)) {
            return false;
        }
        final AutorEntity castOther = (AutorEntity) other;
        return new EqualsBuilder().append(this.autorId, castOther.autorId).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.autorId).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("autorId", this.autorId).append("nombre", this.nombre).toString();
    }

}
