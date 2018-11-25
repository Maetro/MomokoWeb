package com.momoko.es.jpa.customblock.adapter;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;

import java.util.ArrayList;
import java.util.List;

public final class CustomBlockAdapter {

    private CustomBlockAdapter() {
    }

    public static List<CustomBlockDTO> adaptCustomBlockEntityListToCustomBlockDTOList(List<CustomBlockEntity> customBlockEntityList){
        List<CustomBlockDTO> result = new ArrayList<>();

       return result;
    }

    public static CustomBlockDTO adaptCustomBlockEntityToCustomBlockDTO(CustomBlockEntity customBlockEntity){
        CustomBlockDTO customBlockDTO = new CustomBlockDTO();


        return customBlockDTO;
    }


}
