package com.momoko.es.jpa.customblock.controller;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.NewCustomBlockInfoResponse;
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

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(method = RequestMethod.GET, path = "/customBlock")
    public ResponseEntity<List<CustomBlockDTO>> getAllCustomBlocks(){
        List<CustomBlockDTO> customBlocks = new ArrayList<>();
        try {
            customBlocks = this.customBlockService.getAllCustomBlocks();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(customBlocks, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customBlocks, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/customBlock/info")
    public ResponseEntity<NewCustomBlockInfoResponse> getNewCustomBlockInfoResponse(){
        NewCustomBlockInfoResponse newCustomBlockInfoResponse = new NewCustomBlockInfoResponse();
        try {
            newCustomBlockInfoResponse = this.customBlockService.getCustomBlockInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<NewCustomBlockInfoResponse>(newCustomBlockInfoResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NewCustomBlockInfoResponse>(newCustomBlockInfoResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/customBlock")
    public ResponseEntity<CustomBlockDTO> saveCustomBlock(@RequestBody final SaveCustomBlockRequest saveCustomBlockRequest){
        CustomBlockDTO saveCustomBlockResponse = null;
        try {
            saveCustomBlockResponse = this.customBlockService.saveCustomBlock(saveCustomBlockRequest);
        } catch (Exception e) {
            return new ResponseEntity<CustomBlockDTO>(saveCustomBlockResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CustomBlockDTO>(saveCustomBlockResponse, HttpStatus.OK);
    }

}
