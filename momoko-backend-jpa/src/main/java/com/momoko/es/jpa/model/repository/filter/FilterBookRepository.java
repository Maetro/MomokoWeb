package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.key.FilterBookId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilterBookRepository extends CrudRepository<FilterBook, FilterBookId> {

    List<FilterBook> findAllByBookIn(List<LibroEntity> books);
}
