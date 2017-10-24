/**
 * ComentarioEntity.java 24-oct-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class ComentarioEntity.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramón Casares</a>
 */
@Entity
@Table(name = "comentario")
public class ComentarioEntity extends AuditoriaBasica {

    private @Id @GeneratedValue Integer comentarioId;

    /** The autor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity autor;

    /** The votos positivos. */
    private List<String> votosPositivos;

    /** The votos negativos. */
    private List<String> votosNegativos;

    /** The texto comentario. */
    private String textoComentario;

    /** The es spoiler. */
    private boolean esSpoiler;

    /** The es ban. */
    private boolean esBan;

    /** The editorial id. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrada_id")
    private EntradaEntity entrada;

    /**
     * Obtiene autor.
     *
     * @return autor
     */
    public UsuarioEntity getAutor() {
        return autor;
    }

    /**
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setAutor(UsuarioEntity autor) {
        this.autor = autor;
    }

    /**
     * Obtiene votos positivos.
     *
     * @return votos positivos
     */
    public List<String> getVotosPositivos() {
        return votosPositivos;
    }

    /**
     * Establece votos positivos.
     *
     * @param votosPositivos
     *            nuevo votos positivos
     */
    public void setVotosPositivos(List<String> votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    /**
     * Obtiene votos negativos.
     *
     * @return votos negativos
     */
    public List<String> getVotosNegativos() {
        return votosNegativos;
    }

    /**
     * Establece votos negativos.
     *
     * @param votosNegativos
     *            nuevo votos negativos
     */
    public void setVotosNegativos(List<String> votosNegativos) {
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ComentarioEntity))
            return false;
        ComentarioEntity castOther = (ComentarioEntity) other;
        return new EqualsBuilder().append(autor, castOther.autor).append(votosPositivos, castOther.votosPositivos)
                .append(votosNegativos, castOther.votosNegativos).append(textoComentario, castOther.textoComentario)
                .append(entrada, castOther.entrada).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(autor).append(votosPositivos).append(votosNegativos).append(textoComentario)
                .append(entrada).toHashCode();
    }

}
