/**
 * LibroDTO.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class LibroDTO.
 */
public class LibroDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8006086179924231655L;

    /** The libro id. */
    private Integer libroId;

    /** The autor id. */
    private Set<AutorDTO> autores;

    /** The saga id. */
    private SagaDTO saga;

    /** The editorial id. */
    private EditorialDTO editorial;

    /** The genero id. */
    private Set<GeneroDTO> generos;

    /** The titulo. */
    private String titulo;

    /** The titulo original. */
    private String tituloOriginal;

    /** The ano edicion. */
    private Integer anoEdicion;

    /** The ano publicacion. */
    private Integer anoPublicacion;

    /** The numero paginas. */
    private Integer numeroPaginas;

    /** The cita libro. */
    private String citaLibro;

    /** The resumen. */
    private String resumen;

    /** The enlace amazon. */
    private String enlaceAmazon;

    /** The url imagen. */
    private String urlImagen;

    /** The url libro. */
    private String urlLibro;

    /** The entradas. */
    private List<DatoEntradaDTO> entradasLibro;

    /** The portada width. */
    private Integer portadaWidth;

    /** The portada height. */
    private Integer portadaHeight;

    /** The puntuaciones. */
    private Integer mediaPuntuacion;

    /** The nota momoko. */
    private BigDecimal notaMomoko;

    /** The comentario nota momoko. */
    private String comentarioNotaMomoko;

    /** The fecha alta. */
    private Date fechaAlta;

    /** The autores string. */
    private String autoresString;

    /** The generos string. */
    private String generosString;

    /** The visitas. */
    private Integer visitas;

    /** The orden saga. */
    private Integer ordenSaga;

    /**
     * Gets the libro id.
     *
     * @return the libro id
     */
    public Integer getLibroId() {
        return this.libroId;
    }

    /**
     * Sets the libro id.
     *
     * @param libroId
     *            the new libro id
     */
    public void setLibroId(final Integer libroId) {
        this.libroId = libroId;
    }

    /**
     * Gets the autores.
     *
     * @return the autores
     */
    public Set<AutorDTO> getAutores() {
        return this.autores;
    }

    /**
     * Sets the autores.
     *
     * @param autores
     *            the new autores
     */
    public void setAutores(final Set<AutorDTO> autores) {
        this.autores = autores;
    }

    /**
     * Gets the saga id.
     *
     * @return the saga id
     */
    public SagaDTO getSaga() {
        return this.saga;
    }

    /**
     * Sets the saga id.
     *
     * @param saga
     *            the new saga
     */
    public void setSaga(final SagaDTO saga) {
        this.saga = saga;
    }

    /**
     * Gets the editorial id.
     *
     * @return the editorial id
     */
    public EditorialDTO getEditorial() {
        return this.editorial;
    }

    /**
     * Sets the editorial.
     *
     * @param editorial
     *            the new editorial
     */
    public void setEditorial(final EditorialDTO editorial) {
        this.editorial = editorial;
    }

    /**
     * Gets the generos.
     *
     * @return the generos
     */
    public Set<GeneroDTO> getGeneros() {
        return this.generos;
    }

    /**
     * Gets the titulo original.
     *
     * @return the titulo original
     */
    public String getTituloOriginal() {
        return this.tituloOriginal;
    }

    /**
     * Sets the titulo original.
     *
     * @param tituloOriginal
     *            the new titulo original
     */
    public void setTituloOriginal(final String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    /**
     * Gets the ano publicacion.
     *
     * @return the ano publicacion
     */
    public Integer getAnoPublicacion() {
        return this.anoPublicacion;
    }

    /**
     * Sets the ano publicacion.
     *
     * @param anoPublicacion
     *            the new ano publicacion
     */
    public void setAnoPublicacion(final Integer anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    /**
     * Gets the numero paginas.
     *
     * @return the numero paginas
     */
    public Integer getNumeroPaginas() {
        return this.numeroPaginas;
    }

    /**
     * Sets the numero paginas.
     *
     * @param numeroPaginas
     *            the new numero paginas
     */
    public void setNumeroPaginas(final Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Sets the generos.
     *
     * @param generos
     *            the new generos
     */
    public void setGeneros(final Set<GeneroDTO> generos) {
        this.generos = generos;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the ano edicion.
     *
     * @return the ano edicion
     */
    public Integer getAnoEdicion() {
        return this.anoEdicion;
    }

    /**
     * Sets the ano edicion.
     *
     * @param anoEdicion
     *            the new ano edicion
     */
    public void setAnoEdicion(final Integer anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    /**
     * Gets the cita libro.
     *
     * @return the cita libro
     */
    public String getCitaLibro() {
        return this.citaLibro;
    }

    /**
     * Sets the cita libro.
     *
     * @param citaLibro
     *            the new cita libro
     */
    public void setCitaLibro(final String citaLibro) {
        this.citaLibro = citaLibro;
    }

    /**
     * Gets the resumen.
     *
     * @return the resumen
     */
    public String getResumen() {
        return this.resumen;
    }

    /**
     * Sets the resumen.
     *
     * @param resumen
     *            the new resumen
     */
    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    /**
     * Gets the enlace amazon.
     *
     * @return the enlace amazon
     */
    public String getEnlaceAmazon() {
        return this.enlaceAmazon;
    }

    /**
     * Sets the enlace amazon.
     *
     * @param enlaceAmazon
     *            the new enlace amazon
     */
    public void setEnlaceAmazon(final String enlaceAmazon) {
        this.enlaceAmazon = enlaceAmazon;
    }

    /**
     * Gets the url imagen.
     *
     * @return the url imagen
     */
    public String getUrlImagen() {
        return this.urlImagen;
    }

    /**
     * Sets the url imagen.
     *
     * @param urlImagen
     *            the new url imagen
     */
    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Gets the url libro.
     *
     * @return the url libro
     */
    public String getUrlLibro() {
        return this.urlLibro;
    }

    /**
     * Sets the url libro.
     *
     * @param urlLibro
     *            the new url libro
     */
    public void setUrlLibro(final String urlLibro) {
        this.urlLibro = urlLibro;
    }

    /**
     * Gets the entradas libro.
     *
     * @return the entradas libro
     */
    public List<DatoEntradaDTO> getEntradasLibro() {
        return this.entradasLibro;
    }

    /**
     * Sets the entradas libro.
     *
     * @param entradasLibro
     *            the new entradas libro
     */
    public void setEntradasLibro(final List<DatoEntradaDTO> entradasLibro) {
        this.entradasLibro = entradasLibro;
    }

    /**
     * Gets the portada height.
     *
     * @return the portada height
     */
    public Integer getPortadaHeight() {
        return this.portadaHeight;
    }

    /**
     * Sets the portada height.
     *
     * @param portadaHeight
     *            the new portada height
     */
    public void setPortadaHeight(final Integer portadaHeight) {
        this.portadaHeight = portadaHeight;
    }

    /**
     * Gets the portada width.
     *
     * @return the portada width
     */
    public Integer getPortadaWidth() {
        return this.portadaWidth;
    }

    /**
     * Sets the portada width.
     *
     * @param portadaWidth
     *            the new portada width
     */
    public void setPortadaWidth(final Integer portadaWidth) {
        this.portadaWidth = portadaWidth;
    }

    /**
     * Gets the nota momoko.
     *
     * @return the nota momoko
     */
    public BigDecimal getNotaMomoko() {
        return this.notaMomoko;
    }

    /**
     * Sets the nota momoko.
     *
     * @param notaMomoko
     *            the new nota momoko
     */
    public void setNotaMomoko(final BigDecimal notaMomoko) {
        this.notaMomoko = notaMomoko;
    }

    /**
     * Gets the comentario nota momoko.
     *
     * @return the comentario nota momoko
     */
    public String getComentarioNotaMomoko() {
        return this.comentarioNotaMomoko;
    }

    /**
     * Sets the comentario nota momoko.
     *
     * @param comentarioNotaMomoko
     *            the new comentario nota momoko
     */
    public void setComentarioNotaMomoko(final String comentarioNotaMomoko) {
        this.comentarioNotaMomoko = comentarioNotaMomoko;
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
     * Gets the autores string.
     *
     * @return the autores string
     */
    public String getAutoresString() {
        return this.autoresString;
    }

    /**
     * Sets the autores string.
     *
     * @param autoresString
     *            the new autores string
     */
    public void setAutoresString(final String autoresString) {
        this.autoresString = autoresString;
    }

    /**
     * Gets the generos string.
     *
     * @return the generos string
     */
    public String getGenerosString() {
        return this.generosString;
    }

    /**
     * Sets the generos string.
     *
     * @param generosString
     *            the new generos string
     */
    public void setGenerosString(final String generosString) {
        this.generosString = generosString;
    }

    /**
     * Gets the media puntuacion.
     *
     * @return the media puntuacion
     */
    public Integer getMediaPuntuacion() {
        return this.mediaPuntuacion;
    }

    /**
     * Sets the media puntuacion.
     *
     * @param mediaPuntuacion
     *            the new media puntuacion
     */
    public void setMediaPuntuacion(final Integer mediaPuntuacion) {
        this.mediaPuntuacion = mediaPuntuacion;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof LibroDTO)) {
            return false;
        }
        final LibroDTO castOther = (LibroDTO) other;
        return new EqualsBuilder().append(this.libroId, castOther.libroId).append(this.autores, castOther.autores)
                .append(this.saga, castOther.saga).append(this.editorial, castOther.editorial)
                .append(this.generos, castOther.generos).append(this.titulo, castOther.titulo)
                .append(this.tituloOriginal, castOther.tituloOriginal).append(this.anoEdicion, castOther.anoEdicion)
                .append(this.anoPublicacion, castOther.anoPublicacion)
                .append(this.numeroPaginas, castOther.numeroPaginas).append(this.citaLibro, castOther.citaLibro)
                .append(this.resumen, castOther.resumen).append(this.enlaceAmazon, castOther.enlaceAmazon)
                .append(this.urlImagen, castOther.urlImagen).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.libroId).append(this.autores).append(this.saga).append(this.editorial)
                .append(this.generos).append(this.titulo).append(this.tituloOriginal).append(this.anoEdicion)
                .append(this.anoPublicacion).append(this.numeroPaginas).append(this.citaLibro).append(this.resumen)
                .append(this.enlaceAmazon).append(this.urlImagen).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("libroId", this.libroId).append("autores", this.autores)
                .append("saga", this.saga).append("editorial", this.editorial).append("generos", this.generos)
                .append("titulo", this.titulo).append("tituloOriginal", this.tituloOriginal)
                .append("anoEdicion", this.anoEdicion).append("anoPublicacion", this.anoPublicacion)
                .append("numeroPaginas", this.numeroPaginas).append("citaLibro", this.citaLibro)
                .append("resumen", this.resumen).append("enlaceAmazon", this.enlaceAmazon)
                .append("urlImagen", this.urlImagen).toString();
    }

    /**
     * Gets the orden saga.
     *
     * @return the orden saga
     */
    public Integer getOrdenSaga() {
        return this.ordenSaga;
    }

    /**
     * Sets the orden saga.
     *
     * @param ordenSaga
     *            the new orden saga
     */
    public void setOrdenSaga(final Integer ordenSaga) {
        this.ordenSaga = ordenSaga;
    }

}
