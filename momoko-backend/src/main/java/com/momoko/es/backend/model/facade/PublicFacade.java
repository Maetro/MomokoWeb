/**
 * PublicFacade.java 26-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.facade;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.ComentarioDTO;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.IndexDataDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.request.NuevoComentarioRequest;
import com.momoko.es.api.dto.response.GuardarComentarioResponse;
import com.momoko.es.api.dto.response.ObtenerEntradaResponse;
import com.momoko.es.api.dto.response.ObtenerFichaLibroResponse;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.ErrorCreacionComentario;
import com.momoko.es.backend.model.service.ComentarioService;
import com.momoko.es.backend.model.service.EntradaService;
import com.momoko.es.backend.model.service.IndexService;
import com.momoko.es.backend.model.service.LibroService;
import com.momoko.es.backend.model.service.ValidadorService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/public")
public class PublicFacade {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private LibroService libroService;

    @Autowired(required = false)
    private ComentarioService comentarioService;

    @Autowired
    private ValidadorService validadorService;

    @GetMapping(path = "/indexData")
    public @ResponseBody IndexDataDTO getInfoIndex() {
        System.out.println("Llamada a los datos para dibujar el index");
        final List<EntradaSimpleDTO> ultimasEntradas = this.indexService.obtenerUltimasEntradas();
        final List<LibroSimpleDTO> librosMasVistos = this.indexService.obtenerLibrosMasVistos();
        final IndexDataDTO indexDataDTO = new IndexDataDTO();
        indexDataDTO.setUltimasEntradas(ultimasEntradas);
        indexDataDTO.setLibrosMasVistos(librosMasVistos);
        return indexDataDTO;
    }

    @GetMapping(path = "/entradas")
    public @ResponseBody List<EntradaDTO> getAllEntradas() {
        System.out.println("LLamada a la lista de entradas");
        return this.entradaService.recuperarEntradas();
    }

    @GetMapping(path = "/entrada/{url-entrada}")
    public @ResponseBody ObtenerEntradaResponse getEntradaByUrl(@PathVariable("url-entrada") final String urlEntrada) {
        System.out.println("Obtener entrada: " + urlEntrada);
        final ObtenerEntradaResponse respuesta = this.entradaService.obtenerEntrada(urlEntrada);
        return respuesta;
    }

    @GetMapping(path = "/libro/{url-libro}")
    public @ResponseBody ObtenerFichaLibroResponse obtenerLibro(@PathVariable("url-libro") final String urlLibro) {
        System.out.println("Obtener libro: " + urlLibro);
        final ObtenerFichaLibroResponse respuesta = this.libroService.obtenerLibro(urlLibro);
        respuesta.setCincoLibrosParecidos(this.libroService.obtenerLibrosParecidos(respuesta.getLibro(), 5));
        return respuesta;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/comentario/add")
    ResponseEntity<GuardarComentarioResponse> addComentario(@RequestBody final NuevoComentarioRequest comentario) {

        // Validar
        final List<ErrorCreacionComentario> listaErrores = this.validadorService.validarComentario(comentario);

        // Guardar
        ComentarioDTO comentarioDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                comentarioDTO = this.comentarioService.guardarComentario(comentario);
            } catch (final Exception e) {
                listaErrores.add(ErrorCreacionComentario.ERROR_EN_GUARDADO);
            }
        }

        // Responder
        final GuardarComentarioResponse respuesta = new GuardarComentarioResponse();
        respuesta.setComentario(comentarioDTO);
        respuesta.setListaErroresValidacion(listaErrores);

        if ((comentarioDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            respuesta.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }

        return new ResponseEntity<GuardarComentarioResponse>(respuesta, HttpStatus.OK);

    }

    // TODO: BORRAME

    @RequestMapping(method = RequestMethod.GET, path = "/limpiarImagenesLibros")
    void limpiarImagenesLibrosYEntradas() throws Exception {
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        for (final LibroDTO libroDTO : libros) {
            if (!StringUtils.isBlank(libroDTO.getUrlImagen())) {
                final String urlImagen = aPortadas(libroDTO.getUrlImagen());
                libroDTO.setUrlImagen(urlImagen);
                this.libroService.guardarLibro(libroDTO);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/limpiarImagenesEntradas")
    void limpiarImagenesEntradas() throws Exception {
        final List<EntradaDTO> entradas = this.entradaService.recuperarEntradas();
        for (final EntradaDTO entrada : entradas) {
            if (!StringUtils.isBlank(entrada.getImagenDestacada())) {
                final String urlImagen = soloNombreImagen(entrada.getImagenDestacada());
                entrada.setImagenDestacada(urlImagen);
                this.entradaService.guardarEntrada(entrada);
            }
        }
    }

    private String soloNombreImagen(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return lista[elementos - 2] + "/" + lista[elementos - 1];
    }

    private String aPortadas(final String urlImagen) {
        final String[] lista = urlImagen.split("/");
        final int elementos = lista.length;
        return "portadas" + "/" + lista[elementos - 1];
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generarURLsLibros")
    void generarURLsLibros() throws Exception {
        final List<LibroDTO> libros = this.libroService.recuperarLibros();
        for (final LibroDTO libroDTO : libros) {
            if (StringUtils.isBlank(libroDTO.getUrlLibro())) {
                final String urlLibro = toSlug(libroDTO.getTitulo());
                libroDTO.setUrlLibro(urlLibro);
                this.libroService.guardarLibro(libroDTO);
            }
        }
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(final String input) {
        final String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        final String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        final String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
