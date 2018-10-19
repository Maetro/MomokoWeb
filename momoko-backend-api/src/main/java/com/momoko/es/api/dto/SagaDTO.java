/**
 * SagaDTO.java 25-feb-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.api.dto.genre.GenreDTO;

/**
 * The Class SagaDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class SagaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4725553152718656061L;

    /** The libro id. */
    private Integer sagaId;

    private String nombreSaga;

    private String urlSaga;

    private String imagenSaga;

    private Integer notaSaga;

    private List<String> urlsLibrosSaga;

    private String resumen;

    private Integer numeroVolumenes;

    private Boolean estaTerminada;

    private Boolean dominaLibros;

    private String tipoSaga;

    /** The libros string. */
    private String librosString;

    /** The entradas. */
    private List<DatoEntradaDTO> entradasSaga;

    /* SOLO PARA LECTURA - NO NECESARIOS PARA GUARDADO */

    /** The libros saga. */
    private List<LibroDTO> librosSaga;

    /** The puntuaciones saga. */
    private List<PuntuacionDTO> puntuacionesSaga;

    /** The genero id. */
    private Set<GenreDTO> generos;

    /** The autor id. */
    private Set<AuthorDTO> autores;

    /** The editorial. */
    private EditorialDTO editorial;

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
     * Gets the nombre saga.
     *
     * @return the nombre saga
     */
    public String getNombreSaga() {
        return this.nombreSaga;
    }

    /**
     * Sets the nombre saga.
     *
     * @param nombreSaga
     *            the new nombre saga
     */
    public void setNombreSaga(final String nombreSaga) {
        this.nombreSaga = nombreSaga;
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
     * Gets the nota saga.
     *
     * @return the nota saga
     */
    public Integer getNotaSaga() {
        return this.notaSaga;
    }

    /**
     * Sets the nota saga.
     *
     * @param notaSaga
     *            the new nota saga
     */
    public void setNotaSaga(final Integer notaSaga) {
        this.notaSaga = notaSaga;
    }

    /**
     * Gets the libros saga.
     *
     * @return the libros saga
     */
    public List<String> getLibrosSaga() {
        return this.urlsLibrosSaga;
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
     * Gets the urls libros saga.
     *
     * @return the urls libros saga
     */
    public List<String> getUrlsLibrosSaga() {
        return this.urlsLibrosSaga;
    }

    /**
     * Sets the urls libros saga.
     *
     * @param urlsLibrosSaga
     *            the new urls libros saga
     */
    public void setUrlsLibrosSaga(final List<String> urlsLibrosSaga) {
        this.urlsLibrosSaga = urlsLibrosSaga;
    }

    /**
     * Sets the libros saga.
     *
     * @param librosSaga
     *            the new libros saga
     */
    public void setLibrosSaga(final List<LibroDTO> librosSaga) {
        this.librosSaga = librosSaga;
    }

    /**
     * Gets the puntuaciones saga.
     *
     * @return the puntuaciones saga
     */
    public List<PuntuacionDTO> getPuntuacionesSaga() {
        return this.puntuacionesSaga;
    }

    /**
     * Sets the puntuaciones saga.
     *
     * @param puntuacionesSaga
     *            the new puntuaciones saga
     */
    public void setPuntuacionesSaga(final List<PuntuacionDTO> puntuacionesSaga) {
        this.puntuacionesSaga = puntuacionesSaga;
    }

    /**
     * Gets the libros string.
     *
     * @return the libros string
     */
    public String getLibrosString() {
        return this.librosString;
    }

    /**
     * Sets the libros string.
     *
     * @param librosString
     *            the new libros string
     */
    public void setLibrosString(final String librosString) {
        this.librosString = librosString;
    }

    public Boolean getDominaLibros() {
        return this.dominaLibros;
    }

    public void setDominaLibros(final Boolean dominaLibros) {
        this.dominaLibros = dominaLibros;
    }

    public List<DatoEntradaDTO> getEntradasSaga() {
        return this.entradasSaga;
    }

    public void setEntradasSaga(final List<DatoEntradaDTO> entradasSaga) {
        this.entradasSaga = entradasSaga;
    }

    public Set<AuthorDTO> getAutores() {
        return this.autores;
    }

    public void setAutores(final Set<AuthorDTO> autores) {
        this.autores = autores;
    }

    public Set<GenreDTO> getGeneros() {
        return this.generos;
    }

    public void setGeneros(final Set<GenreDTO> generos) {
        this.generos = generos;
    }

    public EditorialDTO getEditorial() {
        return this.editorial;
    }

    public void setEditorial(final EditorialDTO editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SagaDTO)) {
            return false;
        }
        final SagaDTO castOther = (SagaDTO) other;
        return new EqualsBuilder().append(this.sagaId, castOther.sagaId).append(this.nombreSaga, castOther.nombreSaga)
                .append(this.urlSaga, castOther.urlSaga).append(this.imagenSaga, castOther.imagenSaga)
                .append(this.notaSaga, castOther.notaSaga).append(this.urlsLibrosSaga, castOther.urlsLibrosSaga)
                .append(this.resumen, castOther.resumen).append(this.numeroVolumenes, castOther.numeroVolumenes)
                .append(this.estaTerminada, castOther.estaTerminada).append(this.dominaLibros, castOther.dominaLibros)
                .append(this.tipoSaga, castOther.tipoSaga).append(this.librosString, castOther.librosString)
                .append(this.librosSaga, castOther.librosSaga).append(this.puntuacionesSaga, castOther.puntuacionesSaga)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.sagaId).append(this.nombreSaga).append(this.urlSaga)
                .append(this.imagenSaga).append(this.notaSaga).append(this.urlsLibrosSaga).append(this.resumen)
                .append(this.numeroVolumenes).append(this.estaTerminada).append(this.dominaLibros).append(this.tipoSaga)
                .append(this.librosString).append(this.librosSaga).append(this.puntuacionesSaga).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sagaId", this.sagaId).append("nombreSaga", this.nombreSaga)
                .append("urlSaga", this.urlSaga).append("imagenSaga", this.imagenSaga).append("notaSaga", this.notaSaga)
                .append("urlsLibrosSaga", this.urlsLibrosSaga).append("resumen", this.resumen)
                .append("numeroVolumenes", this.numeroVolumenes).append("estaTerminada", this.estaTerminada)
                .append("dominaLibros", this.dominaLibros).append("tipoSaga", this.tipoSaga)
                .append("librosString", this.librosString).append("librosSaga", this.librosSaga)
                .append("puntuacionesSaga", this.puntuacionesSaga).toString();
    }

}
