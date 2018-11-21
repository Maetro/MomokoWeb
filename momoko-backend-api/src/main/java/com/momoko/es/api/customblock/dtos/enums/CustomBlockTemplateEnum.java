package com.momoko.es.api.customblock.dtos.enums;

public enum CustomBlockTemplateEnum {
    BLOCK_ONLY("Solo el bloque central"), FOUR_LINKS_WITH_CONTENT("4 links a los lados y el contenido en el centro");

    private String message;

    CustomBlockTemplateEnum(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
