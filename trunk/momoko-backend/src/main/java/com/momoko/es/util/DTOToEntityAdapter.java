/**
 * DTOToEntityAdapter.java 04-jun-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import com.momoko.es.model.dto.UsuarioDTO;
import com.momoko.es.model.entity.UsuarioEntity;
import com.momoko.es.model.enums.UserStatusEnum;

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

}
