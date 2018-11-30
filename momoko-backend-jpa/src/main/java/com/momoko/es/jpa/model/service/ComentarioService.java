/**
 * ComentarioService.java 02-nov-2017
 *
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.*;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;

import java.util.List;

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

    List<ComentarioDTO> obtenerComentariosEntrada(String urlEntrada);

    /**
     * Obtener comentarios entrada.
     *
     * @param entradaId
     *            the entrada id
     * @return the list
     */
    public List<ComentarioDTO> obtenerComentariosEntrada(Integer entradaId);

    /**
     * Enviar notificacion.
     *
     * @param comentarioDTO
     *            the comentario dto
     */
    public void enviarNotificacion(ComentarioDTO comentarioDTO);

    public void sendContactEmail(AuthorContactRequestDTO contactRequest);

    public void sendContactEmail(PublisherContactRequestDTO publisherContactRequestDTO);

    public void sendContactEmail(EditorContactRequestDTO editorContactRequestDTO);

    public void sendContactEmail(SuscribeContactRequestDTO suscribeContactRequestDTO);

    List<ErrorCreacionComentario> validarComentario(NuevoComentarioRequest comentario);
}
