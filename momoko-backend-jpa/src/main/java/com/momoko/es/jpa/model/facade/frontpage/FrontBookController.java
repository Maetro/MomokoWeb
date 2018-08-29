package com.momoko.es.jpa.model.facade.frontpage;

import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.enums.FilterRuleType;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.dto.response.ApplyFilterResponseDTO;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.service.FilterService;
import com.momoko.es.jpa.model.repository.filter.IDynamicFilterRepository;
import com.momoko.es.jpa.model.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4000", "https://www.momoko.es" })
@RequestMapping(path = "/public")
public class FrontBookController {

    private final GenreService genreService;
    private final IDynamicFilterRepository dynamicFilterRepository;

    @Autowired
    public FrontBookController(GenreService genreService, IDynamicFilterRepository dynamicFilterRepository) {
        this.genreService = genreService;
        this.dynamicFilterRepository = dynamicFilterRepository;
    }

    @GetMapping(path = "/hola")
    void test(){
        List<FilterDTO> filters = new ArrayList<>();
        FilterDTO filter = new FilterDTO();
        filter.setFilterId(2);
        filter.setUrlFilter("origen-americano");
        filter.setValue(Arrays.asList("Marvel", "DC Cómics"));
        filter.setNameFilter("Origen");
        filter.setFilterType(FilterRuleType.ENUM);
        filters.add(filter);
        FilterDTO filter2 = new FilterDTO();
        filter2.setFilterId(1);
        filter2.setUrlFilter("logitud");
        filter2.setValue(Arrays.asList("0-100"));
        filter2.setNameFilter("logitud");
        filter2.setReferencedProperty("numeroPaginas");
        filter2.setFilterType(FilterRuleType.BETWEEN);
        filters.add(filter2);
        this.dynamicFilterRepository.getBookListWithAppliedFilters(filters);
    }

    @PostMapping(path = "/applyfilter/{url-genre}")
    ResponseEntity<ApplyFilterResponseDTO> applyFilter(@PathVariable("url-genre") final String urlGenre,
                                                       @RequestBody final List<FilterDTO> appliedFilters) {
        ApplyFilterResponseDTO result = this.genreService.getBooksWithFilters(urlGenre, appliedFilters);

        return new ResponseEntity<ApplyFilterResponseDTO>(result, HttpStatus.OK);
    }

}
