package com.momoko.es.api.exceptions.security.handlers;

import com.momoko.es.api.exceptions.security.MomokoFieldError;
import com.momoko.es.api.exceptions.security.MultiErrorException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class MultiErrorExceptionHandler extends AbstractExceptionHandler<MultiErrorException> {

	public MultiErrorExceptionHandler() {
		
		super(MultiErrorException.class.getSimpleName());
		log.info("Created");
	}
	
	@Override
	public String getMessage(MultiErrorException ex) {
		return ex.getMessage();
	}

	@Override
	public HttpStatus getStatus(MultiErrorException ex) {
		return ex.getStatus();
	}
	
	@Override
	public Collection<MomokoFieldError> getErrors(MultiErrorException ex) {
		return ex.getErrors();
	}
}
