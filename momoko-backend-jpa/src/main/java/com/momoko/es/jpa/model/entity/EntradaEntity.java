/**
 * EntradaEntity.java 11-oct-2017
 */
package com.momoko.es.jpa.model.entity;

import com.momoko.es.api.enums.EntryStatusEnum;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.jpa.common.entity.AuditableEntity;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "entrada", indexes = {@Index(name = "urlEntradaIndex", columnList = "urlEntrada", unique = true),
                                    @Index(name = "entryTypeIndex", columnList = "entry_type", unique = false)})
public class EntradaEntity extends AuditableEntity implements Comparable<EntradaEntity> {

    private @Id
    @GeneratedValue
    Integer entradaId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity entradaAutor;

    /** The url entrada. */
    private String urlEntrada;

    /** The tipo entrada. */
    private Integer tipoEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", columnDefinition = "VARCHAR(15)")
    private EntryTypeEnum entryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_status", columnDefinition = "VARCHAR(15)")
    private EntryStatusEnum entryStatus;

    /** The titulo entrada. */
    private String tituloEntrada;

    /** The contenido entrada. */
    @Column(name = "contenido_entrada", columnDefinition = "TEXT")
    private String contenidoEntrada;

    /** The resumen entrada. */
    private String resumenEntrada;

    /** The frase descriptiva. */
    private String fraseDescriptiva;

    /** The estado entrada. */
    private Integer estadoEntrada;

    /** The permitir comentarios. */
    private Boolean permitirComentarios;

    /** The libro entrada. */
    @ManyToMany
    @JoinTable(name = "entrada_libro", joinColumns = @JoinColumn(name = "entrada_id", referencedColumnName = "entradaId"), inverseJoinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "libroId"))
    private List<LibroEntity> librosEntrada;

    /** The libro entrada. */
    @ManyToMany
    @JoinTable(name = "entrada_saga", joinColumns = @JoinColumn(name = "entrada_id", referencedColumnName = "entradaId"), inverseJoinColumns = @JoinColumn(name = "saga_id", referencedColumnName = "sagaId"))
    private List<SagaEntity> sagasEntrada;

    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentarios;

    /** The genero id. */
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "entrada_etiqueta", joinColumns = @JoinColumn(name = "entrada_id", referencedColumnName = "entradaId"), inverseJoinColumns = @JoinColumn(name = "etiqueta_id", referencedColumnName = "etiquetaId"))
    private Set<EtiquetaEntity> etiquetas;

    private String imagenDestacada;

    /** The con sidebar. */
    private boolean conSidebar;

    /** The en menu. */
    private boolean enMenu;

    /** The nombre menu libro. */
    private String nombreMenuLibro;

    /** The nombre menu libro. */
    private String urlMenuLibro;

    /** The url antigua. */
    private String urlAntigua;

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

    public EntryTypeEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryTypeEnum entryType) {
        this.entryType = entryType;
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
     * Obtiene libro entrada.
     *
     * @return libro entrada
     */
    public List<LibroEntity> getLibrosEntrada() {
        return this.librosEntrada;
    }

    /**
     * Establece libro entrada.
     *
     * @param librosEntrada
     *            the new libros entrada
     */
    public void setLibrosEntrada(final List<LibroEntity> librosEntrada) {
        this.librosEntrada = librosEntrada;
    }

    /**
     * Gets the sagas entrada.
     *
     * @return the sagas entrada
     */
    public List<SagaEntity> getSagasEntrada() {
        return this.sagasEntrada;
    }

    /**
     * Sets the sagas entrada.
     *
     * @param sagasEntrada
     *            the new sagas entrada
     */
    public void setSagasEntrada(final List<SagaEntity> sagasEntrada) {
        this.sagasEntrada = sagasEntrada;
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
        return this.comentarios;
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
     * Establece comentarios.
     *
     * @param comentarios
     *            nuevo comentarios
     */
    public void setComentarios(final List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public EntryStatusEnum getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(EntryStatusEnum entryStatus) {
        this.entryStatus = entryStatus;
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("entradaAutor", this.entradaAutor)
                .append("urlEntrada", this.urlEntrada).append("tipoEntrada", this.tipoEntrada)
                .append("tituloEntrada", this.tituloEntrada).append("contenidoEntrada", this.contenidoEntrada)
                .append("resumenEntrada", this.resumenEntrada).append("estadoEntrada", this.estadoEntrada)
                .append("permitirComentarios", this.permitirComentarios)
                .append("librosEntrada", this.librosEntrada).toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaEntity)) {
            return false;
        }
        final EntradaEntity castOther = (EntradaEntity) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId).isEquals();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).toHashCode();
    }

    @Override
    public int compareTo(EntradaEntity o) {
        return new CompareToBuilder().append(o.getCreatedDate(), this.getCreatedDate()).toComparison();
    }
}
