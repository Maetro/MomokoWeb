package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.filter.FilterBook;
import com.momoko.es.jpa.filter.key.FilterBookValueId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FilterBookRepository extends CrudRepository<FilterBook, FilterBookValueId> {

    List<FilterBook> findAllByBookIn(List<LibroEntity> books);

    List<FilterBook> removeAllByBook_LibroIdIs(Integer bookId);

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
            "AND e.created_date <= now() " +
            "order by e.created_date desc", nativeQuery = true)
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
            "AND e.created_date <= now() " +
            "order by e.created_date desc", nativeQuery = true)
    List<LibroEntity> getBookListWithAppliedFilters(@Param("urlGenero") String urlGenero,
                                                    @Param("filterQuery") String filterQuery);

    @Modifying
    @Query(value = "INSERT INTO filter_book " +
            "(book_libro_id, filter_filter_id, value_filter_value_id) " +
            " VALUES(:bookId, :filterId, :valueId);", nativeQuery = true)
    @Transactional
    public void saveBookIdFilterIdAndValueIdEntry(@Param("bookId") Integer bookId,
                                                  @Param("filterId") Integer filterId,
                                                  @Param("valueId") Integer valueId);
}
