/**
 * EtiquetaEntity.java 23-oct-2017
 *
 */
package com.ms.backend.model.entity;

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
        return etiqueta_id;
    }

    /**
     * Establece etiqueta id.
     *
     * @param etiqueta_id
     *            nuevo etiqueta id
     */
    public void setEtiqueta_id(Integer etiqueta_id) {
        this.etiqueta_id = etiqueta_id;
    }

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre
     *            nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene etiquetas entrada.
     *
     * @return etiquetas entrada
     */
    public Set<EntradaEntity> getEtiquetasEntrada() {
        return etiquetasEntrada;
    }

    /**
     * Establece etiquetas entrada.
     *
     * @param etiquetasEntrada
     *            nuevo etiquetas entrada
     */
    public void setEtiquetasEntrada(Set<EntradaEntity> etiquetasEntrada) {
        this.etiquetasEntrada = etiquetasEntrada;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EtiquetaEntity))
            return false;
        EtiquetaEntity castOther = (EtiquetaEntity) other;
        return new EqualsBuilder().append(etiqueta_id, castOther.etiqueta_id).append(nombre, castOther.nombre)
                .append(etiquetasEntrada, castOther.etiquetasEntrada).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(etiqueta_id).append(nombre).append(etiquetasEntrada).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("etiqueta_id", etiqueta_id).append("nombre", nombre)
                .append("etiquetasEntrada", etiquetasEntrada).toString();
    }

}
