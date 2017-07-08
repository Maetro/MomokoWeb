/**
 * LibroDTO.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

public class LibroDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8006086179924231655L;

    /** The libro id. */
    private Integer libroId;

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

    public Integer getLibroId() {
        return this.libroId;
    }

    public void setLibroId(final Integer libroId) {
        this.libroId = libroId;
    }

    public Integer getAutorId() {
        return this.autorId;
    }

    public void setAutorId(final Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getSagaId() {
        return this.sagaId;
    }

    public void setSagaId(final Integer sagaId) {
        this.sagaId = sagaId;
    }

    public Integer getEditorialId() {
        return this.editorialId;
    }

    public void setEditorialId(final Integer editorialId) {
        this.editorialId = editorialId;
    }

    public Integer getGeneroId() {
        return this.generoId;
    }

    public void setGeneroId(final Integer generoId) {
        this.generoId = generoId;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoEdicion() {
        return this.anoEdicion;
    }

    public void setAnoEdicion(final Integer anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    public String getCitaLibro() {
        return this.citaLibro;
    }

    public void setCitaLibro(final String citaLibro) {
        this.citaLibro = citaLibro;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    public String getEnlaceAmazon() {
        return this.enlaceAmazon;
    }

    public void setEnlaceAmazon(final String enlaceAmazon) {
        this.enlaceAmazon = enlaceAmazon;
    }

    public String getUrlImagen() {
        return this.urlImagen;
    }

    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
