package com.momoko.es.services;

import com.momoko.es.commons.security.UserDto;
import com.momoko.es.entities.User;
import com.momoko.es.jpa.MomokoService;
import com.momoko.es.jpa.util.MomokoUtils;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyService extends MomokoService<User, Long> {

	public static final String ADMIN_NAME = "Administrator";

	@Override
    public User newUser() {
        return new User();
    }

	@Override
    protected void updateUserFields(User user, User updatedUser, UserDto<Long> currentUser) {

        super.updateUserFields(user, updatedUser, currentUser);

        user.setName(updatedUser.getName());

        MomokoUtils.afterCommit(() -> {
            if (currentUser.getId().equals(user.getId()))
                currentUser.setTag(user.toTag());
        });
    }
    
    @Override
    protected User createAdminUser() {
    	
    	User user = super.createAdminUser(); 
    	user.setName(ADMIN_NAME);
    	return user;
    }
    
    
    @Override
    public void fillAdditionalFields(String registrationId, User user, Map<String, Object> attributes) {
    	
    	String nameKey;
    	
    	switch (registrationId) {
    		
    	case "facebook":
    		nameKey = StandardClaimNames.NAME;
    		break;
    		
    	case "google":
			nameKey = StandardClaimNames.NAME;
			break;
			
		default:
			throw new UnsupportedOperationException("Fetching name from " + registrationId + " login not supprrted");
    	}
    	
    	user.setName((String) attributes.get(nameKey));
    }
}