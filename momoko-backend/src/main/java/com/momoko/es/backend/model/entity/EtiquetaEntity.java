/**
 * EtiquetaEntity.java 23-oct-2017
 *
 */
package com.momoko.es.backend.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "etiqueta")
public class EtiquetaEntity extends AuditoriaBasica {
    /** The autor id. */
    private @Id @GeneratedValue Integer etiqueta_id;

    /** The nombre. */
    private String nombre;

    /** The libros autor. */
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "etiquetas", fetch = FetchType.LAZY)
    private Set<EntradaEntity> etiquetasEntrada;

    /**
     * Obtiene etiqueta id.
     *
     * @return etiqueta id
     */
    public Integer getEtiqueta_id() {
        return this.etiqueta_id;
    }

    /**
     * Establece etiqueta id.
     *
     * @param etiqueta_id
     *            nuevo etiqueta id
     */
    public void setEtiqueta_id(final Integer etiqueta_id) {
        this.etiqueta_id = etiqueta_id;
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

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EtiquetaEntity)) {
            return false;
        }
        final EtiquetaEntity castOther = (EtiquetaEntity) other;
        return new EqualsBuilder().append(this.etiqueta_id, castOther.etiqueta_id).append(this.nombre, castOther.nombre)
                .append(this.etiquetasEntrada, castOther.etiquetasEntrada).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.etiqueta_id).append(this.nombre).append(this.etiquetasEntrada)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("etiqueta_id", this.etiqueta_id).append("nombre", this.nombre)
                .append("etiquetasEntrada", this.etiquetasEntrada).toString();
    }

}
