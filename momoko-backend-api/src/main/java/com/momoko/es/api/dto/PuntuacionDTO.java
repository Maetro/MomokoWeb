/**
 * PuntuacionDTO.java 25-oct-2017
 *
 */
package com.momoko.es.api.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class PuntuacionDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class PuntuacionDTO {

    /** The puntuacion id. */
    private Integer puntuacionId;

    /** The valor. */
    private BigDecimal valor;

    /** The autor. */
    private UsuarioBasicoDTO autor;

    /** The comentario. */
    private String comentario;

    /** The libro id. */
    private Integer libroId;

    /** The libro id. */
    private Integer sagaId;

    /** The es puntuacion momoko. */
    private boolean esPuntuacionMomoko;

    /**
     * Obtiene puntuacion id.
     *
     * @return puntuacion id
     */
    public Integer getPuntuacionId() {
        return this.puntuacionId;
    }

    /**
     * Establece puntuacion id.
     *
     * @param puntuacionId
     *            nuevo puntuacion id
     */
    public void setPuntuacionId(final Integer puntuacionId) {
        this.puntuacionId = puntuacionId;
    }

    /**
     * Obtiene valor.
     *
     * @return valor
     */
    public BigDecimal getValor() {
        return this.valor;
    }

    /**
     * Establece valor.
     *
     * @param valor
     *            nuevo valor
     */
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * Obtiene autor.
     *
     * @return autor
     */
    public UsuarioBasicoDTO getAutor() {
        return this.autor;
    }

    /**
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setAutor(final UsuarioBasicoDTO autor) {
        this.autor = autor;
    }

    /**
     * Obtiene comentario.
     *
     * @return comentario
     */
    public String getComentario() {
        return this.comentario;
    }

    /**
     * Establece comentario.
     *
     * @param comentario
     *            nuevo comentario
     */
    public void setComentario(final String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene libro id.
     *
     * @return libro id
     */
    public Integer getLibroId() {
        return this.libroId;
    }

    /**
     * Establece libro id.
     *
     * @param libroId
     *            nuevo libro id
     */
    public void setLibroId(final Integer libroId) {
        this.libroId = libroId;
    }

    /**
     * Chequea si es puntuacion momoko.
     *
     * @return true, si es puntuacion momoko
     */
    public boolean isEsPuntuacionMomoko() {
        return this.esPuntuacionMomoko;
    }

    /**
     * Establece es puntuacion momoko.
     *
     * @param esPuntuacionMomoko
     *            nuevo es puntuacion momoko
     */
    public void setEsPuntuacionMomoko(final boolean esPuntuacionMomoko) {
        this.esPuntuacionMomoko = esPuntuacionMomoko;
    }

    /**
     * Gets the saga id.
     *
     * @return the saga id
     */
    public Integer getSagaId() {
        return this.sagaId;
    }

    /**
     * Sets the saga id.
     *
     * @param sagaId
     *            the new saga id
     */
    public void setSagaId(final Integer sagaId) {
        this.sagaId = sagaId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PuntuacionDTO)) {
            return false;
        }
        final PuntuacionDTO castOther = (PuntuacionDTO) other;
        return new EqualsBuilder().append(this.puntuacionId, castOther.puntuacionId).append(this.valor, castOther.valor)
                .append(this.autor, castOther.autor).append(this.comentario, castOther.comentario)
                .append(this.libroId, castOther.libroId).append(this.sagaId, castOther.sagaId)
                .append(this.esPuntuacionMomoko, castOther.esPuntuacionMomoko).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.puntuacionId).append(this.valor).append(this.autor)
                .append(this.comentario).append(this.libroId).append(this.sagaId).append(this.esPuntuacionMomoko)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("puntuacionId", this.puntuacionId).append("valor", this.valor)
                .append("autor", this.autor).append("comentario", this.comentario).append("libroId", this.libroId)
                .append("sagaId", this.sagaId).append("esPuntuacionMomoko", this.esPuntuacionMomoko).toString();
    }

}
