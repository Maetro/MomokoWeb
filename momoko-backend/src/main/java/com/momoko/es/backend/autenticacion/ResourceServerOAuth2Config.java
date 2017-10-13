/**
 * ResourceServerOAuth2Config.java 12-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerOAuth2Config extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.requestMatchers().antMatchers("/login/**", "/modelo/**", "/auth/**", "/account/**").and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/modelo/**")
                .access("#oauth2.hasScope('admin') and #oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/modelo/**")
                .access("#oauth2.hasScope('admin') and #oauth2.hasScope('write')")
                // .antMatchers(HttpMethod.GET, "/modelo/**").access("#oauth2.hasScope('admin') and
                // #oauth2.hasScope('read')")
                // .antMatchers(HttpMethod.POST, "/rx/**")
                // .access("#oauth2.hasScope('doctor') and #oauth2.hasScope('write')")
                .antMatchers("/account/**", "/auth/**", "/login/**").permitAll().and().exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().csrf().disable();
        // @formatter:on
    }
}

// @Configuration
// @EnableResourceServer
// public class ResourceServerOAuth2Config extends ResourceServerConfigurerAdapter {
//
// @Autowired
// private TokenStore tokenStore;
//
// @Override
// public void configure(final HttpSecurity http) throws Exception {

//
// }
//
// @Override
// public void configure(final ResourceServerSecurityConfigurer config) {
// final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
// defaultTokenServices.setTokenStore(this.tokenStore);
// config.tokenServices(defaultTokenServices);
// }
// }
