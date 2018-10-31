package com.momoko.es.jpa.author.adapter;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.jpa.author.entity.AuthorEntity;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class AuthorAdapter {


    private AuthorAdapter(){
    }

    /**
     * Adaptar authorEntities.
     *
     * @param authorEntities
     *            authorEntities
     * @return the establece
     */
    public static Set<AuthorDTO> adaptAuthorEntities(final Set<AuthorEntity> authorEntities, boolean adaptBooks) {
        final Set<AuthorDTO> authorDTOs = new HashSet<>();
        for (final AuthorEntity authorEntity : authorEntities) {
            authorDTOs.add(adaptAuthorEntity(authorEntity, adaptBooks));
        }
        return authorDTOs;
    }

    /**
     * Adaptar autor.
     *
     * @param authorEntity
     *            the autor entity
     * @return the autor dto
     */
    public static AuthorDTO adaptAuthorEntity(final AuthorEntity authorEntity, boolean adaptBooks) {
        AuthorDTO result = new AuthorDTO();
        result.setAuthorId(authorEntity.getAuthorId());
        result.setAuthorUrl(authorEntity.getAuthorUrl());
        if (adaptBooks) {
            result.setAuthorBooks(new HashSet<LibroDTO>(EntityToDTOAdapter.adaptarLibros(new ArrayList<>(authorEntity.getAuthorBooks()))));
        }
        result.setAuthorHeaderImage(authorEntity.getAuthorHeaderImage());
        result.setAvatar(authorEntity.getAvatar());
        result.setBirhtYear(authorEntity.getBirhtYear());
        result.setDeathYear(authorEntity.getDeathYear());
        result.setBirthCountry(authorEntity.getBirthCountry());
        result.setDescription(authorEntity.getDescription());
        result.setName(authorEntity.getName());
        result.setFacebook(authorEntity.getFacebook());
        result.setInstagram(authorEntity.getInstagram());
        result.setTwitter(authorEntity.getTwitter());
        result.setYoutube(authorEntity.getYoutube());
        result.setWebpage(authorEntity.getWebpage());
        return result;
    }


    public static AuthorEntity adaptAuthorDTO(AuthorDTO authorDTO, boolean adaptBooks) {
        AuthorEntity result = new AuthorEntity();
        result.setAuthorId(authorDTO.getAuthorId());
        result.setAuthorUrl(authorDTO.getAuthorUrl());
        if (adaptBooks) {
            result.setAuthorBooks(new HashSet<>(DTOToEntityAdapter.adaptarLibros(new ArrayList<>(authorDTO.getAuthorBooks()))));
        }
        result.setAuthorHeaderImage(authorDTO.getAuthorHeaderImage());
        result.setAvatar(authorDTO.getAvatar());
        result.setBirhtYear(authorDTO.getBirhtYear());
        result.setDeathYear(authorDTO.getDeathYear());
        result.setBirthCountry(authorDTO.getBirthCountry());
        result.setDescription(authorDTO.getDescription());
        result.setName(authorDTO.getName());
        result.setTwitter(authorDTO.getTwitter());
        result.setFacebook(authorDTO.getFacebook());
        result.setInstagram(authorDTO.getInstagram());
        result.setYoutube(authorDTO.getYoutube());
        result.setWebpage(authorDTO.getWebpage());
        return result;
    }
}
