package com.momoko.es.backend.security;

import com.momoko.es.backend.security.common.MomokoProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class. Extend it in the
 * application, and make a configuration class. Override
 * protected methods, if you need any customization.
 *
 * */
public class MomokoSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Log log = LogFactory.getLog(MomokoSecurityConfig.class);

    private MomokoProperties properties;
    private UserDetailsService userDetailsService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private LemonOidcUserService oidcUserService;
    private LemonOAuth2UserService<?, ?> oauth2UserService;
    private OAuth2AuthenticationSuccessHandler<?> oauth2AuthenticationSuccessHandler;
    private OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;
    private JwtAuthenticationProvider<?,?> jwtAuthenticationProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void createLemonSecurityConfig(LemonProperties properties, UserDetailsService userDetailsService,
                                          AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler,
                                          LemonOidcUserService oidcUserService,
                                          LemonOAuth2UserService<?, ?> oauth2UserService,
                                          OAuth2AuthenticationSuccessHandler<?> oauth2AuthenticationSuccessHandler,
                                          OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler,
                                          JwtAuthenticationProvider<?,?> jwtAuthenticationProvider,
                                          PasswordEncoder passwordEncoder
    ) {

        this.properties = properties;
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.oidcUserService = oidcUserService;
        this.oauth2UserService = oauth2UserService;
        this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
        this.oauth2AuthenticationFailureHandler = oauth2AuthenticationFailureHandler;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.passwordEncoder = passwordEncoder;

        log.info("Created");
    }

    /**
     * Security configuration, calling protected methods
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        sessionCreationPolicy(http); // set session creation policy
        login(http); // authentication
        logout(http); // logout related configuration
        exceptionHandling(http); // exception handling
        tokenAuthentication(http); // configure token authentication filter
        csrf(http); // CSRF configuration
        cors(http); // CORS configuration
        oauth2Client(http);
        authorizeRequests(http); // authorize requests
        otherConfigurations(http); // override this to add more configurations
    }


    /**
     * Configuring session creation policy
     */
    protected void sessionCreationPolicy(HttpSecurity http) throws Exception {

        // No session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    /**
     * Configuring authentication.
     */
    protected void login(HttpSecurity http) throws Exception {

        http
                .formLogin() // form login
                .loginPage(loginPage())

                /******************************************
                 * Setting a successUrl would redirect the user there. Instead,
                 * let's send 200 and the userDto along with an Authorization token.
                 *****************************************/
                .successHandler(authenticationSuccessHandler)

                /*******************************************
                 * Setting the failureUrl will redirect the user to
                 * that url if login fails. Instead, we need to send
                 * 401. So, let's set failureHandler instead.
                 *******************************************/
                .failureHandler(authenticationFailureHandler);
    }


    /**
     * Override this to change login URL
     *
     * @return
     */
    protected String loginPage() {

        return "/api/core/login";
    }

    /**
     * Logout related configuration
     */
    protected void logout(HttpSecurity http) throws Exception {

        http
                .logout().disable(); // we are stateless; so /logout endpoint not needed
    }


    /**
     * Configures exception-handling
     */
    protected void exceptionHandling(HttpSecurity http) throws Exception {

        http
                .exceptionHandling()

                /***********************************************
                 * To prevent redirection to the login page
                 * when someone tries to access a restricted page
                 **********************************************/
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }


    /**
     * Configuring token authentication filter
     */
    protected void tokenAuthentication(HttpSecurity http) throws Exception {

        http.addFilterBefore(new LemonTokenAuthenticationFilter(
                        super.authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * Disables CSRF. We are stateless.
     */
    protected void csrf(HttpSecurity http) throws Exception {

        http
                .csrf().disable();
    }


    /**
     * Configures CORS
     */
    protected void cors(HttpSecurity http) throws Exception {

        http
                .cors();
    }


    private void oauth2Client(HttpSecurity http) throws Exception {

        http.oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(new HttpCookieOAuth2AuthorizationRequestRepository(properties)).and()
                .successHandler(oauth2AuthenticationSuccessHandler)
                .failureHandler(oauth2AuthenticationFailureHandler)
                .userInfoEndpoint()
                .oidcUserService(oidcUserService)
                .userService(oauth2UserService);
    }


    /**
     * URL based authorization configuration. Override this if needed.
     */
    protected void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/**").permitAll();
    }


    /**
     * Override this to add more http configurations,
     * such as more authentication methods.
     *
     * @param http
     * @throws Exception
     */
    protected void otherConfigurations(HttpSecurity http)  throws Exception {

    }


    /**
     * Needed for configuring JwtAuthenticationProvider
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder).and()
                .authenticationProvider(jwtAuthenticationProvider);
    }
}