/**
 * RegistrarUsuarioRespoonse.java 19-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.ErrorCreacionUsuario;
import com.momoko.es.api.enums.EstadoGuardadoEnum;

/**
 * The Class RegistrarUsuarioRespoonse.
 */
public class RegistrarUsuarioRespoonse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6138141949532723854L;

    /** The lista errores validacion. */
    List<ErrorCreacionUsuario> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The libro dto. */
    UsuarioDTO usuarioDTO;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionUsuario> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionUsuario> listaErroresValidacion) {
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
     * Gets the usuario dto.
     *
     * @return the usuario dto
     */
    public UsuarioDTO getUsuarioDTO() {
        return this.usuarioDTO;
    }

    /**
     * Sets the usuario dto.
     *
     * @param usuarioDTO
     *            the new usuario dto
     */
    public void setUsuarioDTO(final UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GuardarLibroResponse)) {
            return false;
        }
        final RegistrarUsuarioRespoonse castOther = (RegistrarUsuarioRespoonse) other;
        return new EqualsBuilder().append(this.listaErroresValidacion, castOther.listaErroresValidacion)
                .append(this.estadoGuardado, castOther.estadoGuardado).append(this.usuarioDTO, castOther.usuarioDTO)
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
                .append(this.usuarioDTO).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listaErroresValidacion", this.listaErroresValidacion)
                .append("estadoGuardado", this.estadoGuardado).append("usuarioDTO", this.usuarioDTO).toString();
    }
}
