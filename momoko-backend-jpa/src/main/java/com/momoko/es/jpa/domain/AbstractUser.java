package com.momoko.es.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.commons.validation.Password;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.validation.Captcha;
import com.momoko.es.jpa.validation.UniqueEmail;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Base class for User entity
 * 
 * @author Sanjay Patel
 */
@MappedSuperclass
public class AbstractUser extends VersionedEntity {
	
	// email
	@JsonView(UserUtils.SignupInput.class)
	@UniqueEmail(groups = {UserUtils.SignUpValidation.class})
	@Column(nullable = false, unique=true, length = UserUtils.EMAIL_MAX)
	protected String email;
	
	// password
	@JsonView(UserUtils.SignupInput.class)
	@Password(groups = {UserUtils.SignUpValidation.class, UserUtils.ChangeEmailValidation.class})
	@Column(nullable = false) // no length because it will be encrypted
	protected String password;
	
	// roles collection
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="usr_role", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role")
	protected Set<String> roles = new HashSet<>();
	
	// in the email-change process, temporarily stores the new email
	@UniqueEmail(groups = {UserUtils.ChangeEmailValidation.class})
	@Column(length = UserUtils.EMAIL_MAX)
	protected String newEmail;

	// A JWT issued before this won't be valid
	@Column(nullable = false)
	@JsonIgnore
	protected long credentialsUpdatedMillis = System.currentTimeMillis();

	// holds reCAPTCHA response while signing up
	@Transient
	@JsonView(UserUtils.SignupInput.class)
	@Captcha(groups = {UserUtils.SignUpValidation.class})
	private String captchaResponse;
	
	public final boolean hasRole(String role) {
		return roles.contains(role);
	}	
	
	/**
	 * Called by spring security permission evaluator
	 * to check whether the current-user has the given permission
	 * on this entity. 
	 */
	public boolean hasPermission(UserDto<?> currentUser, String permission) {
		
		return UserUtils.hasPermission(getId(), currentUser, permission);		
	}

	
	/**
	 * A convenient toString method
	 */
	@Override
	public String toString() {
		return "AbstractUser [email=" + email + ", roles=" + roles + "]";
	}


	/**
	 * Makes a User DTO
	 */
	public UsuarioDTO toUserDto() {

		UsuarioDTO userDto = new UsuarioDTO();

		userDto.setUsername(email);
		userDto.setPassword(password);
		userDto.setRoles(roles);
		userDto.setTag(toTag());
		
		boolean unverified = hasRole(UserUtils.Role.UNVERIFIED);
		boolean blocked = hasRole(UserUtils.Role.BLOCKED);
		boolean admin = hasRole(UserUtils.Role.ADMIN);
		boolean goodUser = !(unverified || blocked);
		boolean goodAdmin = goodUser && admin;

		userDto.setAdmin(admin);
		userDto.setBlocked(blocked);
		userDto.setGoodAdmin(goodAdmin);
		userDto.setGoodUser(goodUser);
		userDto.setUnverified(unverified);
		
		return userDto;
	}

	/**
	 * Override this to supply any additional fields to the user DTO,
	 * e.g. name
	 */
	public Serializable toTag() {
		
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public long getCredentialsUpdatedMillis() {
		return credentialsUpdatedMillis;
	}

	public void setCredentialsUpdatedMillis(long credentialsUpdatedMillis) {
		this.credentialsUpdatedMillis = credentialsUpdatedMillis;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}
}
