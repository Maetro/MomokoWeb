/**
 * LibroEntity.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
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
 * The Class LibroEntity.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Entity
@Table(name = "libro", indexes = { @Index(name = "urlLibro", columnList = "urlLibro", unique = true) })
public class LibroEntity {

    /** The libro id. */
    private @Id @GeneratedValue Integer libroId;

    /** The autor id. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "libroId"), inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "autorId"))
    private Set<AutorEntity> autores;

    /** The saga id. */
    @ManyToOne
    @JoinColumn(nullable = true)
    private SagaEntity saga;

    /** The orden saga. */
    private Integer ordenSaga;

    /** The editorial id. */
    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private EditorialEntity editorial;

    /** The genero id. */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "libro_genero", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "libroId"), inverseJoinColumns = @JoinColumn(name = "generoId", referencedColumnName = "generoId"))
    private Set<GeneroEntity> generos;

    private String urlLibro;

    /** The titulo. */
    private String titulo;

    /** The ano edicion. */
    private Integer anoEdicion;

    /** The cita libro. */
    private String citaLibro;

    /** The resumen. */
    private String resumen;

    /** The enlace amazon. */
    private String enlaceAmazon;

    /** The url imagen. */
    private String urlImagen;

    /** The ano publicacion. */
    private Integer anoPublicacion;

    /** The numero paginas. */
    private Integer numeroPaginas;

    /** The titulo original. */
    private String tituloOriginal;

    /** The visitas. */
    private Integer visitas;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PuntuacionEntity> puntuaciones = new ArrayList<PuntuacionEntity>();

    @ManyToMany(mappedBy = "librosEntrada")
    private List<EntradaEntity> entradas;

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

    /**
     * Instantiates a new libro entity.
     */
    public LibroEntity() {
    }

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
    public Set<AutorEntity> getAutores() {
        return this.autores;
    }

    /**
     * Sets the autores.
     *
     * @param autores
     *            the new autores
     */
    public void setAutores(final Set<AutorEntity> autores) {
        this.autores = autores;
    }

    /**
     * Gets the saga id.
     *
     * @return the saga id
     */
    public SagaEntity getSaga() {
        return this.saga;
    }

    /**
     * Sets the saga id.
     *
     * @param saga
     *            the new saga id
     */
    public void setSaga(final SagaEntity saga) {
        this.saga = saga;
    }

    /**
     * Gets the editorial id.
     *
     * @return the editorial id
     */
    public EditorialEntity getEditorial() {
        return this.editorial;
    }

    /**
     * Sets the editorial id.
     *
     * @param editorialId
     *            the new editorial id
     */
    public void setEditorial(final EditorialEntity editorialId) {
        this.editorial = editorialId;
    }

    /**
     * Gets the generos.
     *
     * @return the generos
     */
    public Set<GeneroEntity> getGeneros() {
        return this.generos;
    }

    /**
     * Sets the generos.
     *
     * @param generos
     *            the new generos
     */
    public void setGeneros(final Set<GeneroEntity> generos) {
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
     * Obtiene puntuaciones.
     *
     * @return puntuaciones
     */
    public List<PuntuacionEntity> getPuntuaciones() {
        return this.puntuaciones;
    }

    /**
     * Establece puntuaciones.
     *
     * @param puntuaciones
     *            nuevo puntuaciones
     */
    public void setPuntuaciones(final List<PuntuacionEntity> puntuaciones) {
        this.puntuaciones = puntuaciones;
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

    /**
     * Gets the visitas.
     *
     * @return the visitas
     */
    public Integer getVisitas() {
        return this.visitas;
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
     * Gets the entradas.
     *
     * @return the entradas
     */
    public List<EntradaEntity> getEntradas() {
        return this.entradas;
    }

    /**
     * Sets the entradas.
     *
     * @param entradas
     *            the new entradas
     */
    public void setEntradas(final List<EntradaEntity> entradas) {
        this.entradas = entradas;
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
     * @param usuarioAlta
     *            the new usuario alta
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
     * @param fechaAlta
     *            the new fecha alta
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
     * @param usuarioModificacion
     *            the new usuario modificacion
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
     * @param fechaModificacion
     *            the new fecha modificacion
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
     * @param usuarioBaja
     *            the new usuario baja
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
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("libroId", this.libroId).append("autores", this.autores)
                .append("saga", this.saga).append("editorial", this.editorial).append("generos", this.generos)
                .append("titulo", this.titulo).append("anoEdicion", this.anoEdicion).append("citaLibro", this.citaLibro)
                .append("resumen", this.resumen).append("enlaceAmazon", this.enlaceAmazon)
                .append("urlImagen", this.urlImagen).append("anoPublicacion", this.anoPublicacion)
                .append("numeroPaginas", this.numeroPaginas).append("tituloOriginal", this.tituloOriginal).toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof LibroEntity)) {
            return false;
        }
        final LibroEntity castOther = (LibroEntity) other;
        return new EqualsBuilder().append(this.libroId, castOther.libroId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.libroId).toHashCode();
    }

}
