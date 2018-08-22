package com.momoko.es.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.MomokoSecurityConfigImpl;
import com.momoko.es.commons.MomokoProperties;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.validation.RetypePasswordValidator;
import com.momoko.es.exceptions.ErrorResponseComposer;
import com.momoko.es.exceptions.MomokoExceptionsAutoConfiguration;
import com.momoko.es.jpa.domain.MomokoAuditorAware;
import com.momoko.es.jpa.exceptions.DefaultExceptionHandlerControllerAdvice;
import com.momoko.es.jpa.exceptions.MomokoErrorAttributes;
import com.momoko.es.jpa.exceptions.MomokoErrorController;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.model.service.UserService;
import com.momoko.es.jpa.security.*;
import com.momoko.es.jpa.util.MomokoUtils;
import com.momoko.es.jpa.validation.CaptchaValidator;
import com.momoko.es.jpa.validation.UniqueEmailValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AutoConfigureBefore({
	WebMvcAutoConfiguration.class,
	ErrorMvcAutoConfiguration.class,
	SecurityAutoConfiguration.class,
	SecurityFilterAutoConfiguration.class,
	MomokoExceptionsAutoConfiguration.class})
public class MomokoAutoConfiguration {
	
	/**
	 * For handling JSON vulnerability,
	 * JSON response bodies would be prefixed with
	 * this string.
	 */
	public final static String JSON_PREFIX = ")]}',\n";

	private static final Log log = LogFactory.getLog(MomokoAutoConfiguration.class);
	
	public MomokoAutoConfiguration() {
		log.info("Created");
	}

