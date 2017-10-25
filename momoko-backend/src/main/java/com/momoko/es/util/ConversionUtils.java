/**
 * StringToListAdpater.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.backend.model.entity.UsuarioEntity;

/**
 * The Class StringToListAdpater.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
public class ConversionUtils {

    /** The separator. */
    public static String SEPARATOR = ";";

    /**
     * Join.
     *
     * @param lista
     *            lista
     * @return the string
     */
    public static String join(List<String> lista) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(lista)) {
            Iterator<String> it = lista.iterator();
            while(it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(SEPARATOR);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Divide.
     *
     * @param data
     *            data
     * @return the list
     */
    public static List<String> divide(String data) {
        List<String> resultado = new ArrayList<String>();
        if (StringUtils.isNotBlank(data)) {
            String[] separated = StringUtils.split(data, SEPARATOR);
            resultado = new ArrayList<String>(Arrays.asList(separated));
        }
        return resultado;
    }

    /**
     * Obtener usuario basico.
     *
     * @param autor
     *            autor
     * @return the usuario basico DTO
     */
    public static UsuarioBasicoDTO obtenerUsuarioBasico(UsuarioEntity autor) {
        UsuarioBasicoDTO usuarioBasico = new UsuarioBasicoDTO();
        usuarioBasico.setAvatar(autor.getAvatarUrl());
        usuarioBasico.setCargo(autor.getCargo());
        usuarioBasico.setIdUsuario(autor.getUsuarioId());
        usuarioBasico.setNombre(autor.getUsuarioNick());
        return usuarioBasico;
    }

}
