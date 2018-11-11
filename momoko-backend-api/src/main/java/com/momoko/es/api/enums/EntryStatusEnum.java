package com.momoko.es.api.enums;

public enum EntryStatusEnum {
    DRAFT(1, "Borrador"), PUBLISHED(2, "Publicada"), DELETED(3, "Borrada");

    private final int value;
    private final String name;

    EntryStatusEnum(final int newValue, final String name) {
        this.value = newValue;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static EntryStatusEnum getEntryStatus(final int entryStatus) {
        EntryStatusEnum result = null;
        switch (entryStatus) {
        case 1:
            result = EntryStatusEnum.DRAFT;
            break;
        case 2:
            result = EntryStatusEnum.PUBLISHED;
            break;
        case 3:
            result = EntryStatusEnum.DELETED;
            break;
        default:
            result = EntryStatusEnum.DELETED;
            break;
        }
        return result;
    }
}
