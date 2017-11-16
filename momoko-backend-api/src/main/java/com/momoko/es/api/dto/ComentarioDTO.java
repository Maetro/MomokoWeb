/**
 * ComentarioDTO.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class ComentarioDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class ComentarioDTO implements Serializable {

    private static final long serialVersionUID = -3012735456984544175L;

    /** The comentario id. */
    private Integer comentarioId;

    /** The autor. */
    private UsuarioBasicoDTO autor;

    /** The fecha. */
    private Date fecha;

    /** The votos positivos. */
    private Integer votosPositivos;

    /** The votos negativos. */
    private Integer votosNegativos;

    /** The texto comentario. */
    private String textoComentario;

    /** The entrada id. */
    private Integer entradaId;

    /** The comentario referencia. */
    private List<ComentarioDTO> comentariosHijo;

    /** The comentario padre id. */
    private Integer comentarioPadreId;

    /** The es spoiler. */
    private boolean esSpoiler;

    /** The es ban. */
    private boolean esBan;

    /**
     * Obtiene comentario id.
     *
     * @return comentario id
     */
    public Integer getComentarioId() {
        return this.comentarioId;
    }

    /**
     * Establece comentario id.
     *
     * @param comentarioId
     *            nuevo comentario id
     */
    public void setComentarioId(final Integer comentarioId) {
        this.comentarioId = comentarioId;
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
     * Obtiene votos positivos.
     *
     * @return votos positivos
     */
    public Integer getVotosPositivos() {
        return this.votosPositivos;
    }

    /**
     * Establece votos positivos.
     *
     * @param votosPositivos
     *            nuevo votos positivos
     */
    public void setVotosPositivos(final Integer votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    /**
     * Obtiene votos negativos.
     *
     * @return votos negativos
     */
    public Integer getVotosNegativos() {
        return this.votosNegativos;
    }

    /**
     * Establece votos negativos.
     *
     * @param votosNegativos
     *            nuevo votos negativos
     */
    public void setVotosNegativos(final Integer votosNegativos) {
        this.votosNegativos = votosNegativos;
    }

    /**
     * Obtiene texto comentario.
     *
     * @return texto comentario
     */
    public String getTextoComentario() {
        return this.textoComentario;
    }

    /**
     * Establece texto comentario.
     *
     * @param textoComentario
     *            nuevo texto comentario
     */
    public void setTextoComentario(final String textoComentario) {
        this.textoComentario = textoComentario;
    }

    /**
     * Chequea si es spoiler.
     *
     * @return true, si es spoiler
     */
    public boolean isEsSpoiler() {
        return this.esSpoiler;
    }

    /**
     * Establece es spoiler.
     *
     * @param esSpoiler
     *            nuevo es spoiler
     */
    public void setEsSpoiler(final boolean esSpoiler) {
        this.esSpoiler = esSpoiler;
    }

    /**
     * Chequea si es ban.
     *
     * @return true, si es ban
     */
    public boolean isEsBan() {
        return this.esBan;
    }

    /**
     * Establece es ban.
     *
     * @param esBan
     *            nuevo es ban
     */
    public void setEsBan(final boolean esBan) {
        this.esBan = esBan;
    }

    /**
     * Obtiene entrada id.
     *
     * @return entrada id
     */
    public Integer getEntradaId() {
        return this.entradaId;
    }

    /**
     * Establece entrada id.
     *
     * @param entradaId
     *            nuevo entrada id
     */
    public void setEntradaId(final Integer entradaId) {
        this.entradaId = entradaId;
    }

    /**
     * Obtiene comentario referencia.
     *
     * @return comentario referencia
     */
    public List<ComentarioDTO> getComentariosHijo() {
        return this.comentariosHijo;
    }

    /**
     * Establece comentario referencia.
     *
     * @param comentarioReferencia
     *            nuevo comentario referencia
     */
    public void setComentarioReferencia(final List<ComentarioDTO> comentariosHijo) {
        this.comentariosHijo = comentariosHijo;
    }

    /**
     * Sets the comentario referencia.
     *
     * @param comentarioPadreId
     *            the new comentario referencia
     */
    public void setComentarioReferencia(final Integer comentarioPadreId) {
        this.comentarioPadreId = comentarioPadreId;
    }

    /**
     * Gets the comentario padre id.
     *
     * @return the comentario padre id
     */
    public Integer getComentarioPadreId() {
        return this.comentarioPadreId;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha
     *            the new fecha
     */
    public void setFecha(final Date fecha) {
        this.fecha = fecha;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ComentarioDTO)) {
            return false;
        }
        final ComentarioDTO castOther = (ComentarioDTO) other;
        return new EqualsBuilder().append(this.comentarioId, castOther.comentarioId).append(this.autor, castOther.autor)
                .append(this.votosPositivos, castOther.votosPositivos)
                .append(this.votosNegativos, castOther.votosNegativos)
                .append(this.textoComentario, castOther.textoComentario).append(this.esSpoiler, castOther.esSpoiler)
                .append(this.esBan, castOther.esBan).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.comentarioId).append(this.autor).append(this.votosPositivos)
                .append(this.votosNegativos).append(this.textoComentario).append(this.esSpoiler).append(this.esBan)
                .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("comentarioId", this.comentarioId).append("autor", this.autor)
                .append("votosPositivos", this.votosPositivos).append("votosNegativos", this.votosNegativos)
                .append("textoComentario", this.textoComentario).append("esSpoiler", this.esSpoiler)
                .append("esBan", this.esBan).toString();
    }

}
