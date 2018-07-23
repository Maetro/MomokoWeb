package com.momoko.es.api.exceptions.security.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class RetypePasswordValidator
implements ConstraintValidator<RetypePassword, RetypePasswordForm> {
	
	private static final Log log = LogFactory.getLog(RetypePasswordValidator.class);

	@Override
	public boolean isValid(RetypePasswordForm retypePasswordForm,
		ConstraintValidatorContext context) {
		
		if (!Objects.equals(retypePasswordForm.getPassword(),
				            retypePasswordForm.getRetypePassword())) {
			
			log.debug("Retype password validation failed.");
			
			// Moving the error from form-level to
			// field-level properties: password, retypePassword
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
						"{com.naturalprogrammer.spring.different.passwords}")
						.addPropertyNode("password").addConstraintViolation()
				   .buildConstraintViolationWithTemplate(
						"{com.naturalprogrammer.spring.different.passwords}")
						.addPropertyNode("retypePassword").addConstraintViolation();
			
			return false;	
		}
		
		log.debug("Retype password validation succeeded.");		
		return true;
	}
}
