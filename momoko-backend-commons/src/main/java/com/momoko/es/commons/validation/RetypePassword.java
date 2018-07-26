package com.momoko.es.commons.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for retype password constraint
 * 
 * @author Sanjay Patel
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RetypePasswordValidator.class)
public @interface RetypePassword {
 
    String message() default "{com.momoko.es.different.passwords}";

    Class[] groups() default {};
    
    Class[] payload() default {};
}
