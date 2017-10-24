/**
 * EntradaEntity.java 11-oct-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.backend.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private Integer tipoEntrada;

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
    @ManyToOne
    @JoinColumn(name = "libro_entrada")
    private LibroEntity libroEntrada;

    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentarios;

    /** The numero comentarios. */
    private Integer numeroComentarios;

    /** The orden. */
    private Integer orden;

    /** The genero id. */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "entrada_etiqueta", joinColumns = @JoinColumn(name = "entrada_id", referencedColumnName = "entradaId"), inverseJoinColumns = @JoinColumn(name = "etiqueta_id", referencedColumnName = "etiqueta_id"))
    private Set<EtiquetaEntity> etiquetas;

    private String imagenDestacada;

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
     * Obtiene entrada autor.
     *
     * @return entrada autor
     */
    public UsuarioEntity getEntradaAutor() {
        return this.entradaAutor;
    }

    /**
     * Establece entrada autor.
     *
     * @param entradaAutor
     *            nuevo entrada autor
     */
    public void setEntradaAutor(final UsuarioEntity entradaAutor) {
        this.entradaAutor = entradaAutor;
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
    public Integer getTipoEntrada() {
        return this.tipoEntrada;
    }

    /**
     * Establece tipo entrada.
     *
     * @param tipoEntrada
     *            nuevo tipo entrada
     */
    public void setTipoEntrada(final Integer tipoEntrada) {
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
     * Obtiene padre entrada.
     *
     * @return padre entrada
     */
    public EntradaEntity getPadreEntrada() {
        return this.padreEntrada;
    }

    /**
     * Establece padre entrada.
     *
     * @param padreEntrada
     *            nuevo padre entrada
     */
    public void setPadreEntrada(final EntradaEntity padreEntrada) {
        this.padreEntrada = padreEntrada;
    }

    /**
     * Obtiene libro entrada.
     *
     * @return libro entrada
     */
    public LibroEntity getLibroEntrada() {
        return this.libroEntrada;
    }

    /**
     * Establece libro entrada.
     *
     * @param libroEntrada
     *            nuevo libro entrada
     */
    public void setLibroEntrada(final LibroEntity libroEntrada) {
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

    /**
     * Obtiene etiquetas.
     *
     * @return etiquetas
     */
    public Set<EtiquetaEntity> getEtiquetas() {
        return this.etiquetas;
    }

    /**
     * Establece etiquetas.
     *
     * @param etiquetas
     *            nuevo etiquetas
     */
    public void setEtiquetas(final Set<EtiquetaEntity> etiquetas) {
        this.etiquetas = etiquetas;
    }

    /**
     * Obtiene imagen destacada.
     *
     * @return imagen destacada
     */
    public String getImagenDestacada() {
        return this.imagenDestacada;
    }

    /**
     * Establece imagen destacada.
     *
     * @param imagenDestacada
     *            nuevo imagen destacada
     */
    public void setImagenDestacada(final String imagenDestacada) {
        this.imagenDestacada = imagenDestacada;
    }

    /**
     * Obtiene comentarios.
     *
     * @return comentarios
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * Establece comentarios.
     *
     * @param comentarios
     *            nuevo comentarios
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaEntity)) {
            return false;
        }
        final EntradaEntity castOther = (EntradaEntity) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId)
                .append(this.entradaAutor, castOther.entradaAutor).append(this.urlEntrada, castOther.urlEntrada)
                .append(this.tipoEntrada, castOther.tipoEntrada).append(this.tituloEntrada, castOther.tituloEntrada)
                .append(this.contenidoEntrada, castOther.contenidoEntrada)
                .append(this.resumenEntrada, castOther.resumenEntrada)
                .append(this.estadoEntrada, castOther.estadoEntrada)
                .append(this.permitirComentarios, castOther.permitirComentarios)
                .append(this.padreEntrada, castOther.padreEntrada).append(this.libroEntrada, castOther.libroEntrada)
                .append(this.numeroComentarios, castOther.numeroComentarios).append(this.orden, castOther.orden)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).append(this.entradaAutor).append(this.urlEntrada)
                .append(this.tipoEntrada).append(this.tituloEntrada).append(this.contenidoEntrada)
                .append(this.resumenEntrada).append(this.estadoEntrada).append(this.permitirComentarios)
                .append(this.padreEntrada).append(this.libroEntrada).append(this.numeroComentarios).append(this.orden)
                .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("entradaAutor", this.entradaAutor)
                .append("urlEntrada", this.urlEntrada).append("tipoEntrada", this.tipoEntrada)
                .append("tituloEntrada", this.tituloEntrada).append("contenidoEntrada", this.contenidoEntrada)
                .append("resumenEntrada", this.resumenEntrada).append("estadoEntrada", this.estadoEntrada)
                .append("permitirComentarios", this.permitirComentarios).append("padreEntrada", this.padreEntrada)
                .append("libroEntrada", this.libroEntrada).append("numeroComentarios", this.numeroComentarios)
                .append("orden", this.orden).toString();
    }

}
