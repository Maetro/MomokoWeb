/**
 * EntradaRepository.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momoko.es.jpa.model.entity.EntradaEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;

/**
 * The Interface EntradaRepository.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Repository
public interface EntradaRepository extends CrudRepository<EntradaEntity, Integer> {

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    List<EntradaEntity> findTop10ByOrderByFechaAltaDesc();

    List<EntradaEntity> findAll();

    /**
     * Find first by url entrada.
     *
     * @param urlEntrada
     *            url entrada
     * @return the entrada entity
     */
    EntradaEntity findFirstByUrlEntrada(String urlEntrada);

    @Query("SELECT e FROM EntradaEntity e INNER JOIN e.librosEntrada l " + " WHERE e.fechaAlta < :ahora "
            + " AND l.libroId in :libroIds " + " AND e.fechaBaja is NULL ORDER by e.fechaAlta DESC")
    List<EntradaEntity> findAllByLibrosEntradaIdIn(@Param("libroIds") List<Integer> libroIds,
            @Param("ahora") Date ahora);

    List<EntradaEntity> findByEtiquetasEtiquetaIdIn(List<Integer> etiquetaId);

    @Query("select e from EntradaEntity e WHERE e.fechaAlta < :ahora ORDER by e.fechaAlta DESC")
    List<EntradaEntity> findUltimasEntradas(@Param("ahora") Date ahora, Pageable pageable);

    List<EntradaEntity> findTop3ByLibrosEntradaIsNotNullOrderByLibrosEntradaVisitasDesc();

    List<EntradaEntity> findByLibrosEntradaIn(List<LibroEntity> librosEntrada, Pageable limit);

    @Query("select e from EntradaEntity e inner join e.sagasEntrada s where s.sagaId IN :sagaIds and e.fechaBaja IS NULL ORDER by e.fechaAlta DESC")
    List<EntradaEntity> findBySagasEntradaIn(@Param("sagaIds") List<Integer> sagaIds, Pageable limit);

    @Query("select e from EntradaEntity e where e.tipoEntrada = 3 and e.fechaAlta < :fechaAlta and e.fechaBaja IS NULL ORDER by e.fechaAlta DESC")
    Page<EntradaEntity> findEntradaMiscelaneosAnteriorAFecha(@Param("fechaAlta") Date fechaAlta, Pageable limit);

    @Query("select e from EntradaEntity e where e.tipoEntrada = 3 and e.fechaAlta > :fechaAlta and e.fechaBaja IS NULL ORDER by e.fechaAlta DESC")
    Page<EntradaEntity> findEntradaMiscelaneosPosteriorAFecha(@Param("fechaAlta") Date fechaAlta, Pageable limit);

    @Query("SELECT e from EntradaEntity e where e.entradaId <> :entradaId and e.fechaBaja IS NULL ORDER BY rand()")
    Page<EntradaEntity> seleccionarEntradasAleatorias(@Param("entradaId") Integer entradaId, Pageable pageRequest);

    List<EntradaEntity> findByTipoEntradaAndFechaBajaIsNullOrderByFechaAltaDesc(Integer tipoEntrada, Pageable limit);

    Long countByTipoEntradaAndFechaBajaIsNull(Integer tipoEntrada);

    @Query("select distinct e from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2 AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    List<EntradaEntity> findEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2 AND e.fechaBaja IS NULL")
    Long findNumberEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds);

    @Query("select distinct e from EntradaEntity e join e.etiquetas et WHERE et.etiquetaId = :etiquetaId AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    List<EntradaEntity> findEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("etiquetaId") Integer etiquetaId, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.etiquetas et WHERE et.etiquetaId = :etiquetaId AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    Long findNumberEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(@Param("etiquetaId") Integer etiquetaId);

    List<EntradaEntity> findByUrlEntradaIn(List<String> entradasUrls);

    @Query(value = "SELECT * FROM entrada where tipo_entrada = :tipoEntrada ORDER BY RAND() LIMIT 5;", nativeQuery = true)
    List<EntradaEntity> obtenerEntradasAleatoriasDeTipo(@Param("tipoEntrada") Integer tipoEntrada);

    @Query("select distinct e from EntradaEntity e join e.entradaAutor ea WHERE ea.usuarioUrl = :urlEditor AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    List<EntradaEntity> findEntradaByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditor") String urlEditor, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.entradaAutor ea WHERE ea.usuarioUrl = :urlEditor AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    Long findNumberEntradasByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(@Param("urlEditor") String urlEditor);

    @Query("select distinct e from EntradaEntity e join e.librosEntrada l join l.editorial ed WHERE ed.urlEditorial = :urlEditorial AND e.fechaBaja IS NULL ORDER BY e.fechaAlta DESC")
    List<EntradaEntity> obtenerEntradasEditorialPorFecha(@Param("urlEditorial") String urlEditorial, Pageable pageable);

    @Query(value = "select distinct e.* from entrada e " + "left join entrada_saga es on es.entrada_id = e.entrada_id "
            + "left join entrada_libro el on e.entrada_id = el.entrada_id "
            + "left join saga s on s.saga_id = es.saga_id " + "left join libro l on l.saga_id = s.saga_id "
            + "left join libro l2 on l2.libro_id = el.libro_id "
            + "left join libro_genero lg on lg.libro_id = l.libro_id " + "or lg.libro_id = l2.libro_id "
            + "left join genero g on lg.genero_id = g.genero_id " + "where e.tipo_entrada = 2 "
            + "and g.url_genero like :urlGenero " + "and e.fecha_alta <= now() " + "and e.fecha_baja is null "
            + "order by e.fecha_alta desc " + "limit :initElement, :endElement", nativeQuery = true)
    List<EntradaEntity> obtenerAnalisisSagasYLibrosPorGeneroYFecha(@Param("urlGenero") String urlGenero,
            @Param("initElement") Integer initElement, @Param("endElement") Integer endElement);

    @Query(value = "select distinct e.* from entrada e "
            + "            left join entrada_libro el on e.entrada_id = el.entrada_id  "
            + "            left join libro l on l.libro_id = el.libro_id"
            + "            left join entrada_saga es on es.entrada_id = e.entrada_id "
            + "            left join saga s on s.saga_id = es.saga_id "
            + "            left join libro l2 on l2.saga_id = s.saga_id "
            + "            left join libro_genero lg on lg.libro_id = l.libro_id or lg.libro_id = l2.libro_id "
            + "            left join genero g on lg.genero_id = g.genero_id "
            + "            inner join puntuacion p on p.libro_id = l.libro_id or s.saga_id = p.saga_id"
            + "            where e.tipo_entrada = 2 "
            + "            and g.url_genero like :urlGenero and e.fecha_alta <= now() and e.fecha_baja is null "
            + "            order by p.valor desc limit :initElement, :endElement", nativeQuery = true)
    List<EntradaEntity> obtenerAnalisisSagasYlibrosPorGeneroYNota(@Param("urlGenero") String urlGenero,
            @Param("initElement") Integer initElement, @Param("endElement") Integer endElement);
}
