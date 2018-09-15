package com.momoko.es.jpa.model.service.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.FilterValueDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.FilterCreationError;
import com.momoko.es.api.service.FilterService;
import com.momoko.es.commons.security.UserAdminPermission;
import com.momoko.es.jpa.model.entity.GenreEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.PuntuacionEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import com.momoko.es.jpa.model.entity.filter.FilterValueEntity;
import com.momoko.es.jpa.model.entity.filter.key.FilterBookValueId;
import com.momoko.es.jpa.model.repository.GeneroRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.filter.FilterBookRepository;
import com.momoko.es.jpa.model.repository.filter.FilterRepository;
import com.momoko.es.jpa.model.repository.filter.FilterValueRepository;
import com.momoko.es.jpa.model.repository.filter.IDynamicFilterRepository;
import com.momoko.es.jpa.model.service.ValidadorService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;

    private final FilterValueRepository filterValueRepository;

    private final IDynamicFilterRepository dynamicFilterRepository;

    private final FilterBookRepository filterBookRepository;

    private final ValidadorService validatorService;

    private final GeneroRepository genreRepository;

    private final LibroRepository libroRepository;

    @Autowired(required = false)
    public FilterServiceImpl(FilterRepository filterRepository, IDynamicFilterRepository dynamicFilterRepository,
                             FilterBookRepository filterBookRepository, ValidadorService validatorService,
                             GeneroRepository genreRepository, LibroRepository libroRepository,
                             FilterValueRepository filterValueRepository) {
        this.filterRepository = filterRepository;
        this.dynamicFilterRepository = dynamicFilterRepository;
        this.filterBookRepository = filterBookRepository;
        this.validatorService = validatorService;
        this.genreRepository = genreRepository;
        this.libroRepository = libroRepository;
        this.filterValueRepository = filterValueRepository;
    }


    @Override
    public List<FilterDTO> getAllFilters() {
        Set<FilterEntity> filterEntities = filterRepository.findAll();
        return EntityToDTOAdapter.adaptFilters(filterEntities);
    }

    @Override
    @UserAdminPermission
    public SaveFilterResponse saveFilter(FilterDTO filterDTO) throws Exception {
        SaveFilterResponse response = new SaveFilterResponse();
        // Validar
        final List<FilterCreationError> listaErrores = this.validatorService.validateFilter(filterDTO);

        // Guardar
        FilterDTO savedFilterDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                if (filterDTO.getFilterId() != null) {
                    savedFilterDTO = updateFilter(filterDTO);
                } else {
                    savedFilterDTO = createFilter(filterDTO);
                }
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(FilterCreationError.UNKNOWN);
                response.setErrorMessage(e);
                response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
            }
        }

        // Responder
        response.setFilterDTO(savedFilterDTO);
        response.setListaErroresValidacion(listaErrores);

        if ((savedFilterDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            response.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }
        return response;
    }

    private FilterDTO createFilter(FilterDTO filterDTO) {
        FilterEntity filterEntity = DTOToEntityAdapter.adaptFilter(filterDTO);
        setApplicableGenresToFilter(filterDTO, filterEntity);
        FilterEntity savedFilterEntity = filterRepository.save(filterEntity);
        return EntityToDTOAdapter.adaptFilter(savedFilterEntity);
    }

    private FilterDTO updateFilter(FilterDTO filterDTO) throws InstanceNotFoundException {
        FilterEntity oldFilter = filterRepository.findById(filterDTO.getFilterId()).orElseThrow(() ->
                new InstanceNotFoundException("No se encuentra el filtro con el id: " + filterDTO.getFilterId()));
        setApplicableGenresToFilter(filterDTO, oldFilter);

        oldFilter.setNameFilter(filterDTO.getNameFilter());
        oldFilter.setUrlFilter(filterDTO.getUrlFilter());
        oldFilter.setBasic(filterDTO.isBasic());
        oldFilter.setInclusive(filterDTO.isInclusive());
        FilterEntity updatedFilter = filterRepository.save(oldFilter);
        if (CollectionUtils.isNotEmpty(filterDTO.getFilterValues())) {
            for (FilterValueDTO filterValueDTO : filterDTO.getFilterValues()) {
                if (filterValueDTO.getFilterValueId() == null) {
                    createFilterValue(updatedFilter, filterValueDTO);
                } else {
                    updateFilterValue(updatedFilter, filterValueDTO);
                }
            }
        }

        return EntityToDTOAdapter.adaptFilter(updatedFilter);
    }

    private void createFilterValue(FilterEntity updatedFilter, FilterValueDTO filterValueDTO) {
        if (StringUtils.isBlank(filterValueDTO.getName()) || StringUtils.isBlank(filterValueDTO.getValue())) {
            FilterValueEntity filterValueEntity = DTOToEntityAdapter.adaptFilterValue(updatedFilter, filterValueDTO);
            this.filterValueRepository.save(filterValueEntity);
        }
    }

    private void updateFilterValue(FilterEntity updatedFilter, FilterValueDTO filterValueDTO) throws InstanceNotFoundException {
        FilterValueEntity oldFilterValue = this.filterValueRepository.findById(filterValueDTO.getFilterValueId()).orElseThrow(() ->
                new InstanceNotFoundException(""));
        if (StringUtils.isBlank(filterValueDTO.getName()) || StringUtils.isBlank(filterValueDTO.getValue())) {
            this.filterValueRepository.deleteById(filterValueDTO.getFilterValueId());
        } else {
            oldFilterValue.setName(filterValueDTO.getName());
            oldFilterValue.setValue(filterValueDTO.getValue());
            oldFilterValue.setFilterOrder(filterValueDTO.getOrder());
            this.filterValueRepository.save(oldFilterValue);
        }
    }

    private void setApplicableGenresToFilter(FilterDTO filterDTO, FilterEntity filterEntity) {
        if (CollectionUtils.isNotEmpty(filterDTO.getGenres())) {
            List<String> urlsList = filterDTO.getGenres().stream().map(GenreDTO::getUrlGenero).collect(Collectors.toList());
            List<GenreEntity> genresFilter = genreRepository.findByUrlGeneroIn(urlsList);
            filterEntity.setApplicableGenres(genresFilter);
        }
    }

    @Override
    public FilterDTO getFilter(String urlFilter) {
        return null;
    }

    @Override
    public FilterDTO getFilterById(Integer filterId) {
        return null;
    }

    @Override
    public FilterDTO getFilterByUrl(String urlFilter) {
        FilterEntity filterEntity = filterRepository.findOneByUrlFilterIs(urlFilter);
        return EntityToDTOAdapter.adaptFilter(filterEntity);
    }

    @Override
    public List<FilterDTO> getFiltersAppliedByGenreUrl(List<String> urlGenres) {
        List<GenreEntity> genres = this.genreRepository.findByUrlGeneroIn(urlGenres);
        Set<FilterEntity> filterGenre = this.filterRepository.findAllByApplicableGenresIn(genres);
        Set<FilterEntity> genericFilters = this.filterRepository.findAllByApplicableGenresIsNull();
        filterGenre.addAll(genericFilters);
        return EntityToDTOAdapter.adaptFilters(filterGenre);
    }

    @Override
    public List<FilterDTO> getBookFilterValues(List<String> bookUrls) {
        final List<LibroEntity> books = this.libroRepository.findByUrlLibroIn(bookUrls);
        List<FilterDTO> filterList = new ArrayList<>();
        List<FilterBook> filterBooks = filterBookRepository.findAllByBookIn(books);
        if (CollectionUtils.isNotEmpty(filterBooks)) {
            for (FilterBook filterBook : filterBooks) {
                FilterDTO filterDTO = EntityToDTOAdapter.adaptFilter(filterBook.getFilter());
                if (filterList.contains(filterDTO)) {
                    filterList.get(filterList.indexOf(filterDTO)).getSelectedFilterValues().add(
                            filterBook.getValue().getFilterValueId());
                    filterList.get(filterList.indexOf(filterDTO)).getStringSelectedValues().add(
                            filterBook.getValue().getValue());

                } else {
                    filterDTO.setSelectedFilterValues(new ArrayList<>());
                    filterDTO.setStringSelectedValues(new ArrayList<>());
                    filterDTO.getSelectedFilterValues().add(filterBook.getValue().getFilterValueId());
                    filterDTO.getStringSelectedValues().add(filterBook.getValue().getValue());
                    filterList.add(filterDTO);
                }
            }
        }

        return filterList;
    }

    public void saveBookFilters(final Integer libroId, final LibroDTO libroAGuardar) throws InstanceNotFoundException {
        this.filterBookRepository.removeAllByBook_LibroIdIs(libroId);
        if (CollectionUtils.isNotEmpty(libroAGuardar.getFilters())) {
            for (FilterDTO filter : libroAGuardar.getFilters()) {
                if (CollectionUtils.isNotEmpty(filter.getSelectedFilterValues())) {
                    for (Integer valueId : filter.getSelectedFilterValues()) {
                        this.filterBookRepository.saveBookIdFilterIdAndValueIdEntry(libroId, filter.getFilterId(), valueId);
                    }
                }
            }
        }
    }

    @Override
    public List<FilterDTO> getFiltersAvaliableByUrlBookList(List<String> urlsList) {
        List<FilterDTO> filters = this.getBookFilterValues(urlsList);
        List<FilterDTO> finalFilterList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filters)) {
            for (FilterDTO filterDTO : filters) {
                FilterValueDTO namevalue = new FilterValueDTO();
//                namevalue.setValue(filterDTO.getSelectedFilterValues().get(0));
//                namevalue.setName(filterDTO.getSelectedFilterValues().get(0));
                if (finalFilterList.contains(filterDTO)) {
                    FilterDTO filter = finalFilterList.get(finalFilterList.indexOf(filterDTO));
                    if (!filter.getFilterValues().contains(namevalue)) {
                        filter.getFilterValues().add(namevalue);
                    }
                } else {
                    FilterDTO newFilterDTO = new FilterDTO();
                    newFilterDTO.setFilterValues(new ArrayList<>());
                    newFilterDTO.getFilterValues().add(namevalue);
                    newFilterDTO.setNameFilter(filterDTO.getNameFilter());
                    newFilterDTO.setFilterId(filterDTO.getFilterId());
                    newFilterDTO.setUrlFilter(filterDTO.getUrlFilter());
                    newFilterDTO.setBasic(filterDTO.isBasic());
                    newFilterDTO.setFilterType(filterDTO.getFilterType());
                    finalFilterList.add(newFilterDTO);
                }
            }
        }
        return finalFilterList;
    }

    @Override
    public List<LibroSimpleDTO> getBookListWithAppliedFilters(String urlGenre, List<FilterDTO> appliedFilters) {
        List<LibroDTO> books = new ArrayList<>();
        List<String> bookEntities = new ArrayList<>();
        boolean first = true;
        boolean variosGeneros = false;
        FilterDTO filterGenre = null;
        FilterDTO filterNumber = null;
        for (FilterDTO appliedFilter : appliedFilters) {
            if (FilterRuleType.GENRE.equals(appliedFilter.getFilterType())) {
                if (CollectionUtils.isNotEmpty(appliedFilter.getSelectedFilterValues())) {
                    variosGeneros = true;
                    filterGenre = appliedFilter;
                }
            }
            if (FilterRuleType.NUMBER_OF_BOOKS.equals(appliedFilter.getFilterType())) {
                if (CollectionUtils.isNotEmpty(appliedFilter.getSelectedFilterValues())) {
                    filterNumber = appliedFilter;
                }
            }
        }
        Collection<String> urlBooks = this.dynamicFilterRepository.getBookListWithAppliedFilters(urlGenre, appliedFilters);
        if (variosGeneros) {
//            for (String s : filterGenre.getSelectedFilterValues()) {
//                List<String> otherGenreBooks = this.dynamicFilterRepository.getBookListWithAppliedFilters(s, appliedFilters);
//                urlBooks = CollectionUtils.intersection(urlBooks, otherGenreBooks);
//            }
        }
        List<LibroSimpleDTO> result = new ArrayList<>();

        List<LibroEntity> booksFound = this.libroRepository.findByUrlLibroIn(new ArrayList<>(urlBooks));

        booksFound = getBookListFilteredByNumberOfBooks(filterNumber, booksFound);

        for (LibroEntity bookEntity : booksFound) {
            PuntuacionEntity puntuacion = null;
            if (CollectionUtils.isNotEmpty(bookEntity.getPuntuaciones())) {
                puntuacion = bookEntity.getPuntuaciones().iterator().next();
            }
            result.add(ConversionUtils.obtenerLibroSimpleDTO(bookEntity, puntuacion));
        }

        return result;
    }

    private List<LibroEntity> getBookListFilteredByNumberOfBooks(FilterDTO filterNumber, List<LibroEntity> booksFound) {
        if (filterNumber != null) {
            List<LibroEntity> filteredBooks = new ArrayList<>();
            for (LibroEntity libroEntity : booksFound) {
                Integer numberOfBooks = getNumberOfBooks(libroEntity);
                if (filterNumber.getSelectedFilterValues().contains("1") && numberOfBooks == 1) {
                    filteredBooks.add(libroEntity);
                } else if (filterNumber.getSelectedFilterValues().contains("2") && numberOfBooks == 2) {
                    filteredBooks.add(libroEntity);
                } else if (filterNumber.getSelectedFilterValues().contains("3") && numberOfBooks == 3) {
                    filteredBooks.add(libroEntity);
                } else if (filterNumber.getSelectedFilterValues().contains(">3") && numberOfBooks > 3) {
                    filteredBooks.add(libroEntity);
                }
            }
            booksFound = filteredBooks;
        }
        return booksFound;
    }

    private Integer getNumberOfBooks(LibroEntity libroEntity) {
        Integer numberOfBooks = 1;
        if (libroEntity.getSaga() != null) {
            numberOfBooks = libroEntity.getSaga().getLibros().size();
        }
        return numberOfBooks;
    }

    @Override
    public List<FilterDTO> getFiltersByBookListAndGenre(String urlGenre, List<String> urlBooks) {
        List<FilterDTO> filters = this.dynamicFilterRepository.getFilterListWithSelectedBooks(urlGenre, urlBooks);
        return filters;
    }

    @Override
    public List<FilterDTO> getFilterListByGenre(String genreUrl) {
        List<FilterDTO> filters = this.dynamicFilterRepository.getFilterListByGenre(genreUrl);
        return filters;
    }

}