	/**
	 * Prefixes JSON responses for JSON vulnerability. Disabled by default.
	 * To enable, add this to your application properties:
	 *     momoko.enabled.json-prefix: true
	 */
	@Bean
	@ConditionalOnProperty(name="momoko.enabled.json-prefix")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			ObjectMapper objectMapper) {
		
        log.info("Configuring JSON vulnerability prefix");       

        MappingJackson2HttpMessageConverter converter =
        		new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setJsonPrefix(JSON_PREFIX);
        
        return converter;
	}
	
	/**
	 * Configures an Auditor Aware if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(AuditorAware.class)
	public AuditorAware<UsuarioEntity> auditorAware(UsuarioRepository userRepository) {
		
        log.info("Configuring MomokoAuditorAware");
		return new MomokoAuditorAware(userRepository);
	}

	/**
	 * Configures DefaultExceptionHandlerControllerAdvice if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(DefaultExceptionHandlerControllerAdvice.class)
	public <T extends Throwable>
	DefaultExceptionHandlerControllerAdvice<T> defaultExceptionHandlerControllerAdvice(ErrorResponseComposer errorResponseComposer) {
		
        log.info("Configuring DefaultExceptionHandlerControllerAdvice");       
		return new DefaultExceptionHandlerControllerAdvice<T>(errorResponseComposer);
	}
	
	/**
	 * Configures an Error Attributes if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(ErrorAttributes.class)
	public <T extends Throwable>
    ErrorAttributes errorAttributes(ErrorResponseComposer errorResponseComposer) {
		
        log.info("Configuring MomokoErrorAttributes");
		return new MomokoErrorAttributes<T>(errorResponseComposer);
	}
	
	/**
	 * Configures an Error Controller if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(ErrorController.class)
	public ErrorController errorController(ErrorAttributes errorAttributes,
                                           ServerProperties serverProperties,
                                           List<ErrorViewResolver> errorViewResolvers) {
		
        log.info("Configuring MomokoErrorController");       
		return new MomokoErrorController(errorAttributes, serverProperties, errorViewResolvers);
	}	

	/**
	 * Configures AuthenticationSuccessHandler if missing
	 */
	@Bean
	@ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
	public AuthenticationSuccessHandler authenticationSuccessHandler(
            ObjectMapper objectMapper, MomokoService momokoService, MomokoProperties properties) {
		
        log.info("Configuring AuthenticationSuccessHandler");       
		return new AuthenticationSuccessHandler(objectMapper, momokoService, properties);
	}
	
	/**
	 * Configures OAuth2AuthenticationSuccessHandler if missing
	 */
	@Bean
	@ConditionalOnMissingBean(OAuth2AuthenticationSuccessHandler.class)
	public OAuth2AuthenticationSuccessHandler<?> oauth2AuthenticationSuccessHandler(
			MomokoProperties properties, JwtService jwtService) {
		
        log.info("Configuring OAuth2AuthenticationSuccessHandler");       
		return new OAuth2AuthenticationSuccessHandler<>(properties, jwtService);
	}
	
	/**
	 * Configures OAuth2AuthenticationFailureHandler if missing
	 */
	@Bean
	@ConditionalOnMissingBean(OAuth2AuthenticationFailureHandler.class)
	public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
		
        log.info("Configuring OAuth2AuthenticationFailureHandler");       
		return new OAuth2AuthenticationFailureHandler();
	}

	/**
	 * Configures AuthenticationFailureHandler if missing
	 */
	@Bean
	@ConditionalOnMissingBean(AuthenticationFailureHandler.class)
    public AuthenticationFailureHandler authenticationFailureHandler() {
		
        log.info("Configuring SimpleUrlAuthenticationFailureHandler");       
    	return new SimpleUrlAuthenticationFailureHandler();
    }	


	/**
	 * Configures MomokoCorsConfig if missing and momoko.cors.allowed-origins is provided
	 */
	@Bean
	@ConditionalOnProperty(name="momoko.cors.allowed-origins")
	@ConditionalOnMissingBean(MomokoCorsConfig.class)
	public MomokoCorsConfig momokoCorsConfig(MomokoProperties properties) {
		
        log.info("Configuring MomokoCorsConfig");       
		return new MomokoCorsConfig(properties);		
	}
	
	/**
	 * Configures MomokoOidcUserService if missing
	 */
	@Bean
	@ConditionalOnMissingBean(MomokoOidcUserService.class)
	public MomokoOidcUserService momokoOidcUserService(MomokoOAuth2UserService momokoOAuth2UserService) {
		
        log.info("Configuring MomokoOidcUserService");       
		return new MomokoOidcUserService(momokoOAuth2UserService);
	}

	/**
	 * Configures MomokoOAuth2UserService if missing
	 */
	@Bean
	@ConditionalOnMissingBean(MomokoOAuth2UserService.class)
	public MomokoOAuth2UserService momokoOAuth2UserService(
			UsuarioRepository usuarioRepository,
			MomokoService momokoService,
			PasswordEncoder passwordEncoder) {

        log.info("Configuring MomokoOAuth2UserService");
		return new MomokoOAuth2UserService(usuarioRepository, momokoService, passwordEncoder);
	}

	/**
	 * Configures JwtAuthenticationProvider if missing
	 */
	@Bean
	@ConditionalOnMissingBean(JwtAuthenticationProvider.class)
	public JwtAuthenticationProvider jwtAuthenticationProvider(
			JwtService jwtService,
			UserService userService) {

        log.info("Configuring JwtAuthenticationProvider");
		return new JwtAuthenticationProvider(jwtService, userService);
	}

	@Bean
	@ConditionalOnMissingBean(MomokoSecurityConfig.class)
	public MomokoSecurityConfig momokoSecurityConfig() {
		
        log.info("Configuring MomokoSecurityConfig");       
		return new MomokoSecurityConfigImpl();
	}
	
	/**
	 * Configures MomokoUtils
	 */
	@Bean
	public MomokoUtils momokoUtil(ApplicationContext applicationContext,
								  ObjectMapper objectMapper) {

        log.info("Configuring MomokoUtil");       		
		return new MomokoUtils(applicationContext, objectMapper);
	}
	
	/**
	 * Configures CaptchaValidator if missing
	 */
	@Bean
	@ConditionalOnMissingBean(CaptchaValidator.class)
	public CaptchaValidator captchaValidator(MomokoProperties properties, RestTemplateBuilder restTemplateBuilder) {
		
        log.info("Configuring MomokoUserDetailsService");       
		return new CaptchaValidator(properties, restTemplateBuilder);
	}
	
	/**
	 * Configures RetypePasswordValidator if missing
	 */
	@Bean
	@ConditionalOnMissingBean(RetypePasswordValidator.class)
	public RetypePasswordValidator retypePasswordValidator() {
		
        log.info("Configuring RetypePasswordValidator");       
		return new RetypePasswordValidator();
	}
	
	/**
	 * Configures UniqueEmailValidator if missing
	 */
	@Bean
	public UniqueEmailValidator uniqueEmailValidator(UsuarioRepository userRepository) {
		
        log.info("Configuring UniqueEmailValidator");       
		return new UniqueEmailValidator(userRepository);		
	}	
}
