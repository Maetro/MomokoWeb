package com.momoko.es.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.momoko.es.commons.security.PermissionEvaluatorEntity;
import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base class for all entities.
 * 
 * @author Sanjay Patel
  */
@MappedSuperclass
@JsonIgnoreProperties({ "createdBy", "lastModifiedBy", "createdDate", "lastModifiedDate" })
public class MomokoEntity extends AbstractAuditable<UsuarioEntity, Integer> implements PermissionEvaluatorEntity {

	private static final long serialVersionUID = -8151190931948396443L;
	
	/**
	 * Whether the given user has the given permission for
	 * this entity. Override this method where you need.
	 */
	@Override
	public boolean hasPermission(UsuarioDTO<?> currentUser, String permission) {
		return false;
	}
}
