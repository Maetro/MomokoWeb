/**
 * GaleriaDTO.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class GaleriaDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class GaleriaDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 226854924754776466L;

    /** The galeria id. */
    private Integer galeriaId;

    /** The fotografias. */
    private List<String> fotografias;

    /** The columnas. */
    private Integer columnas;

    /** The entrada. */
    private String entrada;

    /**
     * Obtiene galeria id.
     *
     * @return galeria id
     */
    public Integer getGaleriaId() {
        return galeriaId;
    }

    /**
     * Establece galeria id.
     *
     * @param galeriaId
     *            nuevo galeria id
     */
    public void setGaleriaId(Integer galeriaId) {
        this.galeriaId = galeriaId;
    }

    /**
     * Obtiene fotografias.
     *
     * @return fotografias
     */
    public List<String> getFotografias() {
        return fotografias;
    }

    /**
     * Establece fotografias.
     *
     * @param fotografias
     *            nuevo fotografias
     */
    public void setFotografias(List<String> fotografias) {
        this.fotografias = fotografias;
    }

    /**
     * Obtiene columnas.
     *
     * @return columnas
     */
    public Integer getColumnas() {
        return columnas;
    }

    /**
     * Establece columnas.
     *
     * @param columnas
     *            nuevo columnas
     */
    public void setColumnas(Integer columnas) {
        this.columnas = columnas;
    }

    /**
     * Obtiene entrada.
     *
     * @return entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * Establece entrada.
     *
     * @param entrada
     *            nuevo entrada
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GaleriaDTO))
            return false;
        GaleriaDTO castOther = (GaleriaDTO) other;
        return new EqualsBuilder().append(galeriaId, castOther.galeriaId).append(fotografias, castOther.fotografias)
                .append(columnas, castOther.columnas).append(entrada, castOther.entrada).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(galeriaId).append(fotografias).append(columnas).append(entrada)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("galeriaId", galeriaId).append("fotografias", fotografias)
                .append("columnas", columnas).append("entrada", entrada).toString();
    }

}
