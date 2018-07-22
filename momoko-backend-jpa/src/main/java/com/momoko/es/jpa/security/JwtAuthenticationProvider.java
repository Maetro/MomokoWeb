package com.momoko.es.jpa.security;

import com.momoko.es.commons.security.JwtAuthenticationToken;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.jpa.domain.AbstractUser;
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
public class JwtAuthenticationProvider
<U extends AbstractUser<U,ID>, ID extends Serializable> implements AuthenticationProvider {

    private static final Log log = LogFactory.getLog(JwtAuthenticationProvider.class);

	private final JwtService jwtService;
	private MomokoUserDetailsService<U, ID> userDetailsService;
	
	public JwtAuthenticationProvider(JwtService jwtService, MomokoUserDetailsService<U, ID> userDetailsService) {

		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
		
		log.debug("Created");
	}

	@Override
	public Authentication authenticate(Authentication auth) {
		
		log.debug("Authenticating ...");

		String token = (String) auth.getCredentials();
		
		JWTClaimsSet claims = jwtService.parseToken(token, JwtService.AUTH_AUDIENCE);
		
        String username = claims.getSubject();
        U user = userDetailsService.findUserByUsername(username)
        		.orElseThrow(() -> new UsernameNotFoundException(username));

        log.debug("User found ...");

        MomokoUtils.ensureCredentialsUpToDate(claims, user);
        MomokoPrincipal<ID> principal = new MomokoPrincipal<ID>(user.toUserDto());
        		
        return new JwtAuthenticationToken(principal, token, principal.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
