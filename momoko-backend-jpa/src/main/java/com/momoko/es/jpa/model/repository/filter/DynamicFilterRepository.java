package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.filter.dto.FilterValueDTO;
import com.momoko.es.api.filter.enums.FilterRuleType;
import com.momoko.es.jpa.model.entity.GenreEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import com.momoko.es.jpa.model.entity.filter.FilterValueEntity;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;


@Repository
public class DynamicFilterRepository implements IDynamicFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getBookListWithAppliedFilters(String urlGenre, List<FilterDTO> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery().distinct(true);
        Root book = criteriaQuery.from(LibroEntity.class);
        Join entradas = book.join("entradas", JoinType.INNER);
        Join bookFilter = book.join("filters", JoinType.LEFT);
        Join filterEntity = bookFilter.join("filter", JoinType.LEFT);
        Join genre = book.join("generos", JoinType.LEFT);
        criteriaQuery.select(book.get("urlLibro"));

        Predicate urlGeneroFilter = criteriaBuilder.and(criteriaBuilder.equal(genre.get("urlGenero"), urlGenre));
        Predicate entradaAnalisisFilter = criteriaBuilder.and(criteriaBuilder.equal(entradas.get("tipoEntrada"), 2));
        Predicate whereSection = criteriaBuilder.and(urlGeneroFilter, entradaAnalisisFilter);
        List<FilterDTO> filtersToApply = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filters)) {
            for (FilterDTO filter : filters) {
                if (CollectionUtils.isNotEmpty(filter.getSelectedFilterValues())) {
                    if (filter.getSelectedFilterValues().size() == 1) {
                        filtersToApply.add(filter);
                    } else {
                        for (Integer filterValue : filter.getSelectedFilterValues()) {
                            FilterDTO dividedFilter = filter.creteCopy();
                            dividedFilter.setSelectedFilterValues(new ArrayList<>());
                            dividedFilter.getSelectedFilterValues().add(filterValue);
                            filtersToApply.add(dividedFilter);
                        }
                    }
                }
            }
        }
        Iterator<FilterDTO> filterIterator = filtersToApply.iterator();
        FilterDTO filterToApply = filterIterator.next();
        whereSection = addApplyFilter(criteriaBuilder, book, bookFilter, filterEntity, whereSection, filterToApply);


        criteriaQuery.where(whereSection);
        Query query = entityManager.createQuery(criteriaQuery);
        List<String> booksUrls = query.getResultList();
        while (filterIterator.hasNext()){
            booksUrls = applyInclusiveFilterToListOfBooks(booksUrls, urlGenre, filterIterator.next());
        }
        return booksUrls;
    }

    private List<String> applyInclusiveFilterToListOfBooks(List<String> booksUrls, String urlGenre, FilterDTO filterToApply) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery().distinct(true);
        Root book = criteriaQuery.from(LibroEntity.class);
        Join entradas = book.join("entradas", JoinType.INNER);
        Join bookFilter = book.join("filters", JoinType.LEFT);
        Join filterEntity = bookFilter.join("filter", JoinType.LEFT);
        Join genre = book.join("generos", JoinType.LEFT);
        criteriaQuery.select(book.get("urlLibro"));
        Predicate urlBooksFilter = criteriaBuilder.and(book.get("urlLibro").in(booksUrls));
        Predicate entradaAnalisisFilter = criteriaBuilder.and(criteriaBuilder.equal(entradas.get("tipoEntrada"), 2));
        Predicate whereSection = criteriaBuilder.and(urlBooksFilter, entradaAnalisisFilter);
        whereSection = addApplyFilter(criteriaBuilder, book, bookFilter, filterEntity, whereSection, filterToApply);

        criteriaQuery.where(whereSection);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate addApplyFilter(CriteriaBuilder criteriaBuilder, Root book, Join bookFilter, Join filterEntity, Predicate whereSection, FilterDTO filterToApply) {
        if (FilterRuleType.ENUM.equals(filterToApply.getFilterType())) {
            if (CollectionUtils.isNotEmpty(filterToApply.getSelectedFilterValues())) {
                Predicate enumFilter = criteriaBuilder.disjunction();
                enumFilter = getPredicateForEnumTypeFilter(criteriaBuilder, filterEntity, bookFilter, enumFilter, filterToApply);
                whereSection = criteriaBuilder.and(whereSection, enumFilter);
            }
        }
        if (FilterRuleType.BETWEEN.equals(filterToApply.getFilterType())) {
            if (CollectionUtils.isNotEmpty(filterToApply.getSelectedFilterValues())) {
                whereSection = getPredicateForBetweenTypeFilter(criteriaBuilder, book, whereSection, filterToApply);
            }
        }
        if (FilterRuleType.NUMBER_OF_PAGES.equals(filterToApply.getFilterType())) {
            if (CollectionUtils.isNotEmpty(filterToApply.getSelectedFilterValues())) {
                whereSection = getPredicateForBetweenTypeFilter(criteriaBuilder, book, whereSection, filterToApply);
            }
        }
        if (FilterRuleType.PUBLISH_DATE.equals(filterToApply.getFilterType())) {
            if (CollectionUtils.isNotEmpty(filterToApply.getSelectedFilterValues())) {
                whereSection = getPredicateForBetweenTypeFilter(criteriaBuilder, book, whereSection, filterToApply);
            }
        }
        if (FilterRuleType.SCORE.equals(filterToApply.getFilterType())) {
            if (CollectionUtils.isNotEmpty(filterToApply.getSelectedFilterValues())) {
                whereSection = getPredicateForBetweenTypeFilter(criteriaBuilder, book, whereSection, filterToApply);
            }
        }
        return whereSection;
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
        Set<FilterValueDTO> genreList = new HashSet<>();
        Set<FilterValueDTO> numberOfPages = new HashSet<>();
        Set<FilterValueDTO> numberOfBooks = new HashSet<>();
        Set<FilterValueDTO> scores = new HashSet<>();
        Set<FilterValueDTO> publishDates = new HashSet<>();
        for (Object[] value : filtersAndResults) {
            FilterEntity filterTemp = (FilterEntity) value[0];
            FilterBook filterBook = (FilterBook) value[1];
            LibroEntity book = (LibroEntity) value[2];
            if (isAcceptedFilter(urlGenre, filterTemp)) {
                if (filterBook != null) {
                    FilterDTO filterDTO = EntityToDTOAdapter.adaptFilter(filterTemp);
                    if (result.contains(filterDTO)) {
                        FilterValueDTO filterValueDTO = getNameValue(filterBook);

                        List<FilterValueDTO> possibleValues = result.get(result.indexOf(filterDTO)).getFilterValues();
                        if (!possibleValues.contains(filterValueDTO)) {
                            possibleValues.add(filterValueDTO);
                        }
                    } else {
                        if (filterDTO.getReferencedProperty() == null) {
                            filterDTO.getFilterValues().clear();
                            FilterValueDTO filterValueDTO = getNameValue(filterBook);
                            filterDTO.getFilterValues().add(filterValueDTO);
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


    private void addFilterToList(List<FilterDTO> result, Set<FilterValueDTO> values, String urlFilter, FilterRuleType type, int filterId, String filterName,
                                 String referencedProperty) {
        if (values.size() > 1) {
            FilterDTO newGenreFilter = new FilterDTO();
            newGenreFilter.setUrlFilter(urlFilter);
            newGenreFilter.setBasic(false);
            newGenreFilter.setFilterType(type);
            newGenreFilter.setFilterId(filterId);
            newGenreFilter.setNameFilter(filterName);
            newGenreFilter.setReferencedProperty(referencedProperty);
            newGenreFilter.setFilterValues(new ArrayList<>(values));
            result.add(newGenreFilter);
        }
    }

    private void addScoreFilterOptions(Set<FilterValueDTO> scores, LibroEntity book) {
        Integer score = book.getScore();
        if (score != null) {
            if (score < 5) {
                scores.add(new FilterValueDTO(10001,"Libros suspensos", "0-49", 1));
            } else if (score >= 50 && score < 60) {
                scores.add(new FilterValueDTO(10002,"5", "50-59", 2));
            } else if (score >= 60 && score < 70) {
                scores.add(new FilterValueDTO(10003,"6", "60-69", 3));
            } else if (score >= 70 && score < 80) {
                scores.add(new FilterValueDTO(10004,"7", "70-79", 4));
            } else if (score >= 80 && score < 90) {
                scores.add(new FilterValueDTO(10005,"8", "80-89", 5));
            } else if (score >= 90 && score < 100) {
                scores.add(new FilterValueDTO(10006,"9", "90-99", 6));
            } else if (score == 100) {
                scores.add(new FilterValueDTO(10007,"10", "100-100", 7));
            }
        }
    }

    private void addPublishDateFilterOptions(Set<FilterValueDTO> publishDates, LibroEntity book) {
        Integer publishDate = book.getAnoPublicacion();
        if (publishDate != null) {
            if (publishDate < 1799) {
                publishDates.add(new FilterValueDTO(10008,"Hasta el S. XVIII", "0-1799", 1));
            } else if (publishDate >= 1800 && publishDate < 1900) {
                publishDates.add(new FilterValueDTO(10009,"Libros del S. XIX", "1800-1899", 2));
            } else if (publishDate >= 1900 && publishDate < 2000) {
                publishDates.add(new FilterValueDTO(10010,"Libros del S. XX", "1900-1999", 3));
            } else if (publishDate >= 2000) {
                publishDates.add(new FilterValueDTO(10011,"Contemporáneos", "2000-2050", 4));
            }
        }
    }

    private void addBookLengthFilterOptions(Set<FilterValueDTO> numberOfPages, LibroEntity book) {
        Integer bookPages = book.getNumeroPaginas();
        if (bookPages != null) {
            if (bookPages < 200) {
                numberOfPages.add(new FilterValueDTO(10012,"Menos de 200 páginas", "0-199", 1));
            } else if (bookPages >= 200 && bookPages <= 400) {
                numberOfPages.add(new FilterValueDTO(10013,"De 200 a 400 páginas", "200-400", 2));
            } else {
                numberOfPages.add(new FilterValueDTO(10014,"Más de 400 páginas ", "401-9999", 3));
            }
        }
    }

    private void addNumberOfBooksFilterOptions(Set<FilterValueDTO> numberOfBooks, LibroEntity book) {
        if (book.getSaga() == null) {
            numberOfBooks.add(new FilterValueDTO(10015,"Un solo tomo", "1", 1));
        } else {
            int numberSagaBooks = book.getSaga().getLibros().size();
            if (numberSagaBooks == 1) {
                numberOfBooks.add(new FilterValueDTO(10015,"Un solo tomo", "1", 1));
            } else if (numberSagaBooks == 2) {
                numberOfBooks.add(new FilterValueDTO(10016,"Duología", "2", 2));
            } else if (numberSagaBooks == 3) {
                numberOfBooks.add(new FilterValueDTO(10017,"Trilogía", "3", 3));
            } else {
                numberOfBooks.add(new FilterValueDTO(10018,"Saga", ">3", 4));
            }
        }
    }

    private void addGenreFilterOptions(Set<FilterValueDTO> genreList, LibroEntity book, String urlGenre) {
        int cont = 10030;
        for (GenreEntity genreEntity : book.getGeneros()) {
            if (!genreEntity.getUrlGenero().equals(urlGenre)) {
                genreList.add(new FilterValueDTO(cont + genreEntity.getGeneroId(), genreEntity.getNombre(), genreEntity.getUrlGenero(), null));
            }
        }
    }

    private FilterValueDTO getNameValue(FilterBook filterBook) {
        FilterValueDTO filterValueDTO = new FilterValueDTO();
        FilterValueEntity valueToDivide = filterBook.getValue();
        filterValueDTO.setName(valueToDivide.getName());
        filterValueDTO.setValue(valueToDivide.getValue());
        filterValueDTO.setFilterValueId(valueToDivide.getFilterValueId());
        filterValueDTO.setOrder(valueToDivide.getFilterOrder());
        return filterValueDTO;
    }

    private Predicate getPredicateForBetweenTypeFilter(CriteriaBuilder criteriaBuilder, Root book, Predicate whereSection, FilterDTO filter) {
        if (filter.getReferencedProperty() != null) {
            List<Predicate> filterList = new ArrayList<>();
            for (Integer value : filter.getSelectedFilterValues()) {
                List<String> divided = ConversionUtils.divide(filter.getStringSelectedValues().get(0), "-");
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
        if (CollectionUtils.isNotEmpty(filter.getSelectedFilterValues())) {

            List<Predicate> filterList = new ArrayList<>();
            for (Integer value : filter.getSelectedFilterValues()) {
                Predicate filterSection = criteriaBuilder.equal(filterEntity.get("filterId"), filter.getFilterId());
                filterSection = criteriaBuilder.and(filterSection, criteriaBuilder.equal(bookFilter.get("value"),  value));
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
