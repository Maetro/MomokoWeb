package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DynamicFilterRepository implements IDynamicFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getBookListWithAppliedFilters(List<FilterDTO> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root bookFilter = criteriaQuery.from(FilterBook.class);
        Join book = bookFilter.join("book");
        Join genre = book.join("generos");
        criteriaQuery.select(book.get("urlLibro"));


        Predicate whereSection = criteriaBuilder.and(criteriaBuilder.equal(genre.get("urlGenero"), "comic-americano"));
        if (CollectionUtils.isNotEmpty(filters)) {
            //AND
            for (FilterDTO filter : filters) {
                if (FilterRuleType.ENUM.equals(filter.getFilterType())) {
                    whereSection = getPredicateForEnumTypeFilter(criteriaBuilder, bookFilter, whereSection, filter);
                }
                if (FilterRuleType.BETWEEN.equals(filter.getFilterType())) {
                    whereSection = getPredicateForBetweenTypeFilter(criteriaBuilder, book, whereSection, filter);
                }
            }
        }
        criteriaQuery.where(whereSection);
        Query query = entityManager.createQuery(criteriaQuery);
        List<String> result4 = query.getResultList();
        System.out.println(result4);
        return result4;
    }

    private Predicate getPredicateForBetweenTypeFilter(CriteriaBuilder criteriaBuilder, Join book, Predicate whereSection, FilterDTO filter) {
        if (filter.getReferencedProperty() != null) {
            Predicate filterSection = criteriaBuilder.disjunction();
            for (String value : filter.getValue()) {
                List<String> divided = ConversionUtils.divide(value, "-");
                filterSection = criteriaBuilder.or(filterSection, criteriaBuilder.between(book.<Integer>get(
                        filter.getReferencedProperty()), Integer.valueOf(divided.get(0)), Integer.valueOf(divided.get(1))));
            }
            whereSection = criteriaBuilder.and(whereSection, filterSection);
        }
        return whereSection;
    }

    private Predicate getPredicateForEnumTypeFilter(CriteriaBuilder criteriaBuilder, Root bookFilter, Predicate whereSection, FilterDTO filter) {
        if (CollectionUtils.isNotEmpty(filter.getValue())) {
            Predicate filterSection = criteriaBuilder.disjunction();
            //OR
            for (String value : filter.getValue()) {
                filterSection = criteriaBuilder.or(filterSection, criteriaBuilder.equal(bookFilter.get("value"), value));
            }

            whereSection = criteriaBuilder.and(whereSection, filterSection);
        }
        return whereSection;
    }
}
