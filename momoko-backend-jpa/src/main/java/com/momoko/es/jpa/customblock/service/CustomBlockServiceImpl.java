package com.momoko.es.jpa.customblock.service;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;
import com.momoko.es.api.customblock.service.CustomBlockService;
import com.momoko.es.jpa.customblock.adapter.CustomBlockAdapter;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import com.momoko.es.jpa.customblock.repository.CustomBlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomBlockServiceImpl implements CustomBlockService {

    CustomBlockRepository customBlockRepository;

    @Override
    public CustomBlockDTO getCustomBlock(CustomBlockTypeEnum type, List<Integer> excludedBlocks) throws Exception {
        CustomBlockDTO response = null;
        CustomBlockEntity customBlockEntity = customBlockRepository.findOneByTypeIsAndIdIsNotInAndActiveIsTrue(type, excludedBlocks);

        return response;
    }
    
    @Override
    public SaveCustomBlockResponse saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception {
        SaveCustomBlockResponse saveCustomBlockResponse = new SaveCustomBlockResponse();
        this.customBlockRepository.findAll();
        return null;
    }
}
