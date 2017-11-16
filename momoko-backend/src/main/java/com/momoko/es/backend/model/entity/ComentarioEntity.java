/**
 * ComentarioEntity.java 24-oct-2017
 *
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

/**
 * The Class ComentarioEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Entity
@Table(name = "comentario")
public class ComentarioEntity {

    private @Id @GeneratedValue Integer comentarioId;

    /** The pagina web comentario. */
    private String paginaWebComentario;

    /** The nombre comentario. */
    private String nombreComentario;

    /** The email comentario. */
    private String emailComentario;

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
    private ComentarioEntity comentarioReferenciaEntity;

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

    public String getEmailComentario() {
        return this.emailComentario;
    }

    /**
     * Sets the email comentario.
     *
     * @param emailComentario
     *            the new email comentario
     */
    public void setEmailComentario(final String emailComentario) {
        this.emailComentario = emailComentario;
    }

    /**
     * Gets the nombre comentario.
     *
     * @return the nombre comentario
     */
    public String getNombreComentario() {
        return this.nombreComentario;
    }

    /**
     * Sets the nombre comentario.
     *
     * @param nombreComentario
     *            the new nombre comentario
     */
    public void setNombreComentario(final String nombreComentario) {
        this.nombreComentario = nombreComentario;
    }

    /**
     * Gets the pagina web comentario.
     *
     * @return the pagina web comentario
     */
    public String getPaginaWebComentario() {
        return this.paginaWebComentario;
    }

    /**
     * Sets the pagina web comentario.
     *
     * @param paginaWebComentario
     *            the new pagina web comentario
     */
    public void setPaginaWebComentario(final String paginaWebComentario) {
        this.paginaWebComentario = paginaWebComentario;
    }

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
     * Obtiene comentario referencia entity.
     *
     * @return comentario referencia entity
     */
    public ComentarioEntity getComentarioReferenciaEntity() {
        return this.comentarioReferenciaEntity;
    }

    /**
     * Establece comentario referencia entity.
     *
     * @param comentarioReferenciaEntity
     *            nuevo comentario referencia entity
     */
    public void setComentarioReferenciaEntity(final ComentarioEntity comentarioReferenciaEntity) {
        this.comentarioReferenciaEntity = comentarioReferenciaEntity;
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
        if (!(other instanceof ComentarioEntity)) {
            return false;
        }
        final ComentarioEntity castOther = (ComentarioEntity) other;
        return new EqualsBuilder().append(this.emailComentario, castOther.emailComentario)
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
        return new HashCodeBuilder().append(this.emailComentario).append(this.votosPositivos)
                .append(this.votosNegativos).append(this.textoComentario).append(this.entrada).toHashCode();
    }

}
