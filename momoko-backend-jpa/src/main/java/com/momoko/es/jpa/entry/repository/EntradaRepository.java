package com.momoko.es.jpa.entry.repository;

import java.util.Date;
import java.util.List;

import com.momoko.es.api.enums.EntryTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momoko.es.jpa.entry.entity.EntradaEntity;
import com.momoko.es.jpa.book.LibroEntity;

@Repository
public interface EntradaRepository extends CrudRepository<EntradaEntity, Integer> {

    List<EntradaEntity> findTop10ByOrderByCreatedDateDesc();

    List<EntradaEntity> findAll();

    /**
     * Find first by url entrada.
     *
     * @param urlEntrada
     *            url entrada
     * @return the entrada entity
     */
    EntradaEntity findFirstByUrlEntrada(String urlEntrada);

    @Query("SELECT e FROM EntradaEntity e INNER JOIN e.librosEntrada l " + " WHERE e.createdDate < :ahora "
            + " AND l.libroId in :libroIds " + " ORDER by e.createdDate DESC")
    List<EntradaEntity> findAllByLibrosEntradaIdIn(@Param("libroIds") List<Integer> libroIds,
            @Param("ahora") Date ahora);

    List<EntradaEntity> findByEtiquetasEtiquetaIdIn(List<Integer> etiquetaId);

    @Query("select e from EntradaEntity e WHERE e.createdDate < :ahora ORDER by e.createdDate DESC")
    List<EntradaEntity> findUltimasEntradas(@Param("ahora") Date ahora, Pageable pageable);

    List<EntradaEntity> findTop3ByLibrosEntradaIsNotNullOrderByLibrosEntradaVisitasDesc();

    List<EntradaEntity> findByLibrosEntradaIn(List<LibroEntity> librosEntrada, Pageable limit);

    @Query("select e from EntradaEntity e inner join e.sagasEntrada s where s.sagaId IN :sagaIds ORDER by e.createdDate DESC")
    List<EntradaEntity> findBySagasEntradaIn(@Param("sagaIds") List<Integer> sagaIds, Pageable limit);

    @Query("select e from EntradaEntity e where e.entradaId < :entryId and e.entryStatus LIKE 'PUBLISHED' ORDER by e.entradaId DESC")
    Page<EntradaEntity> findEntradaMiscelaneosAnteriorAFecha(@Param("entryId") Integer entryId, Pageable limit);

    @Query("select e from EntradaEntity e where e.entradaId > :entryId and e.entryStatus LIKE 'PUBLISHED' ORDER by e.entradaId ASC")
    Page<EntradaEntity> findEntradaMiscelaneosPosteriorAFecha(@Param("entryId") Integer entryId, Pageable limit);

    @Query("SELECT e from EntradaEntity e where e.entradaId <> :entradaId ORDER BY rand()")
    Page<EntradaEntity> seleccionarEntradasAleatorias(@Param("entradaId") Integer entradaId, Pageable pageRequest);

    List<EntradaEntity> findByTipoEntradaOrderByCreatedDateDesc(Integer tipoEntrada, Pageable limit);

    Long countByTipoEntrada(Integer tipoEntrada);

    @Query("select distinct e from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2 ORDER BY e.createdDate DESC")
    List<EntradaEntity> findEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.librosEntrada l join l.generos g WHERE g.generoId IN :generoIds AND e.tipoEntrada = 2")
    Long findNumberEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("generoIds") List<Integer> generoIds);

    @Query("select distinct e from EntradaEntity e join e.etiquetas et WHERE et.etiquetaId = :etiquetaId ORDER BY e.createdDate DESC")
    List<EntradaEntity> findEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("etiquetaId") Integer etiquetaId, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.etiquetas et WHERE et.etiquetaId = :etiquetaId ORDER BY e.createdDate DESC")
    Long findNumberEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(@Param("etiquetaId") Integer etiquetaId);

    List<EntradaEntity> findByUrlEntradaIn(List<String> entradasUrls);

    @Query(value = "SELECT * FROM entrada where tipo_entrada = :tipoEntrada ORDER BY RAND() LIMIT 5;", nativeQuery = true)
    List<EntradaEntity> obtenerEntradasAleatoriasDeTipo(@Param("tipoEntrada") Integer tipoEntrada);

    @Query("select distinct e from EntradaEntity e join e.entradaAutor ea WHERE ea.usuarioUrl = :urlEditor ORDER BY e.createdDate DESC")
    List<EntradaEntity> findEntradaByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(
            @Param("urlEditor") String urlEditor, Pageable pageable);

    @Query("select COUNT(e) from EntradaEntity e join e.entradaAutor ea WHERE ea.usuarioUrl = :urlEditor ORDER BY e.createdDate DESC")
    Long findNumberEntradasByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(@Param("urlEditor") String urlEditor);

    @Query("select distinct e from EntradaEntity e join e.librosEntrada l join l.editorial ed WHERE ed.urlEditorial = :urlEditorial ORDER BY e.createdDate DESC")
    List<EntradaEntity> obtenerEntradasEditorialPorFecha(@Param("urlEditorial") String urlEditorial, Pageable pageable);

    @Query(value = "select distinct e.* from entrada e " + "left join entrada_saga es on es.entrada_id = e.entrada_id "
            + "left join entrada_libro el on e.entrada_id = el.entrada_id "
            + "left join saga s on s.saga_id = es.saga_id " + "left join libro l on l.saga_id = s.saga_id "
            + "left join libro l2 on l2.libro_id = el.libro_id "
            + "left join libro_genero lg on lg.libro_id = l.libro_id " + "or lg.libro_id = l2.libro_id "
            + "left join genero g on lg.genero_id = g.genero_id " + "where e.tipo_entrada = 2 "
            + "and g.url_genero like :urlGenero " + "and e.created_date <= now() "
            + "order by e.created_date desc " + "limit :initElement, :endElement", nativeQuery = true)
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
            + "            and g.url_genero like :urlGenero and e.created_date <= now() "
            + "            order by p.valor desc limit :initElement, :endElement", nativeQuery = true)
    List<EntradaEntity> obtenerAnalisisSagasYlibrosPorGeneroYNota(@Param("urlGenero") String urlGenero,
            @Param("initElement") Integer initElement, @Param("endElement") Integer endElement);

    @Query("select e from EntradaEntity e WHERE e.entryType = :entryType AND e.createdDate < :ahora ORDER by e.createdDate DESC")
    List<EntradaEntity> findLastEntries(@Param("ahora") Date ahora, @Param("entryType") EntryTypeEnum entryType, Pageable pageable);

    @Query("select e from EntradaEntity e WHERE e.urlEntrada not in (:alreadyUsedUrls) AND e.createdDate < :ahora ORDER by e.createdDate DESC")
    List<EntradaEntity> findLastEntriesNotUsed(
            @Param("ahora") Date ahora, @Param("alreadyUsedUrls") List<String> alreadyUsedUrls, Pageable pageable);
}
