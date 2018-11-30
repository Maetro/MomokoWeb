/**
 * ComentarioRepository.java 02-nov-2017
 *
 */
package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.comment.ComentarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends CrudRepository<ComentarioEntity, Integer> {

    List<ComentarioEntity> findByEntradaEntradaId(Integer entradaId);

    List<ComentarioEntity> findByEntradaUrlEntrada(String urlEntrada);

}
