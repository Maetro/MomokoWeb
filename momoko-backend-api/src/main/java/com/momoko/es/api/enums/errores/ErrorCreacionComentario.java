/**
 * ErrorCreacionComentario.java 12-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums.errores;

public enum ErrorCreacionComentario {
    FALTA_EMAIL("El email es obligatorio."), FALTA_NOMBRE("El nombre es obligatorio."), COMENTARIO_VACIO(
            "No se pueden crear comentarios sin contenido."), NO_SE_ENCUENTRA_ENTRADA(
                    "No se encuentra la entrada a la que se quiere asociar el comentario."), ERROR_EN_GUARDADO(
                            "Ocurrió un problema inesperado, intentalo de nuevo más tarde.");

    private String mensaje;

    ErrorCreacionComentario(final String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
