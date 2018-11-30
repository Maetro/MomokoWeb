/**
 * GuardarLibroResponse.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionLibro;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The Class GuardarLibroResponse.
 */
public class GuardarLibroResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6138141949532723854L;

    /** The lista errores validacion. */
    List<ErrorCreacionLibro> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    LibroDTO libroDTO;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionLibro> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionLibro> listaErroresValidacion) {
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
     * Gets the libro dto.
     *
     * @return the libro dto
     */
    public LibroDTO getLibroDTO() {
        return this.libroDTO;
    }

    /**
     * Sets the libro dto.
     *
     * @param libroDTO
     *            the new libro dto
     */
    public void setLibroDTO(final LibroDTO libroDTO) {
        this.libroDTO = libroDTO;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GuardarLibroResponse)) {
            return false;
        }
        final GuardarLibroResponse castOther = (GuardarLibroResponse) other;
        return new EqualsBuilder().append(this.listaErroresValidacion, castOther.listaErroresValidacion)
                .append(this.estadoGuardado, castOther.estadoGuardado).append(this.libroDTO, castOther.libroDTO)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.listaErroresValidacion).append(this.estadoGuardado)
                .append(this.libroDTO).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listaErroresValidacion", this.listaErroresValidacion)
                .append("estadoGuardado", this.estadoGuardado).append("libroDTO", this.libroDTO).toString();
    }

}
