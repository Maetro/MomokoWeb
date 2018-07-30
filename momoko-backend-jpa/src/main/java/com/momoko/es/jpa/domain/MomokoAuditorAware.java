package com.momoko.es.jpa.domain;

import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.util.MomokoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class MomokoAuditorAware implements AuditorAware<UsuarioEntity> {
	
    private static final Log log = LogFactory.getLog(MomokoAuditorAware.class);
    
    private UsuarioRepository userRepository;
    
	public MomokoAuditorAware(UsuarioRepository userRepository) {
		
		this.userRepository = userRepository;
		log.info("Created");
	}

	@Override
	public Optional<UsuarioEntity> getCurrentAuditor() {
		
		UsuarioDTO<Integer> currentUser = MomokoUtils.currentUser();
		
		if (currentUser == null)
			return Optional.empty();
		
		return userRepository.findById(currentUser.getUserId());
	}	
}
