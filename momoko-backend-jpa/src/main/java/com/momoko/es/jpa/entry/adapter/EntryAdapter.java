package com.momoko.es.jpa.entry.adapter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.entry.dto.EntradaDTO;
import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.entry.entity.EntradaEntity;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import com.momoko.es.jpa.saga.SagaEntity;
import com.momoko.es.jpa.user.UsuarioEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter {

    private EntryAdapter() {
    }

    /**
     * Adaptar entrada.
     *
     * @param entradaDTO
     *            entrada DTO
     * @return the entrada entity
     */
    public static EntradaEntity adaptarEntrada(final EntradaDTO entradaDTO, final List<LibroDTO> librosEntrada,
                                               final List<SagaDTO> sagasEntrada, final UsuarioEntity autor) {
        final EntradaEntity entradaEntity = new EntradaEntity();
        entradaEntity.setEntradaId(entradaDTO.getEntradaId());
        entradaEntity.setContenidoEntrada(entradaDTO.getContenidoEntrada());
        if (autor != null) {
            entradaEntity.setEntradaAutor(autor);
        }
        entradaEntity.setEntryStatus(entradaDTO.getEntryStatus());
        if (librosEntrada != null) {
            entradaEntity.setLibrosEntrada(DTOToEntityAdapter.adaptarLibros(librosEntrada));
        }
        if (sagasEntrada != null) {
            entradaEntity.setSagasEntrada(DTOToEntityAdapter.adaptarSagas(sagasEntrada));
        }
        entradaEntity.setPermitirComentarios(entradaDTO.getPermitirComentarios());
        entradaEntity.setResumenEntrada(entradaDTO.getResumenEntrada());
        entradaEntity.setEntryType(entradaDTO.getEntryType());
        entradaEntity.setTituloEntrada(entradaDTO.getTituloEntrada());
        entradaEntity.setUrlEntrada(entradaDTO.getUrlEntrada());
        entradaEntity.setImagenDestacada(entradaDTO.getImagenDestacada());
        entradaEntity.setEtiquetas(DTOToEntityAdapter.adaptarEtiquetas(entradaDTO.getEtiquetas()));
        entradaEntity.setFraseDescriptiva(entradaDTO.getFraseDescriptiva());
        entradaEntity.setEnMenu(entradaDTO.isEnMenu());
        entradaEntity.setConSidebar(entradaDTO.isConSidebar());
        entradaEntity.setUrlMenuLibro(entradaDTO.getUrlMenuLibro());
        entradaEntity.setNombreMenuLibro(entradaDTO.getNombreMenuLibro());
        return entradaEntity;
    }


    public static List<EntradaDTO> adaptarEntradas(final List<EntradaEntity> entradaEntities) {
        final List<EntradaDTO> entradas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entradaEntities)) {
            for (final EntradaEntity entradaEntity : entradaEntities) {
                entradas.add(adaptarEntrada(entradaEntity));
            }
        }
        return entradas;
    }

    /**
     * Adaptar entrada.
     *
     * @param entradaEntity
     *            entrada DTO
     * @return the entrada entity
     */
    public static EntradaDTO adaptarEntrada(final EntradaEntity entradaEntity) {
        final EntradaDTO entradaDTO = new EntradaDTO();
        entradaDTO.setEntradaId(entradaEntity.getEntradaId());
        entradaDTO.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        entradaDTO.setRedactor(ConversionUtils.getRedactorFromUsuario(entradaEntity.getEntradaAutor()));
        entradaDTO.setEntryStatus(entradaEntity.getEntryStatus());
        if (entradaEntity.getLibrosEntrada() != null) {
            entradaDTO.setLibrosEntrada(EntityToDTOAdapter.adaptarLibros(entradaEntity.getLibrosEntrada()));
        }
        if (entradaEntity.getSagasEntrada() != null) {
            entradaDTO.setSagasEntrada(EntityToDTOAdapter.adaptarSagas(entradaEntity.getSagasEntrada()));
        }
        entradaDTO.setPermitirComentarios(entradaEntity.getPermitirComentarios());
        entradaDTO.setResumenEntrada(entradaEntity.getResumenEntrada());
        if (StringUtils.isNotBlank(entradaEntity.getFraseDescriptiva())) {
            entradaDTO.setFraseDescriptiva(entradaEntity.getFraseDescriptiva());
        } else if (StringUtils.isNotBlank(entradaEntity.getResumenEntrada())) {
            entradaDTO
                    .setFraseDescriptiva(ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getResumenEntrada(), 200));
        } else {
            entradaDTO
                    .setResumenEntrada(ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getContenidoEntrada(), 500));
            entradaDTO.setFraseDescriptiva(
                    ConversionUtils.limpiarHTMLyRecortar(entradaEntity.getContenidoEntrada(), 200));
        }

        entradaDTO.setEntryType(entradaEntity.getEntryType());
        entradaDTO.setFechaAlta(entradaEntity.getCreatedDate());
        entradaDTO.setFechaModificacion(entradaEntity.getModifiedDate());
        entradaDTO.setTituloEntrada(entradaEntity.getTituloEntrada());
        entradaDTO.setEditorNombre(entradaEntity.getEntradaAutor().getUsuarioNick());
        entradaDTO.setUrlEntrada(entradaEntity.getUrlEntrada());
        entradaDTO.setUrlAntigua(entradaEntity.getUrlAntigua());
        entradaDTO.setEtiquetas(EntityToDTOAdapter.adaptarEtiquetas(new ArrayList(entradaEntity.getEtiquetas())));

        entradaDTO.setImagenDestacada(entradaEntity.getImagenDestacada());
        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            final List<String> titulos = new ArrayList<>();
            for (final LibroEntity libroEntity : entradaEntity.getLibrosEntrada()) {
                titulos.add(libroEntity.getTitulo());
            }
            entradaDTO.setTitulosLibrosEntrada(titulos);
        }
        if (CollectionUtils.isNotEmpty(entradaEntity.getSagasEntrada())) {
            final List<String> nombres = new ArrayList<>();
            for (final SagaEntity sagaEntity : entradaEntity.getSagasEntrada()) {
                nombres.add(sagaEntity.getNombre());
            }
            entradaDTO.setNombresSagasEntrada(nombres);
        }
        entradaDTO.setEnMenu(entradaEntity.isEnMenu());
        entradaDTO.setConSidebar(entradaEntity.isConSidebar());
        entradaDTO.setUrlMenuLibro(entradaEntity.getUrlMenuLibro());
        entradaDTO.setNombreMenuLibro(entradaEntity.getNombreMenuLibro());
        return entradaDTO;
    }
}
