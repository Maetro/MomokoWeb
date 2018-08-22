/**
 * StringToListAdpater.java 25-oct-2017
 * <p>
 * Copyright 2017 .
 * Departamento de Sistemas
 */
package com.momoko.es.jpa.model.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.momoko.es.api.dto.*;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.jpa.model.entity.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.*;
import java.util.regex.Pattern;

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
    public static List<String> divide(final String data, final String separator) {
        List<String> resultado = new ArrayList<String>();
        if (StringUtils.isNotBlank(data)) {
            final String[] separated = StringUtils.split(data, separator);
            resultado = new ArrayList<String>(Arrays.asList(separated));
        }
        return resultado;
    }

    /**
     * Divide.
     *
     * @param data
     *            data
     * @return the list
     */
    public static List<String> divide(final String data) {
        return divide(data, ",");
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
        usuarioBasico.setUrlUsuario(autor.getUsuarioUrl());
        return usuarioBasico;
    }

    /**
     * Obtener entradas basicas.
     *
     * @param listaEntities
     *            the lista entities
     * @return the list
     */
    public static List<EntradaSimpleDTO> obtenerEntradasBasicas(final List<EntradaEntity> listaEntities,
                                                                final boolean obtenerComentarios) {
        final List<EntradaSimpleDTO> entradasSimples = new ArrayList<EntradaSimpleDTO>();
        if (CollectionUtils.isNotEmpty(listaEntities)) {
            for (final EntradaEntity entrada : listaEntities) {
                final EntradaSimpleDTO entradaSimple = obtenerEntradaSimpleDTO(entrada, obtenerComentarios);
                entradasSimples.add(entradaSimple);
                if (CollectionUtils.isNotEmpty(entrada.getLibrosEntrada())) {
                    final LibroEntity libroEntrada = entrada.getLibrosEntrada().iterator().next();
                    entradaSimple.setUrlLibro(libroEntrada.getUrlLibro());
                    if (entradaSimple.getUrlMenuLibro() != null) {
                        entradaSimple.setBloque(entradaSimple.getUrlMenuLibro());
                    } else {
                        if (TipoEntrada.NOTICIA.getNombre().equals(entradaSimple.getTipoEntrada())) {
                            entradaSimple.setBloque("noticia");
                        }
                        if (TipoEntrada.OPINIONES.getNombre().equals(entradaSimple.getTipoEntrada())) {
                            entradaSimple.setBloque("opiniones");
                        }
                        if (TipoEntrada.MISCELANEOS.getNombre().equals(entradaSimple.getTipoEntrada())) {
                            entradaSimple.setBloque("miscelaneo");
                        }
                        if (TipoEntrada.VIDEO.getNombre().equals(entradaSimple.getTipoEntrada())) {
                            entradaSimple.setBloque("video");
                        }
                    }
                    final StringBuilder titulosLibros = new StringBuilder();
                    final Iterator<LibroEntity> ite = entrada.getLibrosEntrada().iterator();
                    while (ite.hasNext()) {
                        final LibroEntity libro = ite.next();
                        titulosLibros.append(libro.getTitulo());
                        if (ite.hasNext()) {
                            titulosLibros.append(", ");
                        }
                    }
                    if (entrada.getLibrosEntrada().size() == 1) {
                        entradaSimple.setUrlEditorial(
                                entrada.getLibrosEntrada().iterator().next().getEditorial().getUrlEditorial());
                    }
                    entradaSimple.setTitulosLibros(titulosLibros.toString());
                } else if (CollectionUtils.isNotEmpty(entrada.getSagasEntrada())) {
                    final SagaEntity sagaEntrada = entrada.getSagasEntrada().iterator().next();
                    entradaSimple.setUrlLibro(sagaEntrada.getUrlSaga());
                    if (entradaSimple.getUrlMenuLibro() != null) {
                        entradaSimple.setBloque(entradaSimple.getUrlMenuLibro());
                    } else {

                        if (TipoEntrada.OPINIONES.getNombre().equals(entradaSimple.getTipoEntrada())) {
                            entradaSimple.setBloque("opiniones");
                        }

                    }

                }

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
    public static EntradaSimpleDTO obtenerEntradaSimpleDTO(final EntradaEntity entrada,
                                                           final boolean obtenerComentarios) {
        final EntradaSimpleDTO entradaSimpleDTO = new EntradaSimpleDTO();
        entradaSimpleDTO.setTituloEntrada(entrada.getTituloEntrada());
        entradaSimpleDTO.setNombreAutor(entrada.getEntradaAutor().getUsuarioNick());
        if (obtenerComentarios) {
            entradaSimpleDTO.setNumeroComentarios(entrada.getComentarios().size());
        }
        entradaSimpleDTO.setUrlEntrada(entrada.getUrlEntrada());
        entradaSimpleDTO.setImagenEntrada(entrada.getImagenDestacada());
        entradaSimpleDTO.setUrlEditor(entrada.getEntradaAutor().getUsuarioUrl());
        entradaSimpleDTO.setFechaAlta(entrada.getFechaAlta());
        entradaSimpleDTO.setFechaModificacion(entrada.getFechaModificacion());
        if (obtenerComentarios) {
            entradaSimpleDTO.setCategoria(obtenerCategoriaDeEntrada(entrada));
        }
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
        entradaSimpleDTO.setNombreMenuLibro(entrada.getNombreMenuLibro());
        entradaSimpleDTO.setUrlMenuLibro(entrada.getUrlMenuLibro());

        return entradaSimpleDTO;
    }

    private static String obtenerCategoriaDeEntrada(final EntradaEntity entrada) {
        entrada.getTipoEntrada();
        return TipoEntrada.obtenerTipoEntrada(entrada.getTipoEntrada()).getNombre();
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
        final List<LibroSimpleDTO> librosSimples = new ArrayList<LibroSimpleDTO>();
        if (CollectionUtils.isNotEmpty(listaLibros)) {
            for (final LibroEntity libro : listaLibros) {
                final LibroSimpleDTO libroSimple = obtenerLibroSimpleDTO(libro,
                        mapaPuntacionMomokoPorLibro != null ? mapaPuntacionMomokoPorLibro.get(libro) : null);
                librosSimples.add(libroSimple);
            }
        }
        return librosSimples;
    }

    /**
     * Obtener libro simple dto.
     *
     * @param libro
     *            the libro
     * @return the libro simple dto
     */
    public static LibroSimpleDTO obtenerLibroSimpleDTO(final LibroEntity libro,
                                                       final PuntuacionEntity puntuacionEntity) {
        final LibroSimpleDTO libroSimpleDTO = new LibroSimpleDTO();
        libroSimpleDTO.setNombreAutor(libro.getAutores().iterator().next().getNombre());
        if (puntuacionEntity != null) {
            libroSimpleDTO.setNota(puntuacionEntity.getValor());
        }
        libroSimpleDTO.setPortada(libro.getUrlImagen());
        libroSimpleDTO.setTitulo(libro.getTitulo());
        libroSimpleDTO.setResumen(libro.getResumen());
        libroSimpleDTO.setUrlLibro(libro.getUrlLibro());
        libroSimpleDTO.setFechaAlta(libro.getFechaAlta());
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

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(final String input) {
        final String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        final String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        final String slug = NONLATIN.matcher(normalized).replaceAll("");
        final String urlSlug = eliminarEspaciosSeguidos(slug);
        return urlSlug.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Eliminar espacios seguidos.
     *
     * @param slug
     *            the slug
     * @return the string
     */
    private static String eliminarEspaciosSeguidos(String slug) {
        while (slug.contains("--")) {
            slug = slug.replace("--", "-");
        }
        return slug;
    }

    /**
     * Obtener gravatar.
     *
     * @param emailComentario
     *            the email comentario
     * @return the string
     */
    public static String obtenerGravatar(final String emailComentario) {
        final String hash = ConversionUtils.md5Hex(emailComentario.toLowerCase());
        String url = "https://www.gravatar.com/avatar/" + hash + "?s=120&d=404";
        final RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> respuesta = null;
        try {
            respuesta = restTemplate.getForEntity(url, String.class);
        } catch (final HttpServerErrorException e) {
            respuesta = null;
        } catch (final HttpClientErrorException e) {
            respuesta = null;
        }
        if ((respuesta == null) || !respuesta.getStatusCode().is2xxSuccessful()) {
            final Random r = new Random();
            final int number = r.nextInt((8 - 1) + 1) + 1;
            url = "https://momoko.es/images/avatares/random/avatar-momoko-0" + number + ".png";
        }
        return url;
    }

    /**
     * Gets the redactores from usuarios.
     *
     * @param redactoresEntity
     *            the redactores entity
     * @return the redactores from usuarios
     */
    public static List<RedactorDTO> getRedactoresFromUsuarios(final List<UsuarioEntity> redactoresEntity) {
        final List<RedactorDTO> redactores = new ArrayList<RedactorDTO>();
        if (CollectionUtils.isNotEmpty(redactoresEntity)) {
            for (final UsuarioEntity usuarioEntity : redactoresEntity) {
                redactores.add(getRedactorFromUsuario(usuarioEntity));
            }
        }
        return redactores;
    }

    /**
     * Gets the redactor from usuario.
     *
     * @param usuarioEntity
     *            the usuario entity
     * @return the redactor from usuario
     */
    public static RedactorDTO getRedactorFromUsuario(final UsuarioEntity usuarioEntity) {
        final RedactorDTO redactorDTO = new RedactorDTO();
        redactorDTO.setUsuarioId(usuarioEntity.getUsuarioId());
        redactorDTO.setNombre(usuarioEntity.getUsuarioLogin());
        redactorDTO.setEmail(usuarioEntity.getUsuarioEmail());
        redactorDTO.setNick(usuarioEntity.getUsuarioNick());
        redactorDTO.setImagenCabeceraRedactor(usuarioEntity.getImagenCabeceraRedactor());
        redactorDTO.setAvatarRedactor(usuarioEntity.getAvatarUrl());
        redactorDTO.setUrlRedactor(usuarioEntity.getUsuarioUrl());
        redactorDTO.setDescripcion(usuarioEntity.getDescripcion());
        redactorDTO.setTwitter(usuarioEntity.getTwitter());
        redactorDTO.setFacebook(usuarioEntity.getFacebook());
        redactorDTO.setInstagram(usuarioEntity.getInstagram());
        redactorDTO.setYoutube(usuarioEntity.getYoutube());
        redactorDTO.setPaginaWeb(usuarioEntity.getPaginaWeb());
        redactorDTO.setCargo(usuarioEntity.getCargo());
        redactorDTO.setFechaAlta(usuarioEntity.getFechaAlta());
        return redactorDTO;
    }

    /**
     * De json to info adicional dto.
     *
     * @param infoAdicionalJSON
     *            the info adicional json
     * @return the list
     * @throws JsonParseException
     *             the json parse exception
     * @throws JsonMappingException
     *             the json mapping exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static List<InfoAdicionalDTO> deJSONToInfoAdicionalDTO(final String infoAdicionalJSON) {

        final ObjectMapper mapper = new ObjectMapper();

        final TypeReference<List<InfoAdicionalDTO>> mapType = new TypeReference<List<InfoAdicionalDTO>>() {
        };
        List<InfoAdicionalDTO> lista = null;
        if (StringUtils.isNotEmpty(infoAdicionalJSON)) {
            try {
                lista = mapper.readValue(infoAdicionalJSON, mapType);
            } catch (final JsonParseException e) {
                e.printStackTrace();
            } catch (final JsonMappingException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    /**
     * De info adicional dto to json.
     *
     * @param lista
     *            the lista
     * @return the string
     * @throws JsonParseException
     *             the json parse exception
     * @throws JsonMappingException
     *             the json mapping exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static String deInfoAdicionalDTOToJSON(final List<InfoAdicionalDTO> lista)
            throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final String arrayToJson = mapper.writeValueAsString(lista);
        return arrayToJson;
    }

    public static List<DatoEntradaDTO> obtenerDatosEntradaFromEntradaEntityList(List<EntradaEntity> entradasList) {
        final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();
        if (CollectionUtils.isNotEmpty(entradasList)) {
            Collections.sort(entradasList);
            for (final EntradaEntity entradaRelacionadaEntity : entradasList) {
                final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                datoEntrada.setEnMenu(entradaRelacionadaEntity.isEnMenu());
                datoEntrada.setNombreMenuLibro(entradaRelacionadaEntity.getNombreMenuLibro());
                datoEntrada.setUrlMenuLibro(entradaRelacionadaEntity.getUrlMenuLibro());
                listaDatosEntradas.add(datoEntrada);
            }

        }
        return listaDatosEntradas;
    }

    public static List<LibroDTO> getDataForBookAdminTable(List<LibroEntity> bookEntities) {
        List<LibroDTO> books = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bookEntities)) {
            for (LibroEntity bookEntity : bookEntities) {
                LibroDTO bookDTO = new LibroDTO();
                bookDTO.setLibroId(bookEntity.getLibroId());
                bookDTO.setTitulo(bookEntity.getTitulo());
                bookDTO.setEditorial(EntityToDTOAdapter.adaptarEditorial(bookEntity.getEditorial()));
                bookDTO.setAutoresString(MomokoUtils.generarAutoresString(bookEntity));
                bookDTO.setGenerosString(MomokoUtils.generarGenerosString(bookEntity));
                bookDTO.setAnoEdicion(bookEntity.getAnoEdicion());
                bookDTO.setUrlLibro(bookEntity.getUrlLibro());
                books.add(bookDTO);
            }
        }
        return books;
    }
}
