/**
 * EditorialServiceImpl.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.momoko.es.api.dto.EditorialDTO;

import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;
import com.momoko.es.jpa.entry.entity.EntradaEntity;
import com.momoko.es.jpa.publisher.EditorialEntity;
import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.score.PuntuacionEntity;
import com.momoko.es.jpa.model.repository.EditorialRepository;
import com.momoko.es.jpa.model.repository.PuntuacionRepository;
import com.momoko.es.jpa.publisher.EditorialService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import com.momoko.es.jpa.model.util.MomokoUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Override
    public List<EditorialDTO> recuperarEditoriales() {
        final List<EditorialDTO> editoriales = new ArrayList<>();
        final Iterable<EditorialEntity> listaEditoriales = this.editorialRepository.findAll();
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        for (final EditorialEntity editorialEntity : listaEditoriales) {
            final EditorialDTO editorialDTO = EntityToDTOAdapter.adaptarEditorial(editorialEntity);
            if (editorialDTO.getImagenCabeceraEditorial() != null) {
                editorialDTO.setImagenCabeceraEditorial(imageServer + editorialDTO.getImagenCabeceraEditorial());
            }
            if (editorialDTO.getImagenEditorial() != null) {
                editorialDTO.setImagenEditorial(imageServer + editorialDTO.getImagenEditorial());
            }
            editorialDTO
                    .setInfoAdicional(ConversionUtils.deJSONToInfoAdicionalDTO(editorialEntity.getInfoAdicionalJSON()));
            editoriales.add(editorialDTO);

        }
        return editoriales;
    }

    @Override
    @Transactional
    public EditorialDTO guardarEditorial(final EditorialDTO editorial) {
        EditorialDTO editorialDTO = null;
        if (editorial.getEditorialId() != null) {
            editorialDTO = actualizarEditorial(editorial);
        } else {
            editorialDTO = nuevaEditorial(editorial);
        }
        return editorialDTO;

    }

    private EditorialDTO nuevaEditorial(final EditorialDTO editorial) {
        EditorialEntity entity = DTOToEntityAdapter.adaptarEditorial(editorial);
        entity = anadirDatosCreacionEditorial(entity);
        final EditorialEntity editorialEntity = this.editorialRepository.save(entity);
        return EntityToDTOAdapter.adaptarEditorial(editorialEntity);
    }

    private EditorialDTO actualizarEditorial(final EditorialDTO editorial) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        EditorialEntity editorialEntity = this.editorialRepository.findById(editorial.getEditorialId()).orElse(null);
        editorialEntity.setFechaModificacion(Calendar.getInstance().getTime());
        editorialEntity.setUsuarioModificacion(currentPrincipalName);
        editorialEntity.setUrlEditorial(editorial.getUrlEditorial());
        editorialEntity.setNombreEditorial(editorial.getNombreEditorial());
        editorialEntity.setImagenEditorial(MomokoUtils.soloNombreYCarpeta(editorial.getImagenEditorial()));
        editorialEntity.setDescripcionEditorial(editorial.getDescripcionEditorial());
        editorialEntity.setWebEditorial(editorial.getWebEditorial());
        editorialEntity.setInformacionDeContacto(editorial.getInformacionDeContacto());
        editorialEntity
                .setImagenCabeceraEditorial(MomokoUtils.soloNombreYCarpeta(editorial.getImagenCabeceraEditorial()));
        try {
            editorialEntity
                    .setInfoAdicionalJSON(ConversionUtils.deInfoAdicionalDTOToJSON(editorial.getInfoAdicional()));
        } catch (final JsonParseException e) {
            e.printStackTrace();
        } catch (final JsonMappingException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.editorialRepository.save(editorialEntity);
        return EntityToDTOAdapter.adaptarEditorial(editorialEntity);
    }

    @Override
    public EditorialEntity obtenerEditorialOCrear(final EditorialEntity editorialEntity) {
        EditorialEntity editorialBD = null;
        if (editorialEntity == null) {
            throw new RuntimeException("La editorial es nula");
        }
        if (editorialEntity.getEditorialId() != null) {
            editorialBD = this.editorialRepository.findById(editorialEntity.getEditorialId()).orElse(null);
        } else if (editorialEntity.getUrlEditorial() != null) {
            editorialBD = this.editorialRepository.findFirstByUrlEditorial(editorialEntity.getUrlEditorial());
        } else if (editorialEntity.getNombreEditorial() != null) {
            editorialBD = this.editorialRepository.findFirstByNombreEditorial(editorialEntity.getNombreEditorial());
            if (editorialBD == null) {
                editorialBD = anadirDatosCreacionEditorial(editorialEntity);
                editorialBD.setUrlEditorial(ConversionUtils.toSlug(editorialBD.getNombreEditorial()));
                editorialBD = this.editorialRepository.save(editorialBD);
            }
        } else {
            throw new RuntimeException("La editorial es nula");
        }
        return editorialBD;
    }

    private EditorialEntity anadirDatosCreacionEditorial(final EditorialEntity entity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        entity.setFechaAlta(Calendar.getInstance().getTime());
        entity.setUsuarioAlta(currentPrincipalName);
        return entity;
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosEditorial(final String urlElemento, final int numeroElementos,
            final Integer numeroPagina) {
        final List<LibroEntity> librosEditorial = this.editorialRepository
                .findLibrosByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(urlElemento,
                        PageRequest.of(numeroPagina - 1, numeroElementos));
        final List<Integer> listaLibrosIds = new ArrayList<>();
        for (final LibroEntity libroEntity : librosEditorial) {
            listaLibrosIds.add(libroEntity.getLibroId());
        }

        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(librosEditorial)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }

        }
        final List<LibroSimpleDTO> obtenerLibrosBasicos = ConversionUtils.obtenerLibrosBasicos(librosEditorial, null);

        for (final LibroSimpleDTO libroSimpleDTO : obtenerLibrosBasicos) {
            if (libroSimpleDTO.getPortada() != null) {
                try {
                    libroSimpleDTO.setPortada(
                            this.almacenImagenes.obtenerMiniatura(libroSimpleDTO.getPortada(), 240, 350, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return obtenerLibrosBasicos;
    }

    @Override
    public EditorialDTO obtenerEditorialByUrl(final String urlEditorial) {
        final EditorialEntity editorial = this.editorialRepository.findFirstByUrlEditorial(urlEditorial);
        final EditorialDTO editorialDTO = EntityToDTOAdapter.adaptarEditorial(editorial);
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        if (editorialDTO.getImagenCabeceraEditorial() != null) {
            editorialDTO.setImagenCabeceraEditorial(imageServer + editorialDTO.getImagenCabeceraEditorial());
        } else {
            editorialDTO.setImagenCabeceraEditorial("/assets/style/images/art/parallax2.jpg");
        }
        if (editorialDTO.getImagenEditorial() != null) {
            editorialDTO.setImagenEditorial(imageServer + editorialDTO.getImagenEditorial());
        }
        return editorialDTO;
    }

    @Override
    public Integer obtenerNumeroLibrosEditorial(final String urlEditorial) {
        final Long numeroLibros = this.editorialRepository
                .findNumberEntradasByEditorialURLsAndFechaBajaIsNull(urlEditorial);
        return numeroLibros.intValue();
    }

    @Override
    public List<EntradaSimpleDTO> obtenerUltimasEntradasEditorial(final String urlElemento, final int numeroElementos,
            final Integer numeroPagina) {
        final List<EntradaEntity> entradasEditorial = this.editorialRepository
                .findEntradasByEditorialURLsAndFechaBajaIsNullOrderByFechaAltaDesc(urlElemento,
                        PageRequest.of(numeroPagina - 1, numeroElementos));
        final List<EntradaSimpleDTO> entradasSimples = ConversionUtils.obtenerEntradasBasicas(entradasEditorial, false);

        for (final EntradaSimpleDTO entradaSimpleDTO : entradasSimples) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 350, 253, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return entradasSimples;
    }

    @Override
    public List<ErrorCreacionEditorial> validarEditorial(EditorialDTO editorialDTO) {
        return null;
    }

}
