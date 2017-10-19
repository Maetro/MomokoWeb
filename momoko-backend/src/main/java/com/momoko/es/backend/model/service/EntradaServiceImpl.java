package com.momoko.es.backend.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

public class EntradaServiceImpl implements EntradaService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Override
    public List<EntradaDTO> recuperarEntradas() {
        List<EntradaDTO> entradasDTO = new ArrayList<EntradaDTO>();
        Iterable<EntradaEntity> entradasEntity = entradaRepository.findAll();
        for (EntradaEntity entradaEntity : entradasEntity) {
            entradasDTO.add(EntityToDTOAdapter.adaptarEntrada(entradaEntity));
        }
        return entradasDTO;
    }

    @Override
    public EntradaDTO guardarEntrada(EntradaDTO entradaAGuardar) throws Exception {
        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar);

        boolean esNuevaEntrada = entradaAGuardar.getEntradaId() == null;
        // Comprobamos si la url de la entrada existe.
        final EntradaEntity coincidencia = this.entradaRepository.findFirstByUrlEntrada(entradaEntity.getUrlEntrada());
        if (esNuevaEntrada && coincidencia != null) {
            throw new URLEntradaYaExisteException("La entrada es nueva y se esta intentando utilizar una url ya usada");
        }
        if (!esNuevaEntrada && coincidencia == null) {
            throw new NoSeEncuentraEntradaException("No se encuentra la entrada");
        }
        if (esNuevaEntrada) {
            entradaEntity = crearNuevaEntrada(entradaEntity);
        } else {
            entradaEntity = actualizarEntrada(entradaEntity, coincidencia);
        }
        return EntityToDTOAdapter.adaptarEntrada(entradaEntity);
    }

    private EntradaEntity actualizarEntrada(EntradaEntity entradaEntity, EntradaEntity viejaEntrada) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(viejaEntrada.getFechaAlta());
        entradaEntity.setFechaModificacion(Calendar.getInstance().getTime());
        entradaEntity.setFechaBaja(viejaEntrada.getFechaBaja());
        entradaEntity.setUsuarioAlta(viejaEntrada.getUsuarioAlta());
        entradaEntity.setUsuarioModificacion(currentPrincipalName);
        entradaEntity.setUsuarioBaja(viejaEntrada.getUsuarioBaja());
        UsuarioEntity usuario = usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        entradaEntity.setEntradaAutor(usuario);

        if (entradaEntity.getPadreEntrada() != null) {
            EntradaEntity padre = entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }
        // TODO: RAMON: Implementar
        entradaEntity.setNumeroComentarios(0);
        entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private EntradaEntity crearNuevaEntrada(EntradaEntity entradaEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
        entradaEntity.setUsuarioAlta(currentPrincipalName);

        UsuarioEntity usuario = usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        entradaEntity.setEntradaAutor(usuario);

        if (entradaEntity.getPadreEntrada() != null) {
            EntradaEntity padre = entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }
        // TODO: RAMON: Implementar
        entradaEntity.setNumeroComentarios(0);
        entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private LibroEntity obtenerLibroEntrada(LibroEntity libroABuscar) {

        LibroEntity libroEncontrado = null;
        if (libroABuscar != null) {
            libroEncontrado = this.libroRepository.findOne(libroABuscar.getLibroId());
        }
        return libroEncontrado;

    }



}
