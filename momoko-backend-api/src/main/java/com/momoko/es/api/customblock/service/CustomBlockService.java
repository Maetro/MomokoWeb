package com.momoko.es.api.customblock.service;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.NewCustomBlockInfoResponse;

import java.io.IOException;
import java.util.List;

public interface CustomBlockService {

    List<CustomBlockDTO> getAllCustomBlocks();

    CustomBlockDTO getCustomBlockById(Integer customBlockId) throws Exception;

    CustomBlockDTO getCustomBlock(CustomBlockTypeEnum type, List<Integer> excludedBlocks) throws Exception;

    CustomBlockDTO saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception;

    NewCustomBlockInfoResponse getCustomBlockInfo();

    List<CustomBlockDTO> getCustomBlockByType(String finalType, Integer finalNumberOfBlocks, List<Integer> finalExcludedList) throws IOException;
}
