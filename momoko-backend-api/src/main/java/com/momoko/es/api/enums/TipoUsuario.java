/**
 * TipoUsuario.java 20-abr-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum TipoUsuario {

    REDACTOR(1, "Redactor"), REGISTRADO(2, "Registrado");

    private final Integer value;
    private final String nombre;

    TipoUsuario(final Integer newValue, final String nombre) {
        this.value = newValue;
        this.nombre = nombre;
    }

    public Integer getValue() {
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
    public static TipoUsuario obtenerTipoEntrada(final int tipoEntrada) {
        TipoUsuario resultado = null;
        switch (tipoEntrada) {
        case 1:
            resultado = TipoUsuario.REDACTOR;
            break;
        case 2:
            resultado = TipoUsuario.REGISTRADO;
            break;
        default:
            resultado = TipoUsuario.REGISTRADO;
            break;
        }
        return resultado;
    }
}
