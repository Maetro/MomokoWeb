/**
 * EditorialService.java 10-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.publisher;

import com.momoko.es.api.dto.EditorialDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.enums.errores.ErrorCreacionEditorial;


import java.util.List;

public interface EditorialService {

    List<EditorialDTO> recuperarEditoriales();

    EditorialDTO guardarEditorial(EditorialDTO editorial);

    EditorialEntity obtenerEditorialOCrear(EditorialEntity editorialEntity);

    List<LibroSimpleDTO> obtenerLibrosEditorial(String urlElemento, int numeroElementos, Integer numeroPagina);

    EditorialDTO obtenerEditorialByUrl(String urlElemento);

    Integer obtenerNumeroLibrosEditorial(String urlElemento);

    List<EntradaSimpleDTO> obtenerUltimasEntradasEditorial(String urlElemento, int numeroElementos,
                                                           Integer numeroPagina);

    List<ErrorCreacionEditorial> validarEditorial(EditorialDTO editorialDTO);
}
