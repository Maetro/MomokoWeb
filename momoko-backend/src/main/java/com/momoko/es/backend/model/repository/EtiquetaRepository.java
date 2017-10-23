package com.momoko.es.backend.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.EtiquetaEntity;

public interface EtiquetaRepository extends CrudRepository<EtiquetaEntity, Integer> {

    /**
     * Find by nombre.
     *
     * @param nombre
     *            the nombre
     * @return the list
     */
    List<EtiquetaEntity> findByNombre(String nombre);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
     */
    EtiquetaEntity findOne(Integer etiqueta_id);

}
