/**
 * ComentarioDTO.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

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

    /** The votos positivos. */
    private Integer votosPositivos;

    /** The votos negativos. */
    private Integer votosNegativos;

    /** The texto comentario. */
    private String textoComentario;

    /** The entrada id. */
    private Integer entradaId;

    /** The comentario referencia. */
    private Integer comentarioReferencia;

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
        return comentarioId;
    }

    /**
     * Establece comentario id.
     *
     * @param comentarioId
     *            nuevo comentario id
     */
    public void setComentarioId(Integer comentarioId) {
        this.comentarioId = comentarioId;
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
     * Obtiene votos positivos.
     *
     * @return votos positivos
     */
    public Integer getVotosPositivos() {
        return votosPositivos;
    }

    /**
     * Establece votos positivos.
     *
     * @param votosPositivos
     *            nuevo votos positivos
     */
    public void setVotosPositivos(Integer votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    /**
     * Obtiene votos negativos.
     *
     * @return votos negativos
     */
    public Integer getVotosNegativos() {
        return votosNegativos;
    }

    /**
     * Establece votos negativos.
     *
     * @param votosNegativos
     *            nuevo votos negativos
     */
    public void setVotosNegativos(Integer votosNegativos) {
        this.votosNegativos = votosNegativos;
    }

    /**
     * Obtiene texto comentario.
     *
     * @return texto comentario
     */
    public String getTextoComentario() {
        return textoComentario;
    }

    /**
     * Establece texto comentario.
     *
     * @param textoComentario
     *            nuevo texto comentario
     */
    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    /**
     * Chequea si es spoiler.
     *
     * @return true, si es spoiler
     */
    public boolean isEsSpoiler() {
        return esSpoiler;
    }

    /**
     * Establece es spoiler.
     *
     * @param esSpoiler
     *            nuevo es spoiler
     */
    public void setEsSpoiler(boolean esSpoiler) {
        this.esSpoiler = esSpoiler;
    }

    /**
     * Chequea si es ban.
     *
     * @return true, si es ban
     */
    public boolean isEsBan() {
        return esBan;
    }

    /**
     * Establece es ban.
     *
     * @param esBan
     *            nuevo es ban
     */
    public void setEsBan(boolean esBan) {
        this.esBan = esBan;
    }

    /**
     * Obtiene entrada id.
     *
     * @return entrada id
     */
    public Integer getEntradaId() {
        return entradaId;
    }

    /**
     * Establece entrada id.
     *
     * @param entradaId
     *            nuevo entrada id
     */
    public void setEntradaId(Integer entradaId) {
        this.entradaId = entradaId;
    }

    /**
     * Obtiene comentario referencia.
     *
     * @return comentario referencia
     */
    public Integer getComentarioReferencia() {
        return comentarioReferencia;
    }

    /**
     * Establece comentario referencia.
     *
     * @param comentarioReferencia
     *            nuevo comentario referencia
     */
    public void setComentarioReferencia(Integer comentarioReferencia) {
        this.comentarioReferencia = comentarioReferencia;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ComentarioDTO))
            return false;
        ComentarioDTO castOther = (ComentarioDTO) other;
        return new EqualsBuilder().append(comentarioId, castOther.comentarioId).append(autor, castOther.autor)
                .append(votosPositivos, castOther.votosPositivos).append(votosNegativos, castOther.votosNegativos)
                .append(textoComentario, castOther.textoComentario).append(esSpoiler, castOther.esSpoiler)
                .append(esBan, castOther.esBan).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(comentarioId).append(autor).append(votosPositivos).append(votosNegativos)
                .append(textoComentario).append(esSpoiler).append(esBan).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("comentarioId", comentarioId).append("autor", autor)
                .append("votosPositivos", votosPositivos).append("votosNegativos", votosNegativos)
                .append("textoComentario", textoComentario).append("esSpoiler", esSpoiler).append("esBan", esBan)
                .toString();
    }

}
