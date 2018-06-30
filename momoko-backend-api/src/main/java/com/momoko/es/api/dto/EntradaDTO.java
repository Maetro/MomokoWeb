/**
 * EntradaDTO.java 11-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.api.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class EntradaDTO.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class EntradaDTO {

    /** The entrada id. */
    private Integer entradaId;

    /** The autor id. */
    private RedactorDTO redactor;

    /** The url entrada. */
    private String urlEntrada;

    /** The url entrada. */
    private String urlAntigua;

    /** The tipo entrada. */
    private Integer tipoEntrada;

    /** The tipo entrada string. */
    private String tipoEntradaString;

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
    private List<LibroDTO> librosEntrada;

    /** The libro entrada. */
    private List<SagaDTO> sagasEntrada;

    /** The etiquetas. */
    private List<EtiquetaDTO> etiquetas;

    /** The imagen destacada. */
    private String imagenDestacada;

    /** The numero comentarios. */
    private Integer numeroComentarios;

    /** The titulo libro entrada. */
    private List<String> titulosLibrosEntrada;

    /** The nombres sagas entrada. */
    private List<String> nombresSagasEntrada;

    /** The orden. */
    private Integer orden;

    /** The frase descriptiva. */
    private String fraseDescriptiva;

    /** The url video. */
    private String urlVideo;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The fecha alta. */
    private Date fechaModificacion;

    /** The tiene galeria. */
    private boolean tieneGaleria = false;

    /** The editor nombre. */
    private String editorNombre;

    /** The con sidebar. */
    private boolean conSidebar;

    /** The en menu. */
    private boolean enMenu;

    /** The nombre menu libro. */
    private String nombreMenuLibro;

    /** The nombre menu libro. */
    private String urlMenuLibro;

    /** The visitas. */
    private Integer visitas;

    /** The json ld. */
    private String jsonLD;

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
    public RedactorDTO getRedactor() {
        return this.redactor;
    }

    /**
     * Establece autor.
     *
     * @param autor
     *            nuevo autor
     */
    public void setRedactor(final RedactorDTO redactor) {
        this.redactor = redactor;
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
     * Gets the url antigua.
     *
     * @return the url antigua
     */
    public String getUrlAntigua() {
        return this.urlAntigua;
    }

    /**
     * Sets the url antigua.
     *
     * @param urlAntigua
     *            the new url antigua
     */
    public void setUrlAntigua(final String urlAntigua) {
        this.urlAntigua = urlAntigua;
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
    public List<LibroDTO> getLibrosEntrada() {
        return this.librosEntrada;
    }

    /**
     * Establece libro entrada id.
     *
     * @param librosEntrada
     *            the new libros entrada
     */
    public void setLibrosEntrada(final List<LibroDTO> librosEntrada) {
        this.librosEntrada = librosEntrada;
    }

    public List<SagaDTO> getSagasEntrada() {
        return this.sagasEntrada;
    }

    public void setSagasEntrada(final List<SagaDTO> sagasEntrada) {
        this.sagasEntrada = sagasEntrada;
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
    public List<EtiquetaDTO> getEtiquetas() {
        return this.etiquetas;
    }

    /**
     * Establece etiquetas.
     *
     * @param etiquetas
     *            nuevo etiquetas
     */
    public void setEtiquetas(final List<EtiquetaDTO> etiquetas) {
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
     * Gets the titulo libro entrada.
     *
     * @return the titulo libro entrada
     */
    public List<String> getTitulosLibrosEntrada() {
        return this.titulosLibrosEntrada;
    }

    /**
     * Sets the titulo libro entrada.
     *
     * @param titulosLibrosEntrada
     *            the new titulos libros entrada
     */
    public void setTitulosLibrosEntrada(final List<String> titulosLibrosEntrada) {
        this.titulosLibrosEntrada = titulosLibrosEntrada;
    }

    /**
     * Gets the frase descriptiva.
     *
     * @return the frase descriptiva
     */
    public String getFraseDescriptiva() {
        return this.fraseDescriptiva;
    }

    /**
     * Sets the frase descriptiva.
     *
     * @param fraseDescriptiva
     *            the new frase descriptiva
     */
    public void setFraseDescriptiva(final String fraseDescriptiva) {
        this.fraseDescriptiva = fraseDescriptiva;
    }

    /**
     * Gets the tipo entrada string.
     *
     * @return the tipo entrada string
     */
    public String getTipoEntradaString() {
        return this.tipoEntradaString;
    }

    /**
     * Sets the tipo entrada string.
     *
     * @param tipoEntradaString
     *            the new tipo entrada string
     */
    public void setTipoEntradaString(final String tipoEntradaString) {
        this.tipoEntradaString = tipoEntradaString;
    }

    /**
     * Gets the url video.
     *
     * @return the url video
     */
    public String getUrlVideo() {
        return this.urlVideo;
    }

    /**
     * Sets the url video.
     *
     * @param urlVideo
     *            the new url video
     */
    public void setUrlVideo(final String urlVideo) {
        this.urlVideo = urlVideo;
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
     * Checks if is tiene galeria.
     *
     * @return true, if is tiene galeria
     */
    public boolean isTieneGaleria() {
        return this.tieneGaleria;
    }

    /**
     * Sets the tiene galeria.
     *
     * @param tieneGaleria
     *            the new tiene galeria
     */
    public void setTieneGaleria(final boolean tieneGaleria) {
        this.tieneGaleria = tieneGaleria;
    }

    /**
     * Gets the editor nombre.
     *
     * @return the editor nombre
     */
    public String getEditorNombre() {
        return this.editorNombre;
    }

    /**
     * Sets the editor nombre.
     *
     * @param editorNombre
     *            the new editor nombre
     */
    public void setEditorNombre(final String editorNombre) {
        this.editorNombre = editorNombre;
    }

    /**
     * Checks if is con sidebar.
     *
     * @return true, if is con sidebar
     */
    public boolean isConSidebar() {
        return this.conSidebar;
    }

    /**
     * Sets the con sidebar.
     *
     * @param conSidebar
     *            the new con sidebar
     */
    public void setConSidebar(final boolean conSidebar) {
        this.conSidebar = conSidebar;
    }

    /**
     * Checks if is en menu.
     *
     * @return true, if is en menu
     */
    public boolean isEnMenu() {
        return this.enMenu;
    }

    /**
     * Sets the en menu.
     *
     * @param enMenu
     *            the new en menu
     */
    public void setEnMenu(final boolean enMenu) {
        this.enMenu = enMenu;
    }

    /**
     * Gets the nombre menu libro.
     *
     * @return the nombre menu libro
     */
    public String getNombreMenuLibro() {
        return this.nombreMenuLibro;
    }

    /**
     * Sets the nombre menu libro.
     *
     * @param nombreMenuLibro
     *            the new nombre menu libro
     */
    public void setNombreMenuLibro(final String nombreMenuLibro) {
        this.nombreMenuLibro = nombreMenuLibro;
    }

    /**
     * Gets the url menu libro.
     *
     * @return the url menu libro
     */
    public String getUrlMenuLibro() {
        return this.urlMenuLibro;
    }

    /**
     * Sets the url menu libro.
     *
     * @param urlMenuLibro
     *            the new url menu libro
     */
    public void setUrlMenuLibro(final String urlMenuLibro) {
        this.urlMenuLibro = urlMenuLibro;
    }

    /**
     * Gets the visitas.
     *
     * @return the visitas
     */
    public Integer getVisitas() {
        return this.visitas;
    }

    /**
     * Sets the visitas.
     *
     * @param visitas
     *            the new visitas
     */
    public void setVisitas(final Integer visitas) {
        this.visitas = visitas;
    }

    public String getJsonLD() {
        return this.jsonLD;
    }

    public void setJsonLD(final String jsonLD) {
        this.jsonLD = jsonLD;
    }

    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<String> getNombresSagasEntrada() {
        return this.nombresSagasEntrada;
    }

    public void setNombresSagasEntrada(final List<String> nombresSagasEntrada) {
        this.nombresSagasEntrada = nombresSagasEntrada;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaDTO)) {
            return false;
        }
        final EntradaDTO castOther = (EntradaDTO) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId).append(this.redactor, castOther.redactor)
                .append(this.urlEntrada, castOther.urlEntrada).append(this.tipoEntrada, castOther.tipoEntrada)
                .append(this.tituloEntrada, castOther.tituloEntrada)
                .append(this.contenidoEntrada, castOther.contenidoEntrada)
                .append(this.resumenEntrada, castOther.resumenEntrada)
                .append(this.estadoEntrada, castOther.estadoEntrada)
                .append(this.permitirComentarios, castOther.permitirComentarios)
                .append(this.padreEntrada, castOther.padreEntrada).append(this.librosEntrada, castOther.librosEntrada)
                .append(this.etiquetas, castOther.etiquetas).append(this.imagenDestacada, castOther.imagenDestacada)
                .append(this.numeroComentarios, castOther.numeroComentarios).append(this.orden, castOther.orden)
                .append(this.editorNombre, castOther.editorNombre).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).append(this.redactor).append(this.urlEntrada)
                .append(this.tipoEntrada).append(this.tituloEntrada).append(this.contenidoEntrada)
                .append(this.resumenEntrada).append(this.estadoEntrada).append(this.permitirComentarios)
                .append(this.padreEntrada).append(this.librosEntrada).append(this.etiquetas)
                .append(this.imagenDestacada).append(this.numeroComentarios).append(this.orden)
                .append(this.editorNombre).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("redactor", this.redactor)
                .append("urlEntrada", this.urlEntrada).append("tipoEntrada", this.tipoEntrada)
                .append("tituloEntrada", this.tituloEntrada).append("contenidoEntrada", this.contenidoEntrada)
                .append("resumenEntrada", this.resumenEntrada).append("estadoEntrada", this.estadoEntrada)
                .append("permitirComentarios", this.permitirComentarios).append("padreEntrada", this.padreEntrada)
                .append("libroEntrada", this.librosEntrada).append("etiquetas", this.etiquetas)
                .append("imagenDestacada", this.imagenDestacada).append("numeroComentarios", this.numeroComentarios)
                .append("orden", this.orden).toString();
    }

}
