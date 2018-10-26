package com.momoko.es.jpa.model.repository;

import com.momoko.es.jpa.model.entity.HtmlCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlCodeRepository extends CrudRepository<HtmlCodeEntity, Integer> {

    HtmlCodeEntity findOneByUrlCode(String urlCodigo);
}
