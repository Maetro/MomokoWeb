/**
 * NuevoComentarioRequest.java 12-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.request;

import java.io.Serializable;

/**
 * The Class NuevoComentarioRequest.
 */
public class NuevoComentarioRequest implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2115869324194118798L;

    /** The nombre. */
    private String nombre;

    /** The email. */
    private String email;

    /** The pagina web. */
    private String paginaWeb;

    /** The contenido. */
    private String contenido;

    /** The comentario respuesta. */
    private Integer comentarioRespuesta;

    /** The entrada id. */
    private Integer entradaId;

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the pagina web.
     *
     * @return the pagina web
     */
    public String getPaginaWeb() {
        return this.paginaWeb;
    }

    /**
     * Sets the pagina web.
     *
     * @param paginaWeb
     *            the new pagina web
     */
    public void setPaginaWeb(final String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Gets the contenido.
     *
     * @return the contenido
     */
    public String getContenido() {
        return this.contenido;
    }

    /**
     * Sets the contenido.
     *
     * @param contenido
     *            the new contenido
     */
    public void setContenido(final String contenido) {
        this.contenido = contenido;
    }

    /**
     * Gets the comentario respuesta.
     *
     * @return the comentario respuesta
     */
    public Integer getComentarioRespuesta() {
        return this.comentarioRespuesta;
    }

    /**
     * Sets the comentario respuesta.
     *
     * @param comentarioRespuesta
     *            the new comentario respuesta
     */
    public void setComentarioRespuesta(final Integer comentarioRespuesta) {
        this.comentarioRespuesta = comentarioRespuesta;
    }

    /**
     * Gets the entrada id.
     *
     * @return the entrada id
     */
    public Integer getEntradaId() {
        return this.entradaId;
    }

    /**
     * Sets the entrada id.
     *
     * @param entradaId
     *            the new entrada id
     */
    public void setEntradaId(final Integer entradaId) {
        this.entradaId = entradaId;
    }

}
