package com.momoko.es.jpa.customblock.mapper;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomBlockMapper {

    CustomBlockMapper instance = Mappers.getMapper( CustomBlockMapper.class );

    @Mapping(source="links", target = "links", qualifiedByName = "myTransformation")
    CustomBlockDTO customBlockEntityToCustomBlockDTO(CustomBlockEntity customBlockDTO);

    List<CustomBlockDTO> customBlockEntityListToCustomBlockDTOList(List<CustomBlockEntity> cars);

    @Named("myTransformation")
    default List<LibroEntradaSimpleDTO> linksTransformation(String linksString) {
        List<LibroEntradaSimpleDTO> list = new ArrayList<>();
        String[] links = linksString.split(";");
        for (String s : links) {
            LibroEntradaSimpleDTO libroEntradaSimpleDTO = new LibroEntradaSimpleDTO();
            String type = s.substring(0, s.indexOf(" "));
            String url = s.substring(s.indexOf(" "));
            if (type.equals("Book")){
                LibroSimpleDTO book = new LibroSimpleDTO();
                book.setUrlLibro(url);
                libroEntradaSimpleDTO.setLibro(book);
            } else if (type.equals("Entry")){
                EntradaSimpleDTO entry  = new EntradaSimpleDTO();
                entry.setUrlEntrada(url);
                libroEntradaSimpleDTO.setEntrada(entry);
            }
            list.add(libroEntradaSimpleDTO);
        }

        return list;
    }

}
