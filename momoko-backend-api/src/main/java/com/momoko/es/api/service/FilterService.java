package com.momoko.es.api.service;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;

import java.util.List;

public interface FilterService {

    List<FilterDTO> getAllFilters();

    SaveFilterResponse saveFilter(FilterDTO filterDTO) throws Exception;

    FilterDTO getFilter(String urlFilter);

    FilterDTO getFilterById(Integer filterId);

    FilterDTO getFilterByUrl(String urlFilter);
}
