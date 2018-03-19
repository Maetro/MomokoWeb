/**
 * PuntuacionEntity.java 24-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import java.math.BigDecimal;
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
    private BigDecimal valor; // Del 0 al 100

    /** The autor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity autor;

    /** The comentario. */
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private LibroEntity libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saga_id")
    private SagaEntity saga;

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
    public UsuarioEntity getAutor() {
        return this.autor;
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
     * Obtiene libro.
     *
     * @return libro
     */
    public LibroEntity getLibro() {
        return this.libro;
    }

    /**
     * Establece libro.
     *
     * @param libro
     *            nuevo libro
     */
    public void setLibro(final LibroEntity libro) {
        this.libro = libro;
    }

    /**
     * Gets the saga.
     *
     * @return the saga
     */
    public SagaEntity getSaga() {
        return this.saga;
    }

    /**
     * Sets the saga.
     *
     * @param saga
     *            the new saga
     */
    public void setSaga(final SagaEntity saga) {
        this.saga = saga;
    }

    /**
     * Obtiene usuario alta.
     *
     * @return usuario alta
     */
    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    /**
     * Establece usuario alta.
     *
     * @param usuarioAlta
     *            nuevo usuario alta
     */
    public void setUsuarioAlta(final String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * Obtiene fecha alta.
     *
     * @return fecha alta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Establece fecha alta.
     *
     * @param fechaAlta
     *            nuevo fecha alta
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    /**
     * Establece usuario modificacion.
     *
     * @param usuarioModificacion
     *            nuevo usuario modificacion
     */
    public void setUsuarioModificacion(final String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    /**
     * Establece fecha modificacion.
     *
     * @param fechaModificacion
     *            nuevo fecha modificacion
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene usuario baja.
     *
     * @return usuario baja
     */
    public String getUsuarioBaja() {
        return this.usuarioBaja;
    }

    /**
     * Establece usuario baja.
     *
     * @param usuarioBaja
     *            nuevo usuario baja
     */
    public void setUsuarioBaja(final String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Obtiene fecha baja.
     *
     * @return fecha baja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Establece fecha baja.
     *
     * @param fechaBaja
     *            nuevo fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PuntuacionEntity)) {
            return false;
        }
        final PuntuacionEntity castOther = (PuntuacionEntity) other;
        return new EqualsBuilder().append(this.puntuacionId, castOther.puntuacionId).append(this.valor, castOther.valor)
                .append(this.autor, castOther.autor).append(this.comentario, castOther.comentario)
                .append(this.libro, castOther.libro).append(this.esPuntuacionMomoko, castOther.esPuntuacionMomoko)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.puntuacionId).append(this.valor).append(this.autor)
                .append(this.comentario).append(this.libro).append(this.esPuntuacionMomoko).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("puntuacionId", this.puntuacionId).append("valor", this.valor)
                .append("autor", this.autor).append("comentario", this.comentario).append("libro", this.libro)
                .append("esPuntuacionMomoko", this.esPuntuacionMomoko).toString();
    }

}
