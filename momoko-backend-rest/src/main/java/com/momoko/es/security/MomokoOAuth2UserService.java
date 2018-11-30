package com.momoko.es.security;

import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.exceptions.util.LexUtils;
import com.momoko.es.jpa.MomokoService;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * Logs in or registers a user after OAuth2 SignIn/Up
 */
public class MomokoOAuth2UserService extends DefaultOAuth2UserService {

	private static final Log log = LogFactory.getLog(MomokoOAuth2UserService.class);

	private UsuarioRepository usuarioRepository;
	private MomokoService momokoService;
	private PasswordEncoder passwordEncoder;

	public MomokoOAuth2UserService(
			UsuarioRepository usuarioRepository,
			MomokoService momokoService,
			PasswordEncoder passwordEncoder) {

		this.usuarioRepository = usuarioRepository;
		this.momokoService = momokoService;
		this.passwordEncoder = passwordEncoder;
		
		log.info("Created");
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oath2User = super.loadUser(userRequest);
		return buildPrincipal(oath2User, userRequest.getClientRegistration().getRegistrationId());
	}

	/**
	 * Builds the security principal from the given userReqest.
	 * Registers the user if not already reqistered
	 */
	public MomokoPrincipal buildPrincipal(OAuth2User oath2User, String registrationId) {
		
		Map<String, Object> attributes = oath2User.getAttributes();
		String email = momokoService.getOAuth2Email(registrationId, attributes);
		LexUtils.validate(email != null, "com.momoko.es.oauth2EmailNeeded", registrationId).go();
		
		boolean emailVerified = momokoService.getOAuth2AccountVerified(registrationId, attributes);
		LexUtils.validate(emailVerified, "com.momoko.es.oauth2EmailNotVerified", registrationId).go();

		UsuarioEntity user = usuarioRepository.findByEmail(email).orElse(null);

		if (user == null){

			// register a new user
			UsuarioEntity newUser = new UsuarioEntity();
			newUser.setEmail(email);
			newUser.setPassword(passwordEncoder.encode(MomokoUtils.uid()));

			momokoService.fillAdditionalFields(registrationId, newUser, attributes);
			usuarioRepository.save(newUser);

			try {

				momokoService.mailForgotPasswordLink(newUser);

			} catch (Throwable e) {

				// In case of exception, just log the error and keep silent
				log.error(ExceptionUtils.getStackTrace(e));
			}

			user = newUser;
    	}
    	
    	UsuarioDTO usuarioDTO = EntityToDTOAdapter.adaptarUsuario(user);
		MomokoPrincipal principal = new MomokoPrincipal(usuarioDTO);
		principal.setAttributes(attributes);
		principal.setName(oath2User.getName());
		
		return principal;
	}
}
