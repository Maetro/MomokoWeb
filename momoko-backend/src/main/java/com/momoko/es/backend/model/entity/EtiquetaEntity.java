/**
 * EtiquetaEntity.java 23-oct-2017
 *
 */
package com.momoko.es.backend.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EtiquetaEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramón Casares</a>
 */
@Entity
@Table(name = "etiqueta", indexes = { @Index(name = "etiquetaUrl", columnList = "etiquetaUrl", unique = true) })
public class EtiquetaEntity {
    /** The autor id. */
    private @Id @GeneratedValue Integer etiquetaId;

    /** The nombre. */
    private String nombre;

    /** The libros autor. */
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "etiquetas", fetch = FetchType.LAZY)
    private Set<EntradaEntity> etiquetasEntrada;

    /** The etiqueta url. */
    private String etiquetaUrl;

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
     * Obtiene etiqueta id.
     *
     * @return etiqueta id
     */
    public Integer getEtiquetaId() {
        return this.etiquetaId;
    }

    /**
     * Establece etiqueta id.
     *
     * @param etiqueta_id
     *            nuevo etiqueta id
     */
    public void setEtiquetaId(final Integer etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre
     *            nuevo nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene etiquetas entrada.
     *
     * @return etiquetas entrada
     */
    public Set<EntradaEntity> getEtiquetasEntrada() {
        return this.etiquetasEntrada;
    }

    /**
     * Establece etiquetas entrada.
     *
     * @param etiquetasEntrada
     *            nuevo etiquetas entrada
     */
    public void setEtiquetasEntrada(final Set<EntradaEntity> etiquetasEntrada) {
        this.etiquetasEntrada = etiquetasEntrada;
    }

    /**
     * Gets the etiqueta url.
     *
     * @return the etiqueta url
     */
    public String getEtiquetaUrl() {
        return this.etiquetaUrl;
    }

    /**
     * Sets the etiqueta url.
     *
     * @param etiquetaUrl
     *            the new etiqueta url
     */
    public void setEtiquetaUrl(final String etiquetaUrl) {
        this.etiquetaUrl = etiquetaUrl;
    }

    /**
     * Gets the usuario alta.
     *
     * @return the usuario alta
     */
    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    /**
     * Sets the usuario alta.
     *
     * @param usuarioAlta
     *            the new usuario alta
     */
    public void setUsuarioAlta(final String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * Gets the fecha alta.
     *
     * @return the fecha alta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Sets the fecha alta.
     *
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the usuario modificacion.
     *
     * @return the usuario modificacion
     */
    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    /**
     * Sets the usuario modificacion.
     *
     * @param usuarioModificacion
     *            the new usuario modificacion
     */
    public void setUsuarioModificacion(final String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Gets the fecha modificacion.
     *
     * @return the fecha modificacion
     */
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    /**
     * Sets the fecha modificacion.
     *
     * @param fechaModificacion
     *            the new fecha modificacion
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Gets the usuario baja.
     *
     * @return the usuario baja
     */
    public String getUsuarioBaja() {
        return this.usuarioBaja;
    }

    /**
     * Sets the usuario baja.
     *
     * @param usuarioBaja
     *            the new usuario baja
     */
    public void setUsuarioBaja(final String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    /**
     * Gets the fecha baja.
     *
     * @return the fecha baja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Sets the fecha baja.
     *
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EtiquetaEntity)) {
            return false;
        }
        final EtiquetaEntity castOther = (EtiquetaEntity) other;
        return new EqualsBuilder().append(this.etiquetaId, castOther.etiquetaId).append(this.nombre, castOther.nombre)
                .append(this.etiquetasEntrada, castOther.etiquetasEntrada).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.etiquetaId).append(this.nombre).append(this.etiquetasEntrada)
                .toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("etiquetaId", this.etiquetaId).append("nombre", this.nombre)
                .append("etiquetasEntrada", this.etiquetasEntrada).toString();
    }

}
