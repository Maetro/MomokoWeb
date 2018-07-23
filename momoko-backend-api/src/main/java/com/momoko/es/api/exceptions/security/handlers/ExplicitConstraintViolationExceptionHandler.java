package com.momoko.es.api.exceptions.security.handlers;

import com.momoko.es.api.exceptions.security.ExplicitConstraintViolationException;
import com.momoko.es.api.exceptions.security.MomokoFieldError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExplicitConstraintViolationExceptionHandler
	extends ConstraintViolationExceptionHandler<ExplicitConstraintViolationException> {

	public ExplicitConstraintViolationExceptionHandler() {
		
		super(ExplicitConstraintViolationException.class.getSimpleName());
		log.info("Created");
	}
		
	@Override
	public Collection<MomokoFieldError> getErrors(ExplicitConstraintViolationException ex) {
		return MomokoFieldError.getErrors(ex);
	}
}
