package com.momoko.es.backend.security;

import org.springframework.security.core.GrantedAuthority;

public class MomokoGrantedAuthority implements GrantedAuthority {

    public MomokoGrantedAuthority() {
    }

    public MomokoGrantedAuthority(String authority) {
        this.authority = authority;
    }

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
