package com.momoko.es.backend.model.entity.security.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=CaptchaValidator.class)
public @interface Captcha {
 
    String message() default "{com.naturalprogrammer.spring.wrong.captcha}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}
