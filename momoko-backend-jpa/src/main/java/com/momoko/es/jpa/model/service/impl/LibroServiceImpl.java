/**
 * LibroServiceImpl.java 08-jul-2017
 * <p>
 * Copyright 2017 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.service.impl;

import com.momoko.es.api.dto.*;
import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.filter.dto.FilterDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.dto.response.GuardarLibroResponse;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.TipoEntrada;
import com.momoko.es.api.enums.errores.ErrorCreacionLibro;
import com.momoko.es.api.exceptions.NoExisteGeneroException;
import com.momoko.es.api.author.service.AuthorService;
import com.momoko.es.api.filter.service.FilterService;
import com.momoko.es.jpa.author.entity.AuthorEntity;
import com.momoko.es.jpa.author.repository.AuthorRepository;
import com.momoko.es.jpa.model.entity.*;
import com.momoko.es.jpa.model.repository.*;
import com.momoko.es.jpa.model.service.*;
import com.momoko.es.jpa.model.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Class LibroServiceImpl.
 */
@Service
public class LibroServiceImpl implements LibroService {

    private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);

    private final EditorialService editorialService;

    private final AuthorService authorService;

    private final AuthorRepository authorRepository;

    private final LibroRepository libroRepository;

    private final EntradaRepository entradaRepository;

    private final EditorialRepository editorialRepository;

    private final StorageService almacenImagenes;

    private final PuntuacionRepository puntuacionRepository;

    private final GeneroRepository generoRepository;

    private final FilterService filterService;

    private final ValidadorService validadorService;

    private final PuntuacionService puntuacionService;


    @Autowired(required = false)
    public LibroServiceImpl(final EditorialService editorialService, AuthorService authorService, AuthorRepository authorRepository, final LibroRepository libroRepository,
                            final EntradaRepository entradaRepository, final EditorialRepository editorialRepository,
                            final StorageService almacenImagenes, final PuntuacionRepository puntuacionRepository,
                            final GeneroRepository generoRepository, final FilterService filterService,
                            final ValidadorService validadorService, final PuntuacionService puntuacionService) {
        this.editorialService = editorialService;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.libroRepository = libroRepository;
        this.entradaRepository = entradaRepository;
        this.editorialRepository = editorialRepository;
        this.almacenImagenes = almacenImagenes;
        this.puntuacionRepository = puntuacionRepository;
        this.generoRepository = generoRepository;
        this.filterService = filterService;
        this.validadorService = validadorService;
        this.puntuacionService = puntuacionService;
    }

    @Override
    public List<LibroDTO> recuperarLibros() {
        final List<LibroDTO> listaLibros = new ArrayList<>();
        final Iterable<LibroEntity> libroEntityIterable = this.libroRepository.findAll();
        final String urlImageServer = this.almacenImagenes.getUrlImageServer();
        for (final LibroEntity libroEntity : libroEntityIterable) {

            final LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);

            // Nota Momoko del libro
            final PuntuacionEntity puntuacionEntity = this.puntuacionRepository
                    .findOneByEsPuntuacionMomokoAndLibro(true, libroEntity);
            if (puntuacionEntity != null) {
                libroDTO.setNotaMomoko(puntuacionEntity.getValor());
                libroDTO.setComentarioNotaMomoko(puntuacionEntity.getComentario());
            }
            if (libroDTO.getUrlImagen() != null) {
                libroDTO.setUrlImagen(urlImageServer + libroDTO.getUrlImagen());
            }
            final String autoresString = MomokoUtils.generarAutoresString(libroDTO);

            libroDTO.setAutoresString(autoresString);

            final String generosString = MomokoUtils.generarGenerosString(libroDTO);

            libroDTO.setGenerosString(generosString);

            listaLibros.add(libroDTO);
        }
        return listaLibros;
    }

    @Override
    public LibroDTO guardarLibro(final LibroDTO libroAGuardar) throws Exception {
        final LibroEntity libroEntity = DTOToEntityAdapter.adaptarLibro(libroAGuardar);
        //Remove when puntuacion trransition is over
        if (libroAGuardar.getNotaMomoko() != null) {
            libroEntity.setScore(libroAGuardar.getNotaMomoko().intValue());
        }
        // Comprobamos si el autor existe.
        final List<LibroEntity> coincidencias = this.libroRepository.findByTitulo(libroAGuardar.getTitulo());

        if ((CollectionUtils.isEmpty(coincidencias)) || ((libroAGuardar.getLibroId() != null))) {
            if ((libroEntity.getLibroId() != null) && CollectionUtils.isNotEmpty(coincidencias)) {
                if ((coincidencias.size() > 1)
                        || (!libroEntity.getLibroId().equals(coincidencias.get(0).getLibroId()))) {
                    throw new Exception("El titulo del libro ya se esta utilizando");
                }
            }
            Set<AuthorDTO> authors = this.authorService.getOrSaveBookAuthorByName(libroAGuardar);
            final Set<AuthorEntity> autoresObra = new HashSet<>();
            for (AuthorDTO authorDTO : authors) {
                autoresObra.add(this.authorRepository.findById(authorDTO.getAuthorId()).orElseThrow(
                        () -> new InstanceNotFoundException("author Id: "+ authorDTO.getAuthorId())));
            }
            libroEntity.setAutores(autoresObra);

            final EditorialEntity editorialObra = this.editorialService
                    .obtenerEditorialOCrear(libroEntity.getEditorial());
            libroEntity.setEditorial(editorialObra);
            final Set<GenreEntity> generosObra = obtenerGenerosObra(libroEntity);
            libroEntity.setGeneros(generosObra);

            if (libroEntity.getUrlImagen() != null) {
                libroEntity.setUrlImagen(soloNombreImagenAPortadas(libroEntity.getUrlImagen()));
            }
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String currentPrincipalName = authentication.getName();
            if (libroEntity.getLibroId() != null) {
                final LibroEntity libroBD = this.libroRepository.findById(libroEntity.getLibroId()).orElse(null);
                libroEntity.setFechaAlta(libroBD.getFechaAlta());
                libroEntity.setUsuarioAlta(libroBD.getUsuarioAlta());
                libroEntity.setUsuarioModificacion(currentPrincipalName);
                libroEntity.setFechaModificacion(Calendar.getInstance().getTime());
            } else {
                libroEntity.setUsuarioAlta(currentPrincipalName);
                libroEntity.setFechaAlta(Calendar.getInstance().getTime());
            }
            LibroDTO libroGuardado = EntityToDTOAdapter.adaptarLibro(this.libroRepository.save(libroEntity));
            this.filterService.saveBookFilters(libroGuardado.getLibroId(), libroAGuardar);
            return libroGuardado;

        } else {
            throw new Exception("El titulo del libro ya se esta utilizando");
        }

    }



    private Set<GenreEntity> obtenerGenerosObra(final LibroEntity libroEntity) throws NoExisteGeneroException {
        final Set<GenreEntity> generosObra = new HashSet<GenreEntity>();
        if (CollectionUtils.isNotEmpty(libroEntity.getGeneros())) {
            for (final GenreEntity genero : libroEntity.getGeneros()) {

                final GenreEntity generoBD = this.generoRepository
                        .findOneByNombreAndFechaBajaIsNull(genero.getNombre());
                if (generoBD == null) {
                    throw new NoExisteGeneroException("No existe el genero seleccionado en la BD");

                }
                generosObra.add(generoBD);
            }
        }
        return generosObra;
    }

    private String soloNombreImagenAPortadas(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return "portadas/" + lista[elementos - 1];
    }


    @Override
    public List<String> obtenerListaNombresEditoriales() {
        return this.editorialRepository.findAllNombresEditoriales();
    }

    @Override
    public List<String> obtenerListaTitulosLibros() {
        return this.libroRepository.findAllTitulosLibros();
    }

    @Override
    public LibroDTO obtenerLibroConEntradas(final String urlLibro) {
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        return EntityToDTOAdapter.adaptarLibro(libroEntity, true);
    }

    @Override
    public ObtenerFichaLibroResponse obtenerFichaLibroResponse(final String urlLibro) {
        final ObtenerFichaLibroResponse respuesta = new ObtenerFichaLibroResponse();
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        respuesta.setDatosEntrada(ConversionUtils.obtenerDatosEntradaFromEntradaEntityList(libroEntity.getEntradas()));
        if (libroEntity != null) {
            final List<EntradaEntity> entradasRelacionadas = this.entradaRepository
                    .findByLibrosEntradaIn(Arrays.asList(libroEntity), new PageRequest(0, 4));
            Collections.sort(entradasRelacionadas);
            final List<EntradaSimpleDTO> entradasBasicas = ConversionUtils.obtenerEntradasBasicas(entradasRelacionadas,
                    true);
            // generar miniaturas de 304 x 221
            for (final EntradaSimpleDTO entradaSimpleDTO : entradasBasicas) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            this.almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), 304, 221, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

            respuesta.setTresUltimasEntradas(entradasBasicas);

            final List<DatoEntradaDTO> listaDatosEntradas = ConversionUtils
                    .obtenerDatosEntradaFromEntradaEntityList(entradasRelacionadas);
            LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);
            libroDTO = MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroDTO);
            // Nota Momoko del libro
            final PuntuacionEntity puntuacionEntity = this.puntuacionRepository
                    .findOneByEsPuntuacionMomokoAndLibro(true, libroEntity);
            if (puntuacionEntity != null) {
                libroDTO.setNotaMomoko(puntuacionEntity.getValor());
                libroDTO.setComentarioNotaMomoko(puntuacionEntity.getComentario());
            }
            libroDTO.setEntradasLibro(listaDatosEntradas);

            if (libroEntity.getOrdenSaga() != null) {
                libroDTO.setSaga(EntityToDTOAdapter.adaptarSaga(libroEntity.getSaga(), false, false));
            }

            respuesta.setLibro(libroDTO);
        }
        return respuesta;
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosParecidos(final LibroDTO libro, final int numeroLibros) {
        final Set<GenreEntity> generos = DTOToEntityAdapter.adaptarGeneros(libro.getGeneros());
        final List<Integer> idsGeneros = new ArrayList<>();
        for (final GenreEntity generoEntity : generos) {
            idsGeneros.add(generoEntity.getGeneroId());
        }

        final List<LibroEntity> listaLibrosParecidos = this.libroRepository
                .findLibrosParecidosByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(idsGeneros, libro.getLibroId(),
                        PageRequest.of(0, numeroLibros));

        final List<Integer> listaLibrosIds = new ArrayList<>();
        final List<PuntuacionEntity> listaPuntuaciones = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, listaLibrosIds);
        final Map<LibroEntity, PuntuacionEntity> mapaPuntacionMomokoPorLibro = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listaLibrosParecidos)) {
            for (final PuntuacionEntity puntuacionEntity : listaPuntuaciones) {
                mapaPuntacionMomokoPorLibro.put(puntuacionEntity.getLibro(), puntuacionEntity);
            }

        }
        return ConversionUtils.obtenerLibrosBasicos(listaLibrosParecidos, mapaPuntacionMomokoPorLibro);
    }

    @Override
    public List<LibroSimpleDTO> obtenerLibrosConAnalisisGeneroPorFecha(final GenreDTO genero, final int numeroLibros,
                                                                       final int pagina) {

        final List<Integer> idsGeneros = new ArrayList<>();

        idsGeneros.add(genero.getGeneroId());

        final List<LibroEntity> listaLibrosGenero = this.libroRepository.obtenerLibrosConAnalisisGeneroPorFecha(
                idsGeneros, Calendar.getInstance().getTime(), PageRequest.of(pagina, numeroLibros));

        return ConversionUtils.obtenerLibrosBasicos(listaLibrosGenero, null);
    }

    @Override
    public Integer obtenerNumeroLibrosConAnalisisGenero(final GenreDTO generoDTO) {
        final Long numeroResultados = this.entradaRepository
                .findNumberEntradaOpinionesLibroByGenerosAndFechaBajaIsNullOrderByFechaAltaDesc(
                        Arrays.asList(generoDTO.getGeneroId()));
        return numeroResultados.intValue();
    }

    @Override
    public List<LibroDTO> getBooksWithUrlIn(final List<String> librosUrl) {
        final List<LibroEntity> listaLibros = this.libroRepository.findByUrlLibroIn(librosUrl);
        return EntityToDTOAdapter.adaptarLibros(listaLibros);
    }

    @Override
    public List<LibroDTO> obtenerLibros(final List<String> librosUrl) {
        final List<LibroEntity> listaLibros = this.libroRepository.findByUrlLibroIn(librosUrl);
        final List<LibroDTO> listaLibrosDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listaLibros)) {

            listaLibrosDTO.addAll(EntityToDTOAdapter.adaptarLibros(listaLibros));
            for (final LibroDTO libroDTO : listaLibrosDTO) {

                try {
                    final AnchuraAlturaDTO alturaAnchura = this.almacenImagenes
                            .getImageDimensions(libroDTO.getUrlImagen());
                    libroDTO.setPortadaHeight(alturaAnchura.getAltura());
                    libroDTO.setPortadaWidth(alturaAnchura.getAnchura());
                    final String url = this.almacenImagenes.getUrlImageServer();

                    libroDTO.setUrlImagen(
                            this.almacenImagenes.obtenerMiniatura(libroDTO.getUrlImagen(), 200, 310, true));
                    final Set<GenreDTO> generosImagenes = new HashSet<GenreDTO>();
                    for (final GenreDTO generoDTO : libroDTO.getGeneros()) {
                        generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                        generosImagenes.add(generoDTO);
                    }
                    libroDTO.setGeneros(generosImagenes);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaLibrosDTO;
    }

    @Override
    public EntradaDTO obtenerOpinionesLibro(final String urlLibro) {
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlLibro);
        final List<EntradaEntity> entradas = libroEntity.getEntradas();
        EntradaDTO analisisLibro = null;
        for (final EntradaEntity entradaEntity : entradas) {
            if (TipoEntrada.OPINIONES.getValue().equals(entradaEntity.getTipoEntrada())) {
                analisisLibro = EntityToDTOAdapter.adaptarEntrada(entradaEntity);
                break;
            }
        }
        return analisisLibro;
    }

    @Override
    public BigDecimal obtenerPuntucionMomokoLibro(final String urlLibro) {
        return this.puntuacionRepository.findOneByEsPuntuacionMomokoAndLibroUrl(urlLibro);
    }

    @Override
    @Transactional
    public List<LibroDTO> getAllBooks() {
        List<LibroEntity> bookEntities = this.libroRepository.findAll();
        List<LibroDTO> books = ConversionUtils.getDataForBookAdminTable(bookEntities);
        return books;
    }

    @Override
    public LibroDTO getBookForModification(String urlBook) {
        final LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(urlBook);
        LibroDTO libroDTO = EntityToDTOAdapter.adaptarLibro(libroEntity);
        libroDTO = MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroDTO);
        // Nota Momoko del libro
        final PuntuacionEntity puntuacionEntity = this.puntuacionRepository
                .findOneByEsPuntuacionMomokoAndLibro(true, libroEntity);
        if (puntuacionEntity != null) {
            libroDTO.setNotaMomoko(puntuacionEntity.getValor());
            libroDTO.setComentarioNotaMomoko(puntuacionEntity.getComentario());
        }
        if (CollectionUtils.isNotEmpty(libroDTO.getGeneros())) {
            List<String> urlsList = libroDTO.getGeneros().stream().map(GenreDTO::getUrlGenero).collect(Collectors.toList());
            List<FilterDTO> applicableFilters = this.filterService.getFiltersAppliedByGenreUrl(urlsList);
            List<FilterDTO> bookFilters = this.filterService.getBookFilterValues(Arrays.asList(libroDTO.getUrlLibro()));
            if (CollectionUtils.isNotEmpty(applicableFilters)){
                for (FilterDTO applicableFilter : applicableFilters) {
                    if (!bookFilters.contains(applicableFilter)){
                        bookFilters.add(applicableFilter);
                    }
                }
            }
            libroDTO.setFilters(bookFilters);
        }

        return libroDTO;
    }

    @Override
    public GuardarLibroResponse saveBook(LibroDTO bookDTO) {


        // Validar
        final List<ErrorCreacionLibro> listaErrores = this.validadorService.validarLibro(bookDTO);

        // Guardar
        LibroDTO libro = null;
        if (org.springframework.util.CollectionUtils.isEmpty(listaErrores)) {
            try {
                libro = guardarLibro(bookDTO);
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(ErrorCreacionLibro.TITULO_YA_EXISTE);
            }
        }

        if ((bookDTO.getNotaMomoko() != null) && (libro != null)) {
            final PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
            puntuacionDTO.setValor(bookDTO.getNotaMomoko());
            puntuacionDTO.setComentario(bookDTO.getComentarioNotaMomoko());
            puntuacionDTO.setEsPuntuacionMomoko(true);
            puntuacionDTO.setLibroId(libro.getLibroId());
            try {
                this.puntuacionService.guardarPuntuacion(puntuacionDTO);
            } catch (final Exception e) {
                listaErrores.add(ErrorCreacionLibro.PUNTUACION_YA_EXISTE);
                log.error("context", e);

            }
        }

        // Responder
        final GuardarLibroResponse respuesta = new GuardarLibroResponse();
        respuesta.setLibroDTO(libro);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((libro != null) && org.springframework.util.CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }
        return respuesta;
    }

}