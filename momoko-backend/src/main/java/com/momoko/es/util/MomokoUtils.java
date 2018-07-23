/**
 * MomokoUtils.java 23-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.api.dto.AutorDTO;
import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.exceptions.security.VersionException;
import com.momoko.es.backend.security.common.util.LecUtils;
import com.momoko.es.backend.model.entity.security.AbstractUser;
import com.momoko.es.backend.model.entity.security.VersionedEntity;
import com.momoko.es.backend.security.JwtService;
import com.momoko.es.backend.security.MomokoPrincipal;
import com.momoko.es.backend.security.UserDto;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class MomokoUtils {

    public static final String ERROR = "ERROR";
    private static final Log log = LogFactory.getLog(MomokoUtils.class);

    private static ApplicationContext applicationContext;
    private static ObjectMapper objectMapper;

    public MomokoUtils(ApplicationContext applicationContext,
                      ObjectMapper objectMapper) {

        MomokoUtils.applicationContext = applicationContext;
        MomokoUtils.objectMapper = objectMapper;

        log.info("Created MomokoUtils");
    }




    /**
     * Solo nombre y carpeta.
     *
     * @param imagenDestacada
     *            the imagen destacada
     * @return the string
     */
    public static String soloNombreYCarpeta(final String imagenDestacada) {
        final String[] lista = imagenDestacada.split("/");
        final int elementos = lista.length;
        if (elementos >= 2) {
            return lista[elementos - 2] + "/" + lista[elementos - 1];
        } else {
            return imagenDestacada;
        }
    }

    /**
     * Obtener columna galeria.
     *
     * @param columnas
     *            the columnas
     * @return the string
     */
    public static String obtenerColumnaGaleria(final Integer columnas) {
        String classColumn = "";
        switch (columnas) {
        case 1:
            classColumn = "col-sm-12";
            break;
        case 2:
            classColumn = "col-sm-6";
            break;
        case 3:
            classColumn = "col-sm-4";
            break;
        case 4:
            classColumn = "col-sm-3";
            break;
        case 5:
            classColumn = "col-sm-2";
            break;
        case 6:
            classColumn = "col-sm-2";
            break;
        default:
            classColumn = "col-sm-12";
        }
        return classColumn;
    }

    /**
     * Generar autores string.
     *
     * @param libroDTO
     *            the libro dto
     * @return the string
     */
    public static String generarGenerosString(final LibroDTO libroDTO) {
        String generosString = "";
        if (CollectionUtils.isNotEmpty(libroDTO.getGeneros())) {
            final Iterator<GenreDTO> iterator = libroDTO.getGeneros().iterator();
            while (iterator.hasNext()) {
                final GenreDTO autor = iterator.next();
                generosString += autor.getNombre();
                if (iterator.hasNext()) {
                    generosString += ", ";
                }
            }
        }
        return generosString;
    }

    /**
     * Generar autores string.
     *
     * @param libroDTO
     *            the libro dto
     * @return the string
     */
    public static String generarAutoresString(final LibroDTO libroDTO) {
        String autoresString = "";
        if (CollectionUtils.isNotEmpty(libroDTO.getAutores())) {
            final Iterator<AutorDTO> iterator = libroDTO.getAutores().iterator();
            while (iterator.hasNext()) {
                final AutorDTO autor = iterator.next();
                autoresString += autor.getNombre();
                if (iterator.hasNext()) {
                    autoresString += ", ";
                }
            }
        }
        return autoresString;
    }



    public static ObjectMapper getMapper() {

        return objectMapper;
    }


    /**
     * Gets the reference to an application-context bean
     *
     * @param clazz	the type of the bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


    /**
     * Gets the current-user
     */
    public static <ID extends Serializable> UserDto<ID> currentUser() {

        // get the authentication object
        Authentication auth = SecurityContextHolder
                .getContext().getAuthentication();

        // get the user from the authentication object
        return LecUtils.currentUser(auth);
    }


    /**
     * Signs a user in
     *
     * @param user
     */
    public static <U extends AbstractUser<U,ID>, ID extends Serializable>
    void login(U user) {

        MomokoPrincipal<ID> principal = new MomokoPrincipal<>(user.toUserDto());

        Authentication authentication = // make the authentication object
                new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication); // put that in the security context
        principal.eraseCredentials();
    }


    /**
     * Throws a VersionException if the versions of the
     * given entities aren't same.
     *
     * @param original
     * @param updated
     */
    public static <U extends AbstractUser<U,ID>, ID extends Serializable>
    void ensureCorrectVersion(VersionedEntity<U,ID> original, VersionedEntity<U,ID> updated) {

        if (original.getVersion() != updated.getVersion())
            throw new VersionException(original.getClass().getSimpleName(), original.getId().toString());
    }

    /**
     * A convenient method for running code
     * after successful database commit.
     *
     * @param runnable
     */
    public static void afterCommit(Runnable runnable) {

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {

                        runnable.run();
                    }
                });
    }


    /**
     * Generates a random unique string
     */
    public static String uid() {

        return UUID.randomUUID().toString();
    }


    /**
     * Serializes an object to JSON string
     */
    public static <T> String toJson(T obj) throws JsonProcessingException {

        return objectMapper.writeValueAsString(obj);
    }


    /**
     * Deserializes a JSON String
     */
    public static <T> T fromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        return objectMapper.readValue(json, clazz);
    }


    /**
     * Throws BadCredentialsException if
     * user's credentials were updated after the JWT was issued
     */
    public static <U extends AbstractUser<U,ID>, ID extends Serializable>
    void ensureCredentialsUpToDate(JWTClaimsSet claims, U user) {

        long issueTime = (long) claims.getClaim(JwtService.LEMON_IAT);

        LecUtils.ensureCredentials(issueTime >= user.getCredentialsUpdatedMillis(),
                "com.naturalprogrammer.spring.obsoleteToken");
    }


    /**
     * Reads a resource into a String
     */
    public static String toString(Resource resource) throws IOException {

        String text = null;
        try (Scanner scanner = new Scanner(resource.getInputStream(), StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }

        return text;
    }


    /**
     * Fetches a cookie from the request
     */
    public static Optional<Cookie> fetchCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0)
            for (int i = 0; i < cookies.length; i++)
                if (cookies[i].getName().equals(name))
                    return Optional.of(cookies[i]);

        return Optional.empty();
    }

}
