package com.momoko.es.jpa.entry.mapper;

import com.momoko.es.api.entry.dto.EntradaDTO;
import com.momoko.es.api.entry.request.SaveEntryRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaveEntryMapper {

    SaveEntryMapper instance = Mappers.getMapper( SaveEntryMapper.class );

    EntradaDTO saveEntryRequestDTOToEntradaDTO(SaveEntryRequestDTO entity);

}
