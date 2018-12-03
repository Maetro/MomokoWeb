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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class BookMiscelaneousFrontController {

    private static final Logger log = LoggerFactory.getLogger(BookMiscelaneousFrontController.class);

    private final LibroService libroService;
    private final EntradaService entradaService;
    private final StorageService almacenImagenes;
    private final SagaService sagaService;

    @Autowired
    public BookMiscelaneousFrontController(LibroService libroService, StorageService almacenImagenes,
                                   EntradaService entradaService, SagaService sagaService) {
        this.libroService = libroService;
        this.almacenImagenes = almacenImagenes;
        this.entradaService = entradaService;
        this.sagaService = sagaService;
    }

    @GetMapping(path = "/miscelaneos-libro/{url-libro}/{numero-pagina}")
    public @ResponseBody
    ObtenerPaginaLibroNoticiasResponse obtenerMiscelaneosLibroPagina(
            @PathVariable("url-libro") final String urlLibro,
            @PathVariable("numero-pagina") final Integer numeroPagina) {

        return obtenerPaginaLibroMiscelaneosResponse(urlLibro, numeroPagina);

    }

    @GetMapping(path = "/miscelaneos-libro/{url-libro}")
    public @ResponseBody
    ObtenerPaginaLibroNoticiasResponse obtenerMiscelaneosLibro(
            @PathVariable("url-libro") final String urlLibro,
            @RequestBody(required = false) final ObtenerPaginaElementoRequest request) {

        return obtenerPaginaLibroMiscelaneosResponse(urlLibro, 1);

    }

    private ObtenerPaginaLibroNoticiasResponse obtenerPaginaLibroMiscelaneosResponse(final String urlLibro,
                                                                                     final Integer numeroPagina) {
        log.debug("obtenerPaginaLibroMiscelaneosResponse");
        final ObtenerPaginaLibroNoticiasResponse response = new ObtenerPaginaLibroNoticiasResponse();
        final LibroDTO libroRaw = this.libroService.obtenerLibroConEntradas(urlLibro);
        final LibroDTO libro = MomokoThumbnailUtils.tratarImagenesFichaLibro(this.almacenImagenes, libroRaw);
        final List<DatoEntradaDTO> entradasSimples = libro.getEntradasLibro();

        final Set<DatoEntradaDTO> datosEntrada = new HashSet<>();
        final List<EntradaSimpleDTO> miscelaneos = new ArrayList<>();
        ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, miscelaneos, entradasSimples,
                datosEntrada, EntryTypeEnum.MISCELLANEOUS);
        if ((miscelaneos.isEmpty()) && (libro.getSaga() != null)) {
            final List<String> urlsLibrosSaga = this.sagaService.obtenerListaUrlsLibros(libro.getSaga().getSagaId());
            final List<LibroDTO> librosSaga = this.libroService.obtenerLibros(urlsLibrosSaga);
            librosSaga.forEach(libroSaga -> {
                if (!libroSaga.getUrlLibro().equals(libro.getUrlLibro())) {
                    final List<DatoEntradaDTO> entradasSimplesHermano = libro.getEntradasLibro();
                    ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, miscelaneos,
                            entradasSimplesHermano, datosEntrada, EntryTypeEnum.MISCELLANEOUS);
                }
            });
            if (miscelaneos.isEmpty()) {
                try {
                    final SagaDTO sagaLibro = this.sagaService.obtenerSaga(libro.getSaga().getUrlSaga());
                    final List<DatoEntradaDTO> entradasSaga = sagaLibro.getEntradasSaga();
                    ControllerUtil.addDatosEntradaLibroTipo(this.entradaService, this.almacenImagenes, miscelaneos,
                            entradasSaga, datosEntrada, EntryTypeEnum.MISCELLANEOUS);

                } catch (final UrlElementNotFoundException urlElementNotFoundException) {
                    urlElementNotFoundException.printStackTrace();
                }
            }
        }
        response.setDatoEntrada(new ArrayList<>(datosEntrada));
        response.setLibro(libro);
        response.setNoticias(miscelaneos);
        response.setNumeroEntradas(miscelaneos.size());
        return response;
    }

}
