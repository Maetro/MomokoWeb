/**
 * EditorialDTO.java 08-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    private List<InfoAdicionalDTO> infoAdicional;

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
     * @param editorialId
     *            the new editorial id
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
     * @param nombreEditorial
     *            the new nombre editorial
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
     * @param urlEditorial
     *            the new url editorial
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
        return this.imagenEditorial;
    }

    /**
     * Sets imagen editorial.
     *
     * @param imagenEditorial
     *            the imagen editorial
     */
    public void setImagenEditorial(final String imagenEditorial) {
        this.imagenEditorial = imagenEditorial;
    }

    /**
     * Gets descripcion editorial.
     *
     * @return the descripcion editorial
     */
    public String getDescripcionEditorial() {
        return this.descripcionEditorial;
    }

    /**
     * Sets descripcion editorial.
     *
     * @param descripcionEditorial
     *            the descripcion editorial
     */
    public void setDescripcionEditorial(final String descripcionEditorial) {
        this.descripcionEditorial = descripcionEditorial;
    }

    /**
     * Gets web editorial.
     *
     * @return the web editorial
     */
    public String getWebEditorial() {
        return this.webEditorial;
    }

    /**
     * Sets web editorial.
     *
     * @param webEditorial
     *            the web editorial
     */
    public void setWebEditorial(final String webEditorial) {
        this.webEditorial = webEditorial;
    }

    /**
     * Gets informacion de contacto.
     *
     * @return the informacion de contacto
     */
    public String getInformacionDeContacto() {
        return this.informacionDeContacto;
    }

    /**
     * Sets informacion de contacto.
     *
     * @param informacionDeContacto
     *            the informacion de contacto
     */
    public void setInformacionDeContacto(final String informacionDeContacto) {
        this.informacionDeContacto = informacionDeContacto;
    }

    /**
     * Gets imagen cabecera editorial.
     *
     * @return the imagen cabecera editorial
     */
    public String getImagenCabeceraEditorial() {
        return this.imagenCabeceraEditorial;
    }

    /**
     * Sets imagen cabecera editorial.
     *
     * @param imagenCabeceraEditorial
     *            the imagen cabecera editorial
     */
    public void setImagenCabeceraEditorial(final String imagenCabeceraEditorial) {
        this.imagenCabeceraEditorial = imagenCabeceraEditorial;
    }

    /**
     * Gets the info adicional.
     *
     * @return the info adicional
     */
    public List<InfoAdicionalDTO> getInfoAdicional() {
        return this.infoAdicional;
    }

    /**
     * Sets the info adicional.
     *
     * @param infoAdicional
     *            the new info adicional
     */
    public void setInfoAdicional(final List<InfoAdicionalDTO> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.editorialId, this.nombreEditorial, this.urlEditorial, this.imagenEditorial,
                this.descripcionEditorial, this.webEditorial, this.informacionDeContacto, this.imagenCabeceraEditorial,
                this.infoAdicional);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
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
                && Objects.equals(this.imagenCabeceraEditorial, other.imagenCabeceraEditorial)
                && Objects.equals(this.infoAdicional, other.infoAdicional);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("editorialId", this.editorialId)
                .append("nombreEditorial", this.nombreEditorial).append("urlEditorial", this.urlEditorial)
                .append("imagenEditorial", this.imagenEditorial)
                .append("descripcionEditorial", this.descripcionEditorial).append("webEditorial", this.webEditorial)
                .append("informacionDeContacto", this.informacionDeContacto)
                .append("imagenCabeceraEditorial", this.imagenCabeceraEditorial)
                .append("infoAdicional", this.infoAdicional).toString();
    }

}
