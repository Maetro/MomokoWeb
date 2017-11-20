/**
 * ErrorCreacionLibro.java 15-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum ErrorCreacionGenero {
    OK("OK"), FALTA_GENERO("Falta el nombre del genero"), GENERO_YA_EXISTE("El genero nuevo ya existe"), FALTA_URL(
            "Falta la URL del genero"), FALTA_IMAGEN_CABECERA("Falta la imagen de la cabecera"), FALTA_ICONO(
                    "Falta el icono del genero"), FALTA_CATEGORIA("Falta seleccionar la categoria del genero");

    private final String texto;

    ErrorCreacionGenero(final String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }
}
