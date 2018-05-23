/**
 * HerramientasServiceImpl.java 14-may-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.EntradaUrlDTO;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.service.HerramientasService;

/**
 * The Class HerramientasServiceImpl.
 */
@Service
public class HerramientasServiceImpl implements HerramientasService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<EntradaUrlDTO> obtenerUrlsOrdenadasPorLongitud() {
        final List<EntradaUrlDTO> entradasUrl = new ArrayList<EntradaUrlDTO>();
        // TODO Auto-generated method stub
        return entradasUrl;
    }

}
