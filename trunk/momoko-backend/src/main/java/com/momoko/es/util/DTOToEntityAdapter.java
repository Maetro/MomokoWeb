/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.enums.UserStatusEnum;
import com.momoko.es.model.entity.LibroEntity;
import com.momoko.es.model.entity.UsuarioEntity;

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
        usuario.setUsuario_email(nuevoUsuario.getEmail());
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
        libroEntity.setAutorId(libroDTO.getAutorId());
        libroEntity.setCitaLibro(libroDTO.getCitaLibro());
        libroEntity.setEditorialId(libroDTO.getEditorialId());
        libroEntity.setEnlaceAmazon(libroDTO.getEnlaceAmazon());
        libroEntity.setGeneroId(libroDTO.getGeneroId());
        libroEntity.setLibroId(libroDTO.getLibroId());
        libroEntity.setResumen(libroDTO.getResumen());
        libroEntity.setSagaId(libroDTO.getSagaId());
        libroEntity.setTitulo(libroDTO.getTitulo());
        libroEntity.setUrlImagen(libroDTO.getUrlImagen());
        return libroEntity;
    }
}
