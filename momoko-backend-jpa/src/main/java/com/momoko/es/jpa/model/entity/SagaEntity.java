/**
 * SagaEntity.java 12-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class SagaEntity.
 */
@Entity
@Table(name = "saga")
public class SagaEntity {

    /** The libro id. */
    private @Id @GeneratedValue Integer sagaId;

    /** The descripcion saga. */
    private String nombre;

    /** The url saga. */
    private String urlSaga;

    /** The imagen saga. */
    private String imagenSaga;

    @OneToMany(mappedBy = "saga", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PuntuacionEntity> puntuaciones = new ArrayList<PuntuacionEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "saga")
    private List<LibroEntity> libros;

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

    /** The resumen. */
    private String resumen;

    /** The numero volumenes. */
    private Integer numeroVolumenes;

    /** The esta terminada. */
    private Boolean estaTerminada;

    /** The tipo saga. */
    private String tipoSaga;

    /** The domina libros. */
    private Boolean dominaLibros;

    private Integer score;

    @ManyToMany(mappedBy = "sagasEntrada")
    private List<EntradaEntity> entradas;

    /**
     * Anade el libro.
     *
     * @param libro
     *            the libro
     */
    public void addLibro(final LibroEntity libro) {
        if (libro != null) {
            if (this.libros == null) {
                this.libros = new ArrayList<LibroEntity>();
            }
            this.libros.add(libro);
            libro.setSaga(this);
        }
    }

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
     * Gets the nombre saga.
     *
     * @return the nombre saga
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre saga.
     *
     * @param nombreSaga
     *            the new nombre saga
     */
    public void setNombre(final String nombreSaga) {
        this.nombre = nombreSaga;
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

    /**
     * Gets the url saga.
     *
     * @return the url saga
     */
    public String getUrlSaga() {
        return this.urlSaga;
    }

    /**
     * Sets the url saga.
     *
     * @param urlSaga
     *            the new url saga
     */
    public void setUrlSaga(final String urlSaga) {
        this.urlSaga = urlSaga;
    }

    /**
     * Gets the imagen saga.
     *
     * @return the imagen saga
     */
    public String getImagenSaga() {
        return this.imagenSaga;
    }

    /**
     * Sets the imagen saga.
     *
     * @param imagenSaga
     *            the new imagen saga
     */
    public void setImagenSaga(final String imagenSaga) {
        this.imagenSaga = imagenSaga;
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
     * Gets the numero volumenes.
     *
     * @return the numero volumenes
     */
    public Integer getNumeroVolumenes() {
        return this.numeroVolumenes;
    }

    /**
     * Sets the numero volumenes.
     *
     * @param numeroVolumenes
     *            the new numero volumenes
     */
    public void setNumeroVolumenes(final Integer numeroVolumenes) {
        this.numeroVolumenes = numeroVolumenes;
    }

    /**
     * Gets the esta terminada.
     *
     * @return the esta terminada
     */
    public Boolean getEstaTerminada() {
        return this.estaTerminada;
    }

    /**
     * Sets the esta terminada.
     *
     * @param estaTerminada
     *            the new esta terminada
     */
    public void setEstaTerminada(final Boolean estaTerminada) {
        this.estaTerminada = estaTerminada;
    }

    /**
     * Gets the tipo saga.
     *
     * @return the tipo saga
     */
    public String getTipoSaga() {
        return this.tipoSaga;
    }

    /**
     * Sets the tipo saga.
     *
     * @param tipoSaga
     *            the new tipo saga
     */
    public void setTipoSaga(final String tipoSaga) {
        this.tipoSaga = tipoSaga;
    }

    /**
     * Gets the puntuaciones.
     *
     * @return the puntuaciones
     */
    public List<PuntuacionEntity> getPuntuaciones() {
        return this.puntuaciones;
    }

    /**
     * Gets the domina libros.
     *
     * @return the domina libros
     */
    public Boolean getDominaLibros() {
        return this.dominaLibros;
    }

    /**
     * Sets the domina libros.
     *
     * @param dominaLibros
     *            the new domina libros
     */
    public void setDominaLibros(final Boolean dominaLibros) {
        this.dominaLibros = dominaLibros;
    }

    public List<EntradaEntity> getEntradas() {
		return entradas;
	}
    
    public void setEntradas(List<EntradaEntity> entradas) {
		this.entradas = entradas;
	}

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
