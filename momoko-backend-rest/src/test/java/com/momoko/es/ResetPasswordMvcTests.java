package com.momoko.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momoko.es.commons.domain.ResetPasswordForm;
import com.momoko.es.commons.security.JwtService;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.jpa.util.MomokoUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ResetPasswordMvcTests extends AbstractMvcTests {
	
	private String forgotPasswordCode;
	
	@Autowired
	private JwtService jwtService;
	
	@Before
	public void setUp() {
		
		forgotPasswordCode = jwtService.createToken(
				JwtService.FORGOT_PASSWORD_AUDIENCE,
				ADMIN_EMAIL, 60000L);
	}

	@Test
	public void testResetPassword() throws Exception {
		
		final String NEW_PASSWORD = "newPassword!";
		
		//Thread.sleep(1001L);
		
		mvc.perform(post("/api/core/reset-password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(form(forgotPasswordCode, NEW_PASSWORD)))
		        .andExpect(status().is(200))
				.andExpect(header().string(LecUtils.TOKEN_RESPONSE_HEADER_NAME, containsString(".")))
				.andExpect(jsonPath("$.userId").value(ADMIN_ID));
		
		// New password should work
		login(ADMIN_EMAIL, NEW_PASSWORD);
		
	    // Repeating shouldn't work
		mvc.perform(post("/api/core/reset-password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(form(forgotPasswordCode, NEW_PASSWORD)))
		        .andExpect(status().is(401));
	}
	
	@Test
	public void testResetPasswordInvalidData() throws Exception {
		
		// Wrong code
		mvc.perform(post("/api/core/reset-password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(form("wrong-code", "abc99!")))
		        .andExpect(status().is(401));

		// Blank password
		mvc.perform(post("/api/core/reset-password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(form(forgotPasswordCode, "")))
		        .andExpect(status().is(422));

		// Invalid password
		mvc.perform(post("/api/core/reset-password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(form(forgotPasswordCode, "abc")))
		        .andExpect(status().is(422));
	}
	
	private String form(String code, String newPassword) throws JsonProcessingException {
		
		ResetPasswordForm form = new ResetPasswordForm();
		form.setCode(code);
		form.setNewPassword(newPassword);
		
		return MomokoUtils.toJson(form);
	}
}
