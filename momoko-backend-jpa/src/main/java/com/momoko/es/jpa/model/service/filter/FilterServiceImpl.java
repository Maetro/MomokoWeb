package com.momoko.es.jpa.model.service.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.NameValue;
import com.momoko.es.api.dto.filter.SaveFilterResponse;
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
import com.momoko.es.jpa.model.entity.filter.key.FilterBookId;
import com.momoko.es.jpa.model.repository.GeneroRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.filter.FilterBookRepository;
import com.momoko.es.jpa.model.repository.filter.FilterRepository;
import com.momoko.es.jpa.model.service.ValidadorService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired(required = false)
    private FilterRepository filterRepository;

    @Autowired(required = false)
    private FilterBookRepository filterBookRepository;

    @Autowired(required = false)
    private ValidadorService validatorService;

    @Autowired(required = false)
    private GeneroRepository genreRepository;

    @Autowired(required = false)
    private LibroRepository libroRepository;


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
        if (CollectionUtils.isNotEmpty(filterDTO.getBooks())) {
            List<String> urlsList = filterDTO.getBooks().stream().map(LibroDTO::getUrlLibro).collect(Collectors.toList());
            List<LibroEntity> books = this.libroRepository.findByUrlLibroIn(urlsList);
            List<FilterBook> filterBooks = new ArrayList<>();
            for (LibroEntity book : books) {
                filterBooks.add(new FilterBook(oldFilter, book));
            }
            oldFilter.setFilterBooks(filterBooks);
        }
        oldFilter.setNameFilter(filterDTO.getNameFilter());
        oldFilter.setUrlFilter(filterDTO.getUrlFilter());
        oldFilter.setPossibleValues(ConversionUtils.toPossibleValuesString(filterDTO.getPossibleValues()));
        FilterEntity updatedFilter = filterRepository.save(oldFilter);
        return EntityToDTOAdapter.adaptFilter(updatedFilter);
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
                filterDTO.setValue(Arrays.asList(filterBook.getValue()));
                filterList.add(filterDTO);
            }
        }

        return filterList;
    }

    public void saveBookFilters(final Integer libroId, final LibroDTO libroAGuardar) throws InstanceNotFoundException {
        if (CollectionUtils.isNotEmpty(libroAGuardar.getFilters())) {
            for (FilterDTO filter : libroAGuardar.getFilters()) {
                if (StringUtils.isBlank(filter.getReferencedProperty()) && filter.getValue() != null) {
                    FilterBookId id = new FilterBookId(filter.getFilterId(), libroId);
                    FilterBook oldfilter = filterBookRepository.findById(id).orElse(null);
                    if (oldfilter != null) {
                        oldfilter.setValue(filter.getValue().get(0));
                        this.filterBookRepository.save(oldfilter);
                    } else {
                        oldfilter = new FilterBook();
                        oldfilter.setValue(filter.getValue().get(0));
                        oldfilter.setId(id);
                        FilterEntity filterEntity = this.filterRepository.findById(filter.getFilterId())
                                .orElseThrow(() -> new InstanceNotFoundException("filter id: " + filter.getFilterId()));
                        LibroEntity bookEntity = this.libroRepository.findById(libroId)
                                .orElseThrow(() -> new InstanceNotFoundException("book id: " + libroId));
                        oldfilter.setBook(bookEntity);
                        oldfilter.setFilter(filterEntity);
                        this.filterBookRepository.save(oldfilter);
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
                NameValue namevalue = new NameValue();
                namevalue.setValue(filterDTO.getValue().get(0));
                namevalue.setName(filterDTO.getValue().get(0));
                if (finalFilterList.contains(filterDTO)) {
                    FilterDTO filter = finalFilterList.get(finalFilterList.indexOf(filterDTO));
                    if (!filter.getPossibleValues().contains(namevalue)) {
                        filter.getPossibleValues().add(namevalue);
                    }
                } else {
                    FilterDTO newFilterDTO = new FilterDTO();
                    newFilterDTO.setPossibleValues(new ArrayList<>());
                    newFilterDTO.getPossibleValues().add(namevalue);
                    newFilterDTO.setNameFilter(filterDTO.getNameFilter());
                    newFilterDTO.setFilterId(filterDTO.getFilterId());
                    newFilterDTO.setUrlFilter(filterDTO.getUrlFilter());
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

        String filterQuery = getFilterWhereSection(appliedFilters);
        this.filterBookRepository.getBookListWithAppliedFilters(urlGenre, filterQuery);

        if (CollectionUtils.isNotEmpty(appliedFilters)) {
            for (FilterDTO appliedFilter : appliedFilters) {
                Set<String> booksFilter = new HashSet<>();
                for (String value : appliedFilter.getValue()) {
                    List<String> booksValue = this.filterBookRepository.getBookListWithAppliedFilter(urlGenre,
                            appliedFilter.getFilterId(), value);
                    booksFilter.addAll(booksValue);
                }
                if (first) {
                    bookEntities.addAll(booksFilter);
                } else {
                    List<String> tempBookEntities = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(booksFilter)) {
                        for (String libroEntity : booksFilter) {
                            if (bookEntities.contains(libroEntity)){
                                tempBookEntities.add(libroEntity);
                            }
                        }
                    }
                    bookEntities = tempBookEntities;
                }
            }

        }
        List<LibroSimpleDTO> result = new ArrayList<>();

        List<LibroEntity> booksFound = this.libroRepository.findByUrlLibroIn(bookEntities);

        for (LibroEntity bookEntity : booksFound) {
            PuntuacionEntity puntuacion = null;
            if (CollectionUtils.isNotEmpty(bookEntity.getPuntuaciones())) {
                puntuacion = bookEntity.getPuntuaciones().iterator().next();
            }
            result.add(ConversionUtils.obtenerLibroSimpleDTO(bookEntity, puntuacion));
        }

        return result;
    }


    /*and (
        f.filter_id = 2 and (
            fb.value LIKE 'Marvel'
            OR
            fb.value LIKE 'DC Cómics'
        )
        and
        l.numero_paginas BETWEEN 100 AND 200
    )
    */
    private String getFilterWhereSection(List<FilterDTO> appliedFilters){
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(appliedFilters)){
            Iterator<FilterDTO> filterIterator = appliedFilters.iterator();
            stringBuilder.append("and (");
            while(filterIterator.hasNext()){
                FilterDTO filterDTO = filterIterator.next();
                if (CollectionUtils.isNotEmpty(filterDTO.getValue())){
                    Iterator<String> valueIterator = filterDTO.getValue().iterator();
                    stringBuilder.append("f.filter_id = " + filterDTO.getFilterId());
                    stringBuilder.append(" AND (");
                    while (valueIterator.hasNext()){

                        String value = valueIterator.next();
                        stringBuilder.append("fb.value LIKE '" + value + "'");
                        if (valueIterator.hasNext()){
                            stringBuilder.append(" OR ");
                        }
                    }
                    stringBuilder.append(")");
                    if (filterIterator.hasNext()){
                        stringBuilder.append(" AND ");
                    }
                }
            }
            stringBuilder.append(")");
        }
        
        return stringBuilder.toString();
    }

}
