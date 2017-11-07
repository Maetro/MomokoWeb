/**
 * TipoEntrada.java 03-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum TipoEntrada {
    NOTICIA(1), ANALISIS(2), VIDEO(3);

    private final int value;

    TipoEntrada(final int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return this.value;
    }
}
