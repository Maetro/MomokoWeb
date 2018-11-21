package com.momoko.es.api.customblock.service;

import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;

public interface CustomBlockService {

    SaveCustomBlockResponse saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception;

}
