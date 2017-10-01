/**
 * InformacionGeneralResponse.java 01-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * The Class InformacionGeneralResponse.
 */
public class InformacionGeneralResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 779166290418781622L;

    private List<String> nombresEditoriales;

    private List<String> nombresAutores;

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

}
