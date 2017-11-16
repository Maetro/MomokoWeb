/**
 * ComentarioService.java 02-nov-2017
 *
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;

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
    public ComentarioDTO guardarComentario(NuevoComentarioRequest comentario) throws Exception;

    /**
     * Obtener comentarios entrada.
     *
     * @param entradaId
     *            the entrada id
     * @return the list
     */
    public List<ComentarioDTO> obtenerComentariosEntrada(Integer entradaId);

}
