package com.momoko.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.util.MomokoUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequestEmailChangeMvcTests extends AbstractMvcTests {
	
	private static final String NEW_EMAIL = "new.email@example.com";
	
	private UsuarioEntity form() {

		UsuarioEntity user = new UsuarioEntity();
		user.setPassword(USER_PASSWORD);
		user.setNewEmail(NEW_EMAIL);
		
		return user;
	}

	@Test
	public void testRequestEmailChange() throws Exception {
		
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(form())))
				.andExpect(status().is(204));
		
		verify(mailSender).send(any());

		UsuarioEntity updatedUser = usuarioRepository.findById(UNVERIFIED_USER_ID).get();
		Assert.assertEquals(NEW_EMAIL, updatedUser.getNewEmail());
		Assert.assertEquals(UNVERIFIED_USER_EMAIL, updatedUser.getEmail());
	}
	
	/**
     * A good admin should be able to request changing email of another user.
     */
	@Test
	public void testGoodAdminRequestEmailChange() throws Exception {
		
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID))
				.content(MomokoUtils.toJson(form())))
				.andExpect(status().is(204));
		
		UsuarioEntity updatedUser = usuarioRepository.findById(UNVERIFIED_USER_ID).get();
		Assert.assertEquals(NEW_EMAIL, updatedUser.getNewEmail());
	}	
	
	/**
     * A request changing email of unknown user.
     */
	@Test
	public void testRequestEmailChangeUnknownUser() throws Exception {
		
		mvc.perform(post("/api/core/users/99/email-change-request")
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID))
				.content(MomokoUtils.toJson(form())))
				.andExpect(status().is(404));
		
		verify(mailSender, never()).send(any());
	}

	/**
	 * A non-admin should not be able to request changing
	 * the email id of another user
	 */
	@Test
	public void testNonAdminRequestEmailChangeAnotherUser() throws Exception {
		
		mvc.perform(post("/api/core/users/{id}/email-change-request", ADMIN_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(USER_ID))
				.content(MomokoUtils.toJson(form())))
				.andExpect(status().is(403));
		
		verify(mailSender, never()).send(any());

		UsuarioEntity updatedUser = usuarioRepository.findById(UNVERIFIED_USER_ID).get();
		Assert.assertNull(updatedUser.getNewEmail());
	}
	
	/**
	 * A bad admin trying to change the email id
	 * of another user
	 */
	/**
	 * A non-admin should not be able to request changing
	 * the email id of another user
	 */
	@Test
	public void testBadAdminRequestEmailChangeAnotherUser() throws Exception {
		
		mvc.perform(post("/api/core/users/{id}/email-change-request", ADMIN_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_ADMIN_ID))
				.content(MomokoUtils.toJson(form())))
				.andExpect(status().is(403));
		
		verify(mailSender, never()).send(any());
	}

	/**
     * Trying with invalid data.
	 * @throws Exception 
	 * @throws JsonProcessingException 
     */
	@Test
	public void tryingWithInvalidData() throws JsonProcessingException, Exception {
		
    	// try with null newEmail and password
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(new UsuarioEntity())))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(2)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems(
						"updatedUser.newEmail",
						"updatedUser.password")));
    	
		UsuarioEntity updatedUser = new UsuarioEntity();
		updatedUser.setPassword("");
		updatedUser.setNewEmail("");
		
    	// try with null newEmail and password
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(updatedUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(4)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems(
						"updatedUser.newEmail",
						"updatedUser.password")));

		// try with invalid newEmail
		updatedUser = form();
		updatedUser.setNewEmail("an-invalid-email");
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(updatedUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(1)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems("updatedUser.newEmail")));

		// try with wrong password
		updatedUser = form();
		updatedUser.setPassword("wrong-password");
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(updatedUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(1)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems("updatedUser.password")));

		// try with null password
		updatedUser = form();
		updatedUser.setPassword(null);
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(updatedUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(1)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems("updatedUser.password")));

		// try with an existing email
		updatedUser = form();
		updatedUser.setNewEmail(ADMIN_EMAIL);;
		mvc.perform(post("/api/core/users/{id}/email-change-request", UNVERIFIED_USER_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(UNVERIFIED_USER_ID))
				.content(MomokoUtils.toJson(updatedUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(1)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems("updatedUser.newEmail")));
		
		verify(mailSender, never()).send(any());
	}
}
