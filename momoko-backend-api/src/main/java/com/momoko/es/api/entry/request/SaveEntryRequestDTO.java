package com.momoko.es.api.entry.request;

import com.momoko.es.api.dto.EtiquetaDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SaveEntryRequestDTO implements Serializable {

    private Integer entradaId;
    private String tituloEntrada;
    private String urlEntrada;
    private List<EtiquetaDTO> etiquetas;
    private String entryType;
    private List<String> titulosLibrosEntrada;
    private List<String> nombresSagasEntrada;
    private String editorNombre;
    private String imagenDestacada;
    private boolean permitirComentarios;
    private boolean conSidebar;
    private boolean enMenu;
    private String nombreMenuLibro;
    private String urlMenuLibro;

    private String contenidoEntrada;
    private String resumenEntrada;
    private String fraseDescriptiva;
    private String urlVideo;

    private Integer entryStatusId;
    private Date publishDate;


    public Integer getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Integer entradaId) {
        this.entradaId = entradaId;
    }

    public String getTituloEntrada() {
        return tituloEntrada;
    }

    public void setTituloEntrada(String tituloEntrada) {
        this.tituloEntrada = tituloEntrada;
    }

    public String getUrlEntrada() {
        return urlEntrada;
    }

    public void setUrlEntrada(String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    public List<EtiquetaDTO> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaDTO> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public List<String> getTitulosLibrosEntrada() {
        return titulosLibrosEntrada;
    }

    public void setTitulosLibrosEntrada(List<String> titulosLibrosEntrada) {
        this.titulosLibrosEntrada = titulosLibrosEntrada;
    }

    public List<String> getNombresSagasEntrada() {
        return nombresSagasEntrada;
    }

    public void setNombresSagasEntrada(List<String> nombresSagasEntrada) {
        this.nombresSagasEntrada = nombresSagasEntrada;
    }

    public String getEditorNombre() {
        return editorNombre;
    }

    public void setEditorNombre(String editorNombre) {
        this.editorNombre = editorNombre;
    }

    public String getImagenDestacada() {
        return imagenDestacada;
    }

    public void setImagenDestacada(String imagenDestacada) {
        this.imagenDestacada = imagenDestacada;
    }

    public boolean isPermitirComentarios() {
        return permitirComentarios;
    }

    public void setPermitirComentarios(boolean permitirComentarios) {
        this.permitirComentarios = permitirComentarios;
    }

    public boolean isConSidebar() {
        return conSidebar;
    }

    public void setConSidebar(boolean conSidebar) {
        this.conSidebar = conSidebar;
    }

    public boolean isEnMenu() {
        return enMenu;
    }

    public void setEnMenu(boolean enMenu) {
        this.enMenu = enMenu;
    }

    public String getNombreMenuLibro() {
        return nombreMenuLibro;
    }

    public void setNombreMenuLibro(String nombreMenuLibro) {
        this.nombreMenuLibro = nombreMenuLibro;
    }

    public String getUrlMenuLibro() {
        return urlMenuLibro;
    }

    public void setUrlMenuLibro(String urlMenuLibro) {
        this.urlMenuLibro = urlMenuLibro;
    }

    public String getContenidoEntrada() {
        return contenidoEntrada;
    }

    public void setContenidoEntrada(String contenidoEntrada) {
        this.contenidoEntrada = contenidoEntrada;
    }

    public String getResumenEntrada() {
        return resumenEntrada;
    }

    public void setResumenEntrada(String resumenEntrada) {
        this.resumenEntrada = resumenEntrada;
    }

    public String getFraseDescriptiva() {
        return fraseDescriptiva;
    }

    public void setFraseDescriptiva(String fraseDescriptiva) {
        this.fraseDescriptiva = fraseDescriptiva;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public Integer getEntryStatusId() {
        return entryStatusId;
    }

    public void setEntryStatusId(Integer entryStatusId) {
        this.entryStatusId = entryStatusId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entradaId, tituloEntrada, urlEntrada, etiquetas, entryType, titulosLibrosEntrada, nombresSagasEntrada,
                editorNombre, imagenDestacada, permitirComentarios, conSidebar, enMenu, nombreMenuLibro, urlMenuLibro,
                contenidoEntrada, resumenEntrada, fraseDescriptiva, urlVideo, entryStatusId, publishDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final SaveEntryRequestDTO other = (SaveEntryRequestDTO) obj;
        return Objects.equals(this.entradaId, other.entradaId)
                && Objects.equals(this.tituloEntrada, other.tituloEntrada)
                && Objects.equals(this.urlEntrada, other.urlEntrada)
                && Objects.equals(this.etiquetas, other.etiquetas)
                && Objects.equals(this.entryType, other.entryType)
                && Objects.equals(this.titulosLibrosEntrada, other.titulosLibrosEntrada)
                && Objects.equals(this.nombresSagasEntrada, other.nombresSagasEntrada)
                && Objects.equals(this.editorNombre, other.editorNombre)
                && Objects.equals(this.imagenDestacada, other.imagenDestacada)
                && Objects.equals(this.permitirComentarios, other.permitirComentarios)
                && Objects.equals(this.conSidebar, other.conSidebar)
                && Objects.equals(this.enMenu, other.enMenu)
                && Objects.equals(this.nombreMenuLibro, other.nombreMenuLibro)
                && Objects.equals(this.urlMenuLibro, other.urlMenuLibro)
                && Objects.equals(this.contenidoEntrada, other.contenidoEntrada)
                && Objects.equals(this.resumenEntrada, other.resumenEntrada)
                && Objects.equals(this.fraseDescriptiva, other.fraseDescriptiva)
                && Objects.equals(this.urlVideo, other.urlVideo)
                && Objects.equals(this.entryStatusId, other.entryStatusId)
                && Objects.equals(this.publishDate, other.publishDate);
    }

    @Override
    public String toString() {
        return "SaveEntryRequestDTO{" +
                "entradaId=" + entradaId +
                ", tituloEntrada='" + tituloEntrada + '\'' +
                ", urlEntrada='" + urlEntrada + '\'' +
                ", etiquetas=" + etiquetas +
                ", entryType=" + entryType +
                ", titulosLibrosEntrada=" + titulosLibrosEntrada +
                ", nombresSagasEntrada=" + nombresSagasEntrada +
                ", editorNombre=" + editorNombre +
                ", imagenDestacada='" + imagenDestacada + '\'' +
                ", permitirComentarios=" + permitirComentarios +
                ", conSidebar=" + conSidebar +
                ", enMenu=" + enMenu +
                ", nombreMenuLibro='" + nombreMenuLibro + '\'' +
                ", urlMenuLibro='" + urlMenuLibro + '\'' +
                ", contenidoEntrada='" + contenidoEntrada + '\'' +
                ", resumenEntrada='" + resumenEntrada + '\'' +
                ", fraseDescriptiva='" + fraseDescriptiva + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", entryStatusId=" + entryStatusId +
                ", publishDate=" + publishDate +
                '}';
    }
}
