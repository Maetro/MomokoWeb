package com.momoko.es.api.exceptions.security.validation;

/**
 * A form using RetypePassword constraint
 * should implement this interface
 *  
 * @author Sanjay Patel
 */
public interface RetypePasswordForm {

	String getPassword();
	String getRetypePassword();
}
