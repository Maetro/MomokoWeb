package com.momoko.es.api.service.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface FilterService {

    List<FilterDTO> getAllFilters();

    SaveFilterResponse saveFilter(FilterDTO filterDTO) throws Exception;

    FilterDTO getFilter(String urlFilter);

    FilterDTO getFilterById(Integer filterId);

    FilterDTO getFilterByUrl(String urlFilter);

    List<FilterDTO> getFiltersAppliedByGenreUrl(List<String> urlGenre);

    List<FilterDTO> getBookFilterValues(List<String> bookUrl);

    void saveBookFilters(final Integer libroId, LibroDTO libroAGuardar) throws InstanceNotFoundException;

    List<FilterDTO> getFiltersAvaliableByUrlBookList(List<String> urlsList);

    List<LibroSimpleDTO> getBookListWithAppliedFilters(String urlGenre, List<FilterDTO> appliedFilters);

    List<FilterDTO> getFiltersByBookListAndGenre(String urlGenre, List<String> urlBooks);

    List<FilterDTO> getFilterListByGenre(String genreUrl);
}
