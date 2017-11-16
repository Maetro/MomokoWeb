/**
 * EstadoEntradaEnum.java 11-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum EstadoEntradaEnum {
    BORRADOR(1, "Borrador"), PUBLICADA(2, "Publicada"), BORRADA(3, "Borrada");

    private final int value;
    private final String nombre;

    EstadoEntradaEnum(final int newValue, final String nombre) {
        this.value = newValue;
        this.nombre = nombre;
    }

    public int getValue() {
        return this.value;
    }

    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtener tipo entrada.
     *
     * @param tipoEntrada
     *            the tipo entrada
     * @return the tipo entrada
     */
    public static EstadoEntradaEnum obtenerTipoEntrada(final int tipoEntrada) {
        EstadoEntradaEnum resultado = null;
        switch (tipoEntrada) {
        case 1:
            resultado = EstadoEntradaEnum.BORRADOR;
            break;
        case 2:
            resultado = EstadoEntradaEnum.PUBLICADA;
            break;
        case 3:
            resultado = EstadoEntradaEnum.BORRADA;
            break;
        default:
            resultado = EstadoEntradaEnum.BORRADA;
            break;
        }
        return resultado;
    }
}
