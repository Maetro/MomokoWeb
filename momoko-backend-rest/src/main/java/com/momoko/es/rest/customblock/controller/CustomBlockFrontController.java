package com.momoko.es.rest.customblock.controller;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.service.CustomBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/public/customblock")
public class CustomBlockFrontController {

    final CustomBlockService customBlockService;

    @Autowired
    public CustomBlockFrontController(CustomBlockService customBlockService) {
        this.customBlockService = customBlockService;
    }

    @GetMapping(path = {"", "/{type}", "/{type}/{numberOfBlocks}", "/{type}/{numberOfBlocks}/{excluded}"})
    public @ResponseBody ResponseEntity<List<CustomBlockDTO>>  getCustomBlock(
            @PathVariable("type") final Optional<String> type,
            @PathVariable("numberOfBlocks") final Optional<Integer> numberOfBlocks,
            @PathVariable("excluded") final Optional<List<Integer>> excluded) throws IOException {
        String finalType = type.isPresent() ? type.get(): "SIDEBAR";
        Integer finalNumberOfBlocks = numberOfBlocks.isPresent() ? numberOfBlocks.get(): 1;
        List<Integer> finalExcludedList = excluded.isPresent() ? excluded.get() : Arrays.asList(-1);
        List<CustomBlockDTO> customBlockPageResponse = customBlockService.getCustomBlockByType(finalType, finalNumberOfBlocks,
                finalExcludedList);
        return new ResponseEntity<>(customBlockPageResponse, HttpStatus.OK);
    }

}
