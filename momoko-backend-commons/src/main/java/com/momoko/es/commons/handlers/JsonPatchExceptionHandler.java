package com.momoko.es.commons.handlers;

import com.github.fge.jsonpatch.JsonPatchException;
import com.momoko.es.exceptions.handlers.AbstractBadRequestExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class JsonPatchExceptionHandler extends AbstractBadRequestExceptionHandler<JsonPatchException> {

	public JsonPatchExceptionHandler() {
		
		super(JsonPatchException.class.getSimpleName());
		log.info("Created");
	}
}
