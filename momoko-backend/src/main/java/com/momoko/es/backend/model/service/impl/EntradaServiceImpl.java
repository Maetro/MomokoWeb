/**
 * EntradaServiceImpl.java 24-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.EtiquetaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.exceptions.NoSeEncuentraEntradaException;
import com.momoko.es.api.exceptions.URLEntradaYaExisteException;
import com.momoko.es.api.youtube.list.Video;
import com.momoko.es.api.youtube.list.YoutubeVideoList;
import com.momoko.es.api.youtube.video.Item;
import com.momoko.es.api.youtube.video.VideoYoutube;
import com.momoko.es.backend.model.entity.EntradaEntity;
import com.momoko.es.backend.model.entity.EtiquetaEntity;
import com.momoko.es.backend.model.entity.LibroEntity;
import com.momoko.es.backend.model.entity.PuntuacionEntity;
import com.momoko.es.backend.model.entity.UsuarioEntity;
import com.momoko.es.backend.model.entity.VideoEntity;
import com.momoko.es.backend.model.repository.EntradaRepository;
import com.momoko.es.backend.model.repository.EtiquetaRepository;
import com.momoko.es.backend.model.repository.LibroRepository;
import com.momoko.es.backend.model.repository.PuntuacionRepository;
import com.momoko.es.backend.model.repository.UsuarioRepository;
import com.momoko.es.backend.model.repository.VideoRepository;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.StorageService;
import com.momoko.es.util.ConversionUtils;
import com.momoko.es.util.DTOToEntityAdapter;
import com.momoko.es.util.EntityToDTOAdapter;

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

    @Autowired
    private StorageService almacenImagenes;

    @Autowired
    private ComentarioService comentarioService;

    @Override
    public ObtenerEntradaResponse obtenerEntrada(final String urlEntrada) {
        final ObtenerEntradaResponse respuesta = new ObtenerEntradaResponse();
        final EntradaEntity entradaEntity = this.entradaRepository.findFirstByUrlEntrada(urlEntrada);
        final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();

        try {
            entradaEntity.setImagenDestacada(
                    this.almacenImagenes.obtenerMiniatura(entradaEntity.getImagenDestacada(), 770, 432, true));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
        final LibroEntity libroEntrada = entradaEntity.getLibroEntrada();
        if (libroEntrada != null) {
            final List<EntradaEntity> entradasRelacionadas = this.entradaRepository.findByLibroEntrada(libroEntrada);

            Collections.sort(entradasRelacionadas);
            if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
                for (final EntradaEntity entradaRelacionadaEntity : entradasRelacionadas) {
                    final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                    datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                    datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                    listaDatosEntradas.add(datoEntrada);
                }
            }

            if (entradaDTO.getLibroEntrada() != null) {
                entradaDTO.getLibroEntrada().setEntradasLibro(listaDatosEntradas);
            }
            final List<LibroSimpleDTO> librosParecidos = this.libroService
                    .obtenerLibrosParecidos(entradaDTO.getLibroEntrada(), 5);

            final PuntuacionEntity puntuacion = this.puntuacionRepository.findOneByEsPuntuacionMomokoAndLibro(true,
                    libroEntrada);
            if (puntuacion != null) {
                entradaDTO.getLibroEntrada().setNotaMomoko(puntuacion.getValor());
            }
            respuesta.setCincoLibrosParecidos(librosParecidos);

        }
        // Si es tipo video anadimos su URL
        if (entradaDTO.getTipoEntrada().equals(TipoEntrada.VIDEO.getValue())) {
            final VideoEntity videoEntity = this.videoRepository
                    .findFirstByEntradaUrlEntrada(entradaDTO.getUrlEntrada());
            final String videoUrl = videoEntity.getUrlVideo();
            entradaDTO.setUrlVideo(videoUrl);
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
        respuesta.setEntrada(entradaDTO);
        respuesta.setComentarios(comentariosOrdenados);
        return respuesta;
    }

    @Override
    public List<EntradaDTO> recuperarEntradas() {
        final List<EntradaDTO> entradasDTO = new ArrayList<EntradaDTO>();
        final Iterable<EntradaEntity> entradasEntity = this.entradaRepository.findAll();
        final String imageServer = this.almacenImagenes.getUrlImageServer();
        for (final EntradaEntity entradaEntity : entradasEntity) {
            final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
            entradaDTO.setImagenDestacada(imageServer + entradaDTO.getImagenDestacada());
            entradasDTO.add(entradaDTO);
        }
        return entradasDTO;
    }

    @Override
    @Transactional
    public EntradaDTO guardarEntrada(final EntradaDTO entradaAGuardar) throws Exception {
        LibroDTO libroEntrada = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();
        if (StringUtils.isNotBlank(entradaAGuardar.getTituloLibroEntrada())) {
            libroEntrada = EntityToDTOAdapter
                    .adaptarLibro(this.libroRepository.findOneByTitulo(entradaAGuardar.getTituloLibroEntrada()));
        }
        UsuarioEntity autor = null;
        if (entradaAGuardar.getEntradaId() == null) {
            autor = this.usuarioRepository.findByUsuarioEmail(currentPrincipalName);
        }

        EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, libroEntrada, autor);

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
                        nuevaEtiqueta.setUsuarioAlta(currentPrincipalName);
                        etiquetaBD = this.etiquetaRepository.save(nuevaEtiqueta);
                    }
                    etiquetasBD.add(etiquetaBD);
                }

            }
            entradaEntity.setEtiquetas(etiquetasBD);
        }
        entradaEntity.setImagenDestacada(soloNombreImagenADestacadas(entradaEntity.getImagenDestacada()));
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

        if (entradaEntity.getLibroEntrada() != null) {
            viejaEntrada.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        } else {
            viejaEntrada.setLibroEntrada(null);
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
        this.entradaRepository.save(viejaEntrada);
        return viejaEntrada;
    }

    private EntradaEntity crearNuevaEntrada(final EntradaEntity entradaEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String currentPrincipalName = authentication.getName();

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
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

    private LibroEntity obtenerLibroEntrada(final LibroEntity libroABuscar) {

        LibroEntity libroEncontrado = null;
        if (libroABuscar != null) {
            libroEncontrado = this.libroRepository.findOne(libroABuscar.getLibroId());
        }
        return libroEncontrado;

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
                    final Set<EtiquetaDTO> setEtiquetas = new HashSet<EtiquetaDTO>();
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
                        this.almacenImagenes.store(img, "imagenes-destacadas", urlVideo);
                    } catch (final MalformedURLException e) {
                        e.printStackTrace();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                    nuevaEntradaVideo.setPermitirComentarios(true);
                    nuevaEntradaVideo.setUrlEntrada(urlVideoRemote);
                    nuevaEntradaVideo.setTipoEntrada(4);
                    nuevaEntradaVideo.setTituloEntrada(video.getSnippet().getTitle());
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
            System.out.println(videosCanalMomoko);
        }

        if (videoEntityBD != null) {
            final List<DatoEntradaDTO> listaDatosEntradas = new ArrayList<DatoEntradaDTO>();

            try {
                videoEntityBD.getEntrada().setImagenDestacada(this.almacenImagenes
                        .obtenerMiniatura(videoEntityBD.getEntrada().getImagenDestacada(), 770, 432, true));
            } catch (final IOException e) {
                e.printStackTrace();
            }
            final EntradaDTO entradaDTO = EntityToDTOAdapter.adaptarEntrada(videoEntityBD.getEntrada());
            final LibroEntity libroEntrada = videoEntityBD.getEntrada().getLibroEntrada();
            if (libroEntrada != null) {
                final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                        .findByLibroEntrada(libroEntrada);

                Collections.sort(entradasRelacionadas);
                if (CollectionUtils.isNotEmpty(entradasRelacionadas)) {
                    for (final EntradaEntity entradaRelacionadaEntity : entradasRelacionadas) {
                        final DatoEntradaDTO datoEntrada = new DatoEntradaDTO();
                        datoEntrada.setTipoEntrada(entradaRelacionadaEntity.getTipoEntrada());
                        datoEntrada.setUrlEntrada(entradaRelacionadaEntity.getUrlEntrada());
                        listaDatosEntradas.add(datoEntrada);
                    }
                }

                if (entradaDTO.getLibroEntrada() != null) {
                    entradaDTO.getLibroEntrada().setEntradasLibro(listaDatosEntradas);
                }
                final List<LibroSimpleDTO> librosParecidos = this.libroService
                        .obtenerLibrosParecidos(entradaDTO.getLibroEntrada(), 5);

                final PuntuacionEntity puntuacion = this.puntuacionRepository.findOneByEsPuntuacionMomokoAndLibro(true,
                        libroEntrada);
                if (puntuacion != null) {
                    entradaDTO.getLibroEntrada().setNotaMomoko(puntuacion.getValor());
                }
                respuesta.setCincoLibrosParecidos(librosParecidos);

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
            respuesta.setEntrada(entradaDTO);
            respuesta.setComentarios(comentariosOrdenados);
        }
        return respuesta;
    }

    private EntradaEntity obtenerEntradaEntityParaVideo(final EntradaDTO entradaAGuardar) {
        LibroDTO libroEntrada = null;
        if (StringUtils.isNotBlank(entradaAGuardar.getTituloLibroEntrada())) {
            libroEntrada = EntityToDTOAdapter
                    .adaptarLibro(this.libroRepository.findOneByTitulo(entradaAGuardar.getTituloLibroEntrada()));
        }
        final UsuarioEntity autor = this.usuarioRepository.findByUsuarioLogin(entradaAGuardar.getAutor().getNombre());

        final EntradaEntity entradaEntity = DTOToEntityAdapter.adaptarEntrada(entradaAGuardar, libroEntrada, autor);

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

        if (entradaEntity.getLibroEntrada() != null) {
            entradaEntity.setLibroEntrada(obtenerLibroEntrada(entradaEntity.getLibroEntrada()));
        }
        entradaEntity.setFechaAlta(Calendar.getInstance().getTime());
        entradaEntity.setUsuarioAlta(autor.getUsuarioEmail());

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
        return entradaEntity;
    }

    @Override
    public List<EntradaSimpleDTO> obtenerTresUltimasEntradasPopularesConLibro() {
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findByLibroEntradaNotNullOrderByLibroEntradaVisitasDesc(new PageRequest(0, 3));
        return ConversionUtils.obtenerEntradasBasicas(listaEntities);
    }

}
