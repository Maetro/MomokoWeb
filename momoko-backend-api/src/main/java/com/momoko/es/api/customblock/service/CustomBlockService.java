package com.momoko.es.api.customblock.service;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;

import java.util.List;

public interface CustomBlockService {

    CustomBlockDTO getCustomBlock(CustomBlockTypeEnum type, List<Integer> excludedBlocks) throws Exception;

    SaveCustomBlockResponse saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception;

}
