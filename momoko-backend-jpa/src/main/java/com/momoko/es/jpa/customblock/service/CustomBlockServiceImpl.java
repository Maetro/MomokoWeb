package com.momoko.es.jpa.customblock.service;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.LabelUrlTypeDTO;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.api.customblock.dtos.request.SaveCustomBlockRequest;
import com.momoko.es.api.customblock.dtos.response.NewCustomBlockInfoResponse;
import com.momoko.es.api.customblock.dtos.response.SaveCustomBlockResponse;
import com.momoko.es.api.customblock.service.CustomBlockService;
import com.momoko.es.api.entry.dto.EntradaSimpleDTO;
import com.momoko.es.api.dto.LibroEntradaSimpleDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import com.momoko.es.jpa.customblock.mapper.CustomBlockMapper;
import com.momoko.es.jpa.customblock.mapper.SaveCustomBlockMapper;
import com.momoko.es.jpa.customblock.repository.CustomBlockRepository;
import com.momoko.es.jpa.entry.entity.EntradaEntity;
import com.momoko.es.jpa.book.LibroEntity;
import com.momoko.es.jpa.entry.repository.EntradaRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.MomokoUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomBlockServiceImpl implements CustomBlockService {

    private final CustomBlockRepository customBlockRepository;

    private final EntradaRepository entradaRepository;

    private final LibroRepository libroRepository;

    private final StorageService storageService;

    @Autowired
    public CustomBlockServiceImpl(CustomBlockRepository customBlockRepository, EntradaRepository entradaRepository,
                                  LibroRepository libroRepository, StorageService storageService) {
        this.customBlockRepository = customBlockRepository;
        this.entradaRepository = entradaRepository;
        this.libroRepository = libroRepository;
        this.storageService = storageService;
    }

    @Override
    public List<CustomBlockDTO> getAllCustomBlocks() {
        List<CustomBlockDTO> customBlockList = new ArrayList<>();
        List<CustomBlockEntity> entityList = this.customBlockRepository.findAll();
        return CustomBlockMapper.instance.customBlockEntityListToCustomBlockDTOList(entityList);
    }

    @Override
    public CustomBlockDTO getCustomBlockById(Integer customBlockId) throws Exception {
        return null;
    }

    @Override
    public CustomBlockDTO getCustomBlock(CustomBlockTypeEnum type, List<Integer> excludedBlocks) throws Exception {
        CustomBlockDTO response = null;
        CustomBlockEntity customBlockEntity = customBlockRepository.findOneByTypeIsAndIdIsNotInAndActiveIsTrue(type, excludedBlocks);

        return response;
    }
    
    @Override
    public CustomBlockDTO saveCustomBlock(SaveCustomBlockRequest saveCustomBlockRequest) throws Exception {
        SaveCustomBlockResponse saveCustomBlockResponse = new SaveCustomBlockResponse();
        removeServerFromImageData(saveCustomBlockRequest);
        CustomBlockEntity customBlockEntity = SaveCustomBlockMapper.instance.saveCustomBlockRequestToCustomBlockEntity(saveCustomBlockRequest);
        customBlockEntity = this.customBlockRepository.save(customBlockEntity);
        return CustomBlockMapper.instance.customBlockEntityToCustomBlockDTO(customBlockEntity);
    }

    private void removeServerFromImageData(SaveCustomBlockRequest saveCustomBlockRequest) {
        if (saveCustomBlockRequest.getCustomBlockMainImage() != null) {
            saveCustomBlockRequest.setCustomBlockMainImage(MomokoUtils.soloNombreYCarpeta(saveCustomBlockRequest.getCustomBlockMainImage()));
        }
    }

    @Override
    @Cacheable("customBlockInfo")
    public NewCustomBlockInfoResponse getCustomBlockInfo() {
        NewCustomBlockInfoResponse newCustomBlockInfoResponse = new NewCustomBlockInfoResponse();
        List<LabelUrlTypeDTO> booksAndEntries = new ArrayList<>();
        LabelUrlTypeDTO emptyLabelUrlTypeDTO = new LabelUrlTypeDTO();
        emptyLabelUrlTypeDTO.setLabel("");
        emptyLabelUrlTypeDTO.setType("");
        emptyLabelUrlTypeDTO.setUrl("");
        booksAndEntries.add(emptyLabelUrlTypeDTO);
        List<EntradaEntity> entradas = this.entradaRepository.findAll();
        for (EntradaEntity entrada : entradas) {
            LabelUrlTypeDTO labelUrlTypeDTO = new LabelUrlTypeDTO();
            labelUrlTypeDTO.setLabel(entrada.getTituloEntrada());
            labelUrlTypeDTO.setType("Entry");
            labelUrlTypeDTO.setUrl(entrada.getUrlEntrada());
            booksAndEntries.add(labelUrlTypeDTO);
        }
        List<LibroEntity> books = this.libroRepository.findAll();
        for (LibroEntity bookEntity : books) {
            LabelUrlTypeDTO labelUrlTypeDTO = new LabelUrlTypeDTO();
            labelUrlTypeDTO.setLabel(bookEntity.getTitulo());
            labelUrlTypeDTO.setType("Book");
            labelUrlTypeDTO.setUrl(bookEntity.getUrlLibro());
            booksAndEntries.add(labelUrlTypeDTO);
        }

        newCustomBlockInfoResponse.setBooksAndEntries(booksAndEntries);
        return newCustomBlockInfoResponse;
    }

    @Override
    public List<CustomBlockDTO> getCustomBlockByType(String finalType, Integer finalNumberOfBlocks, List<Integer> finalExcludedList) throws IOException {

        List<CustomBlockEntity> customBlockEntities = customBlockRepository.findByTypeIsAndIdIsNotInAndActiveIsTrue(
                CustomBlockTypeEnum.valueOf(finalType), finalExcludedList, PageRequest.of(0, finalNumberOfBlocks));
        List<CustomBlockDTO> result = CustomBlockMapper.instance.customBlockEntityListToCustomBlockDTOList(customBlockEntities);
        if (CollectionUtils.isNotEmpty(result)) {
            for (CustomBlockDTO customBlockDTO : result) {
                customBlockDTO.setCustomBlockMainImage(this.storageService.obtenerMiniatura(customBlockDTO.getCustomBlockMainImage(), 774, 515, true));
                if (CollectionUtils.isNotEmpty(customBlockDTO.getLinks())) {
                    List<LibroEntradaSimpleDTO> libroEntradaSimpleDTOS = new ArrayList<>();
                    for (LibroEntradaSimpleDTO link : customBlockDTO.getLinks()) {
                        LibroEntradaSimpleDTO libroEntradaSimpleDTO = new LibroEntradaSimpleDTO();
                        if (link.getEntrada() != null) {
                            EntradaEntity entrada = this.entradaRepository.findFirstByUrlEntrada(link.getEntrada().getUrlEntrada().trim());
                            EntradaSimpleDTO entradaSimple = ConversionUtils.obtenerEntradaSimpleDTO(entrada, false);
                            entradaSimple.setImagenEntrada(
                                    this.storageService.obtenerMiniatura(entradaSimple.getImagenEntrada(), 170, 170, true));
                            libroEntradaSimpleDTO.setEntrada(entradaSimple);
                            libroEntradaSimpleDTOS.add(libroEntradaSimpleDTO);
                        }
                        if (link.getLibro() != null) {
                            LibroEntity libroEntity = this.libroRepository.findOneByUrlLibro(link.getLibro().getUrlLibro().trim());
                            LibroSimpleDTO libroSimple = ConversionUtils.obtenerLibroSimpleDTO(libroEntity, null);
                            libroSimple.setPortada(
                                    this.storageService.obtenerMiniatura(libroSimple.getPortada(), 170, 170, false));
                            libroEntradaSimpleDTO.setLibro(libroSimple);
                            libroEntradaSimpleDTOS.add(libroEntradaSimpleDTO);
                        }
                    }
                    customBlockDTO.setLinks(libroEntradaSimpleDTOS);
                }
            }
        }
        return result;
    }
}
