package com.momoko.es.jpa;

import com.momoko.es.commons.MomokoProperties;
import com.momoko.es.commons.domain.ChangePasswordForm;
import com.momoko.es.commons.domain.ResetPasswordForm;
import com.momoko.es.commons.mail.MailSender;
import com.momoko.es.commons.mail.MomokoMailData;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UserEditPermission;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.exceptions.util.LexUtils;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.util.MomokoUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The Momoko Service class
 * 
 * @author Sanjay Patel
 */
@Validated
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public abstract class MomokoService {

    private static final Log log = LogFactory.getLog(MomokoService.class);
    
	private MomokoProperties properties;
	private PasswordEncoder passwordEncoder;
    private MailSender mailSender;
	private UsuarioRepository usuarioRepository;
	private UserService userService;
	private JwtService jwtService;

	@Autowired
	public void createMomokoService(MomokoProperties properties,
			PasswordEncoder passwordEncoder,
			MailSender<?> mailSender, UsuarioRepository usuarioRepository,
			UserService userService,
			JwtService jwtService) {
		
		this.properties = properties;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
		this.usuarioRepository = usuarioRepository;
		this.userService = userService;
		this.jwtService = jwtService;
		
		log.info("Created");
	}

	
	/**
     * This method is called after the application is ready.
     * Needs to be public - otherwise Spring screams.
     * 
     * @param event
     */
    @EventListener
    public void afterApplicationReady(ApplicationReadyEvent event) {
    	
    	log.info("Starting up Spring Momoko ...");
    	onStartup(); // delegate to onStartup()
    	log.info("Spring Momoko started");	
    }

    
	/**
	 * Creates the initial Admin user, if not found.
	 * Override this method if needed.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void onStartup() {
    	
		try {
			
			// Check if the user already exists
			userService
				.loadUserByUsername(properties.getAdmin().getUsername());
			
		} catch (UsernameNotFoundException e) {
			
			// Doesn't exist. So, create it.
	    	UsuarioEntity user = createAdminUser();
			usuarioRepository.save(user);
		}
	}


	/**
	 * Creates the initial Admin user.
	 * Override this if needed.
	 */
    protected UsuarioEntity createAdminUser() {
		
    	// fetch data about the user to be created
    	MomokoProperties.Admin initialAdmin = properties.getAdmin();
    	
    	log.info("Creating the first admin user: " + initialAdmin.getUsername());

    	// create the user
		UsuarioEntity user = newUser();
    	user.setEmail(initialAdmin.getUsername());
		user.setPassword(passwordEncoder.encode(
			properties.getAdmin().getPassword()));
		user.getRoles().add(UserUtils.Role.ADMIN);
		
		return user;
	}

    
	/**
	 * Creates a new user object. Must be overridden in the
	 * subclass, like this:
	 * 
	 * <pre>
	 * public User newUser() {
	 *    return new User();
	 * }
	 * </pre>
	 */
    public abstract UsuarioEntity newUser();


	/**
	 * Returns the context data to be sent to the client,
	 * i.e. <code>reCaptchaSiteKey</code> and all the properties
	 * prefixed with <code>momoko.shared</code>.
	 * 
	 * To send custom properties, put those in your application
	 * properties in the format <em>momoko.shared.fooBar</em>.
	 * 
	 * If a user is logged in, it also returns the user data
	 * and a new authorization token. If expirationMillis is not provided,
	 * the expiration of the new token is set to the default.
	 *
	 * Override this method if needed.
	 */
	public Map<String, Object> getContext(Optional<Long> expirationMillis, HttpServletResponse response) {
		
		log.debug("Getting context ...");

		// make the context
		Map<String, Object> sharedProperties = new HashMap<String, Object>(2);
		sharedProperties.put("reCaptchaSiteKey", properties.getRecaptcha().getSitekey());
		sharedProperties.put("shared", properties.getShared());
		
		UsuarioDTO currentUser = MomokoUtils.currentUser();
		if (currentUser != null)
			addAuthHeader(response, currentUser.getUsername(),
				expirationMillis.orElse(properties.getJwt().getExpirationMillis()));
		
		return LecUtils.mapOf(
				"context", sharedProperties,
				"user", MomokoUtils.currentUser());	
	}
	
	
	/**
	 * Signs up a user.
	 */
	@Validated(UserUtils.SignUpValidation.class)
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(@Valid UsuarioEntity user) {
		
		log.debug("Signing up user: " + user);

		initUser(user); // sets right all fields of the user
		usuarioRepository.save(user);
		
		// if successfully committed
		MomokoUtils.afterCommit(() -> {
		
			MomokoUtils.login(user); // log the user in
			log.debug("Signed up user: " + user);
		});
	}
	
	
	/**
	 * Initializes the user based on the input data,
	 * e.g. encrypts the password
	 */
	protected void initUser(UsuarioEntity user) {
		
		log.debug("Initializing user: " + user);

		user.setPassword(passwordEncoder.encode(user.getPassword())); // encode the password
		makeUnverified(user); // make the user unverified
	}

	
	/**
	 * Makes a user unverified
	 */
	protected void makeUnverified(UsuarioEntity user) {
		
		user.getRoles().add(UserUtils.Role.UNVERIFIED);
		user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		MomokoUtils.afterCommit(() -> sendVerificationMail(user)); // send a verification mail to the user
	}
	
	
	/**
	 * Sends verification mail to a unverified user.
	 */
	protected void sendVerificationMail(final UsuarioEntity user) {
		try {
			
			log.debug("Sending verification mail to: " + user);
			
			String verificationCode = jwtService.createToken(JwtService.VERIFY_AUDIENCE,
					user.getId().toString(), properties.getJwt().getExpirationMillis(),
					LecUtils.mapOf("email", user.getEmail()));

			// make the link
			String verifyLink = properties.getApplicationUrl()
				+ "/users/" + user.getId() + "/verification?code=" + verificationCode;

			// send the mail
			sendVerificationMail(user, verifyLink);

			log.debug("Verification mail to " + user.getEmail() + " queued.");
			
		} catch (Throwable e) {
			// In case of exception, just log the error and keep silent
			log.error(ExceptionUtils.getStackTrace(e));
		}
	}	

	
	/**
	 * Sends verification mail to a unverified user.
	 * Override this method if you're using a different MailData
	 */
	protected void sendVerificationMail(final UsuarioEntity user, String verifyLink) {
		
		// send the mail
		mailSender.send(MomokoMailData.of(user.getEmail(),
			LexUtils.getMessage("com.momoko.es.verifySubject"),
			LexUtils.getMessage(
				"com.momoko.es.verifyEmail",	verifyLink)));
	}	

	
	/**
	 * Resends verification mail to the user.
	 */
	@UserEditPermission
	public void resendVerificationMail(UsuarioEntity user) {

		// The user must exist
		LexUtils.ensureFound(user);
		
		// must be unverified
		LexUtils.validate(user.getRoles().contains(UserUtils.Role.UNVERIFIED),
				"com.momoko.es.alreadyVerified").go();

		// send the verification mail
		sendVerificationMail(user);
	}

	
	/**
	 * Fetches a user by email
	 */
	public UsuarioEntity fetchUserByEmail(@Valid @Email @NotBlank String email) {
		
		log.debug("Fetching user by email: " + email);
		return processUser(usuarioRepository.findByEmail(email).orElse(null));
	}

	
	/**
	 * Returns a non-null, processed user for the client.
	 */
	public UsuarioEntity processUser(UsuarioEntity user) {
		
		log.debug("Fetching user: " + user);

		// ensure that the user exists
		LexUtils.ensureFound(user);
		
		// hide confidential fields
		hideConfidentialFields(user);
		
		return user;
	}
	
	
	/**
	 * Verifies the email id of current-user
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void verifyUser(Integer userId, String verificationCode) {
		
		log.debug("Verifying user ...");

		UsuarioEntity user = usuarioRepository.findById(userId).orElseThrow(LexUtils.notFoundSupplier());
		
		// ensure that he is unverified
		LexUtils.validate(user.hasRole(UserUtils.Role.UNVERIFIED),
				"com.momoko.es.alreadyVerified").go();
		
		JWTClaimsSet claims = jwtService.parseToken(verificationCode, JwtService.VERIFY_AUDIENCE, user.getCredentialsUpdatedMillis());
		
		LecUtils.ensureAuthority(
				claims.getSubject().equals(user.getId().toString()) &&
				claims.getClaim("email").equals(user.getEmail()),
				"com.momoko.es.wrong.verificationCode");
		
		user.getRoles().remove(UserUtils.Role.UNVERIFIED); // make him verified
		user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		usuarioRepository.save(user);
		
		// after successful commit,
		MomokoUtils.afterCommit(() -> {
			
			// Re-login the user, so that the UNVERIFIED role is removed
			MomokoUtils.login(user);
			log.debug("Re-logged-in the user for removing UNVERIFIED role.");		
		});
		
		log.debug("Verified user: " + user);		
	}

	
	/**
	 * Forgot password.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void forgotPassword(@Valid @Email @NotBlank String email) {
		
		log.debug("Processing forgot password for email: " + email);
		
		// fetch the user record from database
		UsuarioEntity user = usuarioRepository.findByEmail(email)
				.orElseThrow(LexUtils.notFoundSupplier());

		mailForgotPasswordLink(user);
	}
	
	
	/**
	 * Mails the forgot password link.
	 * 
	 * @param user
	 */
	public void mailForgotPasswordLink(UsuarioEntity user) {
		
		log.debug("Mailing forgot password link to user: " + user);

		String forgotPasswordCode = jwtService.createToken(JwtService.FORGOT_PASSWORD_AUDIENCE,
				user.getEmail(), properties.getJwt().getExpirationMillis());

		// make the link
		String forgotPasswordLink =	properties.getApplicationUrl()
			    + "/reset-password?code=" + forgotPasswordCode;
		
		mailForgotPasswordLink(user, forgotPasswordLink);
		
		log.debug("Forgot password link mail queued.");
	}

	
	/**
	 * Mails the forgot password link.
	 * 
	 * Override this method if you're using a different MailData
	 */
	public void mailForgotPasswordLink(UsuarioEntity user, String forgotPasswordLink) {
		
		// send the mail
		mailSender.send(MomokoMailData.of(user.getEmail(),
				LexUtils.getMessage("com.momoko.es.forgotPasswordSubject"),
				LexUtils.getMessage("com.momoko.es.forgotPasswordEmail",
					forgotPasswordLink)));
	}
	
	/**
	 * Resets the password.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void resetPassword(@Valid ResetPasswordForm form) {
		
		log.debug("Resetting password ...");

		JWTClaimsSet claims = jwtService.parseToken(form.getCode(),
				JwtService.FORGOT_PASSWORD_AUDIENCE);
		
		String email = claims.getSubject();
		
		// fetch the user
		UsuarioEntity user = usuarioRepository.findByEmail(email).orElseThrow(LexUtils.notFoundSupplier());
		MomokoUtils.ensureCredentialsUpToDate(claims, user);
		
		// sets the password
		user.setPassword(passwordEncoder.encode(form.getNewPassword()));
		user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		//user.setForgotPasswordCode(null);
		
		usuarioRepository.save(user);
		
		// after successful commit,
		MomokoUtils.afterCommit(() -> {
			
			// Login the user
			MomokoUtils.login(user);
		});
		
		log.debug("Password reset.");		
	}

	
	/**
	 * Updates a user with the given data.
	 */
	@UserEditPermission
	@Validated(UserUtils.UpdateValidation.class)
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public UsuarioDTO updateUser(UsuarioEntity user, @Valid UsuarioEntity updatedUser) {
		
		log.debug("Updating user: " + user);

		// checks
		MomokoUtils.ensureCorrectVersion(user, updatedUser);

		// delegates to updateUserFields
		updateUserFields(user, updatedUser, MomokoUtils.currentUser());
		usuarioRepository.save(user);
		
		log.debug("Updated user: " + user);
		
		UsuarioDTO userDto = user.toUserDto();
		userDto.setPassword(null);
		return userDto;
	}
	
	
	/**
	 * Changes the password.
	 */
	@UserEditPermission
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public String changePassword(UsuarioEntity user, @Valid ChangePasswordForm changePasswordForm) {
		
		log.debug("Changing password for user: " + user);
		
		// Get the old password of the logged in user (logged in user may be an ADMIN)
		UsuarioDTO currentUser = MomokoUtils.currentUser();
		UsuarioEntity loggedIn = usuarioRepository.findById(currentUser.getUsuarioId()).orElse(null);
		String oldPassword = loggedIn.getPassword();

		// checks
		LexUtils.ensureFound(user);
		LexUtils.validate("changePasswordForm.oldPassword",
			passwordEncoder.matches(changePasswordForm.getOldPassword(),
					oldPassword),
			"com.momoko.es.wrong.password").go();
		
		// sets the password
		user.setPassword(passwordEncoder.encode(changePasswordForm.getPassword()));
		user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		usuarioRepository.save(user);

		log.debug("Changed password for user: " + user);
		return user.toUserDto().getUsername();
	}


	/**
	 * Updates the fields of the users. Override this if you have more fields.
	 */
	protected void updateUserFields(UsuarioEntity user, UsuarioEntity updatedUser, UsuarioDTO currentUser) {

		log.debug("Updating user fields for user: " + user);

		// Another good admin must be logged in to edit roles
		if (currentUser.isGoodAdmin() &&
		   !currentUser.getUserId().equals(user.getId())) {
			
			log.debug("Updating roles for user: " + user);

			// update the roles
			
			if (user.getRoles().equals(updatedUser.getRoles())) // roles are same
				return;
			
			if (updatedUser.hasRole(UserUtils.Role.UNVERIFIED)) {
				
				if (!user.hasRole(UserUtils.Role.UNVERIFIED)) {

					makeUnverified(user); // make user unverified
				}
			} else {
				
				if (user.hasRole(UserUtils.Role.UNVERIFIED))
					user.getRoles().remove(UserUtils.Role.UNVERIFIED); // make user verified
			}
			
			user.setRoles(updatedUser.getRoles());
			user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		}
	}

	
	/**
	 * Requests for email change.
	 */
	@UserEditPermission
	@Validated(UserUtils.ChangeEmailValidation.class)
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void requestEmailChange(UsuarioEntity user, @Valid UsuarioEntity updatedUser) {
		
		log.debug("Requesting email change: " + user);

		// checks
		LexUtils.ensureFound(user);	
		LexUtils.validate("updatedUser.password",
			passwordEncoder.matches(updatedUser.getPassword(),
									user.getPassword()),
			"com.momoko.es.wrong.password").go();

		// preserves the new email id
		user.setNewEmail(updatedUser.getNewEmail());
		//user.setChangeEmailCode(MomokoUtils.uid());
		usuarioRepository.save(user);
		
		// after successful commit, mails a link to the user
		MomokoUtils.afterCommit(() -> mailChangeEmailLink(user));
		
		log.debug("Requested email change: " + user);		
	}

	
	/**
	 * Mails the change-email verification link to the user.
	 */
	protected void mailChangeEmailLink(UsuarioEntity user) {
		
		String changeEmailCode = jwtService.createToken(JwtService.CHANGE_EMAIL_AUDIENCE,
				user.getId().toString(), properties.getJwt().getExpirationMillis(),
				LecUtils.mapOf("newEmail", user.getNewEmail()));
		
		try {
			
			log.debug("Mailing change email link to user: " + user);

			// make the link
			String changeEmailLink = properties.getApplicationUrl()
				    + "/users/" + user.getId()
					+ "/change-email?code=" + changeEmailCode;
			
			// mail it
			mailChangeEmailLink(user, changeEmailLink);
			
			log.debug("Change email link mail queued.");
			
		} catch (Throwable e) {
			// In case of exception, just log the error and keep silent			
			log.error(ExceptionUtils.getStackTrace(e));
		}
	}


	/**
	 * Mails the change-email verification link to the user.
	 * 
	 * Override this method if you're using a different MailData
	 */
	protected void mailChangeEmailLink(UsuarioEntity user, String changeEmailLink) {
		
		mailSender.send(MomokoMailData.of(user.getNewEmail(),
				LexUtils.getMessage(
				"com.momoko.es.changeEmailSubject"),
				LexUtils.getMessage(
				"com.momoko.es.changeEmailEmail",
				 changeEmailLink)));
	}

	
	/**
	 * Change the email.
	 */
	@PreAuthorize("isAuthenticated()")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void changeEmail(Integer userId, @Valid @NotBlank String changeEmailCode) {
		
		log.debug("Changing email of current user ...");

		// fetch the current-user
		UsuarioDTO currentUser = MomokoUtils.currentUser();
		
		LexUtils.validate(userId.equals(currentUser.getUserId()),
			"com.momoko.es.wrong.login").go();

		UsuarioEntity user = usuarioRepository.findById(userId).orElseThrow(LexUtils.notFoundSupplier());
		
		LexUtils.validate(StringUtils.isNotBlank(user.getNewEmail()),
				"com.momoko.es.blank.newEmail").go();
		
		JWTClaimsSet claims = jwtService.parseToken(changeEmailCode,
				JwtService.CHANGE_EMAIL_AUDIENCE,
				user.getCredentialsUpdatedMillis());
		
		LecUtils.ensureAuthority(
				claims.getSubject().equals(user.getId().toString()) &&
				claims.getClaim("newEmail").equals(user.getNewEmail()),
				"com.momoko.es.wrong.changeEmailCode");
		
		// Ensure that the email would be unique 
		LexUtils.validate(
				!usuarioRepository.findByEmail(user.getNewEmail()).isPresent(),
				"com.momoko.es.duplicate.email").go();
		
		// update the fields
		user.setEmail(user.getNewEmail());
		user.setNewEmail(null);
		//user.setChangeEmailCode(null);
		user.setCredentialsUpdatedMillis(System.currentTimeMillis());
		
		// make the user verified if he is not
		if (user.hasRole(UserUtils.Role.UNVERIFIED))
			user.getRoles().remove(UserUtils.Role.UNVERIFIED);
		
		usuarioRepository.save(user);
		
		// after successful commit,
		MomokoUtils.afterCommit(() -> {
			
			// Login the user
			MomokoUtils.login(user);
		});
		
		log.debug("Changed email of user: " + user);
	}


	/**
	 * Extracts the email id from user attributes received from OAuth2 provider, e.g. Google
	 * 
	 */
	public String getOAuth2Email(String registrationId, Map<String, Object> attributes) {

		return (String) attributes.get(StandardClaimNames.EMAIL);
	}

	
	/**
	 * Extracts additional fields, e.g. name from user attributes received from OAuth2 provider, e.g. Google
	 * Override this if you introduce more user fields, e.g. name
	 */
	public void fillAdditionalFields(String clientId, UsuarioEntity user, Map<String, Object> attributes) {
		
	}

	
	/**
	 * Checks if the account at the OAuth2 provider is verified 
	 */
	public boolean getOAuth2AccountVerified(String registrationId, Map<String, Object> attributes) {

		Object verified = attributes.get(StandardClaimNames.EMAIL_VERIFIED);
		if (verified == null)
			verified = attributes.get("verified");
		
		return (boolean) verified;
	}


	/**
	 * Fetches a new token - for session scrolling etc.
	 * @return 
	 */
	@PreAuthorize("isAuthenticated()")
	public String fetchNewToken(Optional<Long> expirationMillis,
			Optional<String> optionalUsername) {
		
		UsuarioDTO currentUser = MomokoUtils.currentUser();
		String username = optionalUsername.orElse(currentUser.getUsername());
		
		LecUtils.ensureAuthority(currentUser.getUsername().equals(username) ||
				currentUser.isGoodAdmin(), "com.momoko.es.notGoodAdminOrSameUser");
		
		return LecUtils.TOKEN_PREFIX +
				jwtService.createToken(JwtService.AUTH_AUDIENCE, username,
				expirationMillis.orElse(properties.getJwt().getExpirationMillis()));
	}

	
	/**
	 * Saves the user
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(UsuarioEntity user) {
		
		usuarioRepository.save(user);
	}
	
	
	/**
	 * Hides the confidential fields before sending to client
	 */
	protected void hideConfidentialFields(UsuarioEntity user) {
		
		user.setPassword(null); // JsonIgnore didn't work
		
		if (!user.hasPermission(MomokoUtils.currentUser(), UserUtils.Permission.ADMIN))
			user.setEmail(null);
		
		log.debug("Hid confidential fields for user: " + user);
	}

	
	/**
	 * Adds a Momoko-Authorization header to the response
	 */
	public void addAuthHeader(HttpServletResponse response, String username, Long expirationMillis) {
	
		response.addHeader(LecUtils.TOKEN_RESPONSE_HEADER_NAME,
				LecUtils.TOKEN_PREFIX +
				jwtService.createToken(JwtService.AUTH_AUDIENCE, username, expirationMillis));
	}
}
