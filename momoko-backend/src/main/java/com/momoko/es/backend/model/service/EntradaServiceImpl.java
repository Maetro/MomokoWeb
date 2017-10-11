package com.momoko.es.backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

public class EntradaServiceImpl implements EntradaService {

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Override
    public List<EntradaDTO> recuperarEntradas() {
        // TODO Auto-generated method stub
        return null;
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

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(viejaEntrada.getFechaAlta());
        entradaEntity.setFechaModificacion(viejaEntrada.getFechaModificacion());
        entradaEntity.setFechaBaja(viejaEntrada.getFechaBaja());
        entradaEntity.setUsuarioAlta(viejaEntrada.getUsuarioAlta());
        entradaEntity.setUsuarioModificacion(viejaEntrada.getUsuarioModificacion());
        entradaEntity.setUsuarioBaja(viejaEntrada.getUsuarioBaja());

//            final Set<AutorEntity> autoresObra = obtenerOGuardarAutoresObra(libroEntity);
//            libroEntity.setAutores(autoresObra);
//            final EditorialEntity editorialObra = obtenerOGuardarEditorialObra(libroEntity);
//            libroEntity.setEditorial(editorialObra);
//            // libroEntity.
//            if (libroEntity.getLibroId() != null) {
//                libroEntity.setUsuarioModificacion("RMaetro@gmail.com");
//                libroEntity.setFechaModificacion(Calendar.getInstance().getTime());
//            } else {
//                libroEntity.setUsuarioAlta("RMaetro@gmail.com");
//                libroEntity.setFechaAlta(Calendar.getInstance().getTime());
//            }
//            return EntityToDTOAdapter.adaptarLibro(this.libroRepository.save(libroEntity));
//        } else {
//            throw new Exception("El titulo del libro ya se esta utilizando");
//        }
        return entradaEntity;
    }

    private EntradaEntity crearNuevaEntrada(EntradaEntity entradaEntity) {

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
