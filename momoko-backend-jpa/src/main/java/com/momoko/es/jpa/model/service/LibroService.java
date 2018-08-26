/**
 * LibroService.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.response.GuardarLibroResponse;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Interface LibroService.
 */
public interface LibroService {

    public List<LibroDTO> recuperarLibros();

    public LibroDTO guardarLibro(LibroDTO libroAGuardar) throws Exception;

    public List<String> obtenerListaNombresAutores();

    public List<String> obtenerListaNombresEditoriales();

    public List<String> obtenerListaTitulosLibros();

    public LibroDTO obtenerLibroConEntradas(String urlLibro);

    public ObtenerFichaLibroResponse obtenerFichaLibroResponse(String urlLibro);

    public List<LibroSimpleDTO> obtenerLibrosParecidos(LibroDTO libro, int i);

    List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(GenreDTO genero, int numeroLibros, int pagina);

    Integer obtenerNumeroLibrosConAnalisisGenero(GenreDTO generoDTO);

    List<LibroDTO> getBooksWithUrlIn(List<String> librosUrl);

    List<LibroDTO> obtenerLibros(List<String> librosSaga);

    EntradaDTO obtenerOpinionesLibro(String urlLibro);

    BigDecimal obtenerPuntucionMomokoLibro(String urlLibro);

    List<LibroDTO> getAllBooks();

    LibroDTO getBookForModification(String urlBook);

    GuardarLibroResponse saveBook(LibroDTO bookDTO);
}
