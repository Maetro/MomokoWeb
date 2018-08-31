package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.dto.filter.FilterDTO;

import java.util.List;

public interface IDynamicFilterRepository {
    List<String> getBookListWithAppliedFilters(String urlGenre, List<FilterDTO> filters);

    List<FilterDTO> getFilterListWithSelectedBooks(String urlGenre, List<String> urlBooks);
}
