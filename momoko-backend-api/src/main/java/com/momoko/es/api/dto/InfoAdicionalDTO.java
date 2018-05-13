/**
 * InfoAdicionalDTO.java 12-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class InfoAdicionalDTO.
 */
public class InfoAdicionalDTO implements Serializable {

    private static final long serialVersionUID = 788689583391078350L;
    private String key;
    private String valor;

    public InfoAdicionalDTO() {
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the new key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets the valor.
     *
     * @return the valor
     */
    public String getValor() {
        return this.valor;
    }

    /**
     * Sets the valor.
     *
     * @param valor
     *            the new valor
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof InfoAdicionalDTO)) {
            return false;
        }
        final InfoAdicionalDTO castOther = (InfoAdicionalDTO) other;
        return new EqualsBuilder().append(this.key, castOther.key).append(this.valor, castOther.valor).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.key).append(this.valor).toHashCode();
    }

}
