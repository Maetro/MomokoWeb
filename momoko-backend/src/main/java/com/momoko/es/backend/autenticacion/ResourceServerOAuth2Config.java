/**
 * ResourceServerOAuth2Config.java 16-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerOAuth2Config extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/upload", "/modelo/**", "/rx/**", "/account/**").and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/modelo/**")
                .access("#oauth2.hasScope('modelo') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/modelo/**")
                .access("#oauth2.hasScope('modelo') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.GET, "/rx/**").access("#oauth2.hasScope('modelo') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/rx/**")
                .access("#oauth2.hasScope('modelo') and #oauth2.hasScope('write')")
                .antMatchers(HttpMethod.POST, "/upload")
                .access("#oauth2.hasScope('modelo') and #oauth2.hasScope('write')").antMatchers("/account/**")
                .permitAll().and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().csrf()
                .disable();

    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore);
        config.tokenServices(defaultTokenServices);
    }

}
