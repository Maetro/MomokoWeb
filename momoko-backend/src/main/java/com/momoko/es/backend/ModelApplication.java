/**
 * ModelApplication.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.momoko.es.backend.model.service.properties.StorageProperties;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@EnableConfigurationProperties(StorageProperties.class)
public class ModelApplication {

    // @Autowired
    // OAuth2ClientContext oauth2ClientContext;
    //
    // @RequestMapping("/user")
    // public Principal user(final Principal principal) {
    // return principal;
    // }

    public static void main(final String[] args) {
        SpringApplication.run(ModelApplication.class, args);
    }

    // @Override
    // protected void configure(final HttpSecurity http) throws Exception {
    // // @formatter:off
    // http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/modelo/**").permitAll().anyRequest()
    // .authenticated().and().exceptionHandling()
    // .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
    // .logoutSuccessUrl("/").permitAll().and().csrf()
    // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
    // .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    // // @formatter:on
    // }
    //
    // @Bean
    // public FilterRegistrationBean oauth2ClientFilterRegistration(final OAuth2ClientContextFilter filter) {
    // final FilterRegistrationBean registration = new FilterRegistrationBean();
    // registration.setFilter(filter);
    // registration.setOrder(-100);
    // return registration;
    // }
    //
    // private Filter ssoFilter() {
    // final OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
    // "/login/facebook");
    // final OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), this.oauth2ClientContext);
    // facebookFilter.setRestTemplate(facebookTemplate);
    // final UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(),
    // facebook().getClientId());
    // tokenServices.setRestTemplate(facebookTemplate);
    // facebookFilter.setTokenServices(
    // new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
    // return facebookFilter;
    // }
    //
    // @Bean
    // @ConfigurationProperties("facebook.client")
    // public AuthorizationCodeResourceDetails facebook() {
    // return new AuthorizationCodeResourceDetails();
    // }
    //
    // @Bean
    // @ConfigurationProperties("facebook.resource")
    // public ResourceServerProperties facebookResource() {
    // return new ResourceServerProperties();
    // }
}