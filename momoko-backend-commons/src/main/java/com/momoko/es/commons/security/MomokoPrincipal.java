package com.momoko.es.commons.security;

import com.momoko.es.commons.util.LecUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security Principal, implementing both OidcUser, UserDetails
 */
public class MomokoPrincipal implements OidcUser, UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -7849730155307434535L;

	private final UsuarioDTO usuarioDTO;
	
	private Map<String, Object> attributes;
	private String name;
	private Map<String, Object> claims;
	private OidcUserInfo userInfo;
	private OidcIdToken idToken;
	
	public UsuarioDTO currentUser() {
		return usuarioDTO;
	}

	public MomokoPrincipal(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<String> roles = usuarioDTO.getRoles();
		
		Collection<MomokoGrantedAuthority> authorities = roles.stream()
				.map(role -> new MomokoGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toCollection(() ->
					new ArrayList<MomokoGrantedAuthority>(roles.size() + 2)));
		
		if (usuarioDTO.isGoodUser()) {
			
			authorities.add(new MomokoGrantedAuthority("ROLE_"
					+ LecUtils.GOOD_USER));
			
			if (usuarioDTO.isGoodAdmin())
				authorities.add(new MomokoGrantedAuthority("ROLE_"
					+ LecUtils.GOOD_ADMIN));
		}
		
		return authorities;	
	}

	// UserDetails ...

	@Override
	public String getPassword() {

		return usuarioDTO.getPassword();
	}

	@Override
	public String getUsername() {

		return usuarioDTO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public void eraseCredentials() {

		usuarioDTO.setPassword(null);
		attributes = null;
		claims = null;
		userInfo = null;
		idToken = null;
	}

	public UsuarioDTO getUserDto() {
		return usuarioDTO;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Object> getClaims() {
		return claims;
	}

	public void setClaims(Map<String, Object> claims) {
		this.claims = claims;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(OidcUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return idToken;
	}

	public void setIdToken(OidcIdToken idToken) {
		this.idToken = idToken;
	}
}
