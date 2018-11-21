package com.momoko.es.jpa.customblock.service;

import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;
import com.momoko.es.api.customblock.service.CustomBlockService;
import com.momoko.es.jpa.customblock.repository.CustomBlockRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomBlockServiceImpl implements CustomBlockService {

    CustomBlockRepository customBlockRepository;

    @Override
    public SaveCustomBlockResponse saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception {
        SaveCustomBlockResponse saveCustomBlockResponse = new SaveCustomBlockResponse();
        this.customBlockRepository.findAll();
        return null;
    }
}
