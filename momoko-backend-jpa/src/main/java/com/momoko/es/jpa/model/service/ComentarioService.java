package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.request.*;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.api.enums.errores.ErrorPublicarComentario;

import java.util.List;

public interface ComentarioService {

    public ComentarioDTO guardarComentario(NuevoComentarioRequest comentario) throws Exception;

    List<ComentarioDTO> obtenerComentariosEntrada(String urlEntrada);

    List<ComentarioDTO> obtenerComentariosEntrada(Integer entradaId);

    void enviarNotificacion(ComentarioDTO comentarioDTO);

    void sendContactEmail(AuthorContactRequestDTO contactRequest);

    void sendContactEmail(PublisherContactRequestDTO publisherContactRequestDTO);

    void sendContactEmail(EditorContactRequestDTO editorContactRequestDTO);

    void sendContactEmail(SuscribeContactRequestDTO suscribeContactRequestDTO);

    List<ErrorPublicarComentario> validarComentario(ComentarioDTO comentario);

    List<ErrorCreacionComentario> validarComentario(NuevoComentarioRequest comentario);
}
