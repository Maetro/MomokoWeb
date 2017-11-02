package com.momoko.es.backend.model.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.backend.model.entity.ComentarioEntity;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.ComentarioRepository;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private ComentarioRepository comentarioRepository;

    @Override
    public ComentarioDTO guardarComentario(ComentarioDTO comentarioAGuardar) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        EntradaEntity comentarioEntrada = this.entradaRepository.findOne(comentarioAGuardar.getEntradaId());
        UsuarioEntity autor = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        ComentarioEntity comentarioReferenciaEntity = null;
        
        
        if (comentarioAGuardar.getComentarioReferencia() != null) {
            comentarioReferenciaEntity = comentarioRepository.findOne(comentarioAGuardar.getComentarioReferencia());
        }


        ComentarioEntity comentarioEntity = DTOToEntityAdapter.adaptarComentario(comentarioAGuardar, comentarioEntrada,
                autor, comentarioReferenciaEntity);

        if (comentarioEntity.getComentarioId() != null) {
            throw new URLEntradaYaExisteException("El comentario que se esta utilizando ya existe");
        }

        comentarioEntity.setFechaAlta(Calendar.getInstance().getTime());
        comentarioEntity.setUsuarioAlta(currentPrincipalName);

        comentarioEntity.setVotosNegativos("");
        comentarioEntity.setVotosPositivos("");

        ComentarioEntity respuesta = this.comentarioRepository.save(comentarioEntity);

        return EntityToDTOAdapter.adaptarComentario(respuesta);
    }

}
