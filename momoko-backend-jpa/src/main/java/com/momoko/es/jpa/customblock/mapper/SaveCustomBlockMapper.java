package com.momoko.es.jpa.customblock.mapper;

import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import com.momoko.es.jpa.model.util.ConversionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SaveCustomBlockMapper {

    SaveCustomBlockMapper instance = Mappers.getMapper( SaveCustomBlockMapper.class );

    @Mapping(source="links", target = "links", qualifiedByName = "linksTransformation")
    CustomBlockEntity saveCustomBlockRequestToCustomBlockEntity(SaveCustomBlockRequest entity);

    List<CustomBlockEntity> saveCustomBlockRequestListToCustomBlockEntityList(List<SaveCustomBlockRequest> customBlocks);

    @Named("linksTransformation")// or your custom @Qualifier annotation
    default String linksTransformation(List<String> obj) {
        return ConversionUtils.join(obj);
    }


}
