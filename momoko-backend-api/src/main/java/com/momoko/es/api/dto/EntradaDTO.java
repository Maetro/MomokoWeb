/**
 * EntradaDTO.java 11-oct-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EntradaDTO.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramón Casares</a>
 */
public class EntradaDTO {

    /** The entrada id. */
    private Integer entradaId;

    /** The autor id. */
    private UsuarioDTO autor;

    /** The url entrada. */
    private String urlEntrada;

    /** The tipo entrada. */
    private String tipoEntrada;

    /** The titulo entrada. */
    private String tituloEntrada;

    /** The contenido entrada. */
    private String contenidoEntrada;

    /** The resumen entrada. */
    private String resumenEntrada;

    /** The estado entrada. */
    private Integer estadoEntrada;

    /** The permitir comentarios. */
    private Boolean permitirComentarios;

    /** The padre entrada id. */
    private EntradaDTO padreEntrada;

    /** The libro entrada. */
    private LibroDTO libroEntrada;

    /** The numero comentarios. */
    private Integer numeroComentarios;

    /** The orden. */
    private Integer orden;

    /**
     * Instancia un nuevo entrada DTO.
     */
    public EntradaDTO() {
    }

    /**
     * Obtiene entrada id.
     *
     * @return entrada id
     */
    public Integer getEntradaId() {
        return this.entradaId;
    }

    /**
     * Establece entrada id.
     *
     * @param entradaId
     *            nuevo entrada id
     */
    public void setEntradaId(final Integer entradaId) {
        this.entradaId = entradaId;
    }

    /**
     * Obtiene autor.
     *
     * @return autor
     */
    public UsuarioDTO getAutor() {
        return this.autor;
    }

    /**
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setAutor(final UsuarioDTO autor) {
        this.autor = autor;
    }

    /**
     * Obtiene url entrada.
     *
     * @return url entrada
     */
    public String getUrlEntrada() {
        return this.urlEntrada;
    }

    /**
     * Establece url entrada.
     *
     * @param urlEntrada
     *            nuevo url entrada
     */
    public void setUrlEntrada(final String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    /**
     * Obtiene tipo entrada.
     *
     * @return tipo entrada
     */
    public String getTipoEntrada() {
        return this.tipoEntrada;
    }

    /**
     * Establece tipo entrada.
     *
     * @param tipoEntrada
     *            nuevo tipo entrada
     */
    public void setTipoEntrada(final String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    /**
     * Obtiene titulo entrada.
     *
     * @return titulo entrada
     */
    public String getTituloEntrada() {
        return this.tituloEntrada;
    }

    /**
     * Establece titulo entrada.
     *
     * @param tituloEntrada
     *            nuevo titulo entrada
     */
    public void setTituloEntrada(final String tituloEntrada) {
        this.tituloEntrada = tituloEntrada;
    }

    /**
     * Obtiene contenido entrada.
     *
     * @return contenido entrada
     */
    public String getContenidoEntrada() {
        return this.contenidoEntrada;
    }

    /**
     * Establece contenido entrada.
     *
     * @param contenidoEntrada
     *            nuevo contenido entrada
     */
    public void setContenidoEntrada(final String contenidoEntrada) {
        this.contenidoEntrada = contenidoEntrada;
    }

    /**
     * Obtiene resumen entrada.
     *
     * @return resumen entrada
     */
    public String getResumenEntrada() {
        return this.resumenEntrada;
    }

    /**
     * Establece resumen entrada.
     *
     * @param resumenEntrada
     *            nuevo resumen entrada
     */
    public void setResumenEntrada(final String resumenEntrada) {
        this.resumenEntrada = resumenEntrada;
    }

    /**
     * Obtiene estado entrada.
     *
     * @return estado entrada
     */
    public Integer getEstadoEntrada() {
        return this.estadoEntrada;
    }

    /**
     * Establece estado entrada.
     *
     * @param estadoEntrada
     *            nuevo estado entrada
     */
    public void setEstadoEntrada(final Integer estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    /**
     * Obtiene permitir comentarios.
     *
     * @return permitir comentarios
     */
    public Boolean getPermitirComentarios() {
        return this.permitirComentarios;
    }

    /**
     * Establece permitir comentarios.
     *
     * @param permitirComentarios
     *            nuevo permitir comentarios
     */
    public void setPermitirComentarios(final Boolean permitirComentarios) {
        this.permitirComentarios = permitirComentarios;
    }

    /**
     * Obtiene padre entrada id.
     *
     * @return padre entrada id
     */
    public EntradaDTO getPadreEntrada() {
        return this.padreEntrada;
    }

    /**
     * Establece padre entrada id.
     *
     * @param padreEntradaId
     *            nuevo padre entrada id
     */
    public void setPadreEntrada(final EntradaDTO padreEntradaId) {
        this.padreEntrada = padreEntradaId;
    }

    /**
     * Obtiene libro entrada id.
     *
     * @return libro entrada id
     */
    public LibroDTO getLibroEntrada() {
        return this.libroEntrada;
    }

    /**
     * Establece libro entrada id.
     *
     * @param libroEntradaId
     *            nuevo libro entrada id
     */
    public void setLibroEntrada(final LibroDTO libroEntrada) {
        this.libroEntrada = libroEntrada;
    }

    /**
     * Obtiene numero comentarios.
     *
     * @return numero comentarios
     */
    public Integer getNumeroComentarios() {
        return this.numeroComentarios;
    }

    /**
     * Establece numero comentarios.
     *
     * @param numeroComentarios
     *            nuevo numero comentarios
     */
    public void setNumeroComentarios(final Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    /**
     * Obtiene orden.
     *
     * @return orden
     */
    public Integer getOrden() {
        return this.orden;
    }

    /**
     * Establece orden.
     *
     * @param orden
     *            nuevo orden
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaDTO)) {
            return false;
        }
        final EntradaDTO castOther = (EntradaDTO) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId).append(this.autor, castOther.autor)
                .append(this.urlEntrada, castOther.urlEntrada).append(this.tipoEntrada, castOther.tipoEntrada)
                .append(this.tituloEntrada, castOther.tituloEntrada)
                .append(this.contenidoEntrada, castOther.contenidoEntrada)
                .append(this.resumenEntrada, castOther.resumenEntrada)
                .append(this.estadoEntrada, castOther.estadoEntrada)
                .append(this.permitirComentarios, castOther.permitirComentarios)
                .append(this.padreEntrada, castOther.padreEntrada).append(this.libroEntrada, castOther.libroEntrada)
                .append(this.numeroComentarios, castOther.numeroComentarios).append(this.orden, castOther.orden)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).append(this.autor).append(this.urlEntrada)
                .append(this.tipoEntrada).append(this.tituloEntrada).append(this.contenidoEntrada)
                .append(this.resumenEntrada).append(this.estadoEntrada).append(this.permitirComentarios)
                .append(this.padreEntrada).append(this.libroEntrada).append(this.numeroComentarios).append(this.orden)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("autor", this.autor)
                .append("urlEntrada", this.urlEntrada).append("tipoEntrada", this.tipoEntrada)
                .append("tituloEntrada", this.tituloEntrada).append("contenidoEntrada", this.contenidoEntrada)
                .append("resumenEntrada", this.resumenEntrada).append("estadoEntrada", this.estadoEntrada)
                .append("permitirComentarios", this.permitirComentarios).append("padreEntradaId", this.padreEntrada)
                .append("libroEntradaId", this.libroEntrada).append("numeroComentarios", this.numeroComentarios)
                .append("orden", this.orden).toString();
    }

}
