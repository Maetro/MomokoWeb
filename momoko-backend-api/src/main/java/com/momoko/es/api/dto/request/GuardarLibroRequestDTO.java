/**
 * GuardarLibroRequestDTO.java 11-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.dto.request;

import java.io.Serializable;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.PuntuacionDTO;

/**
 * The Class GuardarLibroRequestDTO.
 */
public class GuardarLibroRequestDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7256224466866865372L;

    private LibroDTO libro;

    private PuntuacionDTO puntuacion;

    public LibroDTO getLibro() {
        return this.libro;
    }

    public void setLibro(final LibroDTO libro) {
        this.libro = libro;
    }

    public PuntuacionDTO getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(final PuntuacionDTO puntuacion) {
        this.puntuacion = puntuacion;
    }

}
