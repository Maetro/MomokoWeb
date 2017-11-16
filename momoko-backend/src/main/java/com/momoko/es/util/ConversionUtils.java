/**
 * StringToListAdpater.java 25-oct-2017
 *
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
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
        entradaSimpleDTO.setTipoEntrada(TipoEntrada.obtenerTipoEntrada(entrada.getTipoEntrada()).getNombre());
        if (StringUtils.isNotBlank(entrada.getFraseDescriptiva())) {
            entradaSimpleDTO.setFraseDescriptiva(entrada.getFraseDescriptiva());
        } else if (StringUtils.isNotBlank(entrada.getResumenEntrada())) {
            entradaSimpleDTO
                    .setFraseDescriptiva(ConversionUtils.limpiarHTMLyRecortar(entrada.getResumenEntrada(), 200));
        } else {
            entradaSimpleDTO.setResumen(ConversionUtils.limpiarHTMLyRecortar(entrada.getContenidoEntrada(), 500));
            entradaSimpleDTO
                    .setFraseDescriptiva(ConversionUtils.limpiarHTMLyRecortar(entrada.getContenidoEntrada(), 200));
        }
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
     * @param mapaPuntacionMomokoPorLibro
     * @return the list
     */
    public static List<LibroSimpleDTO> obtenerLibrosBasicos(final List<LibroEntity> listaLibros,
            final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro) {
        final List<LibroSimpleDTO> liborsSimples = new ArrayList<LibroSimpleDTO>();
        if (CollectionUtils.isNotEmpty(listaLibros)) {
            for (final LibroEntity libro : listaLibros) {
                final LibroSimpleDTO libroSimple = obtenerLibroSimpleDTO(libro, mapaPuntacionMomokoPorLibro.get(libro));
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
    private static LibroSimpleDTO obtenerLibroSimpleDTO(final LibroEntity libro,
            final PuntuacionEntity puntuacionEntity) {
        final LibroSimpleDTO libroSimpleDTO = new LibroSimpleDTO();
        libroSimpleDTO.setNombreAutor(libro.getAutores().iterator().next().getNombre());
        if (puntuacionEntity != null) {
            libroSimpleDTO.setNota(puntuacionEntity.getValor());
        }
        libroSimpleDTO.setPortada(libro.getUrlImagen());
        libroSimpleDTO.setTitulo(libro.getTitulo());
        libroSimpleDTO.setUrlLibro(libro.getUrlLibro());
        return libroSimpleDTO;
    }

    /**
     * Limpiar htm ly recortar.
     *
     * @param resumenEntrada
     *            the resumen entrada
     * @param size
     *            the size
     * @return the string
     */
    public static String limpiarHTMLyRecortar(final String resumenEntrada, final int size) {
        String textoSinHtml = Jsoup.parse(resumenEntrada).text();
        if (textoSinHtml.length() > size) {
            textoSinHtml = textoSinHtml.substring(0, size);
            final int ultimoEspacio = textoSinHtml.lastIndexOf(' ');
            textoSinHtml = textoSinHtml.substring(0, ultimoEspacio) + " ...";
        }
        return textoSinHtml;
    }

    public static String hex(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String md5Hex(final String message) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (final NoSuchAlgorithmException e) {
        } catch (final UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * Crear mapa de lista por valor.
     *
     * @param <T>
     *            the generic type
     * @param <P>
     *            the generic type
     * @param lista
     *            the lista
     * @param campo
     *            the campo
     * @param keyClass
     *            the key class
     * @param objectClass
     *            the object class
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public static <T, P> Map<P, T> crearMapaDeListaPorValor(final List<T> lista, final String campo,
            final Class<P> keyClass, final Class<T> objectClass) {
        final Map<P, T> mapa = new HashMap<P, T>();
        if (CollectionUtils.isNotEmpty(lista)) {
            for (final T t : lista) {
                try {
                    for (final PropertyDescriptor pd : Introspector.getBeanInfo(objectClass).getPropertyDescriptors()) {
                        if ((pd.getReadMethod() != null) && pd.getName().equals(campo)) {
                            mapa.put((P) pd.getReadMethod().invoke(t), t);
                        }
                    }
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                } catch (final IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapa;
    }

}
