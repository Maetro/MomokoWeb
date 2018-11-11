/**
 * DatoEntradaDTO.java 03-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.api.enums.EntryTypeEnum;

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
                .append("tipoEntrada", EntryTypeEnum.getEntryType(this.tipoEntrada).getName())
                .append("urlEntrada", this.urlEntrada).append("enMenu", this.enMenu)
                .append("nombreMenuLibro", this.nombreMenuLibro).append("urlMenuLibro", this.urlMenuLibro).toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof DatoEntradaDTO)) {
            return false;
        }
        final DatoEntradaDTO castOther = (DatoEntradaDTO) other;
        return new EqualsBuilder().append(this.tipoEntrada, castOther.tipoEntrada)
                .append(this.urlEntrada, castOther.urlEntrada).append(this.enMenu, castOther.enMenu)
                .append(this.nombreMenuLibro, castOther.nombreMenuLibro)
                .append(this.urlMenuLibro, castOther.urlMenuLibro).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.tipoEntrada).append(this.urlEntrada).append(this.enMenu)
                .append(this.nombreMenuLibro).append(this.urlMenuLibro).toHashCode();
    }

}
