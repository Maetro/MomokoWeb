package com.momoko.es.jpa.customblock.repository;

import com.momoko.es.api.customblock.dtos.CustomBlockDTO;
import com.momoko.es.api.customblock.dtos.enums.CustomBlockTypeEnum;
import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomBlockRepository extends CrudRepository<CustomBlockEntity, Integer> {

    @Override
    public List<CustomBlockEntity> findAll();

    CustomBlockEntity findOneByTypeIsAndIdIsNotInAndActiveIsTrue(CustomBlockTypeEnum type, List<Integer> excludedIds);
}
