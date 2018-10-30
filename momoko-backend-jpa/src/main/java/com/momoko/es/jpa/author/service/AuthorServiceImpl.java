package com.momoko.es.jpa.author.service;

import com.momoko.es.api.author.dto.AuthorDTO;
import com.momoko.es.api.author.enums.AuthorCreationError;
import com.momoko.es.api.author.response.AuthorPageResponse;
import com.momoko.es.api.author.response.SaveAuthorResponse;
import com.momoko.es.api.author.service.AuthorService;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.LibroSimpleDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.commons.security.UserAdminPermission;
import com.momoko.es.exceptions.EntityUrlUsedException;
import com.momoko.es.jpa.author.adapter.AuthorAdapter;
import com.momoko.es.jpa.author.entity.AuthorEntity;
import com.momoko.es.jpa.author.repository.AuthorRepository;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.PuntuacionEntity;
import com.momoko.es.jpa.model.repository.PuntuacionRepository;
import com.momoko.es.jpa.model.service.StorageService;
import com.momoko.es.jpa.model.service.ValidadorService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.MomokoUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    private final ValidadorService validatorService;

    private final PuntuacionRepository puntuacionRepository;

    private final StorageService storageService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ValidadorService validatorService,
                             PuntuacionRepository puntuacionRepository, StorageService storageService) {
        this.authorRepository = authorRepository;
        this.validatorService = validatorService;
        this.puntuacionRepository = puntuacionRepository;
        this.storageService = storageService;
    }

    @Override
    public Set<AuthorDTO> getAllAuthors() {
        Set<AuthorEntity> authorEntities = this.authorRepository.findAll();
        return AuthorAdapter.adaptAuthorEntities(authorEntities, false);
    }

    @Override
    public List<String> getAllAuthorNames() {
        return this.authorRepository.findAllAuthorNames();
    }

    @Override
    public AuthorDTO getAuthorByUrl(String urlAuthor) {
        AuthorDTO authorDTO = AuthorAdapter.adaptAuthorEntity(this.authorRepository.findOneByAuthorUrlIs(urlAuthor), false);
        final String imageServer = this.storageService.getUrlImageServer();
        if (authorDTO.getAvatar() != null) {
            authorDTO.setAvatar(imageServer + authorDTO.getAvatar());
        }
        if (authorDTO.getAuthorHeaderImage() != null) {
            authorDTO.setAuthorHeaderImage(imageServer + authorDTO.getAuthorHeaderImage());
        }
        return authorDTO;
    }

    @Override
    public Set<AuthorDTO> getOrSaveBookAuthorByName(final LibroDTO bookDTO) {
        final Set<AuthorDTO> bookAuthors = new HashSet<>();
        if (CollectionUtils.isNotEmpty(bookDTO.getAutores())) {
            for (final AuthorDTO author : bookDTO.getAutores()) {

                final List<AuthorEntity> authorFoundByName = this.authorRepository.findByName(author.getName());
                AuthorEntity authorFound = null;
                if (CollectionUtils.isEmpty(authorFoundByName)) {
                    final AuthorEntity newAuthor = new AuthorEntity();
                    newAuthor.setName(author.getName());
                    newAuthor.setAuthorUrl(ConversionUtils.toSlug(author.getName()));
                    authorFound = this.authorRepository.save(newAuthor);

                } else {
                    authorFound = authorFoundByName.get(0);
                }
                bookAuthors.add(AuthorAdapter.adaptAuthorEntity(authorFound, false));
            }
        }
        return bookAuthors;
    }

    @UserAdminPermission
    @Override
    public SaveAuthorResponse saveAuthor(AuthorDTO authorDTO) {
        SaveAuthorResponse response = new SaveAuthorResponse();
        // Validar
        final List<AuthorCreationError> listaErrores = this.validatorService.validateAuthor(authorDTO);

        // Guardar
        AuthorDTO savedAuthorDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            removeServerFromImageData(authorDTO);
            try {
                if (authorDTO.getAuthorId() == null) {
                    savedAuthorDTO = saveNewAuthor(authorDTO);
                } else {
                    savedAuthorDTO = updateAuthor(authorDTO);
                }
            } catch (final Exception e) {
                log.error("saveAuthor", e);
                listaErrores.add(AuthorCreationError.UNKNOWN);
                response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
            }
        }

        // Responder
        response.setAuthorDTO(savedAuthorDTO);
        response.setListaErroresValidacion(listaErrores);

        if ((savedAuthorDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            response.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }
        return response;
    }

    private AuthorDTO saveNewAuthor(AuthorDTO authorDTO) throws EntityUrlUsedException {
        AuthorEntity foundCollision = this.authorRepository.findOneByAuthorUrlIs(authorDTO.getAuthorUrl());
        if (foundCollision != null) {
            throw new EntityUrlUsedException();
        }
        AuthorEntity authorToSave = AuthorAdapter.adaptAuthorDTO(authorDTO, false);
        AuthorEntity authorSaved = this.authorRepository.save(authorToSave);
        return AuthorAdapter.adaptAuthorEntity(authorSaved, false);
    }

    private void removeServerFromImageData(AuthorDTO authorDTO) {
        if (authorDTO.getAvatar() != null) {
            authorDTO.setAvatar(MomokoUtils.soloNombreYCarpeta(authorDTO.getAvatar()));
        }
        if (authorDTO.getAuthorHeaderImage() != null) {
            authorDTO.setAuthorHeaderImage(MomokoUtils.soloNombreYCarpeta(authorDTO.getAuthorHeaderImage()));
        }
    }


    private AuthorDTO updateAuthor(AuthorDTO authorDTO) throws InstanceNotFoundException {
        AuthorEntity authorToUpdate = this.authorRepository.findById(authorDTO.getAuthorId()).orElseThrow(() ->
                new InstanceNotFoundException("author id: " + authorDTO.getAuthorId()));

        authorToUpdate.setAuthorUrl(authorDTO.getAuthorUrl());
        authorToUpdate.setAuthorHeaderImage(authorDTO.getAuthorHeaderImage());
        authorToUpdate.setAvatar(authorDTO.getAvatar());
        authorToUpdate.setBirhtYear(authorDTO.getBirhtYear());
        authorToUpdate.setDeathYear(authorDTO.getDeathYear());
        authorToUpdate.setBirthCountry(authorDTO.getBirthCountry());
        authorToUpdate.setDescription(authorDTO.getDescription());
        authorToUpdate.setName(authorDTO.getName());
        AuthorEntity authorUpdated = this.authorRepository.save(authorToUpdate);
        return AuthorAdapter.adaptAuthorEntity(authorUpdated, false);
    }


    @Override
    public AuthorDTO getAuthorById(Integer authorId) throws InstanceNotFoundException {
        return AuthorAdapter.adaptAuthorEntity(this.authorRepository.findById(authorId).orElseThrow(() -> new InstanceNotFoundException("Author id: " + authorId)), false);
    }

    @Override
    public AuthorPageResponse getAuthorAndAuthorBooksByUrl(String urlAuthor) {
        AuthorPageResponse response = new AuthorPageResponse();
        AuthorEntity author = this.authorRepository.findOneByAuthorUrlIs(urlAuthor);

        final String imageServer = this.storageService.getUrlImageServer();

        AuthorDTO authorDTO = AuthorAdapter.adaptAuthorEntity(author, false);

        if (author.getAvatar() != null) {
            try {
                authorDTO.setAvatar(
                        this.storageService.obtenerMiniatura(authorDTO.getAvatar(), 170, 170, true));
            } catch (final IOException e) {
                authorDTO.setAvatar("https://momoko.es/images/fijas/author_avatar_empty.jpg");
            }
        } else {
            authorDTO.setAvatar("https://momoko.es/images/fijas/author_avatar_empty.jpg");
        }
        if (authorDTO.getAuthorHeaderImage() != null) {
            authorDTO.setAuthorHeaderImage(imageServer + authorDTO.getAuthorHeaderImage());
        } else {
            authorDTO.setAuthorHeaderImage("/assets/style/images/art/parallax2.jpg");
        }

        response.setAuthor(authorDTO);
        Set<LibroEntity> authorBooks = author.getAuthorBooks();

        List<Integer> bookIdsList = authorBooks.stream().map(LibroEntity::getLibroId).collect(Collectors.toList());

        final List<PuntuacionEntity> scoreList = this.puntuacionRepository
                .findByEsPuntuacionMomokoAndLibroLibroIdIn(true, bookIdsList);
        final Map<LibroEntity, PuntuacionEntity> bookScoreByBook = new HashMap<>();
        if (CollectionUtils.isNotEmpty(authorBooks)) {
            for (final PuntuacionEntity scoreEntity : scoreList) {
                bookScoreByBook.put(scoreEntity.getLibro(), scoreEntity);
            }

        }
        final List<LibroSimpleDTO> obtenerLibrosBasicos = ConversionUtils.obtenerLibrosBasicos(
                new ArrayList<>(authorBooks), bookScoreByBook);

        for (final LibroSimpleDTO libroSimpleDTO : obtenerLibrosBasicos) {
            if (libroSimpleDTO.getPortada() != null) {
                try {
                    libroSimpleDTO.setPortada(
                            this.storageService.obtenerMiniatura(libroSimpleDTO.getPortada(), 240, 350, true));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setAuthorBooks(obtenerLibrosBasicos);
        return response;
    }

}
