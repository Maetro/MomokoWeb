/**
 * CustomAuthenticationProvider.java 16-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.momoko.es.api.dto.UsuarioDTO;
import com.momoko.es.api.exceptions.UserNotFoundException;
import com.momoko.es.backend.model.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider() {
        super();
    }

    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UsuarioDTO user = null;
        try {
            user = this.userService.doesEmailExist(username);
        } catch (final UserNotFoundException e) {
        }

        if ((user == null) || !user.getUsuarioEmail().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        final String encodedPassword = this.userService.getUserEncodedPassword(user.getUsuarioEmail());

        if (!this.passwordEncoder.matches(password, encodedPassword)) {
            throw new BadCredentialsException("Wrong password.");
        }
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (user.getUsuarioRolId() == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        final UserDetails principal = new org.springframework.security.core.userdetails.User(username, password,
                authorities);
        return new UsernamePasswordAuthenticationToken(principal, password, authorities);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}