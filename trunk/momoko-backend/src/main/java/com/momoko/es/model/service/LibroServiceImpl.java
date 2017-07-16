/**
 * LibroServiceImpl.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.model.entity.LibroEntity;
import com.momoko.es.model.repository.LibroRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

/**
 * The Class LibroServiceImpl.
 */
@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    @Transactional
    public Integer crearLibro(final LibroDTO nuevoLibro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LibroDTO> recuperarLibros() {
        final List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
        final Iterable<LibroEntity> libroEntityIterable = this.libroRepository.findAll();
        for (final LibroEntity libroEntity : libroEntityIterable) {
            listaLibros.add(EntityToDTOAdapter.adaptarLibro(libroEntity));
        }
        return listaLibros;
    }

    @Override
    public LibroDTO guardarLibro(final LibroDTO libroAGuardar) {
        final LibroEntity libroEntiity = DTOToEntityAdapter.adaptarLibro(libroAGuardar);
        return EntityToDTOAdapter.adaptarLibro(this.libroRepository.save(libroEntiity));
    }

}
