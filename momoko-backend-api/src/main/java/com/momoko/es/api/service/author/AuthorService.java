package com.momoko.es.api.service.author;

import com.momoko.es.api.dto.filter.FilterDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();
}
