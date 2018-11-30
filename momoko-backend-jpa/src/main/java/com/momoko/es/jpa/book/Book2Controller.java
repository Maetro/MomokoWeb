package com.momoko.es.jpa.book;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.response.GuardarLibroResponse;
import com.momoko.es.jpa.model.service.LibroService;
import com.momoko.es.jpa.model.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/model")
public class Book2Controller {

    @Autowired(required = false)
    private ValidadorService validadorService;

    @Autowired(required = false)
    private LibroService libroService;

    @GetMapping(path = "/book")
    public @ResponseBody
    List<LibroDTO> getAllBooks() {
        final List<LibroDTO> libros = this.libroService.getAllBooks();
        return libros;
    }

    @GetMapping(path = "/book/{urlBook}")
    public @ResponseBody
    LibroDTO getLibro(@PathVariable("urlBook") String urlBook) {

        final LibroDTO libro = this.libroService.getBookForModification(urlBook);
        return libro;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/book/add")
    ResponseEntity<GuardarLibroResponse> saveBook(@RequestBody final LibroDTO bookDTO) throws Exception {

        GuardarLibroResponse response = this.libroService.saveBook(bookDTO);
        return new ResponseEntity<GuardarLibroResponse>(response, HttpStatus.OK);

    }
//
//    @GetMapping(path = "/filter/genre/{urlGenre}")
//    public @ResponseBody
//    List<FilterDTO> getFilter(@PathVariable("urlGenre") List<String> urlGenre) {
//        final List<FilterDTO> filters = this.filterService.getFiltersAppliedByGenreUrl(urlGenre);
//        return filters;
//    }


}
