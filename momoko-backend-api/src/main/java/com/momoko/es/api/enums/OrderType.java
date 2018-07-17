/**
 * TipoOrden.java 13-jul-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum OrderType {
    SCORE("score"), DATE("date");

    private final String nombre;

    OrderType(final String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public static OrderType getOrderTypeByValue(final String value) {
        if (value.equals("score")) {
            return OrderType.SCORE;
        } else if (value.equals("date")) {
            return OrderType.DATE;
        } else {
            return OrderType.DATE;
        }
    }

}
