package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.NameValue;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DynamicFilterRepository implements IDynamicFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getBookListWithAppliedFilters(String urlGenre, List<FilterDTO> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root book = criteriaQuery.from(LibroEntity.class);
        Join entradas = book.join("entradas", JoinType.INNER);
        Join bookFilter = book.join("filters", JoinType.LEFT);
        Join filterEntity = bookFilter.join("filter", JoinType.LEFT);
        Join genre = book.join("generos", JoinType.LEFT);
        criteriaQuery.select(book.get("urlLibro"));

        Set<Integer> filterNumber = new HashSet<>();
        Predicate urlGeneroFilter = criteriaBuilder.and(criteriaBuilder.equal(genre.get("urlGenero"), urlGenre));
        Predicate entradaAnalisisFilter = criteriaBuilder.and(criteriaBuilder.equal(entradas.get("tipoEntrada"), 2));
        Predicate customFilter = criteriaBuilder.disjunction();
        if (CollectionUtils.isNotEmpty(filters)) {
            //AND
            for (FilterDTO filter : filters) {

                if (FilterRuleType.ENUM.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        filterNumber.add(filter.getFilterId());
                        customFilter = getPredicateForEnumTypeFilter(criteriaBuilder, filterEntity, bookFilter, customFilter, filter);
                    }
                }
                if (FilterRuleType.BETWEEN.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        filterNumber.add(filter.getFilterId());
                        customFilter = getPredicateForBetweenTypeFilter(criteriaBuilder, book, customFilter, filter);
                    }
                }
            }
        }
        urlGeneroFilter = criteriaBuilder.and(urlGeneroFilter, customFilter, entradaAnalisisFilter);
        criteriaQuery.groupBy(book);
        Expression count = criteriaBuilder.count(book);
        criteriaQuery.having(criteriaBuilder.greaterThanOrEqualTo(count, filterNumber.size()));
        criteriaQuery.where(urlGeneroFilter);
        Query query = entityManager.createQuery(criteriaQuery);
        List<String> result4 = query.getResultList();
        System.out.println(result4);
        return result4;
    }

    @Override
    public List<FilterDTO> getFilterListWithSelectedBooks(String urlGenre, List<String> urlBooks) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery().distinct(true);

        Root filter = criteriaQuery.from(FilterEntity.class);
        Join filterBooks = filter.join("filterBooks", JoinType.LEFT);
        Join book = filterBooks.join("book", JoinType.LEFT);
        Join genre = book.join("generos", JoinType.LEFT);
        criteriaQuery.multiselect(filter, filterBooks);

        Predicate orFirst = criteriaBuilder.equal(genre.get("urlGenero"), urlGenre);
        Predicate whereSection = criteriaBuilder.and(orFirst, book.get("urlLibro").in(urlBooks));

        criteriaQuery.where(whereSection);

        List<Object[]> filtersAndResults = entityManager.createQuery(criteriaQuery).getResultList();
        List<FilterDTO> result = new ArrayList<>();
        adaptQueryResultToFilterDTO(filtersAndResults, result);
        return result;
    }

    @Override
    public List<FilterDTO> getFilterListByGenre(String urlGenre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery().distinct(true);

        Root filter = criteriaQuery.from(FilterEntity.class);
        Join filterBooks = filter.join("filterBooks", JoinType.LEFT);
        Join filterGenres = filter.join("applicableGenres", JoinType.LEFT);
        Join book = filterBooks.join("book", JoinType.LEFT);
        Join genre = book.join("generos", JoinType.LEFT);
        criteriaQuery.multiselect(filter, filterBooks);

        Predicate urlGeneroFilter = criteriaBuilder.equal(genre.get("urlGenero"), urlGenre);
        Predicate whereSection = criteriaBuilder.or(urlGeneroFilter, filterGenres.isNull());

        criteriaQuery.where(whereSection);

        List<Object[]> filtersAndResults = entityManager.createQuery(criteriaQuery).getResultList();
        List<FilterDTO> result = new ArrayList<>();
        adaptQueryResultToFilterDTO(filtersAndResults, result);
        return result;
    }

    private void adaptQueryResultToFilterDTO(List<Object[]> filtersAndResults, List<FilterDTO> result) {
        for (Object[] value : filtersAndResults) {
            FilterEntity filterTemp = (FilterEntity) value[0];
            FilterBook filterBook = (FilterBook) value[1];
            FilterDTO filterDTO = EntityToDTOAdapter.adaptFilter(filterTemp);
            if (result.contains(filterDTO)) {
                NameValue nameValue = getNameValue(filterBook);

                List<NameValue> possibleValues = result.get(result.indexOf(filterDTO)).getPossibleValues();
                if (!possibleValues.contains(nameValue)) {
                    possibleValues.add(nameValue);
                }
            } else {
                if (filterDTO.getReferencedProperty() == null) {
                    filterDTO.getPossibleValues().clear();
                    NameValue nameValue = getNameValue(filterBook);
                    filterDTO.getPossibleValues().add(nameValue);
                }
                result.add(filterDTO);
            }

        }
    }

    private NameValue getNameValue(FilterBook filterBook) {
        NameValue nameValue = null;
        if (filterBook.getValue().contains("[")){
            List<String> divided = ConversionUtils.divide("[");
            nameValue = new NameValue(divided.get(1).replace("]","").trim(),
                    divided.get(0).trim());
        } else {
            nameValue = new NameValue(filterBook.getValue(), filterBook.getValue());
        }
        return nameValue;
    }

    private Predicate getPredicateForBetweenTypeFilter(CriteriaBuilder criteriaBuilder, Root book, Predicate whereSection, FilterDTO filter) {
        if (filter.getReferencedProperty() != null) {
            List<Predicate> filterList = new ArrayList<>();
            for (String value : filter.getValue()) {
                String valueToDivide = value;
                if (value.contains("[")){
                    valueToDivide = ConversionUtils.divide(valueToDivide, "[").get(0);
                }
                List<String> divided = ConversionUtils.divide(valueToDivide, "-");
                Predicate filterSection = criteriaBuilder.between(book.<Integer>get(
                        filter.getReferencedProperty()), Integer.valueOf(divided.get(0).trim()), Integer.valueOf(divided.get(1).trim()));
                filterList.add(filterSection);
            }
            Predicate finalfilterSection = criteriaBuilder.disjunction();
            for (Predicate predicate : filterList) {
                finalfilterSection = criteriaBuilder.or(finalfilterSection, predicate);
            }
            whereSection = criteriaBuilder.or(whereSection, finalfilterSection);
        }
        return whereSection;
    }

    private Predicate getPredicateForEnumTypeFilter(CriteriaBuilder criteriaBuilder, Join filterEntity, Join bookFilter, Predicate whereSection, FilterDTO filter) {
        if (CollectionUtils.isNotEmpty(filter.getValue())) {

            List<Predicate> filterList = new ArrayList<>();
            for (String value : filter.getValue()) {
                Predicate filterSection = criteriaBuilder.equal(filterEntity.get("filterId"), filter.getFilterId());
                filterSection = criteriaBuilder.and(filterSection, criteriaBuilder.equal(bookFilter.get("value"), value));
                filterList.add(filterSection);
            }

            Predicate finalfilterSection = criteriaBuilder.disjunction();
            for (Predicate predicate : filterList) {
                finalfilterSection = criteriaBuilder.or(finalfilterSection, predicate);
            }
            whereSection = criteriaBuilder.or(whereSection, finalfilterSection);
        }
        return whereSection;
    }
}
