/**
 * GaleriaEntity.java 24-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class GaleriaEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Entity
@Table(name = "galeria")
public class GaleriaEntity extends AuditoriaBasica {

    /** The galeria id. */
    private @Id @GeneratedValue Integer galeriaId;

    /** The fotografias. */
    private String fotografias;

    /** The columnas. */
    private Integer columnas;

    /** The entrada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrada_id")
    private EntradaEntity entrada;

    /**
     * Instancia un nuevo galeria entity.
     */
    public GaleriaEntity() {
    }

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
    public String getFotografias() {
        return fotografias;
    }

    /**
     * Establece fotografias.
     *
     * @param fotografias
     *            nuevo fotografias
     */
    public void setFotografias(String fotografias) {
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
    public EntradaEntity getEntrada() {
        return entrada;
    }

    /**
     * Establece entrada.
     *
     * @param entrada
     *            nuevo entrada
     */
    public void setEntrada(EntradaEntity entrada) {
        this.entrada = entrada;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GaleriaEntity))
            return false;
        GaleriaEntity castOther = (GaleriaEntity) other;
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
