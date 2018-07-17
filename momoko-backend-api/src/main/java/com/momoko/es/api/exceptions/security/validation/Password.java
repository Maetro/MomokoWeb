package com.momoko.es.api.exceptions.security.validation;

import com.momoko.es.backend.security.common.util.UserUtils;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message="{com.naturalprogrammer.spring.blank.password}")
@Size(min=UserUtils.PASSWORD_MIN, max=UserUtils.PASSWORD_MAX,
	message="{com.naturalprogrammer.spring.invalid.password.size}")
@Retention(RUNTIME)
@Constraint(validatedBy = { })
public @interface Password {
	
	String message() default "{com.naturalprogrammer.spring.invalid.password.size}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
