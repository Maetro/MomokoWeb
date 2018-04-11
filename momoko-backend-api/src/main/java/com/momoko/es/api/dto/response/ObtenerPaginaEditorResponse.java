/**
 * ObtenerPaginaEditorResponse.java 09-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.util.List;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;

/**
 * The Class ObtenerPaginaEditorResponse.
 */
public class ObtenerPaginaEditorResponse {

    /** The autor. */
    private UsuarioBasicoDTO autor;

    /** The nueve entradas editor. */
    private List<EntradaSimpleDTO> nueveEntradasEditor;

    /** The numero libros. */
    private Integer numeroLibros;

    /**
     * Gets the autor.
     *
     * @return the autor
     */
    public UsuarioBasicoDTO getAutor() {
        return this.autor;
    }

    /**
     * Sets the autor.
     *
     * @param autor
     *            the new autor
     */
    public void setAutor(final UsuarioBasicoDTO autor) {
        this.autor = autor;
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
    public Integer getNumeroLibros() {
        return this.numeroLibros;
    }

    /**
     * Sets the numero libros.
     *
     * @param numeroLibros
     *            the new numero libros
     */
    public void setNumeroLibros(final Integer numeroLibros) {
        this.numeroLibros = numeroLibros;
    }

}
