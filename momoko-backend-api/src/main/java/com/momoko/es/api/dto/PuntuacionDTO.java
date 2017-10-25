/**
 * PuntuacionDTO.java 25-oct-2017
 *
 */
package com.momoko.es.api.dto;

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
    private Integer valor; // Del 0 al 100

    /** The autor. */
    private UsuarioBasicoDTO autor;

    /** The comentario. */
    private String comentario;

    /** The libro id. */
    private Integer libroId;

    /** The es puntuacion momoko. */
    private boolean esPuntuacionMomoko;

    /**
     * Obtiene puntuacion id.
     *
     * @return puntuacion id
     */
    public Integer getPuntuacionId() {
        return puntuacionId;
    }

    /**
     * Establece puntuacion id.
     *
     * @param puntuacionId
     *            nuevo puntuacion id
     */
    public void setPuntuacionId(Integer puntuacionId) {
        this.puntuacionId = puntuacionId;
    }

    /**
     * Obtiene valor.
     *
     * @return valor
     */
    public Integer getValor() {
        return valor;
    }

    /**
     * Establece valor.
     *
     * @param valor
     *            nuevo valor
     */
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    /**
     * Obtiene autor.
     *
     * @return autor
     */
    public UsuarioBasicoDTO getAutor() {
        return autor;
    }

    /**
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setAutor(UsuarioBasicoDTO autor) {
        this.autor = autor;
    }

    /**
     * Obtiene comentario.
     *
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece comentario.
     *
     * @param comentario
     *            nuevo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene libro id.
     *
     * @return libro id
     */
    public Integer getLibroId() {
        return libroId;
    }

    /**
     * Establece libro id.
     *
     * @param libroId
     *            nuevo libro id
     */
    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

    /**
     * Chequea si es puntuacion momoko.
     *
     * @return true, si es puntuacion momoko
     */
    public boolean isEsPuntuacionMomoko() {
        return esPuntuacionMomoko;
    }

    /**
     * Establece es puntuacion momoko.
     *
     * @param esPuntuacionMomoko
     *            nuevo es puntuacion momoko
     */
    public void setEsPuntuacionMomoko(boolean esPuntuacionMomoko) {
        this.esPuntuacionMomoko = esPuntuacionMomoko;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PuntuacionDTO))
            return false;
        PuntuacionDTO castOther = (PuntuacionDTO) other;
        return new EqualsBuilder().append(puntuacionId, castOther.puntuacionId).append(valor, castOther.valor)
                .append(autor, castOther.autor).append(comentario, castOther.comentario)
                .append(libroId, castOther.libroId).append(esPuntuacionMomoko, castOther.esPuntuacionMomoko).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(puntuacionId).append(valor).append(autor).append(comentario).append(libroId)
                .append(esPuntuacionMomoko).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("puntuacionId", puntuacionId).append("valor", valor)
                .append("autor", autor).append("comentario", comentario).append("libroId", libroId)
                .append("esPuntuacionMomoko", esPuntuacionMomoko).toString();
    }

}
