/**
 * EntradaServiceImpl.java 24-oct-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
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
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.momoko.es.api.enums.EntryStatusEnum;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.jpa.model.entity.*;
import com.momoko.es.jpa.model.repository.*;
import com.momoko.es.jpa.model.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.RedactorDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.api.youtube.list.Video;
import com.momoko.es.api.youtube.list.YoutubeVideoList;
import com.momoko.es.api.youtube.video.Item;
import com.momoko.es.api.youtube.video.VideoYoutube;
import com.momoko.es.commons.configuration.MomokoConfiguracion;
import com.momoko.es.jpa.model.service.ComentarioService;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.GenreService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.impl.util.FileSystemStorageHelper;

/**
 * The Class EntradaServiceImpl.
 */
@Service
public class EntradaServiceImpl implements EntradaService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private SagaRepository sagaRepository;

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

    @Autowired(required = false)
    private StorageService almacenImagenes;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private GenreService generoService;

    @Autowired
    private MomokoConfiguracion momokoConfiguracion;

    @Autowired
    private HtmlCodeRepository htmlCodeRepository;

    /**
     * Obtener entrada para gestion.
     *
     * @param urlEntrada the url entrada
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
        if (entradaDTO.getEntryType().equals(EntryTypeEnum.VIDEO)) {
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

    public List<EntradaDTO> obtenerDatosEntradasLibro(final Integer libroId) {
        final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<>();

        final List<EntradaEntity> entradas = this.entradaRepository.findAllByLibrosEntradaIdIn(Arrays.asList(libroId),
                Calendar.getInstance().getTime());
        return EntityToDTOAdapter.adaptarEntradas(entradas);
    }

    public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public ObtenerEntradaResponse obtenerEntrada(final String urlEntrada, final boolean transformarGalerias) {
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        if (entradaEntity != null) {

            final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);

            try {
                LocalDateTime creationDate = convertToLocalDateViaInstant(entradaEntity.getCreatedDate());
                LocalDateTime newImageFormat = LocalDateTime.of(2018, Month.NOVEMBER, 26, 23, 0);
                if ( entradaDTO.getEntryType().equals(EntryTypeEnum.OPINION) && creationDate.isAfter(newImageFormat)) {
                    entradaDTO.setImagenDestacada(
                            this.almacenImagenes.obtenerMiniatura(entradaDTO.getImagenDestacada(), 770, 1180, true));
                } else {
                    entradaDTO.setImagenDestacada(
                            this.almacenImagenes.obtenerMiniatura(entradaDTO.getImagenDestacada(), 770, 432, true));
                }
            } catch( final IOException e){
                e.printStackTrace();
            }
            if (EntryTypeEnum.MISCELLANEOUS.equals(entradaDTO.getEntryType()) ||
                    EntryTypeEnum.SPECIAL.equals(entradaDTO.getEntryType())) {
                final String jsonLD = JsonLDUtils.crearJsonLDMiscelaneo(entradaDTO);
                entradaDTO.setJsonLD(jsonLD);
            }
            if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
                obtenerEntradaAsociadaALibros(respuesta, entradaEntity, entradaDTO);
            } else if (CollectionUtils.isNotEmpty(entradaEntity.getSagasEntrada())) {
                obtenerEntradaAsociadaASagas(respuesta, entradaEntity, entradaDTO);
                if (CollectionUtils.isNotEmpty(entradaDTO.getSagasEntrada())
                        && CollectionUtils.isNotEmpty(entradaDTO.getSagasEntrada().iterator().next().getGeneros())) {
                    final Set<GenreDTO> generos = entradaDTO.getSagasEntrada().iterator().next().getGeneros();
                    final String url = this.almacenImagenes.getUrlImageServer();
                    for (final GenreDTO generoDTO : generos) {
                        generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                    }
                }
            }

            final List<EntradaSimpleDTO> cuatroPostPequenosConImagen = obtener4PostPequenosConImagen(
                    entradaEntity.getEntradaId());
            for (final EntradaSimpleDTO entradaSimpleDTO : cuatroPostPequenosConImagen) {
                try {
                    entradaSimpleDTO.setImagenEntrada(this.almacenImagenes
                            .obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 370, 246, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
            respuesta.setCuatroPostPequenosConImagen(cuatroPostPequenosConImagen);


            respuesta.setEntradaAnteriorYSiguiente(obtenerEntradaAnteriorYSiguiente(entradaEntity.getEntradaId()));
            // Si es tipo video anadimos su URL
            if (entradaDTO.getEntryType().equals(EntryTypeEnum.VIDEO)) {
                final VideoEntity videoEntity = this.videoRepository
                        .findFirstByEntradaUrlEntrada(entradaDTO.getUrlEntrada());
                if (videoEntity != null) {
                    final String videoUrl = videoEntity.getUrlVideo();
                    entradaDTO.setUrlVideo(videoUrl);
                }
            }

            final List<ComentarioDTO> comentarios = this.comentarioService
                    .obtenerComentariosEntrada(entradaDTO.getEntradaId());

            final List<ComentarioDTO> comentariosOrdenados = new ArrayList<>();
            final Map<Integer, ComentarioDTO> mapaComentarios = new HashMap<>();
            for (final ComentarioDTO comentarioDTO : comentarios) {
                if (comentarioDTO.getComentarioPadreId() == null) {
                    mapaComentarios.put(comentarioDTO.getComentarioId(), comentarioDTO);
                } else {
                    final ComentarioDTO comentarioPadre = mapaComentarios.get(comentarioDTO.getComentarioPadreId());
                    if (comentarioPadre != null) {
                        List<ComentarioDTO> respuestas = comentarioPadre.getComentariosHijo();
                        if (CollectionUtils.isEmpty(respuestas)) {
                            respuestas = new ArrayList<>();
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
                obtenerBloquesCodigoEntrada(entradaDTO);
                obtenerGifs(entradaDTO);
            }

            if (CollectionUtils.isNotEmpty(entradaDTO.getLibrosEntrada())) {
                for (final LibroDTO libroDTO : entradaDTO.getLibrosEntrada()) {
                    final String url = this.almacenImagenes.getUrlImageServer();
                    final Set<GenreDTO> generosImagenes = new HashSet<GenreDTO>();
                    for (final GenreDTO generoDTO : libroDTO.getGeneros()) {
                        generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                        generosImagenes.add(generoDTO);
                    }
                    libroDTO.setGeneros(generosImagenes);
                }
            }

            if (entradaDTO.getRedactor() != null) {
                final RedactorDTO redactor = entradaDTO.getRedactor();
                if (redactor.getAvatarRedactor() != null) {
                    try {
                        redactor.setAvatarRedactor(
                                this.almacenImagenes.obtenerMiniatura(redactor.getAvatarRedactor(), 120, 120, true));
                    } catch (final IOException e) {
                        redactor.setAvatarRedactor(
                                ConversionUtils.obtenerGravatar(entradaEntity.getEntradaAutor().getEmail()));
                    }

                } else {
                    redactor.setAvatarRedactor(
                            ConversionUtils.obtenerGravatar(entradaEntity.getEntradaAutor().getEmail()));
                }
                entradaDTO.setRedactor(redactor);
            }

            respuesta.setEntrada(entradaDTO);
            respuesta.setComentarios(comentariosOrdenados);
        }
        return respuesta;
    }



    /**
     * Asociar datos saga asociada a entrada.
     *
     * @param respuesta     the respuesta
     * @param entradaEntity the entrada entity
     * @param entradaDTO    the entrada dto
     */
    public void obtenerEntradaAsociadaASagas(final ObtenerEntradaResponse respuesta, final EntradaEntity entradaEntity,
                                             final EntradaDTO entradaDTO) {

        final List<SagaEntity> sagasEntrada = entradaEntity.getSagasEntrada();
        if (CollectionUtils.isNotEmpty(sagasEntrada)) {
            final List<EntradaEntity> entradasRelacionadas = this.entradaRepository.findBySagasEntradaIn(
                    sagasEntrada.stream().map(SagaEntity::getSagaId).collect(Collectors.toList()),
                    PageRequest.of(0, 99));

            final List<DatoEntradaDTO> listaDatosEntradas = ConversionUtils
                    .obtenerDatosEntradaFromEntradaEntityList(entradasRelacionadas);
            entradaDTO.setDatosEntrada(listaDatosEntradas);
        }
        if (EntryTypeEnum.OPINION.equals(entradaDTO.getEntryType())) {
            entradaDTO.setJsonLD("{}");
        }
    }

    public void obtenerEntradaAsociadaALibros(final ObtenerEntradaResponse respuesta, final EntradaEntity entradaEntity,
                                              final EntradaDTO entradaDTO) {
        final Set<DatoEntradaDTO> datosEntradas = new HashSet<>();
        final List<LibroSimpleDTO> librosParecidos = new ArrayList<LibroSimpleDTO>();
        final List<LibroEntity> librosEntrada = entradaEntity.getLibrosEntrada();
        if (CollectionUtils.isNotEmpty(librosEntrada)) {
            for (final LibroEntity libroEntrada : librosEntrada) {

                final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                        .findByLibrosEntradaIn(Arrays.asList(libroEntrada), PageRequest.of(0, 99));

                Collections.sort(entradasRelacionadas);
                if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
                    for (final EntradaEntity entradaRelacionadaEntity : entradasRelacionadas) {
                        final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                        datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                        datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                        datoEntrada.setEnMenu(entradaRelacionadaEntity.isEnMenu());
                        datoEntrada.setNombreMenuLibro(entradaRelacionadaEntity.getNombreMenuLibro());
                        datoEntrada.setUrlMenuLibro(entradaRelacionadaEntity.getUrlMenuLibro());
                        datosEntradas.add(datoEntrada);
                    }
                }

                if (CollectionUtils.isNotEmpty(entradaDTO.getLibrosEntrada())) {
                    for (final LibroDTO libroDTO : entradaDTO.getLibrosEntrada()) {
                        librosParecidos.addAll(this.libroService.obtenerLibrosParecidos(libroDTO, 5));

                        final PuntuacionEntity puntuacion = this.puntuacionRepository
                                .findOneByEsPuntuacionMomokoAndLibro(true, libroEntrada);
                        if (puntuacion != null) {
                            libroDTO.setNotaMomoko(puntuacion.getValor());
                        }
                        if (EntryTypeEnum.OPINION.equals(entradaDTO.getEntryType())) {
                            final String jsonLD = JsonLDUtils.crearJsonLDOpiniones(libroDTO, entradaDTO,
                                    puntuacion.getValor());
                            entradaDTO.setJsonLD(jsonLD);
                        }
                        MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroDTO);
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
            entradaDTO.setDatosEntrada(new ArrayList<>(datosEntradas));
        }
    }

    private void obtenerGifs(final EntradaDTO entradaDTO) {

        while (entradaDTO.getContenidoEntrada().contains("[gif ")) {
            final String gif = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[gif ", "]");
            final String url = this.almacenImagenes.getUrlImageServer();
            final String code = "<img src=\"" + url + "gifs/" + gif.trim() + ".gif\" alt=\"Gif de la entrada "
                    + entradaDTO.getTituloEntrada() + "\" />";
            entradaDTO.setContenidoEntrada(
                    StringUtils.replace(entradaDTO.getContenidoEntrada(), "[gif " + gif + "]", code));
        }
    }

    private void obtenerBloqueslibroEntrada(final EntradaDTO entradaDTO) {

        while (entradaDTO.getContenidoEntrada().contains("[momoko-libro ")) {
            final String bloquelibro = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[momoko-libro ",
                    "]");

            final String titulo = StringUtils.substringBetween(bloquelibro, "titulo=\"", "\"");
            final String columnas = StringUtils.substringBetween(bloquelibro, "columnas=\"", "\"");
            LibroEntity libro = this.libroRepository.findOneByTitulo(titulo);
            String code = generateBookTemplateCode(libro, columnas);
            entradaDTO.setContenidoEntrada(
                    StringUtils.replace(entradaDTO.getContenidoEntrada(), "[momoko-libro " + bloquelibro + "]", code));
        }
    }


    private String generateBookTemplateCode(LibroEntity libro, String columnas) {
        LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libro);
        libro.getUrlImagen();
        MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroDTO);
        String code = "<book-template-angular anchura=\"" + libroDTO.getPortadaWidth() +
                "\" altura=\"" + libroDTO.getPortadaHeight() + "\" imagen=\"" + libroDTO.getUrlImagen() +
                "\" title=\"" + libroDTO.getTitulo() + "\" autor=\"" + MomokoUtils.generarAutoresString(libroDTO) +
                "\" url=\"" + libroDTO.getUrlLibro() + "\" columnas=\"" + columnas + "\"></book-template-angular>";
        return code;

    }


    private void obtenerBloquesCodigoEntrada(final EntradaDTO entradaDTO) {

        while (entradaDTO.getContenidoEntrada().contains("[momoko-code ")) {
            final String bloqueCodigo = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[momoko-code ",
                    "]");

            final String urlCodigo = StringUtils.substringBetween(bloqueCodigo, "url=\"", "\"");
            HtmlCodeEntity htmlCode = this.htmlCodeRepository.findOneByUrlCode(urlCodigo);
            String code = htmlCode.getContent();
            entradaDTO.setContenidoEntrada(
                    StringUtils.replace(entradaDTO.getContenidoEntrada(), "[momoko-code " + bloqueCodigo + "]", code));
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
     * @param entradaDTO the entrada dto
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

    @Override
    public void obtenerGaleriasEntradaAmp(final EntradaDTO entradaDTO) {
        int numeroGaleria = 0;
        while (entradaDTO.getContenidoEntrada().contains("[momoko-galeria-")) {
            final String urlGaleria = StringUtils.substringBetween(entradaDTO.getContenidoEntrada(), "[momoko-galeria-",
                    "]");
            final GaleriaEntity galeria = this.galeriaRepository.findOneByUrlGaleria(urlGaleria);
            if (galeria != null) {
                entradaDTO.setTieneGaleria(true);

                final String code = generarCodigoGaleriaAmp(galeria, numeroGaleria);
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
     * @param entradaDTO the entrada dto
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

    private String generarCodigoGaleriaAmp(final GaleriaEntity galeria, final int numeroGaleria) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<amp-carousel height=\"300\" layout=\"fixed-height\" type=\"carousel\">");
        final List<String> imagenes = ConversionUtils.divide(galeria.getImagenes(), ";");

        final String imageServer = this.almacenImagenes.getUrlImageServer();
        for (final String imagen : imagenes) {
            stringBuilder.append("<amp-img src=\"" + imageServer + imagen
                    + "\" width=\"534\" height=\"300\" alt=\"galeria imagenes libro\"></amp-img>");

        }

        stringBuilder.append("</amp-carousel>");
        return stringBuilder.toString();
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
                    + anchuraAltura.getAnchura() + "px; height:" + anchuraAltura.getAltura() + "px;\" "
                    + "alt =\"Imagen galeria " + galeria.getNombreGaleria() + " " + columna + "\" /></a>");
            stringBuilder.append("</div></div>");
            columna++;
        }

        stringBuilder.append("</div></div></div></div>");
        return stringBuilder.toString();
    }

    /**
     * Obtener4 post pequenos con imagen.
     *
     * @param entradaId the entrada id
     * @return the list
     */
    private List<EntradaSimpleDTO> obtener4PostPequenosConImagen(final Integer entradaId) {
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .seleccionarEntradasAleatorias(entradaId, PageRequest.of(0, 3)).getContent();
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    /**
     * Obtener entrada anterior y siguiente.
     *
     * @param fechaAlta the fecha alta
     * @return the list
     */
    private List<EntradaSimpleDTO> obtenerEntradaAnteriorYSiguiente(final Integer entryId) {
        final List<EntradaEntity> entradaAnterior = this.entradaRepository
                .findEntradaMiscelaneosAnteriorAFecha(entryId, PageRequest.of(0, 1)).getContent();

        final List<EntradaEntity> entradaPosterior = this.entradaRepository
                .findEntradaMiscelaneosPosteriorAFecha(entryId, PageRequest.of(0, 1)).getContent();

        final List<EntradaEntity> listaEntities = new ArrayList<>();
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
        final List<EntradaDTO> entradasDTO = new ArrayList<>();
        final List<EntradaEntity> entradasEntity = this.entradaRepository.findAll();
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

    public List<String> obtenerTitulosLibrosEntrada(final EntradaEntity entradaEntity) {
        final List<String> titulosLibros = new ArrayList<>();
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

        final List<LibroDTO> librosEntrada = new ArrayList<>();
        final List<SagaDTO> sagasEntrada = new ArrayList<>();

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        final List<String> titulosLibrosEntrada = entradaAGuardar.getTitulosLibrosEntrada();
        final List<String> nombresSagasEntrada = entradaAGuardar.getNombresSagasEntrada();
        int cont = 0;
        final String imageServer = this.almacenImagenes.getUrlImageServer();
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
            while (FileSystemStorageHelper.exists(url)) {
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
        if (CollectionUtils.isNotEmpty(nombresSagasEntrada)) {
            for (final String nombre : nombresSagasEntrada) {
                sagasEntrada
                        .add(EntityToDTOAdapter.adaptarSaga(this.sagaRepository.findOneByNombre(nombre), true, true));
            }
        }
        if (CollectionUtils.isNotEmpty(titulosLibrosEntrada)) {
            for (final String titulo : titulosLibrosEntrada) {
                librosEntrada.add(EntityToDTOAdapter.adaptarLibro(this.libroRepository.findOneByTitulo(titulo)));
            }
        }
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioLogin(entradaAGuardar.getEditorNombre());

        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, librosEntrada, sagasEntrada,
                autor);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                List<String> etiquetas = new ArrayList<>();
                if (StringUtils.contains(etiqueta.getNombre(), ",")) {
                    etiquetas = ConversionUtils.divide(etiqueta.getNombre());
                } else {
                    etiquetas.add(etiqueta.getNombre());
                }
                for (final String nombreEtiqueta : etiquetas) {
                    EtiquetaEntity etiquetaBD = this.etiquetaRepository
                            .findOneByEtiquetaUrl(ConversionUtils.toSlug(nombreEtiqueta.trim()));
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
            coincidencia = this.entradaRepository.findById(entradaAGuardar.getEntradaId()).orElse(null);
        }

        if (esNuevaEntrada && (coincidencia != null)) {
            throw new URLEntradaYaExisteException("La entrada es nueva y se esta intentando utilizar una url ya usada");
        }
        if (!esNuevaEntrada && (coincidencia == null)) {
            throw new NoSeEncuentraEntradaException("No se encuentra la entrada");
        }
        if (entradaAGuardar.getUrlMenuLibro() != null) {
            entradaEntity.setEntryType(EntryTypeEnum.SPECIAL);
            entradaEntity.setTipoEntrada(5);
        } else {
            entradaEntity.setTipoEntrada(entradaEntity.getEntryType().getValue());
        }
        //TODO: FIX ME
        entradaEntity.setEstadoEntrada(2);
        entradaEntity.setEntryStatus(EntryStatusEnum.PUBLISHED);
        if (esNuevaEntrada) {
            entradaEntity = crearNuevaEntrada(entradaEntity);
        } else {
            entradaEntity = actualizarEntrada(entradaEntity, coincidencia);
        }
        if (entradaEntity.getTipoEntrada().equals(EntryTypeEnum.VIDEO.getValue())) {
            VideoEntity videoEntity = this.videoRepository.findById(entradaEntity.getEntradaId()).orElse(null);
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

        if (CollectionUtils.isNotEmpty(entradaEntity.getSagasEntrada())) {
            viejaEntrada.setSagasEntrada(obtenerSagasEntrada(entradaEntity.getSagasEntrada()));
        } else {
            viejaEntrada.setSagasEntrada(null);
        }

        if (!viejaEntrada.getUrlEntrada().equals(entradaEntity.getUrlEntrada())) {
            viejaEntrada.setUrlAntigua(viejaEntrada.getUrlEntrada());
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
        viejaEntrada.setEntryType(entradaEntity.getEntryType());
        viejaEntrada.setUrlEntrada(entradaEntity.getUrlEntrada());
        viejaEntrada.setEtiquetas(entradaEntity.getEtiquetas());
        viejaEntrada.setImagenDestacada(entradaEntity.getImagenDestacada());
        viejaEntrada.setEntradaAutor(entradaEntity.getEntradaAutor());
        viejaEntrada.setEnMenu(entradaEntity.isEnMenu());
        viejaEntrada.setUrlMenuLibro(entradaEntity.getUrlMenuLibro());
        viejaEntrada.setNombreMenuLibro(entradaEntity.getNombreMenuLibro());
        this.entradaRepository.save(viejaEntrada);
        return viejaEntrada;
    }

    private List<SagaEntity> obtenerSagasEntrada(final List<SagaEntity> sagasABuscar) {
        List<SagaEntity> librosEncontrado = null;
        if (CollectionUtils.isNotEmpty(sagasABuscar)) {
            final List<Integer> sagasIds = new ArrayList<Integer>();
            for (final SagaEntity sagaEntity : sagasABuscar) {
                sagasIds.add(sagaEntity.getSagaId());
            }
            librosEncontrado = this.sagaRepository.findBySagaIdIn(sagasIds);
        }
        return librosEncontrado;
    }

    private EntradaEntity crearNuevaEntrada(final EntradaEntity entradaEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (CollectionUtils.isNotEmpty(entradaEntity.getLibrosEntrada())) {
            entradaEntity.setLibrosEntrada(obtenerLibrosEntrada(entradaEntity.getLibrosEntrada()));
        }
        if (CollectionUtils.isNotEmpty(entradaEntity.getSagasEntrada())) {
            entradaEntity.setSagasEntrada(obtenerSagasEntrada(entradaEntity.getSagasEntrada()));
        }

        if (entradaEntity.getPermitirComentarios() == null) {
            entradaEntity.setPermitirComentarios(true);
        }
        this.entradaRepository.save(entradaEntity);
        return entradaEntity;
    }

    private List<LibroEntity> obtenerLibrosEntrada(final List<LibroEntity> librosABuscar) {

        List<LibroEntity> librosEncontrado = null;
        if (CollectionUtils.isNotEmpty(librosABuscar)) {
            final List<Integer> librosIds = new ArrayList<>();
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
                    final UsuarioEntity autora = this.usuarioRepository.findByEmail("kizuna.owo@gmail.com").orElseThrow(null);
                    final EntradaDTO nuevaEntradaVideo = new EntradaDTO();
                    nuevaEntradaVideo.setRedactor(ConversionUtils.getRedactorFromUsuario(autora));
                    nuevaEntradaVideo.setContenidoEntrada(videoData.getSnippet().getDescription());
                    nuevaEntradaVideo.setEntryStatus(EntryStatusEnum.PUBLISHED);
                    final EtiquetaDTO etiqueta = new EtiquetaDTO();

                    etiqueta.setNombreEtiqueta("Video momoko");
                    final List<EtiquetaDTO> setEtiquetas = new ArrayList<>();
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
                    nuevaEntradaVideo.setEntryType(EntryTypeEnum.VIDEO);
                    nuevaEntradaVideo.setTituloEntrada(video.getSnippet().getTitle());
                    nuevaEntradaVideo.setEditorNombre("La insomne");
                    nuevaEntradaVideo.setImagenDestacada("imagenes-destacadas/" + urlVideo + ".png");
                    final EntradaEntity entradaVideoEntity = obtenerEntradaEntityParaVideo(nuevaEntradaVideo);
                    final VideoEntity videoEntity = new VideoEntity();
                    videoEntity.setDescripcion(video.getSnippet().getDescription());
                    videoEntity.setFechaAlta(Calendar.getInstance().getTime());
                    videoEntity.setUsuarioAlta(autora.getEmail());
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

        final List<LibroDTO> librosEntrada = new ArrayList<>();
        final List<SagaDTO> sagasEntrada = new ArrayList<>();

        final List<String> titulosLibrosEntrada = entradaAGuardar.getTitulosLibrosEntrada();
        if (CollectionUtils.isNotEmpty(titulosLibrosEntrada)) {
            for (final String titulo : titulosLibrosEntrada) {
                librosEntrada.add(EntityToDTOAdapter.adaptarLibro(this.libroRepository.findOneByTitulo(titulo)));
            }
        }
        final List<String> nombresSagasEntrada = entradaAGuardar.getNombresSagasEntrada();
        if (CollectionUtils.isNotEmpty(nombresSagasEntrada)) {
            for (final String nombre : nombresSagasEntrada) {
                sagasEntrada
                        .add(EntityToDTOAdapter.adaptarSaga(this.sagaRepository.findOneByNombre(nombre), true, true));
            }
        }
        final UsuarioEntity autor = this.usuarioRepository
                .findByUsuarioLogin(entradaAGuardar.getRedactor().getNombre());

        final EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, librosEntrada,
                sagasEntrada, autor);

        if (CollectionUtils.isNotEmpty(entradaEntity.getEtiquetas())) {
            final Set<EtiquetaEntity> etiquetasBD = new HashSet<>();
            for (final EtiquetaEntity etiqueta : entradaEntity.getEtiquetas()) {
                List<String> etiquetas = new ArrayList<>();
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
                        nuevaEtiqueta.setUsuarioAlta(autor.getEmail());
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
        final List<GenreDTO> listaGeneros = this.generoService.obtenerGenerosCategoria(categoriaDTO);
        final List<Integer> generosIds = new ArrayList<Integer>();
        for (final GenreDTO generoDTO : listaGeneros) {
            generosIds.add(generoDTO.getGeneroId());
        }
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(generosIds,
                        PageRequest.of(pagina - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public Integer obtenerNumeroEntradasCategoria(final CategoriaDTO categoriaDTO) {
        final List<GenreDTO> listaGeneros = this.generoService.obtenerGenerosCategoria(categoriaDTO);
        final List<Integer> generosIds = new ArrayList<Integer>();
        for (final GenreDTO generoDTO : listaGeneros) {
            generosIds.add(generoDTO.getGeneroId());
        }
        final Long numeroEntradas = this.entradaRepository
                .findNumberEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(generosIds);
        return numeroEntradas.intValue();
    }

    @Override
    public List<EntradaSimpleDTO> obtenerNoticias(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaNoticias = this.entradaRepository
                .findByTipoEntradaOrderByCreatedDateDesc(EntryTypeEnum.NEWS.getValue(),
                        PageRequest.of(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaNoticias, true);
    }

    @Override
    public Integer obtenerNumeroNoticias() {
        return this.entradaRepository.countByTipoEntrada(EntryTypeEnum.NEWS.getValue()).intValue();
    }

    @Override
    public Collection<EntradaSimpleDTO> obtenerMiscelaneos(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaMiscelaneos = this.entradaRepository
                .findByTipoEntradaOrderByCreatedDateDesc(EntryTypeEnum.MISCELLANEOUS.getValue(),
                        PageRequest.of(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaMiscelaneos, true);
    }

    @Override
    public Integer obtenerNumeroMiscelaneos() {
        return this.entradaRepository.countByTipoEntrada(EntryTypeEnum.MISCELLANEOUS.getValue())
                .intValue();
    }

    @Override
    public Collection<EntradaSimpleDTO> obtenerVideos(final ObtenerPaginaElementoRequest request) {
        final List<EntradaEntity> listaMiscelaneos = this.entradaRepository
                .findByTipoEntradaOrderByCreatedDateDesc(EntryTypeEnum.VIDEO.getValue(),
                        PageRequest.of(request.getNumeroPagina() - 1, 9));
        return ConversionUtils.obtenerEntradasBasicas(listaMiscelaneos, true);
    }

    @Override
    public Integer obtenerNumeroVideos() {
        return this.entradaRepository.countByTipoEntrada(EntryTypeEnum.VIDEO.getValue()).intValue();
    }

    @Override
    public EntradaSimpleDTO obtenerEntradaSimple(final String urlEntrada) {
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        return ConversionUtils.obtenerEntradaSimpleDTO(entradaEntity, true);
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasEtiqueta(final EtiquetaDTO etiquetaDTO) {

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findByEtiquetasEtiquetaIdIn(Arrays.asList(etiquetaDTO.getEtiquetaId()));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasEtiquetaPorFecha(final EtiquetaDTO etiquetaDTO,
                                                                  final int numeroEntradas, final Integer numeroPagina) {

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(etiquetaDTO.getEtiquetaId(),
                        PageRequest.of(numeroPagina - 1, numeroEntradas));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public Integer obtenerNumeroEntradasEtiqueta(final EtiquetaDTO etiquetaDTO) {
        final Long numeroEntradas = this.entradaRepository
                .findNumberEntradasByEtiquetaAndFechaBajaIsNullOrderByFechaAltaDesc(etiquetaDTO.getEtiquetaId());
        return numeroEntradas.intValue();
    }

    @Override

    public List<EntradaDTO> obtenerOpinionesGeneros(final LibroDTO libro) {
        final List<Integer> idsGenero = new ArrayList<Integer>();
        final List<EntradaDTO> entradas = new ArrayList<EntradaDTO>();
        if (CollectionUtils.isNotEmpty(libro.getGeneros())) {
            for (final GenreDTO genero : libro.getGeneros()) {
                idsGenero.add(genero.getGeneroId());
            }
            final List<EntradaEntity> entradasEntity = this.entradaRepository
                    .findEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(idsGenero,
                            PageRequest.of(0, 4));

            for (final EntradaEntity entradaEntity : entradasEntity) {
                boolean entradaValida = true;
                for (final LibroEntity libroEntity : entradaEntity.getLibrosEntrada()) {
                    if (libroEntity.getLibroId().equals(libro.getLibroId())) {
                        entradaValida = false;
                        break;
                    }
                }
                if (entradaValida) {
                    entradas.add(EntityToDTOAdapter.adaptarEntrada(entradaEntity));
                }

            }
        }
        return entradas;
    }

    @Override
    public List<EntradaDTO> obtenerEntradasAleatoriasDeTipo(final Integer tipoEntrada) {
        final List<EntradaDTO> entradas = new ArrayList<>();
        final List<EntradaEntity> entradasEntity = this.entradaRepository.obtenerEntradasAleatoriasDeTipo(tipoEntrada);
        for (final EntradaEntity entradaEntity : entradasEntity) {
            entradas.add(EntityToDTOAdapter.adaptarEntrada(entradaEntity));
        }
        return entradas;
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasEditorPorFecha(final String urlEditor, final int numeroEntradas,
                                                                final Integer numeroPagina) {

        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findEntradaByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(urlEditor,
                        PageRequest.of(numeroPagina - 1, numeroEntradas));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public Integer obtenerNumeroEntradasEditor(final String urlEditor) {
        final Long numeroEntradas = this.entradaRepository
                .findNumberEntradasByEditorURLsAndFechaBajaIsNullOrderByFechaAltaDesc(urlEditor);
        return numeroEntradas.intValue();
    }

    @Override
    public List<EntradaSimpleDTO> obtenerEntradasEditorialPorFecha(final String urlEditorial, final int numeroEntradas,
                                                                   final Integer numeroPagina) {
        final List<EntradaEntity> listaEntities = this.entradaRepository.obtenerEntradasEditorialPorFecha(urlEditorial,
                PageRequest.of(numeroPagina - 1, numeroEntradas));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities, true);
    }

    @Override
    public void eliminarEtiqueta(final String urlEntrada, final Integer etiquetaId) {
        final EtiquetaEntity et = this.etiquetaRepository.findById(etiquetaId).orElse(null);
        final EntradaEntity entrada = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        EtiquetaEntity etiquetaAEliminar = null;
        for (final EtiquetaEntity etiqueta : entrada.getEtiquetas()) {
            if (etiqueta.getEtiquetaId().equals(etiquetaId)) {
                etiquetaAEliminar = etiqueta;
                break;
            }
        }
        entrada.getEtiquetas().remove(etiquetaAEliminar);
        et.getEtiquetasEntrada().remove(entrada);
        this.entradaRepository.save(entrada);
    }

    @Override
    public void anadirEtiqueta(final String urlEntrada, final Integer etiquetaId) {
        final EtiquetaEntity etiqueta = this.etiquetaRepository.findById(etiquetaId).orElse(null);
        final EntradaEntity entrada = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        entrada.getEtiquetas().add(etiqueta);
        etiqueta.getEtiquetasEntrada().add(entrada);
        this.entradaRepository.save(entrada);
    }


    @Override
    public EntradaDTO obtenerEntrada(final String urlEntrada) {
        return EntityToDTOAdapter.adaptarEntrada(this.entradaRepository.findFirstByUrlEntrada(urlEntrada));
    }

    @Override
    public List<ComentarioDTO> getEntryComments(String urlEntrada) {
        return this.comentarioService.obtenerComentariosEntrada(urlEntrada);
    }
}
