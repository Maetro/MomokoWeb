/**
 * VideoEntity.java 13-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.video;

import com.momoko.es.jpa.entry.entity.EntradaEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * The Class VideoEntity.
 */
@Entity
@Table(name = "video")
public class VideoEntity {

    private @Id Integer videoId;

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;

    /** The url video. */
    private String urlVideo;

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

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private EntradaEntity entrada;

    /**
     * Instantiates a new video entity.
     */
    public VideoEntity() {
    }

    /**
     * Gets the video id.
     *
     * @return the video id
     */
    public Integer getVideoId() {
        return this.videoId;
    }

    /**
     * Sets the video id.
     *
     * @param videoId
     *            the new video id
     */
    public void setVideoId(final Integer videoId) {
        this.videoId = videoId;
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
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the url video.
     *
     * @return the url video
     */
    public String getUrlVideo() {
        return this.urlVideo;
    }

    /**
     * Sets the url video.
     *
     * @param urlVideo
     *            the new url video
     */
    public void setUrlVideo(final String urlVideo) {
        this.urlVideo = urlVideo;
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
     * Gets the entrada.
     *
     * @return the entrada
     */
    public EntradaEntity getEntrada() {
        return this.entrada;
    }

    /**
     * Sets the entrada.
     *
     * @param entrada
     *            the new entrada
     */
    public void setEntrada(final EntradaEntity entrada) {
        this.entrada = entrada;
    }

}
