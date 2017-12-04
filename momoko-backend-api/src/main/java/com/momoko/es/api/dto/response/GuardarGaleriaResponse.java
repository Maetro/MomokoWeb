/**
 * GuardarGaleriaResponse.java 25-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.api.dto.GaleriaDTO;
import com.momoko.es.api.enums.ErrorCreacionGaleria;
import com.momoko.es.api.enums.EstadoGuardadoEnum;

/**
 * The Class GuardarGaleriaResponse.
 */
public class GuardarGaleriaResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6138141949532723854L;

    /** The lista errores validacion. */
    List<ErrorCreacionGaleria> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    GaleriaDTO galeria;

    /** The mensaje error. */
    List<String> mensajeError;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionGaleria> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionGaleria> listaErroresValidacion) {
        this.listaErroresValidacion = listaErroresValidacion;
    }

    /**
     * Gets the estado guardado.
     *
     * @return the estado guardado
     */
    public EstadoGuardadoEnum getEstadoGuardado() {
        return this.estadoGuardado;
    }

    /**
     * Sets the estado guardado.
     *
     * @param estadoGuardado
     *            the new estado guardado
     */
    public void setEstadoGuardado(final EstadoGuardadoEnum estadoGuardado) {
        this.estadoGuardado = estadoGuardado;
    }

    /**
     * Gets the galeria.
     *
     * @return the galeria
     */
    public GaleriaDTO getGaleria() {
        return this.galeria;
    }

    /**
     * Sets the galeria.
     *
     * @param galeria
     *            the new galeria
     */
    public void setGaleria(final GaleriaDTO galeria) {
        this.galeria = galeria;
    }

    /**
     * Gets the mensaje error.
     *
     * @return the mensaje error
     */
    public List<String> getMensajeError() {
        return this.mensajeError;
    }

    /**
     * Sets the mensaje error.
     *
     * @param mensajeError
     *            the new mensaje error
     */
    public void setMensajeError(final List<String> mensajeError) {
        this.mensajeError = mensajeError;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GuardarGaleriaResponse)) {
            return false;
        }
        final GuardarGaleriaResponse castOther = (GuardarGaleriaResponse) other;
        return new EqualsBuilder().append(this.listaErroresValidacion, castOther.listaErroresValidacion)
                .append(this.estadoGuardado, castOther.estadoGuardado).append(this.galeria, castOther.galeria)
                .isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.listaErroresValidacion).append(this.estadoGuardado)
                .append(this.galeria).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listaErroresValidacion", this.listaErroresValidacion)
                .append("estadoGuardado", this.estadoGuardado).append("generoDTO", this.galeria).toString();
    }

}
