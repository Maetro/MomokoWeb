package com.momoko.es.backend.model.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.momoko.es.backend.security.PermissionEvaluatorEntity;
import com.momoko.es.backend.security.UserDto;
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
public class MomokoEntity<U extends AbstractUser<U,ID>, ID extends Serializable> extends AbstractAuditable<U, ID> implements PermissionEvaluatorEntity {

	private static final long serialVersionUID = -8151190931948396443L;
	
	/**
	 * Whether the given user has the given permission for
	 * this entity. Override this method where you need.
	 */
	@Override
	public boolean hasPermission(UserDto<?> user, String permission) {
		return false;
	}
	
}
