package com.momoko.es.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.commons.handlers.BadCredentialsExceptionHandler;
import com.momoko.es.commons.mail.MailSender;
import com.momoko.es.commons.mail.MockMailSender;
import com.momoko.es.commons.mail.SmtpMailSender;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.security.MomokoPermissionEvaluator;
import com.momoko.es.commons.util.LecUtils;
import com.nimbusds.jose.KeyLengthException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackageClasses=BadCredentialsExceptionHandler.class)
@EnableAsync
public class MomokoCommonsAutoConfiguration {

	private static final Log log = LogFactory.getLog(MomokoCommonsAutoConfiguration.class);
	
	public MomokoCommonsAutoConfiguration() {
		log.info("Created");
	}
	
	
	/**
	 * Spring Momoko related properties
	 */	
	@Bean
	public MomokoProperties MomokoProperties() {
		
        log.info("Configuring MomokoProperties");
		return new MomokoProperties();
	}
	

	/**
	 * Configures JwtService if missing
	 */
	@Bean
	@ConditionalOnMissingBean(JwtService.class)
	public JwtService jwtService(MomokoProperties properties) throws KeyLengthException {
		
        log.info("Configuring AuthenticationSuccessHandler");       
		return new JwtService(properties.getJwt().getSecret());
	}


	/**
	 * Configures Password encoder if missing
	 */
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
	
		log.info("Configuring PasswordEncoder");		
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	
	
	/**
	 * Configures PermissionEvaluator if missing
	 */
	@Bean
	@ConditionalOnMissingBean(PermissionEvaluator.class)
	public PermissionEvaluator permissionEvaluator() {
		
        log.info("Configuring MomokoPermissionEvaluator");
		return new MomokoPermissionEvaluator();
	}


	/**
	 * Configures a MockMailSender when the property
	 * <code>spring.mail.host</code> isn't defined.
	 */
	@Bean
	@ConditionalOnMissingBean(MailSender.class)
	@ConditionalOnProperty(name="spring.mail.host", havingValue="foo", matchIfMissing=true)
	public MailSender<?> mockMailSender() {

        log.info("Configuring MockMailSender");       
        return new MockMailSender();
	}

	
	/**
	 * Configures an SmtpMailSender when the property
	 * <code>spring.mail.host</code> is defined.
	 */
	@Bean
	@ConditionalOnMissingBean(MailSender.class)
	@ConditionalOnProperty("spring.mail.host")
	public MailSender<?> smtpMailSender(JavaMailSender javaMailSender) {
		
        log.info("Configuring SmtpMailSender");       
		return new SmtpMailSender(javaMailSender);
	}
	
	@Bean
	public LecUtils lecUtils(ObjectMapper objectMapper) {
		return new LecUtils(objectMapper);
	}
}
