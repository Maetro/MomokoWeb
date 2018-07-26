package com.momoko.es.jpa.security;

import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.exceptions.util.LexUtils;
import com.momoko.es.jpa.MomokoService;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Map;

/**
 * Logs in or registers a user after OAuth2 SignIn/Up
 */
public class MomokoOAuth2UserService<U extends AbstractUser<U,ID>, ID extends Serializable> extends DefaultOAuth2UserService {

	private static final Log log = LogFactory.getLog(MomokoOAuth2UserService.class);

	private MomokoUserDetailsService<U, ?> userDetailsService;
	private MomokoService<U, ?> momokoService;
	private PasswordEncoder passwordEncoder;

	public MomokoOAuth2UserService(
			MomokoUserDetailsService<U, ?> userDetailsService,
			MomokoService<U, ?> momokoService,
			PasswordEncoder passwordEncoder) {

		this.userDetailsService = userDetailsService;
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
	public MomokoPrincipal<ID> buildPrincipal(OAuth2User oath2User, String registrationId) {
		
		Map<String, Object> attributes = oath2User.getAttributes();
		String email = momokoService.getOAuth2Email(registrationId, attributes);
		LexUtils.validate(email != null, "com.momoko.es.oauth2EmailNeeded", registrationId).go();
		
		boolean emailVerified = momokoService.getOAuth2AccountVerified(registrationId, attributes);
		LexUtils.validate(emailVerified, "com.momoko.es.oauth2EmailNotVerified", registrationId).go();
		
    	U user = userDetailsService.findUserByUsername(email).orElseGet(()  -> {
    		
			// register a new user
			U newUser = momokoService.newUser();
			newUser.setEmail(email);
			newUser.setPassword(passwordEncoder.encode(MomokoUtils.uid()));
			
			momokoService.fillAdditionalFields(registrationId, newUser, attributes);
			momokoService.save(newUser);

			try {
				
				momokoService.mailForgotPasswordLink(newUser);
				
			} catch (Throwable e) {
				
				// In case of exception, just log the error and keep silent			
				log.error(ExceptionUtils.getStackTrace(e));
			}
			
			return newUser;
    	});
    	
    	UserDto<ID> userDto = user.toUserDto();
		MomokoPrincipal<ID> principal = new MomokoPrincipal<>(userDto);
		principal.setAttributes(attributes);
		principal.setName(oath2User.getName());
		
		return principal;
	}
}
