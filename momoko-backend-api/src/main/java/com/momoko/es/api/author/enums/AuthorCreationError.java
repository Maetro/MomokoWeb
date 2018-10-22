package com.momoko.es.api.author.enums;

public enum AuthorCreationError {
    NO_URL("Se necesita una url para el autor"), URL_USED("La url ya esta siendo utilizada."), NO_NAME(
            "El autor necesita un nombre para poder ser guardado"), UNKNOWN("Error desconocido, avisa al administrador");

    private String message;

    AuthorCreationError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
