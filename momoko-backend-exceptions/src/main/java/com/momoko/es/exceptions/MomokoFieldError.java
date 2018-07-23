package com.momoko.es.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Holds a field or form error
 * 
 * @author Sanjay Patel
 */
public class MomokoFieldError {
	
	// Name of the field. Null in case of a form level error. 
	private String field;
	
	// Error code. Typically the I18n message-code.
	private String code;
	
	// Error message
	private String message;
	
	
	public MomokoFieldError(String field, String code, String message) {
		this.field = field;
		this.code = code;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "FieldError {field=" + field + ", code=" + code + ", message=" + message + "}";
	}


	/**
	 * Converts a set of ConstraintViolations
	 * to a list of FieldErrors
	 * 
	 * @param constraintViolations
	 */
	public static List<MomokoFieldError> getErrors(
			Set<ConstraintViolation<?>> constraintViolations) {
		
		return constraintViolations.stream()
				.map(MomokoFieldError::of).collect(Collectors.toList());
	}
	

	public static Collection<MomokoFieldError> getErrors(ExplicitConstraintViolationException ex) {
		
		return ex.getConstraintViolations().stream()
			.map(constraintViolation ->
				new MomokoFieldError(
						constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessageTemplate(),
						constraintViolation.getMessage()))
		    .collect(Collectors.toList());	
	}

	/**
	 * Converts a ConstraintViolation
	 * to a FieldError
	 */
	private static MomokoFieldError of(ConstraintViolation<?> constraintViolation) {
		
		// Get the field name by removing the first part of the propertyPath.
		// (The first part would be the service method name)
		String field = StringUtils.substringAfter(
				constraintViolation.getPropertyPath().toString(), ".");
		
		return new MomokoFieldError(field,
				constraintViolation.getMessageTemplate(),
				constraintViolation.getMessage());		
	}

	public static List<MomokoFieldError> getErrors(WebExchangeBindException ex) {
		
		List<MomokoFieldError> errors = ex.getFieldErrors().stream()
			.map(MomokoFieldError::of).collect(Collectors.toList());
		
		errors.addAll(ex.getGlobalErrors().stream()
			.map(MomokoFieldError::of).collect(Collectors.toSet()));
		
		return errors;
	}

	private static MomokoFieldError of(FieldError fieldError) {
		
		return new MomokoFieldError(fieldError.getObjectName() + "." + fieldError.getField(),
				fieldError.getCode(), fieldError.getDefaultMessage());
	}

	public static MomokoFieldError of(ObjectError error) {
		
		return new MomokoFieldError(error.getObjectName(),
				error.getCode(), error.getDefaultMessage());
	}

}
