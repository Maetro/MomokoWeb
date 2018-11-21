package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.response.IndexDataReponseDTO;
import com.momoko.es.api.index.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class IndexFrontController {

    private static final Logger log = LoggerFactory.getLogger(IndexFrontController.class);

    private final IndexService indexService;

    @Autowired
    public IndexFrontController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping(path = "/indexData")
    public @ResponseBody
    IndexDataReponseDTO getInfoIndex() {
        final StopWatch stopWatch = new StopWatch("getInfoIndex()");
        stopWatch.start("getInfoIndex");
        IndexDataReponseDTO obtenerIndexDataResponseDTO = this.indexService.getIndexDataResponse();
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        stopWatch.stop();
        stopWatch.start("obtenerLibrosMasVistos");
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        stopWatch.stop();
        stopWatch.start("obtenerUltimasFichas");
        final List<LibroSimpleDTO> ultimasOpiniones = this.indexService.obtenerUltimasFichas();
        stopWatch.stop();
        stopWatch.start("obtenerUltimoComicAnalizado");
        final LibroEntradaSimpleDTO ultimoComicAnalizado = this.indexService.obtenerUltimoComicAnalizado();
        stopWatch.stop();
        stopWatch.start("Crear objeto respuesta");
        obtenerIndexDataResponseDTO.setLibrosMasVistos(librosMasVistos);
        obtenerIndexDataResponseDTO.setUltimoComicAnalizado(ultimoComicAnalizado);
        obtenerIndexDataResponseDTO.setUltimosAnalisis(ultimasOpiniones);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return obtenerIndexDataResponseDTO;
    }

}
