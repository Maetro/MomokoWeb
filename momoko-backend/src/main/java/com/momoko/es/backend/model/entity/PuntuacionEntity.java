/**
 * PuntuacionEntity.java 24-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;

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

    /** The usuario alta. */
    private String usuarioAlta;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The usuario alta. */
    private String usuarioModificacion;

    /** The fecha alta. */
    private Date fechaModificacion;

    /** The usuario alta. */
    private String usuarioBaja;

    /** The fecha alta. */
    private Date fechaBaja;

    /**
     * Instancia un nuevo puntuacion entity.
     */
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

    /**
     * Obtiene usuario alta.
     *
     * @return usuario alta
     */
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    /**
     * Establece usuario alta.
     *
     * @param usuarioAlta
     *            nuevo usuario alta
     */
    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * Obtiene fecha alta.
     *
     * @return fecha alta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece fecha alta.
     *
     * @param fechaAlta
     *            nuevo fecha alta
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * Establece usuario modificacion.
     *
     * @param usuarioModificacion
     *            nuevo usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Establece fecha modificacion.
     *
     * @param fechaModificacion
     *            nuevo fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene usuario baja.
     *
     * @return usuario baja
     */
    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    /**
     * Establece usuario baja.
     *
     * @param usuarioBaja
     *            nuevo usuario baja
     */
    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Obtiene fecha baja.
     *
     * @return fecha baja
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Establece fecha baja.
     *
     * @param fechaBaja
     *            nuevo fecha baja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PuntuacionEntity))
            return false;
        PuntuacionEntity castOther = (PuntuacionEntity) other;
        return new EqualsBuilder().append(puntuacionId, castOther.puntuacionId).append(valor, castOther.valor)
                .append(autor, castOther.autor).append(comentario, castOther.comentario).append(libro, castOther.libro)
                .append(esPuntuacionMomoko, castOther.esPuntuacionMomoko).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(puntuacionId).append(valor).append(autor).append(comentario).append(libro)
                .append(esPuntuacionMomoko).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("puntuacionId", puntuacionId).append("valor", valor)
                .append("autor", autor).append("comentario", comentario).append("libro", libro)
                .append("esPuntuacionMomoko", esPuntuacionMomoko).toString();
    }

}
