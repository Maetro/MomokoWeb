package com.momoko.es.api.enums.errores;

public enum ErrorCreacionRedactor {

    OK("OK"), FALTA_NOMBRE("Falta el nombre del genero"), ERROR_DESCONOCIDO("ERROR_DESCONOCIDO");

    private final String texto;

    ErrorCreacionRedactor(final String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }

}
