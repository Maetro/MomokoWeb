package com.momoko.es.jpa.customblock.controller;

import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;
import com.momoko.es.api.customblock.service.CustomBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4000", "https://www.momoko.es", "https://momoko.es", "http://admin.momoko.es"})
@RequestMapping(path = "/model")
public class CustomBlockController {

    final
    CustomBlockService customBlockService;

    @Autowired
    public CustomBlockController(CustomBlockService customBlockService) {
        this.customBlockService = customBlockService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/customBlock")
    public ResponseEntity<SaveCustomBlockResponse> saveCustomBlock(@RequestBody final SaveCustomBlockRequest saveCustomBlockRequest){
        SaveCustomBlockResponse saveCustomBlockResponse = null;
        try {
            saveCustomBlockResponse = this.customBlockService.saveCustomBlock(saveCustomBlockRequest);
        } catch (Exception e) {
            return new ResponseEntity<SaveCustomBlockResponse>(saveCustomBlockResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SaveCustomBlockResponse>(saveCustomBlockResponse, HttpStatus.OK);
    }

}
