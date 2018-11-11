package com.momoko.es.api.enums;

public enum EntryTypeEnum {
    NEWS(1, "Noticia"), OPINION(2, "Opinión"), MISCELLANEOUS(3, "Miscelaneo"), VIDEO(4, "Vídeo"), SPECIAL(5, "Especial");

    private final Integer value;
    private final String name;

    EntryTypeEnum(final Integer newValue, final String name) {
        this.value = newValue;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static EntryTypeEnum getEntryType(final int entryType) {
        EntryTypeEnum result = null;
        switch (entryType) {
        case 1:
            result = EntryTypeEnum.NEWS;
            break;
        case 2:
            result = EntryTypeEnum.OPINION;
            break;
        case 3:
            result = EntryTypeEnum.MISCELLANEOUS;
            break;
        case 4:
            result = EntryTypeEnum.VIDEO;
            break;
        case 5:
            result = EntryTypeEnum.SPECIAL;
            break;
        default:
            result = EntryTypeEnum.MISCELLANEOUS;
            break;
        }
        return result;
    }
}
