package com.momoko.es.jpa.customblock.repository;

import com.momoko.es.jpa.customblock.entity.CustomBlockEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomBlockRepository extends CrudRepository<CustomBlockEntity, Integer> {

    @Override
    public List<CustomBlockEntity> findAll();
}
