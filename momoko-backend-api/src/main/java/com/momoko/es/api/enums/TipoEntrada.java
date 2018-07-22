/**
 * TipoEntrada.java 03-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.api.enums;

public enum TipoEntrada {
    NOTICIA(1, "Noticia"), OPINIONES(2, "Opiniones"), MISCELANEOS(3, "Misceláneos"), VIDEO(4, "Vídeo"), ESPECIAL(5, "Especial");

    private final Integer value;
    private final String nombre;

    TipoEntrada(final Integer newValue, final String nombre) {
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
    public static TipoEntrada obtenerTipoEntrada(final int tipoEntrada) {
        TipoEntrada resultado = null;
        switch (tipoEntrada) {
        case 1:
            resultado = TipoEntrada.NOTICIA;
            break;
        case 2:
            resultado = TipoEntrada.OPINIONES;
            break;
        case 3:
            resultado = TipoEntrada.MISCELANEOS;
            break;
        case 4:
            resultado = TipoEntrada.VIDEO;
            break;
        case 5:
            resultado = TipoEntrada.ESPECIAL;
            break;
        default:
            resultado = TipoEntrada.MISCELANEOS;
            break;
        }
        return resultado;
    }
}
