/**
 * EditorialEntity.java 08-ago-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * The Class EditorialEntity.
 */
@Entity
@Table(name = "editorial")
public class EditorialEntity {

    /** The editorial id. */
    private @Id
    @GeneratedValue
    Integer editorialId;

    /** The nombre editorial. */
    @Column(name = "nombre_editorial")
    private String nombreEditorial;

    /** The url editorial. */
    private String urlEditorial;

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

    private String imagenEditorial;

    private String descripcionEditorial;

    private String webEditorial;

    private String informacionDeContacto;

    private String imagenCabeceraEditorial;

    /** The libros autor. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "editorial")
    private Set<LibroEntity> librosEditorial;

    /**
     * Gets the editorial id.
     *
     * @return the editorial id
     */
    public Integer getEditorialId() {
        return this.editorialId;
    }

    /**
     * Sets the editorial id.
     *
     * @param editorialId the new editorial id
     */
    public void setEditorialId(final Integer editorialId) {
        this.editorialId = editorialId;
    }

    /**
     * Gets the nombre editorial.
     *
     * @return the nombre editorial
     */
    public String getNombreEditorial() {
        return this.nombreEditorial;
    }

    /**
     * Sets the nombre editorial.
     *
     * @param nombreEditorial the new nombre editorial
     */
    public void setNombreEditorial(final String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
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
     * @param usuarioAlta the new usuario alta
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
     * @param fechaAlta the new fecha alta
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
     * @param usuarioModificacion the new usuario modificacion
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
     * @param fechaModificacion the new fecha modificacion
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
     * @param usuarioBaja the new usuario baja
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
     * @param fechaBaja the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Gets the url editorial.
     *
     * @return the url editorial
     */
    public String getUrlEditorial() {
        return this.urlEditorial;
    }

    /**
     * Sets the url editorial.
     *
     * @param urlEditorial the new url editorial
     */
    public void setUrlEditorial(final String urlEditorial) {
        this.urlEditorial = urlEditorial;
    }

    /**
     * Gets imagen editorial.
     *
     * @return the imagen editorial
     */
    public String getImagenEditorial() {
        return imagenEditorial;
    }

    /**
     * Sets imagen editorial.
     *
     * @param imagenEditorial the imagen editorial
     */
    public void setImagenEditorial(String imagenEditorial) {
        this.imagenEditorial = imagenEditorial;
    }

    /**
     * Gets descripcion editorial.
     *
     * @return the descripcion editorial
     */
    public String getDescripcionEditorial() {
        return descripcionEditorial;
    }

    /**
     * Sets descripcion editorial.
     *
     * @param descripcionEditorial the descripcion editorial
     */
    public void setDescripcionEditorial(String descripcionEditorial) {
        this.descripcionEditorial = descripcionEditorial;
    }

    /**
     * Gets web editorial.
     *
     * @return the web editorial
     */
    public String getWebEditorial() {
        return webEditorial;
    }

    /**
     * Sets web editorial.
     *
     * @param webEditorial the web editorial
     */
    public void setWebEditorial(String webEditorial) {
        this.webEditorial = webEditorial;
    }

    /**
     * Gets informacion de contacto.
     *
     * @return the informacion de contacto
     */
    public String getInformacionDeContacto() {
        return informacionDeContacto;
    }

    /**
     * Sets informacion de contacto.
     *
     * @param informacionDeContacto the informacion de contacto
     */
    public void setInformacionDeContacto(String informacionDeContacto) {
        this.informacionDeContacto = informacionDeContacto;
    }

    /**
     * Gets imagen cabecera editorial.
     *
     * @return the imagen cabecera editorial
     */
    public String getImagenCabeceraEditorial() {
        return imagenCabeceraEditorial;
    }

    /**
     * Sets imagen cabecera editorial.
     *
     * @param imagenCabeceraEditorial the imagen cabecera editorial
     */
    public void setImagenCabeceraEditorial(String imagenCabeceraEditorial) {
        this.imagenCabeceraEditorial = imagenCabeceraEditorial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(editorialId, nombreEditorial, urlEditorial, usuarioAlta, fechaAlta, usuarioModificacion,
                fechaModificacion, usuarioBaja, fechaBaja, imagenEditorial, descripcionEditorial, webEditorial,
                informacionDeContacto, librosEditorial);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final EditorialEntity other = (EditorialEntity) obj;
        return Objects.equals(this.editorialId, other.editorialId)
                && Objects.equals(this.nombreEditorial, other.nombreEditorial)
                && Objects.equals(this.urlEditorial, other.urlEditorial)
                && Objects.equals(this.usuarioAlta, other.usuarioAlta)
                && Objects.equals(this.fechaAlta, other.fechaAlta)
                && Objects.equals(this.usuarioModificacion, other.usuarioModificacion)
                && Objects.equals(this.fechaModificacion, other.fechaModificacion)
                && Objects.equals(this.usuarioBaja, other.usuarioBaja)
                && Objects.equals(this.fechaBaja, other.fechaBaja)
                && Objects.equals(this.imagenEditorial, other.imagenEditorial)
                && Objects.equals(this.descripcionEditorial, other.descripcionEditorial)
                && Objects.equals(this.webEditorial, other.webEditorial)
                && Objects.equals(this.informacionDeContacto, other.informacionDeContacto)
                && Objects.equals(this.librosEditorial, other.librosEditorial);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("editorialId", this.editorialId)
                .append("nombreEditorial", this.nombreEditorial).toString();
    }

}
