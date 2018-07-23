package com.momoko.es.backend.model.entity.security.validation;

import com.momoko.es.backend.security.common.util.UserUtils;

import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NotBlank(message = "{com.naturalprogrammer.spring.blank.email}")
@Size(min=UserUtils.EMAIL_MIN, max=UserUtils.EMAIL_MAX,
	message = "{com.naturalprogrammer.spring.invalid.email.size}")
@Email(message = "{com.naturalprogrammer.spring.invalid.email}")
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueEmailValidator.class)
public @interface UniqueEmail {
 
    String message() default "{com.naturalprogrammer.spring.duplicate.email}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}
