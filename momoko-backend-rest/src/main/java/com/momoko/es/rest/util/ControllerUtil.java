package com.momoko.es.rest.util;

import com.momoko.es.api.dto.DatoEntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.request.ObtenerPaginaElementoRequest;
import com.momoko.es.api.enums.EntryTypeEnum;
import com.momoko.es.jpa.model.service.EntradaService;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.rest.publisher.controller.PublisherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class ControllerUtil {

    private static final Logger log = LoggerFactory.getLogger(ControllerUtil.class);

    private ControllerUtil() {
    }

    public static ObtenerPaginaElementoRequest getObtenerPaginaElementoRequestEmpty(
            String urlCategoria, Integer numeroPagina, ObtenerPaginaElementoRequest request) {
        if (request == null) {
            request = new ObtenerPaginaElementoRequest();
            request.setNumeroPagina(numeroPagina);
            request.setOrdenarPor("fecha");
            request.setUrlElemento(urlCategoria);
        }
        return request;
    }

    public static void generateThumbnailsOfEntrySimpleDTOS(List<EntradaSimpleDTO> entradaSimpleDTOS,
                                                           StorageService almacenImagenes, Integer width, Integer height,
                                                           boolean recortar) {
        for (final EntradaSimpleDTO entradaSimpleDTO : entradaSimpleDTOS) {
            if (entradaSimpleDTO.getImagenEntrada() != null) {
                try {
                    entradaSimpleDTO.setImagenEntrada(
                            almacenImagenes.obtenerMiniatura(entradaSimpleDTO.getImagenEntrada(), width, height, recortar));
                } catch (final IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void addDatosEntradaLibroTipo(final EntradaService entradaService, final StorageService storageService,
                                                final List<EntradaSimpleDTO> entries, final List<DatoEntradaDTO> simpleEntries,
                                                final Set<DatoEntradaDTO> datosEntrada, final EntryTypeEnum entryType) {
        for (final DatoEntradaDTO datoEntradaDTO : simpleEntries) {
            if (datoEntradaDTO.getTipoEntrada().equals(entryType.getValue())) {
                final EntradaSimpleDTO entradaSimple = entradaService
                        .obtenerEntradaSimple(datoEntradaDTO.getUrlEntrada());
                ControllerUtil.generateThumbnailsOfEntrySimpleDTOS(Arrays.asList(entradaSimple), storageService, 370, 208, true);
                entries.add(entradaSimple);
            }
            datosEntrada.add(datoEntradaDTO);
        }
    }


}
