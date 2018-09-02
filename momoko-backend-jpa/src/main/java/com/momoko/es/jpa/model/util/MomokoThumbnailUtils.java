package com.momoko.es.jpa.model.util;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.jpa.model.service.StorageService;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MomokoThumbnailUtils {

    public static LibroDTO tratarImagenesFichaLibro(StorageService almacenImagenes, LibroDTO libroDTO) {

        try {
            final AnchuraAlturaDTO alturaAnchura = almacenImagenes.getImageDimensions(libroDTO.getUrlImagen());
            libroDTO.setPortadaHeight(alturaAnchura.getAltura());
            libroDTO.setPortadaWidth(alturaAnchura.getAnchura());
            final String url = almacenImagenes.getUrlImageServer();
            libroDTO.setUrlImagen(url + libroDTO.getUrlImagen());
            final Set<GenreDTO> generosImagenes = new HashSet<>();
            for (final GenreDTO generoDTO : libroDTO.getGeneros()) {
                generoDTO.setImagenCabeceraGenero(url + generoDTO.getImagenCabeceraGenero());
                generosImagenes.add(generoDTO);
            }
            libroDTO.setGeneros(generosImagenes);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return libroDTO;
    }

    public static List<LibroSimpleDTO> tratarImagenesFichaLibro(StorageService almacenImagenes, List<LibroSimpleDTO> librosDTO) {
        List<LibroSimpleDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(librosDTO)){
            for (LibroSimpleDTO libroSimpleDTO : librosDTO) {
                libroSimpleDTO = tratarImagenesFichaLibro(almacenImagenes,libroSimpleDTO);
                result.add(libroSimpleDTO);
            }
        }
        return result;
    }

    public static LibroSimpleDTO tratarImagenesFichaLibro(StorageService almacenImagenes, LibroSimpleDTO libroDTO) {

        try {
            final AnchuraAlturaDTO alturaAnchura = almacenImagenes.getImageDimensions(libroDTO.getPortada());
            libroDTO.setPortadaHeight(alturaAnchura.getAltura());
            libroDTO.setPortadaWidth(alturaAnchura.getAnchura());
            final String url = almacenImagenes.getUrlImageServer();
            libroDTO.setPortada(url + libroDTO.getPortada());

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return libroDTO;
    }

}
