package com.momoko.es.backend;

import com.momoko.es.backend.security.MomokoSecurityConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class MySecurityConfig extends MomokoSecurityConfig {

    private static final Log log = LogFactory.getLog(MySecurityConfig.class);

    public MySecurityConfig() {
        log.info("Created");
    }

    @Override
    protected void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("GOOD_ADMIN");
        super.authorizeRequests(http);
    }
}
