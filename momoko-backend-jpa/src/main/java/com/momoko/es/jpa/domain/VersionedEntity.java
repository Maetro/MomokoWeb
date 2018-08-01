package com.momoko.es.jpa.domain;

import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.util.UserUtils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;


/**
 * Base class for all entities needing optimistic locking.
 * 
 * @author Sanjay Patel
 */
@MappedSuperclass
public abstract class VersionedEntity extends MomokoEntity {

	private static final long serialVersionUID = 4310555782328370192L;

	@Version
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
