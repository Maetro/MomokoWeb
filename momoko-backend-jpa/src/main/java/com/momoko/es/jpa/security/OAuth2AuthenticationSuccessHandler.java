package com.momoko.es.jpa.security;

import com.momoko.es.commons.MomokoProperties;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Authentication success handler for redirecting the
 * OAuth2 signed in user to a URL with a short lived auth token
 * 
 * @author Sanjay Patel
 */
public class OAuth2AuthenticationSuccessHandler<ID extends Serializable>
	extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Log log = LogFactory.getLog(OAuth2AuthenticationSuccessHandler.class);

	private MomokoProperties properties;
	private JwtService jwtService;

	public OAuth2AuthenticationSuccessHandler(MomokoProperties properties, JwtService jwtService) {

		this.properties = properties;
		this.jwtService = jwtService;

		log.info("Created");
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		
		UsuarioDTO<Integer> currentUser = MomokoUtils.currentUser();
		
		String shortLivedAuthToken = jwtService.createToken(
				JwtService.AUTH_AUDIENCE,
				currentUser.getUsername(),
				(long) properties.getJwt().getShortLivedMillis());

		String targetUrl = MomokoUtils.fetchCookie(request,
				HttpCookieOAuth2AuthorizationRequestRepository.MOMOKO_REDIRECT_URI_COOKIE_PARAM_NAME)
				.map(Cookie::getValue)
				.orElse(properties.getOauth2AuthenticationSuccessUrl());
		
		HttpCookieOAuth2AuthorizationRequestRepository.deleteCookies(request, response);
		return targetUrl + shortLivedAuthToken;
	}
}
