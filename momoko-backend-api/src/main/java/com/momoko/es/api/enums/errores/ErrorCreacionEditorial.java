package com.momoko.es.api.enums.errores;

/**
 * The enum Error creacion editorial.
 */
public enum ErrorCreacionEditorial {

    /**
     * Ok error creacion editorial.
     */
    OK("OK"), /**
     * The Falta nombre.
     */
    FALTA_NOMBRE("Falta el nombre de la editorial"), /**
     * Error desconocido error creacion editorial.
     */
    ERROR_DESCONOCIDO("ERROR_DESCONOCIDO");

    private final String texto;

    ErrorCreacionEditorial(final String texto) {
        this.texto = texto;
    }

    /**
     * Gets texto.
     *
     * @return the texto
     */
    public String getTexto() {
        return this.texto;
    }

}
