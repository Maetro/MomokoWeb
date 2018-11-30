package com.momoko.es;

import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.jpa.user.UsuarioEntity;
import com.momoko.es.jpa.util.MomokoUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql({"/test-data/initialize.sql", "/test-data/finalize.sql"})
public class SignupMvcTests extends AbstractMvcTests {
	
	@Test
	public void testSignupWithInvalidData() throws Exception {
		
		UsuarioEntity invalidUser = new UsuarioEntity("abc", "user1", null);

		mvc.perform(post("/api/core/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(MomokoUtils.toJson(invalidUser)))
				.andExpect(status().is(422))
				.andExpect(jsonPath("$.errors[*].field").value(hasSize(4)))
				.andExpect(jsonPath("$.errors[*].field").value(hasItems(
					"user.email", "user.password", "user.usuarioLogin")));

		verify(mailSender, never()).send(any());
	}

	@Test
	public void testSignup() throws Exception {

		UsuarioEntity user = new UsuarioEntity("alexparejapress@gmail.com", "dinosauriosatanico", "UsuarioEntity Foo");

		mvc.perform(post("/api/core/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(MomokoUtils.toJson(user)))
				.andExpect(status().is(201))
				.andExpect(header().string(LecUtils.TOKEN_RESPONSE_HEADER_NAME, containsString(".")))
				.andExpect(jsonPath("$.userId").exists())
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.usuarioEmail").value("alexparejapress@gmail.com"))
				.andExpect(jsonPath("$.roles").value(hasSize(1)))
				.andExpect(jsonPath("$.roles[0]").value("UNVERIFIED"))
				.andExpect(jsonPath("$.tag.name").value("UsuarioEntity Foo"))
				.andExpect(jsonPath("$.unverified").value(true))
				.andExpect(jsonPath("$.blocked").value(false))
				.andExpect(jsonPath("$.admin").value(false))
				.andExpect(jsonPath("$.goodUser").value(false))
				.andExpect(jsonPath("$.goodAdmin").value(false));

		verify(mailSender).send(any());

		// Ensure that password got encrypted
		Assert.assertNotEquals("user123", usuarioRepository.findByEmail("alexparejapress@gmail.com").get().getPassword());
	}

//	@Test
//	public void testSignupLoggedIn() throws Exception {
//
//		String adminToken = login("admin@example.com", "admin!");
//
//		UsuarioEntity user = new UsuarioEntity("user1@example.com", "user123", "UsuarioEntity 1");
//
//		mvc.perform(post("/api/core/users")
//				.header(MomokoSecurityConfig.TOKEN_REQUEST_HEADER_NAME, adminToken)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(MomokoUtils.toJson(user)))
//				.andExpect(status().is(403));
//	}
//
	@Test
	public void testSignupDuplicateEmail() throws Exception {

		UsuarioEntity user = new UsuarioEntity("user@example.com", "user123", "UsuarioEntity");

		mvc.perform(post("/api/core/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(MomokoUtils.toJson(user)))
				.andExpect(status().is(422));
		
		verify(mailSender, never()).send(any());
	}
}
