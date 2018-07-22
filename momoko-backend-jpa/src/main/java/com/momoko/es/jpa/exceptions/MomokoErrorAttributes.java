package com.momoko.es.jpa.exceptions;

import com.momoko.es.exceptions.ErrorResponseComposer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Used for handling exceptions that can't be handled by
 * <code>DefaultExceptionHandlerControllerAdvice</code>,
 * e.g. exceptions thrown in filters.
 */
public class MomokoErrorAttributes<T extends Throwable> extends DefaultErrorAttributes {
	
    private static final Log log = LogFactory.getLog(MomokoErrorAttributes.class);

	static final String HTTP_STATUS_KEY = "httpStatus";
	
	private ErrorResponseComposer<T> errorResponseComposer;
	
    public MomokoErrorAttributes(ErrorResponseComposer<T> errorResponseComposer) {

		this.errorResponseComposer = errorResponseComposer;
		log.info("Created");
	}
	
    /**
     * Calls the base class and then adds our details
     */
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest request,
			boolean includeStackTrace) {
			
		Map<String, Object> errorAttributes =
				super.getErrorAttributes(request, includeStackTrace);
		
		addMomokoErrorDetails(errorAttributes, request);
		
		return errorAttributes;
	}

	/**
     * Handles exceptions
     */
	@SuppressWarnings("unchecked")
	protected void addMomokoErrorDetails(
			Map<String, Object> errorAttributes, WebRequest request) {
		
		Throwable ex = getError(request);
		
		if (ex == null) // sometimes getError may return null,
			return;     // in which case, we can't add any more details
		
		errorAttributes.put("exception", ex.getClass().getSimpleName());
		
		errorResponseComposer.compose((T)ex).ifPresent(errorResponse -> {
			
			// check for null - errorResponse may have left something for the DefaultErrorAttributes
			
			if (errorResponse.getMessage() != null)
				errorAttributes.put("message", errorResponse.getMessage());
			
			Integer status = errorResponse.getStatus();
			
			if (status != null) {
				errorAttributes.put(HTTP_STATUS_KEY, status); // a way to pass response status to MomokoErrorController
				errorAttributes.put("status", status);
				errorAttributes.put("error", errorResponse.getError());
			}

			if (errorResponse.getErrors() != null)
				errorAttributes.put("errors", errorResponse.getErrors());			
		});
	}
}
