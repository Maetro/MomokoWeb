/**
 * UsuarioAutenticado.java 08-dic-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioAutenticado extends User {

    private String userLogin;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5616089211137339414L;

    public UsuarioAutenticado(final String username, final String password, final boolean enabled,
            final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked,
            final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UsuarioAutenticado(final String username, final String password, final List<GrantedAuthority> authorities,
            final String usuarioLogin) {
        super(username, password, true, true, true, true, authorities);
        this.userLogin = usuarioLogin;
    }

    /**
     * Gets the user login.
     *
     * @return the user login
     */
    public String getUserLogin() {
        return this.userLogin;
    }

    /**
     * Sets the user login.
     *
     * @param userLogin
     *            the new user login
     */
    public void setUserLogin(final String userLogin) {
        this.userLogin = userLogin;
    }

}
