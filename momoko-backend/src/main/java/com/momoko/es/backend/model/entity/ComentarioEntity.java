/**
 * ComentarioEntity.java 24-oct-2017
 *
 */
package com.momoko.es.backend.model.entity;

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
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
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
    private String votosPositivos;

    /** The votos negativos. */
    private String votosNegativos;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comentario_id")
    private ComentarioEntity respuesta;

    /**
     * Obtiene autor.
     *
     * @return autor
     */
    public UsuarioEntity getAutor() {
        return this.autor;
    }

    /**
     * Obtiene comentario id.
     *
     * @return comentario id
     */
   public Integer getComentarioId(){
        return this.comentarioId;
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
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setAutor(final UsuarioEntity autor) {
        this.autor = autor;
    }

    /**
     * Obtiene votos positivos.
     *
     * @return votos positivos
     */
    public String getVotosPositivos() {
        return this.votosPositivos;
    }

    /**
     * Establece votos positivos.
     *
     * @param votosPositivos
     *            nuevo votos positivos
     */
    public void setVotosPositivos(final String votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    /**
     * Obtiene votos negativos.
     *
     * @return votos negativos
     */
    public String getVotosNegativos() {
        return this.votosNegativos;
    }

    /**
     * Establece votos negativos.
     *
     * @param votosNegativos
     *            nuevo votos negativos
     */
    public void setVotosNegativos(final String votosNegativos) {
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
     * Obtiene entrada.
     *
     * @return entrada
     */
    public EntradaEntity getEntrada() {
        return this.entrada;
    }

    /**
     * Establece entrada.
     *
     * @param entrada
     *            nuevo entrada
     */
    public void setEntrada(final EntradaEntity entrada) {
        this.entrada = entrada;
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ComentarioEntity)) {
            return false;
        }
        final ComentarioEntity castOther = (ComentarioEntity) other;
        return new EqualsBuilder().append(this.autor, castOther.autor)
                .append(this.votosPositivos, castOther.votosPositivos)
                .append(this.votosNegativos, castOther.votosNegativos)
                .append(this.textoComentario, castOther.textoComentario).append(this.entrada, castOther.entrada)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.autor).append(this.votosPositivos).append(this.votosNegativos)
                .append(this.textoComentario).append(this.entrada).toHashCode();
    }

}
