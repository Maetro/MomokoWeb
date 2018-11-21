package com.momoko.es.api.customblock.dtos.enums;

public enum CustomBlockTypeEnum {
    INDEX("Se mostrará en la portada de la página"), SIDEBAR("Se mostrará en la barra lateral de las entradas");

    private String message;

    CustomBlockTypeEnum(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
