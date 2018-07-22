package com.momoko.es.jpa.domain;

import com.momoko.es.commons.security.UserDto;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.AuditorAware;

import java.io.Serializable;
import java.util.Optional;

/**
 * Needed for auto-filling of the
 * AbstractAuditable columns of AbstractUser
 *  
 * @author Sanjay Patel
 */
public class MomokoAuditorAware
	<U extends AbstractUser<U,ID>,
	 ID extends Serializable>
implements AuditorAware<U> {
	
    private static final Log log = LogFactory.getLog(MomokoAuditorAware.class);
    
    private AbstractUserRepository<U,ID> userRepository;
    
	public MomokoAuditorAware(AbstractUserRepository<U,ID> userRepository) {
		
		this.userRepository = userRepository;
		log.info("Created");
	}

	@Override
	public Optional<U> getCurrentAuditor() {
		
		UserDto<ID> currentUser = MomokoUtils.currentUser();
		
		if (currentUser == null)
			return Optional.empty();
		
		return userRepository.findById(currentUser.getId());
	}	
}
