/**
 * EntradaServiceImpl.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.GeneroDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.UsuarioBasicoDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.api.youtube.list.Video;
import com.momoko.es.api.youtube.list.YoutubeVideoList;
import com.momoko.es.api.youtube.video.Item;
import com.momoko.es.api.youtube.video.VideoYoutube;
import com.momoko.es.backend.configuration.MomokoConfiguracion;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.GaleriaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.entity.VideoEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.repository.GaleriaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.repository.VideoRepository;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.GeneroService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;
import com.momoko.es.util.MomokoUtils;

/**
 * The Class EntradaServiceImpl.
 */
@Service
public class EntradaServiceImpl implements EntradaService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired(required = false)
    private GaleriaRepository galeriaRepository;

    @Autowired
    private StorageService almacenImagenes;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private MomokoConfiguracion momokoConfiguracion;

    /**
     * Obtener entrada para gestion.
     *
     * @param urlEntrada
     *            the url entrada
     * @return the entrada dto
     */
    @Override
    public ObtenerEntradaResponse obtenerEntradaParaGestion(final String urlEntrada) {
        final ObtenerEntradaResponse response = new ObtenerEntradaResponse();
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);

        entradaEntity
                .setImagenDestacada(this.almacenImagenes.obtenerImagenOriginal(entradaEntity.getImagenDestacada()));
        final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
        // Si es tipo video anadimos su URL
        if (entradaDTO.getTipoEntrada().equals(TipoEntrada.VIDEO.getValue())) {
            final VideoEntity videoEntity = this.videoRepository
                    .findFirstByEntradaUrlEntrada(entradaDTO.getUrlEntrada());
            if (videoEntity != null) {
                final String videoUrl = videoEntity.getUrlVideo();
                entradaDTO.setUrlVideo(videoUrl);
            }
        }
        obtenerImagenesContenidasEntrada(entradaDTO);
        response.setEntrada(entradaDTO);
        return response;
    }

    @Override
    public ObtenerEntradaResponse obtenerEntrada(final String urlEntrada, final boolean transformarGalerias) {
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        if (entradaEntity != null) {
            final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();
            final List<LibroSimpleDTO> librosParecidos = new ArrayList<LibroSimpleDTO>();

            final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
            try {
                entradaDTO.setImagenDestacada(
                        this.almacenImagenes.obtenerMiniatura(entradaDTO.getImagenDestacada(), 770, 432, true));
            } catch (final IOException e) {
                e.printStackTrace();
            }

            if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
                final List<LibroEntity> librosEntrada = entradaEntity.getLibrosEntrada();
                if (CollectionUtils.isNotEmpty(librosEntrada)) {
                    for (final LibroEntity libroEntrada : librosEntrada) {

                        final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                                .findByLibrosEntradaIn(Arrays.asList(libroEntrada));

                        Collections.sort(entradasRelacionadas);
                        if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
                            for (final EntradaEntity entradaRelacionadaEntity : entradasRelacionadas) {
                                final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                                datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                                datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                                datoEntrada.setEnMenu(entradaRelacionadaEntity.isEnMenu());
                                datoEntrada.setNombreMenuLibro(entradaRelacionadaEntity.getNombreMenuLibro());
                                datoEntrada.setUrlMenuLibro(entradaRelacionadaEntity.getUrlMenuLibro());
                                listaDatosEntradas.add(datoEntrada);
                            }
                        }

                        if (CollectionUtils.isNotEmpty(entradaDTO.getLibrosEntrada())) {
                            for (final LibroDTO libroDTO : entradaDTO.getLibrosEntrada()) {
                                libroDTO.setEntradasLibro(listaDatosEntradas);
                                librosParecidos.addAll(this.libroService.obtenerLibrosParecidos(libroDTO, 5));

                                final PuntuacionEntity puntuacion = this.puntuacionRepository
                                        .findOneByEsPuntuacionMomokoAndLibro(true, libroEntrada);
                                if (puntuacion != null) {
                                    libroDTO.setNotaMomoko(puntuacion.getValor());
                                }
                            }

                        }
                    }
                    respuesta.setCincoLibrosParecidos(
                            librosParecidos.subList(0, librosParecidos.size() > 5 ? 5 : librosParecidos.size()));
                    if (CollectionUtils.isNotEmpty(librosParecidos)) {
                        final String url = this.almacenImagenes.getUrlImageServer();
                        for (final LibroSimpleDTO libroSimple : librosParecidos) {

                            libroSimple.setPortada(url + libroSimple.getPortada());

                        }
                    }
                }
            } else {

                final List<EntradaSimpleDTO> cuatroPostPequenosConImagen = obtener4PostPequenosConImagen(
                        entradaEntity.getEntradaId());
                for (final EntradaSimpleDTO entradaSimpleDTO : cuatroPostPequenosConImagen) {
                    try {
                        entradaSimpleDTO.setImagenEntrada(this.almacenImagenes
                                .obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 137, 100, true));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
                respuesta.setCuatroPostPequenosConImagen(cuatroPostPequenosConImagen);
            }

            respuesta.setEntradaAnteriorYSiguiente(obtenerEntradaAnteriorYSiguiente(entradaEntity.getFechaAlta()));
            // Si es tipo video anadimos su URL
            if (entradaDTO.getTipoEntrada().equals(TipoEntrada.VIDEO.getValue())) {
                final VideoEntity videoEntity = this.videoRepository
                        .findFirstByEntradaUrlEntrada(entradaDTO.getUrlEntrada());
                if (videoEntity != null) {
                    final String videoUrl = videoEntity.getUrlVideo();
                    entradaDTO.setUrlVideo(videoUrl);
                }
            }

            final List<ComentarioDTO> comentarios = this.comentarioService
                    .obtenerComentariosEntrada(entradaDTO.getEntradaId());

            final List<ComentarioDTO> comentariosOrdenados = new ArrayList<ComentarioDTO>();
            final Map<Integer, ComentarioDTO> mapaComentarios = new HashMap<Integer, ComentarioDTO>();
            for (final ComentarioDTO comentarioDTO : comentarios) {
                if (comentarioDTO.getComentarioPadreId() == null) {
                    mapaComentarios.put(comentarioDTO.getComentarioId(), comentarioDTO);
                } else {
                    final ComentarioDTO comentarioPadre = mapaComentarios.get(comentarioDTO.getComentarioPadreId());
                    if (comentarioPadre != null) {
                        List<ComentarioDTO> respuestas = comentarioPadre.getComentariosHijo();
                        if (CollectionUtils.isEmpty(respuestas)) {
                            respuestas = new ArrayList<ComentarioDTO>();
                        }
                        respuestas.add(comentarioDTO);
                        comentarioPadre.setComentarioReferencia(respuestas);
                        mapaComentarios.put(comentarioDTO.getComentarioId(), comentarioDTO);
                    } else {
                        System.out.println("WOLA");
                    }
                }
            }

            for (final ComentarioDTO comentarioDTO : mapaComentarios.values()) {
                if (comentarioDTO.getComentarioPadreId() == null) {
                    comentariosOrdenados.add(comentarioDTO);
                }
            }
            entradaDTO.setNumeroComentarios(comentarios.size());
            if (transformarGalerias) {
                obtenerGaleriasEntrada(entradaDTO);
            }
            obtenerImagenesContenidasEntrada(entradaDTO);

            if (transformarGalerias) {
                obtenerBloqueslibroEntrada(entradaDTO);
                obtenerGifs(entradaDTO);
            }

            if (CollectionUtils.isNotEmpty(entradaDTO.getLibrosEntrada())) {
                for (final LibroDTO libroDTO : entradaDTO.getLibrosEntrada()) {
                    final String url = this.almacenImagenes.getUrlImageServer();
                    final Set<GeneroDTO> generosImagenes = new HashSet<GeneroDTO>();
                    for (final GeneroDTO generoDTO : libroDTO.getGeneros()) {
                        generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                        generosImagenes.add(generoDTO);
                    }
                    libroDTO.setGeneros(generosImagenes);
                }
            }

            if (entradaDTO.getAutor() != null) {
                final UsuarioBasicoDTO autor = entradaDTO.getAutor();
                if (autor.getAvatar() != null) {
                    try {
                        autor.setAvatar(this.almacenImagenes.obtenerMiniatura(autor.getAvatar(), 120, 120, true));
                    } catch (final IOException e) {
                        autor.setAvatar(
                                ConversionUtils.obtenerGravatar(entradaEntity.getEntradaAutor().getUsuarioEmail()));
                    }

                } else {
                    autor.setAvatar(ConversionUtils.obtenerGravatar(entradaEntity.getEntradaAutor().getUsuarioEmail()));
                }
                autor.setCargo(entradaEntity.getEntradaAutor().getCargo());
                entradaDTO.setAutor(autor);
            }

            respuesta.setEntrada(entradaDTO);
            respuesta.setComentarios(comentariosOrdenados);
        }
        return respuesta;
    }

    private void obtenerGifs(final EntradaDTO entradaDTO) {

        while (entradaDTO.getContenidoEntrada().contains("[gif ")) {
            final String gif = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[gif ", "]");
            final String url = this.almacenImagenes.getUrlImageServer();
            final String code = "<img src=\"" + url + "gifs/" + gif.trim() + ".gif\" />";
            entradaDTO.setContenidoEntrada(
                    StringUtils.replace(entradaDTO.getContenidoEntrada(), "[gif " + gif + "]", code));
        }
    }

    private void obtenerBloqueslibroEntrada(final EntradaDTO entradaDTO) {

        while (entradaDTO.getContenidoEntrada().contains("[momoko-libro ")) {
            final String bloquelibro = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[momoko-libro ",
                    "]");

            final String titulo = StringUtils.substringBetween(bloquelibro, "titulo=\"", "\"");
            final String autor = StringUtils.substringBetween(bloquelibro, "autor=\"", "\"");
            final String texto = StringUtils.substringBetween(bloquelibro, "texto=\"", "\"");
            final String colorFondo = StringUtils.substringBetween(bloquelibro, "colorFondo=\"", "\"");
            final String posicionLibro = StringUtils.substringBetween(bloquelibro, "posicionLibro=\"", "\"");
            final String img = StringUtils.substringBetween(bloquelibro, "img=\"", "\" ");
            final String code = generarBloqueLibro(img, titulo, autor, texto, colorFondo, posicionLibro);
            entradaDTO.setContenidoEntrada(
                    StringUtils.replace(entradaDTO.getContenidoEntrada(), "[momoko-libro " + bloquelibro + "]", code));
        }
    }

    private String generarBloqueLibro(final String imagen, final String titulo, final String autor, final String texto,
            final String colorFondo, final String posicionLibro) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (colorFondo.toLowerCase().equals("negro")) {
            stringBuilder.append("<div class=\"row dark-wrapper\">");
        } else {
            stringBuilder.append("<div class=\"row light-wrapper\">");
        }
        if (posicionLibro.toLowerCase().equals("right")) {
            stringBuilder.append("<div class=\"imagenLibro\" style=\"float:right\">");
            stringBuilder.append(imagen);
        } else {
            stringBuilder.append("<div class=\"imagenLibro\" style=\"float:left\">");
            stringBuilder.append(imagen);
        }
        stringBuilder.append("</div>");

        if (StringUtils.isNotEmpty(titulo)) {
            stringBuilder.append("<div class=\"tituloBloqueLibro\">");
            stringBuilder.append("<h3>" + titulo + "</h3>");
            stringBuilder.append("</div>");
        }

        if (StringUtils.isNotEmpty(autor)) {
            stringBuilder.append("<div class=\"autorBloqueLibro\">");
            stringBuilder.append("<h4>" + autor + "</h4>");
            stringBuilder.append("</div>");
        }

        if (StringUtils.isNotEmpty(texto)) {
            stringBuilder.append("<div class=\"textoBloqueLibro\">");
            stringBuilder.append("<p>" + texto + "</p>");
            stringBuilder.append("</div>");
        }
        stringBuilder.append("</div>");

        return stringBuilder.toString();

    }

    /**
     * Obtener galerias entrada.
     *
     * @param entradaDTO
     *            the entrada dto
     */
    public void obtenerGaleriasEntrada(final EntradaDTO entradaDTO) {
        int numeroGaleria = 0;
        while (entradaDTO.getContenidoEntrada().contains("[momoko-galeria-")) {
            final String urlGaleria = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[momoko-galeria-",
                    "]");
            final GaleriaEntity galeria = this.galeriaRepository.findOneByUrlGaleria(urlGaleria);
            if (galeria != null) {
                entradaDTO.setTieneGaleria(true);

                final String code = generarCodigoGaleria(galeria, numeroGaleria);
                entradaDTO.setContenidoEntrada(StringUtils.replace(entradaDTO.getContenidoEntrada(),
                        "[momoko-galeria-" + urlGaleria + "]", code));
                numeroGaleria++;
            } else {
                break;
            }
        }
    }

    /**
     * Obtener imagenes contenidas entrada.
     *
     * @param entradaDTO
     *            the entrada dto
     */
    public void obtenerImagenesContenidasEntrada(final EntradaDTO entradaDTO) {
        if (entradaDTO.getContenidoEntrada().contains("contenido-entrada/")) {
            final StringBuilder builder = new StringBuilder();
            final String[] partes = entradaDTO.getContenidoEntrada().split("contenido-entrada/");
            final String imageServer = this.almacenImagenes.getUrlImageServer();
            int numParte = 0;
            for (final String parte : partes) {
                builder.append(parte);
                if (partes.length != (numParte + 1)) {
                    builder.append(imageServer + "contenido-entrada/");
                }
                numParte++;
            }
            entradaDTO.setContenidoEntrada(builder.toString());
        }
    }

    private String generarCodigoGaleria(final GaleriaEntity galeria, final int numeroGaleria) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<div class=\"light-wrapper\"><div class=\"container-fluid inner2 tp0\"><div class=\"collage-wrapper\"><div id=\"collage-large-"
                        + numeroGaleria + "\" class=\"collage effect-parent light-gallery\">");
        final List<String> imagenes = ConversionUtils.divide(galeria.getImagenes(), ";");
        final Integer columnas = galeria.getColumnas();
        final String classColumn = MomokoUtils.obtenerColumnaGaleria(columnas);

        final String imageServer = this.almacenImagenes.getUrlImageServer();
        int columna = 0;
        for (final String imagen : imagenes) {
            if ((columna != 0) && ((columna % columnas) == 0)) {
                // stringBuilder.append("<div class=\"clearfix\"></div>");
            }
            AnchuraAlturaDTO anchuraAltura = null;
            try {
                anchuraAltura = this.almacenImagenes.getImageDimensions(imageServer + imagen);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append("<div class=\"collage-image-wrapper\"><div class=\"overlay\">");
            stringBuilder.append("<a href=\"" + imageServer + imagen + "\" class=\"lgitem\" data-sub-html=\"#caption"
                    + columna + "\"><img src=\"" + imageServer + imagen + "\" style=\"width:"
                    + anchuraAltura.getAnchura() + "px; height:" + anchuraAltura.getAltura() + "px;\" /></a>");
            stringBuilder.append("</div></div>");
            columna++;
        }

        stringBuilder.append("</div></div></div></div>");
        return stringBuilder.toString();
    }

    /**
     * Obtener4 post pequenos con imagen.
     *
     * @param entradaId
     *            the entrada id
     * @return the list
     */
    private List<EntradaSimpleDTO> obtener4PostPequenosConImagen(final Integer entradaId) {
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .seleccionarEntradasAleatorias(entradaId, new PageRequest(0, 4)).getContent();
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    /**
     * Obtener entrada anterior y siguiente.
     *
     * @param fechaAlta
     *            the fecha alta
     * @return the list
     */
    private List<EntradaSimpleDTO> obtenerEntradaAnteriorYSiguiente(final Date fechaAlta) {
        final List<EntradaEntity> entradaAnterior = this.entradaRepository
                .findEntradaMiscelaneosAnteriorAFecha(fechaAlta, new PageRequest(0, 1)).getContent();

        final List<EntradaEntity> entradaPosterior = this.entradaRepository
                .findEntradaMiscelaneosPosteriorAFecha(fechaAlta, new PageRequest(0, 1)).getContent();

        final List<EntradaEntity> listaEntities = new ArrayList<EntradaEntity>();
        if (CollectionUtils.isNotEmpty(entradaAnterior)) {
            listaEntities.add(entradaAnterior.iterator().next());
        }
        if (CollectionUtils.isNotEmpty(entradaPosterior)) {
            listaEntities.add(entradaPosterior.iterator().next());
        }
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, false);
    }

    @Override
    public List<EntradaDTO> recuperarEntradas() {
        final List<EntradaDTO> entradasDTO = new ArrayList<EntradaDTO>();
        final Iterable<EntradaEntity> entradasEntity = this.entradaRepository.findAll();
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        for (final EntradaEntity entradaEntity : entradasEntity) {
            final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
            entradaDTO.setImagenDestacada(imageServer + entradaDTO.getImagenDestacada());

            entradaDTO.setTitulosLibrosEntrada(obtenerTitulosLibrosEntrada(entradaEntity));
            entradaEntity.getLibrosEntrada();
            entradasDTO.add(entradaDTO);
        }
        return entradasDTO;
    }

    @Override
    public List<EntradaSimpleDTO> recuperarEntradasSimples() {
        final List<EntradaEntity> entradasEntity = this.entradaRepository.findAll();
        final List<EntradaSimpleDTO> entradasBasicas = ConversionUtils.obtenerEntradasBasicas(entradasEntity, false);
        return entradasBasicas;
    }

    /**
     * Obtener titulos libros entrada.
     *
     * @param entradaEntity
     *            the entrada entity
     * @param entradaDTO
     *            the entrada dto
     * @return
     */
    public List<String> obtenerTitulosLibrosEntrada(final EntradaEntity entradaEntity) {
        final List<String> titulosLibros = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            for (final LibroEntity libro : entradaEntity.getLibrosEntrada()) {
                titulosLibros.add(libro.getTitulo());
            }
        }
        return titulosLibros;
    }

    @Override
    @Transactional
    public EntradaDTO guardarEntrada(final EntradaDTO entradaAGuardar) throws Exception {
        final List<LibroDTO> librosEntrada = new ArrayList<LibroDTO>();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final List<String> titulosLibrosEntrada = entradaAGuardar.getTitulosLibrosEntrada();
        int cont = 0;
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        final String localFolder = this.almacenImagenes.getImageFolder();
        while (entradaAGuardar.getContenidoEntrada().contains("data:image/png;base64,")
                || entradaAGuardar.getContenidoEntrada().contains("data:image/jpeg;base64,")
                || entradaAGuardar.getContenidoEntrada().contains("data:image/gif;base64,")) {
            // Hay imagenes en base 64
            String extension = "png";
            int comienzo = entradaAGuardar.getContenidoEntrada().indexOf("data:image/png;base64,");
            if (comienzo == -1) {
                comienzo = entradaAGuardar.getContenidoEntrada().indexOf("data:image/jpeg;base64,");
                extension = "jpg";
            }
            if (comienzo == -1) {
                comienzo = entradaAGuardar.getContenidoEntrada().indexOf("data:image/gif;base64,");
                extension = "gif";

            }
            final int fin = entradaAGuardar.getContenidoEntrada().indexOf("\"", comienzo);
            final String imagenBase64 = entradaAGuardar.getContenidoEntrada().substring(comienzo, fin);

            // entradaAGuardar.getContenidoEntrada().replaceFirst(regex, replacement);
            // tokenize the data
            final byte[] imageBytes = DatatypeConverter.parseBase64Binary(imagenBase64.substring(22));
            final BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

            final String carpeta = "contenido-entrada/" + entradaAGuardar.getUrlEntrada();
            String nombreImagen = entradaAGuardar.getUrlEntrada() + cont;
            this.almacenImagenes.crearCarpetaSiNoExiste("/" + carpeta);
            String url = imageServer + carpeta + "/" + nombreImagen + "." + extension;
            while (FileSystemStorageService.exists(url)) {
                cont++;
                nombreImagen = entradaAGuardar.getUrlEntrada() + cont;
                url = imageServer + carpeta + "/" + nombreImagen + "." + extension;
            }

            this.almacenImagenes.store(img, carpeta, nombreImagen, extension);

            entradaAGuardar.setContenidoEntrada(entradaAGuardar.getContenidoEntrada().replace(imagenBase64,
                    carpeta + "/" + nombreImagen + "." + extension));
            cont++;
        }

        while (entradaAGuardar.getContenidoEntrada().contains(imageServer)) {
            entradaAGuardar.setContenidoEntrada(entradaAGuardar.getContenidoEntrada().replace(imageServer, ""));
        }

        if (CollectionUtils.isNotEmpty(titulosLibrosEntrada)) {
            for (final String titulo : titulosLibrosEntrada) {
                librosEntrada.add(EntityToDTOAdapter.adaptarLibro(this.libroRepository.findOneByTitulo(titulo)));
            }
        }
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioLogin(entradaAGuardar.getEditorNombre());

        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, librosEntrada, autor);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<EtiquetaEntity>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                List<String> etiquetas = new ArrayList<String>();
                if (StringUtils.contains(etiqueta.getNombre(), ",")) {
                    etiquetas = ConversionUtils.divide(etiqueta.getNombre());
                } else {
                    etiquetas.add(etiqueta.getNombre());
                }
                for (final String nombreEtiqueta : etiquetas) {
                    EtiquetaEntity etiquetaBD = this.etiquetaRepository.findOneByNombre(nombreEtiqueta.trim());
                    if (etiquetaBD == null) {
                        final EtiquetaEntity nuevaEtiqueta = new EtiquetaEntity();
                        nuevaEtiqueta.setNombre(nombreEtiqueta.trim());
                        nuevaEtiqueta.setEtiquetaUrl(ConversionUtils.toSlug(nombreEtiqueta.trim()));
                        nuevaEtiqueta.setFechaAlta(Calendar.getInstance().getTime());
                        nuevaEtiqueta.setUsuarioAlta(currentPrincipalName);
                        etiquetaBD = this.etiquetaRepository.save(nuevaEtiqueta);
                    }
                    etiquetasBD.add(etiquetaBD);
                }

            }
            entradaEntity.setEtiquetas(etiquetasBD);
        }
        if (entradaEntity.getImagenDestacada() != null) {
            entradaEntity.setImagenDestacada(soloNombreImagenADestacadas(entradaEntity.getImagenDestacada()));
        } else {
            entradaEntity.setImagenDestacada(this.momokoConfiguracion.getImagenDefault());
        }
        final boolean esNuevaEntrada = entradaAGuardar.getEntradaId() == null;
        // Comprobamos si la url de la entrada existe.
        EntradaEntity coincidencia;
        if (esNuevaEntrada) {
            coincidencia = this.entradaRepository.findFirstByUrlEntrada(entradaEntity.getUrlEntrada());
        } else {
            coincidencia = this.entradaRepository.findOne(entradaAGuardar.getEntradaId());
        }

        if (esNuevaEntrada && (coincidencia != null)) {
            throw new URLEntradaYaExisteException("La entrada es nueva y se esta intentando utilizar una url ya usada");
        }
        if (!esNuevaEntrada && (coincidencia == null)) {
            throw new NoSeEncuentraEntradaException("No se encuentra la entrada");
        }
        if (esNuevaEntrada) {
            entradaEntity = crearNuevaEntrada(entradaEntity);
        } else {
            entradaEntity = actualizarEntrada(entradaEntity, coincidencia);
        }
        if (entradaEntity.getTipoEntrada().equals(TipoEntrada.VIDEO.getValue())) {
            VideoEntity videoEntity = this.videoRepository.findOne(entradaEntity.getEntradaId());
            if (videoEntity == null) {
                videoEntity = new VideoEntity();
                videoEntity.setTitulo(entradaEntity.getTituloEntrada());
                videoEntity.setEntrada(entradaEntity);
                videoEntity.setDescripcion(entradaEntity.getResumenEntrada());
                videoEntity.setUrlVideo(entradaAGuardar.getUrlVideo());
                videoEntity.setFechaAlta(Calendar.getInstance().getTime());
                videoEntity.setUsuarioAlta(entradaEntity.getEntradaAutor().getUsuarioNick());
            } else {
                videoEntity.setUrlVideo(entradaAGuardar.getUrlVideo());
            }
            this.videoRepository.save(videoEntity);
        }
        return EntityToDTOAdapter.adaptarEntrada(entradaEntity);
    }

    private String soloNombreImagenADestacadas(final String imagenDestacada) {
        final String[] lista = imagenDestacada.split("/");
        final int elementos = lista.length;
        return "imagenes-destacadas/" + lista[elementos - 1];
    }

    private EntradaEntity actualizarEntrada(final EntradaEntity entradaEntity, final EntradaEntity viejaEntrada) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            viejaEntrada.setLibrosEntrada(obtenerLibrosEntrada(entradaEntity.getLibrosEntrada()));
        } else {
            viejaEntrada.setLibrosEntrada(null);
        }

        viejaEntrada.setUsuarioModificacion(currentPrincipalName);
        viejaEntrada.setFechaModificacion(Calendar.getInstance().getTime());

        if (viejaEntrada.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            viejaEntrada.setPadreEntrada(padre);
        } else {
            viejaEntrada.setPadreEntrada(null);
        }

        viejaEntrada.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        viejaEntrada.setEntradaId(entradaEntity.getEntradaId());
        viejaEntrada.setContenidoEntrada(entradaEntity.getContenidoEntrada());
        viejaEntrada.setEstadoEntrada(entradaEntity.getEstadoEntrada());

        viejaEntrada.setPermitirComentarios(entradaEntity.getPermitirComentarios());
        viejaEntrada.setResumenEntrada(entradaEntity.getResumenEntrada());
        viejaEntrada.setFraseDescriptiva(entradaEntity.getFraseDescriptiva());
        viejaEntrada.setTipoEntrada(entradaEntity.getTipoEntrada());
        viejaEntrada.setTituloEntrada(entradaEntity.getTituloEntrada());
        viejaEntrada.setUrlEntrada(entradaEntity.getUrlEntrada());
        viejaEntrada.setEtiquetas(entradaEntity.getEtiquetas());
        viejaEntrada.setImagenDestacada(entradaEntity.getImagenDestacada());
        viejaEntrada.setEntradaAutor(entradaEntity.getEntradaAutor());
        viejaEntrada.setFechaAlta(entradaEntity.getFechaAlta());
        viejaEntrada.setEnMenu(entradaEntity.isEnMenu());
        viejaEntrada.setUrlMenuLibro(entradaEntity.getUrlMenuLibro());
        viejaEntrada.setNombreMenuLibro(entradaEntity.getNombreMenuLibro());
        this.entradaRepository.save(viejaEntrada);
        return viejaEntrada;
    }

    private EntradaEntity crearNuevaEntrada(final EntradaEntity entradaEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            entradaEntity.setLibrosEntrada(obtenerLibrosEntrada(entradaEntity.getLibrosEntrada()));
        }
        if (entradaEntity.getFechaAlta() == null) {
            entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
        }

        entradaEntity.setUsuarioAlta(currentPrincipalName);

        if (entradaEntity.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }
        // TODO: RAMON: Implementar
        entradaEntity.setNumeroComentarios(0);
        if (entradaEntity.getPermitirComentarios() == null) {
            entradaEntity.setPermitirComentarios(true);
        }
        this.entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private List<LibroEntity> obtenerLibrosEntrada(final List<LibroEntity> librosABuscar) {

        List<LibroEntity> librosEncontrado = null;
        if (CollectionUtils.isNotEmpty(librosABuscar)) {
            final List<Integer> librosIds = new ArrayList<Integer>();
            for (final LibroEntity libroEntity : librosABuscar) {
                librosIds.add(libroEntity.getLibroId());
            }
            librosEncontrado = this.libroRepository.findByLibroIdIn(librosIds);
        }
        return librosEncontrado;

    }

    @Override
    public ObtenerEntradaResponse obtenerEntradaVideo(final String urlVideo) {
        // TODO Auto-generated method stub

        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        VideoEntity videoEntityBD = this.videoRepository.findFirstByEntradaUrlEntrada(urlVideo);

        if (videoEntityBD == null) {
            // Creamos la entrada de video basado en sus datos
            final RestTemplate restTemplate = new RestTemplate();
            final YoutubeVideoList videosCanalMomoko = restTemplate.getForObject(
                    "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCJxqau4eHsx-pzYDdylD2PA&maxResults=5&order=date&type=video&key=AIzaSyCJrWHmLXtIAri-uhpJzOh30jdtZl03dgA",
                    YoutubeVideoList.class);
            for (final Video video : videosCanalMomoko.getItems()) {
                final String urlVideoRemote = ConversionUtils.toSlug(video.getSnippet().getTitle());
                if (urlVideo.equals(urlVideoRemote)) {
                    final VideoYoutube videoMomokoYoutube = restTemplate.getForObject(
                            "https://www.googleapis.com/youtube/v3/videos?part=id,snippet&id="
                                    + video.getId().getVideoId() + "&key=AIzaSyCJrWHmLXtIAri-uhpJzOh30jdtZl03dgA",
                            VideoYoutube.class);
                    final Item videoData = videoMomokoYoutube.getItems().iterator().next();
                    final UsuarioEntity autora = this.usuarioRepository.findByUsuarioEmail("kizuna.owo@gmail.com");
                    final EntradaDTO nuevaEntradaVideo = new EntradaDTO();
                    nuevaEntradaVideo.setAutor(ConversionUtils.obtenerUsuarioBasico(autora));
                    nuevaEntradaVideo.setContenidoEntrada(videoData.getSnippet().getDescription());
                    nuevaEntradaVideo.setEstadoEntrada(2);
                    final EtiquetaDTO etiqueta = new EtiquetaDTO();

                    etiqueta.setNombreEtiqueta("Video momoko");
                    final List<EtiquetaDTO> setEtiquetas = new ArrayList<EtiquetaDTO>();
                    setEtiquetas.add(etiqueta);
                    for (final String etiquetaString : videoData.getSnippet().getTags()) {
                        final EtiquetaDTO etiquetaS = new EtiquetaDTO();
                        etiquetaS.setNombreEtiqueta(etiquetaString);
                        setEtiquetas.add(etiquetaS);
                    }
                    nuevaEntradaVideo.setEtiquetas(setEtiquetas);
                    nuevaEntradaVideo.setFraseDescriptiva(video.getSnippet().getDescription());
                    nuevaEntradaVideo.setResumenEntrada(video.getSnippet().getDescription());
                    URL url;
                    BufferedImage img;
                    try {
                        url = new URL(videoData.getSnippet().getThumbnails().getMaxres().getUrl());
                        img = ImageIO.read(url);
                        this.almacenImagenes.store(img, "imagenes-destacadas", urlVideo, "png");
                    } catch (final MalformedURLException e) {
                        e.printStackTrace();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                    nuevaEntradaVideo.setPermitirComentarios(true);
                    nuevaEntradaVideo.setUrlEntrada(urlVideoRemote);
                    nuevaEntradaVideo.setTipoEntrada(4);
                    nuevaEntradaVideo.setTituloEntrada(video.getSnippet().getTitle());
                    nuevaEntradaVideo.setEditorNombre("La insomne");
                    nuevaEntradaVideo.setImagenDestacada("imagenes-destacadas/" + urlVideo + ".png");
                    final EntradaEntity entradaVideoEntity = obtenerEntradaEntityParaVideo(nuevaEntradaVideo);
                    final VideoEntity videoEntity = new VideoEntity();
                    videoEntity.setDescripcion(video.getSnippet().getDescription());
                    videoEntity.setFechaAlta(Calendar.getInstance().getTime());
                    videoEntity.setUsuarioAlta(autora.getUsuarioEmail());
                    videoEntity.setTitulo(video.getSnippet().getTitle());
                    videoEntity.setUrlVideo("https://youtu.be/" + video.getId().getVideoId());
                    videoEntity.setEntrada(entradaVideoEntity);
                    final VideoEntity videoEntitySaved = this.videoRepository.save(videoEntity);
                    videoEntityBD = videoEntitySaved;
                }
            }
        }
        return obtenerEntrada(urlVideo, true);
    }

    private EntradaEntity obtenerEntradaEntityParaVideo(final EntradaDTO entradaAGuardar) {
        final List<LibroDTO> librosEntrada = new ArrayList<LibroDTO>();
        final List<String> titulosLibrosEntrada = entradaAGuardar.getTitulosLibrosEntrada();
        if (CollectionUtils.isNotEmpty(titulosLibrosEntrada)) {
            for (final String titulo : titulosLibrosEntrada) {
                librosEntrada.add(EntityToDTOAdapter.adaptarLibro(this.libroRepository.findOneByTitulo(titulo)));
            }
        }
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioLogin(entradaAGuardar.getAutor().getNombre());

        final EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, librosEntrada, autor);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<EtiquetaEntity>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                List<String> etiquetas = new ArrayList<String>();
                if (StringUtils.contains(etiqueta.getNombre(), ",")) {
                    etiquetas = ConversionUtils.divide(etiqueta.getNombre());
                } else {
                    etiquetas.add(etiqueta.getNombre());
                }
                for (final String nombreEtiqueta : etiquetas) {
                    EtiquetaEntity etiquetaBD = this.etiquetaRepository.findOneByNombre(nombreEtiqueta.trim());
                    if (etiquetaBD == null) {
                        final EtiquetaEntity nuevaEtiqueta = new EtiquetaEntity();
                        nuevaEtiqueta.setNombre(nombreEtiqueta.trim());
                        nuevaEtiqueta.setFechaAlta(Calendar.getInstance().getTime());
                        nuevaEtiqueta.setUsuarioAlta(autor.getUsuarioEmail());
                        etiquetaBD = this.etiquetaRepository.save(nuevaEtiqueta);
                    }
                    etiquetasBD.add(etiquetaBD);
                }

            }
            entradaEntity.setEtiquetas(etiquetasBD);
        }
        entradaEntity.setImagenDestacada(soloNombreImagenADestacadas(entradaEntity.getImagenDestacada()));
        final boolean esNuevaEntrada = entradaAGuardar.getEntradaId() == null;

        if (entradaEntity.getLibrosEntrada() != null) {
            entradaEntity.setLibrosEntrada(obtenerLibrosEntrada(entradaEntity.getLibrosEntrada()));
        }
        entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
        entradaEntity.setUsuarioAlta(autor.getUsuarioEmail());

        if (entradaEntity.getPadreEntrada() != null) {
            final EntradaEntity padre = this.entradaRepository
                    .findFirstByUrlEntrada(entradaEntity.getPadreEntrada().getUrlEntrada());
            entradaEntity.setPadreEntrada(padre);
        }

        entradaEntity.setNumeroComentarios(0);
        if (entradaEntity.getPermitirComentarios() == null) {
            entradaEntity.setPermitirComentarios(true);
        }
        return entradaEntity;
    }

    @Override
    public List<EntradaSimpleDTO> obtenerTresUltimasEntradasPopularesConLibro() {
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findTop3ByLibrosEntradaIsNotNullOrderByLibrosEntradaVisitasDesc();
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasCategoriaPorFecha(final CategoriaDTO categoriaDTO,
            final int numeroEntradas, final int pagina) {
        final List<GeneroDTO> listaGeneros = this.generoService.obtenerGenerosCategoria(categoriaDTO);
        final List<Integer> generosIds = new ArrayList<Integer>();
        for (final GeneroDTO generoDTO : listaGeneros) {
            generosIds.add(generoDTO.getGeneroId());
        }
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findEntradaAnalisisLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(generosIds,
                        new PageRequest(pagina - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public Integer obtenerNumeroEntradasCategoria(final CategoriaDTO categoriaDTO) {
        final List<GeneroDTO> listaGeneros = this.generoService.obtenerGenerosCategoria(categoriaDTO);
        final List<Integer> generosIds = new ArrayList<Integer>();
        for (final GeneroDTO generoDTO : listaGeneros) {
            generosIds.add(generoDTO.getGeneroId());
        }
        final Long numeroEntradas = this.entradaRepository
                .findNumberEntradaAnalisisLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(generosIds);
        return numeroEntradas.intValue();
    }

    @Override
    public List<EntradaSimpleDTO> obtenerNoticias(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaNoticias = this.entradaRepository
                .findByTipoEntradaAndFechaBajaIsNullOrderByFechaAltaDesc(TipoEntrada.NOTICIA.getValue(),
                        new PageRequest(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaNoticias, true);
    }

    @Override
    public Integer obtenerNumeroNoticias() {
        return this.entradaRepository.countByTipoEntradaAndFechaBajaIsNull(TipoEntrada.NOTICIA.getValue()).intValue();
    }

    @Override
    public Collection<EntradaSimpleDTO> obtenerMiscelaneos(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaMiscelaneos = this.entradaRepository
                .findByTipoEntradaAndFechaBajaIsNullOrderByFechaAltaDesc(TipoEntrada.MISCELANEOS.getValue(),
                        new PageRequest(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaMiscelaneos, true);
    }

    @Override
    public Integer obtenerNumeroMiscelaneos() {
        return this.entradaRepository.countByTipoEntradaAndFechaBajaIsNull(TipoEntrada.MISCELANEOS.getValue())
                .intValue();
    }

    @Override
    public Collection<EntradaSimpleDTO> obtenerVideos(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaMiscelaneos = this.entradaRepository
                .findByTipoEntradaAndFechaBajaIsNullOrderByFechaAltaDesc(TipoEntrada.VIDEO.getValue(),
                        new PageRequest(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaMiscelaneos, true);
    }

    @Override
    public Integer obtenerNumeroVideos() {
        return this.entradaRepository.countByTipoEntradaAndFechaBajaIsNull(TipoEntrada.VIDEO.getValue()).intValue();
    }

    @Override
    public EntradaSimpleDTO obtenerEntradaSimple(final String urlEntrada) {
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        return ConversionUtils.obtenerEntradaSimpleDTO(entradaEntity, true);
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasEtiquetaPorFecha(final EtiquetaDTO etiquetaDTO,
            final int numeroEntradas, final Integer numeroPagina) {

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(etiquetaDTO.getEtiquetaId(),
                        new PageRequest(numeroPagina - 1, numeroEntradas));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public Integer obtenerNumeroEntradasEtiqueta(final EtiquetaDTO etiquetaDTO) {
        final Long numeroEntradas = this.entradaRepository
                .findNumberEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(etiquetaDTO.getEtiquetaId());
        return numeroEntradas.intValue();
    }

}
