/**
 * SecurityConfiguration.java 12-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

@Configuration
@RestController
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(6)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(final Principal principal) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // @Override
    // protected void configure(final AuthenticationManagerBuilder authBuilder) {
    // authBuilder.authenticationProvider(this.authProvider);
    // this.authProvider.setPasswordEncoder(encoder());
    // }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
                .authenticated().and().exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
                .logoutSuccessUrl("/").permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        // @formatter:on
    }

    // http.requestMatchers().antMatchers("/login/**", "/modelo/**", "/auth/**", "/account/**").and()
    // .authorizeRequests().antMatchers(HttpMethod.GET, "/modelo/**")
    // .access("#oauth2.hasScope('admin') and #oauth2.hasScope('read')")
    // .antMatchers(HttpMethod.POST, "/modelo/**")
    // .access("#oauth2.hasScope('admin') and #oauth2.hasScope('write')")
    // .antMatchers(HttpMethod.GET, "/modelo/**").access("#oauth2.hasScope('admin') and
    // #oauth2.hasScope('read')")
    // .antMatchers(HttpMethod.POST, "/rx/**")
    // .access("#oauth2.hasScope('doctor') and #oauth2.hasScope('write')")
    // .antMatchers("/account/**", "/auth/**", "/login/**").permitAll().and().exceptionHandling()
    // .accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().csrf().disable();

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(final OAuth2ClientContextFilter filter) {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("gmail")
    public ClientResources gmail() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    private Filter ssoFilter() {
        final CompositeFilter filter = new CompositeFilter();
        final List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(gmail(), "/login/gmail"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(final ClientResources client, final String path) {
        final OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(
                path);
        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(),
                this.oauth2ClientContext);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        final UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
                client.getClient().getClientId());
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        return oAuth2ClientAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
