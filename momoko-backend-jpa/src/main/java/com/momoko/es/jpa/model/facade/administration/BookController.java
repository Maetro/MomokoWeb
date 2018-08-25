package com.momoko.es.jpa.model.facade.administration;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;
import com.momoko.es.api.service.FilterService;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4000", "https://www.momoko.es" })
@RequestMapping(path = "/modelo")
public class BookController {

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private LibroService libroService;

    @GetMapping(path = "/book")
    public @ResponseBody
    List<LibroDTO> getAllFilters() {
        final List<LibroDTO> libros = this.libroService.getAllBooks();
        return libros;
    }

    @GetMapping(path = "/book/{urlBook}")
    public @ResponseBody
    LibroDTO getLibro(@PathVariable("urlBook") String urlBook) {

        final LibroDTO libro = this.libroService.obtenerLibroConEntradas(urlBook);
        return libro;
    }

//    @RequestMapping(method = RequestMethod.POST, path = "/book/add")
//    ResponseEntity<SaveFilterResponse> guardarRedactor(@RequestBody final FilterDTO filterDTO) {
//        SaveFilterResponse response = new SaveFilterResponse();
//        try {
//            response = this.libroService.saveFilter(filterDTO);
//        } catch (Exception e) {
//            response.setErrorMessage(e);
//            return new ResponseEntity<SaveFilterResponse>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<SaveFilterResponse>(response, HttpStatus.OK);
//
//    }
//
//    @GetMapping(path = "/filter/genre/{urlGenre}")
//    public @ResponseBody
//    List<FilterDTO> getFilter(@PathVariable("urlGenre") List<String> urlGenre) {
//        final List<FilterDTO> filters = this.filterService.getFiltersAppliedByGenreUrl(urlGenre);
//        return filters;
//    }


}