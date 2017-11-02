/**
 * ComentarioService.java 02-nov-2017
 *
 */
package com.momoko.es.backend.model.service;

import com.momoko.es.api.dto.ComentarioDTO;

/**
 * The Interface ComentarioService.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public interface ComentarioService {

    /**
     * Guardar comentario.
     *
     * @param comentario
     *            comentario
     * @return the entrada DTO
     * @throws Exception
     *             de exception
     */
    public ComentarioDTO guardarComentario(ComentarioDTO comentario) throws Exception;

}
