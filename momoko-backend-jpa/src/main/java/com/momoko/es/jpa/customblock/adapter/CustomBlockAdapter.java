package com.momoko.es.jpa.customblock.adapter;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;

public final class CustomBlockAdapter {

    private CustomBlockAdapter() {
    }

    public static CustomBlockDTO adaptCustomBlockEntityToCustomBlockDTO(CustomBlockEntity customBlockEntity){
        CustomBlockDTO customBlockDTO = new CustomBlockDTO();


        return customBlockDTO;
    }


}
