package com.momoko.es.api.dto;

import com.momoko.es.api.enums.EntryStatusEnum;
import com.momoko.es.api.enums.EntryTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class EntradaDTO {

    private Integer entradaId;
    private RedactorDTO redactor;
    private String urlEntrada;
    private String urlAntigua;
    private EntryTypeEnum entryType;
    private String tituloEntrada;
    private String contenidoEntrada;
    private String resumenEntrada;
    private EntryStatusEnum entryStatus;
    private Boolean permitirComentarios;
    private List<LibroDTO> librosEntrada;
    private List<SagaDTO> sagasEntrada;
    private List<EtiquetaDTO> etiquetas;
    private String imagenDestacada;
    private List<String> titulosLibrosEntrada;
    private List<String> nombresSagasEntrada;
    private String fraseDescriptiva;
    private String urlVideo;
    private Date fechaAlta;
    private Date fechaModificacion;
    private boolean tieneGaleria = false;
    private String editorNombre;
    private boolean conSidebar;
    private boolean enMenu;
    private String nombreMenuLibro;
    private String urlMenuLibro;
    private Integer visitas;
    private String jsonLD;
    private List<DatoEntradaDTO> datosEntrada;
    private Integer numeroComentarios;

    public Integer getEntradaId() {
        return this.entradaId;
    }

    public void setEntradaId(final Integer entradaId) {
        this.entradaId = entradaId;
    }

    public RedactorDTO getRedactor() {
        return this.redactor;
    }

    public void setRedactor(final RedactorDTO redactor) {
        this.redactor = redactor;
    }

    public String getUrlEntrada() {
        return this.urlEntrada;
    }

    public void setUrlEntrada(final String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    public String getUrlAntigua() {
        return this.urlAntigua;
    }

    public void setUrlAntigua(final String urlAntigua) {
        this.urlAntigua = urlAntigua;
    }

    public String getTituloEntrada() {
        return this.tituloEntrada;
    }

    public void setTituloEntrada(final String tituloEntrada) {
        this.tituloEntrada = tituloEntrada;
    }

    public String getContenidoEntrada() {
        return this.contenidoEntrada;
    }

    public void setContenidoEntrada(final String contenidoEntrada) {
        this.contenidoEntrada = contenidoEntrada;
    }

    public String getResumenEntrada() {
        return this.resumenEntrada;
    }

    public void setResumenEntrada(final String resumenEntrada) {
        this.resumenEntrada = resumenEntrada;
    }

    public EntryStatusEnum getEntryStatus() {
        return this.entryStatus;
    }

    public void setEntryStatus(final EntryStatusEnum entryStatus) {
        this.entryStatus = entryStatus;
    }

    public Boolean getPermitirComentarios() {
        return this.permitirComentarios;
    }

    public void setPermitirComentarios(final Boolean permitirComentarios) {
        this.permitirComentarios = permitirComentarios;
    }

    public List<LibroDTO> getLibrosEntrada() {
        return this.librosEntrada;
    }

    public void setLibrosEntrada(final List<LibroDTO> librosEntrada) {
        this.librosEntrada = librosEntrada;
    }

    public List<SagaDTO> getSagasEntrada() {
        return this.sagasEntrada;
    }

    public void setSagasEntrada(final List<SagaDTO> sagasEntrada) {
        this.sagasEntrada = sagasEntrada;
    }

    public List<EtiquetaDTO> getEtiquetas() {
        return this.etiquetas;
    }

    public void setEtiquetas(final List<EtiquetaDTO> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getImagenDestacada() {
        return this.imagenDestacada;
    }

    public void setImagenDestacada(final String imagenDestacada) {
        this.imagenDestacada = imagenDestacada;
    }

    public List<String> getTitulosLibrosEntrada() {
        return this.titulosLibrosEntrada;
    }

    public void setTitulosLibrosEntrada(final List<String> titulosLibrosEntrada) {
        this.titulosLibrosEntrada = titulosLibrosEntrada;
    }

    public String getFraseDescriptiva() {
        return this.fraseDescriptiva;
    }

    public void setFraseDescriptiva(final String fraseDescriptiva) {
        this.fraseDescriptiva = fraseDescriptiva;
    }

    public EntryTypeEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryTypeEnum entryType) {
        this.entryType = entryType;
    }

    public String getUrlVideo() {
        return this.urlVideo;
    }

    public void setUrlVideo(final String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isTieneGaleria() {
        return this.tieneGaleria;
    }

    public void setTieneGaleria(final boolean tieneGaleria) {
        this.tieneGaleria = tieneGaleria;
    }

    public String getEditorNombre() {
        return this.editorNombre;
    }

    public void setEditorNombre(final String editorNombre) {
        this.editorNombre = editorNombre;
    }

    public boolean isConSidebar() {
        return this.conSidebar;
    }

    public void setConSidebar(final boolean conSidebar) {
        this.conSidebar = conSidebar;
    }

    public boolean isEnMenu() {
        return this.enMenu;
    }

    public void setEnMenu(final boolean enMenu) {
        this.enMenu = enMenu;
    }

    public String getNombreMenuLibro() {
        return this.nombreMenuLibro;
    }

    public void setNombreMenuLibro(final String nombreMenuLibro) {
        this.nombreMenuLibro = nombreMenuLibro;
    }

    public String getUrlMenuLibro() {
        return this.urlMenuLibro;
    }

    public void setUrlMenuLibro(final String urlMenuLibro) {
        this.urlMenuLibro = urlMenuLibro;
    }

    public Integer getVisitas() {
        return this.visitas;
    }

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

    public List<DatoEntradaDTO> getDatosEntrada() {
        return this.datosEntrada;
    }

    public void setDatosEntrada(final List<DatoEntradaDTO> datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

    public Integer getNumeroComentarios() {
        return numeroComentarios;
    }

    public void setNumeroComentarios(Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }
    
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EntradaDTO)) {
            return false;
        }
        final EntradaDTO castOther = (EntradaDTO) other;
        return new EqualsBuilder().append(this.entradaId, castOther.entradaId).append(this.redactor, castOther.redactor)
                .append(this.entryType, castOther.entryType)
                .append(this.tituloEntrada, castOther.tituloEntrada)
                .append(this.contenidoEntrada, castOther.contenidoEntrada)
                .append(this.resumenEntrada, castOther.resumenEntrada)
                .append(this.entryStatus, castOther.entryStatus)
                .append(this.permitirComentarios, castOther.permitirComentarios)
                .append(this.librosEntrada, castOther.librosEntrada)
                .append(this.etiquetas, castOther.etiquetas).append(this.imagenDestacada, castOther.imagenDestacada)
                .append(this.editorNombre, castOther.editorNombre).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.entradaId).append(this.redactor).append(this.urlEntrada)
                .append(this.entryType).append(this.tituloEntrada).append(this.contenidoEntrada)
                .append(this.resumenEntrada).append(this.entryStatus).append(this.permitirComentarios)
                .append(this.librosEntrada).append(this.etiquetas).append(this.imagenDestacada)
                .append(this.editorNombre).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entradaId", this.entradaId).append("redactor", this.redactor)
                .append("urlEntrada", this.urlEntrada).append("tipoEntrada", this.entryType)
                .append("tituloEntrada", this.tituloEntrada).append("contenidoEntrada", this.contenidoEntrada)
                .append("resumenEntrada", this.resumenEntrada).append("entryStatus", this.entryStatus)
                .append("permitirComentarios", this.permitirComentarios)
                .append("libroEntrada", this.librosEntrada).append("etiquetas", this.etiquetas)
                .append("imagenDestacada", this.imagenDestacada).toString();
    }


}
