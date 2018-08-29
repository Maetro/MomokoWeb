package com.momoko.es.jpa.model.repository.filter;

import com.momoko.es.api.dto.filter.FilterDTO;

import java.util.List;

public interface IDynamicFilterRepository {
    List<String> getBookListWithAppliedFilters(List<FilterDTO> filters);
}
