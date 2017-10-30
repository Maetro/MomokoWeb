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

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
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
    public static String join(final List<String> lista) {
        final StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(lista)) {
            final Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
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
    public static List<String> divide(final String data) {
        List<String> resultado = new ArrayList<String>();
        if (StringUtils.isNotBlank(data)) {
            final String[] separated = StringUtils.split(data, ",");
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
    public static UsuarioBasicoDTO obtenerUsuarioBasico(final UsuarioEntity autor) {
        final UsuarioBasicoDTO usuarioBasico = new UsuarioBasicoDTO();
        usuarioBasico.setAvatar(autor.getAvatarUrl());
        usuarioBasico.setCargo(autor.getCargo());
        usuarioBasico.setIdUsuario(autor.getUsuarioId());
        usuarioBasico.setNombre(autor.getUsuarioNick());
        return usuarioBasico;
    }

    /**
     * Obtener entradas basicas.
     *
     * @param listaEntities
     *            the lista entities
     * @return the list
     */
    public static List<EntradaSimpleDTO> obtenerEntradasBasicas(final List<EntradaEntity> listaEntities) {
        final List<EntradaSimpleDTO> entradasSimples = new ArrayList<EntradaSimpleDTO>();
        if (CollectionUtils.isNotEmpty(listaEntities)) {
            for (final EntradaEntity entradas : listaEntities) {
                final EntradaSimpleDTO entradaSimple = obtenerEntradaSimpleDTO(entradas);
                entradasSimples.add(entradaSimple);
            }
        }
        return entradasSimples;
    }

    /**
     * Obtener entrada simple dto.
     *
     * @param entrada
     *            the entrada
     * @return the entrada simple dto
     */
    private static EntradaSimpleDTO obtenerEntradaSimpleDTO(final EntradaEntity entrada) {
        final EntradaSimpleDTO entradaSimpleDTO = new EntradaSimpleDTO();
        entradaSimpleDTO.setTituloEntrada(entrada.getTituloEntrada());
        entradaSimpleDTO.setNombreAutor(entrada.getEntradaAutor().getUsuarioNick());
        entradaSimpleDTO.setNumeroComentarios(entrada.getComentarios().size());
        entradaSimpleDTO.setUrlEntrada(entrada.getUrlEntrada());
        entradaSimpleDTO.setImagenEntrada(entrada.getImagenDestacada());
        entradaSimpleDTO.setFechaAlta(entrada.getFechaAlta());
        entradaSimpleDTO.setCategoria(obtenerCategoriaDeEntrada(entrada));
        entradaSimpleDTO.setResumen(entrada.getResumenEntrada());
        return entradaSimpleDTO;
    }

    private static String obtenerCategoriaDeEntrada(final EntradaEntity entrada) {
        // TODO: RAMON Implementar
        return "MISCELÁNEOS";
    }

    /**
     * Obtener libros basicos.
     *
     * @param listaLibros
     *            the lista libros
     * @return the list
     */
    public static List<LibroSimpleDTO> obtenerLibrosBasicos(final List<LibroEntity> listaLibros) {
        final List<LibroSimpleDTO> liborsSimples = new ArrayList<LibroSimpleDTO>();
        if (CollectionUtils.isNotEmpty(listaLibros)) {
            for (final LibroEntity libro : listaLibros) {
                final LibroSimpleDTO libroSimple = obtenerLibroSimpleDTO(libro);
                liborsSimples.add(libroSimple);
            }
        }
        return liborsSimples;
    }

    /**
     * Obtener libro simple dto.
     *
     * @param libro
     *            the libro
     * @return the libro simple dto
     */
    private static LibroSimpleDTO obtenerLibroSimpleDTO(final LibroEntity libro) {
        final LibroSimpleDTO libroSimpleDTO = new LibroSimpleDTO();
        libroSimpleDTO.setNombreAutor(libro.getAutores().iterator().next().getNombre());
        // TODO: RAMON implementar
        libroSimpleDTO.setNota(0);
        libroSimpleDTO.setPortada(libro.getUrlImagen());
        libroSimpleDTO.setTitulo(libro.getTitulo());
        return libroSimpleDTO;
    }

}
