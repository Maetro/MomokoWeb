package com.momoko.es.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class MomokoSecurityConfigImpl extends MomokoSecurityConfig {

    private static final Log log = LogFactory.getLog(MomokoSecurityConfigImpl.class);

    public MomokoSecurityConfigImpl() {
        log.info("Created");
    }

    @Override
    protected void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("GOOD_ADMIN");
        super.authorizeRequests(http);
    }
}