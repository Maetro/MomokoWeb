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
    private String resumen;
    private String fraseDescriptiva;
    private String tipoEntrada;
    private String categoria;

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

}
