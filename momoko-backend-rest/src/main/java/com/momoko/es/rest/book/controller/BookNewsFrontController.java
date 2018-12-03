package com.momoko.es.rest.book.controller;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.SagaDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.dto.response.ObtenerPaginaLibroNoticiasResponse;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.api.exceptions.UrlElementNotFoundException;
import com.momoko.es.api.entry.service.EntradaService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.SagaService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.util.MomokoThumbnailUtils;
import com.momoko.es.rest.util.ControllerUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class BookNewsFrontController {

    private static final Logger log = LoggerFactory.getLogger(BookFrontController.class);

    private final LibroService libroService;
    private final EntradaService entradaService;
    private final StorageService almacenImagenes;
    private final SagaService sagaService;

    @Autowired
    public BookNewsFrontController(LibroService libroService, StorageService almacenImagenes,
                               EntradaService entradaService, SagaService sagaService) {
        this.libroService = libroService;
        this.almacenImagenes = almacenImagenes;
        this.entradaService = entradaService;
        this.sagaService = sagaService;
    }

    @GetMapping(path = "/noticias-libro/{url-libro}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaLibroNoticiasResponse obtenerNoticiasLibroPagina(
            @PathVariable("url-libro") final String urlLibro, @PathVariable("numero-pagina") final Integer numeroPagina,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse = new ObtenerPaginaLibroNoticiasResponse();
        final List<EntradaSimpleDTO> noticias = new ArrayList<>();
        ObtenerPaginaElementoRequest finalRequest = ControllerUtil.getObtenerPaginaElementoRequestEmpty(urlLibro,
                numeroPagina, request);

        return this.obtenerPaginaLibroNoticiasResponse(urlLibro, finalRequest, paginaLibroNoticiasResponse, noticias);

    }

    @GetMapping(path = "/noticias-libro/{url-libro}")
    public @ResponseBody
    ObtenerPaginaLibroNoticiasResponse obtenerNoticiasLibro(
            @PathVariable("url-libro") final String urlLibro,
            @RequestBody(required = false) ObtenerPaginaElementoRequest request) {
        final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse = new ObtenerPaginaLibroNoticiasResponse();
        final List<EntradaSimpleDTO> noticias = new ArrayList<>();
        ObtenerPaginaElementoRequest finalRequest = ControllerUtil.getObtenerPaginaElementoRequestEmpty(urlLibro, 1, request);
        return this.obtenerPaginaLibroNoticiasResponse(urlLibro, finalRequest, paginaLibroNoticiasResponse, noticias);

    }

    private ObtenerPaginaLibroNoticiasResponse obtenerPaginaLibroNoticiasResponse(
            final String urlLibro, final ObtenerPaginaElementoRequest request,
            final ObtenerPaginaLibroNoticiasResponse paginaLibroNoticiasResponse,
            final List<EntradaSimpleDTO> noticias) {
        final LibroDTO libroRaw = this.libroService.obtenerLibroConEntradas(urlLibro);
        final LibroDTO libro = MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroRaw);
        final List<DatoEntradaDTO> entradasSimples = libro.getEntradasLibro();

        final Set<DatoEntradaDTO> datosEntrada = new HashSet<>();
        ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, noticias, entradasSimples,
                datosEntrada, EntryTypeEnum.NEWS);
        if (CollectionUtils.isEmpty(noticias) && (libro.getSaga() != null)) {
            final List<String> urlsLibrosSaga = this.sagaService.obtenerListaUrlsLibros(libro.getSaga().getSagaId());
            final List<LibroDTO> librosSaga = this.libroService.obtenerLibros(urlsLibrosSaga);
            librosSaga.forEach(libroSaga -> {
                if (!libroSaga.getUrlLibro().equals(libro.getUrlLibro())) {
                    final List<DatoEntradaDTO> entradasSimplesHermano = libro.getEntradasLibro();
                    ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, noticias,
                            entradasSimplesHermano, datosEntrada, EntryTypeEnum.NEWS);
                }
            });
            if (CollectionUtils.isEmpty(noticias)) {
                try {
                    final SagaDTO sagaLibro = this.sagaService.obtenerSaga(libro.getSaga().getUrlSaga());
                    final List<DatoEntradaDTO> entradasSaga = sagaLibro.getEntradasSaga();
                    ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, noticias,
                            entradasSaga, datosEntrada, EntryTypeEnum.NEWS);

                } catch (final UrlElementNotFoundException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        paginaLibroNoticiasResponse.setDatoEntrada(new ArrayList<>(datosEntrada));
        paginaLibroNoticiasResponse.setLibro(libro);
        paginaLibroNoticiasResponse.setNoticias(noticias);
        paginaLibroNoticiasResponse.setNumeroEntradas(noticias.size());
        return paginaLibroNoticiasResponse;
    }


}
