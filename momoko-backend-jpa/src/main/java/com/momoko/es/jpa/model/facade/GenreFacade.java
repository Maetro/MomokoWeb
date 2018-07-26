/**
 * GenreFacade.java 13-jul-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momoko.es.api.dto.genre.GenrePageResponse;
import com.momoko.es.api.enums.OrderType;
import com.momoko.es.jpa.model.service.GenreService;

@Controller
@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es", "https://momoko.es" })
@RequestMapping(path = "/public")
public class GenreFacade {

    @Autowired(required = false)
    private GenreService generoService;

    @GetMapping(path = "/genre/{genre-url}/{page-number}/{order-type}")
    public @ResponseBody GenrePageResponse obtenerGeneroPaginaPorNota(@PathVariable("genre-url") final String genreUrl,
            @PathVariable("page-number") final Integer pageNumber, @PathVariable("order-type") final String orderType) {

        return this.generoService.getGenrePage(genreUrl, pageNumber - 1, OrderType.getOrderTypeByValue(orderType));

    }

}
