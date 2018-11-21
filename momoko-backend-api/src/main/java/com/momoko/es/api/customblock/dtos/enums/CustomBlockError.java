package com.momoko.es.api.customblock.dtos.enums;

public enum CustomBlockError {

    UNKNOWN("Error desconocido, avisa al administrador");

    private String message;

    CustomBlockError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
