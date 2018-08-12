package com.momoko.es.jpa.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.exceptions.VersionException;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.domain.VersionedEntity;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.nimbusds.jwt.JWTClaimsSet;
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
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class MomokoUtils {
	
	private static final Log log = LogFactory.getLog(MomokoUtils.class);

	private static ApplicationContext applicationContext;
	private static ObjectMapper objectMapper;
	
	public MomokoUtils(ApplicationContext applicationContext,
					   ObjectMapper objectMapper) {
		
		MomokoUtils.applicationContext = applicationContext;
		MomokoUtils.objectMapper = objectMapper;
		
		log.info("Created");
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
	public static <ID extends Serializable> UsuarioDTO<ID> currentUser() {
		
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
	public static void login(UsuarioEntity user) {
		
		MomokoPrincipal principal = new MomokoPrincipal(user.toUserDto());

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
	public static void ensureCorrectVersion(VersionedEntity original, VersionedEntity updated) {
		
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
	public static void ensureCredentialsUpToDate(JWTClaimsSet claims, UsuarioEntity user) {
		
		long issueTime = (long) claims.getClaim(JwtService.MOMOKO_IAT);

		LecUtils.ensureCredentials(issueTime >= user.getCredentialsUpdatedMillis(),
				"com.momoko.es.obsoleteToken");
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
