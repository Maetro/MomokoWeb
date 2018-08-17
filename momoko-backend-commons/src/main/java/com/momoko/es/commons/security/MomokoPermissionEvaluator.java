package com.momoko.es.commons.security;

import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.commons.util.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;


public class MomokoPermissionEvaluator implements PermissionEvaluator {

	private static final Log log = LogFactory.getLog(MomokoPermissionEvaluator.class);

	public MomokoPermissionEvaluator() {
		log.info("Created");
	}

	/**
	 * Called by Spring Security to evaluate the permission
	 * 
	 * @param auth	Spring Security authentication object,
	 * 				from which the current-user can be found
	 * @param targetDomainObject	Object for which permission is being checked
	 * @param permission			What permission is being checked for, e.g. 'edit'
	 */
	@Override
	public boolean hasPermission(Authentication auth,
			Object targetDomainObject, Object permission) {

		UsuarioDTO<Integer> user =  LecUtils.currentUser(auth);
		log.debug("Checking whether " + (user != null ? user.getUsuarioEmail() : auth.getName())
				+ "\n  has " + permission + " permission");
		if (targetDomainObject == null)	// if no domain object is provided,
			return true;
		PermissionEvaluatorEntity entity = (PermissionEvaluatorEntity) targetDomainObject;
		return entity.hasPermission(user, (String) permission);
	}

	
	/**
	 * We need to override this method as well. To keep things simple,
	 * Let's not use this, throwing exception is someone uses it.
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		
		throw new UnsupportedOperationException("hasPermission() by ID is not supported");		
	}

}
