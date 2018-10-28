package com.momoko.es.api.author.response;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;

import java.io.Serializable;
import java.util.List;

public class AuthorPageResponse implements Serializable {

    private AuthorDTO author;
    private List<LibroSimpleDTO> authorBooks;

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<LibroSimpleDTO> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<LibroSimpleDTO> authorBooks) {
        this.authorBooks = authorBooks;
    }


}
