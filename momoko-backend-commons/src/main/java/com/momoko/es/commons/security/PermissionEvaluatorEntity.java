package com.momoko.es.commons.security;

public interface PermissionEvaluatorEntity {

	/**
	 * Whether the given user has the given permission for
	 * this entity. Override this method where you need.
	 */
	public boolean hasPermission(UsuarioDTO<?> currentUser, String permission);
}
