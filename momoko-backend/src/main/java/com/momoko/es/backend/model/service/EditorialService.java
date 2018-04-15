/**
 * EditorialService.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.List;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.backend.model.entity.EditorialEntity;

public interface EditorialService {

    /**
     * Recuperar usuarios.
     *
     * @return the list
     */
    List<EditorialDTO> recuperarEditoriales();

    /**
     * Guardar editorial.
     *
     * @param editorial
     *            the editorial
     */
    EditorialDTO guardarEditorial(EditorialDTO editorial);

    /**
     * Obtener editorial o crear.
     *
     * @param editorialEntity
     *            the libro entity
     * @return the editorial entity
     */
    EditorialEntity obtenerEditorialOCrear(EditorialEntity editorialEntity);

    /**
     * Obtener libros editorial.
     *
     * @param urlElemento
     *            the url elemento
     * @param i
     *            the i
     * @param numeroPagina
     *            the numero pagina
     * @return the list
     */
    List<LibroSimpleDTO> obtenerLibrosEditorial(String urlElemento, int numeroElementos, Integer numeroPagina);

    /**
     * Obtener editorial by url.
     *
     * @param urlElemento
     *            the url elemento
     * @return the editorial dto
     */
    EditorialDTO obtenerEditorialByUrl(String urlElemento);

    /**
     * Obtener numero libros editorial.
     *
     * @param urlElemento
     *            the url elemento
     * @return the integer
     */
    Integer obtenerNumeroLibrosEditorial(String urlElemento);

    /**
     * Obtener ultimas entradas editorial.
     *
     * @param urlElemento
     *            the url elemento
     * @param numeroElementos
     *            the numero elementos
     * @param numeroPagina
     *            the numero pagina
     * @return the list
     */
    List<EntradaSimpleDTO> obtenerUltimasEntradasEditorial(String urlElemento, int numeroElementos,
            Integer numeroPagina);

}
