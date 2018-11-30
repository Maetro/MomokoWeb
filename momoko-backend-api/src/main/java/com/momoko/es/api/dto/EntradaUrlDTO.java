/**
 * EntradaUrlDTO.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * The Class EntradaUrlDTO.
 */
public class EntradaUrlDTO implements Comparable<EntradaUrlDTO>, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5302194484010779415L;

    /** The entrada id. */
    Integer entradaId;

    /** The url completa. */
    String urlCompleta;

    /** The longitud. */
    Integer longitud;

    /** The url entrada. */
    String urlEntrada;

    /** The url entrada. */
    String urlAntigua;

    /**
     * The Met description.
     */
    String metaDescription;

    /**
     * The Title.
     */
    String title;

    /**
     * The Tipo url.
     */
    String tipoUrl;

    /**
     * Gets the entrada id.
     *
     * @return the entrada id
     */
    public Integer getEntradaId() {
        return this.entradaId;
    }

    /**
     * Sets the entrada id.
     *
     * @param entradaId
     *            the new entrada id
     */
    public void setEntradaId(final Integer entradaId) {
        this.entradaId = entradaId;
    }

    /**
     * Gets the url completa.
     *
     * @return the url completa
     */
    public String getUrlCompleta() {
        return this.urlCompleta;
    }

    /**
     * Sets the url completa.
     *
     * @param urlCompleta
     *            the new url completa
     */
    public void setUrlCompleta(final String urlCompleta) {
        this.urlCompleta = urlCompleta;
    }

    /**
     * Gets the longitud.
     *
     * @return the longitud
     */
    public Integer getLongitud() {
        return this.longitud;
    }

    /**
     * Sets the longitud.
     *
     * @param longitud
     *            the new longitud
     */
    public void setLongitud(final Integer longitud) {
        this.longitud = longitud;
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
     * Gets the url antigua.
     *
     * @return the url antigua
     */
    public String getUrlAntigua() {
        return this.urlAntigua;
    }

    /**
     * Sets the url antigua.
     *
     * @param urlAntigua
     *            the new url antigua
     */
    public void setUrlAntigua(final String urlAntigua) {
        this.urlAntigua = urlAntigua;
    }

    public String getMetaDescription() {
        return this.metaDescription;
    }

    public void setMetaDescription(final String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getTipoUrl() {
        return this.tipoUrl;
    }

    public void setTipoUrl(final String tipoUrl) {
        this.tipoUrl = tipoUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int compareTo(final EntradaUrlDTO other) {
        return new CompareToBuilder().append(this.longitud, other.longitud).toComparison();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaUrlDTO)) {
            return false;
        }
        final EntradaUrlDTO castOther = (EntradaUrlDTO) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId)
                .append(this.urlCompleta, castOther.urlCompleta).append(this.longitud, castOther.longitud)
                .append(this.urlEntrada, castOther.urlEntrada).append(this.urlAntigua, castOther.urlAntigua).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).append(this.urlCompleta).append(this.longitud)
                .append(this.urlEntrada).append(this.urlAntigua).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("urlCompleta", this.urlCompleta)
                .append("longitud", this.longitud).append("urlEntrada", this.urlEntrada)
                .append("urlAntigua", this.urlAntigua).toString();
    }

}
