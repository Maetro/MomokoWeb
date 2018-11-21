package com.momoko.es.api.customblock.dtos.request;

import com.momoko.es.api.dto.LibroEntradaSimpleDTO;

import java.io.Serializable;
import java.util.List;

public class SaveCustomBlockRequest implements Serializable {


    private String blockContent;
    private String blockMainImage;
    private List<LibroEntradaSimpleDTO> blockLinks;

    public String getBlockContent() {
        return blockContent;
    }

    public void setBlockContent(String blockContent) {
        this.blockContent = blockContent;
    }

    public String getBlockMainImage() {
        return blockMainImage;
    }

    public void setBlockMainImage(String blockMainImage) {
        this.blockMainImage = blockMainImage;
    }

    public List<LibroEntradaSimpleDTO> getBlockLinks() {
        return blockLinks;
    }

    public void setBlockLinks(List<LibroEntradaSimpleDTO> blockLinks) {
        this.blockLinks = blockLinks;
    }
}
