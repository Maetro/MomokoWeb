package com.momoko.es.jpa.security;

import com.momoko.es.commons.security.MomokoPrincipal;
import com.momoko.es.exceptions.util.LexUtils;
import com.momoko.es.jpa.domain.AbstractUser;
import com.momoko.es.jpa.domain.AbstractUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.util.Optional;

/**
 * UserDetailsService, as required by Spring Security.
 * 
 * @author Sanjay Patel
 */
public class MomokoUserDetailsService
	<U extends AbstractUser<U,ID>, ID extends Serializable>
implements UserDetailsService {

	private static final Log log = LogFactory.getLog(MomokoUserDetailsService.class);

	private final AbstractUserRepository<U,ID> userRepository;
	
	public MomokoUserDetailsService(AbstractUserRepository<U, ID> userRepository) {
		
		this.userRepository = userRepository;
		log.info("Created");
	}
	
	@Override
	public MomokoPrincipal<ID> loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		log.debug("Loading user having username: " + username);
		
		// delegates to findUserByUsername
		U user = findUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException(
				LexUtils.getMessage("com.momoko.es.userNotFound", username)));

		log.debug("Loaded user having username: " + username);

		return new MomokoPrincipal<>(user.toUserDto());
	}

	/**
	 * Finds a user by the given username. Override this
	 * if you aren't using email as the username.
	 */
	public Optional<U> findUserByUsername(String username) {
		return userRepository.findByEmail(username);
	}
}
