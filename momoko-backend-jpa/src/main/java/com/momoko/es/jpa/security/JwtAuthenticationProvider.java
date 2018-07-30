package com.momoko.es.jpa.security;

import com.momoko.es.commons.security.JwtAuthenticationToken;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.util.MomokoUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

/**
 * Authentication provider for JWT token authentication
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Log log = LogFactory.getLog(JwtAuthenticationProvider.class);

	private final JwtService jwtService;
	private UserService userService;
	
	public JwtAuthenticationProvider(JwtService jwtService, UserService userService) {

		this.jwtService = jwtService;
		this.userService = userService;
		
		log.debug("Created");
	}

	@Override
	public Authentication authenticate(Authentication auth) {
		
		log.debug("Authenticating ...");

		String token = (String) auth.getCredentials();
		
		JWTClaimsSet claims = jwtService.parseToken(token, JwtService.AUTH_AUDIENCE);
		
        String username = claims.getSubject();
		UsuarioEntity user = userService.findByUsuarioEmail(username);
		if (user == null){
			throw new UsernameNotFoundException(username);
		}
        log.debug("User found ...");

        MomokoUtils.ensureCredentialsUpToDate(claims, user);
        MomokoPrincipal principal = new MomokoPrincipal(user.toUserDto());
        		
        return new JwtAuthenticationToken(principal, token, principal.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
