/**
 * IndexServiceImpl.java 28-oct-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.momoko.es.api.dto.response.IndexDataReponseDTO;
import com.momoko.es.api.enums.EntryTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.CategoriaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.enums.TipoVisitaEnum;
import com.momoko.es.jpa.entry.entity.EntradaEntity;
import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.score.PuntuacionEntity;
import com.momoko.es.jpa.suscription.SuscripcionEntity;
import com.momoko.es.jpa.video.VideoEntity;
import com.momoko.es.jpa.entry.repository.EntradaRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.PuntuacionRepository;
import com.momoko.es.jpa.model.repository.SuscripcionRepository;
import com.momoko.es.jpa.model.repository.VideoRepository;
import com.momoko.es.jpa.model.repository.VisitaRepository;
import com.momoko.es.jpa.genre.service.GenreService;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.util.ConversionUtils;

/**
 * The Class IndexServiceImpl.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired(required = false)
    private EntradaRepository entradaRepository;

    @Autowired(required = false)
    private LibroRepository libroRepository;

    @Autowired(required = false)
    private PuntuacionRepository puntuacionRepository;

    @Autowired(required = false)
    private VisitaRepository visitaRepository;

    @Autowired(required = false)
    private StorageService storageService;

    @Autowired(required = false)
    private VideoRepository videoRepository;

    @Autowired(required = false)
    private GenreService generoService;

    @Autowired(required = false)
    private LibroService libroService;

    @Autowired(required = false)
    private SuscripcionRepository suscripcionRepository;

    @Autowired(required = false)
    private FileSystemStorageService almacenImagenes;

    @Override
    public List<EntradaSimpleDTO> obtenerUltimasEntradas() {
        final List<EntradaEntity> listaEntities = this.entradaRepository
                .findUltimasEntradas(Calendar.getInstance().getTime(), PageRequest.of(0, 11));
        final List<EntradaSimpleDTO> listaEntradasSimples = ConversionUtils.obtenerEntradasBasicas(listaEntities, true);

        for (int i = 0; i < 5; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 628, 418, true);
                    entradaSimpleDTO.setImagenEntrada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 5; i < 8; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 770, 450, true);
                    entradaSimpleDTO.setImagenEntrada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
            if (entradaSimpleDTO.getTipoEntrada().equals(EntryTypeEnum.VIDEO.getName())) {
                // Si es tipo video anadimos su URL
                final VideoEntity videoEntity = this.videoRepository
                        .findFirstByEntradaUrlEntrada(entradaSimpleDTO.getUrlEntrada());
                final String videoUrl = videoEntity.getUrlVideo();
                entradaSimpleDTO.setUrlVideo(videoUrl);

            }
        }
        for (int i = 8; i < 11; i++) {
            final EntradaSimpleDTO entradaSimpleDTO = listaEntradasSimples.get(i);
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                            entradaSimpleDTO.getImagenEntrada(), 520, 384, true);
                    entradaSimpleDTO.setImagenEntrada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaEntradasSimples;
    }

    @Override
    @Cacheable("mostSeenBooks")
    public List<LibroSimpleDTO> obtenerLibrosMasVistos() {
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);

        final List<String> librosMasVisitadosMes = this.visitaRepository
                .findTipoVisitaMasVistosDesde(PageRequest.of(0, 5), TipoVisitaEnum.LIBRO.toString(), c.getTime());

        final List<LibroEntity> listaLibros = this.libroRepository.findByUrlLibroIn(librosMasVisitadosMes);
        final List<Integer> listaLibrosIds = new ArrayList<>();
        for (final LibroEntity libroEntity : listaLibros) {
            listaLibrosIds.add(libroEntity.getLibroId());
        }
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listaPuntuaciones)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }
        }
        final List<LibroSimpleDTO> listaLibrosSimples = ConversionUtils.obtenerLibrosBasicos(listaLibros,
                mapaPuntacionMomokoPorLibro);

        for (final LibroSimpleDTO libroSimpleDTO : listaLibrosSimples) {
            if (libroSimpleDTO.getPortada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("portadas",
                            libroSimpleDTO.getPortada(), 170, 250, false);
                    libroSimpleDTO.setPortada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaLibrosSimples;
    }

    @Override
    @Cacheable("menu")
    public List<MenuDTO> obtenerMenu() {
        final List<GenreDTO> generos = this.generoService.getAllGenres();
        final List<CategoriaDTO> categorias = this.generoService.obtenerListaCategorias();
        Collections.sort(categorias);
        final List<MenuDTO> menu = new ArrayList<MenuDTO>();
        for (final CategoriaDTO categoria : categorias) {
            final MenuDTO menuPart = new MenuDTO();
            menuPart.setNombre(categoria.getNombreCategoria());
            menuPart.setUrl(categoria.getUrlCategoria());
            menuPart.setOrden(categoria.getOrden());
            menuPart.setShowOnMenu(categoria.isShowOnMenu());
            final List<GenreDTO> generosCategoria = new ArrayList<GenreDTO>();
            for (final GenreDTO generoDTO : generos) {
                if (generoDTO.getCategoria().equals(categoria)) {
                    final Integer numeroLibros = this.libroService.obtenerNumeroLibrosConAnalisisGenero(generoDTO);
                    if (numeroLibros > 0) {
                        generosCategoria.add(generoDTO);
                    }
                }
            }
            Collections.sort(generosCategoria);
            menuPart.setGeneros(generosCategoria);
            menu.add(menuPart);
        }
        return menu;
    }

    @Override
    public LibroEntradaSimpleDTO obtenerUltimoComicAnalizado() {
        final LibroEntradaSimpleDTO libroEntradaSimpleDTO = new LibroEntradaSimpleDTO();
        final CategoriaDTO categoria = this.generoService.obtenerCategoriaPorUrl("comics-novelas-graficas");
        final List<GenreDTO> generos = this.generoService.obtenerGenerosCategoria(categoria);
        final List<Integer> idsGeneros = new ArrayList<Integer>();
        for (final GenreDTO generoDTO : generos) {
            idsGeneros.add(generoDTO.getGeneroId());
        }

        final EntradaEntity ultimoComicAnalisis = this.entradaRepository
                .findEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(idsGeneros,
                        PageRequest.of(0, 1))
                .iterator().next();

        final LibroEntity ultimoComicAnalizado = ultimoComicAnalisis.getLibrosEntrada().iterator().next();

        final LibroSimpleDTO libroSimpleDTO = ConversionUtils.obtenerLibroSimpleDTO(ultimoComicAnalizado, null);

        if (libroSimpleDTO.getPortada() != null) {
            try {

                final String thumbnail = this.storageService.obtenerMiniatura("portadas", libroSimpleDTO.getPortada(),
                        296, 405, false);
                libroSimpleDTO.setPortada(thumbnail);
                final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes
                        .getImageDimensionsThumbnail(libroSimpleDTO.getPortada());
                libroSimpleDTO.setPortadaHeight(alturaAnchura.getAltura());
                libroSimpleDTO.setPortadaWidth(alturaAnchura.getAnchura());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        libroEntradaSimpleDTO.setEntrada(ConversionUtils.obtenerEntradaSimpleDTO(ultimoComicAnalisis, true));

        libroEntradaSimpleDTO.setLibro(libroSimpleDTO);
        return libroEntradaSimpleDTO;
    }

    @Override
    public List<LibroSimpleDTO> obtenerUltimosAnalisis() {
        final List<LibroEntity> listaLibros = this.libroRepository.findUltimosAnalisis(PageRequest.of(0, 5));
        final List<Integer> listaLibrosIds = new ArrayList<>();
        for (final LibroEntity libroEntity : listaLibros) {
            listaLibrosIds.add(libroEntity.getLibroId());
        }
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listaPuntuaciones)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }
        }
        final List<LibroSimpleDTO> listaLibrosSimples = ConversionUtils.obtenerLibrosBasicos(listaLibros,
                mapaPuntacionMomokoPorLibro);

        for (final LibroSimpleDTO libroSimpleDTO : listaLibrosSimples) {
            if (libroSimpleDTO.getPortada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("portadas",
                            libroSimpleDTO.getPortada(), 170, 250, false);
                    libroSimpleDTO.setPortada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaLibrosSimples;
    }

    @Override
    public List<LibroSimpleDTO> obtenerUltimasFichas() {
        final List<LibroEntity> listaLibros = this.libroRepository.findUltimasFichas(PageRequest.of(0, 5));
        final List<Integer> listaLibrosIds = new ArrayList<>();
        for (final LibroEntity libroEntity : listaLibros) {
            listaLibrosIds.add(libroEntity.getLibroId());
        }
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listaPuntuaciones)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }
        }
        final List<LibroSimpleDTO> listaLibrosSimples = ConversionUtils.obtenerLibrosBasicos(listaLibros,
                mapaPuntacionMomokoPorLibro);

        for (final LibroSimpleDTO libroSimpleDTO : listaLibrosSimples) {
            if (libroSimpleDTO.getPortada() != null) {
                try {

                    final String thumbnail = this.storageService.obtenerMiniatura("portadas",
                            libroSimpleDTO.getPortada(), 170, 250, false);
                    libroSimpleDTO.setPortada(thumbnail);

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaLibrosSimples;
    }

    @Override
    public void suscribirse(final String email) {
        if (this.suscripcionRepository.findOneByEmail(email) == null) {
            final SuscripcionEntity nuevaSuscripcion = new SuscripcionEntity();
            nuevaSuscripcion.setEmail(email);
            this.suscripcionRepository.save(nuevaSuscripcion);
        }

    }

    @Override
    @Cacheable("index")
    public IndexDataReponseDTO getIndexDataResponse() {
        IndexDataReponseDTO indexDataReponseDTO = new IndexDataReponseDTO();
        List<EntradaSimpleDTO> opinions = getLastEntriesOfType(3, EntryTypeEnum.OPINION, 370, 490);
        List<EntradaSimpleDTO> miscellaneous = getLastEntriesOfType(1, EntryTypeEnum.MISCELLANEOUS, 704, 469);
        List<EntradaSimpleDTO> news = getLastEntriesOfType(4, EntryTypeEnum.NEWS, 220, 220);
        List<String> alreadyUsedUrls = new ArrayList<>();
        alreadyUsedUrls.addAll(opinions.stream().map(EntradaSimpleDTO::getUrlEntrada).collect(Collectors.toList()));
        alreadyUsedUrls.addAll(miscellaneous.stream().map(EntradaSimpleDTO::getUrlEntrada).collect(Collectors.toList()));
        alreadyUsedUrls.addAll(news.stream().map(EntradaSimpleDTO::getUrlEntrada).collect(Collectors.toList()));
        List<EntradaSimpleDTO> masonryEntries = getLastEntriesNotUsedBefore(6, alreadyUsedUrls);
        indexDataReponseDTO.setLastOpinions(opinions);
        indexDataReponseDTO.setLastMiscellaneous(miscellaneous);
        indexDataReponseDTO.setLastNews(news);
        indexDataReponseDTO.setMasonryEntries(masonryEntries);
        return indexDataReponseDTO;
    }

    private List<EntradaSimpleDTO> getLastEntriesNotUsedBefore(int quantity, List<String> alreadyUsedUrls) {
        final List<EntradaEntity> lastReviews = this.entradaRepository
                .findLastEntriesNotUsed(Calendar.getInstance().getTime(), alreadyUsedUrls, PageRequest.of(0, quantity));
        final List<EntradaSimpleDTO> lastReviewsSimple = ConversionUtils.obtenerEntradasBasicas(lastReviews, true);
        Integer thumbnailHeight = 0;
        for (EntradaSimpleDTO entradaSimpleDTO : lastReviewsSimple) {
            switch (EntryTypeEnum.getEntryTypeByName(entradaSimpleDTO.getTipoEntrada())){
                case NEWS:
                    thumbnailHeight = 370;
                    break;
                case OPINION:
                    thumbnailHeight = 490;
                    break;
                case SPECIAL:
                case MISCELLANEOUS:
                case VIDEO:
                    thumbnailHeight = 246;
                    break;
                case EMPTY:
                    thumbnailHeight = 0;
            }
            adaptImagesToIndexPlace(entradaSimpleDTO, 370, thumbnailHeight);
        }
        return lastReviewsSimple;
    }


    private List<EntradaSimpleDTO> getLastEntriesOfType(int quantity, EntryTypeEnum entryType, int thumbnailWidth, int thumbnailHeight) {
        final List<EntradaEntity> lastReviews = this.entradaRepository
                .findLastEntries(Calendar.getInstance().getTime(), entryType, PageRequest.of(0, quantity));
        final List<EntradaSimpleDTO> lastReviewsSimple = ConversionUtils.obtenerEntradasBasicas(lastReviews, true);
        for (EntradaSimpleDTO entradaSimpleDTO : lastReviewsSimple) {
            adaptImagesToIndexPlace(entradaSimpleDTO, thumbnailWidth, thumbnailHeight);
        }
        return lastReviewsSimple;
    }


    private void adaptImagesToIndexPlace(EntradaSimpleDTO entradaSimpleDTO, Integer thumbnailWidth, Integer thumbnailHeight) {


        if (entradaSimpleDTO.getImagenEntrada() != null) {
            try {
                final String thumbnail = this.storageService.obtenerMiniatura("imagenes-destacadas",
                        entradaSimpleDTO.getImagenEntrada(), thumbnailWidth, thumbnailHeight, true);
                entradaSimpleDTO.setImagenEntrada(thumbnail);

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

    }

}