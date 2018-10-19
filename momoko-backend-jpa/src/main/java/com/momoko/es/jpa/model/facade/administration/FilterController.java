package com.momoko.es.jpa.model.facade.administration;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;
import com.momoko.es.api.service.filter.FilterService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/modelo")
public class FilterController {

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private FilterService filterService;

    @GetMapping(path = "/filter")
    public @ResponseBody
    List<FilterDTO> getAllFilters() {
        final List<FilterDTO> filters = this.filterService.getAllFilters();
        return filters;
    }

    @GetMapping(path = "/filter/{urlFilter}")
    public @ResponseBody
    FilterDTO getFilter(@PathVariable("urlFilter") String urlFilter) {
        final FilterDTO filter = this.filterService.getFilterByUrl(urlFilter);
        return filter;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/filter/add")
    ResponseEntity<SaveFilterResponse> saveFilter(@RequestBody final FilterDTO filterDTO) {
        SaveFilterResponse response = new SaveFilterResponse();
        try {
            response = this.filterService.saveFilter(filterDTO);
        } catch (Exception e) {
            response.setErrorMessage(e);
            return new ResponseEntity<SaveFilterResponse>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SaveFilterResponse>(response, HttpStatus.OK);

    }

    @PostMapping(path = "/filter/genre")
    public @ResponseBody
    List<FilterDTO> getFilter(@RequestBody List<String> urlGenre) {
        final List<FilterDTO> filters = this.filterService.getFiltersAppliedByGenreUrl(urlGenre);
        return filters;
    }




}
