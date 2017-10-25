/**
 * PuntuacionEntity.java 24-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
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
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class PuntuacionEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Entity
@Table(name = "puntuacion")
public class PuntuacionEntity {

    /** The puntuacion id. */
    private @Id @GeneratedValue Integer puntuacionId;

    /** The valor. */
    private Integer valor; // Del 0 al 100

    /** The autor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity autor;

    /** The comentario. */
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private LibroEntity libro;

    /** The es puntuacion momoko. */
    private boolean esPuntuacionMomoko;

    public PuntuacionEntity() {
    }

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
     * Obtiene libro.
     *
     * @return libro
     */
    public LibroEntity getLibro() {
        return libro;
    }

    /**
     * Establece libro.
     *
     * @param libro
     *            nuevo libro
     */
    public void setLibro(LibroEntity libro) {
        this.libro = libro;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PuntuacionEntity))
            return false;
        PuntuacionEntity castOther = (PuntuacionEntity) other;
        return new EqualsBuilder().append(puntuacionId, castOther.puntuacionId).append(valor, castOther.valor)
                .append(autor, castOther.autor).append(comentario, castOther.comentario).append(libro, castOther.libro)
                .append(esPuntuacionMomoko, castOther.esPuntuacionMomoko).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(puntuacionId).append(valor).append(autor).append(comentario).append(libro)
                .append(esPuntuacionMomoko).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("puntuacionId", puntuacionId).append("valor", valor)
                .append("autor", autor).append("comentario", comentario).append("libro", libro)
                .append("esPuntuacionMomoko", esPuntuacionMomoko).toString();
    }

}
