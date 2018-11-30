package com.momoko.es.jpa.genre.service;

import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.genre.GenrePageResponse;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.api.enums.OrderType;
import com.momoko.es.api.enums.errores.ErrorCreacionGenero;
import com.momoko.es.api.filter.dto.FilterDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO saveGenre(GenreDTO generoDTO) throws Exception;

    CategoriaDTO obtenerCategoriaPorUrl(String urlCategoria);

    GenreDTO obtenerGeneroPorUrl(String urlGenero);

    List<CategoriaDTO> obtenerListaCategorias();

    List<GenreDTO> obtenerGenerosCategoria(CategoriaDTO categoriaDTO);

    List<LibroSimpleDTO> obtenerLibrosConOpinionesGeneroPorFecha(GenreDTO generoDTO, int numElements,
                                                                 Integer numeroPagina);


    List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorNota(GenreDTO generoDTO, int numElements,
                                                               Integer numeroPagina);

    GenrePageResponse getGenrePage(String genreUrl, Integer pageNumber, OrderType tipoOrden);

    ApplyFilterResponseDTO getBooksWithFilters(String urlGenre, List<FilterDTO> appliedFilters);

    List<ErrorCreacionGenero> validarGenero(GenreDTO generoDTO);
}
