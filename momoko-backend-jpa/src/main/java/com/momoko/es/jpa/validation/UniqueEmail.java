package com.momoko.es.jpa.validation;

import com.momoko.es.commons.util.UserUtils;

import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for unique-email constraint,
 * ensuring that the given email id is not already
 * used by a user.  
 * 
 * @author Sanjay Patel
 */
@NotBlank(message = "{com.momoko.es.blank.email}")
@Size(min=UserUtils.EMAIL_MIN, max=UserUtils.EMAIL_MAX,
	message = "{com.momoko.es.invalid.email.size}")
@Email(message = "{com.momoko.es.invalid.email}")
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueEmailValidator.class)
public @interface UniqueEmail {
 
    String message() default "{com.momoko.es.duplicate.email}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}
