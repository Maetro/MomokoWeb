/**
 * GuardarGeneroResponse.java 26-sep-2017
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

import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionGenero;

public class GuardarGeneroResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6138141949532723854L;

    /** The lista errores validacion. */
    List<ErrorCreacionGenero> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    GeneroDTO generoDTO;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionGenero> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionGenero> listaErroresValidacion) {
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
     * Gets the genero dto.
     *
     * @return the genero dto
     */
    public GeneroDTO getGeneroDTO() {
        return this.generoDTO;
    }

    /**
     * Sets the genero dto.
     *
     * @param generoDTO
     *            the new genero dto
     */
    public void setGeneroDTO(final GeneroDTO generoDTO) {
        this.generoDTO = generoDTO;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GuardarGeneroResponse)) {
            return false;
        }
        final GuardarGeneroResponse castOther = (GuardarGeneroResponse) other;
        return new EqualsBuilder().append(this.listaErroresValidacion, castOther.listaErroresValidacion)
                .append(this.estadoGuardado, castOther.estadoGuardado).append(this.generoDTO, castOther.generoDTO)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.listaErroresValidacion).append(this.estadoGuardado)
                .append(this.generoDTO).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listaErroresValidacion", this.listaErroresValidacion)
                .append("estadoGuardado", this.estadoGuardado).append("generoDTO", this.generoDTO).toString();
    }
}
