/**
 * EntradaEntity.java 11-oct-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EntradaEntity.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramón Casares</a>
 */
@Entity
@Table(name = "entrada")
public class EntradaEntity extends AuditoriaBasica {

    private @Id @GeneratedValue Integer entradaId;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity entradaAutor;
    
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

    /** The padre entrada. */
    @ManyToOne
    @JoinColumn(name = "padre_entrada")
    private EntradaEntity padreEntrada;

    /** The libro entrada. */
    private LibroEntity libroEntrada;

    /** The numero comentarios. */
    private Integer numeroComentarios;

    /** The orden. */
    private Integer orden;

    /**
     * Instancia un nuevo entrada entity.
     */
    public EntradaEntity() {
    }

    /**
     * Obtiene entrada id.
     *
     * @return entrada id
     */
    public Integer getEntradaId() {
        return entradaId;
    }

    /**
     * Establece entrada id.
     *
     * @param entradaId
     *            nuevo entrada id
     */
    public void setEntradaId(Integer entradaId) {
        this.entradaId = entradaId;
    }

    /**
     * Obtiene entrada autor.
     *
     * @return entrada autor
     */
    public UsuarioEntity getEntradaAutor() {
        return entradaAutor;
    }

    /**
     * Establece entrada autor.
     *
     * @param entradaAutor
     *            nuevo entrada autor
     */
    public void setEntradaAutor(UsuarioEntity entradaAutor) {
        this.entradaAutor = entradaAutor;
    }

    /**
     * Obtiene url entrada.
     *
     * @return url entrada
     */
    public String getUrlEntrada() {
        return urlEntrada;
    }

    /**
     * Establece url entrada.
     *
     * @param url_Entrada
     *            nuevo url entrada
     */
    public void setUrlEntrada(String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    /**
     * Obtiene tipo entrada.
     *
     * @return tipo entrada
     */
    public String getTipoEntrada() {
        return tipoEntrada;
    }

    /**
     * Establece tipo entrada.
     *
     * @param tipoEntrada
     *            nuevo tipo entrada
     */
    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    /**
     * Obtiene titulo entrada.
     *
     * @return titulo entrada
     */
    public String getTituloEntrada() {
        return tituloEntrada;
    }

    /**
     * Establece titulo entrada.
     *
     * @param tituloEntrada
     *            nuevo titulo entrada
     */
    public void setTituloEntrada(String tituloEntrada) {
        this.tituloEntrada = tituloEntrada;
    }

    /**
     * Obtiene contenido entrada.
     *
     * @return contenido entrada
     */
    public String getContenidoEntrada() {
        return contenidoEntrada;
    }

    /**
     * Establece contenido entrada.
     *
     * @param contenidoEntrada
     *            nuevo contenido entrada
     */
    public void setContenidoEntrada(String contenidoEntrada) {
        this.contenidoEntrada = contenidoEntrada;
    }

    /**
     * Obtiene resumen entrada.
     *
     * @return resumen entrada
     */
    public String getResumenEntrada() {
        return resumenEntrada;
    }

    /**
     * Establece resumen entrada.
     *
     * @param resumenEntrada
     *            nuevo resumen entrada
     */
    public void setResumenEntrada(String resumenEntrada) {
        this.resumenEntrada = resumenEntrada;
    }

    /**
     * Obtiene estado entrada.
     *
     * @return estado entrada
     */
    public Integer getEstadoEntrada() {
        return estadoEntrada;
    }

    /**
     * Establece estado entrada.
     *
     * @param estadoEntrada
     *            nuevo estado entrada
     */
    public void setEstadoEntrada(Integer estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    /**
     * Obtiene permitir comentarios.
     *
     * @return permitir comentarios
     */
    public Boolean getPermitirComentarios() {
        return permitirComentarios;
    }

    /**
     * Establece permitir comentarios.
     *
     * @param permitirComentarios
     *            nuevo permitir comentarios
     */
    public void setPermitirComentarios(Boolean permitirComentarios) {
        this.permitirComentarios = permitirComentarios;
    }

    /**
     * Obtiene padre entrada.
     *
     * @return padre entrada
     */
    public EntradaEntity getPadreEntrada() {
        return padreEntrada;
    }

    /**
     * Establece padre entrada.
     *
     * @param padreEntrada
     *            nuevo padre entrada
     */
    public void setPadreEntrada(EntradaEntity padreEntrada) {
        this.padreEntrada = padreEntrada;
    }

    /**
     * Obtiene libro entrada.
     *
     * @return libro entrada
     */
    public LibroEntity getLibroEntrada() {
        return libroEntrada;
    }

    /**
     * Establece libro entrada.
     *
     * @param libroEntrada
     *            nuevo libro entrada
     */
    public void setLibroEntrada(LibroEntity libroEntrada) {
        this.libroEntrada = libroEntrada;
    }

    /**
     * Obtiene numero comentarios.
     *
     * @return numero comentarios
     */
    public Integer getNumeroComentarios() {
        return numeroComentarios;
    }

    /**
     * Establece numero comentarios.
     *
     * @param numeroComentarios
     *            nuevo numero comentarios
     */
    public void setNumeroComentarios(Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    /**
     * Obtiene orden.
     *
     * @return orden
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * Establece orden.
     *
     * @param orden
     *            nuevo orden
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaEntity))
            return false;
        EntradaEntity castOther = (EntradaEntity) other;
        return new EqualsBuilder().append(entradaId, castOther.entradaId).append(entradaAutor, castOther.entradaAutor)
                .append(urlEntrada, castOther.urlEntrada).append(tipoEntrada, castOther.tipoEntrada)
                .append(tituloEntrada, castOther.tituloEntrada).append(contenidoEntrada, castOther.contenidoEntrada)
                .append(resumenEntrada, castOther.resumenEntrada).append(estadoEntrada, castOther.estadoEntrada)
                .append(permitirComentarios, castOther.permitirComentarios).append(padreEntrada, castOther.padreEntrada)
                .append(libroEntrada, castOther.libroEntrada).append(numeroComentarios, castOther.numeroComentarios)
                .append(orden, castOther.orden).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(entradaId).append(entradaAutor).append(urlEntrada).append(tipoEntrada)
                .append(tituloEntrada).append(contenidoEntrada).append(resumenEntrada).append(estadoEntrada)
                .append(permitirComentarios).append(padreEntrada).append(libroEntrada).append(numeroComentarios)
                .append(orden).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", entradaId).append("entradaAutor", entradaAutor)
                .append("urlEntrada", urlEntrada).append("tipoEntrada", tipoEntrada)
                .append("tituloEntrada", tituloEntrada).append("contenidoEntrada", contenidoEntrada)
                .append("resumenEntrada", resumenEntrada).append("estadoEntrada", estadoEntrada)
                .append("permitirComentarios", permitirComentarios).append("padreEntrada", padreEntrada)
                .append("libroEntrada", libroEntrada).append("numeroComentarios", numeroComentarios)
                .append("orden", orden).toString();
    }
    

}
