package com.momoko.es.commons.handlers;

import com.momoko.es.exceptions.handlers.AbstractExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AccessDeniedExceptionHandler extends AbstractExceptionHandler<AccessDeniedException> {
	
	public AccessDeniedExceptionHandler() {
		
		super(AccessDeniedException.class.getSimpleName());
		log.info("Created");
	}
	
	@Override
	public HttpStatus getStatus(AccessDeniedException ex) {
		return HttpStatus.FORBIDDEN;
	}
}
