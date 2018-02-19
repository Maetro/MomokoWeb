/**
 * EntradaSimpleDTO.java 28-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class EntradaSimpleDTO.
 */
public class EntradaSimpleDTO implements Serializable {

    private static final long serialVersionUID = 8549260722564138380L;
    private String tituloEntrada;
    private String imagenEntrada;
    private String nombreAutor;
    private String urlEntrada;
    private Integer numeroComentarios;
    private Date fechaAlta;
    private Date fechaModificacion;
    private String resumen;
    private String fraseDescriptiva;
    private String tipoEntrada;
    private String categoria;
    private String videoUrl;
    private String titulosLibros;
    private String nombreMenuLibro;
    private String urlMenuLibro;
    private String urlLibro;
    private Integer visitas;
    private String bloque;

    /**
     * Gets the titulo entrada.
     *
     * @return the titulo entrada
     */
    public String getTituloEntrada() {
        return this.tituloEntrada;
    }

    /**
     * Sets the titulo entrada.
     *
     * @param tituloEntrada
     *            the new titulo entrada
     */
    public void setTituloEntrada(final String tituloEntrada) {
        this.tituloEntrada = tituloEntrada;
    }

    /**
     * Gets the imagen entrada.
     *
     * @return the imagen entrada
     */
    public String getImagenEntrada() {
        return this.imagenEntrada;
    }

    /**
     * Sets the imagen entrada.
     *
     * @param imagenEntrada
     *            the new imagen entrada
     */
    public void setImagenEntrada(final String imagenEntrada) {
        this.imagenEntrada = imagenEntrada;
    }

    /**
     * Gets the nombre autor.
     *
     * @return the nombre autor
     */
    public String getNombreAutor() {
        return this.nombreAutor;
    }

    /**
     * Sets the nombre autor.
     *
     * @param nombreAutor
     *            the new nombre autor
     */
    public void setNombreAutor(final String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    /**
     * Gets the url entrada.
     *
     * @return the url entrada
     */
    public String getUrlEntrada() {
        return this.urlEntrada;
    }

    /**
     * Sets the url entrada.
     *
     * @param urlEntrada
     *            the new url entrada
     */
    public void setUrlEntrada(final String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    /**
     * Gets the numero comentarios.
     *
     * @return the numero comentarios
     */
    public Integer getNumeroComentarios() {
        return this.numeroComentarios;
    }

    /**
     * Sets the numero comentarios.
     *
     * @param numeroComentarios
     *            the new numero comentarios
     */
    public void setNumeroComentarios(final Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    /**
     * Gets the categoria.
     *
     * @return the categoria
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the categoria.
     *
     * @param categoria
     *            the new categoria
     */
    public void setCategoria(final String categoria) {
        this.categoria = categoria;
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
     * Gets the frase descriptiva.
     *
     * @return the frase descriptiva
     */
    public String getFraseDescriptiva() {
        return this.fraseDescriptiva;
    }

    /**
     * Sets the frase descriptiva.
     *
     * @param fraseDescriptiva
     *            the new frase descriptiva
     */
    public void setFraseDescriptiva(final String fraseDescriptiva) {
        this.fraseDescriptiva = fraseDescriptiva;
    }

    /**
     * Gets the tipo entrada.
     *
     * @return the tipo entrada
     */
    public String getTipoEntrada() {
        return this.tipoEntrada;
    }

    /**
     * Sets the tipo entrada.
     *
     * @param tipoEntrada
     *            the new tipo entrada
     */
    public void setTipoEntrada(final String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    /**
     * Gets the video url.
     *
     * @return the video url
     */
    public String getVideoUrl() {
        return this.videoUrl;
    }

    /**
     * Sets the url video.
     *
     * @param videoUrl
     *            the new url video
     */
    public void setUrlVideo(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Gets the titulos libros.
     *
     * @return the titulos libros
     */
    public String getTitulosLibros() {
        return this.titulosLibros;
    }

    /**
     * Gets the nombre menu libro.
     *
     * @return the nombre menu libro
     */
    public String getNombreMenuLibro() {
        return this.nombreMenuLibro;
    }

    /**
     * Sets the nombre menu libro.
     *
     * @param nombreMenuLibro
     *            the new nombre menu libro
     */
    public void setNombreMenuLibro(final String nombreMenuLibro) {
        this.nombreMenuLibro = nombreMenuLibro;
    }

    /**
     * Sets the titulos libros.
     *
     * @param titulosLibros
     *            the new titulos libros
     */
    public void setTitulosLibros(final String titulosLibros) {
        this.titulosLibros = titulosLibros;
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
     * Gets the visitas.
     *
     * @return the visitas
     */
    public Integer getVisitas() {
        return this.visitas;
    }

    /**
     * Sets the visitas.
     *
     * @param visitas
     *            the new visitas
     */
    public void setVisitas(final Integer visitas) {
        this.visitas = visitas;
    }

    /**
     * Sets the video url.
     *
     * @param videoUrl
     *            the new video url
     */
    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Gets the url menu libro.
     *
     * @return the url menu libro
     */
    public String getUrlMenuLibro() {
        return this.urlMenuLibro;
    }

    /**
     * Sets the url menu libro.
     *
     * @param urlMenuLibro
     *            the new url menu libro
     */
    public void setUrlMenuLibro(final String urlMenuLibro) {
        this.urlMenuLibro = urlMenuLibro;
    }

    /**
     * Gets the url libro.
     *
     * @return the url libro
     */
    public String getUrlLibro() {
        return this.urlLibro;
    }

    /**
     * Sets the url libro.
     *
     * @param urlLibro
     *            the new url libro
     */
    public void setUrlLibro(final String urlLibro) {
        this.urlLibro = urlLibro;
    }

    /**
     * Gets the bloque.
     *
     * @return the bloque
     */
    public String getBloque() {
        return this.bloque;
    }

    /**
     * Sets the bloque.
     *
     * @param bloque
     *            the new bloque
     */
    public void setBloque(final String bloque) {
        this.bloque = bloque;
    }

}
