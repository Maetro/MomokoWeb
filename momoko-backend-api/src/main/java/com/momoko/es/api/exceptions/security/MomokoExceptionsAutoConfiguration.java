package com.momoko.es.api.exceptions.security;

import com.momoko.es.api.exceptions.security.handlers.AbstractExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses=AbstractExceptionHandler.class)
public class MomokoExceptionsAutoConfiguration {

	private static final Log log = LogFactory.getLog(MomokoExceptionsAutoConfiguration.class);

	public MomokoExceptionsAutoConfiguration() {
		log.info("Created");
	}
	
	
	/**
	 * Configures ErrorResponseComposer if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(ErrorResponseComposer.class)
	public <T extends Throwable>
	ErrorResponseComposer<T> errorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {
		
        log.info("Configuring ErrorResponseComposer");       
		return new ErrorResponseComposer<T>(handlers);
	}
	

	/**
	 * Configures LexUtils
	 */
	@Bean
	public LexUtils lexUtils(MessageSource messageSource, LocalValidatorFactoryBean validator) {

        log.info("Configuring LexUtils");       		
		return new LexUtils(messageSource, validator);
	}
}
