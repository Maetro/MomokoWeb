package com.momoko.es;

import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.commons.util.UserUtils;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.util.MomokoUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql({"/src/test/resources/test-data/initialize.sql", "/src/test/resources/test-data/finalize.sql"})
public class UpdateUserMvcTests extends AbstractMvcTests {
	
	private static final String UPDATED_NAME = "Edited name";
	
    private String userPatch;
    private String userPatchAdminRole;
    private String userPatchNullName;
    private String userPatchLongName;
	
	@Value("classpath:/src/test/resources/update-user/patch-update-user.json")
	public void setUserPatch(Resource patch) throws IOException {
		this.userPatch = MomokoUtils.toString(patch);
	}
	
	@Value("classpath:/src/test/resources/update-user/patch-admin-role.json")
	public void setUserPatchAdminRole(Resource patch) throws IOException {
		this.userPatchAdminRole = MomokoUtils.toString(patch);;
	}

	@Value("classpath:/src/test/resources/update-user/patch-null-name.json")
	public void setUserPatchNullName(Resource patch) throws IOException {
		this.userPatchNullName = MomokoUtils.toString(patch);;
	}

	@Value("classpath:/src/test/resources/update-user/patch-long-name.json")
	public void setUserPatchLongName(Resource patch) throws IOException {
		this.userPatchLongName = MomokoUtils.toString(patch);;
	}

	/**
	 * A non-admin user should be able to update his own name,
	 * but changes in roles should be skipped.
	 * The name of security principal object should also
	 * change in the process.
	 * @throws Exception 
	 */
	@Test
    public void testUpdateSelf() throws Exception {
		
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(userPatch))
				.andExpect(status().is(200))
				.andExpect(header().string(LecUtils.TOKEN_RESPONSE_HEADER_NAME, containsString(".")))
				.andExpect(jsonPath("$.tag.name").value(UPDATED_NAME))
				.andExpect(jsonPath("$.roles").value(hasSize(1)))
				.andExpect(jsonPath("$.roles[0]").value("UNVERIFIED"))
				.andExpect(jsonPath("$.usuarioEmail").value(UNVERIFIED_USER_EMAIL));
		
		UsuarioEntity user = usuarioRepository.findById(UNVERIFIED_USER_ID).get();
		
		// Ensure that data changed properly
		Assert.assertEquals(UNVERIFIED_USER_EMAIL, user.getEmail());
		Assert.assertEquals(1, user.getRoles().size());
		Assert.assertTrue(user.getRoles().contains(UserUtils.Role.UNVERIFIED));
		Assert.assertEquals(2L, user.getVersion().longValue());
		
		// Version mismatch
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(userPatch))
				.andExpect(status().is(409));	
    }

	/**
	 * A good ADMIN should be able to update another user's name and roles.
	 * The name of security principal object should NOT change in the process,
	 * and the verification code should get set/unset on addition/deletion of
	 * the UNVERIFIED role. 
	 * @throws Exception 
	 */
	@Test
    public void testGoodAdminCanUpdateOther() throws Exception {
		
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID))
				.content(userPatch))
				.andExpect(status().is(200))
				.andExpect(header().string(LecUtils.TOKEN_RESPONSE_HEADER_NAME, containsString(".")))
				.andExpect(jsonPath("$.userId").value(UNVERIFIED_USER_ID))
				.andExpect(jsonPath("$.tag.name").value(UPDATED_NAME))
				.andExpect(jsonPath("$.roles").value(hasSize(1)))
				.andExpect(jsonPath("$.roles[0]").value("ADMIN"))
				.andExpect(jsonPath("$.usuarioEmail").value(UNVERIFIED_USER_EMAIL));
		
		UsuarioEntity user = usuarioRepository.findById(UNVERIFIED_USER_ID).get();
    	
		// Ensure that data changed properly
		Assert.assertEquals(UNVERIFIED_USER_EMAIL, user.getEmail());
		Assert.assertEquals(1, user.getRoles().size());
		Assert.assertTrue(user.getRoles().contains(UserUtils.Role.ADMIN));
    }
	
	/**
	 * Providing an unknown id should return 404.
	 */
	@Test
    public void testUpdateUnknownId() throws Exception {
    	
		mvc.perform(patch("/api/core/users/{id}", 99)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID))
				.content(userPatch))
				.andExpect(status().is(404));
    }
	
	/**
	 * A non-admin trying to update the name and roles of another user should throw exception
	 * @throws Exception 
	 */
	@Test
    public void testUpdateAnotherUser() throws Exception {
    	
		mvc.perform(patch("/api/core/users/{id}", ADMIN_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(userPatch))
				.andExpect(status().is(403));
    }

	/**
	 * A bad ADMIN trying to update the name and roles of another user should throw exception
	 * @throws Exception 
	 */
	@Test
    public void testBadAdminUpdateAnotherUser() throws Exception {
		
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_ADMIN_ID))
				.content(userPatch))
				.andExpect(status().is(403));

		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(BLOCKED_ADMIN_ID))
				.content(userPatch))
				.andExpect(status().is(403));
	}

	/**
	 * A good ADMIN should not be able to change his own roles
	 * @throws Exception 
	 */
	@Test
    public void goodAdminCanNotUpdateSelfRoles() throws Exception {
    	
		mvc.perform(patch("/api/core/users/{id}", ADMIN_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID))
				.content(userPatchAdminRole))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.tag.name").value(UPDATED_NAME))
				.andExpect(jsonPath("$.roles").value(hasSize(1)))
				.andExpect(jsonPath("$.roles[0]").value("ADMIN"));
    }
	
	/**
	 * Invalid name
	 * @throws Exception 
	 */
	@Test
    public void testUpdateUserInvalidNewName() throws Exception {
    	
		// Null name
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(userPatchNullName))
				.andExpect(status().is(422));

		// Too long name
		mvc.perform(patch("/api/core/users/{id}", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(userPatchLongName))
				.andExpect(status().is(422));
    }
}
