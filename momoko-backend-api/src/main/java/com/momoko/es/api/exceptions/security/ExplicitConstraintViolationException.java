package com.momoko.es.api.exceptions.security;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class ExplicitConstraintViolationException extends ConstraintViolationException {

	private static final long serialVersionUID = 3723548255231135762L;

	private final String objectName;
	
	public ExplicitConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations, String objectName) {
		super(constraintViolations);
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}
}
