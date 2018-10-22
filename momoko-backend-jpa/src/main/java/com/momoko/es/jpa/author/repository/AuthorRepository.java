/**
 * AutorRepository.java 08-jul-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.author.repository;

import com.momoko.es.jpa.author.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {

    List<AuthorEntity> findByName(String name);

    @Query("SELECT a.name FROM AuthorEntity a")
    List<String> findAllAuthorNames();

    AuthorEntity findOneByAuthorUrlIs(String urlAuthor);

    Set<AuthorEntity> findAll();

}
