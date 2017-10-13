/**
 * SagaEntity.java 12-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class SagaEntity.
 *
 * @author <a href="DireccionCorreoUsuario@Empresa">Nombre del Autor</a>
 */
@Entity
@Table(name = "saga")
public class SagaEntity extends AuditoriaBasica {

    /** The libro id. */
    private @Id @GeneratedValue Integer sagaId;

    /** The descripcion saga. */
    private String descripcionSaga;

    /** The titulo saga. */
    private String tituloSaga;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saga")
    private List<LibroEntity> libros;

    /**
     * Obtiene saga id.
     *
     * @return saga id
     */
    public Integer getSagaId() {
        return this.sagaId;
    }

    /**
     * Establece saga id.
     *
     * @param sagaId
     *            nuevo saga id
     */
    public void setSagaId(final Integer sagaId) {
        this.sagaId = sagaId;
    }

    /**
     * Obtiene descripcion saga.
     *
     * @return descripcion saga
     */
    public String getDescripcionSaga() {
        return this.descripcionSaga;
    }

    /**
     * Establece descripcion saga.
     *
     * @param descripcionSaga
     *            nuevo descripcion saga
     */
    public void setDescripcionSaga(final String descripcionSaga) {
        this.descripcionSaga = descripcionSaga;
    }

    /**
     * Gets the titulo saga.
     *
     * @return the titulo saga
     */
    public String getTituloSaga() {
        return this.tituloSaga;
    }

    /**
     * Sets the titulo saga.
     *
     * @param tituloSaga
     *            the new titulo saga
     */
    public void setTituloSaga(final String tituloSaga) {
        this.tituloSaga = tituloSaga;
    }

    /**
     * Gets the libros.
     *
     * @return the libros
     */
    public List<LibroEntity> getLibros() {
        return this.libros;
    }

    /**
     * Sets the libros.
     *
     * @param libros
     *            the new libros
     */
    public void setLibros(final List<LibroEntity> libros) {
        this.libros = libros;
    }

}
