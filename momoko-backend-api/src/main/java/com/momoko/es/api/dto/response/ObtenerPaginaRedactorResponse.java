/**
 * ObtenerPaginaEditorResponse.java 09-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.util.List;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.RedactorDTO;

/**
 * The Class ObtenerPaginaEditorResponse.
 */
public class ObtenerPaginaRedactorResponse {

    /** The autor. */
    private RedactorDTO redactor;

    /** The nueve entradas editor. */
    private List<EntradaSimpleDTO> nueveEntradasEditor;

    /** The numero libros. */
    private Integer numeroEntradas;

    /**
     * Gets the autor.
     *
     * @return the autor
     */
    public RedactorDTO getRedactor() {
        return this.redactor;
    }

    /**
     * Sets the autor.
     *
     * @param redactor
     *            the new autor
     */
    public void setRedactor(final RedactorDTO redactor) {
        this.redactor = redactor;
    }

    /**
     * Gets the nueve entradas editor.
     *
     * @return the nueve entradas editor
     */
    public List<EntradaSimpleDTO> getNueveEntradasEditor() {
        return this.nueveEntradasEditor;
    }

    /**
     * Sets the nueve entradas editor.
     *
     * @param nueveEntradasEditor
     *            the new nueve entradas editor
     */
    public void setNueveEntradasEditor(final List<EntradaSimpleDTO> nueveEntradasEditor) {
        this.nueveEntradasEditor = nueveEntradasEditor;
    }

    /**
     * Gets the numero libros.
     *
     * @return the numero libros
     */
    public Integer getNumeroEntradas() {
        return this.numeroEntradas;
    }

    /**
     * Sets the numero libros.
     *
     * @param numeroLibros
     *            the new numero libros
     */
    public void setNumeroEntradas(final Integer numeroLibros) {
        this.numeroEntradas = numeroLibros;
    }

}
