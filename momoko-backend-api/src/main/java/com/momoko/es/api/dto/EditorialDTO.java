/**
 * EditorialDTO.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EditorialDTO.
 */
public class EditorialDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8662615990339682558L;

    /** The editorial id. */
    private Integer editorialId;

    /** The nombre editorial. */
    private String nombreEditorial;

    /** The url editorial. */
    private String urlEditorial;

    private String imagenEditorial;

    private String descripcionEditorial;

    private String webEditorial;

    private String informacionDeContacto;

    private String imagenCabeceraEditorial;

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
        return Objects.hash(editorialId, nombreEditorial, urlEditorial, imagenEditorial, descripcionEditorial, webEditorial, informacionDeContacto, imagenCabeceraEditorial);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final EditorialDTO other = (EditorialDTO) obj;
        return Objects.equals(this.editorialId, other.editorialId)
                && Objects.equals(this.nombreEditorial, other.nombreEditorial)
                && Objects.equals(this.urlEditorial, other.urlEditorial)
                && Objects.equals(this.imagenEditorial, other.imagenEditorial)
                && Objects.equals(this.descripcionEditorial, other.descripcionEditorial)
                && Objects.equals(this.webEditorial, other.webEditorial)
                && Objects.equals(this.informacionDeContacto, other.informacionDeContacto)
                && Objects.equals(this.imagenCabeceraEditorial, other.imagenCabeceraEditorial);
    }

    @Override
    public String toString() {
        return "EditorialDTO{" +
                "editorialId=" + editorialId +
                ", nombreEditorial='" + nombreEditorial + '\'' +
                ", urlEditorial='" + urlEditorial + '\'' +
                ", imagenEditorial='" + imagenEditorial + '\'' +
                ", descripcionEditorial='" + descripcionEditorial + '\'' +
                ", webEditorial='" + webEditorial + '\'' +
                ", informacionDeContacto='" + informacionDeContacto + '\'' +
                ", imagenCabeceraEditorial='" + imagenCabeceraEditorial + '\'' +
                '}';
    }
}
