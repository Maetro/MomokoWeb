/**
 * InformacionGeneralResponse.java 01-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.momoko.es.api.dto.CategoriaDTO;

/**
 * The Class InformacionGeneralResponse.
 */
public class InformacionGeneralResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 779166290418781622L;

    private List<String> nombresEditoriales;

    private List<String> nombresAutores;

    private List<String> titulosLibros;

    private List<CategoriaDTO> categorias;

    /**
     * Gets the nombres editoriales.
     *
     * @return the nombres editoriales
     */
    public List<String> getNombresEditoriales() {
        return this.nombresEditoriales;
    }

    /**
     * Sets the nombres editoriales.
     *
     * @param nombresEditoriales
     *            the new nombres editoriales
     */
    public void setNombresEditoriales(final List<String> nombresEditoriales) {
        this.nombresEditoriales = nombresEditoriales;
    }

    /**
     * Gets the nombres autores.
     *
     * @return the nombres autores
     */
    public List<String> getNombresAutores() {
        return this.nombresAutores;
    }

    /**
     * Sets the nombres autores.
     *
     * @param nombresAutores
     *            the new nombres autores
     */
    public void setNombresAutores(final List<String> nombresAutores) {
        this.nombresAutores = nombresAutores;
    }

    /**
     * Gets the titulos libros.
     *
     * @return the titulos libros
     */
    public List<String> getTitulosLibros() {
        return this.titulosLibros;
    }

    /**
     * Sets the titulos libros.
     *
     * @param titulosLibros
     *            the new titulos libros
     */
    public void setTitulosLibros(final List<String> titulosLibros) {
        this.titulosLibros = titulosLibros;
    }

    /**
     * Gets the categorias.
     *
     * @return the categorias
     */
    public List<CategoriaDTO> getCategorias() {
        return this.categorias;
    }

    /**
     * Sets the categorias.
     *
     * @param categorias
     *            the new categorias
     */
    public void setCategorias(final List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

}
