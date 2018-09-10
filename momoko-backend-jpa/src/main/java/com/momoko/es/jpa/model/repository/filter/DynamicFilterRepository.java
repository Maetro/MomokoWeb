package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.NameValue;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.jpa.model.entity.GenreEntity;
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

import static com.sun.jmx.snmp.ThreadContext.contains;

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

        Predicate urlGeneroFilter = criteriaBuilder.and(criteriaBuilder.equal(genre.get("urlGenero"), urlGenre));
        Predicate entradaAnalisisFilter = criteriaBuilder.and(criteriaBuilder.equal(entradas.get("tipoEntrada"), 2));
        Predicate customFilter = urlGeneroFilter;
        if (CollectionUtils.isNotEmpty(filters)) {
            //AND
            for (FilterDTO filter : filters) {

                if (FilterRuleType.ENUM.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        Predicate enumFilter = criteriaBuilder.disjunction();
                        enumFilter = getPredicateForEnumTypeFilter(criteriaBuilder, filterEntity, bookFilter, enumFilter, filter);
                        customFilter = criteriaBuilder.and(customFilter, enumFilter);
                    }
                }
                if (FilterRuleType.BETWEEN.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        customFilter = getPredicateForBetweenTypeFilter(criteriaBuilder, book, customFilter, filter);
                    }
                }
                if (FilterRuleType.NUMBER_OF_PAGES.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        customFilter = getPredicateForBetweenTypeFilter(criteriaBuilder, book, customFilter, filter);
                    }
                }
                if (FilterRuleType.PUBLISH_DATE.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        customFilter = getPredicateForBetweenTypeFilter(criteriaBuilder, book, customFilter, filter);
                    }
                }

                if (FilterRuleType.SCORE.equals(filter.getFilterType())) {
                    if (CollectionUtils.isNotEmpty(filter.getValue())) {
                        customFilter = getPredicateForBetweenTypeFilter(criteriaBuilder, book, customFilter, filter);
                    }
                }
            }
        }
        urlGeneroFilter = criteriaBuilder.and(customFilter, entradaAnalisisFilter);
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

        Root book = criteriaQuery.from(LibroEntity.class);
        Join entrada = book.join("entradas");
        Join genre = book.join("generos", JoinType.LEFT);
        Join filterBooks = book.join("filters", JoinType.LEFT);
        Join filter = filterBooks.join("filter", JoinType.LEFT);

        criteriaQuery.multiselect(filter, filterBooks, book);
        Predicate whereSection = criteriaBuilder.and(book.get("urlLibro").in(urlBooks));

        criteriaQuery.where(whereSection);

        List<Object[]> filtersAndResults = entityManager.createQuery(criteriaQuery).getResultList();

        List<FilterDTO> result = new ArrayList<>();
        adaptQueryResultToFilterDTO(filtersAndResults, result, urlGenre);
        return result;
    }

    @Override
    public List<FilterDTO> getFilterListByGenre(String urlGenre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery().distinct(true);

        Root book = criteriaQuery.from(LibroEntity.class);
        Join entrada = book.join("entradas");
        Join genre = book.join("generos", JoinType.LEFT);
        Join filterBooks = book.join("filters", JoinType.LEFT);
        Join filter = filterBooks.join("filter", JoinType.LEFT);

        criteriaQuery.multiselect(filter, filterBooks, book);
        Predicate whereSection = criteriaBuilder.and(criteriaBuilder.equal(genre.get("urlGenero"), urlGenre),
                criteriaBuilder.equal(entrada.get("tipoEntrada"), 2));

        criteriaQuery.where(whereSection);

        List<Object[]> filtersAndResults = entityManager.createQuery(criteriaQuery).getResultList();
        List<FilterDTO> result = new ArrayList<>();
        adaptQueryResultToFilterDTO(filtersAndResults, result, urlGenre);
        return result;
    }


    private boolean isAcceptedFilter(String urlGenre, FilterEntity filterDTO) {
        boolean acceptedFilter = true;
        if (filterDTO != null && CollectionUtils.isNotEmpty(filterDTO.getApplicableGenres())) {
            acceptedFilter = false;
            for (GenreEntity genre : filterDTO.getApplicableGenres()) {
                if (genre.getUrlGenero().equals(urlGenre)) {
                    acceptedFilter = true;
                }
            }
        }
        return acceptedFilter;
    }


    private void adaptQueryResultToFilterDTO(List<Object[]> filtersAndResults, List<FilterDTO> result, String urlGenre) {
        Set<NameValue> genreList = new HashSet<>();
        Set<NameValue> numberOfPages = new HashSet<>();
        Set<NameValue> numberOfBooks = new HashSet<>();
        Set<NameValue> scores = new HashSet<>();
        Set<NameValue> publishDates = new HashSet<>();
        for (Object[] value : filtersAndResults) {
            FilterEntity filterTemp = (FilterEntity) value[0];
            FilterBook filterBook = (FilterBook) value[1];
            LibroEntity book = (LibroEntity) value[2];
            if (isAcceptedFilter(urlGenre, filterTemp)) {
                if (filterBook != null) {
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
            if (book != null) {
                addGenreFilterOptions(genreList, book, urlGenre);
                addBookLengthFilterOptions(numberOfPages, book);
                addNumberOfBooksFilterOptions(numberOfBooks, book);
                addScoreFilterOptions(scores, book);
                addPublishDateFilterOptions(publishDates, book);
            }
        }
        addFilterToList(result, genreList, "genre-filter", FilterRuleType.GENRE,
                1000, "Por género", null);
        addFilterToList(result, numberOfPages, "number-of-pages-filter", FilterRuleType.NUMBER_OF_PAGES,
                1001, "Longitud del libro", "numeroPaginas");
        addFilterToList(result, numberOfBooks, "number-of-books-filter", FilterRuleType.NUMBER_OF_BOOKS,
                1002, "Número de libros", null);
        addFilterToList(result, scores, "score-filter", FilterRuleType.SCORE,
                1003, "Por nota", "score");
        addFilterToList(result, publishDates, "publish-date", FilterRuleType.PUBLISH_DATE,
                1004, "Fecha de publicación", "anoPublicacion");
    }


    private void addFilterToList(List<FilterDTO> result, Set<NameValue> values, String urlFilter, FilterRuleType type, int filterId, String filterName,
                                 String referencedProperty) {
        if (values.size() > 1) {
            FilterDTO newGenreFilter = new FilterDTO();
            newGenreFilter.setUrlFilter(urlFilter);
            newGenreFilter.setBasic(false);
            newGenreFilter.setFilterType(type);
            newGenreFilter.setFilterId(filterId);
            newGenreFilter.setNameFilter(filterName);
            newGenreFilter.setReferencedProperty(referencedProperty);
            newGenreFilter.setPossibleValues(new ArrayList<>(values));
            result.add(newGenreFilter);
        }
    }

    private void addScoreFilterOptions(Set<NameValue> scores, LibroEntity book) {
        Integer score = book.getScore();
        if (score != null) {
            if (score < 5) {
                scores.add(new NameValue("Libros suspensos", "0-49", 1));
            } else if (score >= 50 && score < 60) {
                scores.add(new NameValue("5", "50-59", 2));
            } else if (score >= 60 && score < 70) {
                scores.add(new NameValue("6", "60-69", 3));
            } else if (score >= 70 && score < 80) {
                scores.add(new NameValue("7", "70-79", 4));
            } else if (score >= 80 && score < 90) {
                scores.add(new NameValue("8", "80-89", 5));
            } else if (score >= 90 && score < 100) {
                scores.add(new NameValue("9", "90-99", 6));
            } else if (score == 100) {
                scores.add(new NameValue("10", "100-100", 7));
            }
        }
    }

    private void addPublishDateFilterOptions(Set<NameValue> publishDates, LibroEntity book) {
        Integer publishDate = book.getAnoPublicacion();
        if (publishDate != null) {
            if (publishDate < 1799) {
                publishDates.add(new NameValue("Hasta el S. XVIII", "0-1799", 1));
            } else if (publishDate >= 1800 && publishDate < 1900) {
                publishDates.add(new NameValue("Libros del S. XIX", "1800-1899", 2));
            } else if (publishDate >= 1900 && publishDate < 2000) {
                publishDates.add(new NameValue("Libros del S. XX", "1900-1999", 3));
            } else if (publishDate >= 2000) {
                publishDates.add(new NameValue("Contemporáneos", "2000-2050", 4));
            }
        }
    }

    private void addBookLengthFilterOptions(Set<NameValue> numberOfPages, LibroEntity book) {
        Integer bookPages = book.getNumeroPaginas();
        if (bookPages != null) {
            if (bookPages < 200) {
                numberOfPages.add(new NameValue("Menos de 200 páginas", "0-199", 1));
            } else if (bookPages >= 200 && bookPages <= 400) {
                numberOfPages.add(new NameValue("De 200 a 400 páginas", "200-400", 2));
            } else {
                numberOfPages.add(new NameValue("Más de 400 páginas ", "401-9999", 3));
            }
        }
    }

    private void addNumberOfBooksFilterOptions(Set<NameValue> numberOfBooks, LibroEntity book) {
        if (book.getSaga() == null) {
            numberOfBooks.add(new NameValue("Un solo tomo", "1", 1));
        } else {
            int numberSagaBooks = book.getSaga().getLibros().size();
            if (numberSagaBooks == 1) {
                numberOfBooks.add(new NameValue("Un solo tomo", "1", 1));
            } else if (numberSagaBooks == 2) {
                numberOfBooks.add(new NameValue("Duología", "2", 2));
            } else if (numberSagaBooks == 3) {
                numberOfBooks.add(new NameValue("Trilogía", "3", 3));
            } else {
                numberOfBooks.add(new NameValue("Saga", ">3", 4));
            }
        }
    }

    private void addGenreFilterOptions(Set<NameValue> genreList, LibroEntity book, String urlGenre) {
        for (GenreEntity genreEntity : book.getGeneros()) {
            if (!genreEntity.getUrlGenero().equals(urlGenre)) {
                genreList.add(new NameValue(genreEntity.getNombre(), genreEntity.getUrlGenero()));
            }
        }
    }

    private NameValue getNameValue(FilterBook filterBook) {
        NameValue nameValue = null;
        Integer order = null;
        String valueToDivide = filterBook.getValue();
        if (valueToDivide.contains(")")) {
            List<String> divided = ConversionUtils.divide(valueToDivide, ")", 2);
            order = Integer.valueOf(divided.get(0).trim().substring(1));
            valueToDivide = divided.get(1).trim();
        }
        if (valueToDivide.contains("[")) {
            List<String> divided = ConversionUtils.divide(valueToDivide , "[");
            nameValue = new NameValue(divided.get(1).replace("]", "").trim(),
                    divided.get(0).trim(), order);
        } else {
            nameValue = new NameValue(valueToDivide, valueToDivide, order);
        }
        return nameValue;
    }

    private Predicate getPredicateForBetweenTypeFilter(CriteriaBuilder criteriaBuilder, Root book, Predicate whereSection, FilterDTO filter) {
        if (filter.getReferencedProperty() != null) {
            List<Predicate> filterList = new ArrayList<>();
            for (String value : filter.getValue()) {
                String valueToDivide = value;
                Integer orden = null;
                if (value.contains("[")) {
                    valueToDivide = ConversionUtils.divide(valueToDivide, "[").get(0);
                }
                if (value.contains(")")) {
                    valueToDivide = ConversionUtils.divide(valueToDivide, ")").get(1);
                }
                List<String> divided = ConversionUtils.divide(valueToDivide, "-");
                Predicate filterSection = criteriaBuilder.between(book.<Integer>get(
                        filter.getReferencedProperty()), Integer.valueOf(divided.get(0).trim()), Integer.valueOf(divided.get(1).trim()));
                filterList.add(filterSection);
            }
            if (filterList.size() > 1) {
                Predicate finalfilterSection = criteriaBuilder.disjunction();
                for (Predicate predicate : filterList) {
                    finalfilterSection = criteriaBuilder.or(finalfilterSection, predicate);
                }
                whereSection = criteriaBuilder.and(whereSection, finalfilterSection);
            } else {
                whereSection = criteriaBuilder.and(whereSection, filterList.get(0));
            }

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
