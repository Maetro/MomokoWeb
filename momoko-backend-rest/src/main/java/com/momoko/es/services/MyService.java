package com.momoko.es.services;

import com.momoko.es.commons.security.UsuarioDTO;
import com.momoko.es.jpa.MomokoService;
import com.momoko.es.jpa.model.entity.UsuarioEntity;
import com.momoko.es.jpa.util.MomokoUtils;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyService extends MomokoService {

	public static final String ADMIN_NAME = "Administrator";

	@Override
    public UsuarioEntity newUser() {
        return new UsuarioEntity();
    }

	@Override
    protected void updateUserFields(UsuarioEntity user, UsuarioEntity updatedUser, UsuarioDTO currentUser) {

        super.updateUserFields(user, updatedUser, currentUser);

		user.setUsuarioLogin(updatedUser.getUsuarioLogin());

        MomokoUtils.afterCommit(() -> {
            if (currentUser.getUserId().equals(user.getId()))
                currentUser.setTag(user.toTag());
        });
    }
    
    @Override
    protected UsuarioEntity createAdminUser() {

		UsuarioEntity user = super.createAdminUser();
    	user.setUsuarioLogin(ADMIN_NAME);
    	return user;
    }
    

    public void fillAdditionalFields(String registrationId, UsuarioEntity user, Map<String, Object> attributes) {
    	
    	String nameKey;
    	
    	switch (registrationId) {
    		
    	case "facebook":
    		nameKey = StandardClaimNames.NAME;
    		break;
    		
    	case "google":
			nameKey = StandardClaimNames.NAME;
			break;
			
		default:
			throw new UnsupportedOperationException("Fetching name from " + registrationId + " login not supported");
    	}
    	
    	user.setUsuarioLogin((String) attributes.get(nameKey));
    }
}