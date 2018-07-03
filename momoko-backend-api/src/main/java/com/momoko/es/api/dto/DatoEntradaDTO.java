/**
 * DatoEntradaDTO.java 03-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.api.enums.TipoEntrada;

/**
 * The Class DatoEntradaDTO.
 */
public class DatoEntradaDTO implements Serializable {

    private static final long serialVersionUID = 1771931410522575737L;
    private Integer tipoEntrada;
    private String urlEntrada;
    private Boolean enMenu;
    private String nombreMenuLibro;
    private String urlMenuLibro;

    /**
     * Gets the tipo entrada.
     *
     * @return the tipo entrada
     */
    public Integer getTipoEntrada() {
        return this.tipoEntrada;
    }

    /**
     * Sets the tipo entrada.
     *
     * @param tipoEntrada
     *            the new tipo entrada
     */
    public void setTipoEntrada(final Integer tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
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
     * Gets the en menu.
     *
     * @return the en menu
     */
    public Boolean getEnMenu() {
        return this.enMenu;
    }

    /**
     * Sets the en menu.
     *
     * @param enMenu
     *            the new en menu
     */
    public void setEnMenu(final Boolean enMenu) {
        this.enMenu = enMenu;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tipoEntrada", TipoEntrada.obtenerTipoEntrada(this.tipoEntrada).getNombre())
                .append("urlEntrada", this.urlEntrada).append("enMenu", this.enMenu)
                .append("nombreMenuLibro", this.nombreMenuLibro).append("urlMenuLibro", this.urlMenuLibro).toString();
    }

}
