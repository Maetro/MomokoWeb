
package com.momoko.es.api.dto.response;

import com.momoko.es.api.entry.dto.EntradaDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionEntrada;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class GuardarEntradaResponse {

    /** The lista errores validacion. */
    List<ErrorCreacionEntrada> listaErroresValidacion;

    /** The estado guardado. */
    EstadoGuardadoEnum estadoGuardado;

    /** The entrada DTO. */
    EntradaDTO entradaDTO;

    /**
     * Gets the lista errores validacion.
     *
     * @return the lista errores validacion
     */
    public List<ErrorCreacionEntrada> getListaErroresValidacion() {
        return this.listaErroresValidacion;
    }

    /**
     * Sets the lista errores validacion.
     *
     * @param listaErroresValidacion
     *            the new lista errores validacion
     */
    public void setListaErroresValidacion(final List<ErrorCreacionEntrada> listaErroresValidacion) {
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
     * Obtiene entrada DTO.
     *
     * @return entrada DTO
     */
    public EntradaDTO getEntradaDTO() {
        return entradaDTO;
    }

    /**
     * Establece entrada DTO.
     *
     * @param entradaDTO
     *            nuevo entrada DTO
     */
    public void setEntradaDTO(EntradaDTO entradaDTO) {
        this.entradaDTO = entradaDTO;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GuardarEntradaResponse)) {
            return false;
        }
        final GuardarEntradaResponse castOther = (GuardarEntradaResponse) other;
        return new EqualsBuilder().append(this.listaErroresValidacion, castOther.listaErroresValidacion)
                .append(this.estadoGuardado, castOther.estadoGuardado).append(this.entradaDTO, castOther.entradaDTO)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.listaErroresValidacion).append(this.estadoGuardado)
                .append(this.entradaDTO).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listaErroresValidacion", this.listaErroresValidacion)
                .append("estadoGuardado", this.estadoGuardado).append("entradaDTO", this.entradaDTO).toString();
    }

}
