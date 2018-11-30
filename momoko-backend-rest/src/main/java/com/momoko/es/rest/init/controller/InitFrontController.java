package com.momoko.es.rest.init.controller;

import com.momoko.es.api.dto.InitDataDTO;
import com.momoko.es.api.dto.MenuDTO;
import com.momoko.es.api.index.service.IndexService;
import com.momoko.es.rest.index.controller.IndexFrontController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public")
public class InitFrontController {

    private static final Logger log = LoggerFactory.getLogger(IndexFrontController.class);

    private final IndexService indexService;

    @Autowired
    public InitFrontController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping(path = "/initData")
    public @ResponseBody
    InitDataDTO getInitData() {
        final StopWatch stopWatch = new StopWatch("getInitData()");
        stopWatch.start("Obtener Init Data");
        final List<MenuDTO> menu = this.indexService.obtenerMenu();

        final InitDataDTO initDataDTO = new InitDataDTO();
        initDataDTO.setMenu(menu);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
        return initDataDTO;
    }

}
