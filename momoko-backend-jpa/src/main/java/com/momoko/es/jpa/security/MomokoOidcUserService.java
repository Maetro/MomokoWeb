package com.momoko.es.jpa.security;

import com.momoko.es.commons.security.MomokoPrincipal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

/**
 * Logs in or registers a user after OpenID Connect SignIn/Up
 */
public class MomokoOidcUserService extends OidcUserService {
	
	private static final Log log = LogFactory.getLog(MomokoOidcUserService.class);

	private MomokoOAuth2UserService oauth2UserService;

	public MomokoOidcUserService(MomokoOAuth2UserService oauth2UserService) {

		this.oauth2UserService = oauth2UserService;
		log.debug("Created");
	}

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		
		OidcUser oidcUser = super.loadUser(userRequest);
		MomokoPrincipal principal = oauth2UserService.buildPrincipal(oidcUser,
			userRequest.getClientRegistration().getRegistrationId());
		
		principal.setClaims(oidcUser.getClaims());
		principal.setIdToken(oidcUser.getIdToken());
		principal.setUserInfo(oidcUser.getUserInfo());
		
		return principal;
	}
}
