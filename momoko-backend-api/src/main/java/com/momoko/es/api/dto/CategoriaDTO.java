/**
 * CategoriaDTO.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class CategoriaDTO.
 */
public class CategoriaDTO implements Serializable, Comparable<CategoriaDTO> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5910577190726650825L;

    /** The categoria id. */
    private Integer categoriaId;

    /** The nombre categoria. */
    private String nombreCategoria;

    /** The url categoria. */
    private String urlCategoria;

    /** The background color. */
    private String backgroundColor;

    /** The foreground color. */
    private String foregroundColor;

    /** The orden. */
    private Integer orden;

    private boolean showOnMenu;

    /**
     * Gets the categoria id.
     *
     * @return the categoria id
     */
    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    /**
     * Sets the categoria id.
     *
     * @param categoriaId
     *            the new categoria id
     */
    public void setCategoriaId(final Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    /**
     * Gets the url categoria.
     *
     * @return the url categoria
     */
    public String getUrlCategoria() {
        return this.urlCategoria;
    }

    /**
     * Gets the nombre categoria.
     *
     * @return the nombre categoria
     */
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    /**
     * Sets the nombre categoria.
     *
     * @param nombreCategoria
     *            the new nombre categoria
     */
    public void setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Sets the url categoria.
     *
     * @param urlCategoria
     *            the new url categoria
     */
    public void setUrlCategoria(final String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }

    /**
     * Gets the background color.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the background color.
     *
     * @param backgroundColor
     *            the new background color
     */
    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets the foreground color.
     *
     * @return the foreground color
     */
    public String getForegroundColor() {
        return this.foregroundColor;
    }

    /**
     * Sets the foreground color.
     *
     * @param foregroundColor
     *            the new foreground color
     */
    public void setForegroundColor(final String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    /**
     * Gets the orden.
     *
     * @return the orden
     */
    public Integer getOrden() {
        return this.orden;
    }

    public boolean isShowOnMenu() {
        return showOnMenu;
    }

    public void setShowOnMenu(boolean showOnMenu) {
        this.showOnMenu = showOnMenu;
    }

    /**
     * Sets the orden.
     *
     * @param orden
     *            the new orden
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CategoriaDTO)) {
            return false;
        }
        final CategoriaDTO castOther = (CategoriaDTO) other;
        return new EqualsBuilder().append(this.categoriaId, castOther.categoriaId)
                .append(this.urlCategoria, castOther.urlCategoria)
                .append(this.nombreCategoria, castOther.nombreCategoria)
                .append(this.backgroundColor, castOther.backgroundColor)
                .append(this.foregroundColor, castOther.foregroundColor).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.categoriaId).append(this.nombreCategoria).append(this.urlCategoria)
                .append(this.backgroundColor).append(this.foregroundColor).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoriaId", this.categoriaId)
                .append("nombreCategoria", this.nombreCategoria).append("urlCategoria", this.urlCategoria)
                .append("backgroundColor", this.backgroundColor).append("foregroundColor", this.foregroundColor)
                .toString();
    }

    public int compareTo(final CategoriaDTO other) {
        return new CompareToBuilder().append(this.orden, other.orden).toComparison();
    }

}
