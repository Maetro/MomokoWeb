package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.jpa.model.entity.GenreEntity;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface FilterRepository extends CrudRepository<FilterEntity, Integer>{

    Set<FilterEntity> findAll();

    public List<FilterEntity> findAllByApplicableGenresIn(List<GenreEntity> genres);

    FilterEntity findOneByUrlFilterIs(String urlFilter);

//    @Query("select distinct l from FilterEntity l join l.filterbooks fb"
//            + " WHERE e.tipoEntrada = 2 AND g.generoId IN :generoIds AND e.tipoEntrada IS NOT NULL"
//            + " AND e.fechaAlta < :ahora ORDER BY l.fechaAlta DESC")
//    public List<FilterEntity> findAllByApplicableGenresIdsIn(@Param("generoIds") List<Integer> generoIds);

}
