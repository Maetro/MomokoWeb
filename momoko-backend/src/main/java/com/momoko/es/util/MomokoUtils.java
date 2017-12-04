/**
 * MomokoUtils.java 23-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

public class MomokoUtils {

    /**
     * Solo nombre y carpeta.
     *
     * @param imagenDestacada
     *            the imagen destacada
     * @return the string
     */
    public static String soloNombreYCarpeta(final String imagenDestacada) {
        final String[] lista = imagenDestacada.split("/");
        final int elementos = lista.length;
        if (elementos >= 2) {
            return lista[elementos - 2] + "/" + lista[elementos - 1];
        } else {
            return imagenDestacada;
        }
    }

    /**
     * Obtener columna galeria.
     *
     * @param columnas
     *            the columnas
     * @return the string
     */
    public static String obtenerColumnaGaleria(final Integer columnas) {
        String classColumn = "";
        switch (columnas) {
        case 1:
            classColumn = "col-sm-12";
            break;
        case 2:
            classColumn = "col-sm-6";
            break;
        case 3:
            classColumn = "col-sm-4";
            break;
        case 4:
            classColumn = "col-sm-3";
            break;
        case 5:
            classColumn = "col-sm-2";
            break;
        case 6:
            classColumn = "col-sm-2";
            break;
        default:
            classColumn = "col-sm-12";
        }
        return classColumn;
    }

}
