package com.momoko.es.jpa;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.momoko.es.commons.MomokoProperties;
import com.momoko.es.commons.domain.ChangePasswordForm;
import com.momoko.es.commons.domain.ResetPasswordForm;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.exceptions.util.LexUtils;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public abstract class MomokoController
	<U extends AbstractUser<U,ID>, ID extends Serializable> {

	private static final Log log = LogFactory.getLog(MomokoController.class);

    private long jwtExpirationMillis;
    private JwtService jwtService;
	private MomokoService<U, ID> momokoService;
	
	@Autowired
	public void createMomokoController(
			MomokoProperties properties,
			MomokoService<U, ID> momokoService,
			JwtService jwtService) {
		
		this.jwtExpirationMillis = properties.getJwt().getExpirationMillis();
		this.momokoService = momokoService;
		this.jwtService = jwtService;
		
		log.info("Created");
	}


	/**
	 * A simple function for pinging this server.
	 */
	@GetMapping("/ping")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ping() {
		
		log.debug("Received a ping");
	}
	
	
	/**
	 * Returns context properties needed at the client side,
	 * current-user data and an Authorization token as a response header.
	 */
	@GetMapping("/context")
	public Map<String, Object> getContext(
			@RequestParam Optional<Long> expirationMillis,
			HttpServletResponse response) {

		log.debug("Getting context ");
		Map<String, Object> context = momokoService.getContext(expirationMillis, response);
		log.debug("Returning context: " + context);

		return context;
	}
	

	/**
	 * Signs up a user, and
	 * returns current-user data and an Authorization token as a response header.
	 */
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto<ID> signup(@RequestBody @JsonView(UserUtils.SignupInput.class) U user,
			HttpServletResponse response) {
		
		log.debug("Signing up: " + user);
		momokoService.signup(user);
		log.debug("Signed up: " + user);

		return userWithToken(response);
	}
	
	
	/**
	 * Resends verification mail
	 */
	@PostMapping("/users/{id}/resend-verification-mail")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resendVerificationMail(@PathVariable("id") U user) {
		
		log.debug("Resending verification mail for: " + user);
		momokoService.resendVerificationMail(user);
		log.debug("Resent verification mail for: " + user);
	}	


	/**
	 * Verifies current-user
	 */
	@PostMapping("/users/{id}/verification")
	public UserDto<ID> verifyUser(
			@PathVariable ID id,
			@RequestParam String code,
			HttpServletResponse response) {
		
		log.debug("Verifying user ...");		
		momokoService.verifyUser(id, code);
		
		return userWithToken(response);
	}
	

	/**
	 * The forgot Password feature
	 */
	@PostMapping("/forgot-password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void forgotPassword(@RequestParam String email) {
		
		log.debug("Received forgot password request for: " + email);				
		momokoService.forgotPassword(email);
	}
	

	/**
	 * Resets password after it's forgotten
	 */
	@PostMapping("/reset-password")
	public UserDto<ID> resetPassword(
			@RequestBody ResetPasswordForm form,
			HttpServletResponse response) {
		
		log.debug("Resetting password ... ");				
		momokoService.resetPassword(form);
		
		return userWithToken(response);
	}


	/**
	 * Fetches a user by email
	 */
	@PostMapping("/users/fetch-by-email")
	public U fetchUserByEmail(@RequestParam String email) {
		
		log.debug("Fetching user by email: " + email);						
		return momokoService.fetchUserByEmail(email);
	}

	
	/**
	 * Fetches a user by ID
	 */	
	@GetMapping("/users/{id}")
	public U fetchUserById(@PathVariable("id") U user) {
		
		log.debug("Fetching user: " + user);				
		return momokoService.processUser(user);
	}

	
	/**
	 * Updates a user
	 */
	@PatchMapping("/users/{id}")
	public UserDto<ID> updateUser(
			@PathVariable("id") U user,
			@RequestBody String patch,
			HttpServletResponse response)
			throws JsonProcessingException, IOException, JsonPatchException {
		
		log.debug("Updating user ... ");
		
		// ensure that the user exists
		LexUtils.ensureFound(user);
		U updatedUser = LecUtils.applyPatch(user, patch); // create a patched form
		UserDto<ID> userDto = momokoService.updateUser(user, updatedUser);
		
		// Send a new token for logged in user in the response
		userWithToken(response);
		
		// Send updated user data in the response
		return userDto;
	}
	
	
	/**
	 * Changes password
	 */
	@PostMapping("/users/{id}/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@PathVariable("id") U user,
			@RequestBody ChangePasswordForm changePasswordForm,
			HttpServletResponse response) {
		
		log.debug("Changing password ... ");				
		String username = momokoService.changePassword(user, changePasswordForm);
		
		momokoService.addAuthHeader(response, username, jwtExpirationMillis);
	}


	/**
	 * Requests for changing email
	 */
	@PostMapping("/users/{id}/email-change-request")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void requestEmailChange(@PathVariable("id") U user,
								   @RequestBody U updatedUser) {
		
		log.debug("Requesting email change ... ");				
		momokoService.requestEmailChange(user, updatedUser);
	}


	/**
	 * Changes the email
	 */
	@PostMapping("/users/{userId}/email")
	public UserDto<ID> changeEmail(
			@PathVariable ID userId,
			@RequestParam String code,
			HttpServletResponse response) {
		
		log.debug("Changing email of user ...");		
		momokoService.changeEmail(userId, code);
		
		// return the currently logged in user with new email
		return userWithToken(response);		
	}


	/**
	 * Fetch a new token - for session sliding, switch user etc.
	 */
	@PostMapping("/fetch-new-auth-token")
	public Map<String, String> fetchNewToken(
			@RequestParam Optional<Long> expirationMillis,
			@RequestParam Optional<String> username,
			HttpServletResponse response) {
		
		log.debug("Fetching a new token ... ");
		return LecUtils.mapOf("token", momokoService.fetchNewToken(expirationMillis, username));
	}


	/**
	 * returns the current user and a new authorization token in the response
	 */
	protected UserDto<ID> userWithToken(HttpServletResponse response) {
		
		UserDto<ID> currentUser = MomokoUtils.currentUser();
		momokoService.addAuthHeader(response, currentUser.getUsername(), jwtExpirationMillis);
		return currentUser;
	}
}
