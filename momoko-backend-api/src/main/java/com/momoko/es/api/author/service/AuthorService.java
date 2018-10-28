package com.momoko.es.api.author.service;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.response.AuthorPageResponse;
import com.momoko.es.api.author.response.SaveAuthorResponse;
import com.momoko.es.api.dto.LibroDTO;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Set;

public interface AuthorService {

    Set<AuthorDTO> getAllAuthors();

    List<String> getAllAuthorNames();

    AuthorDTO getAuthorByUrl(String urlAuthor);

    Set<AuthorDTO> getOrSaveBookAuthorByName(LibroDTO bookDTO);

    SaveAuthorResponse saveAuthor(AuthorDTO authorDTO);

    AuthorDTO getAuthorById(Integer authorId) throws InstanceNotFoundException;

    AuthorPageResponse getAuthorAndAuthorBooksByUrl(String urlAuthor);
}
