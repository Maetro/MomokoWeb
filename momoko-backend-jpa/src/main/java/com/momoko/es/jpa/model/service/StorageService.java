package com.momoko.es.jpa.model.service;

import com.momoko.es.api.dto.AnchuraAlturaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface StorageService {

    String store(MultipartFile file, String tipoAlmacenamineto);

    String store(BufferedImage image, String tipoAlmacenamineto, String name, String extension);

    String obtenerMiniatura(String urlImagen, Integer width, Integer height, boolean recortar) throws IOException;

    String obtenerMiniatura(String tipoAlmacenamiento, String filename, Integer width, Integer height, boolean recortar)
            throws IOException;

    AnchuraAlturaDTO getImageDimensions(String filename, String tipoAlmacenamiento) throws IOException;

    AnchuraAlturaDTO getImageDimensions(String urlImagen) throws IOException;

    AnchuraAlturaDTO getImageDimensionsThumbnail(String urlImagen) throws IOException;;

    String getUrlImageServer();

    String obtenerImagenOriginal(String imagenDestacada);

    boolean crearCarpetaSiNoExiste(String carpeta);

    String getImageFolder();

    String getUrlSitemap();

    String getTemplateFolder();

    String getUrlUpload();

    String getBackendServer();
}
