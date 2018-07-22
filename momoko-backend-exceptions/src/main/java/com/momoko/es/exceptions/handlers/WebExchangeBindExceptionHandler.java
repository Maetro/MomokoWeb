package com.momoko.es.exceptions.handlers;

import com.momoko.es.exceptions.MomokoFieldError;
import com.momoko.es.exceptions.util.LexUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Collection;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebExchangeBindExceptionHandler extends AbstractExceptionHandler<WebExchangeBindException> {

	public WebExchangeBindExceptionHandler() {
		
		super(WebExchangeBindException.class.getSimpleName());
		log.info("Created");
	}
	
	@Override
	public HttpStatus getStatus(WebExchangeBindException ex) {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
	@Override
	public Collection<MomokoFieldError> getErrors(WebExchangeBindException ex) {
		return MomokoFieldError.getErrors(ex);
	}
	
	@Override
	public String getMessage(WebExchangeBindException ex) {
		return LexUtils.getMessage("com.naturalprogrammer.spring.validationError");
	}
}
