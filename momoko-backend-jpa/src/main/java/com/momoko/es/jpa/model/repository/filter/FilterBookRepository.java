package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.jpa.model.entity.EntradaEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.key.FilterBookId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FilterBookRepository extends CrudRepository<FilterBook, FilterBookId> {

    List<FilterBook> findAllByBookIn(List<LibroEntity> books);


    @Query(value = "SELECT DISTINCT l.url_libro from libro l " +
            "inner join filter_book fb on fb.book_libro_id = l.libro_id " +
            "inner join `filter` f on f.filter_id = fb.filter_filter_id " +
            "inner join libro_genero lg on lg.libro_id = l.libro_id " +
            "inner join genero g on lg.genero_id = g.genero_id " +
            "inner join entrada_libro el on l.libro_id = el.libro_id " +
            "inner join entrada e on e.entrada_id = el.entrada_id " +
            "where g.url_genero LIKE :urlGenero " +
            "and f.filter_id = :filterId " +
            "and fb.value LIKE :filterValue " +
            "AND e.tipo_entrada = 2 " +
            "AND e.fecha_alta <= now() and e.fecha_baja is null " +
            "order by e.fecha_alta desc", nativeQuery = true)
    List<String> getBookListWithAppliedFilter(@Param("urlGenero") String urlGenero,
                                                                   @Param("filterId") Integer filterId,
                                                                   @Param("filterValue") String filterValue);



    @Query(value = "SELECT DISTINCT l.* from libro l " +
            "inner join filter_book fb on fb.book_libro_id = l.libro_id " +
            "inner join `filter` f on f.filter_id = fb.filter_filter_id " +
            "inner join libro_genero lg on lg.libro_id = l.libro_id " +
            "inner join genero g on lg.genero_id = g.genero_id " +
            "inner join entrada_libro el on l.libro_id = el.libro_id " +
            "inner join entrada e on e.entrada_id = el.entrada_id " +
            "where g.url_genero LIKE :urlGenero " +
            ":filterQuery " +
            "AND e.tipo_entrada = 2 " +
            "AND e.fecha_alta <= now() and e.fecha_baja is null " +
            "order by e.fecha_alta desc", nativeQuery = true)
    List<LibroEntity> getBookListWithAppliedFilters(@Param("urlGenero") String urlGenero,
                                                    @Param("filterQuery") String filterQuery);
}
