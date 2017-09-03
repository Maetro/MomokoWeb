/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.util.HashSet;
import java.util.Set;

import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.UserStatusEnum;
import com.momoko.es.backend.model.entity.AutorEntity;
import com.momoko.es.backend.model.entity.EditorialEntity;
import com.momoko.es.backend.model.entity.GeneroEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;

/**
 * The Class DTOToEntityAdapter.
 */
public final class DTOToEntityAdapter {

    /**
     * Adaptar usuario.
     *
     * @param nuevoUsuario
     *            the nuevo usuario
     * @return
     */
    public static UsuarioEntity adaptarUsuario(final UsuarioDTO nuevoUsuario) {
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuario_id(nuevoUsuario.getUsuarioId());
        usuario.setUsuario_login(nuevoUsuario.getLogin());
        usuario.setUsuario_contrasena(nuevoUsuario.getContrasena());
        usuario.setUsuarioEmail(nuevoUsuario.getEmail());
        usuario.setUsuario_fecha_registro(nuevoUsuario.getFechaRegistro());
        usuario.setUsuario_nick(nuevoUsuario.getNick());
        usuario.setUsuario_url(nuevoUsuario.getUrl());
        usuario.setUsuario_status(obtenerIdStatus(nuevoUsuario.getUsuarioStatus()));
        usuario.setUsuario_rol_id(nuevoUsuario.getUsuario_rol_id());
        return usuario;
    }

    /**
     * Obtener id status.
     *
     * @param usuarioStatus
     *            the usuario status
     * @return the integer
     */
    private static Integer obtenerIdStatus(final UserStatusEnum usuarioStatus) {
        Integer idStatus = 0;
        switch (usuarioStatus) {
        case ACTIVO:
            idStatus = 1;
            break;
        case ELIMINADO:
            idStatus = 2;
            break;
        default:
            break;
        }
        return idStatus;
    }

    /**
     * Adaptar libro.
     *
     * @param libroDTO
     *            the libro dto
     * @return the libro entity
     */
    public static LibroEntity adaptarLibro(final LibroDTO libroDTO) {
        final LibroEntity libroEntity = new LibroEntity();
        libroEntity.setAnoEdicion(libroDTO.getAnoEdicion());
        libroEntity.setAutores(adaptarAutores(libroDTO.getAutores()));
        libroEntity.setCitaLibro(libroDTO.getCitaLibro());
        libroEntity.setEditorial(adaptarEditorial(libroDTO.getEditorial()));
        libroEntity.setEnlaceAmazon(libroDTO.getEnlaceAmazon());
        libroEntity.setGeneros(adaptarGeneros(libroDTO.getGeneros()));
        libroEntity.setLibroId(libroDTO.getLibroId());
        libroEntity.setResumen(libroDTO.getResumen());
        libroEntity.setSagaId(libroDTO.getSagaId());
        libroEntity.setTitulo(libroDTO.getTitulo());
        libroEntity.setUrlImagen(libroDTO.getUrlImagen());
        return libroEntity;
    }

    /**
     * Adaptar editorial.
     *
     * @param editorial
     *            the editorial
     * @return the editorial entity
     */
    private static EditorialEntity adaptarEditorial(final EditorialDTO editorial) {
        final EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setEditorialId(editorial.getEditorialId());
        editorialEntity.setNombreEditorial(editorial.getNombreEditorial());
        return editorialEntity;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            the autores
     * @return the establece
     */
    public static Set<AutorEntity> adaptarAutores(final Set<AutorDTO> autores) {
        final Set<AutorEntity> autoresEntities = new HashSet<AutorEntity>();
        for (final AutorDTO autorDTO : autores) {
            autoresEntities.add(adaptarAutor(autorDTO));
        }
        return autoresEntities;
    }

    /**
     * Adaptar autor.
     *
     * @param autorDTO
     *            the autor dto
     * @return the autor entity
     */
    public static AutorEntity adaptarAutor(final AutorDTO autorDTO) {
        final AutorEntity entity = new AutorEntity();
        entity.setAutorId(autorDTO.getAutorId());
        entity.setNombre(autorDTO.getNombre());
        return entity;
    }

    /**
     * Adaptar autores.
     *
     * @param autores
     *            the autores
     * @return the establece
     */
    public static Set<GeneroEntity> adaptarGeneros(final Set<GeneroDTO> generos) {
        final Set<GeneroEntity> generosEntities = new HashSet<GeneroEntity>();
        for (final GeneroDTO generoDTO : generos) {
            generosEntities.add(adaptarGenero(generoDTO));
        }
        return generosEntities;
    }

    /**
     * Adaptar genero.
     *
     * @param generoDTO
     *            the genero dto
     * @return the genero entity
     */
    public static GeneroEntity adaptarGenero(final GeneroDTO generoDTO) {
        final GeneroEntity entity = new GeneroEntity();
        entity.setGenero_id(generoDTO.getGeneroId());
        entity.setNombre(generoDTO.getNombre());
        return entity;
    }
}
