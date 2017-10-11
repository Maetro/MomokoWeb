package com.momoko.es.backend.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.momoko.es.backend.model.entity.EntradaEntity;

/**
 * The Interface EntradaRepository.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramón Casares</a>
 */
public interface EntradaRepository extends CrudRepository<EntradaEntity, Integer> {

    /**
     * Find first by url entrada.
     *
     * @param urlEntrada
     *            url entrada
     * @return the entrada entity
     */
    EntradaEntity findFirstByUrlEntrada(String urlEntrada);

}
