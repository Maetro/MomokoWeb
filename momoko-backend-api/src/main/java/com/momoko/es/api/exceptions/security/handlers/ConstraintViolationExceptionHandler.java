package com.momoko.es.api.exceptions.security.handlers;

import com.momoko.es.api.exceptions.security.LexUtils;
import com.momoko.es.api.exceptions.security.MomokoFieldError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ConstraintViolationExceptionHandler<E extends ConstraintViolationException> extends AbstractExceptionHandler<E> {

	public ConstraintViolationExceptionHandler() {
		
		super(ConstraintViolationException.class.getSimpleName());
		log.info("Created");
	}
	
	public ConstraintViolationExceptionHandler(String exceptionName) {
		super(exceptionName);
	}

	@Override
	public HttpStatus getStatus(E ex) {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
	@Override
	public Collection<MomokoFieldError> getErrors(E ex) {
		return MomokoFieldError.getErrors(ex.getConstraintViolations());
	}
	
	@Override
	public String getMessage(E ex) {
		return LexUtils.getMessage("com.naturalprogrammer.spring.validationError");
	}
}
